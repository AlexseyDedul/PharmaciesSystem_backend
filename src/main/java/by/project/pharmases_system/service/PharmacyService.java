package by.project.pharmases_system.service;

import by.project.pharmases_system.model.Pharmacy;
import by.project.pharmases_system.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PharmacyService {
    public ResponseEntity<List<Pharmacy>> getListPharmacies(String name);

    public ResponseEntity<Pharmacy> getPharmacyById(Long id);

    public ResponseEntity<Pharmacy> createPharmacy(Pharmacy pharmacyBody);

    public ResponseEntity<Pharmacy> updatePharmacy(Long id, Pharmacy pharmacyBody);

    public ResponseEntity<Pharmacy> deletePharmacy(Long id);
}
