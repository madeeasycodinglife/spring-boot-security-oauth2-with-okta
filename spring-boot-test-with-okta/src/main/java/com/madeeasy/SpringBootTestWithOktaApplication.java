package com.madeeasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringBootTestWithOktaApplication {

    @GetMapping("/")
    public String getName(@AuthenticationPrincipal OidcUser oidcUser,
                          @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient,
                          OAuth2AuthenticationToken oAuth2AuthenticationToken,
                          Principal principal,
                          Authentication authentication) {
        String fullName = oidcUser.getFullName();
        String username = oidcUser.getName(); // OIDC username is typically the sub claim
        String clientId = oidcUser.getAttribute("client_id");

        System.out.println("isAuthenticated : " + authentication.isAuthenticated());
        // Access tokens
        OidcIdToken idToken = oidcUser.getIdToken(); // ID token

        String accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
//        String refreshToken = oAuth2AuthorizedClient.getRefreshToken().getTokenValue(); .getRefreshToken() is
//        returning null
        ClientRegistration clientRegistration = oAuth2AuthorizedClient.getClientRegistration();
        String principalName = oAuth2AuthorizedClient.getPrincipalName();

        // Other claims from OIDC ID token
        Map<String, Object> idTokenClaims = oidcUser.getClaims();

        return "Hello full Name : " + fullName +
                " username from oidcUser : " + username +
                " clientId from oidcUser : " + clientId +
                " ID Token: " + idToken +
                " Access Token: " + accessToken +
//                " Refresh Token: " + refreshToken +
                " claims from oidcUser : " + idTokenClaims +
                " clientRegistration : " + clientRegistration;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestWithOktaApplication.class, args);
    }

}
