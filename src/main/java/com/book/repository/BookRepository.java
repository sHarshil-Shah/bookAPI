package com.book.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByBookID(Long bookID);

	List<Book> findByTitleContaining(String title);

}
