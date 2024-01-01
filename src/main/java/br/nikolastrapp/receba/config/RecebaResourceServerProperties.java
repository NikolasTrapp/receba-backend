package br.nikolastrapp.receba.config;

import br.nikolastrapp.receba.config.resourceServer.IssuerConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties("receba.security")
public class RecebaResourceServerProperties {

    private List<IssuerConfig> issuers;

}
