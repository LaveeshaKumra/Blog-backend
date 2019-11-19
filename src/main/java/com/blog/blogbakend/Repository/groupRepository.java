package com.blog.blogbakend.Repository;

import com.blog.blogbakend.modals.groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface groupRepository extends JpaRepository<groups, Integer> {
    groups findByGroupid(int id);
    List<groups> findByCreator(int id);
    //List<groups> findByGroupName(String s);
}
