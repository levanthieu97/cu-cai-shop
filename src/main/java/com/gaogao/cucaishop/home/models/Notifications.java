package com.gaogao.cucaishop.home.models;

import com.gaogao.cucaishop.common.models.GeneralInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Notifications extends GeneralInfo {
    private String notificationId;
    private String notificationName;
    private String notificationContent;
    private Date endDate;
    private String type;
}
