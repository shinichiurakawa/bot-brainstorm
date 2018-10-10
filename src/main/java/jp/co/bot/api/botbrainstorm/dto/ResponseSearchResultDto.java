package jp.co.bot.api.botbrainstorm.dto;

import java.util.List;

public class ResponseSearchResultDto {
	private String status;
	private List<IdeaDto> result;

	public void urlEncode() {
		result.stream().forEach(idea->{
			idea.urlEncode();
		});
	}

	public String createJson() {
		StringBuilder ret = new StringBuilder();

		for (int idx = 0; idx < result.size(); idx++) {
			ret.append(String.valueOf(idx+1) + ") " + result.get(idx).getIdeaText() + "\n");
		}
		return new String(ret);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<IdeaDto> getResult() {
		return result;
	}

	public void setResult(List<IdeaDto> result) {
		this.result = result;
	}
}
