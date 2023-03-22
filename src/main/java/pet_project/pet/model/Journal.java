package pet_project.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Journal extends BaseEntity {

    private Timestamp timestamp;
    private Boolean status;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    private Double deposit;
    @Column(name = "deposit_status")
    private Boolean depositStatus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> bookList;
}
