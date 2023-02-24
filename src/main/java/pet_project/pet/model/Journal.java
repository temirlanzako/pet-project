package pet_project.pet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Journal extends BaseEntity {

    private Timestamp timestamp;
    private Boolean status;
    @OneToOne
    private User user;
    @OneToMany
    private List<Book> bookList;
}
