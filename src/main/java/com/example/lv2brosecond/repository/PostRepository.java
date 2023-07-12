package com.example.lv2brosecond.repository;

import com.example.lv2brosecond.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
