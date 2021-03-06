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
import com.tectonica.jonix.codelist.CurrencyCodes;
import com.tectonica.jonix.codelist.CurrencyZones;
import com.tectonica.jonix.codelist.DiscountCodeTypes;
import com.tectonica.jonix.codelist.PositionOnProducts;
import com.tectonica.jonix.codelist.PriceDateRoles;
import com.tectonica.jonix.codelist.PriceIdentifierTypes;
import com.tectonica.jonix.codelist.PriceStatuss;
import com.tectonica.jonix.codelist.PriceTypeQualifiers;
import com.tectonica.jonix.codelist.PriceTypes;
import com.tectonica.jonix.codelist.PrintedOnProducts;
import com.tectonica.jonix.codelist.RecordSourceTypes;
import com.tectonica.jonix.codelist.UnitOfPricings;
import com.tectonica.jonix.struct.JonixDiscountCoded;
import com.tectonica.jonix.struct.JonixPriceDate;
import com.tectonica.jonix.struct.JonixPriceIdentifier;

/*
 * NOTE: THIS IS AN AUTO-GENERATED FILE, DON'T EDIT MANUALLY
 */

/**
 * <h1>Price composite</h1>
 * <p>
 * A optional and repeatable group of data elements which together specify a unit price, used here to indicate a price
 * that will apply when the product is reissued. Deprecated in this context.
 * </p>
 * <table border='1' cellpadding='3'>
 * <tr>
 * <td>Reference name</td>
 * <td>&lt;Price&gt;</td>
 * </tr>
 * <tr>
 * <td>Short tag</td>
 * <td>&lt;price&gt;</td>
 * </tr>
 * <tr>
 * <td>Cardinality</td>
 * <td>0&#8230;n</td>
 * </tr>
 * </table>
 */
