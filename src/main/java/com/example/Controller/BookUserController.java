package com.example.Controller;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repo.book.BookRepo;
import com.example.Repo.user.UserRepo;
import com.example.entity.book.Book;
import com.example.entity.user.User;

@RestController
@ComponentScan(basePackages = "com.example.Repo.*")
public class BookUserController {

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private UserRepo userRepo;

	@PostMapping(path = "/add")
	public void addData2DB() {

		userRepo.saveAll(
				Stream.of(new User(1, "Vijay", "hyd"), new User(2, "sai", "hyd")).collect(Collectors.toList()));
		bookRepo.saveAll(Stream.of(new Book(101, "God", "peter"), new Book(201, "finding life", "Jhon"))
				.collect(Collectors.toList()));

	}

	@GetMapping(path = "/getUsers")
	public Iterable<User> getUser() {
		return userRepo.findAll();
	}

	@GetMapping(path = "/getBooks")
	public Iterable<Book> getbook() {
		return bookRepo.findAll();
	}

}
