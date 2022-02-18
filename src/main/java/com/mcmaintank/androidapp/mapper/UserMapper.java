package com.mcmaintank.androidapp.mapper;

import com.mcmaintank.androidapp.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-10 10:22
 */
public interface UserMapper {

    @Select("select * from t_user_info where user_id = #{userId}")
    User selectUserById(@Param("userId")Long userId);

    @Insert("insert into t_user_info(user_name,user_password,user_group) " +
            "values(#{userLoginname},#{userPassword},#{userEmail})")
    int insertUser(User user);

    @Select("select * from t_user_info where user_name = #{userName}")
    User selectUserByName(@Param("userName")String userName);

    @Select("select user_password from t_user_info where user_name = #{userName}")
    String getPassword(String userName);
}
