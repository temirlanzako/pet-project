package pet_project.pet.dto;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import pet_project.pet.model.Book;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String nameDto;
    private String surname;
    private List<BookDto> bookList;
}
