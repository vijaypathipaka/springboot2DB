package com.example.entity.book;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOK_DB")
public class Book {

	@Id
	private Integer bookId;
	private String bookName;
	private String bookAuthor;
}
