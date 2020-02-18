package com.gaogao.cucaishop.home.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gaogao.cucaishop.adm.social.AuthProvider;
import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@JsonIgnoreProperties({"user_id","role_id","password"})
@Data
public class Users extends GeneralInfo {

    private String userId;

    @NotBlank
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    private String password;
    private String firstName;
    private String lastName;
    private int gender;
    private String phone;
    private char haveShop;
    private char isActive;
    private Date createDate;
    private Date updateDate;
    private String providerId;
    private String imageUrl;
    private String roleId;

    private AuthProvider provider;
    private Roles roles;

    public Users(@NotBlank String email, @NotBlank String fullName, @NotBlank String password, AuthProvider provider) {
        this.userId = "USR" + System.currentTimeMillis();
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.provider = provider;
    }

    public Users(){
        this.userId = "USR" + System.currentTimeMillis();
    }

    //    private Set<Roles> roles = new HashSet<>();
}
