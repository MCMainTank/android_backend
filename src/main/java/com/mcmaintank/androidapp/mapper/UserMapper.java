package com.mcmaintank.androidapp.mapper;

import com.mcmaintank.androidapp.model.Activity;
import com.mcmaintank.androidapp.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("SELECT * FROM t_user_info where deleted = 0 order by reported desc LIMIT 0,10")
    List<User> selectTopTenReportedUsers();

    @Update("update t_user_info set reported = #{reportedSum} where user_id = #{userId}")
    void updateUserReported(@Param("reportedSum")Integer reportedSum,@Param("userId")Long userId);

    @Insert("insert into t_activity_info(user_id,geocache_id,activity_type,activity_date_of_upload,activity_content,deleted) "+
            "values(#{userId},#{geocacheId},#{activityType},#{activityDateOfUpload},#{activityContent},#{deleted})")
    int insertActivity(Activity activity);

    @Select("select * from t_activity_info where user_id = #{userId}")
    List<Activity> selectActivityByUserId(@Param("userId")Long userId);

    @Select("select max(activity_id) from t_activity_info where user_id = #{userId} and deleted = 0")
    int selectLatestActivityByUserName(@Param("userId")Long userId);

    @Select("select max(activity_id) from t_activity_info where user_id = #{userId} and geocache_id = #{geocacheId} and activity_type = \"REPORT\"")
    int selectReportActivityId(@Param("userId")Long userId,@Param("geocacheId")Long geocacheId);

}
