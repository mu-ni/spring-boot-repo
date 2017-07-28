package com.muni.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muni.test.model.ResponseForm;
import com.muni.test.websocket.WebSocketServer;

@RestController
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Value("${alipay.gateway.url}")
	String gatewayUrl;
	
	@Value("${alipay.application.public.key}")
	String publicKey;

	@Value("${alipay.application.private.key}")
	String privateKey;

	@Value("${alipay.request.return.url}")
	String returnUrl;

	@Value("${alipay.request.notify.url}")
	String notifyUrl;

	@GetMapping("/test")
	public String test() {
		logger.info("GET /test");
		return "test";
	}

	@PostMapping("/alipay")
	public @ResponseBody String alipay() {
		logger.info("Post /alipay");

		AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, "2016080600178567", privateKey,
				AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8, publicKey, AlipayConstants.SIGN_TYPE_RSA2);

		try {		
			AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
			model.setSubject("TEST");
			model.setOutTradeNo("70501111111S001111108");
			model.setTotalAmount("10.00");
			model.setProductCode("QUICK_WAP_WAY");
			logger.info("AlipayTradeWapPayModel: " + new ObjectMapper().writeValueAsString(model));
			
			AlipayTradeWapPayRequest wapPay = new AlipayTradeWapPayRequest();
			wapPay.setReturnUrl(returnUrl);
			wapPay.setNotifyUrl(notifyUrl);
			wapPay.setBizModel(model);
			logger.info("AlipayTradeWapPayRequest: " + new ObjectMapper().writeValueAsString(wapPay));
			
			String formAndScript = alipayClient.pageExecute(wapPay).getBody();
			logger.info("AlipayClient.pageExecute(wapPay).getBody(): " + formAndScript);
			
			String form = formAndScript.substring(0, formAndScript.indexOf("<script>"));
			logger.info("Form: " + form);
			
			ResponseForm response = new ResponseForm();
			response.setForm(form);
			String resJson = new ObjectMapper().writeValueAsString(response);
			logger.info("Response: " + resJson);
			
			return resJson;
		} catch (AlipayApiException e) {
			logger.error(e.getErrMsg());
			return "{\"form\" : \"Oooops Alipay error! " + e.getErrMsg() + "\"}";
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			return "{\"form\" : \"Oooops Json error! " + e.getMessage() + "\"}";
		}

	}
	
	@PostMapping("/notify")
	public void notify(@RequestBody(required = true) String request) {
		//verify sign
		//@ModelAttribute("trade_status") String tradeStatus
//		AlipaySignature.rsaCheckV1(params, request, charset)
//		AlipayTradeWapPayResponse response = new AlipayTradeWapPayResponse();
		logger.info("Post /notify");
		logger.info("Request Body: " + request);
		WebSocketServer.sendMessage("支付成功");
	}

}
