package training.BookStore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import training.BookStore.domain.Book;
import training.BookStore.domain.BookRepository;
import training.BookStore.domain.CategoryRepository;

@Controller
public class BookController {
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository catRepository;

	// login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Show all books
	@RequestMapping(value = { "/bookList", "/" })
	public String showBooklist(Model model) {
		log.info("get books from db");
		model.addAttribute("books", bookRepository.findAll());
		return "bookList";
	}

	// Add a new book
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addBook")
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", catRepository.findAll());
		return "newBook";
	}

	// Save new book
	@PostMapping("saveBook")
	public String saveBook(@Validated Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some validation error happened");
			return "/newBook";
		}
		bookRepository.save(book);
		return "redirect:/bookList";
	}

	// Delete a book
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookRepository.deleteById(id);
		return "redirect:/bookList";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookRepository.findById(id));
		model.addAttribute("categories", catRepository.findAll());
		return "editBook";
	}

}