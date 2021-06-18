package com.huma.mail.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hudenian
 * @date 2021/6/9
 */
@Data
@Component
@ConfigurationProperties(prefix = "system.mail")
public class MailConfig {
    private String mailHost;
    private String mailUser;
    private String mailPwd;
    private String mailFrom;
    private String mailTo;
}
