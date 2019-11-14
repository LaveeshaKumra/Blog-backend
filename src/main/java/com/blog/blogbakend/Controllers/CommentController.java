package com.blog.blogbakend.Controllers;

import com.blog.blogbakend.Service.blogService;
import com.blog.blogbakend.Service.commentService;
import com.blog.blogbakend.Service.userService;
import com.blog.blogbakend.modals.Comments;
import com.blog.blogbakend.modals.Users;
import com.blog.blogbakend.modals.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;



@CrossOrigin("*")
@RestController
@RequestMapping("/comments")
public class CommentController {


    @Autowired
    com.blog.blogbakend.Service.blogService blogService;
    @Autowired
    com.blog.blogbakend.Service.userService userService;
    @Autowired
    com.blog.blogbakend.Service.commentService commentService;

    @PostMapping(value="/addcomment/{blogid}")
    @ResponseBody
    public Comments addcomments(@Valid  @RequestBody Comments comment,@PathVariable("blogid")int id,  Principal principal)
    {
        return commentService.addcomment(comment,id,userService.getUserId(principal));
    }

    @RequestMapping(value="/showall",method= RequestMethod.GET)
    @ResponseBody
    public List<Comments> showblogs()
    {

        return commentService.getList();
    }

    @GetMapping(value="/getbyblog/{blogid}")
    public List<Comments> getCommentByBlog(@PathVariable("blogid")int id){
        return commentService.getbyblog(id);
    }

    @RequestMapping(value="/deletecomment/{commentid}",method= RequestMethod.GET)
    @ResponseBody
    public List<Comments> deletecomment(@PathVariable("commentid") int commentid)
    {
        return commentService.deletecomment(commentid);
    }
}
