package by.project.pharmases_system.service;

import by.project.pharmases_system.model.ReleaseForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReleaseFormService {
    public ResponseEntity<List<ReleaseForm>> getListReleaseForms();

    public ResponseEntity<ReleaseForm> getReleaseFormById( Long id);

    public ResponseEntity<ReleaseForm> createReleaseForm(ReleaseForm releaseFormBody);

    public ResponseEntity<ReleaseForm> updateReleaseForm(Long id, ReleaseForm releaseFormBody);

    public ResponseEntity<ReleaseForm> deleteReleaseForm(Long id);
}
