package com.chengfan.ycjw.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengfan.weixin.pojo.WeChat;
import com.chengfan.weixin.service.CoreService;
import com.chengfan.weixin.util.SignUtil;



/**
 * 微信controller
 * @author ChengFan
 *
 */
@Controller
@RequestMapping("/cf")
public class WeixinController {
	
	@RequestMapping(value="/api",method = RequestMethod.GET)
	@ResponseBody
	public String xxtInterface(WeChat wc){
		String signature = wc.getSignature(); // 微信加密签名  
        String timestamp = wc.getTimestamp(); // 时间戳  
        String nonce = wc.getNonce();// 随机数  
        String echostr = wc.getEchostr();// 随机字符串  
  
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            return echostr;  
        } else {  
            System.out.println("不是微信服务器发来的请求,请小心!");  
            return null;
        }  
	}
	
	/**
	 * 处理来自微信用户发来的消息
	 * @param request
	 * @param response
	 * @throws Exception
	 * 
	 * @author ChengFan
	 */
	@RequestMapping(value="/api",method = RequestMethod.POST)
	@ResponseBody
	public void getWeiXinMessage(HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		 // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
        String respMessage = CoreService.processRequest(request);  
          
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();
	}
	
	
	//test
	
	/**
	 * 测试
	 * @author ChengFan
	 */
	@Test
	public void test() {
		
		String signature = "xxxxxxxxxxxxxxxxxxxxxxxx"; // 微信加密签名  
        String timestamp = "12131"; // 时间戳  
        String nonce = "32432";// 随机数  
        String echostr = "fddsads";// 随机字符串  
  
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
        	System.out.println("接入成功"+echostr);
        } else {  
            System.out.println("不是微信服务器发来的请求,请小心!");  
        }  
	}
}
