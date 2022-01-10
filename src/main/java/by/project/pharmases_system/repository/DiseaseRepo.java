package by.project.pharmases_system.repository;

import by.project.pharmases_system.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepo extends JpaRepository<Disease, Long> {
}
