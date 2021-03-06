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
import com.tectonica.jonix.OnixComposite.OnixSuperComposite;
import com.tectonica.jonix.codelist.NameCodeTypes;
import com.tectonica.jonix.codelist.ProductContactRoles;
import com.tectonica.jonix.codelist.RecordSourceTypes;
import com.tectonica.jonix.struct.JonixProductContactIdentifier;

/*
 * NOTE: THIS IS AN AUTO-GENERATED FILE, DON'T EDIT MANUALLY
 */

/**
 * <h1>Product contact composite</h1>
 * <p>
 * A group of data elements which together specify an organization (which may or may not be the publisher’s
 * representative) responsible for dealing with enquiries related to the product in the market.
 * </p>
 * <table border='1' cellpadding='3'>
 * <tr>
 * <td>Reference name</td>
 * <td>&lt;ProductContact&gt;</td>
 * </tr>
 * <tr>
 * <td>Short tag</td>
 * <td>&lt;productcontact&gt;</td>
 * </tr>
 * <tr>
 * <td>Cardinality</td>
 * <td>0&#8230;n</td>
 * </tr>
 * </table>
 */
public class ProductContact implements OnixSuperComposite, Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String refname = "ProductContact";
	public static final String shortname = "productcontact";

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
	 * (this field is required)
	 */
	public ProductContactRole productContactRole;

	/**
	 * (this list is required to contain at least one item)
	 */
	public List<ProductContactIdentifier> productContactIdentifiers;

	/**
	 * (this field is optional)
	 */
	public ProductContactName productContactName;

	/**
	 * (this field is optional)
	 */
	public ContactName contactName;

	/**
	 * (this field is optional)
	 */
	public EmailAddress emailAddress;

	// ///////////////////////////////////////////////////////////////////////////////
	// SERVICES
	// ///////////////////////////////////////////////////////////////////////////////

	public ProductContact()
	{}

	public ProductContact(org.w3c.dom.Element element)
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
				if (name.equals(ProductContactRole.refname) || name.equals(ProductContactRole.shortname))
					productContactRole = new ProductContactRole(element);
				else if (name.equals(ProductContactIdentifier.refname)
						|| name.equals(ProductContactIdentifier.shortname))
					productContactIdentifiers = JPU.addToList(productContactIdentifiers, new ProductContactIdentifier(
							element));
				else if (name.equals(ProductContactName.refname) || name.equals(ProductContactName.shortname))
					productContactName = new ProductContactName(element);
				else if (name.equals(ContactName.refname) || name.equals(ContactName.shortname))
					contactName = new ContactName(element);
				else if (name.equals(EmailAddress.refname) || name.equals(EmailAddress.shortname))
					emailAddress = new EmailAddress(element);
			}
		});
	}

	public ProductContactRoles getProductContactRoleValue()
	{
		return (productContactRole == null) ? null : productContactRole.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum 50 characters
	 */
	public String getProductContactNameValue()
	{
		return (productContactName == null) ? null : productContactName.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum 300 characters
	 */
	public String getContactNameValue()
	{
		return (contactName == null) ? null : contactName.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum length 100 characters
	 */
	public String getEmailAddressValue()
	{
		return (emailAddress == null) ? null : emailAddress.value;
	}

	public JonixProductContactIdentifier findProductContactIdentifier(NameCodeTypes productContactIDType)
	{
		if (productContactIdentifiers != null)
		{
			for (ProductContactIdentifier x : productContactIdentifiers)
			{
				if (x.getProductContactIDTypeValue() == productContactIDType)
					return x.asJonixProductContactIdentifier();
			}
		}
		return null;
	}

	public List<JonixProductContactIdentifier> findProductContactIdentifiers(
			java.util.Set<NameCodeTypes> productContactIDTypes)
	{
		if (productContactIdentifiers != null)
		{
			List<JonixProductContactIdentifier> matches = new ArrayList<>();
			for (ProductContactIdentifier x : productContactIdentifiers)
			{
				if (productContactIDTypes == null || productContactIDTypes.contains(x.getProductContactIDTypeValue()))
					matches.add(x.asJonixProductContactIdentifier());
			}
			return matches;
		}
		return null;
	}
}
