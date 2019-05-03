package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.HealthData;
import com.example.demo.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HealthDataController {

    @Autowired
    private HealthDataService healthDataService;

    @RequestMapping(value = "/getDatas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HealthData> getDatas(@RequestBody JSONObject jsonParam) {
        int pageSize = jsonParam.getInteger("pageSize");
        int currentPage = jsonParam.getInteger("currentPage");
        System.out.println("HealthDataController get pageSize " + pageSize);
        try {
            List<HealthData> dataList = healthDataService.getDatas(pageSize, currentPage);
            return dataList;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataController Exception thrown: " + e);
            return null;
        }
    }

    @RequestMapping(value = "/getDataById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HealthData getDataById(@RequestBody JSONObject jsonParam) {
        int id = jsonParam.getInteger("id");
        System.out.println("HealthDataController get id " + id);
        try {
            HealthData data = healthDataService.getDataById(id);
            return data;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataController Exception thrown: " + e);
            return null;
        }
    }

    @RequestMapping(value = "/getDatasByDepartAndName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HealthData> getDatasByDepartAndName(@RequestBody JSONObject jsonParam) {
        String department = jsonParam.getString("department");
        String name = jsonParam.getString("name");
        int pageSize = jsonParam.getInteger("pageSize");
        int currentPage = jsonParam.getInteger("currentPage");
        System.out.println("HealthDataController get department " + department + " name " + name + " pageSize " + pageSize
            + " currentPage " + currentPage);
        try {
            List<HealthData> dataList = healthDataService.getDatasByDepartAndName(department, name, pageSize, currentPage);
            return dataList;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataController Exception thrown: " + e);
            return null;
        }
    }

    @RequestMapping(value = "/updateDataById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int updateDataById(@RequestBody JSONObject jsonParam) {
        JSONObject data = jsonParam.getJSONObject("form");
        System.out.println("HealthDataController get jsonData " + data);
        HealthData healthData = new HealthData(data.getInteger("id"), data.getString("name"), data.getString("sex"),
                data.getString("age"), data.getString("birthdate"), data.getString("department"),
                data.getString("place"), data.getString("colorVision"), data.getString("rightSight"),
                data.getString("leftSight"), data.getString("nose"), data.getString("throat"),
                data.getString("height"), data.getString("highPressure"), data.getString("lowPressure"),
                data.getString("weight"), data.getString("liver"), data.getString("thyroid"),
                data.getString("bladder"), data.getString("dr"), data.getString("electrocardiogram"),
                data.getString("bone"), data.getString("eb"), data.getString("alb"),
                data.getString("whiteCell"), data.getString("redCell"));
        try {
            int affectedRows = healthDataService.updateDataById(healthData);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataController Exception thrown: " + e);
            return -1;
        }
    }

    @RequestMapping(value = "/deleteDataById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int deleteDataById(@RequestBody JSONObject jsonParam) {
        int id = jsonParam.getInteger("id");
        System.out.println("HealthDataController get id " + id);
        try {
            int affectedRows = healthDataService.deleteDataById(id);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("HealthDataController Exception thrown: " + e);
            return -1;
        }
    }
}

