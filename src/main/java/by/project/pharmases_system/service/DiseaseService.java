package by.project.pharmases_system.service;

import by.project.pharmases_system.model.Disease;
import by.project.pharmases_system.model.ReleaseForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiseaseService {
    public ResponseEntity<List<Disease>> getListDiseases();

    public ResponseEntity<Disease> getDiseaseById(Long id);

    public ResponseEntity<Disease> createDisease(Disease diseaseBody);

    public ResponseEntity<Disease> updateDisease(Long id, Disease diseaseBody);

    public ResponseEntity<Disease> deleteDisease(Long id);
}
