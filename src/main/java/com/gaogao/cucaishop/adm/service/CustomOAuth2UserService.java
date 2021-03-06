package com.gaogao.cucaishop.adm.service;

import com.gaogao.cucaishop.adm.mappers.UserMapper;
import com.gaogao.cucaishop.adm.security.JwtUser;
import com.gaogao.cucaishop.adm.social.AuthProvider;
import com.gaogao.cucaishop.adm.social.OAuth2AuthenticationProcessingException;
import com.gaogao.cucaishop.adm.social.OAuth2UserInfo;
import com.gaogao.cucaishop.adm.social.OAuth2UserInfoFactory;
import com.gaogao.cucaishop.common.models.CommonStrings;
import com.gaogao.cucaishop.home.models.RoleName;
import com.gaogao.cucaishop.home.models.Roles;
import com.gaogao.cucaishop.home.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    UserMapper userMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomOAuth2UserService.class);


    private Users registerUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        Users users = new Users();
        users.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        users.setProviderId(oAuth2UserInfo.getId());
        users.setFirstName(oAuth2UserInfo.getFirstName());
        users.setLastName(oAuth2UserInfo.getLastName());
        users.setFullName(oAuth2UserInfo.getName());
        users.setEmail(oAuth2UserInfo.getEmail());
        users.setImageUrl(oAuth2UserInfo.getImageUrl());
        users.setCreateDate(new Date());
        users.setRoleId(CommonStrings.ROLE_USER);
        users.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        users.setRoles(new Roles(RoleName.ROLE_USER));
//
//        // milliseconds
//        long miliSec = 1573809200568L;
//
//        // Creating date format
//        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
//
//        // Creating date from milliseconds
//        // using Date() constructor
//        Date result = new Date(miliSec);
//
//        // Formatting Date according to the
//        // given format
//        System.out.println(simple.format(result));

        try {
            int result = userMapper.saveInfoBySocial(users);
            if (result > 0) {
                return users;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    private Users updateExistUser(Users users, OAuth2UserInfo oAuth2UserInfo) {
        users.setFirstName(oAuth2UserInfo.getFirstName());
        users.setLastName(oAuth2UserInfo.getLastName());
        users.setFullName(oAuth2UserInfo.getName());
        users.setImageUrl(oAuth2UserInfo.getImageUrl());

        try {
            int result = userMapper.updateExistingUser(users);
            if(result > 0) return users;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    /*
    - this method is called after an access is token is obtained from the OAuth2 provider

    - In this method, we first fetch the user’s details from the OAuth2 provider. If a user with the same email
    already exists in our database then we update his details, otherwise, we register a new user.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return processOAuth2User(userRequest, oAuth2User);
        }
        catch (AuthenticationException ex) {
            throw ex;
        }catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Users users = userMapper.findByEmail(oAuth2UserInfo.getEmail());

        if (users != null) {
            if (!users.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        users.getProvider() + " account. Please use your " + users.getProvider() +
                        " account to login.");
            }
            users = updateExistUser(users, oAuth2UserInfo);
        } else {
            users = registerUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return JwtUser.create(users, oAuth2User.getAttributes());
    }

}
