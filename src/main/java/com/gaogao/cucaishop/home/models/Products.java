package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Products extends GeneralInfo {
    private String productId;
    private String shopId;
    private String productName;
    private String trademark;
    private String origin;
    private String warranty;
    private String warrantyCondition;
    private String content;
    private String shortDescription;
    private String description;
    private int quantity;
    private String status;
}
