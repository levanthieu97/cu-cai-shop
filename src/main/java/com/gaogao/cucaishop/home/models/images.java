package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class images extends generalInfo {
    private String image_id;
    private String user_id;
    private String image_content;
    private String status;
    private String type;
}
