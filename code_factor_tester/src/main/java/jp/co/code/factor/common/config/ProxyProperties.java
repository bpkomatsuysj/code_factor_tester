package jp.co.code.factor.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "proxy")
public class ProxyProperties {

  private String host;

  private int port;

  private String user;

  private String password;

}
