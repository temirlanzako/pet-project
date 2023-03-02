package pet_project.pet.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet_project.pet.dto.PermissionDto;
import pet_project.pet.mapper.PermissionMapper;
import pet_project.pet.mapper.UserMapper;
import pet_project.pet.model.Journal;
import pet_project.pet.model.Permission;
import pet_project.pet.model.User;
import pet_project.pet.repository.JournalRepository;
import pet_project.pet.repository.UserRepository;
import pet_project.pet.service.JournalService;
import pet_project.pet.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;
    private final UserService userService;

    private final UserMapper userMapper;

    private final PermissionMapper permissionMapper;

    @GetMapping
    public List<Journal> getRecords() {
        return journalService.getRecords();
    }

    @GetMapping(value = "{id}")
    public Journal getOneRecord(@PathVariable Long id) {
        return journalService.getRecord(id);
    }

    @PostMapping(value = "{sessionid}{userId}/{id}/{bookId}")
    public String addBooksToRecord(@PathVariable Long id,
                                    @PathVariable(name = "sessionid") Long sessionId,
                                    @PathVariable(name = "userId") Long userId,
                                    @PathVariable(name = "bookId") Long bookId) {
        User user = userService.getUser(sessionId);
        List<Permission> permissionList = user.getPermissionList();
        for(Permission per : permissionList) {
            if(per.getName().equals("ROLE_STAFF")) {
                journalService.addRecord(userId, id, bookId);
                return "Status of the record successfully changed";
            }
        }
        return "Sorry, you dont have permission";
    }

    @PutMapping(value = "{userid}/{id}")
    public String changeStatus(@PathVariable Long id,
                               @PathVariable(name = "userid") Long userId) {
        User user = userService.getUser(userId);
        List<Permission> permissionList = user.getPermissionList();
        for(Permission per : permissionList) {
            if(per.getName().equals("ROLE_STAFF")) {
                journalService.changeStatus(id);
                return "Status of the record successfully changed";
            }
        }
        return "Sorry, you dont have permission";
    }
}
    /*
    @PostMapping(value = "{id}/{bookid}")
    public boolean addRecord(@PathVariable Long id,
                             @PathVariable(name = "bookid") Long bookId) {
        return journalService.addRecord(id, bookId);
    }*/
