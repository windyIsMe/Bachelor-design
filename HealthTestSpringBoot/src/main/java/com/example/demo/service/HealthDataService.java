package com.example.demo.service;

import com.example.demo.dao.HealthDataDao;
import com.example.demo.domain.HealthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthDataService {
    @Autowired
    private HealthDataDao healthDataDao;

    public List<HealthData> getDatas(int pageSize, int currentPage) {
        System.out.println("HealthDataService get");
        try {
            int start = (currentPage - 1) * pageSize;
            List<HealthData> dataList = healthDataDao.getDatas(start, pageSize);
            System.out.println("HealthDataService return:");
            return dataList;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataService Exception thrown: " + e);
            return null;
        }
    }

    public HealthData getDataById(int id) {
        try {
            HealthData healthData = healthDataDao.getDataById(id);
            System.out.println("HealthDataService return:");
            System.out.println(healthData.getName());
            return healthData;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataService Exception thrown: " + e);
            return null;
        }
    }

    public List<HealthData> getDatasByDepartAndName(String department, String name, int pageSize, int currentPage) {
        List<HealthData> dataList = null;
        int start = (currentPage - 1) * pageSize;
        try {
            if ((department == "" || department == null) && name == "") {
                dataList = healthDataDao.getDatas(start, pageSize);
            } else if (department == "" || department == null) {
                dataList = healthDataDao.getDatasByName(name, start, pageSize);
            } else if (name == "") {
                dataList = healthDataDao.getDatasByDepart(department, start, pageSize);
            } else {
                dataList = healthDataDao.getDatasByDepartAndName(department, name, start, pageSize);
            }
            System.out.println("HealthDataService return getDatasByDepartAndName:");
            return dataList;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataService Exception thrown: " + e);
            return null;
        }
    }

    public int updateDataById(HealthData healthData) {
        try {
            int affectedRows = healthDataDao.updateDataById(healthData);
            System.out.println("HealthDataService return affectedRows: " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataService Exception thrown: " + e);
            return -1;
        }
    }

    public int deleteDataById(int id) {
        try {
            int affectedRows = healthDataDao.deleteDataById(id);
            System.out.println("HealthDataService return affectedRows: " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataService Exception thrown: " + e);
            return -1;
        }
    }
}














