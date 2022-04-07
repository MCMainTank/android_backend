package com.mcmaintank.androidapp.mapper;

import com.mcmaintank.androidapp.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-10 10:22
 */
@Repository
public interface UserMapper {

    @Select("select * from t_user_info where user_id = #{userId}")
    User selectUserById(@Param("userId")Long userId);

    @Insert("insert into t_user_info(user_name,user_password,user_group) " +
            "values(#{userName},#{userPassword},#{userGroup})")
    int insertUser(User user);

    @Select("select * from t_user_info where user_name = #{userName}")
    User selectUserByName(@Param("userName")String userName);

    @Select("select user_password from t_user_info where user_name = #{userName}")
    String getPassword(String userName);

    @Select("select user_group from t_user_info where user_name = #{userName}")
    int selectUserGroupByName(@Param("userName")String userName);

    @Update("update t_user_info set deleted = 1 where user_id = #{userId}")
    int logicDeleteUser(@Param("userId")Long userId);

    @Select("select deleted from t_user_info where user_name = #{userName}")
    int getDeleted(@Param("userName")String userName);

}
