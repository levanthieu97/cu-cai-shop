package com.gaogao.cucaishop.adm.oauth2;

import com.gaogao.cucaishop.adm.social.CookieUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*

- The OAuth2 protocol using a state parameter.
- During authentication, the application send this parameter in the authorization request, and the Oauth2
provider returns this parameter unchanged in the OAuth2 callback

- the application compares the value of the STATE parameter returned from the Oauth2 provider with the value
that it had sent initially.

-the application needs to store the state parameter somewhere
so that it can later compare it with the state returned from the OAuth2 provider.

- We’ll be storing the state as well as the 'redirect_uri' in a short-lived cookie.
The following class provides functionality for storing the authorization request in cookies and retrieving it.
 */

@Component
public class HttpCookieOAuth2 implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public static final String OAUTH2_COOKIE_NAME = "oauth2_auth_request";

    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    private static final int cookieExpireSeconds = 180;
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpCookieOAuth2.class);



    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return CookieUtils.getCookie(request,OAUTH2_COOKIE_NAME)
                .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class)).orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        if(authorizationRequest == null){
            CookieUtils.deleteCookie(request,response,OAUTH2_COOKIE_NAME);
            CookieUtils.deleteCookie(request,response,REDIRECT_URI_PARAM_COOKIE_NAME);
            return;
        }

        CookieUtils.addCookie(response, OAUTH2_COOKIE_NAME, CookieUtils.serialize(authorizationRequest), cookieExpireSeconds);
        String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
        if(StringUtils.isNotBlank(redirectUriAfterLogin)){
            CookieUtils.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin,cookieExpireSeconds);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }

    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response){
        CookieUtils.deleteCookie(request,response, OAUTH2_COOKIE_NAME);
        CookieUtils.deleteCookie(request,response, REDIRECT_URI_PARAM_COOKIE_NAME);
    }
}
