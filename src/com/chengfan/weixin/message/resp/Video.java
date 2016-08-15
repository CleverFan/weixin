package com.chengfan.weixin.message.resp;

/**
 * ��Ƶmodel
 * 
 * @author ChengFan
 */
public class Video {
	// ý���ļ�id
	private String MediaId;
	// ����ͼ��ý��id
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
