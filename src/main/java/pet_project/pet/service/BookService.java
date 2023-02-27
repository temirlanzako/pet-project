package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pet_project.pet.dto.BookDto;
import pet_project.pet.dto.CatalogDto;
import pet_project.pet.mapper.BookMapper;
import pet_project.pet.model.Book;
import pet_project.pet.repository.BookRepository;
import pet_project.pet.repository.CatalogRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final CatalogRepository catalogRepository;


    private final BookMapper bookMapper;

    public List<Book> getBooks() {

        return bookRepository.findAll();
    }

    public List<BookDto> getAllBooks() {
        return bookMapper.toDtoList(getBooks());
    } // DTO

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public BookDto getOneBook(Long id) {
        return bookMapper.toDto(getBook(id));
    } // DTO

    public Book addBook(Book book) {

        return bookRepository.save(book);
    }

    public BookDto addOneBook(BookDto bookDto) {
        return bookMapper.toDto(addBook(bookMapper.toEntity(bookDto)));
    } // DTO

    public boolean deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

  /*  public boolean deleteBookDto(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            BookDto bookDto = bookMapper.toDto(book);
        }

    }*/

    public Book updateBook(Book book) {

        return bookRepository.save(book);
    }

    public BookDto updateBookDto(BookDto bookDto) {
        return bookMapper.toDto(updateBook(bookMapper.toEntity(bookDto)));
    } // DTO
}
