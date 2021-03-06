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

package com.tectonica.jonix.onix2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tectonica.jonix.JPU;
import com.tectonica.jonix.OnixComposite.OnixSuperComposite;
import com.tectonica.jonix.codelist.CurrencyCodes;
import com.tectonica.jonix.codelist.DefaultLinearUnits;
import com.tectonica.jonix.codelist.DefaultUnitOfWeights;
import com.tectonica.jonix.codelist.LanguageCodes;
import com.tectonica.jonix.codelist.NameCodeTypes;
import com.tectonica.jonix.codelist.PriceTypes;
import com.tectonica.jonix.codelist.RecordSourceTypes;
import com.tectonica.jonix.codelist.TextCaseFlags;
import com.tectonica.jonix.codelist.TextFormats;
import com.tectonica.jonix.codelist.TransliterationSchemes;
import com.tectonica.jonix.struct.JonixAddresseeIdentifier;
import com.tectonica.jonix.struct.JonixSenderIdentifier;

/*
 * NOTE: THIS IS AN AUTO-GENERATED FILE, DON'T EDIT MANUALLY
 */

/**
 * <h1>Header composite</h1>
 * <p>
 * A group of data elements which together constitute a message header. The elements may alternatively be sent without
 * being grouped into a composite, but the composite approach is recommended since it makes it easier to maintain a
 * standard header “package” to drop into any new ONIX Product Information Message. <strong>Note that the Sender and
 * Addressee Identifier composites can only be used within the Header composite, and future extensions to the Header
 * will be defined only within the composite.</strong>
 * </p>
 * <table border='1' cellpadding='3'>
 * <tr>
 * <td>Reference name</td>
 * <td>&lt;Header&gt;</td>
 * </tr>
 * <tr>
 * <td>Short tag</td>
 * <td>&lt;header&gt;</td>
 * </tr>
 * </table>
 */
