package jp.co.bot.api.botbrainstorm.dao;

import jp.co.bot.api.botbrainstorm.dto.IdeaDto;
import jp.co.bot.api.botbrainstorm.dto.PostIdeaDto;
import jp.co.bot.api.botbrainstorm.dto.SearchIdeaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BotBrainstormImplDao implements BotBrainstormDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setNamedParameJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String postIdeaSql() {
        String sql = "INSERT INTO idea" +
                " (user_id" +
                " ,idea_text" +
                " ,valuation) " +
                "VALUE " +
                " (:user_id" +
                " ,:idea_text" +
                " ,0) ";
        return sql;
    }

    private String searchIdeaSql() {
        String sql = "SELECT user_id, idea_text, valuation FROM idea " +
                " WHERE idea_text LIKE :keyword AND user_id = :user_id ORDER BY valuation LIMIT 15";
//        " WHERE idea_text LIKE :keyword AND user_id = :user_id ORDER BY valuation LIMIT 15" ;
        return sql;
    }

    public void postIdea(PostIdeaDto postIdeaDto) throws Exception {
        logger.info("[IN] BotBrainstormDto.postIdea()");
        try {
            logger.info("[--] BotBrainstormDto.postIdea postIdeaDto = " + postIdeaDto.logString());
            String sql = postIdeaSql();
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters
                    .addValue("user_id",postIdeaDto.getUserId())
                    .addValue("idea_text",postIdeaDto.getIdeaText())
                    ;
            jdbcTemplate.update(sql,parameters);

            logger.info("[OUT] BotBrainstormDao.postIdea()");
        } catch(Exception e) {
            logger.error("[Exception] BotBrainstormImplDao.postIdea e = " + e.getMessage());
            throw e;
        }
    }

    private List<IdeaDto> mapForSearchIdeas(List<Map<String, Object>> results) {
        List<IdeaDto> ideaList = new ArrayList<>();

        for (Map<String, Object> r : results) {
            IdeaDto idea = new IdeaDto();

            idea.setUserId(String.valueOf(r.get("user_id")));
            idea.setIdeaText(String.valueOf(r.get("idea_text")));
            idea.setValuation((int)r.get("valuation"));
            ideaList.add(idea);
            logger.info("get idea = " + idea.logString());
        }
        return ideaList;
    }

    public List<IdeaDto> searchIdea(SearchIdeaDto searchIdeaDto) throws Exception {
        logger.info("[IN] BotBrainstormDto.searchIdea()");
        List<IdeaDto> ideaList = null;
        try {
            String sql = searchIdeaSql();
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters
                    .addValue("user_id",searchIdeaDto.getUserId())
                    .addValue("keyword","%" + searchIdeaDto.getKeyword() + "%")
            ;
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql,parameters);

            logger.info("BotBrainstormDao.searchIdea  searchIdeaDto = " + searchIdeaDto.logString());
            logger.info("BotBrainstormDao.searchIdea  get idea num = " + String.valueOf(results.size()));
            ideaList = mapForSearchIdeas(results);

        } catch(Exception e) {
            logger.error("[Exception] BotBrainstormImplDao.searchIdea");
            throw e;
        }
        logger.info("[OUT] BotBrainstormDao.searchIdea()");
        return ideaList;
    }
}
