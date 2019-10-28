package com.example.demo.web;


import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public List<User> getNameList(){
        System.out.println("getNamelist"+userRepository.findAll());
        return userRepository.findAll();
    }


    public String postName(String name) {
        System.out.println("postName:"+name);
        userRepository.save(new User(name));
        return "success";
    }

    public User getName(String name) {
        System.out.println("getName:"+name);
        return userRepository.findByName(name);
    }

    public String putName(String lname,String fname) {
        System.out.println("putName:"+lname+fname);
        userRepository.updateNameById(lname,fname);
        return "success";
    }

    public String deleteName(String name){
        userRepository.delete(userRepository.findByName(name));
        System.out.println("deleteName:"+name);
        return "success";
    }
}
