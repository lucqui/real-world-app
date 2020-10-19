package com.lsq.realWorldApplication.service;

import com.lsq.realWorldApplication.external.SupplierSummaryEntry;
import com.lsq.realWorldApplication.repository.InvoiceRepo;
import com.lsq.realWorldApplication.repository.TotalsBySupplier;
import com.lsq.realWorldApplication.service.converter.SupplierSummaryEntryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceInformationService {

    @Autowired
    InvoiceRepo invoiceRepo;

    public List<SupplierSummaryEntry> getSupplierSummary() {
        List<TotalsBySupplier> totalsBySuppliers = invoiceRepo.getTotalsBySupplierIdList();
        return SupplierSummaryEntryConverter.convertToSupplierSummary(totalsBySuppliers);
    }

}