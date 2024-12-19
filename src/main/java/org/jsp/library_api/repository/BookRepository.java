package org.jsp.library_api.repository;

import java.util.List;

import org.jsp.library_api.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findByName(String name);

	List<Book> findByPriceGreaterThanEqual(double price);

	List<Book> findBypublicationYearBetween(int min, int max);

}
