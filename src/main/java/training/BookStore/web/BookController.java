package training.BookStore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import training.BookStore.domain.BookRepository;

@Controller
public class BookController {
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = { "/main" })
	public String showMainPage() {
		return "index";
	}

	@RequestMapping(value = { "/bookList", "/" })
	public String showBooklist(Model model) {
		log.info("get books from db");
		model.addAttribute("books", bookRepository.findAll());
		return "bookList";
	}
}