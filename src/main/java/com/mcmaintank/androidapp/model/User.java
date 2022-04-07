package com.mcmaintank.androidapp.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-5 22:17
 */
@Entity
@Table(name="t_user_info",schema="android_app")
//@EqualsAndHashCode(callSuper = true)
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String userPassword;

    private Integer userGroup;

    private Integer reported;

    public boolean equals(Object o) {
        if (o instanceof User) {
            return (userName == ((User) o).userName);
        }
        return false;
    }

}
