package com.example.w6d4t.repository;


import com.example.w6d4t.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>, PagingAndSortingRepository<BlogPost, Integer> {
}
