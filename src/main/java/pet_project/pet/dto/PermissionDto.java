package pet_project.pet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class PermissionDto {
    Long id;
    private String name;
}
