package pet_project.pet.model;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Permission extends BaseEntity {

    private String name;
}
