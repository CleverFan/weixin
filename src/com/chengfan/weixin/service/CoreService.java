package com.chengfan.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.chengfan.weixin.facecheck.FaceService;
import com.chengfan.weixin.fanyi.BaiduTranslateUtil;
import com.chengfan.weixin.message.resp.Article;
import com.chengfan.weixin.message.resp.NewsMessage;
import com.chengfan.weixin.message.resp.TextMessage;
import com.chengfan.weixin.robotUtil.ChatToRobot;
import com.chengfan.weixin.todayInHistory.TodayInHistoryService;
import com.chengfan.weixin.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author ChengFan
 * 
 */
public class CoreService {

	private static boolean isChatToRobot = false;

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respMessage = processText(requestMap.get("Content"),
						fromUserName, toUserName);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
				respMessage = processPicture(requestMap);
				
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");

				TextMessage textMessage = new TextMessage();
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				textMessage.setFuncFlag(0);

				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "欢迎关注计算机组织部微信~输入help查看微信全部功能";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("14")) {
						respContent = TodayInHistoryService
								.getTodayInHistoryInfo().replaceAll("&nbsp;","");
						textMessage.setContent(respContent);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					} else if (eventKey.equals("31")) {
						//原创绑定
						respMessage = sendPicAndTxt("yuanchuangbangding",fromUserName, toUserName);
					} else if (eventKey.equals("32")) {
						//课表查询
						respMessage = sendPicAndTxt("kebiaochaxun",fromUserName, toUserName);
					} else if (eventKey.equals("33")) {
						//成绩查询
						respMessage = sendPicAndTxt("chengjichaxun",fromUserName, toUserName);
					}
				}

				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

	/**
	 * 人脸检测
	 * @param requestMap
	 * @return
	 */
	private static String processPicture(Map<String, String> requestMap) {
		
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
					
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		 // 取得图片地址  
        String picUrl = requestMap.get("PicUrl");  
        // 人脸检测  
        String detectResult = FaceService.detect(picUrl);  
        textMessage.setContent(detectResult);
        
        
		return MessageUtil.textMessageToXml(textMessage);
	}

	private static String processText(String msgFromUser, String fromUserName,
			String toUserName) throws Exception {

		// 回复文本消息

		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);

		String returnContent = "您可以输入help查看菜单~";
		String returnMsg = "";
		String msgFromUser_ = msgFromUser.trim();
		switch (msgFromUser_) {
		case "help":
			StringBuffer buffer = new StringBuffer();
			buffer.append("您好，我是机智的饭饭，请回复数字选择服务：").append("\n\n");
			//buffer.append("1  天气预报").append("\n");
			//buffer.append("2  公交查询").append("\n");
			//buffer.append("3  周边搜索").append("\n");
			//buffer.append("4  歌曲点播").append("\n");
			//buffer.append("5  经典游戏").append("\n");
			buffer.append("1  历史上的今天").append("\n");
			buffer.append("2  人脸识别").append("\n");
			buffer.append("3  智能聊天").append("\n");
			buffer.append("4  智能翻译").append("\n");
			buffer.append("5  查询助手").append("\n");
			buffer.append("回复“help”显示此帮助菜单");

			returnContent = buffer.toString();
			textMessage.setContent(returnContent);
			returnMsg = MessageUtil.textMessageToXml(textMessage);
			break;

		case "1":
			returnMsg = TodayInHistoryService.getTodayInHistoryInfo()
					.replaceAll("&nbsp;", "");
			textMessage.setContent(returnContent);
			returnMsg = MessageUtil.textMessageToXml(textMessage);
			break;
		case "2":
			textMessage.setContent(getUsage());
			returnMsg = MessageUtil.textMessageToXml(textMessage);
			break;
		case "3":
			isChatToRobot = true;
			returnContent = "现在您可以和机器人聊天了~输入“###”可以结束聊天~";
			textMessage.setContent(returnContent);
			returnMsg = MessageUtil.textMessageToXml(textMessage);
			break;
		case "4":
			textMessage.setContent(getTranslateUsage());
			returnMsg = MessageUtil.textMessageToXml(textMessage);
			break;
		case "5":
			textMessage.setContent("请点击下方的快捷按钮");
			returnMsg = MessageUtil.textMessageToXml(textMessage);
			break;

		case "###":
			if (isChatToRobot) {
				returnContent = "聊天结束~感谢您的使用";
				textMessage.setContent(returnContent);
				returnMsg = MessageUtil.textMessageToXml(textMessage);
				isChatToRobot = false;
			} else {
				returnContent = "您可以输入help查看菜单~";
				textMessage.setContent(returnContent);
				returnMsg = MessageUtil.textMessageToXml(textMessage);
			}
			break;
		case "blog":
			// 发送图文消息给用户
			returnMsg = sendPicAndTxt(msgFromUser, fromUserName, toUserName);
			break;


		default:
			if (isChatToRobot) {
				// 此时发来的消息是要和机器人聊天
				JSONObject jsonObject = JSONObject.fromObject(ChatToRobot
						.messageFromRobot(msgFromUser));

				returnContent = jsonObject.getString("text").replaceAll(
						"图灵机器人", "机智的饭饭");
				textMessage.setContent(returnContent);
				returnMsg = MessageUtil.textMessageToXml(textMessage);
			} else if (msgFromUser_.startsWith("翻译")) {

				String keyWord = msgFromUser_.replaceAll("^翻译", "").trim();
				if ("".equals(keyWord)) {
					textMessage.setContent(getTranslateUsage());
					returnMsg = MessageUtil.textMessageToXml(textMessage);
				} else {

					String result = BaiduTranslateUtil.translateToEn(keyWord);
					if (result == null) {
						result = "翻译出错了，试试翻译其他词语吧~";
					}
					textMessage.setContent(result);
					returnMsg = MessageUtil.textMessageToXml(textMessage);

				}
				// out.print(WeixinUtil.textMessageToXml(textMessage));
			}
		}

		return returnMsg;
	}

	/**
	 * @param msgFromUser
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static String sendPicAndTxt(String msgFromUser, String fromUserName,
			String toUserName) {
		// 接收用户发送的文本消息内容
		String content = msgFromUser;
		String respMessage = "您可以输入help查看菜单~";

		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		// 单图文消息
		if ("yuanchuangbangding".equals(content)) {
			Article article = new Article();
			article.setTitle("原创绑定");
			article.setDescription("您可以进行原创绑定，绑定后不用输入学号和密码就可以进行其他查询，一个微信号只能绑定一个学号！");
			article.setPicUrl("http://1.weixincf.applinzi.com/images/zzb.jpg");
			article.setUrl("http://1.weixincf.applinzi.com/bangding?FromUserName="+fromUserName);
			articleList.add(article);
			// 设置图文消息个数
			newsMessage.setArticleCount(articleList.size());
			// 设置图文消息包含的图文集合
			newsMessage.setArticles(articleList);
			// 将图文消息对象转换成xml字符串
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}else if ("blog".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("Java进阶之自动拆箱与自动装箱");
			article1.setDescription("");
			article1.setPicUrl("http://1.weixincf.applinzi.com/images/head.png");
			article1.setUrl("http://blog.csdn.net/qq_31655965/article/details/51597285");

			Article article2 = new Article();
			article2.setTitle("Java中String转化为其他类型方法汇总");
			article2.setDescription("");
			article2.setPicUrl("http://1.weixincf.applinzi.com/images/head.png");
			article2.setUrl("http://blog.csdn.net/qq_31655965/article/details/49862581");

			articleList.add(article1);
			articleList.add(article2);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
			System.out.println("22222222");
		}else if ("chengjichaxun".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("2015-2016第二学期成绩");
			article1.setDescription("");
			article1.setPicUrl("http://1.weixincf.applinzi.com/images/zzb.jpg");
			article1.setUrl("http://1.weixincf.applinzi.com/queryScore?FromUserName="+fromUserName+"&Cbo_Xueqi=2015/2016(2)");

			Article article2 = new Article();
			article2.setTitle("2015-2016第一学期成绩");
			article2.setDescription("");
			article2.setPicUrl("http://1.weixincf.applinzi.com/images/zzb.jpg");
			article2.setUrl("http://1.weixincf.applinzi.com/queryScore?FromUserName="+fromUserName+"&Cbo_Xueqi=2015/2016(1)");

			Article article3 = new Article();
			article3.setTitle("2014-2015第二学期成绩");
			article3.setDescription("");
			article3.setPicUrl("http://1.weixincf.applinzi.com/images/zzb.jpg");
			article3.setUrl("http://1.weixincf.applinzi.com/queryScore?FromUserName="+fromUserName+"&Cbo_Xueqi=2014/2015(2)");
			
			Article article4 = new Article();
			article4.setTitle("2014-2015第一学期成绩");
			article4.setDescription("");
			article4.setPicUrl("http://1.weixincf.applinzi.com/images/zzb.jpg");
			article4.setUrl("http://1.weixincf.applinzi.com/queryScore?FromUserName="+fromUserName+"&Cbo_Xueqi=2014/2015(1)");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			articleList.add(article4);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}
		// 多图文消息---最后一条消息不含图片
		else if ("kebiaochaxun".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("2015-2016第二学期课表");
			article1.setDescription("");
			article1.setPicUrl("http://1.weixincf.applinzi.com/images/zzb.jpg");
			article1.setUrl("http://1.weixincf.applinzi.com/queryTable?FromUserName="+fromUserName+"&Cbo_Xueqi=2015/2016(2)");

			Article article2 = new Article();
			article2.setTitle("2015-2016第一学期课表");
			article2.setDescription("");
			article2.setPicUrl("http://1.weixincf.applinzi.com/images/zzb.jpg");
			article2.setUrl("http://1.weixincf.applinzi.com/queryTable?FromUserName="+fromUserName+"&Cbo_Xueqi=2015/2016(1)");

			Article article3 = new Article();
			article3.setTitle("2014-2015第二学期课表");
			article3.setDescription("");
			article3.setPicUrl("http://1.weixincf.applinzi.com/images/zzb.jpg");
			article3.setUrl("http://1.weixincf.applinzi.com/queryTable?FromUserName="+fromUserName+"&Cbo_Xueqi=2014/2015(2)");
			
			Article article4 = new Article();
			article4.setTitle("2014-2015第一学期课表");
			article4.setDescription("");
			article4.setPicUrl("http://1.weixincf.applinzi.com/images/zzb.jpg");
			article4.setUrl("http://1.weixincf.applinzi.com/queryTable?FromUserName="+fromUserName+"&Cbo_Xueqi=2014/2015(1)");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			articleList.add(article4);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}
		return respMessage;
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * F译通使用指南
	 * 
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(emoji(0xe148)).append("F译通使用指南").append("\n\n");
		buffer.append("F译通为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n\n");
		buffer.append("回复“help”显示主菜单");
		return buffer.toString();
	}
	
	/** 
     * 人脸检测帮助菜单 
     */  
    public static String getUsage() {  
        StringBuffer buffer = new StringBuffer();  
        buffer.append("人脸检测使用指南").append("\n\n");  
        buffer.append("发送一张清晰的照片，就能帮你分析出种族、年龄、性别等信息").append("\n");  
        buffer.append("快来试试你是不是长得太着急");  
        return buffer.toString();  
    }  
}
