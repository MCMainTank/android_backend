package com.mcmaintank.androidapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_activity_info",schema="android_app")
//@EqualsAndHashCode(callSuper = true)
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long activityId;

    private Date activityDateOfUpload;

    private Long userId;

    private Long geocacheId;

    private String activityType;

    private String activityContent;

    private boolean deleted;

}
