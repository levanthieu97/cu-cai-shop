package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Categories extends GeneralInfo {
    private String categoryId;
    private String categoryName;
    private String categoryDescription;
    private String parentId;
    private int level;
}
