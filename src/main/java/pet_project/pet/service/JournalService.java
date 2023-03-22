package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pet_project.pet.dto.BookDto;
import pet_project.pet.dto.JournalDto;
import pet_project.pet.dto.UserDto;
import pet_project.pet.mapper.BookMapper;
import pet_project.pet.mapper.JournalMapper;
import pet_project.pet.model.Book;
import pet_project.pet.model.Journal;
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
    private final EmailSenderService emailSenderService;

    private final JournalMapper journalMapper;

    private final BookMapper bookMapper;

    public void saveJournal(Journal journal){
        journalRepository.save(journal);
    }

    public List<Journal> getRecords() {
        return journalRepository.findAll();
    }

    public List<JournalDto> getDtoRecords() {
        List<JournalDto> journalDtoList = journalMapper.toDtoList(getRecords());
        return journalDtoList;
    }

    public Journal getRecord(Long id) {
        return journalRepository.findById(id).orElse(null);
    }

    public JournalDto getDtoRecord(Long id) {
        JournalDto journalDto = journalMapper.toDto(getRecord(id));
        return journalDto;
    }

    public boolean addRecord(Long userId, Long journalId, Long bookId) {
        Journal journal = journalRepository.findById(journalId).orElse(null);
        if (journal == null) {
            Journal newRecord = new Journal();
            newRecord.setId(journalId);
            newRecord.setUser(userService.getUser(userId));
            newRecord.setStatus(false);
            newRecord.setTimestamp(Timestamp.from(Instant.now()));
            journal = newRecord;
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
        journal.setDeposit(500.00);
        journal.setDepositStatus(false);
        journalRepository.save(journal);
        return true;
    }

    public boolean addDtoRecord(Long userId, Long journalId, Long bookId) {
        JournalDto journalDto = getDtoRecord(journalId);
        List<BookDto> bookDtoList = bookMapper.toDtoList(journalDto.getBookList());
        BookDto bookDto = bookService.getOneBook(bookId);
        UserDto userDto = userService.getUserDto(userId);

        if (bookDtoList == null) {
            List<BookDto> newBookDtoList = new ArrayList<>();
            newBookDtoList.add(bookDto);
            journalDto.setBookList(bookMapper.toEntityList(newBookDtoList));
        } else {
            bookDtoList.add(bookDto);
            journalDto.setBookList(bookMapper.toEntityList(bookDtoList));
        }
        journalDto.setUserDto(userDto);
        journalDto.setStatus(false);
        journalDto.setTimestamp(Timestamp.from(Instant.now()));
        journalDto.setDeposit(500.00);
        journalDto.setDepositStatus(false);
        journalRepository.save(journalMapper.toEntity(journalDto));
        return true;
    }

    public boolean changeStatus(Long id) {
        Journal journal = journalRepository.findById(id).orElseThrow();
        if (journal.getStatus() == false) {
            journal.setDepositStatus(true);
            journal.setStatus(true);
            journal.setDeposit(0.00);
        } else {
            journal.setStatus(false);
            journal.setStatus(false);
            journal.setDeposit(500.00);
        }
        journalRepository.save(journal);
        return true;
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
