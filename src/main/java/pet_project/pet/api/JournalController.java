package pet_project.pet.api;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import pet_project.pet.dto.JournalDto;
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

import java.sql.Timestamp;
import java.util.Date;
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
    public List<JournalDto> getRecords() {
        return journalService.getDtoRecords();
    }

    @GetMapping(value = "{id}")
    public JournalDto getOneRecord(@PathVariable Long id) {
        return journalService.getDtoRecord(id);
    }

    @PostMapping(value = "{userId}/{journalid}/{bookId}")
    public boolean addBooksToRecord(@PathVariable(name = "journalid")Long journalId,
                                    @PathVariable(name = "userId") Long userId,
                                    @PathVariable(name = "bookId") Long bookId) {
                journalService.addDtoRecord(userId, journalId, bookId);
                return true;
    }

    @PutMapping(value = "{userid}/{id}")
    public boolean changeStatus(@PathVariable Long id,
                               @PathVariable(name = "userid") Long userId) {
        User user = userService.getUser(userId);
        List<Permission> permissionList = user.getPermissionList();
        for(Permission per : permissionList) {
            if(per.getName().equals("ROLE_STAFF")) {
                journalService.changeStatus(id);
                return true;
            }
        }
        return false;
    }
}
    /*
    @PostMapping(value = "{id}/{bookid}")
    public boolean addRecord(@PathVariable Long id,
                             @PathVariable(name = "bookid") Long bookId) {
        return journalService.addRecord(id, bookId);
    }*/
