package org.jsp.library_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.jsp.library_api.dto.Book;
import org.jsp.library_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	@Autowired
	BookRepository repository;

	public ResponseEntity<Object> saveBook(Book book) {
		repository.save(book);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Book Added Success");
		map.put("data", book);

		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> saveBooks(List<Book> books) {
		repository.saveAll(books);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Book Added Success");
		map.put("data", books);

		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> fetchAllBooks() {
		List<Book> list = repository.findAll();
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Books Found");

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Books Found");
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchById(int id) {
		Optional<Book> optional = repository.findById(id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Books Found with Id: "+id);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Books Found");
			map.put("data", optional.get());

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByName(String name) {
		List<Book> list = repository.findByName(name);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Books Found with Name :"+name);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Books Found");
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByPriceGreater(double price) {
		List<Book> list = repository.findByPriceGreaterThanEqual(price);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Books Found Price Greater Than: "+price);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Books Found");
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchBypublicationYearBetween(int min, int max) {
		List<Book> list = repository.findBypublicationYearBetween(min,max);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Books Found publicationYear Between: "+min+" and "+max);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Books Found");
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> deleteById(int id) {
		Optional<Book> optional = repository.findById(id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Book Found with Id: "+id);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			repository.deleteById(id);
			map.put("message", "Book Deleted Success");

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> updateBook(Book  book) {
		repository.save(book);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Book Updated Success");

		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	public ResponseEntity<Object> updateBook(int id, Book book) {
		Optional<Book> optional = repository.findById(id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Book Found with Id: "+id);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			
			Book existingBook = optional.get();
			if(book.getName()!=null)
				existingBook.setName(book.getName());
			if(book.getAuthour()!=null)
				existingBook.setAuthour(book.getAuthour());
			if(book.getPrice()!=0)
				existingBook.setPrice(book.getPrice());
			if(book.getPublicationYear()!=0)
				existingBook.setPublicationYear(book.getPublicationYear());
			
			repository.save(existingBook);
			
			
			map.put("message", "Book Updated Success");

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

}
