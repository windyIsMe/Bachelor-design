package com.example.demo.service;

import com.example.demo.dao.AnnounceDao;
import com.example.demo.domain.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnnounceService {
    @Autowired
    private AnnounceDao announceDao;

    public int addAnnounce(String content, String name) {
        System.out.println("AnnounceService get " + content + " and " + name);
        try {
            Date date = new Date();
            int affectedRows = announceDao.addAnnounce(content, name, date);
            System.out.println("return affectedRows is " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("AnnounceService Exception thrown: " + e);
            return -1;
        }
    }

    public List<Announce> getAnnounces() {
        System.out.println("getAnnounces enter AnnounceService");
        try {
            Date date = new Date();
            List<Announce> announceList = announceDao.getAnnounces();
            System.out.println("return announceList size is " + announceList.size());
            return announceList;
        }
        catch (NullPointerException e) {
            System.out.println("AnnounceService Exception thrown: " + e);
            return null;
        }
    }

}
