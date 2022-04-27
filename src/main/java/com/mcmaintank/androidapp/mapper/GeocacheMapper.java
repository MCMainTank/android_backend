package com.mcmaintank.androidapp.mapper;

import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-9 21:11
 */
@Repository
public interface GeocacheMapper {


    @Insert("insert into t_geocache_info(geocache_latitudes,geocache_longitudes,geocache_location_description,pid,deleted,geocache_date_of_upload) " +
            "values(#{geocacheLatitudes},#{geocacheLongitudes},#{geocacheLocationDescription},#{pid},#{deleted},#{geocacheDateOfUpload})")
    int insertGeocache(Geocache geocache);

    @Select("select * from t_geocache_info where geocache_id = #{geocacheId} and deleted = 0")
    Geocache selectGeocacheById(@Param("geocacheId")Long geocacheId);

    @Select("select * from t_geocache_info where pid = #{userId} and deleted = 0")
    List<Geocache> selectGeocacheByUserId(@Param("userId")Long userId);

    @Select("select max(geocache_id) from t_geocache_info where pid = #{userId} and deleted = 0")
    int selectLatestGeocacheIdByUserId(@Param("userId")Long userId);

    @Update("update t_geocache_info set deleted = 1 where geocache_id = #{geocacheId}")
    int logicDeleteGeocache(@Param("geocacheId")Long geocacheId);

    @Select("select deleted from t_geocache_info where geocache_id = #{geocacheId}")
    int getDeleted(@Param("geocacheId")Long geocacheId);

    @Select("SELECT * FROM t_geocache_info order by reported desc LIMIT 0,10 where deleted = 0")
    List<Geocache> selectTopTenReportedGeocaches();

    @Update("update t_geocache_info set geocache_location_description = #{geocacheLocationDescription} where geocache_id = #{geocacheId}")
    void updateGeocacheById(@Param("geocacheId")Long geocacheId,@Param("geocacheLocationDescription")String geocacheLocationDescription);

    @Select("select sum(reported) from t_geocache_info where pid = #{pid} and deleted = 0")
    int selectReportedSum(@Param("pid")Long pid);

    @Update("update t_geocache_info set reported = reported+1 where geocache_id = #{geocacheId}")
    void updateGeocacheReportedById(@Param("geocacheId")Long geocacheId);


}
