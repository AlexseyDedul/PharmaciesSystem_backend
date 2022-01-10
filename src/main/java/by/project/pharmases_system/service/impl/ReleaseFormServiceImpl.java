package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.ReleaseForm;
import by.project.pharmases_system.repository.ReleaseFormRepo;
import by.project.pharmases_system.service.ReleaseFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReleaseFormServiceImpl implements ReleaseFormService {
    @Autowired
    private ReleaseFormRepo releaseFormRepo;

    public ReleaseFormServiceImpl(ReleaseFormRepo releaseFormRepo) {
        this.releaseFormRepo = releaseFormRepo;
    }

    @Override
    public ResponseEntity<List<ReleaseForm>> getListReleaseForms() {
        try {
            List<ReleaseForm> listReleaseForms = releaseFormRepo.findAll();

            if (listReleaseForms.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(listReleaseForms, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ReleaseForm> getReleaseFormById(Long id) {
        Optional<ReleaseForm> releaseFormData = releaseFormRepo.findById(id);

        if(!releaseFormData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(releaseFormData.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReleaseForm> createReleaseForm(ReleaseForm releaseFormBody) {
        try {
            ReleaseForm releaseForm = releaseFormRepo.save(new ReleaseForm(
                    releaseFormBody.getName()));
            return new ResponseEntity<>(releaseForm, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ReleaseForm> updateReleaseForm(Long id, ReleaseForm releaseFormBody) {
        Optional<ReleaseForm> releaseForm = releaseFormRepo.findById(id);

        if(!releaseForm.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ReleaseForm releaseFormTmp = releaseForm.get();
        releaseFormTmp.setName(releaseFormBody.getName());

        return new ResponseEntity<>(releaseFormRepo.save(releaseFormTmp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReleaseForm> deleteReleaseForm(Long id) {
        try {
            releaseFormRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
