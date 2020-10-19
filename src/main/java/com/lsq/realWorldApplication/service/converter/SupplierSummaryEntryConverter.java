package com.lsq.realWorldApplication.service.converter;

import com.lsq.realWorldApplication.external.SupplierSummaryEntry;
import static com.lsq.realWorldApplication.repository.InvoiceStatus.*;

import com.lsq.realWorldApplication.repository.InvoiceStatus;
import com.lsq.realWorldApplication.repository.TotalsBySupplier;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class SupplierSummaryEntryConverter {

    public static List<SupplierSummaryEntry> convertToSupplierSummary(List<TotalsBySupplier> totalsBySuppliers) {

        Set<String> suppliers = totalsBySuppliers.stream().map(TotalsBySupplier::getSupplierId).collect(Collectors.toSet());

        List<SupplierSummaryEntry> supplierSummary = new ArrayList<>();

        suppliers.forEach(supplier -> {
            TotalsBySupplier openTotal = getTotal(totalsBySuppliers, OPEN, supplier);
            TotalsBySupplier lateTotal = getTotal(totalsBySuppliers, LATE, supplier);
            SupplierSummaryEntry summaryEntry = new SupplierSummaryEntry(supplier);
            summaryEntry.totalLateInvoiceAmount = lateTotal.getTotalAmount();
            summaryEntry.totalOpenInvoiceAmount = openTotal.getTotalAmount();
            summaryEntry.lateInvoices = lateTotal.getCount().intValue();
            summaryEntry.openInvoices = openTotal.getCount().intValue();
            summaryEntry.totalInvoices = totalsBySuppliers.stream().filter(x -> {
                return x.getSupplierId().equals(supplier);
            }).map(TotalsBySupplier::getCount).reduce(BigInteger.ZERO, BigInteger::add).intValue();
            supplierSummary.add(summaryEntry);
        });
        return supplierSummary;
    }

    private static TotalsBySupplier getTotal(List<TotalsBySupplier> totalsBySuppliers, InvoiceStatus status, String supplierId) {
        Optional<TotalsBySupplier> optionalTotals = totalsBySuppliers.stream().filter(x -> {
            return x.getStatus() == status && x.getSupplierId().equals(supplierId);
        }).findAny();
        return optionalTotals.orElseGet(TotalsBySupplier::new);
    }

}