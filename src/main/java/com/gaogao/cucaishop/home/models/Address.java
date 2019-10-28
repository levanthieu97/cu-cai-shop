package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address extends GeneralInfo {
    private String address_id;
    private String user_id;
    private String street_name;
    private String ward_id;
    private String ward_name;
    private String district_id;
    private String district_name;
    private String city_id;
    private String city_name;
    private String country_id;
    private String country_name;
    private String type;
}
