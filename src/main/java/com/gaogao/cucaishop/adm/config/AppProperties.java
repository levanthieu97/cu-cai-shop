package com.gaogao.cucaishop.adm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();

    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }

    public static class Auth{
        private String tokenSecret;
        private Long tokenExpiration;

        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public Long getTokenExpiration() {
            return tokenExpiration;
        }

        public void setTokenExpiration(Long tokenExpiration) {
            this.tokenExpiration = tokenExpiration;
        }
    }

    public static final class OAuth2{
        private List<String> authorizedRedirectUri = new ArrayList<>();

        public List<String> getAuthorizedRedirectUri() {
            return authorizedRedirectUri;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUri) {

            this.authorizedRedirectUri = authorizedRedirectUri;
            return this;
        }
    }
}
