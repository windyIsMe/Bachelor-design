package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Appoint;
import com.example.demo.service.AppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class AppointController {
    @Autowired
    private AppointService appointService;

    @RequestMapping(value = "/addAppoint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int addAppoint(@RequestBody JSONObject jsonParam) {
        int id = jsonParam.getInteger("id");
        Date date = jsonParam.getDate("date");
        System.out.println("AppointController get " + id + " and " + date);
        try {
            int affectedRows = appointService.addAppoint(id, date);
            System.out.println("return affectedRows is " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("AppointController Exception thrown: " + e);
            return -1;
        }
    }

    @RequestMapping(value = "/getAppointsById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Appoint> getAppointsById(@RequestBody JSONObject jsonParam) {
        int examinerId = jsonParam.getInteger("id");
        System.out.println("AppointController get " + examinerId);
        try {
            return appointService.getAppointsById(examinerId);
        }
        catch (NullPointerException e) {
            System.out.println("AppointController Exception thrown: " + e);
            return null;
        }
    }

    @RequestMapping(value = "/getAppoints", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Appoint> getDatas(@RequestBody JSONObject jsonParam) {
        int pageSize = jsonParam.getInteger("pageSize");
        int currentPage = jsonParam.getInteger("currentPage");
        System.out.println("AppointController get pageSize " + pageSize + " currentPage " + currentPage);
        try {
            List<Appoint> dataList = appointService.getAppoints(pageSize, currentPage);
            return dataList;
        }
        catch (NullPointerException e) {
            System.out.println("AppointController Exception thrown: " + e);
            return null;
        }
    }

    @RequestMapping(value = "/updateAppoint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int updateAppoint(@RequestBody JSONObject jsonParam) {
        int id = jsonParam.getInteger("id");
        String result = jsonParam.getString("result");
        System.out.println("AppointController get id " + id);
        try {
            int affectedRows = appointService.updateAppoint(id, result);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("AppointController Exception thrown: " + e);
            return -1;
        }
    }

    @RequestMapping(value = "/deleteAppoint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int deleteAppoint(@RequestBody JSONObject jsonParam) {
        int id = jsonParam.getInteger("id");
        System.out.println("AppointController get id " + id);
        try {
            int affectedRows = appointService.deleteAppoint(id);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("AppointController Exception thrown: " + e);
            return -1;
        }
    }
}
