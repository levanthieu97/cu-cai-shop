package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductPromotions extends GeneralInfo {
    private String promotionId;
    private String productId;
    private String productDetailId;
    private String status;
}
