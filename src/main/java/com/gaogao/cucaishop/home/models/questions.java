package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class questions extends generalInfo {
    private String question_id;
    private String user_id;
    private String product_id;
    private String question_content;
    private String answer_content;
    private String type;
}
