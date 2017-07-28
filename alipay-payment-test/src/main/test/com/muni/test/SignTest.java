//package com.muni.test;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import org.springframework.stereotype.Component;
//
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayConstants;
//import com.alipay.api.internal.util.AlipayHashMap;
//import com.alipay.api.internal.util.AlipaySignature;
//
//@Component
//public class SignTest {
//	
////	@Value("${alipay.request.format}")
////	String format;
////	
////	@Value("${alipay.request.charset}")
////	String charset;
////	
////	@Value("${alipay.request.sign.type}")
////	String signType;
////	
////	@Value("${alipay.request.version}")
////	String version;
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
////		PublicRequest publicRequest = new PublicRequest("2016080600178567", "alipay.trade.wap.pay", format, "", charset, signType,
////				"sign", Timestamp.valueOf(LocalDateTime.now()).toString(), version, "", "{}");
//		
//		AlipayHashMap mappp = new AlipayHashMap();
//		mappp.put(AlipayConstants.APP_ID, "2016080600178567");
//		mappp.put(AlipayConstants.METHOD, "alipay.trade.wap.pay");
//		mappp.put(AlipayConstants.FORMAT, "format");
//		mappp.put(AlipayConstants.RETURN_URL, "");
//		mappp.put(AlipayConstants.CHARSET, "charset");
//		mappp.put(AlipayConstants.SIGN_TYPE, "signType");
//		mappp.put(AlipayConstants.METHOD, "alipay.trade.wap.pay");
//		mappp.put(AlipayConstants.SIGN, "");
//		mappp.put(AlipayConstants.TIMESTAMP, Timestamp.valueOf(LocalDateTime.now()).toString());
//		mappp.put(AlipayConstants.VERSION, "version");
//		mappp.put(AlipayConstants.BIZ_CONTENT_KEY, "{}");
//		String content = AlipaySignature.getSignContent(mappp);
//		System.out.println(content);
//		//String content = "app_id=2016080600178567&biz_content= { \"subject\":\"test\", \"out_trade_no\":\"70501111111S001111120\", \"total_amount\":10.00, \"product_code\":\"QUICK_WAP_WAY\" }&charset=utf-8&method=alipay.trade.wap.pay&sign_type=RSA2&timestamp=2013-01-01 08:08:08&version=1.0";
//		String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCS0xJWEaGo1/h5WeRDcir/Nhtcprym9My54UAFM48UFE6QUlUc6ef0ZrxIDSg5pPKUp3kXmHw1rxJLnPmo+nS5a+s/ED+cAConzoYmGj0S4LPfv/Tl/gqSEgNqPL8ncQOV7yFlKUKurP3ZtHMpJESKv4LzTSXZHqPt/NKz7OFqAolgzkS99ehYvdHK0L607H/LVc500oq5yOgpVz8KtjorEQby+idmYw4E7bXSpyp0Z4n2os85+LUJrmv76gPhDa1rvr4wgE8PO21rn8PCQ0CZi9Jpu5X4ccy/63ywTp/Q2knoNOWSmxmkl8BAjaIyZcEYdIhpzNJEilBMDWFuMofJAgMBAAECggEAJOn2WTdTmpy59uCX2DEzCDk1ReUR1zQC6NTuAKDG3zh/zB83EO7qB3Nw8PiF3YBQEPnOIxWOXPzoL6SGzGozwI6WdaODKpiMALgW2CuOoVlnP+EGFHD7fWAUBbjCIotyaFbX4ZQvFLWfmQWaUoSwyuV9aehClMT0WBhRbTUoMzuQKM3DNdj62lG7ipxaIe2wXqUB41DqdMLLn3Xn7/XYx4pxyBl21H2WSBBontEksGJRNXk1bDlxgY9GcDxdiqf64HvAWMJCNqdcRXllNY8nWWMZ0ciTYqyJkUxLoydl1cBYhwU5Dw9ROkXgiJV9K2cdeVR7SUKh+zhmVe0eewX4AQKBgQDT7mP1envqrAFYwQIZ0/R1JnWn1ZSYBjHP0pNvUlmaS9aZhsQ29yxYYzu1JZaJqSdcfwx08yLTG6UOlxI9bXR8VPWPnS307ePNW9C23QbYUn1obQN6Tsf+uEB+VC909QUEdCJphigR+0ZLOKoHCN1ThLllksT9MkIr+uD7y76jAQKBgQCxWuTNNgTJL8+ptbVGxJn6I4aanWVqQyV75RLnMseZdv0BYwZN74aWeF9KIWx9SL3MjUEybwANumFNnbnmwJM/l9sdMoiTcmeJPaHL69VeX0lncOG6gdf5+HmeXdCRHqN4tCJknV7vtcL04EUWfr92GZDV1/LSomkuFa0JFGCMyQKBgQCPEKG35/xY2c567KgPaW7f2puYNez+h0f/BCb1AkFskH+I2loAxPDRpgN7cKljuqXQog91r2A15LaS19a/UvFMsUmnrjKE4kZirfWCTEi/pUf1Q4oSebY2tIkM/qWerXy6me9VDvRj1x2Hz/OvlgNaywEgmWiTh+b1q4FPWSYTAQKBgGNMRbBTn0hZOYaGUxFlP+SaAWIN39sn7xO4BOZnOvPEeBFPtRbrlconaQqjPg/DK2kcaTVoinolMRd4yL3Wclr3ldl6EpejRS7E0BLRXqww43ND804uJpMxOD6fvgeSROpuyJbWuXGyrY3XBzd6DY587+eaubyO7B6+Shw8SXZpAoGBAK7VqEYZncI7eVj70TCI8m5EV+BmqaN5oPyOeHXR0TVT9/bb0EiVFN2mNrXAClnrcZBImJ2Csec8c2shVgSrHZLPSXxPteNmSvI3o1EWc9MM1K9iF0IPehj2rgrDCH4cpB5OdEaSvWWZE1m9mfuD4f2LL9d3KQCly7BpSYVO7Gj8";
//		try {
//			String sign = AlipaySignature.rsaSign(content, privateKey, "utf-8", "RSA2");
//			System.out.println(sign);
//		} catch (AlipayApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
