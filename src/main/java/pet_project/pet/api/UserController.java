package pet_project.pet.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pet_project.pet.dto.BookDto;
import pet_project.pet.dto.UserDto;
import pet_project.pet.model.Book;
import pet_project.pet.model.Permission;
import pet_project.pet.model.User;
import pet_project.pet.service.EmailSenderService;
import pet_project.pet.service.FileUploadService;
import pet_project.pet.service.UserService;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final FileUploadService fileUploadService;

    private final EmailSenderService emailSenderService;

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

    @PutMapping(value = "{sessionid}/{id}/{bookid}")
    public String deleteUserBook(@PathVariable Long id,
                                 @PathVariable(name = "sessionid") Long sessionId,
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


    @PostMapping(value = "/{sessionid}/{id}/{roleid}")
    public String addRoleToUser(@PathVariable Long id,
                                @PathVariable(name = "sessionid") Long sessionId,
                                @PathVariable(name = "roleid") Long roleId) {
        User user = userService.getUser(sessionId);
        List<Permission> permissionList = user.getPermissionList();
        for (Permission per : permissionList) {
            if (per.getName().equals("ROLE_ADMIN")) {
                userService.addRoleToUser(id, roleId);
                return "Role successfully assigned";
            }
        }
        return "Sorry, but you don't have permission";
    }
    @PostMapping(value = "/upload/{id}")
    public User uploadPicture(@RequestParam(name = "file") MultipartFile multipartFile,
                              @PathVariable Long id) {

        return fileUploadService.uploadPicture(multipartFile, id);
    }
    @GetMapping(value = "/viewpicture/{picture}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPicture(@PathVariable String picture) throws IOException {
        return fileUploadService.getPicture(picture);
    }
    @PostMapping(value="/send-email/{id}")
    public String sendEmail(@PathVariable Long id,
                            @RequestParam(name = "email") String email,
                            @RequestParam(name = "subject") String subject,
                            @RequestParam(name = "body") String body) {
        emailSenderService.sendEmail(email, subject, body, id);
        return "Message sent successfully";
    }
}
