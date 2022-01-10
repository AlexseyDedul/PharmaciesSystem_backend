package by.project.pharmases_system.controller;

import by.project.pharmases_system.model.Disease;
import by.project.pharmases_system.model.MedicinesInTheApplication;
import by.project.pharmases_system.service.MedicinesInTheApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medinapp")
public class MedicinesInTheApplicationController {
    @Autowired
    private MedicinesInTheApplicationService medicinesInTheApplicationService;

    public MedicinesInTheApplicationController(MedicinesInTheApplicationService medicinesInTheApplicationService) {
        this.medicinesInTheApplicationService = medicinesInTheApplicationService;
    }

    @GetMapping
    public List<MedicinesInTheApplication> getListMedicinesInTheApplications() {
        return medicinesInTheApplicationService.getListMedicinesInTheApplications().getBody();
    }

    @GetMapping("{id}")
    public MedicinesInTheApplication getMedicinesInTheApplicationById(@PathVariable Long id) {
        return medicinesInTheApplicationService.getMedicinesInTheApplicationById(id).getBody();
    }

    @PostMapping
    public ResponseEntity<MedicinesInTheApplication> createMedicinesInTheApplication(@RequestBody MedicinesInTheApplication medicinesInTheApplicationBody) {
        return medicinesInTheApplicationService.createMedicinesInTheApplication(medicinesInTheApplicationBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<MedicinesInTheApplication> updateMedicinesInTheApplication(@PathVariable Long id, @RequestBody MedicinesInTheApplication medicinesInTheApplicationBody) {
        return medicinesInTheApplicationService.updateMedicinesInTheApplication(id, medicinesInTheApplicationBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MedicinesInTheApplication> deleteMedicinesInTheApplication(@PathVariable Long id) {
        return medicinesInTheApplicationService.deleteMedicinesInTheApplication(id);
    }
}
