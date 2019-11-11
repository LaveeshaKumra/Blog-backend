package com.blog.blogbakend.Controllers;


import com.blog.blogbakend.modals.Users;
import com.blog.blogbakend.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class UserLoginController {
    @Autowired
    userRepository userRepository;

    @GetMapping("/validateUser")
    public String validateuser()
    {
        return "\"user validated\"";
    }
    @PostMapping(path="/addUsers", produces="application/json")
    public Users addusers(@Valid @RequestBody Users user )
    {
        return userRepository.save(user);
    }
    @GetMapping("/getUsers")
    public List<Users> getusers()
    {
        return userRepository.findAll();
    }


}
