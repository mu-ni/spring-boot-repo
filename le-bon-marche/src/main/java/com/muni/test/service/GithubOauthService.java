package com.muni.test.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muni.test.dto.GithubUserInfo;
import com.muni.test.dto.UrlParameter;
import com.muni.test.exception.HttpException;
import com.muni.test.exception.OauthException;
import com.muni.test.tool.HttpTools;
import com.muni.test.tool.OauthTools;

@Component
public class GithubOauthService {
	
	private static final Logger logger = LoggerFactory.getLogger(GithubOauthService.class);
	
	@Value("${github.login.oauth.authorize.url}")
	private String authorizeUrl;
	
	@Value("${github.login.oauth.access.token.url}")
	private String accessTokenUrl;
	
	@Value("${github.login.oauth.redirect.url}")
	private String redirectUrl;
	
	@Value("${github.login.oauth.state}")
	private String state;
	
	@Value("${github.user.url}")
	private String userInfoUrl;
	
	@Value("${github.client.id}")
	private String clientId;
	
	@Value("${github.client.secret}")
	private String clientSecret;
	
	
	public String getAuthUrl(){		
		logger.info("getAuthUrl");
		
		UrlParameter params = new UrlParameter();
		params.setClientId(clientId);
		params.setRedirectUrl(redirectUrl);
		params.setState(state);
		params.setScope("user");
		
		String url = authorizeUrl + "?" + OauthTools.generateUrlParameter(params);
		logger.info("URL= " + url);
		
		return url;
	}
	
	public UrlParameter getAccessToken(String code, String state) throws OauthException{
		logger.info("getAccessToken");
		
		if(!this.state.equals(state)){
			throw new OauthException("State value has been changed");
		}
		
		UrlParameter params = new UrlParameter();
		params.setClientId(clientId);
		params.setClientSecret(clientSecret);
		params.setCode(code);
		params.setRedirectUrl(redirectUrl);
		params.setState(state);
		
		try {
			String resStr = HttpTools.httpRequest("POST", accessTokenUrl, null, OauthTools.generateUrlParameter(params));			
			logger.info("Response = " + resStr);
			
			return OauthTools.parameterStringToObject(resStr);			
		} catch (HttpException e) {
			logger.error(e.getMessage());			
			return null;
		}
	}
	
	public GithubUserInfo getUserInfo(String tokenType, String accessToken){
		logger.info("getUserInfo");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", tokenType + " " + accessToken);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try {
			String UserInfoJsonStr = HttpTools.httpRequest("GET", userInfoUrl, map, null);				
			GithubUserInfo userInfo = mapper.readValue(UserInfoJsonStr, GithubUserInfo.class);
			return userInfo;
		} catch (HttpException | IOException e) {
			logger.error(e.getMessage());
			return null;
		}		
	}
	
	

}
