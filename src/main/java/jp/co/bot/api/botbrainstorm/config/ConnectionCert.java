package jp.co.bot.api.botbrainstorm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "connectioncert")
public class ConnectionCert {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String id;
    private String key;

    public Boolean check(String target_id, String target_key) {
        logger.info("[IN] CoonnectionCert.check()");
        Boolean ret = false;
        if (!id.equals(target_id)) {
            logger.warn("[---] CoonnectionCert.check() unauthorized connection detected.");
            logger.info("[OUT] CoonnectionCert.check() false id");
            return false;
        }
        if (!key.equals(target_key)) {
            logger.warn("[---] CoonnectionCert.check() unauthorized connection detected.");
            logger.info("[OUT] CoonnectionCert.check() false key");
            return false;
        }
        logger.info("[OUT] CoonnectionCert.check() true");
        return true;
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
}

