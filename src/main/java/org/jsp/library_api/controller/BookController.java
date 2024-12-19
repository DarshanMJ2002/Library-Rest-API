package org.jsp.library_api.controller;

import java.util.List;

import org.jsp.library_api.dto.Book;
import org.jsp.library_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	@Autowired BookService bookService;
	
	//saving one book
	
	@PostMapping("/books")
	public ResponseEntity<Object> saveBook(@RequestBody Book book){
		return bookService.saveBook(book);
	}
	
	
	//Save Multiple Books
		@PostMapping("/books/many")
		public ResponseEntity<Object> saveBooks(@RequestBody List<Book> books){
			return bookService.saveBooks(books);
		}
		
		//Fetch All Book
		@GetMapping("/books")
		public ResponseEntity<Object> fetchAllBooks(){
			return bookService.fetchAllBooks();
		}
		
		//Fetch Book By Id
		@GetMapping("/books/{id}")
		public ResponseEntity<Object> fetchById(@PathVariable int id){
			return bookService.fetchById(id);
		}
		
		//Fetch Book By Name
		@GetMapping("/books/name/{name}")
		public ResponseEntity<Object> fetchByName(@PathVariable String name){
			return bookService.fetchByName(name);
		}
		
		//Fetch books Price Greater Than
		@GetMapping("/books/price/greater/{price}")
		public ResponseEntity<Object> fetchByPriceGreater(@PathVariable double price){
			return bookService.fetchByPriceGreater(price);
		}
		
		//Fetch Book By publicationYear Between
		@GetMapping("/books/publicationYear/{min}/{max}")
		public ResponseEntity<Object> fetchBypublicationYearBetween(@PathVariable int min,@PathVariable int max){
			return bookService.fetchBypublicationYearBetween(min,max);
		}
		
		//Delete Book By Id
		@DeleteMapping("/books/{id}")
		public ResponseEntity<Object> deleteById(@PathVariable int id){
			return bookService.deleteById(id);
		}
		
		//Update Book - PUT
		@PutMapping("/books")
		public ResponseEntity<Object> updateRecord(@RequestBody Book book){
			return bookService.updateBook(book);
		}
		
		//Update Book- PATCH
		@PatchMapping("/books/{id}")
		public ResponseEntity<Object> updateRecord(@PathVariable int id,@RequestBody Book book){
			return bookService.updateBook(id,book);
		}
	

}
