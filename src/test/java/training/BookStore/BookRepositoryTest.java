package training.BookStore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import training.BookStore.domain.Book;
import training.BookStore.domain.BookRepository;
import training.BookStore.domain.Category;

@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void findByAuthorShouldReturnTitle() {
		List<Book> books = bookRepository.findByAuthor("Pelle Hermanni");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Naurattaa");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("Halleluja", "Jeesus Kristus", 2002, "321231232", 19.0, new Category("Religious"));
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void updateBook() {
		Optional<Book> book = bookRepository.findById((long) 1);
		assertNotEquals(book.get().getId(), null);
		book.get().setAuthor("testi");
		List<Book> books = bookRepository.findByAuthor("testi");
		assertThat(books).hasSize(1);

	}

	@Test
	public void deleteNewBook() {
		List<Book> books = bookRepository.findByAuthor("Pelle Hermanni");
		Book book = books.get(0);
		bookRepository.delete(book);
		List<Book> newBooks = bookRepository.findByAuthor("Pelle Hermanni");
		assertThat(newBooks).hasSize(0);
	}

}
