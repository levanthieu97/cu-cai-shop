package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Promotions extends GeneralInfo {
    private String promotionId;
    private String promotionName;
    private String promotionContent;
    private int percent;
    private Date fromDate;
    private Date toDate;
    private String status;
    private String type;
}
