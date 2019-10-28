package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Categories extends GeneralInfo {
    private String category_id;
    private String category_name;
    private String category_description;
    private String parent_id;
    private int level;
}
