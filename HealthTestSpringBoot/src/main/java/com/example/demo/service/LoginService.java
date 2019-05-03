package com.example.demo.service;

import com.example.demo.dao.LoginDao;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public Boolean checkDoctorLogin(String username, String password) {
        System.out.println("LoginService get " + username + " and " + password);
        try {
            String mappedPassword = loginDao.getDoctorPassword(username);
            System.out.println("LoginService get password from mysql " + mappedPassword);
            return (password.equals(mappedPassword));
        }
        catch (NullPointerException e) {
            System.out.println("LoginService Exception thrown: " + e);
            return null;
        }
    }

    public User checkLogin(String actor, String username, String password) {
        System.out.println("LoginService get " + username + " and " + password);
        try {
            User user = loginDao.getUser(actor, username, password);
            if (user != null) {
                System.out.println("LoginService get id from mysql " + user.getUsername());
                return user;
            } else {
                System.out.println("LoginService cannot find this user");
                return null;
            }
        }
        catch (NullPointerException e) {
            System.out.println("LoginService Exception thrown: " + e);
            return null;
        }
    }

    public int updateExaminerPassword(int id, String password) {
        System.out.println("LoginService get " + id + " and " + password);
        try {
            int affectedRows = loginDao.updateExaminerPassword(id, password);
            System.out.println("LoginService get affectedRows " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("LoginService Exception thrown: " + e);
            return -1;
        }
    }

    public int updateDoctorPassword(int id, String password) {
        System.out.println("LoginService get " + id + " and " + password);
        try {
            int affectedRows = loginDao.updateDoctorPassword(id, password);
            System.out.println("LoginService get affectedRows " + affectedRows);
            return affectedRows;
        }
        catch (NullPointerException e) {
            System.out.println("LoginService Exception thrown: " + e);
            return -1;
        }
    }
}












