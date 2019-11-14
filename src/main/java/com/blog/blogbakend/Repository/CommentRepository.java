package com.blog.blogbakend.Repository;


import com.blog.blogbakend.modals.Comments;
import com.blog.blogbakend.modals.blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comments, Integer> {
    Comments findById(int commentid);
    List<Comments> findByBlog(blog blog);
}
