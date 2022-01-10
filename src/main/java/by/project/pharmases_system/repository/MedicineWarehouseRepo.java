package by.project.pharmases_system.repository;

import by.project.pharmases_system.model.MedicineWarehouse;
import by.project.pharmases_system.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineWarehouseRepo extends JpaRepository<MedicineWarehouse, Long> {
    List<MedicineWarehouse> findByNameContaining(String name);
}
