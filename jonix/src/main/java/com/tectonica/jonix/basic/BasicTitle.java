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

import java.io.Serializable;

import com.tectonica.jonix.codelist.TitleTypes;
import com.tectonica.jonix.onix3.TitleElement;

@SuppressWarnings("serial")
public class BasicTitle implements Serializable
{
	public TitleTypes titleType;
	public String titleText;
	public String titleWithoutPrefix;
	public String subtitle;

	public BasicTitle extractFrom(com.tectonica.jonix.onix2.Title title)
	{
		titleType = title.getTitleTypeValue();
		titleText = noBreaks(title.getTitleTextValue());
		titleWithoutPrefix = noBreaks(title.getTitleWithoutPrefixValue());
		subtitle = noBreaks(title.getSubtitleValue());
		return this;
	}

	public BasicTitle extractFrom(com.tectonica.jonix.onix3.TitleDetail title)
	{
		TitleElement titleElement = title.titleElements.get(0); // at least 1 is mandatory
		titleType = title.getTitleTypeValue();
		titleText = noBreaks(titleElement.getTitleTextValue());
		titleWithoutPrefix = noBreaks(titleElement.getTitleWithoutPrefixValue());
		subtitle = noBreaks(titleElement.getSubtitleValue());
		return this;
	}

	private String noBreaks(String s)
	{
		return (s == null || s.isEmpty()) ? s : s.replaceAll("\\t|\\n|\\r", " ");
	}
}