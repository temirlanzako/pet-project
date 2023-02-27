package pet_project.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class User extends BaseEntity{

    private String name;
    private String surname;
    @OneToMany
    private List<Book> bookList;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permissionList;

}
