package com.blog.blogbakend.Service;

import com.blog.blogbakend.Repository.*;
import com.blog.blogbakend.modals.Users;
import com.blog.blogbakend.modals.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class currentUser {
    @Autowired
    private userRepository userRepository;
    @Autowired
    private BlogRepository BlogRepository;
    @Autowired
    private blogService blogService;

    public Users getUserProfile(Principal principal) {
        Optional<Users> optional= userRepository.findByUsername(principal.getName());
        return optional.get();
    }

    public  Users follow(int userid, Principal principal){
        Users user=userRepository.findByUserid(userid);
        String name=principal.getName();
        int  principalid = userRepository.findByUsername(name).get().getUserid();
        Users currentuser=userRepository.findByUserid(principalid);
        ArrayList<Integer> arr=user.getFollowers();
        arr.add(principalid);
        user.setFollowers(arr);
        ArrayList<Integer> arr2=currentuser.getFollowing();
        arr2.add(userid);
        currentuser.setFollowing(arr2);
        userRepository.save(user);
        userRepository.save(currentuser);
        Optional<Users> optional= userRepository.findByUsername(principal.getName());
        return optional.get();
    }

    public Users unfollow (int userid, Principal principal){
        Users user=userRepository.findByUserid(userid);
        String name=principal.getName();
        int  principalid = userRepository.findByUsername(name).get().getUserid();
        Users currentuser=userRepository.findByUserid(principalid);
        ArrayList<Integer> arr=user.getFollowers();
        arr.remove(Integer.valueOf(principalid));
        user.setFollowers(arr);
        ArrayList<Integer> arr2=currentuser.getFollowing();
        arr2.remove(Integer.valueOf(userid));
        currentuser.setFollowing(arr2);
        userRepository.save(user);
        userRepository.save(currentuser);
        Optional<Users> optional= userRepository.findByUsername(principal.getName());
        return optional.get();
    }

    public Users deletefollowing (int userid, Principal principal){
        Users user=userRepository.findByUserid(userid);
        String name=principal.getName();
        int  principalid = userRepository.findByUsername(name).get().getUserid();

        Users currentuser=userRepository.findByUserid(principalid);
        ArrayList<Integer> arr=currentuser.getFollowing();
        arr.remove(Integer.valueOf(userid));
        currentuser.setFollowing(arr);
        ArrayList<Integer> arr2=user.getFollowers();
        arr2.remove(Integer.valueOf(principalid));
        user.setFollowers(arr2);
        userRepository.save(user);
        userRepository.save(currentuser);
        Optional<Users> optional= userRepository.findByUsername(principal.getName());
        return optional.get();
    }

    public List<blog> getblogsoffollowing(Principal principal){
        String name=principal.getName();
        int  principalid = userRepository.findByUsername(name).get().getUserid();
        Users currentuser=userRepository.findByUserid(principalid);
        ArrayList<Integer> arr=currentuser.getFollowing();
        ArrayList<blog> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            Users user=userRepository.findByUserid(arr.get(i));
            List<blog> blog=BlogRepository.findByUser(user);
            list.addAll(blog);
        }
        List<blog> publicblogs= blogService.getpublicBlogs();
        list.addAll(publicblogs);
        return list;
    }


    public List<Users> getfollowers(Principal principal){
        String name=principal.getName();
        int  principalid = userRepository.findByUsername(name).get().getUserid();
        Users currentuser=userRepository.findByUserid(principalid);
        ArrayList<Integer> arr=currentuser.getFollowers();
        List<Users> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            Users user=userRepository.findByUserid(arr.get(i));
            list.add(user);
        }
        return list;
    }

    public List<Users> getfollowing(Principal principal){
        String name=principal.getName();
        int  principalid = userRepository.findByUsername(name).get().getUserid();
        Users currentuser=userRepository.findByUserid(principalid);
        ArrayList<Integer> arr=currentuser.getFollowing();
        List<Users> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            Users user=userRepository.findByUserid(arr.get(i));
            list.add(user);
        }
        return list;
    }


}
