package pet_project.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Getter
@Setter
@ToString
public class User extends BaseEntity{

    private String name;
    private String surname;
    private String email;
    @Column(name ="picture-url")
    private String picture;
    private byte[] realPicture;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> bookList;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissionList;

}
