package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.Disease;
import by.project.pharmases_system.model.ReleaseForm;
import by.project.pharmases_system.repository.DiseaseRepo;
import by.project.pharmases_system.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    @Autowired
    private DiseaseRepo diseaseRepo;

    public DiseaseServiceImpl(DiseaseRepo diseaseRepo) {
        this.diseaseRepo = diseaseRepo;
    }

    @Override
    public ResponseEntity<List<Disease>> getListDiseases() {
        try {
            List<Disease> listDiseases = diseaseRepo.findAll();

            if (listDiseases.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(listDiseases, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Disease> getDiseaseById(Long id) {
        Optional<Disease> diseaseData = diseaseRepo.findById(id);

        if(!diseaseData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(diseaseData.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Disease> createDisease(Disease diseaseBody) {
        try {
            Disease disease = diseaseRepo.save(new Disease(
                    diseaseBody.getName(),
                    diseaseBody.getTreatment()));
            return new ResponseEntity<>(disease, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Disease> updateDisease(Long id, Disease diseaseBody) {
        Optional<Disease> disease = diseaseRepo.findById(id);

        if(!disease.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Disease diseaseTmp = disease.get();
        diseaseTmp.setName(diseaseBody.getName());
        diseaseTmp.setTreatment(diseaseBody.getTreatment());

        return new ResponseEntity<>(diseaseRepo.save(diseaseTmp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Disease> deleteDisease(Long id) {
        try {
            diseaseRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
