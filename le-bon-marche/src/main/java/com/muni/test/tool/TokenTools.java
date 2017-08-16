package com.muni.test.tool;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.muni.test.entity.AccessToken;
import com.muni.test.entity.User;
import com.muni.test.repo.AccessTokenRepository;

@Component
public class TokenTools {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenTools.class);

	@Autowired
	private AccessTokenRepository accessTokenRepository;
	
	@Value("${oauth.access.token.inactive.alive.minute}")
	private int aliveMin;
	
	public void grantAccessToken(User user){
		logger.info("Grant access token to user " + user.getUsername());
		
		AccessToken token = new AccessToken();
		token.setUser(user);
		token.setToken(OauthTools.getRandomString(32));
		accessTokenRepository.save(token);
		logger.info("User " + user.getUsername() + "'s accessToken saved in DB");
		
		updateExpirationDate(token);
		logger.info("AccessToken's expirationDate updated in DB");
		
	}
	
	public void updateExpirationDate(AccessToken token){
		logger.info("updateExpirationDate token id = " + token.getId());
		logger.info("Alive minuites = " + aliveMin);
		
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(token.getCreationDate().getTime());
        cal.add(Calendar.MINUTE, aliveMin);
        Timestamp expDate = new Timestamp(cal.getTime().getTime());
        logger.info("Expiration Date = " + expDate);
        
        accessTokenRepository.updateExpirationDate(token.getId(), expDate);
        logger.info("Update expiration date success");
	}
	
	public String getAccessToken(User user){
		AccessToken accessToken = accessTokenRepository.findByUser(user);
		return accessToken.getToken();
	}
	
	public User getUser(String token){
		AccessToken accessToken = accessTokenRepository.findByToken(token);
		return accessToken.getUser();
	}
	
	public boolean isValidToken(String token){
		AccessToken accessToken = accessTokenRepository.findByToken(token);
		//accessToken is null
		if(accessToken.getExpirationDate().after(Timestamp.valueOf(LocalDateTime.now()))){
			logger.info("Valid token: " + token);
			
			updateExpirationDate(accessToken);
			logger.info("AccessToken's expirationDate updated in DB");
			
			return true;
		}else{
			logger.info("Expired token: " + token);
			return false;
		}
	}

}
