package pet_project.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
public class Book extends BaseEntity{

    private String name;
    private String description;
    private String author;
    private int price;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Catalog catalog;

}
