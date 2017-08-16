package com.muni.test.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muni.test.dto.UrlParameter;

public class OauthTools {
	
	private static final Logger logger = LoggerFactory.getLogger(OauthTools.class);
	
	public static String getRandomString(int length){
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
	}
	
	public static String generateUrlParameter(UrlParameter params){
		@SuppressWarnings("unchecked")
		Map<String, String> map = new ObjectMapper().convertValue(params, Map.class);
		
		String parameters = "";		
		for (Entry<String, String> e : map.entrySet()) {
			if (e.getValue() != null && !e.getValue().isEmpty()) {
				parameters += e.getKey().replaceAll("([A-Z])", "_$1").toLowerCase() + "=" + e.getValue() + "&";
			}
		}
		parameters = parameters.substring(0, parameters.length()-1);
		logger.info("Parameters: " + parameters);

		return parameters;
	}
	
	public static UrlParameter parameterStringToObject(String paramStr){
		Map<String, String> map = new HashMap<String, String>();		
		for(String str: paramStr.split("&")){
			String key = str.split("=")[0];
			String value = str.split("=")[1];
			
			while(key.indexOf("_") > 0){
				int index = key.indexOf("_");
				String uc = key.substring(index+1, index+2).toUpperCase();
				key = key.substring(0, index) + uc + key.substring(index+2);
			}
			
			map.put(key, value);
		}	
		
		return new ObjectMapper().convertValue(map, UrlParameter.class);
	}
	
	public static boolean tokenIsValid(){
		//TODO
		return false;
	}

}
