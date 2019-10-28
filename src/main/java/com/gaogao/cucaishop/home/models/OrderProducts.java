package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderProducts extends GeneralInfo {
    private String orderId;
    private String productId;
    private String productDetailId;
    private String payment;
    private String status;
}
