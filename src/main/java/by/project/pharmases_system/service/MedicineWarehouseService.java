package by.project.pharmases_system.service;

import by.project.pharmases_system.model.MedicineWarehouse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicineWarehouseService {
    public ResponseEntity<List<MedicineWarehouse>> getListMedicineWarehouses(String name);

    public ResponseEntity<MedicineWarehouse> getMedicineWarehouseById(Long id);

    public ResponseEntity<MedicineWarehouse> createMedicineWarehouse(MedicineWarehouse medicineWarehouseBody);

    public ResponseEntity<MedicineWarehouse> updateMedicineWarehouse(Long id, MedicineWarehouse medicineWarehouseBody);

    public ResponseEntity<MedicineWarehouse> deleteMedicineWarehouse(Long id);
}
