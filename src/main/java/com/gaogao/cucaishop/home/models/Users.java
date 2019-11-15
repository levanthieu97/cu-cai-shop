package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.adm.social.AuthProvider;
import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Users extends GeneralInfo {
    private String userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private int gender;
    private String email;
    private String phone;
    private char haveShop;
    private char isActive;
    private Date createDate;
    private Date updateDate;
    private String providerId;
    private String imageUrl;

    @NotNull
    private AuthProvider provider;

    private Set<Roles> roles = new HashSet<>();
}
