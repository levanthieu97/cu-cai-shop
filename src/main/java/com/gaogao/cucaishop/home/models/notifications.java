package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.generalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class notifications extends generalInfo {
    private String notification_id;
    private String notification_name;
    private String notification_content;
    private Date end_date;
    private String type;
}