public class Price implements OnixSuperComposite, Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String refname = "Price";
	public static final String shortname = "price";

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
	 * (this list may be empty)
	 */
	public List<PriceIdentifier> priceIdentifiers;

	/**
	 * (this field is optional)
	 */
	public PriceType priceType;

	/**
	 * (this field is optional)
	 */
	public PriceQualifier priceQualifier;

	/**
	 * (this list may be empty)
	 */
	public List<PriceTypeDescription> priceTypeDescriptions;

	/**
	 * (this field is optional)
	 */
	public PricePer pricePer;

	/**
	 * (this list may be empty)
	 */
	public List<PriceCondition> priceConditions;

	/**
	 * (this field is optional)
	 */
	public MinimumOrderQuantity minimumOrderQuantity;

	/**
	 * (this list may be empty)
	 */
	public List<BatchBonus> batchBonuss;

	/**
	 * (this list may be empty)
	 */
	public List<DiscountCoded> discountCodeds;

	/**
	 * (this list may be empty)
	 */
	public List<Discount> discounts;

	/**
	 * (this field is optional)
	 */
	public PriceStatus priceStatus;

	/**
	 * (this field is optional)
	 */
	public PriceAmount priceAmount;

	/**
	 * (this field is optional)
	 */
	public PriceCoded priceCoded;

	/**
	 * (this list may be empty)
	 */
	public List<Tax> taxs;

	/**
	 * (this field is optional)
	 */
	public CurrencyCode currencyCode;

	/**
	 * (this field is optional)
	 */
	public Territory territory;

	/**
	 * (this field is optional)
	 */
	public CurrencyZone currencyZone;

	/**
	 * (this list may be empty)
	 */
	public List<ComparisonProductPrice> comparisonProductPrices;

	/**
	 * (this list may be empty)
	 */
	public List<PriceDate> priceDates;

	/**
	 * (this field is optional)
	 */
	public PrintedOnProduct printedOnProduct;

	/**
	 * (this field is optional)
	 */
	public PositionOnProduct positionOnProduct;

	// ///////////////////////////////////////////////////////////////////////////////
	// SERVICES
	// ///////////////////////////////////////////////////////////////////////////////

	public Price()
	{}

	public Price(org.w3c.dom.Element element)
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
				if (name.equals(PriceIdentifier.refname) || name.equals(PriceIdentifier.shortname))
					priceIdentifiers = JPU.addToList(priceIdentifiers, new PriceIdentifier(element));
				else if (name.equals(PriceType.refname) || name.equals(PriceType.shortname))
					priceType = new PriceType(element);
				else if (name.equals(PriceQualifier.refname) || name.equals(PriceQualifier.shortname))
					priceQualifier = new PriceQualifier(element);
				else if (name.equals(PriceTypeDescription.refname) || name.equals(PriceTypeDescription.shortname))
					priceTypeDescriptions = JPU.addToList(priceTypeDescriptions, new PriceTypeDescription(element));
				else if (name.equals(PricePer.refname) || name.equals(PricePer.shortname))
					pricePer = new PricePer(element);
				else if (name.equals(PriceCondition.refname) || name.equals(PriceCondition.shortname))
					priceConditions = JPU.addToList(priceConditions, new PriceCondition(element));
				else if (name.equals(MinimumOrderQuantity.refname) || name.equals(MinimumOrderQuantity.shortname))
					minimumOrderQuantity = new MinimumOrderQuantity(element);
				else if (name.equals(BatchBonus.refname) || name.equals(BatchBonus.shortname))
					batchBonuss = JPU.addToList(batchBonuss, new BatchBonus(element));
				else if (name.equals(DiscountCoded.refname) || name.equals(DiscountCoded.shortname))
					discountCodeds = JPU.addToList(discountCodeds, new DiscountCoded(element));
				else if (name.equals(Discount.refname) || name.equals(Discount.shortname))
					discounts = JPU.addToList(discounts, new Discount(element));
				else if (name.equals(PriceStatus.refname) || name.equals(PriceStatus.shortname))
					priceStatus = new PriceStatus(element);
				else if (name.equals(PriceAmount.refname) || name.equals(PriceAmount.shortname))
					priceAmount = new PriceAmount(element);
				else if (name.equals(PriceCoded.refname) || name.equals(PriceCoded.shortname))
					priceCoded = new PriceCoded(element);
				else if (name.equals(Tax.refname) || name.equals(Tax.shortname))
					taxs = JPU.addToList(taxs, new Tax(element));
				else if (name.equals(CurrencyCode.refname) || name.equals(CurrencyCode.shortname))
					currencyCode = new CurrencyCode(element);
				else if (name.equals(Territory.refname) || name.equals(Territory.shortname))
					territory = new Territory(element);
				else if (name.equals(CurrencyZone.refname) || name.equals(CurrencyZone.shortname))
					currencyZone = new CurrencyZone(element);
				else if (name.equals(ComparisonProductPrice.refname) || name.equals(ComparisonProductPrice.shortname))
					comparisonProductPrices = JPU.addToList(comparisonProductPrices,
							new ComparisonProductPrice(element));
				else if (name.equals(PriceDate.refname) || name.equals(PriceDate.shortname))
					priceDates = JPU.addToList(priceDates, new PriceDate(element));
				else if (name.equals(PrintedOnProduct.refname) || name.equals(PrintedOnProduct.shortname))
					printedOnProduct = new PrintedOnProduct(element);
				else if (name.equals(PositionOnProduct.refname) || name.equals(PositionOnProduct.shortname))
					positionOnProduct = new PositionOnProduct(element);
			}
		});
	}

	public PriceTypes getPriceTypeValue()
	{
		return (priceType == null) ? null : priceType.value;
	}

	public PriceTypeQualifiers getPriceQualifierValue()
	{
		return (priceQualifier == null) ? null : priceQualifier.value;
	}

	/**
	 * Raw Format: Variable-length text, suggested maximum length 200 characters
	 */
	public List<String> getPriceTypeDescriptionValues()
	{
		if (priceTypeDescriptions != null)
		{
			List<String> list = new ArrayList<>();
			for (PriceTypeDescription i : priceTypeDescriptions)
				list.add(i.value);
			return list;
		}
		return null;
	}

	public UnitOfPricings getPricePerValue()
	{
		return (pricePer == null) ? null : pricePer.value;
	}

	/**
	 * Raw Format: Variable-length integer, suggested maximum length 4 digits
	 */
	public Integer getMinimumOrderQuantityValue()
	{
		return (minimumOrderQuantity == null) ? null : minimumOrderQuantity.value;
	}

	public PriceStatuss getPriceStatusValue()
	{
		return (priceStatus == null) ? null : priceStatus.value;
	}

	/**
	 * Raw Format: Variable length real number, with explicit decimal point when required, suggested maximum length 12
	 * characters
	 */
	public Double getPriceAmountValue()
	{
		return (priceAmount == null) ? null : priceAmount.value;
	}

	public CurrencyCodes getCurrencyCodeValue()
	{
		return (currencyCode == null) ? null : currencyCode.value;
	}

	public CurrencyZones getCurrencyZoneValue()
	{
		return (currencyZone == null) ? null : currencyZone.value;
	}

	public PrintedOnProducts getPrintedOnProductValue()
	{
		return (printedOnProduct == null) ? null : printedOnProduct.value;
	}

	public PositionOnProducts getPositionOnProductValue()
	{
		return (positionOnProduct == null) ? null : positionOnProduct.value;
	}

	public JonixPriceIdentifier findPriceIdentifier(PriceIdentifierTypes priceIDType)
	{
		if (priceIdentifiers != null)
		{
			for (PriceIdentifier x : priceIdentifiers)
			{
				if (x.getPriceIDTypeValue() == priceIDType)
					return x.asJonixPriceIdentifier();
			}
		}
		return null;
	}

	public List<JonixPriceIdentifier> findPriceIdentifiers(java.util.Set<PriceIdentifierTypes> priceIDTypes)
	{
		if (priceIdentifiers != null)
		{
			List<JonixPriceIdentifier> matches = new ArrayList<>();
			for (PriceIdentifier x : priceIdentifiers)
			{
				if (priceIDTypes == null || priceIDTypes.contains(x.getPriceIDTypeValue()))
					matches.add(x.asJonixPriceIdentifier());
			}
			return matches;
		}
		return null;
	}

	public JonixDiscountCoded findDiscountCoded(DiscountCodeTypes discountCodeType)
	{
		if (discountCodeds != null)
		{
			for (DiscountCoded x : discountCodeds)
			{
				if (x.getDiscountCodeTypeValue() == discountCodeType)
					return x.asJonixDiscountCoded();
			}
		}
		return null;
	}

	public List<JonixDiscountCoded> findDiscountCodeds(java.util.Set<DiscountCodeTypes> discountCodeTypes)
	{
		if (discountCodeds != null)
		{
			List<JonixDiscountCoded> matches = new ArrayList<>();
			for (DiscountCoded x : discountCodeds)
			{
				if (discountCodeTypes == null || discountCodeTypes.contains(x.getDiscountCodeTypeValue()))
					matches.add(x.asJonixDiscountCoded());
			}
			return matches;
		}
		return null;
	}

	public JonixPriceDate findPriceDate(PriceDateRoles priceDateRole)
	{
		if (priceDates != null)
		{
			for (PriceDate x : priceDates)
			{
				if (x.getPriceDateRoleValue() == priceDateRole)
					return x.asJonixPriceDate();
			}
		}
		return null;
	}

	public List<JonixPriceDate> findPriceDates(java.util.Set<PriceDateRoles> priceDateRoles)
	{
		if (priceDates != null)
		{
			List<JonixPriceDate> matches = new ArrayList<>();
			for (PriceDate x : priceDates)
			{
				if (priceDateRoles == null || priceDateRoles.contains(x.getPriceDateRoleValue()))
					matches.add(x.asJonixPriceDate());
			}
			return matches;
		}
		return null;
	}
}
