package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet_project.pet.model.Book;
import pet_project.pet.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBooks() {

        return bookRepository.findAll();
    }
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    public boolean deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Book updateBook(Book book) {

        return bookRepository.save(book);
    }
}
