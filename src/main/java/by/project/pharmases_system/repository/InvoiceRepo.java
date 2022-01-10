package by.project.pharmases_system.repository;

import by.project.pharmases_system.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
}
