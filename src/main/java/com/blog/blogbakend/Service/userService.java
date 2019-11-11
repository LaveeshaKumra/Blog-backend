package com.blog.blogbakend.Service;


import com.blog.blogbakend.modals.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.blogbakend.Repository.userRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class userService {
    @Autowired
    private userRepository userRepository;
    public Optional<Users> CurrentUser(Principal prinicipal) {

        String username = prinicipal.getName();

        return userRepository.findByUsername(username);
    }
    public int getUserId(Principal principal)
    {
        String username = principal.getName();
        int  id = userRepository.findByUsername(username).get().getUserid();
        return id;
    }

    public Optional<Users> getUserProfile(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }



}




