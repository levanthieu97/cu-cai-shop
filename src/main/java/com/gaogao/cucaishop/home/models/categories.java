package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class categories extends generalInfo {
    private String category_id;
    private String category_name;
    private String category_description;
    private String parent_id;
    private int level;
}
