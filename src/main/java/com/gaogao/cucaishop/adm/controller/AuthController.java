package com.gaogao.cucaishop.adm.controller;

import com.gaogao.cucaishop.adm.models.LoginRequest;
import com.gaogao.cucaishop.adm.models.ResponseApi;
import com.gaogao.cucaishop.adm.security.JwtTokenProvider;
import com.gaogao.cucaishop.adm.security.JwtUser;
import com.gaogao.cucaishop.adm.service.UserService;
import com.gaogao.cucaishop.common.models.CommonStrings;
import com.gaogao.cucaishop.common.models.ResponseObject;
import com.gaogao.cucaishop.home.models.Roles;
import com.gaogao.cucaishop.home.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    private UserService userService;

    private Users validUser(String email, String password) {
        Users users = userService.loginUser(email);
        if (users != null && jwtTokenProvider.validatePassword(password, users.getPassword())) {
            return users;
        }
        return null;
    }

    // @Valid -> annotation Validation check @NotNull,@Min,@NotBlank,...
    @PostMapping("/sign-in")
    public ResponseObject authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Users user = validUser(loginRequest.getUsername(), loginRequest.getPassword());
            if (user != null) {
                ResponseApi responseApi = new ResponseApi();
                responseApi.setAccount(user);
                responseApi.setToken(jwtTokenProvider.generateToken((Authentication) JwtUser.create(user)));
                Roles userRole = user.getRoles();
                responseApi.setRole(userRole.getRoleName());

                // result API
                responseObject.setData(responseApi);
                responseObject.setSuccess(true);
                responseObject.setMessage(CommonStrings.RESP_MSG_SUCCESS);
            } else {
                responseObject.setSuccess(false);
                responseObject.setMessage("Tài khoản hoặc mật khẩu không đúng");
            }
            return responseObject;
        } catch (Exception e) {
            LOGGER.error("Fail when call authenticateUser " + e.toString());
            throw e;
        }
    }

    @PostMapping("/register")
    public ResponseObject registerNewUser(@Valid @RequestBody Users users) {
        ResponseObject responseObject = new ResponseObject();
        try{
            if(userService.checkUserValid(users.getEmail()) != null){
                responseObject.setMessage("Tài khoản đã tồn tại !!!");
                responseObject.setSuccess(false);
            }else{
                int result = userService.registerUser(users);
                if(result > 0 ) {
                    responseObject.setData(result);
                    responseObject.setSuccess(true);
                    responseObject.setMessage("Đăng ký thành công !!!");
                }else{
                    responseObject.setData(result);
                    responseObject.setSuccess(false);
                }
            }
            return responseObject;
        }catch (Exception e){
            LOGGER.error("Fail when call registerNewUser : " + e.toString());
            throw e;
        }
    }
}
