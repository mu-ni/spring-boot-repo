//package com.muni.test;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.SpringApplication;
//
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.internal.util.AlipaySignature;
//import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
//import com.alipay.api.request.AlipayTradeWapPayRequest;
//import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
//
//public class Test {
//	
//	private static final Logger logger = LoggerFactory.getLogger(Test.class);
//	
//	static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALkqRF2fDYLddJRkuSBwkONjA2MjrMHzmZkjdbDwgfarHKqCGmP5kXDfQhl+ygT4oEbeQeGiAoJfY5vpWIR9MeaZ39TkWWArEciGBJSXY51S4YlhlMJPqoPU4tlWsQBRvBBQYU3XXnJuZW4+XsDkl4sgVwP2QEgF71dtb465JH01AgMBAAECgYAghoQrzdyZuDzaNDcws5U/3AykRYf7Kcc5Vzk9M4Igyj3m1aO9HAGoSg9aWyMxbFr7F9+KuuKl9AuTP5IE89jn1D7TsjTKaVh4ppI1SL9RlhqqDSMq9I2hGCiH3vYU7cGvHGP7yHsshZm20FXZwPvLFHBBYhiJJVHG8fqaxdvroQJBAOodtfkUFk8MhFVZPYrmJhQaio8DgJfgWKrNr+fKMVpZGKJzkelCx9D2JQrHsNkEEfjZeioYNISaXIfiFFNH0rMCQQDKeS66sVmOZDil17rJJBuBZVL5aWed+yTPI5I6d1sRNo/T7kORzOw+S/acF6ONJIzlajIKM5FUQgcWnnNzDkR3AkAqtcjg6902Bp6Nw34KvPntaPomJQlsq60BXAzfSG/Af8iSumrAF0LEyGLxmISW0rtEnsg+rbPHDTIcSA5VMyEpAkASWrvutqcMrm8oMY9ALqLDryC/dVusSZkDohDedfVucQjGyIGdlPsRhqdbbRUpdVfEcEULAbO59KZZ2ZFTq8ZvAkAf0mYYFLBD/oQHAHDc8boTjKvHzm1Ad1rc1gP5SZ+viDkEUZOin8VrSXC7ikXjzCpuvHCtxciJO1R2aeDkR9cb";
//	static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB+ZFw30IZfsoE+KBG3kHhogKCX2Ob6ViEfTHmmd/U5FlgKxHIhgSUl2OdUuGJYZTCT6qD1OLZVrEAUbwQUGFN115ybmVuPl7A5JeLIFcD9kBIBe9XbW+OuSR9NQIDAQAB";
//
//	public static void main(String[] args) {
//
////		Signature.getInstance("SHA256withRSA");
////		try {
////			doPost();
////		} catch (ServletException | IOException e) {
////			logger.error(e.getMessage());
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
//
//	public static void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
//			throws ServletException, IOException {
//		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
//				"2016080600178567", privateKey, "json", "utf-8", publicKey, "RSA2");
//		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
//		alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
//		alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");// 在公共参数中设置回跳和通知地址
//		alipayRequest
//				.setBizContent("{" + "    \"out_trade_no\":\"20150320010101002\"," + "    \"total_amount\":\"88.88\","
//						+ "    \"subject\":\"Iphone6 16G\"," + "    \"product_code\":\"QUICK_WAP_PAY\"" + "  }");// 填充业务参数
//		String form = "";
//		try {
//			form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
//			logger.info(form);
//			verifySign();
//		} catch (AlipayApiException e) {
//			logger.error(e.getMessage());
//			e.printStackTrace();
//		}
////		httpResponse.setContentType("text/html;charset=utf-8");
////		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
////		httpResponse.getWriter().flush();
////		httpResponse.getWriter().close();
//	}
//	
//	public static void verifySign() throws AlipayApiException{
//		Map<String, String> paramsMap = new HashMap<String, String>(); //将异步通知中收到的所有参数都存放到map中
//		//paramsMap.put("test", "test");
//		boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, publicKey, "utf-8", "RSA2"); //调用SDK验证签名
//		if(signVerified){
//			logger.info("Sign authentication success!");
//		    // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
//		}else{
//			logger.error("Sign authentication failed!");
//		    // TODO 验签失败则记录异常日志，并在response中返回failure.
//		}
//	}
//
//}
		// wapPay.putOtherTextParam(AlipayConstants.APP_ID, "2016080600178567");
		// wapPay.putOtherTextParam(AlipayConstants.METHOD,
		// "alipay.trade.wap.pay");
		// wapPay.putOtherTextParam(AlipayConstants.FORMAT,
		// AlipayConstants.FORMAT_JSON);
		// wapPay.putOtherTextParam(AlipayConstants.RETURN_URL, returnUrl);
		// wapPay.putOtherTextParam(AlipayConstants.CHARSET,
		// AlipayConstants.CHARSET_UTF8);
		// wapPay.putOtherTextParam(AlipayConstants.SIGN_TYPE,
		// AlipayConstants.SIGN_TYPE_RSA2);
		// wapPay.putOtherTextParam(AlipayConstants.TIMESTAMP,
		// Timestamp.valueOf(LocalDateTime.now()).toString());
		// wapPay.putOtherTextParam(AlipayConstants.VERSION, version);
		// wapPay.putOtherTextParam(AlipayConstants.NOTIFY_URL, notifyUrl);

		// try {
		// String content =
		// AlipaySignature.getSignContent(wapPay.getTextParams());
		// logger.info("Sign Content: " + content);
		//
		// String sign = AlipaySignature.rsaSign(content, privateKey,
		// AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
		// logger.info("Sign: " + sign);
		//
		// wapPay.putOtherTextParam(AlipayConstants.SIGN, sign);
		//
		// String query = WebUtils.buildQuery(wapPay.getTextParams(),
		// AlipayConstants.CHARSET_UTF8);
		// logger.info("Query: " + query);
		//
		// String redirect = gatewayUrl + "?" + query;
		// logger.info("redirect: " + redirect);
		//
		// return "{\"redirect\" : \"" + redirect + "\"}";
		//
		// } catch (AlipayApiException | IOException e) {
		// logger.error("AlipayApiException while calculating sign: " +
		// e.getMessage());
		// return "{\"redirect\" : \"error\"}";
		// }