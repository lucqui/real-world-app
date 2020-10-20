package com.lsq.realWorldApplication.external;

import com.lsq.realWorldApplication.repository.InvoiceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class InvoiceSummaryEntry {
    public String supplierId;
    public String invoiceId;
    public InvoiceStatus status;
    public LocalDate invoiceDate;
    public BigDecimal invoiceAmount;
    public LocalDate dueDate;
    public int daysPastDueDate;
    public LocalDate paymentDate;
    public BigDecimal paymentAmount;

    public InvoiceSummaryEntry(String supplierId, String invoiceId, InvoiceStatus status, LocalDate invoiceDate, BigDecimal invoiceAmount, int terms, LocalDate paymentDate, BigDecimal paymentAmount) {
        this.supplierId = supplierId;
        this.invoiceId = invoiceId;
        this.status = status;
        this.invoiceDate = invoiceDate;
        this.invoiceAmount = invoiceAmount;
        this.dueDate = invoiceDate.plusMonths(terms);

        this.daysPastDueDate = Period.between(LocalDate.now(), dueDate).getDays();
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
}