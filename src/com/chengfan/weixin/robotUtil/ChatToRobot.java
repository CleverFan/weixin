package com.chengfan.weixin.robotUtil;

import net.sf.json.JSONObject;

/**
 * @author ChengFan
 *
 */
public class ChatToRobot {
	// 图灵网站上的secret
	private static String secret = "47d12c33b63bded0";
	// 图灵网站上的apiKey
	private static String apiKey = "2de7aab7fdf6afa7d90c85fd8d323cfb";

	public static String messageFromRobot(String messageFromUser) {

		String cmd = messageFromUser;// 用户发来的消息
		// 待加密的json数据
		String data = "{\"key\":\"" + apiKey + "\",\"info\":\"" + cmd + "\"}";
		// 获取时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());

		// 生成密钥
		String keyParam = secret + timestamp + apiKey;
		String key = Md5.MD5(keyParam);

		// 加密
		Aes mc = new Aes(key);
		data = mc.encrypt(data);

		// 封装请求参数
		JSONObject json = new JSONObject();
		json.put("key", apiKey);
		json.put("timestamp", timestamp);
		json.put("data", data);
		// 请求图灵api
		String result = PostServer.SendPost(json.toString(),
				"http://www.tuling123.com/openapi/api");
		return result;
	}
}
