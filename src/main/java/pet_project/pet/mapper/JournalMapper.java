package pet_project.pet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet_project.pet.dto.JournalDto;
import pet_project.pet.model.Journal;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JournalMapper {

    @Mapping(target = "user", source = "userDto")
    Journal toEntity(JournalDto journalDto);

    @Mapping(target = "userDto", source = "user")
    JournalDto toDto(Journal journal);

    List<Journal> toEntityList(List<JournalDto> journalDtoList);

    List<JournalDto> toDtoList(List<Journal> journalList);
}
