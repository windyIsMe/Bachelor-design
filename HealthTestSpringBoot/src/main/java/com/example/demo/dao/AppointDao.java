package com.example.demo.dao;

import com.example.demo.domain.Appoint;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointDao {
    String getName(int id);

    int addAppoint(@Param("examinerId") int examinerId, @Param("sendDate") Date sendDate,
                   @Param("appointDate") Date appointDate, @Param("name") String name);

    List<Appoint> getAppointsById(int examinerId);

    List<Appoint> getAppoints(int start, int num);

    int updateAppoint(@Param("id") int id, @Param("result") String result);

    int deleteAppoint(@Param("id") int id);
}










