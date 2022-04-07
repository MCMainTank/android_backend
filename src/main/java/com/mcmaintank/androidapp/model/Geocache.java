package com.mcmaintank.androidapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-5 14:03
 */
@Entity
@Table(name="t_geocache_info",schema="android_app")
//@EqualsAndHashCode(callSuper = true)
@Data
public class Geocache {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long geocacheId;

    private Double geocacheLatitudes;

    private Double geocacheLongitudes;

    private String geocacheLocationDescription;

    private Long pid;

    private boolean deleted;

    private Date geocacheDateOfUpload;

    private Integer reported;

    public void setGeocacheId(Long geocacheId){
        this.geocacheId = geocacheId;
    }

    public boolean setGeocacheDateOfUpload(Date geocacheDateOfUpload){
        if(geocacheDateOfUpload!=null){
            this.geocacheDateOfUpload = geocacheDateOfUpload;
            return true;
        } else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Geocache) {
            return (geocacheId == ((Geocache) o).geocacheId);
        }
        return false;
    }

}
