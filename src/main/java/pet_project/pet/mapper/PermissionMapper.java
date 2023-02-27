package pet_project.pet.mapper;

import org.mapstruct.Mapper;
import pet_project.pet.dto.PermissionDto;
import pet_project.pet.model.Permission;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toEntity(PermissionDto permissionDto);
    PermissionDto toDto(Permission permission);

    List<Permission> toEntityList(List<PermissionDto> permissionDtoList);
    List<PermissionDto> toDtoList(List<Permission> permissionList);
}
