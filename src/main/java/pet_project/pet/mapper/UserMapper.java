package pet_project.pet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet_project.pet.dto.BookDto;
import pet_project.pet.dto.UserDto;
import pet_project.pet.model.Book;
import pet_project.pet.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "nameDto", target = "name")
    User toEntity(UserDto userDto);
    @Mapping(source = "name", target = "nameDto")
    UserDto toDto(User user);

    List<User> toEntityList(List<UserDto> userDtoList);
    List<UserDto> toDtoList(List<User> userList);

    List<Book> toEntityBookList(List<BookDto> userBookDtoList);
    List<BookDto> toDtoBookList(List<Book> userBookList);
}
