package com.cares.cervello.utility;

public class PasswordManager {
	
	public static String encryptString(String str){
	    int code;
	    String result = "";
	    for (int i = 0; i < str.length(); i++) {
	      code = Math.round((float) Math.random()*8+1);
	      result += code + Integer.toHexString( ((int) str.charAt(i) ) ^ code )+"-";
	    }
	    return result.substring(0, result.lastIndexOf("-"));
	  }

	public static String decryptString(String str){
	    str = str.replace("-", "");
	    String result = "";
	    for (int i = 0; i < str.length(); i+=3) {
	      String hex =  str.substring(i+1, i+3);
	      result += (char) (Integer.parseInt(hex, 16) ^ (Integer.parseInt(String.valueOf(str.charAt(i)))));
	    }
	    return result;
	  }
}
