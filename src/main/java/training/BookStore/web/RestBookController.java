package training.BookStore.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import training.BookStore.domain.Book;
import training.BookStore.domain.BookRepository;

@RestController
public class RestBookController {

	private static final Logger log = LoggerFactory.getLogger(RestBookController.class);

	@Autowired
	private BookRepository bookRepository;

	// returns list of books
	@GetMapping("/books")
	public Iterable<Book> getBooks() {
		log.info("//fetch and return books");
		return bookRepository.findAll();
	}

	@GetMapping("/books/{id}")
	public Optional<Book> getBookById(@PathVariable Long id) {
		log.info("fetch book by id = " + id);
		return bookRepository.findById(id);
	}

	// add a new book
	@PostMapping("books")
	Book newBook(@RequestBody Book newBook) {
		log.info("save new book " + newBook);
		return bookRepository.save(newBook);
	}

	// edit existing book info
	@PutMapping("/books/{id}")
	Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
		log.info("edit book " + editedBook);
		editedBook.setId(id);
		return bookRepository.save(editedBook);
	}

	// delete book
	@DeleteMapping("/books/{id}")
	public Iterable<Book> deleteBook(@PathVariable Long id) {
		log.info("delete car, id = " + id);
		bookRepository.deleteById(id);
		return bookRepository.findAll();
	}

}