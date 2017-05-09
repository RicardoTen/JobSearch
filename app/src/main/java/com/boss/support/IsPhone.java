package com.boss.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsPhone {

	/**
	 * @param phone
	 * @return 是否手机号
	 */
	public static boolean isPhone(String phone) {
		Pattern p = Pattern
				.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
		Matcher m = p.matcher(phone);
		return m.matches();
	}
}
