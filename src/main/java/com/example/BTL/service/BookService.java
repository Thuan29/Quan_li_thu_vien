package com.example.BTL.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.BTL.entity.Book;
import com.example.BTL.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	
	private BookRepository bRepo;
	
	public void save(Book b, MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("Khong co gia tri file");
		}
		try {
			b.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		}catch(IOException e){
			e.printStackTrace();
		}
		bRepo.save(b);
	}
	
	public List<Book> getAllBooks(){
		return bRepo.findAll();
	}
	
	public void deleteById(int id) {
		bRepo.deleteById(id);
	}
	
	public Book getBookById(int id) {
		return bRepo.findById(id).get();
	}
	
	
	public boolean checkTitleAndAuthor(String title, String author) {
		return bRepo.existsByTitleAndAuthor(title, author);
	} 

}
