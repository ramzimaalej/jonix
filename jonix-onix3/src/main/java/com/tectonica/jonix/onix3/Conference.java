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
import java.util.List;

import com.tectonica.jonix.JPU;
import com.tectonica.jonix.OnixComposite.OnixSuperComposite;
import com.tectonica.jonix.codelist.RecordSourceTypes;

/*
 * NOTE: THIS IS AN AUTO-GENERATED FILE, DON'T EDIT MANUALLY
 */

/**
 * <h1>Conference composite</h1>
 * <p>
 * A group of data elements which together describe a conference to which the product is related. Optional, and
 * repeatable if the product contains material from two or more conferences.
 * </p>
 * <table border='1' cellpadding='3'>
 * <tr>
 * <td>Reference name</td>
 * <td>&lt;Conference&gt;</td>
 * </tr>
 * <tr>
 * <td>Short tag</td>
 * <td>&lt;conference&gt;</td>
 * </tr>
 * <tr>
 * <td>Cardinality</td>
 * <td>0&#8230;n</td>
 * </tr>
 * </table>
 */
public class Conference implements OnixSuperComposite, Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String refname = "Conference";
	public static final String shortname = "conference";

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
	public ConferenceRole conferenceRole;

	/**
	 * (this field is required)
	 */
	public ConferenceName conferenceName;

	/**
	 * (this field is optional)
	 */
	public ConferenceAcronym conferenceAcronym;

	/**
	 * (this field is optional)
	 */
	public ConferenceNumber conferenceNumber;

	/**
	 * (this field is optional)
	 */
	public ConferenceTheme conferenceTheme;

	/**
	 * (this field is optional)
	 */
	public ConferenceDate conferenceDate;

	/**
	 * (this field is optional)
	 */
	public ConferencePlace conferencePlace;

	/**
	 * (this list may be empty)
	 */
	public List<ConferenceSponsor> conferenceSponsors;

	/**
	 * (this list may be empty)
	 */
	public List<Website> websites;

	// ///////////////////////////////////////////////////////////////////////////////
	// SERVICES
	// ///////////////////////////////////////////////////////////////////////////////

	public Conference()
	{}

	public Conference(org.w3c.dom.Element element)
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
				if (name.equals(ConferenceRole.refname) || name.equals(ConferenceRole.shortname))
					conferenceRole = new ConferenceRole(element);
				else if (name.equals(ConferenceName.refname) || name.equals(ConferenceName.shortname))
					conferenceName = new ConferenceName(element);
				else if (name.equals(ConferenceAcronym.refname) || name.equals(ConferenceAcronym.shortname))
					conferenceAcronym = new ConferenceAcronym(element);
				else if (name.equals(ConferenceNumber.refname) || name.equals(ConferenceNumber.shortname))
					conferenceNumber = new ConferenceNumber(element);
				else if (name.equals(ConferenceTheme.refname) || name.equals(ConferenceTheme.shortname))
					conferenceTheme = new ConferenceTheme(element);
				else if (name.equals(ConferenceDate.refname) || name.equals(ConferenceDate.shortname))
					conferenceDate = new ConferenceDate(element);
				else if (name.equals(ConferencePlace.refname) || name.equals(ConferencePlace.shortname))
					conferencePlace = new ConferencePlace(element);
				else if (name.equals(ConferenceSponsor.refname) || name.equals(ConferenceSponsor.shortname))
					conferenceSponsors = JPU.addToList(conferenceSponsors, new ConferenceSponsor(element));
				else if (name.equals(Website.refname) || name.equals(Website.shortname))
					websites = JPU.addToList(websites, new Website(element));
			}
		});
	}

	/**
	 * Raw Format: Fixed-length, two digits
	 */
	public String getConferenceRoleValue()
	{
		return (conferenceRole == null) ? null : conferenceRole.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum length 200 characters
	 */
	public String getConferenceNameValue()
	{
		return (conferenceName == null) ? null : conferenceName.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum length 20 characters
	 */
	public String getConferenceAcronymValue()
	{
		return (conferenceAcronym == null) ? null : conferenceAcronym.value;
	}

	/**
	 * Raw Format: Variable-length integer, suggested maximum length 4 digits
	 */
	public Integer getConferenceNumberValue()
	{
		return (conferenceNumber == null) ? null : conferenceNumber.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum length 200 characters. XHTML is enabled in this element - see
	 * Using XHTML, HTML or XML with ONIX text fields - but is strongly discouraged
	 */
	public String getConferenceThemeValue()
	{
		return (conferenceTheme == null) ? null : conferenceTheme.value;
	}

	/**
	 * Raw Format: As specified by the value in the dateformat attribute, or the default of YYYY if the attribute is
	 * missing. Note that the dateformat attribute allows exact dates to be supplied if necessary, including the cases
	 * where a conference spreads over a range of dates or the date can only be supplied as a text string
	 */
	public String getConferenceDateValue()
	{
		return (conferenceDate == null) ? null : conferenceDate.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum length 100 characters
	 */
	public String getConferencePlaceValue()
	{
		return (conferencePlace == null) ? null : conferencePlace.value;
	}
}
