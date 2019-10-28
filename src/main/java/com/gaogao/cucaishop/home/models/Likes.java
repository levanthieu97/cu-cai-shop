package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Likes extends GeneralInfo {
    private String likeId;
    private String userIdFrom;
    private String userIdTo;
    private String productId;
    private String type;
}
