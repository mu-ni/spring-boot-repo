package com.muni.test.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muni.test.dto.WebSocket;
import com.muni.test.entity.Product;
import com.muni.test.entity.PurchaseOrder;
import com.muni.test.entity.User;
import com.muni.test.enumerate.UserSignupSource;
import com.muni.test.enumerate.WebsocketEvent;
import com.muni.test.repo.ProductRepository;
import com.muni.test.repo.PurchaseOrderRepository;
import com.muni.test.repo.UserRepository;
import com.muni.test.websocket.WebSocketServer;

@RestController
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private PurchaseOrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/order")
	public String order(@RequestHeader(value="User-Agent") String userAgent) {
		logger.info("GET /order");
		User user = new User();
		user.setUsername("muni");
		user.setPassword("aaa");
		userRepository.save(user);
		
		Product product = new Product();
		product.setProductCode("CODE");
		product.setPrice("100");
		productRepository.save(product);
		
		PurchaseOrder order = new PurchaseOrder();
		order.setId(123);
		order.setPaymentMethod("alibaba");
		order.setProduct(product);
		order.setUser(user);
		order.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
		orderRepository.save(order);
		return "test";
	}
	
	@PostMapping("/test")
	public String test1() {
		logger.info("POST /test");		
		User user = new User();
		user.setUsername("test");
		user.setEmail("aaa@aaa.com");
		user.setLocation("China");
		user.setSource(UserSignupSource.GITHUB);
		
		WebSocket oauth = new WebSocket();
		oauth.setEvent(WebsocketEvent.OAUTH);
//		oauth.setProvider(UserSignupSource.GITHUB);
//		oauth.setRedirectTo("/#/main");
		oauth.setUser(user);
		
		String oauthJson;
		try {
			oauthJson = new ObjectMapper().writeValueAsString(oauth);
			WebSocketServer.sendMessage(oauthJson);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		}
		return "success";
	}
}
