package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Users extends GeneralInfo {
    private String userId;
    private String userName;
    private String password;
    private String fullName;
    private int gender;
    private String email;
    private String phone;
    private String haveShop;

    private Set<Roles> roles = new HashSet<>();
}
