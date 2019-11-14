package com.blog.blogbakend.Service;
import com.blog.blogbakend.Repository.*;
import com.blog.blogbakend.modals.Comments;
import com.blog.blogbakend.modals.Users;
import com.blog.blogbakend.modals.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class commentService {
    @Autowired
    private userRepository userRepository;
    @Autowired
    private BlogRepository BlogRepository;
    @Autowired
    private CommentRepository CommentRepository;

    public Comments addcomment(Comments comments,int blogid, int  userid) {
        Users user = userRepository.findByUserid(userid);
        blog blog =BlogRepository.findByBlogid(blogid);
        comments.setUser(user);
        comments.setBlog(blog);
        return CommentRepository.save(comments);
    }

    public List<Comments> getList(){
        return CommentRepository.findAll();

    }

    public List<Comments> getbyblog(int id){
        blog b=BlogRepository.findByBlogid(id);
        return CommentRepository.findByBlog(b);
    }

    public List<Comments> deletecomment(int commentid)
    {
        Comments comment =CommentRepository.findById(commentid);
        blog b=comment.getBlog();
        CommentRepository.delete(comment);
        return CommentRepository.findByBlog(b);

    }

}
