package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product_promotions extends GeneralInfo {
    private String promotion_id;
    private String product_id;
    private String product_detail_id;
    private String status;
}