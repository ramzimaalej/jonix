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

import com.tectonica.jonix.JPU;
import com.tectonica.jonix.OnixElement;
import com.tectonica.jonix.codelist.RecordSourceTypes;
import com.tectonica.jonix.codelist.SalesRestrictionTypes;

/*
 * NOTE: THIS IS AN AUTO-GENERATED FILE, DON'T EDIT MANUALLY
 */

/**
 * <h1>Sales restriction type code</h1>
 * <p>
 * An ONIX code which identifies a non-territorial sales restriction. Mandatory in each occurrence of the
 * &lt;SalesRestriction&gt; composite, and non-repeating.
 * </p>
 * <table border='1' cellpadding='3'>
 * <tr>
 * <td>Format</td>
 * <td>Fixed-length, two digits</td>
 * </tr>
 * <tr>
 * <td>Codelist</td>
 * <td>List 71</td>
 * </tr>
 * <tr>
 * <td>Reference name</td>
 * <td>&lt;SalesRestrictionType&gt;</td>
 * </tr>
 * <tr>
 * <td>Short tag</td>
 * <td>&lt;b381&gt;</td>
 * </tr>
 * <tr>
 * <td>Cardinality</td>
 * <td>1</td>
 * </tr>
 * <tr>
 * <td>Example</td>
 * <td>&lt;b381&gt;07&lt;/b381&gt; (Schools-only edition)</td>
 * </tr>
 * </table>
 */
public class SalesRestrictionType implements OnixElement, Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String refname = "SalesRestrictionType";
	public static final String shortname = "b381";

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
	// VALUE MEMBER
	// ///////////////////////////////////////////////////////////////////////////////

	public SalesRestrictionTypes value;

	// ///////////////////////////////////////////////////////////////////////////////
	// SERVICES
	// ///////////////////////////////////////////////////////////////////////////////

	public SalesRestrictionType()
	{}

	public SalesRestrictionType(org.w3c.dom.Element element)
	{
		datestamp = JPU.getAttribute(element, "datestamp");
		sourcetype = RecordSourceTypes.byCode(JPU.getAttribute(element, "sourcetype"));
		sourcename = JPU.getAttribute(element, "sourcename");

		value = SalesRestrictionTypes.byCode(JPU.getContentAsString(element));
	}
}
