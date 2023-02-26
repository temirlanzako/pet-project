package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet_project.pet.dto.BookDto;
import pet_project.pet.dto.UserDto;
import pet_project.pet.mapper.UserMapper;
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

    private final UserMapper userMapper;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<UserDto> getAllUsersDto() {
        List<User> userList = getAllUsers();
        return userMapper.toDtoList(userList);
    } //DTO

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserDto getUserDto(Long id) {
        return userMapper.toDto(getUser(id));
    } //DTO

    public List<Book> getUserBooks(Long id) {
        if(id == null) {
            System.err.println("id == null");
        }
        User user = getUser(id);
        List<Book> userBooks = user.getBookList();
        if(userBooks == null) {
            System.err.println("User doesnt have any books");
        }
        return userBooks;
    }
    public  List<BookDto> getUserBooksDto(Long id) {
        UserDto userDto = getUserDto(id);
        List<BookDto> bookDtoList = userDto.getBookList();
        if(bookDtoList == null) {
            System.err.println("User doesnt have any books");
        }
        return bookDtoList;
    } //DTO
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
        if(user != null) {
            userRepository.save(user);
        } else {
            System.err.println("In method addBookToUser, USER IS NULL");
        }
        return true;
    }
    public boolean addBookToUserDto(Long id, Long bookId) {
        List<BookDto> bookDtoList = getUserBooksDto(id);
        UserDto userDto = getUserDto(id);
        BookDto bookDto = bookService.getOneBook(bookId);
        if(bookDtoList == null) {
            List<BookDto> booksDto = new ArrayList<>();
            booksDto.add(bookDto);
            userDto.setBookList(booksDto);
        } else {
            bookDtoList.add(bookDto);
            userDto.setBookList(bookDtoList);
        }
        userRepository.save(userMapper.toEntity(userDto));
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
