package com.lsq.realWorldApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<Invoice, String> {
    Invoice findOneByInvoiceIdAndSupplierId(String invoiceId, String supplierId);
}