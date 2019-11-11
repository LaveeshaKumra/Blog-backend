package com.blog.blogbakend.Repository;

import com.blog.blogbakend.modals.Users;
import com.blog.blogbakend.modals.blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<blog, Integer> {

    List<blog>  findById(int blogid);
    List<blog> findByUser(Users user);
    blog findByBlogid(int blogid);
    //blog findByUserAndBlogid(Users users, blog blog);

}
