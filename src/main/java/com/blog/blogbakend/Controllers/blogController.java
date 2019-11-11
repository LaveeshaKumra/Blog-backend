package com.blog.blogbakend.Controllers;

import com.blog.blogbakend.Repository.BlogRepository;
import com.blog.blogbakend.Repository.userRepository;
import com.blog.blogbakend.Service.blogService;
import com.blog.blogbakend.Service.userService;
import com.blog.blogbakend.modals.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/myblogs")
public class blogController {
    @Autowired
    blogService blogService;
    @Autowired
    userService userService;

    @PostMapping(value="/addblog")
    @ResponseBody
    public blog addblog(@RequestBody blog blog,  Principal principal)
    {
        return blogService.addblog(blog,userService.getUserId(principal));
    }


    @RequestMapping(value="/showall",method= RequestMethod.GET)
    @ResponseBody
    public List<blog> showblogs()
    {

        return blogService.getBlogList();
    }


    @GetMapping(value="/getblogById/{blogid}")
    public List<blog> getBlogById(@PathVariable("blogid")int id){
        return blogService.getBlogById(id);
    }

    @GetMapping(value="/getBlogByCurrentUser")
    public List<blog> getBlogByCurrentUser(Principal principle){
        int id= userService.getUserId(principle);
        return blogService.getBlogByUserId(id);
    }

    @PutMapping("/update/{blogid}")
    public blog update(@Valid @RequestBody blog blog){
        blogService.update(blog);
        return blog;
    }



    @RequestMapping(value="/deleteblog/{blogid}",method= RequestMethod.GET)
    @ResponseBody
    public List<blog> deleteblog(@PathVariable("blogid") int blogid, Principal principal)
    {
        return blogService.deleteblog(userService.getUserId(principal),blogid);
    }
}
