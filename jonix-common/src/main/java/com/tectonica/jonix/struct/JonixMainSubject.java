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

package com.tectonica.jonix.struct;

import java.io.Serializable;

import com.tectonica.jonix.codelist.MainSubjectSchemeIdentifiers;

/*
 * NOTE: THIS IS AN AUTO-GENERATED FILE, DON'T EDIT MANUALLY
 */

@SuppressWarnings("serial")
public class JonixMainSubject implements Serializable
{
	public MainSubjectSchemeIdentifiers mainSubjectSchemeIdentifier;

	/**
	 * Raw Format: Free form. Suggested maximum length 10 characters, for consistency with other version number
	 * elements.
	 * <p>
	 * (type: NonEmptyString)
	 */
	public String subjectSchemeVersion;

	/**
	 * Raw Format: Variable-length, alphanumeric, suggested maximum length 20 characters.
	 * <p>
	 * (type: NonEmptyString)
	 */
	public String subjectCode;

	/**
	 * Raw Format: Variable-length text, suggested maximum length 100 characters.
	 * <p>
	 * (type: NonEmptyString)
	 */
	public String subjectHeadingText;
}
