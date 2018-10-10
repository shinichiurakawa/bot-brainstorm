package jp.co.bot.api.botbrainstorm.service;


import jp.co.bot.api.botbrainstorm.dto.PostIdeaDto;
import jp.co.bot.api.botbrainstorm.dto.ResponseDto;
import jp.co.bot.api.botbrainstorm.dto.ResponseSearchResultDto;
import jp.co.bot.api.botbrainstorm.dto.SearchIdeaDto;

public interface BotBrainstormService {
    ResponseDto postIdea(PostIdeaDto postIdeaDto) throws Exception;
    ResponseSearchResultDto searchIdea(SearchIdeaDto searchIdeaDto) throws Exception;
}
