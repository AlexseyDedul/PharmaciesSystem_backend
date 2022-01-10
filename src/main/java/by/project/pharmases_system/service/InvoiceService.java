package by.project.pharmases_system.service;

import by.project.pharmases_system.model.Application;
import by.project.pharmases_system.model.Invoice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvoiceService {
    public ResponseEntity<List<Invoice>> getListInvoices();

    public ResponseEntity<Invoice> getInvoiceById(Long id);

    public ResponseEntity<Invoice> createInvoice(Invoice invoiceBody);

    public ResponseEntity<Invoice> updateInvoice(Long id, Invoice invoiceBody);

    public ResponseEntity<Invoice> deleteInvoice(Long id);
}
