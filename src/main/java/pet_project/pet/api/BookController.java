package pet_project.pet.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet_project.pet.model.Book;
import pet_project.pet.service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping(value="{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
    @DeleteMapping(value = "{id}")
    public boolean deleteBook(@PathVariable Long id) {
         return bookService.deleteBook(id);
    }
    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
}
