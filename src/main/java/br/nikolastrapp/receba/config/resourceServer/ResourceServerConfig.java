package br.nikolastrapp.receba.config.resourceServer;

import br.nikolastrapp.receba.config.RecebaResourceServerProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;

import static java.util.Objects.isNull;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ResourceServerConfig {

    private final RecebaResourceServerProperties properties;

    @Bean
    @Order(1)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/user/register", "/h2-console/**", "/actuator/health").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors().disable()
                .oauth2ResourceServer()
                    .authenticationManagerResolver(buildResolver());

        return http.formLogin(Customizer.withDefaults()).build();
    }

    private JwtIssuerAuthenticationManagerResolver buildResolver() {
        var managers = new HashMap<String, AuthenticationManager>();
        var issuers = properties.getIssuers();
        for (IssuerConfig issuer : issuers) {
            log.debug("setting up issuer config for {}", issuer.getUri());
            var decoder = toDecoder(issuer);
            var provider = new JwtAuthenticationProvider(decoder);
            provider.setJwtAuthenticationConverter(new JwtAuthenticationConverter());
            managers.put(issuer.getUri(), provider::authenticate);
        }
        return new JwtIssuerAuthenticationManagerResolver(managers::get);
    }

    private NimbusJwtDecoder toDecoder(IssuerConfig issuer) {
        NimbusJwtDecoder decoder = JwtDecoders.fromOidcIssuerLocation(issuer.getUri());
        OAuth2TokenValidator<Jwt> audienceValidator = new JwtAudienceValidator(issuer.getAudience());
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer.getUri());
        OAuth2TokenValidator<Jwt> opaco = JwtValidators.createDefault();
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
        decoder.setJwtValidator(withAudience);
        return decoder;
    }


    //TODO why is this bean necessary? I need to figure out what is making this bean necessary even if i dont need clients.
    @Bean
    public ClientRegistrationRepository registeredClientRepository() {
        return new InMemoryClientRegistrationRepository();
    }

}