public class Header implements OnixSuperComposite, Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String refname = "Header";
	public static final String shortname = "header";

	// ///////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	// ///////////////////////////////////////////////////////////////////////////////

	public TextFormats textformat;

	public TextCaseFlags textcase;

	public LanguageCodes language;

	public TransliterationSchemes transliteration;

	/**
	 * (type: DateOrDateTime)
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
	public FromEANNumber fromEANNumber;

	/**
	 * (this field is optional)
	 */
	public FromSAN fromSAN;

	/**
	 * (this list may be empty)
	 */
	public List<SenderIdentifier> senderIdentifiers;

	/**
	 * (this field is optional)
	 */
	public FromCompany fromCompany;

	/**
	 * (this field is optional)
	 */
	public FromPerson fromPerson;

	/**
	 * (this field is optional)
	 */
	public FromEmail fromEmail;

	/**
	 * (this field is optional)
	 */
	public ToEANNumber toEANNumber;

	/**
	 * (this field is optional)
	 */
	public ToSAN toSAN;

	/**
	 * (this list may be empty)
	 */
	public List<AddresseeIdentifier> addresseeIdentifiers;

	/**
	 * (this field is optional)
	 */
	public ToCompany toCompany;

	/**
	 * (this field is optional)
	 */
	public ToPerson toPerson;

	/**
	 * (this field is optional)
	 */
	public MessageNumber messageNumber;

	/**
	 * (this field is optional)
	 */
	public MessageRepeat messageRepeat;

	/**
	 * (this field is required)
	 */
	public SentDate sentDate;

	/**
	 * (this field is optional)
	 */
	public MessageNote messageNote;

	/**
	 * (this field is optional)
	 */
	public DefaultLanguageOfText defaultLanguageOfText;

	/**
	 * (this field is optional)
	 */
	public DefaultPriceTypeCode defaultPriceTypeCode;

	/**
	 * (this field is optional)
	 */
	public DefaultCurrencyCode defaultCurrencyCode;

	/**
	 * (this field is optional)
	 */
	public DefaultLinearUnit defaultLinearUnit;

	/**
	 * (this field is optional)
	 */
	public DefaultWeightUnit defaultWeightUnit;

	/**
	 * (this field is optional)
	 */
	public DefaultClassOfTrade defaultClassOfTrade;

	// ///////////////////////////////////////////////////////////////////////////////
	// SERVICES
	// ///////////////////////////////////////////////////////////////////////////////

	public Header()
	{}

	public Header(org.w3c.dom.Element element)
	{
		textformat = TextFormats.byCode(JPU.getAttribute(element, "textformat"));
		textcase = TextCaseFlags.byCode(JPU.getAttribute(element, "textcase"));
		language = LanguageCodes.byCode(JPU.getAttribute(element, "language"));
		transliteration = TransliterationSchemes.byCode(JPU.getAttribute(element, "transliteration"));
		datestamp = JPU.getAttribute(element, "datestamp");
		sourcetype = RecordSourceTypes.byCode(JPU.getAttribute(element, "sourcetype"));
		sourcename = JPU.getAttribute(element, "sourcename");

		JPU.forElementsOf(element, new JPU.ElementListener()
		{
			@Override
			public void onElement(org.w3c.dom.Element element)
			{
				final String name = element.getNodeName();
				if (name.equals(FromEANNumber.refname) || name.equals(FromEANNumber.shortname))
					fromEANNumber = new FromEANNumber(element);
				else if (name.equals(FromSAN.refname) || name.equals(FromSAN.shortname))
					fromSAN = new FromSAN(element);
				else if (name.equals(SenderIdentifier.refname) || name.equals(SenderIdentifier.shortname))
					senderIdentifiers = JPU.addToList(senderIdentifiers, new SenderIdentifier(element));
				else if (name.equals(FromCompany.refname) || name.equals(FromCompany.shortname))
					fromCompany = new FromCompany(element);
				else if (name.equals(FromPerson.refname) || name.equals(FromPerson.shortname))
					fromPerson = new FromPerson(element);
				else if (name.equals(FromEmail.refname) || name.equals(FromEmail.shortname))
					fromEmail = new FromEmail(element);
				else if (name.equals(ToEANNumber.refname) || name.equals(ToEANNumber.shortname))
					toEANNumber = new ToEANNumber(element);
				else if (name.equals(ToSAN.refname) || name.equals(ToSAN.shortname))
					toSAN = new ToSAN(element);
				else if (name.equals(AddresseeIdentifier.refname) || name.equals(AddresseeIdentifier.shortname))
					addresseeIdentifiers = JPU.addToList(addresseeIdentifiers, new AddresseeIdentifier(element));
				else if (name.equals(ToCompany.refname) || name.equals(ToCompany.shortname))
					toCompany = new ToCompany(element);
				else if (name.equals(ToPerson.refname) || name.equals(ToPerson.shortname))
					toPerson = new ToPerson(element);
				else if (name.equals(MessageNumber.refname) || name.equals(MessageNumber.shortname))
					messageNumber = new MessageNumber(element);
				else if (name.equals(MessageRepeat.refname) || name.equals(MessageRepeat.shortname))
					messageRepeat = new MessageRepeat(element);
				else if (name.equals(SentDate.refname) || name.equals(SentDate.shortname))
					sentDate = new SentDate(element);
				else if (name.equals(MessageNote.refname) || name.equals(MessageNote.shortname))
					messageNote = new MessageNote(element);
				else if (name.equals(DefaultLanguageOfText.refname) || name.equals(DefaultLanguageOfText.shortname))
					defaultLanguageOfText = new DefaultLanguageOfText(element);
				else if (name.equals(DefaultPriceTypeCode.refname) || name.equals(DefaultPriceTypeCode.shortname))
					defaultPriceTypeCode = new DefaultPriceTypeCode(element);
				else if (name.equals(DefaultCurrencyCode.refname) || name.equals(DefaultCurrencyCode.shortname))
					defaultCurrencyCode = new DefaultCurrencyCode(element);
				else if (name.equals(DefaultLinearUnit.refname) || name.equals(DefaultLinearUnit.shortname))
					defaultLinearUnit = new DefaultLinearUnit(element);
				else if (name.equals(DefaultWeightUnit.refname) || name.equals(DefaultWeightUnit.shortname))
					defaultWeightUnit = new DefaultWeightUnit(element);
				else if (name.equals(DefaultClassOfTrade.refname) || name.equals(DefaultClassOfTrade.shortname))
					defaultClassOfTrade = new DefaultClassOfTrade(element);
			}
		});
	}

	/**
	 * Raw Format: Fixed-length, thirteen numeric digits, of which the last is a check digit.
	 */
	public String getFromEANNumberValue()
	{
		return (fromEANNumber == null) ? null : fromEANNumber.value;
	}

	/**
	 * Raw Format: Fixed-length, seven characters. The first six are numeric digits, and the seventh is a check
	 * character which may be a numeric digit or letter X.
	 */
	public String getFromSANValue()
	{
		return (fromSAN == null) ? null : fromSAN.value;
	}

	/**
	 * Raw Format: Variable-length ASCII text, suggested maximum 30 characters
	 */
	public String getFromCompanyValue()
	{
		return (fromCompany == null) ? null : fromCompany.value;
	}

	/**
	 * Raw Format: Variable-length ASCII text, suggested maximum 300 characters
	 */
	public String getFromPersonValue()
	{
		return (fromPerson == null) ? null : fromPerson.value;
	}

	/**
	 * Raw Format: Variable-length ASCII text, suggested maximum 100 characters
	 */
	public String getFromEmailValue()
	{
		return (fromEmail == null) ? null : fromEmail.value;
	}

	/**
	 * Raw Format: Fixed-length, thirteen numeric digits, of which the last is a check digit.
	 */
	public String getToEANNumberValue()
	{
		return (toEANNumber == null) ? null : toEANNumber.value;
	}

	/**
	 * Raw Format: Fixed-length, seven characters. The first six are numeric digits, and the seventh is a check
	 * character which may be a numeric digit or letter X.
	 */
	public String getToSANValue()
	{
		return (toSAN == null) ? null : toSAN.value;
	}

	/**
	 * Raw Format: Variable-length ASCII text, suggested maximum 30 characters
	 */
	public String getToCompanyValue()
	{
		return (toCompany == null) ? null : toCompany.value;
	}

	/**
	 * Raw Format: Variable-length ASCII text, suggested maximum 300 characters
	 */
	public String getToPersonValue()
	{
		return (toPerson == null) ? null : toPerson.value;
	}

	/**
	 * Raw Format: Variable-length integer,
	 */
	public String getMessageNumberValue()
	{
		return (messageNumber == null) ? null : messageNumber.value;
	}

	/**
	 * Raw Format: Variable-length integer
	 */
	public String getMessageRepeatValue()
	{
		return (messageRepeat == null) ? null : messageRepeat.value;
	}

	/**
	 * Raw Format: Eight or twelve numeric digits only (YYYYMMDD or YYYYMMDDHHMM)
	 */
	public String getSentDateValue()
	{
		return (sentDate == null) ? null : sentDate.value;
	}

	/**
	 * Raw Format: Variable-length ASCII text, suggested maximum 500 characters
	 */
	public String getMessageNoteValue()
	{
		return (messageNote == null) ? null : messageNote.value;
	}

	public LanguageCodes getDefaultLanguageOfTextValue()
	{
		return (defaultLanguageOfText == null) ? null : defaultLanguageOfText.value;
	}

	public PriceTypes getDefaultPriceTypeCodeValue()
	{
		return (defaultPriceTypeCode == null) ? null : defaultPriceTypeCode.value;
	}

	public CurrencyCodes getDefaultCurrencyCodeValue()
	{
		return (defaultCurrencyCode == null) ? null : defaultCurrencyCode.value;
	}

	public DefaultLinearUnits getDefaultLinearUnitValue()
	{
		return (defaultLinearUnit == null) ? null : defaultLinearUnit.value;
	}

	public DefaultUnitOfWeights getDefaultWeightUnitValue()
	{
		return (defaultWeightUnit == null) ? null : defaultWeightUnit.value;
	}

	/**
	 * Raw Format: Variable length ASCII text, suggested maximum length 50 characters
	 */
	public String getDefaultClassOfTradeValue()
	{
		return (defaultClassOfTrade == null) ? null : defaultClassOfTrade.value;
	}

	public JonixSenderIdentifier findSenderIdentifier(NameCodeTypes senderIDType)
	{
		if (senderIdentifiers != null)
		{
			for (SenderIdentifier x : senderIdentifiers)
			{
				if (x.getSenderIDTypeValue() == senderIDType)
					return x.asJonixSenderIdentifier();
			}
		}
		return null;
	}

	public List<JonixSenderIdentifier> findSenderIdentifiers(java.util.Set<NameCodeTypes> senderIDTypes)
	{
		if (senderIdentifiers != null)
		{
			List<JonixSenderIdentifier> matches = new ArrayList<>();
			for (SenderIdentifier x : senderIdentifiers)
			{
				if (senderIDTypes == null || senderIDTypes.contains(x.getSenderIDTypeValue()))
					matches.add(x.asJonixSenderIdentifier());
			}
			return matches;
		}
		return null;
	}

	public JonixAddresseeIdentifier findAddresseeIdentifier(NameCodeTypes addresseeIDType)
	{
		if (addresseeIdentifiers != null)
		{
			for (AddresseeIdentifier x : addresseeIdentifiers)
			{
				if (x.getAddresseeIDTypeValue() == addresseeIDType)
					return x.asJonixAddresseeIdentifier();
			}
		}
		return null;
	}

	public List<JonixAddresseeIdentifier> findAddresseeIdentifiers(java.util.Set<NameCodeTypes> addresseeIDTypes)
	{
		if (addresseeIdentifiers != null)
		{
			List<JonixAddresseeIdentifier> matches = new ArrayList<>();
			for (AddresseeIdentifier x : addresseeIdentifiers)
			{
				if (addresseeIDTypes == null || addresseeIDTypes.contains(x.getAddresseeIDTypeValue()))
					matches.add(x.asJonixAddresseeIdentifier());
			}
			return matches;
		}
		return null;
	}
}
