package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.Application;
import by.project.pharmases_system.model.Disease;
import by.project.pharmases_system.repository.ApplicationRepo;
import by.project.pharmases_system.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepo applicationRepo;

    public ApplicationServiceImpl(ApplicationRepo applicationRepo) {
        this.applicationRepo = applicationRepo;
    }

    @Override
    public ResponseEntity<List<Application>> getListApplications() {
        try {
            List<Application> listApplications = applicationRepo.findAll();

            if (listApplications.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(listApplications, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Application> getApplicationById(Long id) {
        Optional<Application> applicationData = applicationRepo.findById(id);

        if(!applicationData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(applicationData.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Application> createApplication(Application applicationBody) {
        try {
            Application application = applicationRepo.save(new Application(
                    applicationBody.getPharmacy(),
                    applicationBody.getReason(),
                    applicationBody.getAllPrice()
                    ));
            return new ResponseEntity<>(application, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Application> updateApplication(Long id, Application applicationBody) {
        Optional<Application> application = applicationRepo.findById(id);

        if(!application.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Application applicationTmp = application.get();
        applicationTmp.setDate();
        applicationTmp.setPharmacy(applicationBody.getPharmacy());
        applicationTmp.setReason(applicationBody.getReason());
        applicationTmp.setAllPrice(applicationBody.getAllPrice());

        return new ResponseEntity<>(applicationRepo.save(applicationTmp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Application> deleteApplication(Long id) {
        try {
            applicationRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
