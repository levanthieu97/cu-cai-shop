package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address extends GeneralInfo {
    private String addressId;
    private String userId;
    private String streetName;
    private String wardId;
    private String wardName;
    private String districtId;
    private String districtName;
    private String cityId;
    private String cityName;
    private String countryId;
    private String countryName;
    private String type;
}
