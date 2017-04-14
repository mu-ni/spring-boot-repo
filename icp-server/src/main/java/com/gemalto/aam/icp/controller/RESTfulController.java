//package com.gemalto.aam.icp.controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@RestController
//public class RESTfulController {
//	private static final Logger logger = LoggerFactory.getLogger(RESTfulController.class);
//	
//
////	@Autowired
////	private UserRepository userRepository;
////	@Autowired
////	private ImageRepository imageRepository;
////	
////	private String currentUserName = null;
////	private static final int PAGE_SIZE = 10;
//	
//	@GetMapping("/initData")
//	public String checkGet(){
//		logger.info("GET /initData");
//		
//		try {
//			return new JSONObject("{'reviewList':[{'packageId':123, 'imgKey': 456, 'activationDate':789}], 'checkList': [{'packageId':321, 'imgKey': 654, 'activationDate':987}]}").toString();
//		} catch (JSONException e) {
//			logger.error(e.getMessage());
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	@PostMapping("/check/{imgid}")
//	public @ResponseBody String checkPost(
//			@ModelAttribute("imgid") String imgid,
//			@RequestBody(required = true) String reqBody
//			){
//		logger.info("POST /check/" + imgid);
//		
//		try {
//			JSONObject json = new JSONObject(reqBody);
//			String result = (String) json.get("result");
//			logger.info("Check result is: " + result);
//			return new JSONObject("{'action':'check','nextid' : '000'}").toString();
//		} catch (JSONException e) {
//			logger.error(e.getMessage());
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//	
//	@PostMapping("/review/{imgid}")
//	public @ResponseBody String reviewPost(
//			@ModelAttribute("imgid") String imgid,
//			@RequestBody(required = true) String reqBody
//			){
//		logger.info("POST /review/" + imgid);
//		
//		try {
//			JSONObject json = new JSONObject(reqBody);
//			String result = (String) json.get("result");
//			logger.info("Review result is: " + result);
//			return new JSONObject("{'action':'review','nextid' : '999'}").toString();
//		} catch (JSONException e) {
//			logger.error(e.getMessage());
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//
//}
