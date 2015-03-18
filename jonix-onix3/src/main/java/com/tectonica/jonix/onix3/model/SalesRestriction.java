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

package com.tectonica.jonix.onix3.model;

import java.util.List;

import com.tectonica.jonix.onix3.DU;
import com.tectonica.jonix.onix3.codelist.List3;

public class SalesRestriction
{
	public static final String refname = "SalesRestriction";
	public static final String shortname = "salesrestriction";

	public String datestamp; // dt.DateOrDateTime
	public List3 sourcetype;
	public String sourcename;

	public SalesRestrictionType salesRestrictionType; // Required
	public List<SalesOutlet> salesOutlets; // ZeroOrMore
	public List<SalesRestrictionNote> salesRestrictionNotes; // ZeroOrMore
	public StartDate startDate; // Optional
	public EndDate endDate; // Optional

	public static SalesRestriction fromDoc(org.w3c.dom.Element element)
	{
		final SalesRestriction x = new SalesRestriction();

		x.datestamp = DU.getAttribute(element, "datestamp");
		x.sourcetype = List3.byValue(DU.getAttribute(element, "sourcetype"));
		x.sourcename = DU.getAttribute(element, "sourcename");

		DU.forElementsOf(element, new DU.ElementListener()
		{
			@Override
			public void onElement(org.w3c.dom.Element element)
			{
				final String name = element.getNodeName();
				if (name.equalsIgnoreCase(SalesRestrictionType.refname) || name.equalsIgnoreCase(SalesRestrictionType.shortname))
					x.salesRestrictionType = SalesRestrictionType.fromDoc(element);
				else if (name.equalsIgnoreCase(SalesOutlet.refname) || name.equalsIgnoreCase(SalesOutlet.shortname))
					x.salesOutlets = DU.addToList(x.salesOutlets, SalesOutlet.fromDoc(element));
				else if (name.equalsIgnoreCase(SalesRestrictionNote.refname) || name.equalsIgnoreCase(SalesRestrictionNote.shortname))
					x.salesRestrictionNotes = DU.addToList(x.salesRestrictionNotes, SalesRestrictionNote.fromDoc(element));
				else if (name.equalsIgnoreCase(StartDate.refname) || name.equalsIgnoreCase(StartDate.shortname))
					x.startDate = StartDate.fromDoc(element);
				else if (name.equalsIgnoreCase(EndDate.refname) || name.equalsIgnoreCase(EndDate.shortname))
					x.endDate = EndDate.fromDoc(element);
			}
		});

		return x;
	}
}