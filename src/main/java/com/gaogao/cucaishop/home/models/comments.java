package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class comments extends generalInfo {
    private String comment_id;
    private String user_id;
    private String parent_id;
    private String product_id;
    private float rate;
    private String type;
    private String status;
}
