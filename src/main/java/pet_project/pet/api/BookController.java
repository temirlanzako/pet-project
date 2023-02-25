package pet_project.pet.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet_project.pet.dto.BookDto;
import pet_project.pet.model.Book;
import pet_project.pet.service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDto> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(value="{id}")
    public BookDto getBook(@PathVariable Long id) {

        return bookService.getOneBook(id);
    }
    @PostMapping
    public BookDto addBook(@RequestBody BookDto bookDto) {

        return bookService.addOneBook(bookDto);
    }
    @DeleteMapping(value = "{id}")
    public boolean deleteBook(@PathVariable Long id) {
         return bookService.deleteBook(id);
    }
    @PutMapping
    public BookDto updateBookDto(@RequestBody BookDto bookDto) {
        return bookService.updateBookDto(bookDto);
    }
}
