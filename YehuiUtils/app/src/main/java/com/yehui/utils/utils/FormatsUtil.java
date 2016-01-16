package com.yehui.utils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通过正则表达式判断格式是否正确
 *
 */
public class FormatsUtil {
	
	//判断邮件格式
	public static boolean isEmail(String email){
		//邮件格式
		String str="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p=Pattern.compile(str);
		Matcher m=p.matcher(email);
		return m.matches();
	}

}
