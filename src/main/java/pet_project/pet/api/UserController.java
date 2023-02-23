package pet_project.pet.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet_project.pet.model.Book;
import pet_project.pet.model.User;
import pet_project.pet.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "{id}")
    public List<Book> getUsersBooks(@PathVariable Long id) {
        return userService.getUserBooks(id);
    }

    @PostMapping(value = "{id}/{bookid}")
    public boolean addBookToUser(@PathVariable Long id,
                                 @PathVariable(name = "bookid") Long bookId) {
        return userService.addBookToUser(id, bookId);
    }
    @PutMapping(value = "{id}/{bookid}")
    public boolean deleteUserBook(@PathVariable Long id,
                                  @PathVariable(name = "bookid") Long bookId) {
        return userService.deleteUserBook(id, bookId);
    }
}
