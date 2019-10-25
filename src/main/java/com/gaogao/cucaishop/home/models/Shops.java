package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Shops extends generalInfo {
    private String shop_id;
    private String user_id;
    private String shop_name;
    private String address_id;
    private String email;
    private String phone;
    private String website;
    private String facebook;
    private String status;
}
