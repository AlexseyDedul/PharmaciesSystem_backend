package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.Manufacturer;
import by.project.pharmases_system.model.ReleaseForm;
import by.project.pharmases_system.repository.ManufacturerRepo;
import by.project.pharmases_system.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepo manufacturerRepo;

    public ManufacturerServiceImpl(ManufacturerRepo manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    @Override
    public ResponseEntity<List<Manufacturer>> getListManufacturers() {
        try {
            List<Manufacturer> listManufacturers = manufacturerRepo.findAll();

            if (listManufacturers.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(listManufacturers, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Manufacturer> getManufacturerById(Long id) {
        Optional<Manufacturer> manufacturerData = manufacturerRepo.findById(id);

        if(!manufacturerData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(manufacturerData.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Manufacturer> createManufacturer(Manufacturer manufacturerBody) {
        try {
            Manufacturer manufacturer = manufacturerRepo.save(new Manufacturer(
                    manufacturerBody.getName(),
                    manufacturerBody.getCountry(),
                    manufacturerBody.getCity(),
                    manufacturerBody.getAddress(),
                    manufacturerBody.getPhoneNumb()));
            return new ResponseEntity<>(manufacturer, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Manufacturer> updateManufacturer(Long id, Manufacturer manufacturerBody) {
        Optional<Manufacturer> manufacturer = manufacturerRepo.findById(id);

        if(!manufacturer.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Manufacturer manufacturerTmp = manufacturer.get();
        manufacturerTmp.setName(manufacturerBody.getName());
        manufacturerTmp.setCountry(manufacturerBody.getCountry());
        manufacturerTmp.setCity(manufacturerBody.getCity());
        manufacturerTmp.setAddress(manufacturerBody.getAddress());
        manufacturerTmp.setPhoneNumb(manufacturerBody.getPhoneNumb());

        return new ResponseEntity<>(manufacturerRepo.save(manufacturerTmp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Manufacturer> deleteManufacturer(Long id) {
        try {
            manufacturerRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
