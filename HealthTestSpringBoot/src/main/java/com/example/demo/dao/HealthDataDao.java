package com.example.demo.dao;

import com.example.demo.domain.HealthData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthDataDao {
    List<HealthData> getDatas(int start, int num);

    HealthData getDataById(int id);

    List<HealthData> getDatasByDepart(@Param("department") String department, @Param("start") int start, @Param("num") int num);

    List<HealthData> getDatasByName(@Param("name") String name, @Param("start") int start, @Param("num") int num);

    List<HealthData> getDatasByDepartAndName(@Param("department") String department, @Param("name") String name,
                                             @Param("start") int start, @Param("num") int num);

    int updateDataById(HealthData healthData);

    int deleteDataById(int id);
}
