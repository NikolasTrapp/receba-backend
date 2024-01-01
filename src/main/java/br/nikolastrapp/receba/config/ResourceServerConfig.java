package br.nikolastrapp.receba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

import static java.util.Objects.isNull;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/user/register", "/h2-console/**", "/actuator/health").permitAll()
                .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .cors().disable()
            .oauth2ResourceServer(resourceServer -> {
                resourceServer.jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
            }).oauth2Login();

        return http.formLogin(Customizer.withDefaults()).build();
    }

    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter((jwt) -> {
            var authorities = jwt.getClaimAsStringList("authorities");
            if (isNull(authorities)) {
                return Collections.emptyList();
            }
            var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
            var grantedAuthorities = jwtGrantedAuthoritiesConverter.convert(jwt);
            var grantedUserAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).toList();

            grantedAuthorities.addAll(grantedUserAuthorities);
            return grantedAuthorities;
        });
        return converter;
    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder
//                .withJwkSetUri(jwkSetUri)
//                .jwsAlgorithm(SignatureAlgorithm.RS256).build();
//    }
}
