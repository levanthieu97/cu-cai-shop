package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Promotions extends GeneralInfo {
    private String promotion_id;
    private String promotion_name;
    private String promotion_content;
    private int percent;
    private Date from_date;
    private Date to_date;
    private String status;
    private String type;
}
