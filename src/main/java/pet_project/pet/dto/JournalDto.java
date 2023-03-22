package pet_project.pet.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet_project.pet.model.BaseEntity;
import pet_project.pet.model.Book;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
public class JournalDto extends BaseEntity {

    private Timestamp timestamp;
    private Boolean status;
    private UserDto userDto;
    private Double deposit;
    private Boolean depositStatus;
    private List<Book> bookList;
}
