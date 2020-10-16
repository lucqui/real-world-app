package com.lsq.realWorldApplication.service.converter;

import com.lsq.realWorldApplication.external.InvoiceDto;
import com.lsq.realWorldApplication.repository.Invoice;
import com.lsq.realWorldApplication.repository.InvoiceStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InvoiceConverter {

    public Invoice convertToDbModel(InvoiceDto dto) {
        return new Invoice(
                dto.getSupplierId(),
                dto.getInvoiceId(),
                dto.getInvoiceDate(),
                dto.getInvoiceAmount(),
                dto.getTerms(),
                dto.getPaymentDate(),
                dto.getPaymentAmount(),
                determineStatus(dto)
        );
    }

    public Invoice updateExistingInvoice(InvoiceDto dto, Invoice existing) {
        existing.setAmount(dto.getInvoiceAmount());
        existing.setInvoiceDate(dto.getInvoiceDate());
        existing.setTerms(dto.getTerms());
        existing.setPaymentDate(dto.getPaymentDate());
        existing.setPaymentAmount(dto.getPaymentAmount());
        existing.setStatus(determineStatus(dto));
        return existing;
    }

    public InvoiceStatus determineStatus(InvoiceDto dto) {
        // Open (invoice is unpaid/partially paid)
        //Closed (invoice is paid in full, payment date in the past)
        //Late (invoice has past due date without payment)
        //Payment Scheduled (invoice has payment set for future date)

        if (isInvoicePaidInFull(dto)) {
            return InvoiceStatus.CLOSED;
        } else if (isUnpaidInvoicePastDue(dto)) {
            return InvoiceStatus.LATE;
        } else if (isUnpaidInvoiceScheduledForFuturePayment(dto)) {
            return InvoiceStatus.PAYMENT_SCHEDULED;
        } else {
            return InvoiceStatus.OPEN;
        }
    }

    private boolean isInvoicePaidInFull(InvoiceDto invoice) {
        if (invoice.getPaymentAmount() == null) {
            return false;
        }
        return invoice.getPaymentAmount().equals(invoice.getInvoiceAmount());
    }

    private boolean isUnpaidInvoicePastDue(InvoiceDto invoice) {
        return invoice.getPaymentDate() == null && invoice.getInvoiceDate().compareTo(LocalDate.now()) < 0;
    }

    private boolean isUnpaidInvoiceScheduledForFuturePayment(InvoiceDto invoice) {
        return invoice.getPaymentDate().compareTo(LocalDate.now()) > 0;
    }

}