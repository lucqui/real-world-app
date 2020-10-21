package com.lsq.realWorldApplication.external;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentSummaryEntry {
    //ListPayments (only closed invoices, sorted by payment date descending)
    //
    //    Supplier Id (group by supplier)
    //    Payment Date (group by payment date)
    //    Payment Amount (sum of payments made on given payment date)
    //    Number of Invoices Paid (count of invoices in payment)

    public PaymentSummaryEntry(String supplierId, LocalDate paymentDate, BigDecimal paymentAmount, Integer numberOfInvoicesPaid) {
        this.supplierId = supplierId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.numberOfInvoicesPaid = numberOfInvoicesPaid;
    }

    public String supplierId;
    public LocalDate paymentDate;
    public BigDecimal paymentAmount;
    public Integer numberOfInvoicesPaid;
}