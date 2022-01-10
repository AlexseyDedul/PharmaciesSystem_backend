package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.MedicinesInTheApplication;
import by.project.pharmases_system.model.MedicinesInTheInvoice;
import by.project.pharmases_system.repository.MedicinesInTheInvoiceRepo;
import by.project.pharmases_system.service.MedicinesInTheInvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicinesInTheInvoiceServiceImpl implements MedicinesInTheInvoiceService {
    private MedicinesInTheInvoiceRepo medicinesInTheInvoiceRepo;

    public MedicinesInTheInvoiceServiceImpl(MedicinesInTheInvoiceRepo medicinesInTheInvoiceRepo) {
        this.medicinesInTheInvoiceRepo = medicinesInTheInvoiceRepo;
    }

    @Override
    public ResponseEntity<List<MedicinesInTheInvoice>> getListMedicinesInTheInvoices() {
        try {
            List<MedicinesInTheInvoice> listMedicinesInTheInvoices = medicinesInTheInvoiceRepo.findAll();

            if (listMedicinesInTheInvoices.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(listMedicinesInTheInvoices, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<MedicinesInTheInvoice> getMedicinesInTheInvoiceById(Long id) {
        Optional<MedicinesInTheInvoice> medicinesInTheInvoiceData = medicinesInTheInvoiceRepo.findById(id);

        if(!medicinesInTheInvoiceData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(medicinesInTheInvoiceData.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicinesInTheInvoice> createMedicinesInTheInvoice(MedicinesInTheInvoice medicinesInTheInvoiceBody) {
        try {
            MedicinesInTheInvoice medicinesInTheInvoice = medicinesInTheInvoiceRepo.save(new MedicinesInTheInvoice(
                    medicinesInTheInvoiceBody.getInvoice(),
                    medicinesInTheInvoiceBody.getMedicineWarehouse(),
                    medicinesInTheInvoiceBody.getNumber()));
            return new ResponseEntity<>(medicinesInTheInvoice, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<MedicinesInTheInvoice> updateMedicinesInTheInvoice(Long id, MedicinesInTheInvoice medicinesInTheInvoiceBody) {
        Optional<MedicinesInTheInvoice> medicinesInTheInvoice = medicinesInTheInvoiceRepo.findById(id);

        if(!medicinesInTheInvoice.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        MedicinesInTheInvoice medicinesInTheInvoiceTmp = medicinesInTheInvoice.get();
        medicinesInTheInvoiceTmp.setInvoice(medicinesInTheInvoiceBody.getInvoice());
        medicinesInTheInvoiceTmp.setMedicineWarehouse(medicinesInTheInvoiceBody.getMedicineWarehouse());
        medicinesInTheInvoiceTmp.setNumber(medicinesInTheInvoiceBody.getNumber());

        return new ResponseEntity<>(medicinesInTheInvoiceRepo.save(medicinesInTheInvoiceTmp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicinesInTheInvoice> deleteMedicinesInTheInvoice(Long id) {
        try {
            medicinesInTheInvoiceRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
