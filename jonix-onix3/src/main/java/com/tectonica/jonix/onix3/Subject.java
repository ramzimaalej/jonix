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

package com.tectonica.jonix.onix3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tectonica.jonix.JPU;
import com.tectonica.jonix.OnixComposite.OnixDataComposite;
import com.tectonica.jonix.codelist.RecordSourceTypes;
import com.tectonica.jonix.codelist.SubjectSchemeIdentifiers;
import com.tectonica.jonix.struct.JonixSubject;

/*
 * NOTE: THIS IS AN AUTO-GENERATED FILE, DON'T EDIT MANUALLY
 */

/**
 * <h1>Subject composite</h1>
 * <p>
 * A group of data elements which together describe a subject of a content item. Optional and repeatable.
 * </p>
 * <table border='1' cellpadding='3'>
 * <tr>
 * <td>Reference name</td>
 * <td>&lt;Subject&gt;</td>
 * </tr>
 * <tr>
 * <td>Short tag</td>
 * <td>&lt;subject&gt;</td>
 * </tr>
 * <tr>
 * <td>Cardinality</td>
 * <td>0&#8230;n</td>
 * </tr>
 * </table>
 */
public class Subject implements OnixDataComposite, Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String refname = "Subject";
	public static final String shortname = "subject";

	// ///////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	// ///////////////////////////////////////////////////////////////////////////////

	/**
	 * (type: dt.DateOrDateTime)
	 */
	public String datestamp;

	public RecordSourceTypes sourcetype;

	public String sourcename;

	// ///////////////////////////////////////////////////////////////////////////////
	// MEMBERS
	// ///////////////////////////////////////////////////////////////////////////////

	/**
	 * (this field is optional)
	 */
	public MainSubject mainSubject;

	/**
	 * (this field is required)
	 */
	public SubjectSchemeIdentifier subjectSchemeIdentifier;

	/**
	 * (this field is optional)
	 */
	public SubjectSchemeName subjectSchemeName;

	/**
	 * (this field is optional)
	 */
	public SubjectSchemeVersion subjectSchemeVersion;

	/**
	 * (this field is required)
	 */
	public SubjectCode subjectCode;

	/**
	 * (this list may be empty)
	 */
	public List<SubjectHeadingText> subjectHeadingTexts;

	// ///////////////////////////////////////////////////////////////////////////////
	// SERVICES
	// ///////////////////////////////////////////////////////////////////////////////

	public Subject()
	{}

	public Subject(org.w3c.dom.Element element)
	{
		datestamp = JPU.getAttribute(element, "datestamp");
		sourcetype = RecordSourceTypes.byCode(JPU.getAttribute(element, "sourcetype"));
		sourcename = JPU.getAttribute(element, "sourcename");

		JPU.forElementsOf(element, new JPU.ElementListener()
		{
			@Override
			public void onElement(org.w3c.dom.Element element)
			{
				final String name = element.getNodeName();
				if (name.equals(MainSubject.refname) || name.equals(MainSubject.shortname))
					mainSubject = new MainSubject(element);
				else if (name.equals(SubjectSchemeIdentifier.refname) || name.equals(SubjectSchemeIdentifier.shortname))
					subjectSchemeIdentifier = new SubjectSchemeIdentifier(element);
				else if (name.equals(SubjectSchemeName.refname) || name.equals(SubjectSchemeName.shortname))
					subjectSchemeName = new SubjectSchemeName(element);
				else if (name.equals(SubjectSchemeVersion.refname) || name.equals(SubjectSchemeVersion.shortname))
					subjectSchemeVersion = new SubjectSchemeVersion(element);
				else if (name.equals(SubjectCode.refname) || name.equals(SubjectCode.shortname))
					subjectCode = new SubjectCode(element);
				else if (name.equals(SubjectHeadingText.refname) || name.equals(SubjectHeadingText.shortname))
					subjectHeadingTexts = JPU.addToList(subjectHeadingTexts, new SubjectHeadingText(element));
			}
		});
	}

	public boolean isMainSubject()
	{
		return (mainSubject != null);
	}

	public SubjectSchemeIdentifiers getSubjectSchemeIdentifierValue()
	{
		return (subjectSchemeIdentifier == null) ? null : subjectSchemeIdentifier.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum length 100 characters
	 */
	public String getSubjectSchemeNameValue()
	{
		return (subjectSchemeName == null) ? null : subjectSchemeName.value;
	}

	/**
	 * Raw Format: Free form. Suggested maximum length 10 characters, for consistency with other version number elements
	 */
	public String getSubjectSchemeVersionValue()
	{
		return (subjectSchemeVersion == null) ? null : subjectSchemeVersion.value;
	}

	/**
	 * Raw Format: Variable-length, alphanumeric, suggested maximum length 20 characters
	 */
	public String getSubjectCodeValue()
	{
		return (subjectCode == null) ? null : subjectCode.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum length 250 characters
	 */
	public List<String> getSubjectHeadingTextValues()
	{
		if (subjectHeadingTexts != null)
		{
			List<String> list = new ArrayList<>();
			for (SubjectHeadingText i : subjectHeadingTexts)
				list.add(i.value);
			return list;
		}
		return null;
	}

	public JonixSubject asJonixSubject()
	{
		JonixSubject x = new JonixSubject();
		x.subjectCode = getSubjectCodeValue();
		x.subjectHeadingTexts = getSubjectHeadingTextValues();
		x.subjectSchemeIdentifier = getSubjectSchemeIdentifierValue();
		x.subjectSchemeName = getSubjectSchemeNameValue();
		x.subjectSchemeVersion = getSubjectSchemeVersionValue();
		return x;
	}
}
