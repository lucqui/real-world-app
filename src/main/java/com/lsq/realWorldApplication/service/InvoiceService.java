package com.lsq.realWorldApplication.service;

import com.lsq.realWorldApplication.service.converter.InvoiceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//TODO write spock specification
public class InvoiceService {

    @Autowired
    InvoiceOldRepo invoiceOldRepo;

    @Autowired
    InvoiceConverter invoiceConverter;

    void saveInvoice(com.lsq.realWorldApplication.business.InvoiceOld invoiceOld) {
        InvoiceOld matching = invoiceOldRepo.findByErpNumber(invoiceOld.getErpNumber());
        if(matching != null) {
            throw new IllegalArgumentException("Invoice with erpNumber: " + invoiceOld.getErpNumber() + " already exists");
        }
        invoiceOldRepo.saveAndFlush(invoiceConverter.convertToModel(invoiceOld));
    }
}