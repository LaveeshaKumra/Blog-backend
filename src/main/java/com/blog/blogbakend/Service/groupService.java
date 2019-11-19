package com.blog.blogbakend.Service;

import com.blog.blogbakend.Repository.*;
import com.blog.blogbakend.modals.Users;
import com.blog.blogbakend.modals.groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class groupService {

    @Autowired
    private  userRepository userRepository;

    @Autowired
    private  groupRepository groupRepository;

    @Autowired
    private userService userService;


    public groups creategroup(groups group, int  userid) {
        group.setCreator(userid);
        return groupRepository.save(group);
    }

    public List<groups> getListogGroups(){
        return groupRepository.findAll();
    }

    public List<groups> deletegroup(Principal principal, int id)
    {
        groups blog1=groupRepository.findByGroupid(id);
        groupRepository.delete(blog1);
        return getListofMyGroupsAsAdmin(principal);

    }

    public List <groups> getListofMyGroupsAsAdmin(Principal principal){
        List<groups> admin=groupRepository.findByCreator(userService.getUserId(principal));
        return admin;
    }



    public List <groups> getListofMyGroupsAsMember(Principal principal){
        List<groups> list =new ArrayList<>();
        int id=userService.getUserId(principal);
        List<groups> all=groupRepository.findAll();
        System.out.println(all.size());
        for(int i=0;i<all.size();i++){
            List mem=all.get(i).getMembers();
            if(mem.contains(id)){
                list.add(all.get(i));
            }
        }
        return list;
    }

    public List<groups> leavegroup(int gid , Principal principal){
        int id=userService.getUserId(principal);
        groups group=groupRepository.findByGroupid(gid);
        ArrayList mem=group.getMembers();
        mem.remove(Integer.valueOf(id));
        group.setMembers(mem);
        groupRepository.save(group);
        return getListofMyGroupsAsMember(principal);
    }
}
