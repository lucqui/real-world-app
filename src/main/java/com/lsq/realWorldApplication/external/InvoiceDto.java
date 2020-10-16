package com.lsq.realWorldApplication.external;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceDto {
    String supplierId;
    String invoiceId;
    LocalDate invoiceDate;
    BigDecimal invoiceAmount;
    Integer terms;
    LocalDate paymentDate;
    BigDecimal paymentAmount;

    public InvoiceDto(String supplierId, String invoiceId, LocalDate invoiceDate, BigDecimal invoiceAmount, Integer terms, LocalDate paymentDate, BigDecimal paymentAmount) {
        this.supplierId = supplierId;
        this.invoiceId = invoiceId;
        this.invoiceDate = invoiceDate;
        this.invoiceAmount = invoiceAmount;
        this.terms = terms;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getUniqueKey() {
        return this.supplierId + this.invoiceId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Integer getTerms() {
        return terms;
    }

    public void setTerms(Integer terms) {
        this.terms = terms;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}