/*
 * Copyright (C) 2012 Zach Melamed
 * 
 * Latest version available online at https://github.com/zach-m/jonix
 * Contact me at zach@tectonica.co.il
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tectonica.jonix.codegen.generator;

import java.io.PrintStream;

import com.tectonica.jonix.codegen.generator.GenUtil.FieldInfo;
import com.tectonica.jonix.codegen.generator.GenUtil.TypeInfo;
import com.tectonica.jonix.codegen.metadata.OnixAttribute;
import com.tectonica.jonix.codegen.metadata.OnixClass;
import com.tectonica.jonix.codegen.metadata.OnixCompositeDef;
import com.tectonica.jonix.codegen.metadata.OnixCompositeMember;
import com.tectonica.jonix.codegen.metadata.OnixConst;
import com.tectonica.jonix.codegen.metadata.OnixDoc;
import com.tectonica.jonix.codegen.metadata.OnixElementDef;
import com.tectonica.jonix.codegen.metadata.OnixFlagDef;
import com.tectonica.jonix.codegen.metadata.OnixMetadata;
import com.tectonica.jonix.codegen.metadata.OnixStruct;
import com.tectonica.jonix.codegen.metadata.OnixStructMember;
import com.tectonica.jonix.codegen.metadata.OnixStructMember.TransformationType;

public class OnixClassGen
{
	private static final String EQUALS = "equals"; // turn to "equalsIgnoreCase" to assume case-insensitive XML

	private final String packageName;
	private final String folderName;

	private final OnixMetadata ref;

	public OnixClassGen(String basePackage, String baseFolder, String subfolder, OnixMetadata ref)
	{
		packageName = basePackage + "." + subfolder;
		folderName = baseFolder + "/" + subfolder;
		this.ref = ref;
		GenUtil.prepareOutputFolder(folderName);
	}

	public void generate(OnixClass onixClass)
	{
		try
		{
			String fileName = folderName + "/" + onixClass.name + ".java";

			try (PrintStream p = new PrintStream(fileName, "UTF-8"))
			{
				if (onixClass instanceof OnixCompositeDef)
					writeCompositeClass((OnixCompositeDef) onixClass, p);
				else if (onixClass instanceof OnixElementDef)
					writeElementClass((OnixElementDef) onixClass, p);
				else if (onixClass instanceof OnixFlagDef)
					writeFlagClass((OnixFlagDef) onixClass, p);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void writeCompositeClass(OnixCompositeDef composite, PrintStream p)
	{
		final boolean isDataComposite = ref.jonixStructs.containsKey(composite.name);
		final String markerInterfaceName = isDataComposite ? "OnixDataComposite" : "OnixSuperComposite";

		p.println(Comments.Copyright);
		p.printf("package %s;\n", packageName);
		p.println();
		p.println("import java.io.Serializable;");
		p.println("import java.util.List;");
		p.println("import java.util.ArrayList;");
		p.println();
		p.printf("import %s.JPU;\n", GenUtil.COMMON_PACKAGE);
		p.printf("import %s.OnixComposite.%s;\n", GenUtil.COMMON_PACKAGE, markerInterfaceName);
		p.printf("import %s.codelist.*;\n", GenUtil.COMMON_PACKAGE);
		p.printf("import %s.struct.*;\n", GenUtil.COMMON_PACKAGE);
		p.println();
		p.println(Comments.AutoGen);

		printOnixDoc(p, composite.onixDoc);
		p.printf("public class %s implements %s, Serializable\n", composite.name, markerInterfaceName);
		p.printf("{\n");

		declareConstsAndAttributes(p, composite);

		p.println();
		printCaptionComment(p, "MEMBERS");

		// declare members
		for (OnixCompositeMember member : composite.members)
		{
			final FieldInfo fi = GenUtil.fieldInfoOf(member);
			p.println();
			p.printf("   /**\n");
			p.printf("    * %s\n", fi.comment);
			p.printf("    */\n");
			p.printf("   public %s %s;\n", fi.type, fi.name, fi.comment);
		}

		p.println();
		printCaptionComment(p, "SERVICES");

		// default-constructor
		p.println();
		p.printf("   public %s()\n", composite.name);
		p.printf("   {}\n");

		// constructor
		p.println();
		p.printf("   public %s(org.w3c.dom.Element element)\n", composite.name);
		p.printf("   {\n");

		setInitializers(composite, p);

		p.println();
		p.printf("      JPU.forElementsOf(element, new JPU.ElementListener()\n");
		p.printf("      {\n");
		p.printf("         @Override\n");
		p.printf("         public void onElement(org.w3c.dom.Element element)\n");
		p.printf("         {\n");
		p.printf("            final String name = element.getNodeName();\n");
		boolean first = true;
		for (OnixCompositeMember m : composite.members)
		{
			final String className = m.className;
			final String field = GenUtil.fieldOf(className);
			p.print("            ");
			if (first)
				first = false;
			else
				p.print("else ");
			p.printf("if (name.%s(%s.refname) || name.%s(%s.shortname))\n", EQUALS, className, EQUALS, className);
			if (m.cardinality.singular)
				p.printf("               %s = new %s(element);\n", field, className);
			else
				p.printf("               %ss = JPU.addToList(%ss, new %s(element));\n", field, field, className);
		}
		p.printf("         }\n");
		p.printf("      });\n");

		p.printf("   }\n");

		// generate getters for members that have a single value (or single collection of values)
		for (OnixCompositeMember m : composite.members)
		{
			// declare direct value getters for element-members
			final OnixElementDef element = ref.onixElements.get(m.className);
			if (element != null)
			{
				p.println();
				boolean isEnum = element.valueMember.simpleType.isEnum();
				if (!isEnum) // no need to provide format information on enums, they are parsed by the system
				{
					if (element.onixDoc != null && element.onixDoc.format != null && !element.onixDoc.format.isEmpty())
					{
						p.printf("   /**\n");
						p.printf("   * Raw Format: %s\n", element.onixDoc.format);
						p.printf("   */\n");
					}
				}
				final TypeInfo ti = GenUtil.typeInfoOf(element.valueMember.simpleType);
				final String field = GenUtil.fieldOf(m.className);
				String javaType = ti.javaType;
				if (element.isSpaceable)
					javaType = "java.util.Set<" + javaType + ">";
				if (m.cardinality.singular)
				{
					final String caption = element.isSpaceable ? "Set" : "Value";
					p.printf("   public %s get%s%s()\n", javaType, m.className, caption);
					p.printf("   {\n");
					p.printf("      return (%s == null) ? null : %s.value;\n", field, field);
					p.printf("   }\n");
				}
				else
				{
					final String caption = element.isSpaceable ? "Sets" : "Values";
					p.printf("   public List<%s> get%s%s()\n", javaType, m.className, caption);
					p.printf("   {\n");
					p.printf("      if (%ss != null) \n", field);
					p.printf("      { \n");
					p.printf("         List<%s> list = new ArrayList<>(); \n", javaType);
					p.printf("         for (%s i : %ss) \n", m.className, field);
					p.printf("            list.add(i.value); \n");
					p.printf("         return list; \n");
					p.printf("      } \n");
					p.printf("      return null;\n");
					p.printf("   }\n");
				}
			}

			// declare direct boolean getter for flag-members
			final OnixFlagDef flag = ref.onixFlags.get(m.className);
			if (flag != null)
			{
				final String field = GenUtil.fieldOf(flag.name);
				if (m.cardinality.singular)
				{
					p.println();
					p.printf("   public boolean is%s()\n", flag.name);
					p.printf("   {\n");
					p.printf("      return (%s != null);\n", field);
					p.printf("   }\n");
				}
				else
				{
					throw new RuntimeException("can't handle multiple flags");
				}
			}
		}

		// declare struct finder for keyed, repeatable elements
		for (OnixCompositeMember m : composite.members)
		{
			if (!m.cardinality.singular)
			{
				final OnixStruct struct = ref.unifiedStructs.get(m.className);
				if (struct != null && struct.isKeyed())
				{
					final String structName = "Jonix" + m.className;
					final OnixCompositeMember keyMember = struct.keyMember.dataMember;
					final OnixElementDef keyClass = (OnixElementDef) keyMember.onixClass;
					final TypeInfo kti = GenUtil.typeInfoOf(keyClass.valueMember.simpleType);
					String keyClassName = keyMember.className;
					if (struct.keyMember.transformationNeededInVersion == ref.onixVersion
							&& struct.keyMember.transformationType == TransformationType.ChangeClassName)
						keyClassName = struct.keyMember.transformationHint;
					final String keyField = GenUtil.fieldOf(keyClassName);
					final String memberfield = GenUtil.fieldOf(m.className) + "s";
					final String caption = keyClass.isSpaceable ? "Set" : "Value";

					p.println();
					p.printf("   public %s find%s(%s %s)\n", structName, m.className, kti.javaType, keyField);
					p.printf("   {\n");
					p.printf("      if (%s != null)\n", memberfield);
					p.printf("      {\n");
					p.printf("         for (%s x : %s)\n", m.className, memberfield);
					p.printf("         {\n");
					p.printf("            if (x.get%s%s() == %s)\n", keyClassName, caption, keyField);
					p.printf("               return x.as%s();\n", structName);
					p.printf("         }\n");
					p.printf("      }\n");
					p.printf("      return null;\n");
					p.printf("   }\n");

					p.println();
					p.printf("   public List<%s> find%ss(java.util.Set<%s> %ss)\n", structName, m.className,
							kti.javaType, keyField);
					p.printf("   {\n");
					p.printf("      if (%s != null)\n", memberfield);
					p.printf("      {\n");
					p.printf("         List<%s> matches = new ArrayList<>();\n", structName);
					p.printf("         for (%s x : %s)\n", m.className, memberfield);
					p.printf("         {\n");
					p.printf("            if (%ss == null || %ss.contains(x.get%s%s()))\n", keyField, keyField,
							keyClassName, caption);
					p.printf("               matches.add(x.as%s());\n", structName);
					p.printf("         }\n");
					p.printf("         return matches;\n", structName);
					p.printf("      }\n");
					p.printf("      return null;\n");
					p.printf("   }\n");
				}
			}
		}

		// declare struct provider on composites that can be represented as one
		final OnixStruct struct = ref.unifiedStructs.get(composite.name);
		if (struct != null)
		{
			final String structName = "Jonix" + composite.name;

			p.println();
			p.printf("   public %s as%s()\n", structName, structName);
			p.printf("   {\n");
			p.printf("      %s x = new %s();\n", structName, structName);

			for (OnixStructMember structMember : struct.allMembers())
			{
				final OnixCompositeMember member = structMember.dataMember;
				if (member.onixClass instanceof OnixElementDef)
				{
					String field = GenUtil.fieldOf(member.className);
					if (!member.cardinality.singular)
						field += "s";
					boolean transformationNeeded = (structMember.transformationNeededInVersion == ref.onixVersion);
					if (transformationNeeded)
					{
						switch (structMember.transformationType)
						{
						case SingularToMultiple:
							p.printf("      x.%s = java.util.Arrays.asList(get%sValue());\n", field, member.className);
							break;
						case ChangeClassName:
							p.printf("      x.%s = get%sValue();\n", field, structMember.transformationHint);
							break;
						default:
							p.printf("      x.%s = JPU.convert%s(get%sValue());\n", field,
									structMember.transformationType.name(), member.className);
						}
					}
					else
					{
						String caption = ((OnixElementDef) member.onixClass).isSpaceable ? "Set" : "Value";
						if (!member.cardinality.singular)
							caption += "s";
						p.printf("      x.%s = get%s%s();\n", field, member.className, caption);
					}
				}
				else
				// i.e. (member.onixClass instanceof OnixFlagDef)
				{
					p.printf("      x.is%s = is%s();\n", member.className, member.className);
				}
			}

			p.printf("      return x;\n");
			p.printf("   }\n");
		}

		p.println("}");
	}

	private void writeElementClass(OnixElementDef element, PrintStream p)
	{
		p.println(Comments.Copyright);
		p.printf("package %s;\n", packageName);
		p.println();
		p.println("import java.io.Serializable;");
		p.printf("import %s.JPU;\n", GenUtil.COMMON_PACKAGE);
		p.printf("import %s.OnixElement;\n", GenUtil.COMMON_PACKAGE);
		p.printf("import %s.codelist.*;\n", GenUtil.COMMON_PACKAGE);
		p.println();
		p.println(Comments.AutoGen);

		printOnixDoc(p, element.onixDoc);
		p.printf("public class %s implements OnixElement, Serializable\n", element.name);
		p.printf("{\n");

		declareConstsAndAttributes(p, element);

		p.println();
		printCaptionComment(p, "VALUE MEMBER");

		// declare value
		final TypeInfo ti = GenUtil.typeInfoOf(element.valueMember.simpleType);
		p.println();
		if (ti.comment != null)
		{
			p.printf("   /**\n");
			if (element.onixDoc != null && element.onixDoc.format != null && !element.onixDoc.format.isEmpty())
				p.printf("   * Raw Format: %s<p>\n", element.onixDoc.format);
			p.printf("   * %s\n", ti.comment);
			p.printf("   */\n");
		}
		if (!element.isSpaceable)
			p.printf("   public %s value;\n", ti.javaType);
		else
			p.printf("   public java.util.Set<%s> value;\n", ti.javaType);

		p.println();
		printCaptionComment(p, "SERVICES");

		// default-constructor
		p.println();
		p.printf("   public %s()\n", element.name);
		p.printf("   {}\n");

		// constructor
		p.println();
		p.printf("   public %s(org.w3c.dom.Element element)\n", element.name);
		p.printf("   {\n");

		setInitializers(element, p);

		p.println();
		if (ti.isXHTML)
			p.printf("      value = JPU.getChildXHTML(element, true);\n");
		else if (!element.isSpaceable)
		{
			if (ti.isPrimitive)
				p.printf("      value = JPU.getContentAs%s(element);\n", ti.javaType);
			else
				p.printf("      value = %s.byCode(JPU.getContentAsString(element));\n", ti.javaType);
		}
		else
		{
			p.printf("      value = new java.util.HashSet<>();\n");
			p.printf("      for (String split : JPU.getContentAsString(element).trim().split(\" +\"))\n");
			if (ti.isPrimitive)
				p.printf("         value.add(%s.valueOf(split));\n", ti.javaType);
			else
				p.printf("         value.add(%s.byCode(split));\n", ti.javaType);
		}

		p.printf("   }\n");

		p.println("}");
	}

	private void writeFlagClass(OnixFlagDef flag, PrintStream p)
	{
		p.println(Comments.Copyright);
		p.printf("package %s;\n", packageName);
		p.println();
		p.println("import java.io.Serializable;");
		p.printf("import %s.JPU;\n", GenUtil.COMMON_PACKAGE);
		p.printf("import %s.OnixFlag;\n", GenUtil.COMMON_PACKAGE);
		p.printf("import %s.codelist.*;\n", GenUtil.COMMON_PACKAGE);
		p.println();
		p.println(Comments.AutoGen);

		printOnixDoc(p, flag.onixDoc);
		p.printf("public class %s implements OnixFlag, Serializable\n", flag.name);
		p.printf("{\n");

		declareConstsAndAttributes(p, flag);

		p.println();
		printCaptionComment(p, "CONSTRUCTORS");

		// default-constructor
		p.println();
		p.printf("   public %s()\n", flag.name);
		p.printf("   {}\n");

		// constructor
		p.println();
		p.printf("   public %s(org.w3c.dom.Element element)\n", flag.name);
		p.printf("   {\n");

		setInitializers(flag, p);

		p.printf("   }\n");

		p.println("}");
	}

	private void printOnixDoc(PrintStream p, OnixDoc onixDoc)
	{
		if (onixDoc != null)
		{
			p.printf("/**\n");
			p.printf(" * %s\n", onixDoc.toHtml());
			p.printf(" */\n");
		}
	}

	private void declareConstsAndAttributes(PrintStream p, OnixClass clz)
	{
		p.printf("   private static final long serialVersionUID = 1L;\n\n");

		for (OnixConst c : clz.consts)
			p.printf("   public static final String %s = \"%s\";\n", c.name, c.value);

		p.println();
		printCaptionComment(p, "ATTRIBUTES");
		for (OnixAttribute a : clz.attributes)
		{
			final TypeInfo ti = GenUtil.typeInfoOf(a);
			p.println();
			if (ti.comment != null)
			{
				p.printf("   /**\n");
				p.printf("    * %s\n", ti.comment);
				p.printf("    */\n");
			}
			p.printf("   public %s %s;\n", ti.javaType, a.name);
		}
	}

	private void setInitializers(OnixClass clz, PrintStream p)
	{
		for (OnixAttribute a : clz.attributes)
		{
			String enumType = a.getEnumName();

			if (enumType == null)
				p.printf("      %s = JPU.getAttribute(element, \"%s\");\n", a.name, a.name);
			else
				p.printf("      %s = %s.byCode(JPU.getAttribute(element, \"%s\"));\n", a.name, enumType, a.name);
		}
	}

	private void printCaptionComment(PrintStream p, String caption)
	{
		p.println("   /////////////////////////////////////////////////////////////////////////////////");
		p.println("   // " + caption);
		p.println("   /////////////////////////////////////////////////////////////////////////////////");
	}
}
