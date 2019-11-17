package com.gaogao.cucaishop.adm.service;

import com.gaogao.cucaishop.adm.mappers.UserMapper;
import com.gaogao.cucaishop.home.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;


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
}
