package pet_project.pet.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_project.pet.dto.PermissionDto;
import pet_project.pet.model.Permission;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByName(String name);
}
