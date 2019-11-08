package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Shops extends GeneralInfo {
    private String shopId;
    private String userId;
    private String shopName;
    private String addressId;
    private String email;
    private String phone;
    private String website;
    private String facebook;
    private String status;
}
