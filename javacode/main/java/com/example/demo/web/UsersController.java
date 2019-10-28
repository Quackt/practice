package com.example.demo.web;

import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@Controller
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    @ResponseBody
    public List<User> getNameList(){
        return userService.getNameList();
    }


    @PostMapping(value="/{name}")
    @ResponseBody
    public String postName(@PathVariable String name) {
        return userService.postName(name);
    }

    @GetMapping(value="/{name}")
    @ResponseBody
    public User getName(@PathVariable String name) {
        return userService.getName(name);
    }

    @ResponseBody
    @PutMapping(value="/{lname}/{fname}")
    public String putName(@PathVariable String lname,@PathVariable String fname) {
        return userService.putName(lname,fname);
    }


    @DeleteMapping(value ="/{name}")
    @ResponseBody
    public String deleteName(@PathVariable String name){
        return userService.deleteName(name);
    }
}

