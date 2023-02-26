package pet_project.pet.dto;

import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapping;
import pet_project.pet.model.Catalog;

@Getter
@Setter
@Builder
public class BookDto {
    private Long id;
    private String nameDto;
    private String description;
    private String author;
    private int price;
    private CatalogDto catalogDto;
}
