package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class order_products extends generalInfo {
    private String order_id;
    private String product_id;
    private String product_detail_id;
    private String payment;
    private String status;
}
