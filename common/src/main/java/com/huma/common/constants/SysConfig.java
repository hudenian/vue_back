package com.huma.common.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hudenian
 * @date 2021/6/9
 */
@Data
@Component
@ConfigurationProperties(prefix = "system.config")
public class SysConfig {
    private long loginTimeOut = 1800000;
    private boolean kickMode = true;
}
