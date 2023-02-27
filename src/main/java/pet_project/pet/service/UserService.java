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

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;

    private final UserMapper userMapper;
    private final PermissionMapper permissionMapper;
    private final PermissionRepository permissionRepository;


    public void getUserPermissionList() {
        User user = userRepository.findById(1l).orElse(null);
        List<Permission> permissionList = user.getPermissionList();
        for(Permission per : permissionList) {
            System.out.println(per.getName());
        }

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

    // METHOD ADD ROLE TO USER IS NOT WORKING....
    public void addRoleToUser(Long id, Long roleId) {
        UserDto userDto = userMapper.toDto(userRepository.findById(id).orElse(null));
        PermissionDto permissionDto = permissionMapper.toDto(permissionRepository.findById(roleId).orElse(null));
        if(permissionDto==null) {
            permissionDto.setName("ROLE_ADMIN");
            permissionRepository.save(permissionMapper.toEntity(permissionDto));
        }
        if(userDto != null) {
            List<PermissionDto> permissionDtoList = permissionMapper.toDtoList(permissionRepository.findAll());
            if(permissionDtoList == null) {
                List<PermissionDto> permissionDtoList1 = new ArrayList<>();
                permissionDtoList1.add(permissionDto);
                userDto.setPermissionDtoList(permissionDtoList1);
            } else {
                userDto.setPermissionDtoList(permissionDtoList);
            }
            userDto.setNameDto(userDto.getNameDto());
            userDto.setBookList(userDto.getBookList());
            userDto.setSurname(userDto.getSurname());
            userRepository.save(userMapper.toEntity(userDto));
        } else {
            System.err.println("IN METHOD ADD ROLE TO USER, USER IS NULL");
        }
    }
}
