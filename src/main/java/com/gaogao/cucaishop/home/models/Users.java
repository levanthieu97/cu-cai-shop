package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Users extends generalInfo {
    private String user_id;
    private String user_name;
    private String password;
    private String full_name;
    private int gender;
    private String email;
    private String phone;
    private String have_shop;
}
