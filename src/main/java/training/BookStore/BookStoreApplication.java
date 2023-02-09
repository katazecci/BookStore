package training.BookStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import training.BookStore.domain.Book;
import training.BookStore.domain.BookRepository;
import training.BookStore.domain.Category;
import training.BookStore.domain.CategoryRepository;

@SpringBootApplication
public class BookStoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstore(CategoryRepository catRepository, BookRepository bookRepository) {
		return (args) -> {

			log.info("create categories");
			catRepository.save(new Category("Thriller"));
			catRepository.save(new Category("Mystery"));

			log.info("create books");
			bookRepository.save(new Book("Kasvoton kuolema", "Hennig Mankell", 2002, "1289129812", 12.0,
					catRepository.findByName("Thriller").get(0)));
			bookRepository.save(new Book("Riian verikoirat", "Hennig Mankell", 2003, "6748342222", 13.0,
					catRepository.findByName("Mystery").get(0)));

			log.info("fetch all books from the database");
			for (Book book : bookRepository.findAll()) {
				System.out.println("kirja: " + book.toString());
			}
		};
	}
}
