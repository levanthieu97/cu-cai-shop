package com.gaogao.cucaishop.adm.service;

import com.gaogao.cucaishop.adm.mappers.UserMapper;
import com.gaogao.cucaishop.adm.security.JwtUser;
import com.gaogao.cucaishop.home.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);


    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userMapper.findByUserName(username).orElseThrow(
                ()-> new UsernameNotFoundException("User not found with username: " + username));
        return JwtUser.create(users);
    }
}
