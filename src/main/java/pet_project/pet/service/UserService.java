package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet_project.pet.dto.BookDto;
import pet_project.pet.dto.PermissionDto;
import pet_project.pet.dto.UserDto;
import pet_project.pet.mapper.PermissionMapper;
import pet_project.pet.mapper.UserMapper;
import pet_project.pet.model.Book;
import pet_project.pet.model.Permission;
import pet_project.pet.model.User;
import pet_project.pet.repository.PermissionRepository;
import pet_project.pet.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;

    private final UserMapper userMapper;
    private final PermissionMapper permissionMapper;
    private final PermissionRepository permissionRepository;

    public void updateUser(User user) {
        userRepository.save(user);
    }


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
        User user = getUser(id);
        if (user == null) {
            System.err.println("User is null");
        }
        List<Book> userBooks = user.getBookList();
        if (userBooks == null) {
            System.err.println("User doesnt have any books");
        }
        return userBooks;
    }

    public List<BookDto> getUserBooksDto(Long id) {
        UserDto userDto = getUserDto(id);
        if (userDto == null) {
            System.err.println("UserDto is null");
        }
        List<BookDto> bookDtoList = userDto.getBookList();
        if (bookDtoList == null) {
            System.err.println("UserDto doesnt have any booksDto");
        }
        return bookDtoList;
    } //DTO

    public boolean addBookToUser(Long id, Long bookId) {
        List<Book> userBooks = getUserBooks(id);
        User user = getUser(id);
        if (user != null) {
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
        return false;
    }

    public boolean addBookToUserDto(Long id, Long bookId) {
        List<BookDto> bookDtoList = getUserBooksDto(id);
        UserDto userDto = getUserDto(id);
        if (userDto != null) {
            BookDto bookDto = bookService.getOneBook(bookId);
            if (bookDtoList == null) {
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
        return false;
    }

    public void deleteUserBook(Long id, Long bookId) {
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
        }
        System.err.println("NO SUCH BOOK OR USER");
    }

    // METHOD ADD ROLE TO USER IS NOT WORKING....
    public boolean addRoleToUser(Long id, Long roleId) {
        UserDto userDto = userMapper.toDto(userRepository.findById(id).orElse(null));
        PermissionDto permissionDto = permissionMapper.toDto(permissionRepository.findById(roleId).orElse(null));
        if (permissionDto == null) {
            permissionDto.setName("ROLE_ADMIN");
            permissionRepository.save(permissionMapper.toEntity(permissionDto));
        }
        if (userDto != null) {
            List<PermissionDto> permissionDtoList = userDto.getPermissionDtoList();
            if (permissionDtoList == null) {
                List<PermissionDto> list = new ArrayList<>();
                list.add(permissionDto);
                userDto.setPermissionDtoList(list);
            } else {
                permissionDtoList.add(permissionDto);
                userDto.setPermissionDtoList(permissionDtoList);
            }
            userDto.setNameDto(userDto.getNameDto());
            userDto.setBookList(userDto.getBookList());
            userDto.setSurname(userDto.getSurname());
            userRepository.save(userMapper.toEntity(userDto));
            return true;
        }
        return false;
    }
}
