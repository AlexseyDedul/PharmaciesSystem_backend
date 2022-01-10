package by.project.pharmases_system.controller;

import by.project.pharmases_system.model.Disease;
import by.project.pharmases_system.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disease")
public class DiseaseController {
    @Autowired
    private DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping
    public List<Disease> getListDiseases() {
        return diseaseService.getListDiseases().getBody();
    }

    @GetMapping("{id}")
    public Disease getDiseaseById(@PathVariable Long id) {
        return diseaseService.getDiseaseById(id).getBody();
    }

    @PostMapping
    public ResponseEntity<Disease> createDisease(@RequestBody Disease diseaseBody) {
        return diseaseService.createDisease(diseaseBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<Disease> updateDisease(@PathVariable Long id, @RequestBody Disease diseaseBody) {
        return diseaseService.updateDisease(id, diseaseBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Disease> deleteDisease(@PathVariable Long id) {
        return diseaseService.deleteDisease(id);
    }
}
