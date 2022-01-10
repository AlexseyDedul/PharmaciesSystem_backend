package by.project.pharmases_system.service.impl;

import by.project.pharmases_system.model.Application;
import by.project.pharmases_system.model.Invoice;
import by.project.pharmases_system.repository.InvoiceRepo;
import by.project.pharmases_system.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepo invoiceRepo;

    public InvoiceServiceImpl(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    @Override
    public ResponseEntity<List<Invoice>> getListInvoices() {
        try {
            List<Invoice> listInvoices = invoiceRepo.findAll();

            if (listInvoices.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(listInvoices, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Invoice> getInvoiceById(Long id) {
        Optional<Invoice> invoiceData = invoiceRepo.findById(id);

        if(!invoiceData.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Invoice> createInvoice(Invoice invoiceBody) {
        try {
            Invoice invoice = invoiceRepo.save(new Invoice(
                    invoiceBody.getApplication(),
                    invoiceBody.getReason(),
                    invoiceBody.getFullPrice()
            ));
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Invoice> updateInvoice(Long id, Invoice invoiceBody) {
        Optional<Invoice> invoice = invoiceRepo.findById(id);

        if(!invoice.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Invoice invoiceTmp = invoice.get();
        invoiceTmp.setApplication(invoiceBody.getApplication());
        invoiceTmp.setReason(invoiceBody.getReason());
        invoiceTmp.setFullPrice(invoiceBody.getFullPrice());

        return new ResponseEntity<>(invoiceRepo.save(invoiceTmp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Invoice> deleteInvoice(Long id) {
        try {
            invoiceRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
