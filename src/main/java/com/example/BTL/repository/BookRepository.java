package com.example.BTL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BTL.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	public boolean existsByTitleAndAuthor(String title, String author);
}
