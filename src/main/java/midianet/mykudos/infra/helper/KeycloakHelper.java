package midianet.mykudos.infra.helper;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class KeycloakHelper {

    @Autowired
    private HttpServletRequest request;

    private Optional<AccessToken> getToken() {
        if( ObjectUtils.isEmpty(request.getUserPrincipal())) return Optional.empty();
        final var user = (KeycloakAuthenticationToken) request.getUserPrincipal();
        final var token = user.getAccount().getKeycloakSecurityContext().getToken();
        return Optional.ofNullable(token);
    }

    public String getUserName() {
        return getToken().map(token -> token.getPreferredUsername()).orElse("");
    }

}
