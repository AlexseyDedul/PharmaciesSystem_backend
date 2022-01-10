package by.project.pharmases_system.controller;

import by.project.pharmases_system.model.Application;
import by.project.pharmases_system.model.Invoice;
import by.project.pharmases_system.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getListInvoices() {
        return invoiceService.getListInvoices().getBody();
    }

    @GetMapping("{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id).getBody();
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoiceBody) {
        return invoiceService.createInvoice(invoiceBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoiceBody) {
        return invoiceService.updateInvoice(id, invoiceBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Long id) {
        return invoiceService.deleteInvoice(id);
    }
}
