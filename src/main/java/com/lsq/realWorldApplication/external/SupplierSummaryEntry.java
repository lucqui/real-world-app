package com.lsq.realWorldApplication.external;

import java.math.BigDecimal;

public class SupplierSummaryEntry {
    public String supplierId;
    public int totalInvoices = 0;
    public int openInvoices = 0;
    public int lateInvoices = 0;
    public BigDecimal totalOpenInvoiceAmount = new BigDecimal("0");
    public BigDecimal totalLateInvoiceAmount = new BigDecimal("0");

    public SupplierSummaryEntry(String supplierId) {
        this.supplierId = supplierId;
    }
}