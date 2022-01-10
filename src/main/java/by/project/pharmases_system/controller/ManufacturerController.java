package by.project.pharmases_system.controller;

import by.project.pharmases_system.model.Manufacturer;
import by.project.pharmases_system.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> getListReleaseForms() {
        return manufacturerService.getListManufacturers().getBody();
    }

    @GetMapping("{id}")
    public Manufacturer getReleaseFormById(@PathVariable Long id) {
        return manufacturerService.getManufacturerById(id).getBody();
    }

    @PostMapping
    public ResponseEntity<Manufacturer> createReleaseForm(@RequestBody Manufacturer releaseFormBody) {
        return manufacturerService.createManufacturer(releaseFormBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<Manufacturer> updateReleaseForm(@PathVariable Long id, @RequestBody Manufacturer releaseFormBody) {
        return manufacturerService.updateManufacturer(id, releaseFormBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Manufacturer> deleteReleaseForm(@PathVariable Long id) {
        return manufacturerService.deleteManufacturer(id);
    }
}
