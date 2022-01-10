package by.project.pharmases_system.controller;

import by.project.pharmases_system.model.ReleaseForm;
import by.project.pharmases_system.service.ReleaseFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/releaseform")
public class ReleaseFormController {

    private ReleaseFormService releaseFormService;

    public ReleaseFormController(ReleaseFormService releaseFormService) {
        this.releaseFormService = releaseFormService;
    }

    @GetMapping
    public List<ReleaseForm> getListReleaseForms() {
        return releaseFormService.getListReleaseForms().getBody();
    }

    @GetMapping("{id}")
    public ReleaseForm getReleaseFormById(@PathVariable Long id) {
        return releaseFormService.getReleaseFormById(id).getBody();
    }

    @PostMapping
    public ResponseEntity<ReleaseForm> createReleaseForm(@RequestBody ReleaseForm releaseFormBody) {
        return releaseFormService.createReleaseForm(releaseFormBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReleaseForm> updateReleaseForm(@PathVariable Long id, @RequestBody ReleaseForm releaseFormBody) {
        return releaseFormService.updateReleaseForm(id, releaseFormBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ReleaseForm> deleteReleaseForm(@PathVariable Long id) {
        return releaseFormService.deleteReleaseForm(id);
    }
}
