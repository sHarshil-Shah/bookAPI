package com.book.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.book.model.Book;
import com.book.repository.BookRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			List<Book> Books = new ArrayList<Book>();

			bookRepository.findAll().forEach(Books::add);

			if (Books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/search/{title}", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getByTitleContaining(@PathVariable String title) {
		List<Book> Books = new ArrayList<Book>();
		bookRepository.findByTitleContaining(title).forEach(Books::add);
		return new ResponseEntity<>(Books, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Book> getBookByID(@PathVariable("id") long id) {
		Optional<Book> BookData = bookRepository.findById(id);

		if (BookData.isPresent()) {
			return new ResponseEntity<>(BookData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/Book", method = RequestMethod.POST)
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		try {
			Book _book = bookRepository.save(book);
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@PutMapping("/Books/{id}")
//	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book Book) {
//		Optional<Book> BookData = BookRepository.findById(id);
//
//		if (BookData.isPresent()) {
//			Book _Book = BookData.get();
//			_Book.setTitle(Book.getTitle());
//			_Book.setDescription(Book.getDescription());
//			_Book.setPublished(Book.isPublished());
//			return new ResponseEntity<>(BookRepository.save(_Book), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

//	@DeleteMapping("/Books/{id}")
//	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
//		try {
//			BookRepository.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@DeleteMapping("/Books")
//	public ResponseEntity<HttpStatus> deleteAllBooks() {
//		try {
//			BookRepository.deleteAll();
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}

//	@GetMapping("/Books/published")
//	public ResponseEntity<List<Book>> findByPublished() {
//		try {
//			List<Book> Books = BookRepository.findByPublished(true);
//
//			if (Books.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(Books, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
