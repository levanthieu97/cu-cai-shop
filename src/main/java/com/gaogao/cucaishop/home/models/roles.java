package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class roles extends generalInfo {
    private String role_id;
    private String role_name;
    private String role_description;
}
