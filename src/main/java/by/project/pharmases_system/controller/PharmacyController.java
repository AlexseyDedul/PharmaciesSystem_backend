package by.project.pharmases_system.controller;

import by.project.pharmases_system.model.Pharmacy;
import by.project.pharmases_system.service.PharmacyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    private PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public ResponseEntity<List<Pharmacy>> getListPharmacies(@RequestParam(required = false) String name){
        return pharmacyService.getListPharmacies(name);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable Long id){
        return pharmacyService.getPharmacyById(id);
    }

    @PostMapping
    public ResponseEntity<Pharmacy> createPharmacy(@RequestBody Pharmacy pharmacy){
        return pharmacyService.createPharmacy(pharmacy);
    }

    @PutMapping("{id}")
    public ResponseEntity<Pharmacy> updatePharmacy(@PathVariable Long id, @RequestBody Pharmacy pharmacy){
        return pharmacyService.updatePharmacy(id, pharmacy);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pharmacy> deletePharmacy(@PathVariable Long id){
        return pharmacyService.deletePharmacy(id);
    }
}
