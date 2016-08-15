package com.chengfan.weixin.getuserinfo;

import com.chengfan.weixin.pojo.AccessToken;
import com.chengfan.weixin.pojo.WeixinUserInfo;
import com.chengfan.weixin.util.WeixinUtil;

public class Test {
	public static void main(String args[]) {
		
		// 第三方用户唯一凭证  
        String appId = "wx57ab0f813479cb11";  
        // 第三方用户唯一凭证密钥  
        String appSecret = "b789bb211c59c56898a14e9ccd075bdd"; 
	    // 获取接口访问凭证
        AccessToken accessToken = WeixinUtil.getAccessToken(appId, appSecret);
	    /**
	     * 获取用户信息
	     */
	    WeixinUserInfo user = GetUserInfoUtil.getUserInfo(accessToken.getToken(), "ooK-yuJvd9gEegH6nRIen-gnLrVw");
	    System.out.println("OpenID：" + user.getOpenId());
	    System.out.println("关注状态：" + user.getSubscribe());
	    System.out.println("关注时间：" + user.getSubscribeTime());
	    System.out.println("昵称：" + user.getNickname());
	    System.out.println("性别：" + user.getSex());
	    System.out.println("国家：" + user.getCountry());
	    System.out.println("省份：" + user.getProvince());
	    System.out.println("城市：" + user.getCity());
	    System.out.println("语言：" + user.getLanguage());
	    System.out.println("头像：" + user.getHeadImgUrl());
	  }
}
