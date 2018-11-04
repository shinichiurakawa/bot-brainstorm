package jp.co.bot.api.botbrainstorm.dto;

import org.apache.commons.codec.net.URLCodec;

import java.util.ArrayList;
import java.util.List;

public class IdeaDto {
	private String userId;
	private String ideaText;
	private Integer valuation;

	public String logString() {
		List<String> item_list = new ArrayList<>();
		item_list.add("userId:" + userId);
		item_list.add("ideaText:" + ideaText);
		item_list.add("valuation:" + valuation);
		return String.join(",",item_list);
	}

	/*
	public void urlEncode() {
		URLCodec codec = new URLCodec("UTF-8");
		try {
			ideaText = codec.encode(ideaText, "UTF-8");
		} catch (Exception e) {
			System.out.println("Exception at IdeaDto.urlEncode. e = " + e.getMessage());
		}
	}
	*/

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

	public Integer getValuation() {
		return this.valuation;
	}

	public void setValuation(int valuation) {
		this.valuation = valuation;
	}
}

