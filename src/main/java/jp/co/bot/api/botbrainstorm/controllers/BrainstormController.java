package jp.co.bot.api.botbrainstorm.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.bot.api.botbrainstorm.dto.PostIdeaDto;
import jp.co.bot.api.botbrainstorm.dto.ResponseDto;
import jp.co.bot.api.botbrainstorm.dto.ResponseSearchResultDto;
import jp.co.bot.api.botbrainstorm.dto.SearchIdeaDto;
import jp.co.bot.api.botbrainstorm.service.BotBrainstormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BrainstormController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BotBrainstormService brainstormService;

    @PostMapping("/searchIdea")
    public String searchIdea(@RequestBody SearchIdeaDto searchIdeaDto) {
        logger.info("[IN] BrainstormController.searchIdea()");
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);
        String response_json = "";
        try {
            searchIdeaDto.urlDecode();
            logger.info("[--] BrainstormController.searchIdea searchIdeaDto = " + searchIdeaDto.logString());

            ResponseSearchResultDto responseDto = brainstormService.searchIdea(searchIdeaDto);
            responseDto.urlEncode();
            //response_json = mapper.writeValueAsString(responseDto);
            response_json = responseDto.createJson();
        } catch (Exception e) {
            response_json = "Error : searchIdea, e = " + e.getMessage();
        }
        logger.info("[OUT] BrainstormController.searchIdea() response_json = " + response_json);
        return response_json;
    }

    @PostMapping("/postIdea")
    public String postIdea(@RequestBody PostIdeaDto postIdeaDto) {
        logger.info("[IN] BrainstormController.postIdea()");
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);
        String response_json = "";
        try {
            postIdeaDto.urlDecode();
            logger.info("[--] BrainstormController.searchIdea searchIdeaDto = " + postIdeaDto.logString());
            ResponseDto responseDto = brainstormService.postIdea(postIdeaDto);
            response_json = mapper.writeValueAsString(responseDto);

        } catch (Exception e) {
		    response_json = "Error : searchIdea, e = " + e.getMessage();
        }
        logger.info("[OUT] BrainstormController.postIdea() response_json = " + response_json);
        return response_json;
    }
}


