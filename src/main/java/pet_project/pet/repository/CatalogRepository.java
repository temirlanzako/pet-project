package pet_project.pet.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_project.pet.model.Catalog;

@Repository
@Transactional
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
