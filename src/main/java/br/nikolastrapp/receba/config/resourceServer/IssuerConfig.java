package br.nikolastrapp.receba.config.resourceServer;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Data
public class IssuerConfig {

    private String uri;
    private String audience;
}
