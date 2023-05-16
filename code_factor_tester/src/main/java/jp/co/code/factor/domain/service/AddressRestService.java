package jp.co.code.factor.domain.service;

import java.net.PasswordAuthentication;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import jp.co.code.factor.common.config.ProxyProperties;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

@Service
public class AddressRestService {

  @Autowired
  private ProxyProperties proxyProperties;

  public String getAddress() {

    Function<String, String> passwordHandler = (username) -> {
      PasswordAuthentication pa = getPasswordAuthenticationFromKeychain(username);
      if (pa != null) {
        return new String(pa.getPassword());
      }
      return null;
    };

    ReactorClientHttpConnector httpConnector =
        new ReactorClientHttpConnector((HttpClient.create().proxy(proxy -> {
          proxy.type(ProxyProvider.Proxy.HTTP).host(proxyProperties.getHost())
              .port(proxyProperties.getPort()).username(proxyProperties.getUser())
              .password(passwordHandler);
        })));

    WebClient webClient = WebClient.builder().baseUrl("https://zipcloud.ibsnet.co.jp")
        .clientConnector(httpConnector).build();

    return webClient.get().uri("/api/search?zipcode=7830060").retrieve().bodyToMono(String.class)
        .block();
  }

  private PasswordAuthentication getPasswordAuthenticationFromKeychain(String username) {
    return new PasswordAuthentication(username, proxyProperties.getPassword().toCharArray());
  }
}
