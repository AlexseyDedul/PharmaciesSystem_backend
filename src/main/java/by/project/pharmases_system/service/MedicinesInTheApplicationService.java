package by.project.pharmases_system.service;

import by.project.pharmases_system.model.Disease;
import by.project.pharmases_system.model.MedicinesInTheApplication;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicinesInTheApplicationService {
    public ResponseEntity<List<MedicinesInTheApplication>> getListMedicinesInTheApplications();

    public ResponseEntity<MedicinesInTheApplication> getMedicinesInTheApplicationById(Long id);

    public ResponseEntity<MedicinesInTheApplication> createMedicinesInTheApplication(MedicinesInTheApplication medicinesInTheApplicationBody);

    public ResponseEntity<MedicinesInTheApplication> updateMedicinesInTheApplication(Long id, MedicinesInTheApplication medicinesInTheApplicationBody);

    public ResponseEntity<MedicinesInTheApplication> deleteMedicinesInTheApplication(Long id);
}
