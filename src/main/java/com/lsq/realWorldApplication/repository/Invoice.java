package com.lsq.realWorldApplication.repository;

import com.lsq.realWorldApplication.external.PaymentSummaryEntry;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "invoice")
@SqlResultSetMapping(
        name="totalsBySupplier",
        classes = @ConstructorResult(
                targetClass = TotalsBySupplier.class,
                columns={
                        @ColumnResult(name="invoiceStatus", type=String.class),
                        @ColumnResult(name="supplierId", type=String.class),
                        @ColumnResult(name="count", type= BigInteger.class),
                        @ColumnResult(name="totalAmount", type=BigDecimal.class)
                }))
@NamedNativeQuery(
        name="getTotalsBySupplierIdList",
        query="select supplier_id as supplierId, sum(1) as count, status as invoiceStatus, sum(amount) as totalAmount from invoice group by supplier_id, status order by supplier_id limit 1000",
        resultSetMapping = "totalsBySupplier",
        resultClass = TotalsBySupplier.class
)
@SqlResultSetMapping(
        name="paymentSummary",
        classes = @ConstructorResult(
        targetClass = PaymentSummaryEntry.class,
                columns = {
                        @ColumnResult(name="supplierId", type=String.class),
                        @ColumnResult(name="paymentDate", type=LocalDate.class),
                        @ColumnResult(name="paymentAmount", type=BigDecimal.class),
                        @ColumnResult(name="numberOfInvoicesPaid", type=Integer.class)
                }
        )
)
@NamedNativeQuery(
        name="getPaymentsList",
        query="select supplier_id as supplierId, payment_date as paymentDate, sum(amount) as paymentAmount, sum(1) as numberOfInvoicesPaid from invoice group by supplier_id, payment_date order by paymentDate desc",
        resultSetMapping = "paymentSummary",
        resultClass = PaymentSummaryEntry.class
)
public class Invoice {

    @Id
//    @GeneratedValue
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