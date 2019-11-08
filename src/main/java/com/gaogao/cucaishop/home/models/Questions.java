package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Questions extends GeneralInfo {
    private String questionId;
    private String userId;
    private String productId;
    private String questionContent;
    private String answerContent;
    private String type;
}
