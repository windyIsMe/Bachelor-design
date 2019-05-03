package com.example.demo.service;

import com.example.demo.dao.AppointDao;
import com.example.demo.domain.Appoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointService {
    @Autowired
    private AppointDao appointDao;

    public int addAppoint(int id, Date date) {
        System.out.println("AppointService get " + id + " and " + date);
        try {
            String name = appointDao.getName(id);
            Date sendDate = new Date();
            //sendDate.setTime(sendDate.getTime() + );
            System.out.println("sendDate is " + sendDate);
            System.out.println("date is " + date);
            int affectedRows = appointDao.addAppoint(id, sendDate, date, name);
            System.out.println("return affectedRows is " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("AppointService Exception thrown: " + e);
            return -1;
        }
    }

    public List<Appoint> getAppointsById(int examinerId) {
        System.out.println("AppointService get " + examinerId);
        try {
            List<Appoint> appointList = appointDao.getAppointsById(examinerId);
            System.out.println(appointList.size());
            for (Appoint appoint : appointList) {
                System.out.println("appointList " + appoint.getName());
            }
            return appointList;
        }
        catch (NullPointerException e) {
            System.out.println("AppointService Exception thrown: " + e);
            return null;
        }
    }

    public List<Appoint> getAppoints(int pageSize, int currentPage) {
        System.out.println("AppointService get");
        try {
            int start = (currentPage - 1) * pageSize;
            List<Appoint> dataList = appointDao.getAppoints(start, pageSize);
            System.out.println("AppointService return:");
            return dataList;
        }
        catch (NullPointerException e) {
            System.out.println("AppointService Exception thrown: " + e);
            return null;
        }
    }

    public int updateAppoint(int id, String result) {
        try {
            int affectedRows = appointDao.updateAppoint(id, result);
            System.out.println("AppointService return affectedRows: " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("AppointService Exception thrown: " + e);
            return -1;
        }
    }

    public int deleteAppoint(int id) {
        try {
            int affectedRows = appointDao.deleteAppoint(id);
            System.out.println("AppointService return affectedRows: " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("AppointService Exception thrown: " + e);
            return -1;
        }
    }
}
