package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comments extends GeneralInfo {
    private String comment_id;
    private String user_id;
    private String parent_id;
    private String product_id;
    private float rate;
    private String type;
    private String status;
}
