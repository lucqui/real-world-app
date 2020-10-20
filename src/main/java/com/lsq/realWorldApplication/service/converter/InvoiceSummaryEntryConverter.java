package com.lsq.realWorldApplication.service.converter;

import com.lsq.realWorldApplication.external.InvoiceSummaryEntry;
import com.lsq.realWorldApplication.repository.Invoice;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InvoiceSummaryEntryConverter {

    public static List<InvoiceSummaryEntry> convertToInvoiceSummaryList(List<Invoice> invoices) {
        return invoices.stream().map(converter).collect(Collectors.toList());
    }

    private static final Function<Invoice,InvoiceSummaryEntry> converter = inv -> {
        return new InvoiceSummaryEntry(
                inv.getSupplierId(),
                inv.getInvoiceId(),
                inv.getStatus(),
                inv.getInvoiceDate(),
                inv.getAmount(),
                inv.getTerms(),
                inv.getPaymentDate(),
                inv.getPaymentAmount()
        );
    };

}