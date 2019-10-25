package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class likes extends generalInfo {
    private String like_id;
    private String user_id_from;
    private String user_id_to;
    private String product_id;
    private String type;
}
