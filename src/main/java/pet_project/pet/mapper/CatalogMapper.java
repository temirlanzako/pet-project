package pet_project.pet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet_project.pet.dto.CatalogDto;
import pet_project.pet.model.Catalog;

@Mapper(componentModel = "spring")
public interface CatalogMapper {
    @Mapping(source = "categoryDto", target = "category")
    Catalog toEntity(CatalogDto catalogDto);
    @Mapping(source = "category", target = "categoryDto")

    CatalogDto toDto(Catalog catalog);
}
