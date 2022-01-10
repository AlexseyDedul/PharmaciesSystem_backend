package by.project.pharmases_system.controller;

import by.project.pharmases_system.model.Application;
import by.project.pharmases_system.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<Application> getListApplications() {
        return applicationService.getListApplications().getBody();
    }

    @GetMapping("{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id).getBody();
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody Application applicationBody) {
        return applicationService.createApplication(applicationBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id, @RequestBody Application applicationBody) {
        return applicationService.updateApplication(id, applicationBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Application> deleteApplication(@PathVariable Long id) {
        return applicationService.deleteApplication(id);
    }
}
