//import java.io.IOException;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
//import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.muni.test.dto.GithubUserInfo;
//
//public class HttpTest {
//
//	public static void main(String[] args) {
////		// TODO Auto-generated method stub
////		String res;
////		Map<String, String> map = new HashMap<String, String>();
////		map.put("Authorization", "bearer 87f4c78e13881c3c62b19dda4ea7aa61e608b0ef");
////		try {
////			res = HttpTools.httpRequest("GET", "https://api.github.com/user", map, "");
////			System.out.println(res);
////		} catch (HttpException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
////		UrlParameter github = new UrlParameter();
//////		String state = OauthTools.getRandomString(32);
//////		System.out.println(state);
//////		github.setState(state);
////		
////		String rst = OauthTools.generateUrl("https://github.com/login/oauth/authorize", github);
////		System.out.println(rst);
//		
////		String test = "access_token=145f3c2edf9cea5a4bd5de7032600bc684b0bcd5&scope=user&token_type_test=bearer";
////		UrlParameter param = OauthTools.parameterStringToObject(test);
////		
////		System.out.println(param.getAccessToken());
////		System.out.println(param.getScope());
////		System.out.println(param.getTokenType());
//		
////		GithubUserInfo info = new GithubUserInfo();
////		info.setName("muni");
////		info.setId(123);
//		
////		String json = "";
////		ObjectMapper mapper = new ObjectMapper();
////		mapper.setSerializationInclusion(Include.NON_NULL);
////		mapper.setVisibility(JsonMethod.FIELD,Visibility.ANY);
////		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
////                false);
////		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//		
//
//		// Jackson 2.0 or later
////		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//		
//		
////		try {
////			json = mapper.writeValueAsString(info);
////		} catch (JsonProcessingException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
//		String json = "{\"name\": \"muni\",\"id\": \"11784076\"}";
//		System.out.println(json);
//		
//		GithubUserInfo user;
//		try {
//			user = new ObjectMapper().readValue(json, GithubUserInfo.class);
//			System.out.println(user.getName());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
