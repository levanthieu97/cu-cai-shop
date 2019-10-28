package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order_products extends GeneralInfo {
    private String order_id;
    private String product_id;
    private String product_detail_id;
    private String payment;
    private String status;
}
