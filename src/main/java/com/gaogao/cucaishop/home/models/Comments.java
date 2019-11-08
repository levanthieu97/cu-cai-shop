package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comments extends GeneralInfo {
    private String commentId;
    private String userId;
    private String parentId;
    private String productId;
    private float rate;
    private String type;
    private String status;
}
