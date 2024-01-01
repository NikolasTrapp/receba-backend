package br.nikolastrapp.receba.config.resourceServer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
public class JwtAudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final Collection<String> audiences;

    public JwtAudienceValidator(String... audiences) {
        this(Arrays.asList(audiences));
    }

    public JwtAudienceValidator(Collection<String> audiences) {
        this.audiences = audiences;
    }

    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        log.debug("validating audience {} against audiences {}", jwt.getAudience(), audiences);
        if (isValid(jwt.getAudience())) {
            return OAuth2TokenValidatorResult.success();
        }
        log.info("jwt audience is invalid");
        return OAuth2TokenValidatorResult.failure(buildError());
    }

    private boolean isValid(List<String> jwtAudiences) {
        return jwtAudiences.stream().anyMatch(audiences::contains);
    }

    private static OAuth2Error buildError() {
        return new OAuth2Error("invalid_token", "The required audience is missing", null);
    }
}
