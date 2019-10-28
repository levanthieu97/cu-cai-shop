package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetails extends GeneralInfo {
    private String productDetailsId;
    private String color;
    private String size;
    private int price;
    private int quantity;
    private String status;
}
