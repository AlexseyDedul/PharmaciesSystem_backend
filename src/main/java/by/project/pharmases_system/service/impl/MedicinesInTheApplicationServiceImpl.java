package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.MedicinesInTheApplication;
import by.project.pharmases_system.repository.MedicinesInTheApplicationRepo;
import by.project.pharmases_system.service.MedicinesInTheApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicinesInTheApplicationServiceImpl implements MedicinesInTheApplicationService {
    @Autowired
    private MedicinesInTheApplicationRepo medicinesInTheApplicationRepo;

    public MedicinesInTheApplicationServiceImpl(MedicinesInTheApplicationRepo medicinesInTheApplicationRepo) {
        this.medicinesInTheApplicationRepo = medicinesInTheApplicationRepo;
    }

    @Override
    public ResponseEntity<List<MedicinesInTheApplication>> getListMedicinesInTheApplications() {
        try {
            List<MedicinesInTheApplication> listMedicinesInTheApplications = medicinesInTheApplicationRepo.findAll();

            if (listMedicinesInTheApplications.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(listMedicinesInTheApplications, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<MedicinesInTheApplication> getMedicinesInTheApplicationById(Long id) {
        Optional<MedicinesInTheApplication> medicinesInTheApplicationData = medicinesInTheApplicationRepo.findById(id);

        if(!medicinesInTheApplicationData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(medicinesInTheApplicationData.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicinesInTheApplication> createMedicinesInTheApplication(MedicinesInTheApplication medicinesInTheApplicationBody) {
        try {
            MedicinesInTheApplication medicinesInTheApplication = medicinesInTheApplicationRepo.save(new MedicinesInTheApplication(
                    medicinesInTheApplicationBody.getApplication(),
                    medicinesInTheApplicationBody.getMedicineWarehouse(),
                    medicinesInTheApplicationBody.getNumber()));
            return new ResponseEntity<>(medicinesInTheApplication, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<MedicinesInTheApplication> updateMedicinesInTheApplication(Long id, MedicinesInTheApplication medicinesInTheApplicationBody) {
        Optional<MedicinesInTheApplication> medicinesInTheApplication = medicinesInTheApplicationRepo.findById(id);

        if(!medicinesInTheApplication.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        MedicinesInTheApplication medicinesInTheApplicationTmp = medicinesInTheApplication.get();
        medicinesInTheApplicationTmp.setApplication(medicinesInTheApplicationBody.getApplication());
        medicinesInTheApplicationTmp.setMedicineWarehouse(medicinesInTheApplicationBody.getMedicineWarehouse());
        medicinesInTheApplicationTmp.setNumber(medicinesInTheApplicationBody.getNumber());

        return new ResponseEntity<>(medicinesInTheApplicationRepo.save(medicinesInTheApplicationTmp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicinesInTheApplication> deleteMedicinesInTheApplication(Long id) {
        try {
            medicinesInTheApplicationRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
