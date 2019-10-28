package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product_details extends GeneralInfo {
    private String product_details_id;
    private String color;
    private String size;
    private int price;
    private int quantity;
    private String status;
}
