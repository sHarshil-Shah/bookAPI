package com.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.book.controller.BookController;
import com.book.model.Book;
import com.book.repository.BookRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(DemoApplication.class, args);

	}

	// populating data into H2 database from given URL
	@Bean
	CommandLineRunner runner(BookController bookController) {
		return args -> {
			System.out.println("Fetching data from " + GetBooks.GET_URL);
			List<Book> books = GetBooks.sendGET();

			for (Book book : books)
				bookController.createBook(book);
			System.out.println("Populated h2 database using " + GetBooks.GET_URL);

		};
	}

}

class GetBooks {

	private static final String USER_AGENT = "Mozilla/5.0";

	static final String GET_URL = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json";

	static List<Book> sendGET() throws IOException, ParseException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine + "\n");
			}
			in.close();

			// return array list of books from JSON
			return jsonToArrayList(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

		return null;
	}

	// my function to convert JSON string to Array List - Did not use other library
	// to reduce dependencies
	private static List<Book> jsonToArrayList(String json) throws ParseException {
		List<Book> books = new ArrayList<>();
		Book t = null;
		String[] inputLines = json.split("\n");
		for (String inputLine : inputLines) {
			if (inputLine.contains("[") || inputLine.contains("]"))
				continue;
			else if (inputLine.contains("{")) {
				t = new Book();
			} else if (inputLine.contains("bookID")) {
				t.setBookID(Long.parseLong((String) getVal(inputLine)));
			} else if (inputLine.contains("title")) {
				t.setTitle((String) getVal(inputLine));
			} else if (inputLine.contains("authors")) {
				t.setAuthors((String) getVal(inputLine));
			} else if (inputLine.contains("average_rating")) {
				t.setAverage_rating((String) getVal(inputLine));
			} else if (inputLine.contains("isbn")) {
				t.setIsbn((String) getVal(inputLine));
			} else if (inputLine.contains("language_code")) {
				t.setLanguage_code((String) getVal(inputLine));
			} else if (inputLine.contains("ratings_count")) {
				t.setRatings_count((String) getVal(inputLine));
			} else if (inputLine.contains("price")) {
				t.setPrice((String) getVal(inputLine));
			} else if (inputLine.contains("}")) {
				books.add(t);
			}
		}
		return books;
	}

	// "key"="value" -> returns value from the json line in mentioned format
	private static Object getVal(String in) {
		in = in.split(":", 2)[1].trim();
		if (in.charAt(0) == '"') {
			in = in.substring(1, in.length());
		}
		if (in.charAt(in.length() - 1) == ',') {
			in = in.substring(0, in.length() - 1);
		}
		if (in.charAt(in.length() - 1) == '"') {
			in = in.substring(0, in.length() - 1);
		}
		return in;
	}
}
