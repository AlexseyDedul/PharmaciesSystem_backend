package by.project.pharmases_system.controller;

import by.project.pharmases_system.model.MedicineWarehouse;
import by.project.pharmases_system.model.User;
import by.project.pharmases_system.service.ManufacturerService;
import by.project.pharmases_system.service.MedicineWarehouseService;
import by.project.pharmases_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicinewarehouse")
public class MedicineWarehouseController {
    @Autowired
    private MedicineWarehouseService medicineWarehouseService;

    public MedicineWarehouseController(MedicineWarehouseService medicineWarehouseService) {
        this.medicineWarehouseService = medicineWarehouseService;
    }

    @GetMapping
    public List<MedicineWarehouse> getListMedicineWarehouses(@RequestParam(required = false) String surname) {
        return medicineWarehouseService.getListMedicineWarehouses(surname).getBody();
    }

    @GetMapping("{id}")
    public MedicineWarehouse getMedicineWarehouseById(@PathVariable Long id) {
        return medicineWarehouseService.getMedicineWarehouseById(id).getBody();
    }

    @PostMapping
    public ResponseEntity<MedicineWarehouse> createMedicineWarehouse(@RequestBody MedicineWarehouse medicineWarehouseBody) {
        return medicineWarehouseService.createMedicineWarehouse(medicineWarehouseBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<MedicineWarehouse> updateMedicineWarehouse(@PathVariable Long id, @RequestBody MedicineWarehouse medicineWarehouseBody) {
        return medicineWarehouseService.updateMedicineWarehouse(id, medicineWarehouseBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MedicineWarehouse> deleteMedicineWarehouse(@PathVariable Long id) {
        return medicineWarehouseService.deleteMedicineWarehouse(id);
    }
}
