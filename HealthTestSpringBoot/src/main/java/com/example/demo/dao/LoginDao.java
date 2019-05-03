package com.example.demo.dao;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {
    String getDoctorPassword(String username);

    User getUser(String actor, String username, String password);

    int updateExaminerPassword(@Param("id") int id, @Param("password") String password);

    int updateDoctorPassword(@Param("id") int id, @Param("password") String password);
}
