package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Announce;
import com.example.demo.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnnouceController {
    @Autowired
    private AnnounceService announceService;

    @RequestMapping(value = "/addAnnounce", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int addAnnounce(@RequestBody JSONObject jsonParam) {
        String content = jsonParam.getString("content");
        String name = jsonParam.getString("name");
        System.out.println("AnnouceController get " + content + " and " + name);
        try {
            int affectedRows = announceService.addAnnounce(content, name);
            System.out.println("return affectedRows is " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("AppointController Exception thrown: " + e);
            return -1;
        }
    }

    @RequestMapping(value = "/getAnnounces", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Announce> getAnnounces() {
        System.out.println("getAnnounces enter AnnouceController");
        try {
            List<Announce> announceList = announceService.getAnnounces();
            return announceList;
        }
        catch (NullPointerException e) {
            System.out.println("AppointController Exception thrown: " + e);
            return null;
        }
    }
}
