package com.chengfan.weixin.message.resp;

/**
 * ��Ƶ��Ϣ
 * 
 * @author ChengFan
 */
public class VideoMessage extends BaseMessage {
	// ��Ƶ
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
