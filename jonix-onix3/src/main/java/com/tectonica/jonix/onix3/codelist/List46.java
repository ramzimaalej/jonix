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

package com.tectonica.jonix.onix3.codelist;

import java.util.HashMap;
import java.util.Map;

/**
 * Sales rights type code
 */
public enum List46
{
	/**
	 * May only be used with the ONIX 3 <ROWSalesRightsType> element.
	 */
	Sales_rights_unknown_or_unstated_for_any_reason("00"), //

	For_sale_with_exclusive_rights_in_the_specified_countries_or_territories("01"), //

	For_sale_with_non_exclusive_rights_in_the_specified_countries_or_territories("02"), //

	Not_for_sale_in_the_specified_countries_or_territories__reason_unspecified_("03"), //

	Not_for_sale_in_the_specified_countries__but_publisher_holds_exclusive_rights_in_those_countries_or_territories_("04"), //

	Not_for_sale_in_the_specified_countries__publisher_holds_non_exclusive_rights_in_those_countries_or_territories_("05"), //

	Not_for_sale_in_the_specified_countries__because_publisher_does_not_hold_rights_in_those_countries_or_territories_("06"), //

	/**
	 * Only for use with ONIX 3. Deprecated.
	 */
	For_sale_with_exclusive_rights_in_the_specified_countries_or_territories__sales_restriction_applies_("07"), //

	/**
	 * Only for use with ONIX 3. Deprecated.
	 */
	For_sale_with_non_exclusive_rights_in_the_specified_countries_or_territories__sales_restriction_applies_("08");

	public final String value;

	private List46(String value)
	{
		this.value = value;
	}

	private static Map<String, List46> map;

	private static Map<String, List46> map()
	{
		if (map == null)
		{
			map = new HashMap<>();
			for (List46 e : values())
				map.put(e.value, e);
		}
		return map;
	}

	public static List46 byValue(String value)
	{
		if (value == null || value.isEmpty())
			return null;
		return map().get(value);
	}
}