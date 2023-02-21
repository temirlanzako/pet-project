package pet_project.pet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book extends BaseEntity{

    private String name;
    private String description;
    private String author;
    private int price;
    @ManyToOne
    private Catalog catalog;

}
