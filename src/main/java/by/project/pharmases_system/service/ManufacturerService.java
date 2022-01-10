package by.project.pharmases_system.service;

import by.project.pharmases_system.model.Manufacturer;
import by.project.pharmases_system.model.ReleaseForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ManufacturerService {
    public ResponseEntity<List<Manufacturer>> getListManufacturers();

    public ResponseEntity<Manufacturer> getManufacturerById( Long id);

    public ResponseEntity<Manufacturer> createManufacturer(Manufacturer manufacturerBody);

    public ResponseEntity<Manufacturer> updateManufacturer(Long id, Manufacturer manufacturerBody);

    public ResponseEntity<Manufacturer> deleteManufacturer(Long id);
}
