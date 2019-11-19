package com.blog.blogbakend.Controllers;

import com.blog.blogbakend.Service.*;
import com.blog.blogbakend.modals.groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/group")
public class groupController {
    @Autowired
    userService userService;

    @Autowired
    groupService groupService;

    @PostMapping(value="/creategroup")
    @ResponseBody
    public groups creategroup(@RequestBody groups group, Principal principal)
    {
        return groupService.creategroup(group,userService.getUserId(principal));
    }

    @RequestMapping(value="/showall",method= RequestMethod.GET)
    @ResponseBody
    public List<groups> showall()
    {
        return groupService.getListogGroups();
    }

    @RequestMapping(value="/deletegroup/{gid}",method= RequestMethod.GET)
    @ResponseBody
    public List<groups> deleteblog(@PathVariable("gid") int gid, Principal principal)
    {
        return groupService.deletegroup(principal,gid);
    }

    @RequestMapping(value="/mygroupsAsAdmin",method= RequestMethod.GET)
    @ResponseBody
    public List<groups> showmygroups(Principal principal)
    {
        return groupService.getListofMyGroupsAsAdmin(principal);
    }

    @RequestMapping(value="/mygroupsAsMember",method= RequestMethod.GET)
    @ResponseBody
    public List<groups> showmygroupsAsMember(Principal principal)
    {
        return groupService.getListofMyGroupsAsMember(principal);
    }

    @RequestMapping(value="/leavegroup/{gid}",method= RequestMethod.GET)
    @ResponseBody
    public List<groups> leavegroup(@PathVariable("gid") int gid,Principal principal)
    {
        return groupService.leavegroup(gid,principal);
    }
}
