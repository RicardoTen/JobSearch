package com.boss.util;

public class ErrorMessage {

	public static String errorCode(int errorCode) {
		String errorMsg;
		if (errorCode == 165) {
			errorMsg = "图片短边错误";
		} else if (errorCode == 207) {
			errorMsg = "验证码错误";
		} else if (errorCode == 202) {
			errorMsg = "该账号已存在";
		}

		else {
			errorMsg = errorCode + "";
		}

		return errorMsg;
	}

}
