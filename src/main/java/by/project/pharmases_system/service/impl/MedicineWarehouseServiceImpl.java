package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.MedicineWarehouse;
import by.project.pharmases_system.model.Pharmacy;
import by.project.pharmases_system.model.User;
import by.project.pharmases_system.repository.MedicineWarehouseRepo;
import by.project.pharmases_system.service.MedicineWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineWarehouseServiceImpl implements MedicineWarehouseService {
    @Autowired
    private MedicineWarehouseRepo medicineWarehouseRepo;

    public MedicineWarehouseServiceImpl(MedicineWarehouseRepo medicineWarehouseRepo) {
        this.medicineWarehouseRepo = medicineWarehouseRepo;
    }

    @Override
    public ResponseEntity<List<MedicineWarehouse>> getListMedicineWarehouses(String name) {
        try{
            List<MedicineWarehouse> medicineWarehouseList = null;
            if(name == null)
                medicineWarehouseList = medicineWarehouseRepo.findAll();
            else
                medicineWarehouseList = medicineWarehouseRepo.findByNameContaining(name);

            if(medicineWarehouseList.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(medicineWarehouseList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<MedicineWarehouse> getMedicineWarehouseById(Long id) {
        Optional<MedicineWarehouse> medicineWarehouseData = medicineWarehouseRepo.findById(id);

        if(!medicineWarehouseData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(medicineWarehouseData.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicineWarehouse> createMedicineWarehouse(MedicineWarehouse medicineWarehouseBody) {
        try{
            MedicineWarehouse medicineWarehouse = medicineWarehouseRepo.save(new MedicineWarehouse(
                    medicineWarehouseBody.getName(),
                    medicineWarehouseBody.getReleaseForm(),
                    medicineWarehouseBody.getManufacture(),
                    medicineWarehouseBody.getPrice(),
                    medicineWarehouseBody.getShelfLife(),
                    medicineWarehouseBody.getDescription(),
                    medicineWarehouseBody.getDosage(),
                    medicineWarehouseBody.getNumber(),
                    medicineWarehouseBody.getDisease()));
            return new ResponseEntity<>(medicineWarehouse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<MedicineWarehouse> updateMedicineWarehouse(Long id, MedicineWarehouse medicineWarehouseBody) {
        Optional<MedicineWarehouse> medicineWarehouse = medicineWarehouseRepo.findById(id);

        if(!medicineWarehouse.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        MedicineWarehouse medicineWarehouseTmp = medicineWarehouse.get();
        medicineWarehouseTmp.setName(medicineWarehouseBody.getName());
        medicineWarehouseTmp.setReleaseForm(medicineWarehouseBody.getReleaseForm());
        medicineWarehouseTmp.setManufacture(medicineWarehouseBody.getManufacture());
        medicineWarehouseTmp.setPrice(medicineWarehouseBody.getPrice());
        medicineWarehouseTmp.setShelfLife(medicineWarehouseBody.getShelfLife());
        medicineWarehouseTmp.setDescription(medicineWarehouseBody.getDescription());
        medicineWarehouseTmp.setDosage(medicineWarehouseBody.getDosage());
        medicineWarehouseTmp.setNumber(medicineWarehouseBody.getNumber());
        medicineWarehouseTmp.setDisease(medicineWarehouseBody.getDisease());

        return new ResponseEntity<>(medicineWarehouseRepo.save(medicineWarehouseTmp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicineWarehouse> deleteMedicineWarehouse(Long id) {
        try {
            medicineWarehouseRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
