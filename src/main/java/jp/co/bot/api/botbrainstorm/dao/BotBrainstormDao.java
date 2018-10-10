package jp.co.bot.api.botbrainstorm.dao;

import jp.co.bot.api.botbrainstorm.dto.IdeaDto;
import jp.co.bot.api.botbrainstorm.dto.PostIdeaDto;
import jp.co.bot.api.botbrainstorm.dto.SearchIdeaDto;

import java.util.List;

public interface BotBrainstormDao {
    void postIdea(PostIdeaDto postIdeaDto) throws Exception;
    List<IdeaDto> searchIdea(SearchIdeaDto searchIdeaDto) throws Exception;
}
