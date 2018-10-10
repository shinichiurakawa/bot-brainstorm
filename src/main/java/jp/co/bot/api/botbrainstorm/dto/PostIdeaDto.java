package jp.co.bot.api.botbrainstorm.dto;

import org.apache.commons.codec.net.URLCodec;

import java.util.ArrayList;
import java.util.List;

public class PostIdeaDto {
	private String id;
	private String key;
	private String userId;
	private String ideaText;

	public String logString() {
		List<String> item_list = new ArrayList<>();
		item_list.add("id:" + id);
		item_list.add("key:" + key);
		item_list.add("userId:" + userId);
		item_list.add("ideaText:" + ideaText);
		return String.join(",",item_list);
	}

	public void urlDecode() {
		URLCodec codec = new URLCodec("UTF-8");
		try {
			ideaText = codec.decode(ideaText, "UTF-8");
		} catch (Exception e) {
			System.out.println("Exception at PostIdeaDto.urlDecode. e = " + e.getMessage());
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdeaText() {
		return ideaText;
	}

	public void setIdeaText(String ideaText) {
		this.ideaText = ideaText;
	}
}

