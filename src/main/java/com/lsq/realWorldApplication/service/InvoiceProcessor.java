package com.lsq.realWorldApplication.service;

import com.lsq.realWorldApplication.external.InvoiceDto;
import com.lsq.realWorldApplication.repository.Invoice;
import com.lsq.realWorldApplication.repository.InvoiceRepo;
import com.lsq.realWorldApplication.service.converter.InvoiceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProcessor {

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    InvoiceConverter converter;

    @Async
    void process(InvoiceDto invoiceDto) {

        Invoice existing = invoiceRepo.findOneByInvoiceIdAndSupplierId(invoiceDto.getInvoiceId(), invoiceDto.getSupplierId());

        Invoice invoice;
        if(existing != null) {
            invoice = converter.updateExistingInvoice(invoiceDto, existing);
        } else {
            invoice = converter.convertToDbModel(invoiceDto);
        }
        invoiceRepo.saveAndFlush(invoice);

    }

}