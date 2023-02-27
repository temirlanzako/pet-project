package pet_project.pet.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet_project.pet.dto.BookDto;
import pet_project.pet.dto.UserDto;
import pet_project.pet.model.Book;
import pet_project.pet.model.Permission;
import pet_project.pet.model.User;
import pet_project.pet.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsersDto();
    }

    @GetMapping(value = "{id}")
    public List<BookDto> getUsersBooks(@PathVariable Long id) {
        return userService.getUserBooksDto(id);
    }

    @PostMapping(value = "{id}/{bookid}")
    public boolean addBookToUser(@PathVariable Long id,
                                 @PathVariable(name = "bookid") Long bookId) {
        return userService.addBookToUserDto(id, bookId);
    }

    @PutMapping(value = "{id}/{bookid}")
    public String deleteUserBook(@PathVariable Long id,
                                 @PathVariable(name = "bookid") Long bookId) {
        User user = userService.getUser(id);
        List<Permission> permissionList = user.getPermissionList();
        for (Permission per : permissionList) {
            if (per.getName().equals("ROLE_ADMIN")) {
                userService.deleteUserBook(id, bookId);
                return "Book successfully deleted from Users list";
            }
        }
        return "Sorry, but you don't have permission";
    }


    @PostMapping(value = "/addrole/{id}/{roleid}")
    public String addRoleToUser(@PathVariable Long id,
                                @PathVariable(name = "roleid") Long roleId) {
        User user = userService.getUser(id);
        List<Permission> permissionList = user.getPermissionList();
        for (Permission per : permissionList) {
            if (per.getName().equals("ROLE_ADMIN")) {
                userService.addRoleToUser(id, roleId);
                return "Role successfully assigned";
            }
        }
        return "Sorry, but you don't have permission";

    }
}
