package com.muni.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muni.test.dto.GithubUserInfo;
import com.muni.test.dto.WebSocket;
import com.muni.test.dto.Response;
import com.muni.test.dto.UrlParameter;
import com.muni.test.entity.User;
import com.muni.test.enumerate.UserRoleType;
import com.muni.test.enumerate.UserSignupSource;
import com.muni.test.enumerate.WebsocketEvent;
import com.muni.test.exception.OauthException;
import com.muni.test.repo.UserRepository;
import com.muni.test.service.GithubOauthService;
import com.muni.test.tool.PasswordTools;
import com.muni.test.tool.TokenTools;
import com.muni.test.websocket.WebSocketServer;

@RestController
public class Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GithubOauthService githubOauthService;
	@Autowired
	private TokenTools tokenTools;
	
	@PostMapping("/username")
	public @ResponseBody String usernameExist(@RequestBody(required = true) User user){
		logger.info("POST /username, username = " + user.getUsername());
		
		int resCode = 0;
		String status = "Valid username!";
		
		if(userRepository.findByUsername(user.getUsername()).size() != 0){
			logger.info("Username " + user.getUsername() + " already exist in DB!");			
			resCode = 1;
			status = "Username exist!";			
		}

		try {
			Response response = new Response();	
			response.setCode(resCode);
			response.setStatus(status);
			String resJson = new ObjectMapper().writeValueAsString(response);			
			logger.info("Response: " + resJson);
			
			return resJson;
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());			
			return "{\"code\":\"9\",\"status\":\"Internal error\"}";
		}
	}
	
	@PostMapping("/signup")
	public @ResponseBody String signup(@RequestBody(required = true) User user){
		logger.info("POST /signup");
		
		user.setPassword(PasswordTools.getMd5Hash(user.getPassword()));
		User currentUser = userRepository.save(user);
		logger.info("User saved in DB");
		
		logger.info("Grant token to user");
		tokenTools.grantAccessToken(currentUser);
		
		try {
			Response response = new Response();
			response.setCode(0);
			response.setStatus("Signup success!");
			response.setUser(currentUser);
			response.setAccessToken(tokenTools.getAccessToken(currentUser));
			String resJson = new ObjectMapper().writeValueAsString(response);			
			logger.info("Response: " + resJson);
			
			return resJson;
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			return "{\"code\":\"9\",\"status\":\"Internal error\"}";
		}		
	}
	
	@PostMapping("/signin")
	public @ResponseBody String signin(@RequestBody(required = true) User user){
		logger.info("POST /signin, username = " + user.getUsername() + ", password = " + user.getPassword());
		
		int resCode = 0;
		String status = "";
		
		if(userRepository.findByUsername(user.getUsername()).size() == 0){
			resCode = 2;
			status = "User not exist!";
		}
		
		if(userRepository.findByUsername(user.getUsername()).size() > 1){
			resCode = 3;
			status = "Duplicated username! Dirty data in DB!";
		}
		
		User currentUser = userRepository.findByUsername(user.getUsername()).get(0);
		
		String password = currentUser.getPassword();
		String hashPassword = PasswordTools.getMd5Hash(user.getPassword());

		if(hashPassword.equals(password)){
			resCode = 0;
			status = "Authentication success!";
		}else{
			resCode = 4;
			status = "Authentication failed!";
		}
		
		try {
			
			Response response = new Response();
			response.setCode(resCode);
			response.setStatus(status);
			response.setUser(currentUser);
			response.setAccessToken(tokenTools.getAccessToken(currentUser));
			String resJson = new ObjectMapper().writeValueAsString(response);			
			logger.info("Response: " + resJson);
			
			return resJson;
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			return "{\"code\":\"9\",\"status\":\"Internal error\"}";
		}		
	}
	
	@PostMapping("/oauth")
	public @ResponseBody String oauth(
			@RequestParam(value="provider", required=true) String provider
			){
		logger.info("POST /oauth, provider = " + provider);
		
		int resCode = 0;
		String status = githubOauthService.getAuthUrl();
		logger.info("Oauth url = " + status);
		
		if(!provider.toLowerCase().equals("github")){
			logger.error("Only github is supported now!");
			resCode = 5;
			status = "Not supported";	
		}	
		
		try {
			Response response = new Response();	
			response.setCode(resCode);
			response.setStatus(status);
			String resJson = new ObjectMapper().writeValueAsString(response);			
			logger.info("Response: " + resJson);
			
			return resJson;
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());			
			return "{\"code\":\"9\",\"status\":\"Internal error\"}";
		}
	}
	
	@GetMapping("/oauth/auth/github")
	public String oauthAuth(@RequestParam(value="code", required=true) String code, 
			@RequestParam(value="state", required=true) String state) {
		logger.info("Get /oauth/auth/github, code="+ code + ", state=" + state);
		
		try {
			UrlParameter token = githubOauthService.getAccessToken(code, state);
			
			String tokenType = token.getTokenType();
			String accessToken = token.getAccessToken();
			
			logger.info("Token Type: " + tokenType);
			logger.info("Access Token: " + accessToken);
			
			GithubUserInfo userInfo = githubOauthService.getUserInfo(tokenType, accessToken);
			logger.info("Login: " + userInfo.getLogin());
			logger.info("Name: " + userInfo.getName());
			logger.info("Email:" + userInfo.getEmail());
			logger.info("Location: " + userInfo.getLocation());
			
			User user = new User();
			user.setUsername(userInfo.getLogin());
			user.setEmail(userInfo.getEmail());
			user.setLocation(userInfo.getLocation());
			user.setSource(UserSignupSource.GITHUB);
			
			WebSocket websocket = new WebSocket();
			websocket.setEvent(WebsocketEvent.OAUTH);
			websocket.setUser(user);
			
			String oauthJson = new ObjectMapper().writeValueAsString(websocket);
			WebSocketServer.sendMessage(oauthJson);			
			logger.info("Websocket done");			
			
			return "<script>window.close();</script>";
		} catch (OauthException | JsonProcessingException e) {
			logger.error(e.getMessage());
			return "<h1>Oooops! Server error: " + e.getMessage() + "</h1>";
		}
	}
	
	@PostMapping("/visitor")
	public String visitor() {
		User user = new User();
		user.setUsername(UserRoleType.VISITOR + (int) (Math.random()*100000));
		user.setRole(UserRoleType.VISITOR);
		User currentUser = userRepository.save(user);
		logger.info("Visitor saved in DB");
		
		logger.info("Grant token to visitor");
		tokenTools.grantAccessToken(currentUser);
		
		try {
			Response response = new Response();	
			response.setCode(0);
			response.setStatus("success");
			response.setUser(currentUser);
			response.setAccessToken(tokenTools.getAccessToken(currentUser));
			String resJson = new ObjectMapper().writeValueAsString(response);			
			logger.info("Response: " + resJson);
			
			return resJson;
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());			
			return "{\"code\":\"9\",\"status\":\"Internal error\"}";
		}
	}
	
	@PostMapping("/accesstoken")
	public String accessToken(@RequestHeader(value="Authorization", required=true) String authorization) {
		logger.info("Post /accesstoken: " + authorization);
		
		int resCode = 0;
		String status = "";
		User currentUser = null;
		
		String token = authorization.substring(7);
		logger.info("Access token = " + token);
		
		if(tokenTools.isValidToken(token)){
			logger.info("Token is valid");
			resCode = 0;
			status = "success";
			
			currentUser = tokenTools.getUser(token);
			logger.info("Find user " + currentUser.getUsername() + " by access token");
		}else{
			logger.info("Token is expired");
			resCode = 6;
			status = "Signin timeout, please signin again.";			
		}
		try {
			Response response = new Response();	
			response.setCode(resCode);
			response.setStatus(status);
			response.setUser(currentUser);
			String resJson = new ObjectMapper().writeValueAsString(response);			
			logger.info("Response: " + resJson);
			
			return resJson;
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());			
			return "{\"code\":\"9\",\"status\":\"Internal error\"}";
		}
	}

}
