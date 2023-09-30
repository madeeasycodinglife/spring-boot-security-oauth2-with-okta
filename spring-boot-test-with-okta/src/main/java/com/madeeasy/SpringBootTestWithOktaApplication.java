package com.madeeasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class SpringBootTestWithOktaApplication {

    @GetMapping("/")
    public String getName(@AuthenticationPrincipal OidcUser oidcUser) {
        return "Hello " + oidcUser.getFullName();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestWithOktaApplication.class, args);
    }

}
