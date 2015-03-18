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

public class Sender
{
	public static final String refname = "Sender";
	public static final String shortname = "sender";

	public String datestamp; // dt.DateOrDateTime
	public List3 sourcetype;
	public String sourcename;

	public List<SenderIdentifier> senderIdentifiers; // OneOrMore
	public SenderName senderName; // Optional
	public ContactName contactName; // Optional
	public EmailAddress emailAddress; // Optional

	public static Sender fromDoc(org.w3c.dom.Element element)
	{
		final Sender x = new Sender();

		x.datestamp = DU.getAttribute(element, "datestamp");
		x.sourcetype = List3.byValue(DU.getAttribute(element, "sourcetype"));
		x.sourcename = DU.getAttribute(element, "sourcename");

		DU.forElementsOf(element, new DU.ElementListener()
		{
			@Override
			public void onElement(org.w3c.dom.Element element)
			{
				final String name = element.getNodeName();
				if (name.equalsIgnoreCase(SenderIdentifier.refname) || name.equalsIgnoreCase(SenderIdentifier.shortname))
					x.senderIdentifiers = DU.addToList(x.senderIdentifiers, SenderIdentifier.fromDoc(element));
				else if (name.equalsIgnoreCase(SenderName.refname) || name.equalsIgnoreCase(SenderName.shortname))
					x.senderName = SenderName.fromDoc(element);
				else if (name.equalsIgnoreCase(ContactName.refname) || name.equalsIgnoreCase(ContactName.shortname))
					x.contactName = ContactName.fromDoc(element);
				else if (name.equalsIgnoreCase(EmailAddress.refname) || name.equalsIgnoreCase(EmailAddress.shortname))
					x.emailAddress = EmailAddress.fromDoc(element);
			}
		});

		return x;
	}
}