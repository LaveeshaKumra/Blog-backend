package com.blog.blogbakend.Controllers;


import com.blog.blogbakend.Repository.userRepository;
import com.blog.blogbakend.Service.blogService;
import com.blog.blogbakend.Service.currentUser;
import com.blog.blogbakend.Service.userService;
import com.blog.blogbakend.modals.Users;
import com.blog.blogbakend.modals.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    currentUser currentuser;
    @Autowired
    userService userService;
    @Autowired
    userRepository userRepository;
    @Autowired
    com.blog.blogbakend.Service.blogService blogService;

    @GetMapping("/get")
    public Users getdata(Principal principal){

        return currentuser.getUserProfile(principal);
    }

    @PutMapping("/update")
    public Users update(@Valid @RequestBody Users users){
        users.setActive(1);
        userRepository.save(users);
        return users;
    }

    @GetMapping("/get/{id}")
    public Users getdata(@PathVariable("id") int id){
        Users user=userRepository.findByUserid(id);
        return user;
    }


    @GetMapping("/follow/{userid}")
    public Users follows(@PathVariable("userid") int userid, Principal principal){
        currentuser.follow(userid,principal);
        return currentuser.getUserProfile(principal);
    } 

    @GetMapping("/unfollow/{userid}")
    public Users unfollow(@PathVariable("userid") int userid, Principal principal){
        currentuser.unfollow(userid,principal);
        return currentuser.getUserProfile(principal);
    }

    @GetMapping("/deletefollowing/{userid}")
    public Users deletefollowing(@PathVariable("userid") int userid, Principal principal){
        currentuser.deletefollowing(userid,principal);
        return currentuser.getUserProfile(principal);
    }

    @GetMapping("/getblogsoffollowing")
    public List<blog> getblogs(Principal principal){
        return currentuser.getblogsoffollowing(principal);
    }


    @GetMapping("/getfollowers")
    public List<Users> getfollowers(Principal principal){
        return currentuser.getfollowers(principal);
    }

    @GetMapping("/getfollowing")
    public List<Users> getfollowing(Principal principal){
        return currentuser.getfollowing(principal);
    }


    @GetMapping(path = "search/{keyword}")
    public List<Users> getSearchResult(@PathVariable("keyword") String keyword) {
        return userService.searchResult(keyword);
    }
}
