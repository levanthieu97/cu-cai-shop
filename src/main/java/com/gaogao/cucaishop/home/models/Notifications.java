package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Notifications extends GeneralInfo {
    private String notification_id;
    private String notification_name;
    private String notification_content;
    private Date end_date;
    private String type;
}
