package pet_project.pet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CatalogDto {
    private Long id;
    private String categoryDto;
}
