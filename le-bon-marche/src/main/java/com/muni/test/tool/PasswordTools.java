package com.muni.test.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordTools {
	private static final Logger logger = LoggerFactory.getLogger(PasswordTools.class);
	
	public static String getMd5Hash(String plainText) {		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());

	        byte byteData[] = md.digest();
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }	        
	        return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("PasswordTools getMd5Hash(): " + e.getMessage());
			return null;
		}
	}

}
