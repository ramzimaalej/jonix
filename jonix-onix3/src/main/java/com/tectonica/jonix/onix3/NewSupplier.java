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

import java.util.List;

import com.tectonica.jonix.DU;
import com.tectonica.jonix.codelist.RecordSourceTypes;

/*
 * NOTE: THIS IS AN AUTO-GENERATED FILE, DON'T EDIT IT
 */

public class NewSupplier
{
	public static final String refname = "NewSupplier";
	public static final String shortname = "newsupplier";

	public String datestamp; // dt.DateOrDateTime
	public RecordSourceTypes sourcetype;
	public String sourcename;

	public List<SupplierIdentifier> supplierIdentifiers; // OneOrMore
	public SupplierName supplierName; // Optional
	public List<TelephoneNumber> telephoneNumbers; // ZeroOrMore
	public List<FaxNumber> faxNumbers; // ZeroOrMore
	public List<EmailAddress> emailAddresss; // ZeroOrMore

	public static NewSupplier fromDoc(org.w3c.dom.Element element)
	{
		final NewSupplier x = new NewSupplier();

		x.datestamp = DU.getAttribute(element, "datestamp");
		x.sourcetype = RecordSourceTypes.byValue(DU.getAttribute(element, "sourcetype"));
		x.sourcename = DU.getAttribute(element, "sourcename");

		DU.forElementsOf(element, new DU.ElementListener()
		{
			@Override
			public void onElement(org.w3c.dom.Element element)
			{
				final String name = element.getNodeName();
				if (name.equals(SupplierIdentifier.refname) || name.equals(SupplierIdentifier.shortname))
					x.supplierIdentifiers = DU.addToList(x.supplierIdentifiers, SupplierIdentifier.fromDoc(element));
				else if (name.equals(SupplierName.refname) || name.equals(SupplierName.shortname))
					x.supplierName = SupplierName.fromDoc(element);
				else if (name.equals(TelephoneNumber.refname) || name.equals(TelephoneNumber.shortname))
					x.telephoneNumbers = DU.addToList(x.telephoneNumbers, TelephoneNumber.fromDoc(element));
				else if (name.equals(FaxNumber.refname) || name.equals(FaxNumber.shortname))
					x.faxNumbers = DU.addToList(x.faxNumbers, FaxNumber.fromDoc(element));
				else if (name.equals(EmailAddress.refname) || name.equals(EmailAddress.shortname))
					x.emailAddresss = DU.addToList(x.emailAddresss, EmailAddress.fromDoc(element));
			}
		});

		return x;
	}

	public String getSupplierNameValue()
	{
		return (supplierName == null) ? null : supplierName.value;
	}
}