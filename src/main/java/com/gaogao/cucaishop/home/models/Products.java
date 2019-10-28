package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Products extends GeneralInfo {
    private String product_id;
    private String shop_id;
    private String product_name;
    private String trademark;
    private String origin;
    private String warranty;
    private String warranty_condition;
    private String content;
    private String short_description;
    private String description;
    private int quantity;
    private String status;
}