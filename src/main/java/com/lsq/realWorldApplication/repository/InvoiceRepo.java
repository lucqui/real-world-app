package com.lsq.realWorldApplication.repository;

import com.lsq.realWorldApplication.external.PaymentSummaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, String> {
    Invoice findOneByInvoiceIdAndSupplierId(String invoiceId, String supplierId);

    @Query(nativeQuery = true, name = "getTotalsBySupplierIdList")
    List<TotalsBySupplier> getTotalsBySupplierIdList();

    @Query(nativeQuery = true, name = "getPaymentsList")
    List<PaymentSummaryEntry> getPaymentsList();
}