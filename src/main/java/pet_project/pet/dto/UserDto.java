package pet_project.pet.dto;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pet_project.pet.model.Book;
import pet_project.pet.model.Permission;

import java.util.List;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String nameDto;
    private String surname;
    private String email;
    private List<BookDto> bookList;
    private List<PermissionDto> permissionDtoList;
}
