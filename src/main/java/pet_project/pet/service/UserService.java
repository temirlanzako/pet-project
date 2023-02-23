package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet_project.pet.model.Book;
import pet_project.pet.model.User;
import pet_project.pet.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<Book> getUserBooks(Long id) {
        User user = getUser(id);
        List<Book> userBooks = user.getBookList();
        return userBooks;
    }

    public boolean addBookToUser(Long id, Long bookId) {
        List<Book> userBooks = getUserBooks(id);
        User user = getUser(id);
        Book book = bookService.getBook(bookId);
        if (userBooks == null) {
            List<Book> books = new ArrayList<>();
            books.add(book);
            user.setBookList(books);
        } else {
            userBooks.add(book);
            user.setBookList(userBooks);
        }
        userRepository.save(user);
        return true;
    }

    public boolean deleteUserBook(Long id, Long bookId) {
        List<Book> userBooks = getUserBooks(id);
        if (userBooks != null) {
            for (Book b : userBooks) {
                if (b.getId() == bookId) {
                    userBooks.remove(b);
                }
            }
            User user = getUser(id);
            user.setBookList(userBooks);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
