package br.nikolastrapp.receba.config.authorizationServer;

import br.nikolastrapp.receba.entities.RoleEntity;
import br.nikolastrapp.receba.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class CustomJwtTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    private final UserRepository userRepository;

    @Override
    public void customize(JwtEncodingContext context) {
        var authentication = context.getPrincipal();
        if (authentication.getPrincipal() instanceof User user) {
            var userEntity = userRepository.findByEmailOrUsername(user.getUsername()).orElseThrow();
            var authorities = userEntity.getAuthorities().stream().map(RoleEntity::getAuthority).collect(Collectors.toSet());
            var grantType = ofNullable(context.getAuthorization())
                    .map(oAuth2Authorization -> oAuth2Authorization.getAuthorizationGrantType().getValue())
                    .orElse(AuthorizationGrantType.AUTHORIZATION_CODE.getValue());

            context.getClaims().claim("user_id", userEntity.getId().toString());
            context.getClaims().claim("authorities", authorities);
            context.getClaims().claim("client_id", context.getRegisteredClient().getClientId());
            context.getClaims().claim("grant_type", grantType);

        }
    }
}
