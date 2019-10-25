package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class product_promotions extends generalInfo {
    private String promotion_id;
    private String product_id;
    private String product_detail_id;
    private String status;
}
