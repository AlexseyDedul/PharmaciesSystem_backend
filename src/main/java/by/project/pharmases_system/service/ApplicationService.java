package by.project.pharmases_system.service;

import by.project.pharmases_system.model.Application;
import by.project.pharmases_system.model.Disease;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {
    public ResponseEntity<List<Application>> getListApplications();

    public ResponseEntity<Application> getApplicationById(Long id);

    public ResponseEntity<Application> createApplication(Application applicationBody);

    public ResponseEntity<Application> updateApplication(Long id, Application applicationBody);

    public ResponseEntity<Application> deleteApplication(Long id);
}
