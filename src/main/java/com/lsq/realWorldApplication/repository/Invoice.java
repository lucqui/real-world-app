package com.lsq.realWorldApplication.repository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    String id = UUID.randomUUID().toString();

    String supplierId;
    String invoiceId;
    LocalDate invoiceDate;
    BigDecimal amount;
    Integer terms;
    LocalDate paymentDate;
    BigDecimal paymentAmount;

    @Enumerated(EnumType.STRING)
    InvoiceStatus status;

    public Invoice(String supplierId, String invoiceId, LocalDate invoiceDate, BigDecimal amount, Integer terms, LocalDate paymentDate, BigDecimal paymentAmount, InvoiceStatus status) {
        this.supplierId = supplierId;
        this.invoiceId = invoiceId;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
        this.terms = terms;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.status = status;
    }

    public Invoice() {}

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

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

}