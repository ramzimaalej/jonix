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

import com.tectonica.jonix.basic.BasicImprint;
import com.tectonica.jonix.onix2.Imprint;

/**
 * ONIX2 concrete implementation for {@link BasicImprint}
 * 
 * @author Zach Melamed
 */
public class BasicImprint2 extends BasicImprint
{
	private static final long serialVersionUID = 1L;

	public BasicImprint2(Imprint imprint)
	{
		// TODO: we should at least read one required field (unlike ImprintName)
		imprintName = imprint.getImprintNameValue();
	}

//	public BasicImprint2(com.tectonica.jonix.onix3.Imprint imprint)
//	{
//		// TODO: we should at least read one required field (unlike ImprintName)
//		imprintName = imprint.getImprintNameValue();
//	}
}