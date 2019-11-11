package com.blog.blogbakend.Service;

import com.blog.blogbakend.Repository.userRepository;
import com.blog.blogbakend.modals.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class currentUser {
    @Autowired
    private com.blog.blogbakend.Repository.userRepository userRepository;

    public Users getUserProfile(Principal principal) {
        Optional<Users> optional= userRepository.findByUsername(principal.getName());
        return optional.get();
    }

//    public Users follow( int userid, Principal principal){
//        Users user=userRepository.findByUserid(userid);
//        String name=principal.getName();
//        int  principalid = userRepository.findByUsername(name).get().getUserid();
//
//        ArrayList<Integer> arr=user.getFollowers();
//        arr.add(principalid);
//        user.setFollowers(arr);
//
//        ArrayList<Integer> arr2=userRepository.findByUsername(name).get().getFollowing();
//        arr.add(userid);
//        user.setFollowing(arr2);
//
//
//        Optional<Users> optional= userRepository.findByUsername(principal.getName());
//        return optional.get();
//    }


}

