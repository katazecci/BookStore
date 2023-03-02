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
import training.BookStore.domain.AppUser;
import training.BookStore.domain.AppUserRepository;

@SpringBootApplication
public class BookStoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstore(CategoryRepository catRepository, BookRepository bookRepository,
			AppUserRepository userRepository) {
		return (args) -> {

			log.info("create categories");
			catRepository.save(new Category("Thriller"));
			catRepository.save(new Category("Mystery"));
			catRepository.save(new Category("Comedy"));

			log.info("create books");
			bookRepository.save(new Book("Kasvoton kuolema", "Hennig Mankell", 2002, "1289129812", 12.0,
					catRepository.findByName("Thriller").get(0)));
			bookRepository.save(new Book("Naurattaa", "Pelle Hermanni", 2018, "43232333812", 18.0,
					catRepository.findByName("Comedy").get(0)));
			bookRepository.save(new Book("Riian verikoirat", "Hennig Mankell", 2003, "6748342222", 13.0,
					catRepository.findByName("Mystery").get(0)));

			// Create users: admin/admin user/user
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			log.info("fetch all books from the database");
			for (Book book : bookRepository.findAll()) {
				System.out.println("kirja: " + book.toString());
			}
		};
	}
}
