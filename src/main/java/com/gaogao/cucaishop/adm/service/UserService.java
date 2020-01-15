package com.gaogao.cucaishop.adm.service;

import com.gaogao.cucaishop.adm.mappers.UserMapper;
import com.gaogao.cucaishop.adm.security.JwtTokenProvider;
import com.gaogao.cucaishop.adm.social.AuthProvider;
import com.gaogao.cucaishop.common.models.CommonStrings;
import com.gaogao.cucaishop.home.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public Users loginUser(String email) {
        Users users = null;
        try {
            users = userMapper.findByEmail(email);
        } catch (Exception e) {
            LOGGER.error("Fail when call loginUser. ", e);
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return users;
    }

    @Transactional
    public int registerUser(Users user){
        int result = 0;
        try {
            if(user == null){
                return 0;
            }else {
                Users newUser = new Users(user.getEmail(),user.getFullName(),user.getPassword(), AuthProvider.local);
                // generatePassword for new user
                newUser.setPassword(jwtTokenProvider.generatePassword(user.getPassword()));
                newUser.setRoleId(CommonStrings.ROLE_USER);
                newUser.setCreateDate(new Date());
                newUser.setIsActive('Y');

                int successNewUser = userMapper.registerNewUser(newUser);
                if(successNewUser > 0) {
                    result = successNewUser;
                }
            }
        } catch (Exception e){
            LOGGER.error("Fail when call loginUser. ", e);
        }
        return result;
    }

    public Users checkUserValid(String email){
        try{
            Users user = userMapper.checkUser(email);
            if(user != null ){
                return user;
            }
        }catch (Exception e){
            LOGGER.error("Fail when call checkUserValid. ", e);
        }
        return null;
    }
}
