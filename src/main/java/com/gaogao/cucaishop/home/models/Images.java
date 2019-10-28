package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Images extends GeneralInfo {
    private String imageId;
    private String userId;
    private String imageContent;
    private String status;
    private String type;
}
