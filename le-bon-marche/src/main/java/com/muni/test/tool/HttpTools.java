package com.muni.test.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.muni.test.exception.HttpException;

public class HttpTools {
	private static final Logger logger = LoggerFactory.getLogger(HttpTools.class);
	
	public static String httpRequest(String method, String targetURL, Map<String, String> header, String urlParameters) throws HttpException{
		
		logger.info("Http Request " + method + ": " + targetURL);
		logger.info("Parameters: " + urlParameters);
		
		try {
			URL obj = new URL(targetURL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(method.toUpperCase());
//			con.setRequestProperty("User-Agent", USER_AGENT);
			
			if(header != null && !header.isEmpty()){
				for (Entry<String, String> e : header.entrySet()) {
					logger.info("Add request head: " + e.getKey() + ":" + e.getValue());
					con.setRequestProperty(e.getKey(), e.getValue());
				}
			}
			
			if(method.toUpperCase().equals("POST")){
				con.setDoOutput(true);
				OutputStream os = con.getOutputStream();
				os.write(urlParameters.getBytes());
				os.flush();
				os.close();
			}

			int responseCode = con.getResponseCode();
			logger.info("Response Code: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				logger.info("Response: " + response.toString());				
				return response.toString();
			} else {
				throw new HttpException("Error! Error code = " + responseCode);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new HttpException("IOException: " + e.getMessage());
		}		
	}

}
