package jp.co.bot.api.botbrainstorm.service;


import jp.co.bot.api.botbrainstorm.dao.BotBrainstormDao;
import jp.co.bot.api.botbrainstorm.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BotBrainstormImplService implements BotBrainstormService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BotBrainstormDao botBrainstormDao;

    public ResponseDto postIdea(PostIdeaDto postIdeaDto) {
        logger.info("[IN] BotBrainstormService.postIdea()");
        ResponseDto response = new ResponseDto();
        try {
            botBrainstormDao.postIdea(postIdeaDto);
            response.setStatus(postIdeaDto.logString());
        } catch (Exception e) {
            logger.info("[Exception] BotBrainstormService.postIdea()");
            response.setStatus("[Exception] BotBrainstormImplService.postIdea: " + e.getMessage());
        }
        logger.info("[OUT] BotBrainstormService.postIdea()");
        return response;
    }

    public ResponseSearchResultDto searchIdea(SearchIdeaDto searchIdeaDto) throws Exception {
        logger.info("[IN] BotBrainstormService.searchIdea()");
        ResponseSearchResultDto response = new ResponseSearchResultDto();
        try {
            List<IdeaDto> ideaList = botBrainstormDao.searchIdea(searchIdeaDto);
            response.setResult(ideaList);
            response.setStatus("OK");
        } catch (Exception e) {
            logger.info("[Exception] BotBrainstormService.searchIdea()");
            throw e;
        }
        logger.info("[OUT] BotBrainstormService.searchIdea()");
        return response;
    }

}
