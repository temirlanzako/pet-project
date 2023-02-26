package pet_project.pet.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet_project.pet.model.Journal;
import pet_project.pet.repository.JournalRepository;
import pet_project.pet.service.JournalService;

import java.util.List;

@RestController
@RequestMapping(value = "/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;
    private final JournalRepository journalRepository;

    @GetMapping
    public List<Journal> getRecords() {
        return journalService.getRecords();
    }

    @GetMapping(value = "{id}")
    public Journal getOneRecord(@PathVariable Long id) {
        return journalService.getRecord(id);
    }

    @PostMapping(value = "{userId}/{id}/{bookId}")
    public boolean addBooksToRecord(@PathVariable Long id,
                                    @PathVariable(name="userId") Long userId,
                                    @PathVariable(name = "bookId") Long bookId) {
        journalService.addRecord(userId,id,bookId);
        return true;
    }
    @PutMapping(value ="{id}")
    public Journal changeStatus(@PathVariable Long id) {
        return journalService.changeStatus(id);
    }
    /*
    @PostMapping(value = "{id}/{bookid}")
    public boolean addRecord(@PathVariable Long id,
                             @PathVariable(name = "bookid") Long bookId) {
        return journalService.addRecord(id, bookId);
    }*/
}
