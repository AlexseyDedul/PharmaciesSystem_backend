package by.project.pharmases_system.service;

import by.project.pharmases_system.model.MedicinesInTheApplication;
import by.project.pharmases_system.model.MedicinesInTheInvoice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicinesInTheInvoiceService {
    public ResponseEntity<List<MedicinesInTheInvoice>> getListMedicinesInTheInvoices();

    public ResponseEntity<MedicinesInTheInvoice> getMedicinesInTheInvoiceById(Long id);

    public ResponseEntity<MedicinesInTheInvoice> createMedicinesInTheInvoice(MedicinesInTheInvoice medicinesInTheInvoiceBody);

    public ResponseEntity<MedicinesInTheInvoice> updateMedicinesInTheInvoice(Long id, MedicinesInTheInvoice medicinesInTheInvoiceBody);

    public ResponseEntity<MedicinesInTheInvoice> deleteMedicinesInTheInvoice(Long id);
}
