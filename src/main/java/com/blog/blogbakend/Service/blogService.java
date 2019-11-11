package com.blog.blogbakend.Service;
import com.blog.blogbakend.Repository.userRepository;
import com.blog.blogbakend.Repository.BlogRepository;
import com.blog.blogbakend.modals.Users;
import com.blog.blogbakend.modals.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class blogService {
    @Autowired
    private BlogRepository BlogRepository;


    @Autowired
    private userRepository userRepository;


    public blog addblog(blog blog,int  userid) {
        Users user = userRepository.findByUserid(userid);
        blog.setUser(user);
        return BlogRepository.save(blog);
    }



    public List<blog> getBlogList(){
        return BlogRepository.findAll();

    }

    public List<blog> getBlogById(int id){
        return BlogRepository.findById(id);
    }

    public List<blog> getBlogByUserId(int id){
        Users u=userRepository.findByUserid(id);
        return BlogRepository.findByUser(u);
    }

    public blog update(blog b){
        int blogid= b.getId();
        blog blog=BlogRepository.findByBlogid(blogid);
        blog.setBody(b.getBody());
        blog.setTitle(b.getTitle());
        blog.setCreateDate(b.getCreateDate());
        return BlogRepository.save(blog);
    }

    public List<blog> deleteblog(int userid, int blogid)
    {
        blog blog1=BlogRepository.findByBlogid(blogid);
        Users users=userRepository.findByUserid(userid);
        BlogRepository.delete(blog1);
        return BlogRepository.findByUser(users);

    }
}
