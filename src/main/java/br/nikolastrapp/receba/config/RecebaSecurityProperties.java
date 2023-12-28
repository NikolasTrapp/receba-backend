package br.nikolastrapp.receba.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("receba.security")
public class RecebaSecurityProperties {

    @NotBlank
    private String providerUrl;

    @NotNull
    private Resource jksLocation;

    @NotBlank
    private String password;

    @NotBlank
    private String keypairAlias;
}
