package com.muni.test.repo;

import java.sql.Timestamp;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.muni.test.entity.AccessToken;
import com.muni.test.entity.User;

public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {
	
	@Modifying
	@Transactional
	@Query("update AccessToken token set token.expirationDate = ?2 where token.id = ?1")
	void updateExpirationDate(Integer id, Timestamp expDate);
	
	AccessToken findByUser(User user);
	
	AccessToken findByToken(String token);
}
