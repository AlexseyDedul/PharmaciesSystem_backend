package by.project.pharmases_system.repository;

import by.project.pharmases_system.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyRepo extends JpaRepository<Pharmacy, Long> {
    List<Pharmacy> findByNameContaining(String name);
}
