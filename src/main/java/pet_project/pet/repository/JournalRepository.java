package pet_project.pet.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet_project.pet.model.Book;
import pet_project.pet.model.Journal;

import java.util.List;

@Repository
@Transactional
public interface JournalRepository extends JpaRepository<Journal, Long> {
}
