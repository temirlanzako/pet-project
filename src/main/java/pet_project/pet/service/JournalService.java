package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet_project.pet.model.Book;
import pet_project.pet.model.Journal;
import pet_project.pet.model.User;
import pet_project.pet.repository.JournalRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;

    private final UserService userService;

    private final BookService bookService;

    public List<Journal> getRecords(){
        return journalRepository.findAll();
    }

    public Journal getRecord(Long id) {
        return journalRepository.findById(id).orElseThrow();
    }
    public boolean addRecord(Long userId, Long id, Long bookId) {
        if (id != null) {
            Journal journal = journalRepository.findById(id).orElse(null);
            if (journal == null) {
                Journal journal1 = new Journal();
                journal1.setUser(userService.getUser(userId));
                journal1.setStatus(false);
                journal1.setTimestamp(Timestamp.from(Instant.now()));
                journal = journal1;
            } else {
                journal.setUser(userService.getUser(userId));
                journal.setStatus(false);
                journal.setTimestamp(Timestamp.from(Instant.now()));
            }
            List<Book> books = journal.getBookList();
            if (books == null) {
                List<Book> bookList = new ArrayList<>();
                bookList.add(bookService.getBook(bookId));
                journal.setBookList(bookList);
            } else {
                books.add(bookService.getBook(bookId));
                journal.setBookList(books);
            }
            journalRepository.save(journal);
        }
        return true;
    }
    public void changeStatus(Long id) {
        Journal journal = journalRepository.findById(id).orElseThrow();
        if(journal.getStatus()==false) {
            journal.setStatus(true);
        } else {
            journal.setStatus(false);
        }
        journalRepository.save(journal);
    }
}

  /*  public boolean addRecord(Long userId, Long bookId) {
        Journal journal = new Journal();
        User user = userService.getUser(userId);
        journal.setUser(user);
        List<Book> bookList = addBookToJournal(bookId, journal.getId());
        journal.setBookList(bookList);
        journal.setTimestamp(Timestamp.from(Instant.now()));
        journal.setStatus(false);
        journalRepository.save(journal);
        return true;
    }*/
