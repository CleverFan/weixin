package com.chengfan.weixin.fanyi;

import com.chengfan.weixin.util.MessageUtil;

/**
 * 直接运行main方法即可输出翻译结果
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		String source = "翻译百度";
		//String keyWord = source.replaceAll("^翻译", "").trim();
		//String result = BaiduTranslateUtil.translateToEn(keyWord);
		
		
		
		String keyWord = source.replaceAll("^翻译", "").trim();
		if ("".equals(keyWord)) {
			System.out.println("1111");
		} else {

			String result = BaiduTranslateUtil.translateToEn(keyWord);
			if (result == null) {
				result = "翻译出错，参考百度错误代码和说明。";
			}
			//textMessage.setContent(result);

			System.out.println(source + "：" + result);
			System.out.println(source + "：" + result);
		}
	}
}
