package com.book.model;

import javax.persistence.*;

@Entity
public class Book {

	@Id
	@Column
	private long bookID;

	@Column(length = 100000)
	private String title;
	@Column(length = 100000)
	private String authors;
	@Column(length = 100000)
	private String average_rating;
	@Column(length = 100000)
	private String isbn;
	@Column(length = 100000)
	private String language_code;
	@Column(length = 100000)
	private String ratings_count;
	@Column(length = 100000)
	private String price;

	public Book(long bookID, String title, String authors, String average_rating, String isbn, String language_code,
			String ratings_count, String price) {
		this.bookID = bookID;
		this.title = title;
		this.authors = authors;
		this.average_rating = average_rating;
		this.isbn = isbn;
		this.language_code = language_code;
		this.ratings_count = ratings_count;
		this.price = price;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public long getBookID() {
		return bookID;
	}

	public void setBookID(long bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(String average_rating) {
		this.average_rating = average_rating;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLanguage_code() {
		return language_code;
	}

	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}

	public String getRatings_count() {
		return ratings_count;
	}

	public void setRatings_count(String ratings_count) {
		this.ratings_count = ratings_count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "{\r\n" + "        \"bookID\": " + bookID + ",\r\n" + "        \"title\": " + title + "\",\r\n"
				+ "        \"authors\": " + authors + "\",\r\n" + "        \"average_rating\": " + average_rating
				+ ",\r\n" + "        \"isbn\": " + isbn + ",\r\n" + "        \"language_code\": " + language_code
				+ "\",\r\n" + "        \"ratings_count\": " + ratings_count + ",\r\n" + "        \"price\": " + price
				+ "\r\n" + "    }\n";
	}

}