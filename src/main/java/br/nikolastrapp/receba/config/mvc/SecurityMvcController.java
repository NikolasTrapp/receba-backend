package br.nikolastrapp.receba.config.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class SecurityMvcController {

    private final RegisteredClientRepository registeredClientRepository;
    private final OAuth2AuthorizationConsentService consentService;

    @GetMapping("/oauth2/consent")
    public String consent(
            Principal principal,
            Model model,
            @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
            @RequestParam(OAuth2ParameterNames.SCOPE) String scope,
            @RequestParam(OAuth2ParameterNames.STATE) String state
    ) {
        var client = this.registeredClientRepository.findByClientId(clientId);

        if(client == null) {
            throw new AccessDeniedException(String.format("Cliente de %s não foi encontrado", clientId));
        }

        var consent = this.consentService.findById(client.getId(), principal.getName());

        var scopeArray = StringUtils.delimitedListToStringArray(scope, " ");
        var scopesParaAprovar = new HashSet<>(Set.of(scopeArray));

        var scopesAprovadosAnteriormente = new HashSet<String>();
        if (consent != null) {
            scopesAprovadosAnteriormente.addAll(consent.getScopes());
            scopesParaAprovar.removeAll(scopesAprovadosAnteriormente);
        }

        model.addAttribute("clientId", clientId);
        model.addAttribute("state", state);
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("scopesParaAprovar", scopesParaAprovar);
        model.addAttribute("scopesAprovadosAnteriormente", scopesAprovadosAnteriormente);

        return "pages/approval";
    }


}
