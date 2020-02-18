package com.gaogao.cucaishop.adm.controller;

import com.gaogao.cucaishop.adm.security.JwtAuthenticationFilter;
import com.gaogao.cucaishop.adm.service.UserService;
import com.gaogao.cucaishop.common.models.ResponseObject;
import com.gaogao.cucaishop.home.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/profile")
    public ResponseObject getProfile(@RequestAttribute(JwtAuthenticationFilter.REQ_USR) String jwtUser) {
        ResponseObject responseObject = new ResponseObject();
        try{
            Users result  = userService.loginUser(jwtUser);
            if(result != null ){
                responseObject.setSuccess(true);
                responseObject.setData(result);
            }else{
                responseObject.setSuccess(false);
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }

        return responseObject;
    }

}
