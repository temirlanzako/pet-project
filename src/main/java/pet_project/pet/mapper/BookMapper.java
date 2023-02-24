package pet_project.pet.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import pet_project.pet.dto.BookDto;
import pet_project.pet.model.Book;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "nameDto", target = "name")
/*
    @Mapping(source = "catalog.categoryDto", target = "catalog.category") // <=== cant see property in CatalogDto
*/
    Book toEntity(BookDto bookDto);

    @Mapping(source = "name", target = "nameDto")
/*
    @Mapping(source = "catalog.category", target = "catalog.categoryDto")
*/
    BookDto toDto(Book book);

    List<Book> toEntityList(List<BookDto> bookDtoList);

    List<BookDto> toDtoList(List<Book> bookList);
}
