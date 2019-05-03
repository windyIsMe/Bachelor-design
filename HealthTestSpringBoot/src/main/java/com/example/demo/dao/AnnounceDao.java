package com.example.demo.dao;

import com.example.demo.domain.Announce;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AnnounceDao {
    int addAnnounce(@Param("content") String content, @Param("name") String name, @Param("date") Date date);

    List<Announce> getAnnounces();
}










