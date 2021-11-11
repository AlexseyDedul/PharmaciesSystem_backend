package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.Pharmacy;
import by.project.pharmases_system.repository.PharmacyRepo;
import by.project.pharmases_system.service.PharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    private PharmacyRepo pharmacyRepo;

    public PharmacyServiceImpl(PharmacyRepo pharmacyRepo) {
        this.pharmacyRepo = pharmacyRepo;
    }

    @Override
    public ResponseEntity<List<Pharmacy>> getListPharmacies(String name) {
        try{
            List<Pharmacy> pharmacyList = null;
            if(name == null)
                pharmacyList = pharmacyRepo.findAll();
            else
                pharmacyList = pharmacyRepo.findByNameContaining(name);

            if(pharmacyList.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(pharmacyList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Pharmacy> getPharmacyById(Long id) {
        Optional<Pharmacy> pharmacyData = pharmacyRepo.findById(id);

        if(!pharmacyData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(pharmacyData.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Pharmacy> createPharmacy(Pharmacy pharmacyBody) {
        try{
            Pharmacy pharmacy = pharmacyRepo.save(new Pharmacy(
                    pharmacyBody.getName(),
                    pharmacyBody.getStateNumber(),
                    pharmacyBody.getCountry(),
                    pharmacyBody.getAddress()));
            return new ResponseEntity<>(pharmacy, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Pharmacy> updatePharmacy(Long id, Pharmacy pharmacyBody) {
        Optional<Pharmacy> pharmacy = pharmacyRepo.findById(id);

        if(!pharmacy.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Pharmacy pharmacyTmp = pharmacy.get();

        pharmacyTmp.setName(pharmacyBody.getName());
        pharmacyTmp.setStateNumber(pharmacyBody.getStateNumber());
        pharmacyTmp.setCountry(pharmacyBody.getCountry());
        pharmacyTmp.setAddress(pharmacyBody.getAddress());

        return new ResponseEntity<>(pharmacyRepo.save(pharmacyTmp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Pharmacy> deletePharmacy(Long id) {
        try{
            pharmacyRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
