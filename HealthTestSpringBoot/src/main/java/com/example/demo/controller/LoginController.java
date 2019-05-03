package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/doctorLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean checkDoctorLogin(@RequestBody JSONObject jsonParam) {
        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");
        System.out.println("LoginController get " + username + " and " + password);
        try {
            Boolean result = loginService.checkDoctorLogin(username, password);
            System.out.println("LoginController return checkLogin is " + result);
            return result;
        }
        catch (NullPointerException e) {
            System.out.println("LoginController Exception thrown: " + e);
            return null;
        }
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User checkLogin(@RequestBody JSONObject jsonParam) {
        String actor = jsonParam.getString("actor");
        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");
        System.out.println("LoginController get " + actor + " and " + username + " and " + password);
        try {
            User user = loginService.checkLogin(actor, username, password);
            if (user != null) {
                System.out.println("LoginController return checkLogin is " + user);
                return user;
            } else {
                System.out.println("LoginController cannot find this user");
                return null;
            }
        }
        catch (NullPointerException e) {
            System.out.println("LoginController Exception thrown: " + e);
            return null;
        }
    }

    @RequestMapping(value = "/updateExaminerPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int updateExaminerPassword(@RequestBody JSONObject jsonParam) {
        int id = jsonParam.getInteger("id");
        String password = jsonParam.getString("password");
        System.out.println("LoginController get " + id + " and " + password);
        try {
            int result = loginService.updateExaminerPassword(id, password);
            System.out.println("LoginController return updateExaminerPassword is " + result);
            return result;
        }
        catch (NullPointerException e) {
            System.out.println("LoginController Exception thrown: " + e);
            return -1;
        }
    }

    @RequestMapping(value = "/updateDoctorPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int updateDoctorPassword(@RequestBody JSONObject jsonParam) {
        int id = jsonParam.getInteger("id");
        String password = jsonParam.getString("password");
        System.out.println("LoginController get " + id + " and " + password);
        try {
            int result = loginService.updateDoctorPassword(id, password);
            System.out.println("LoginController return updateDoctorPassword is " + result);
            return result;
        }
        catch (NullPointerException e) {
            System.out.println("LoginController Exception thrown: " + e);
            return -1;
        }
    }
}











