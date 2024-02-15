package com.example.w6d4t.repository;


import com.example.w6d4t.model.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AutoreRepository extends JpaRepository<Autore, Integer>, PagingAndSortingRepository<Autore, Integer> {
}
