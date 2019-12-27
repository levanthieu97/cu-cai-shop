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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Users users = userMapper.findByEmail(email);

            if(users == null){
                throw new UsernameNotFoundException(email);
            }
            return JwtUser.create(users);
        } catch (Exception e) {
            LOGGER.error("User not found", e);
            throw new UsernameNotFoundException("User not found with username: " + email);
        }


    }
}
