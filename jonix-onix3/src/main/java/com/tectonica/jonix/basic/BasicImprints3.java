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

package com.tectonica.jonix.basic;

import java.util.ArrayList;
import java.util.List;

import com.tectonica.jonix.basic.BasicImprint;
import com.tectonica.jonix.basic.BasicImprints;
import com.tectonica.jonix.onix3.Imprint;
import com.tectonica.jonix.onix3.Product;

/**
 * ONIX3 concrete implementation for {@link BasicImprints}
 * 
 * @author Zach Melamed
 */
public class BasicImprints3 extends BasicImprints
{
	private static final long serialVersionUID = 1L;

	private transient final Product product;

	public BasicImprints3(Product product)
	{
		this.product = product;
	}

	@Override
	protected List<BasicImprint> initialize()
	{
		List<BasicImprint> list = new ArrayList<>();
		if (product.publishingDetail.imprints != null)
		{
			for (Imprint imprint : product.publishingDetail.imprints)
				list.add(new BasicImprint3(imprint));
		}
		return list;
	}
}
