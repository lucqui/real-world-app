package com.lsq.realWorldApplication.service;

import com.lsq.realWorldApplication.external.InvoiceDto;
import com.lsq.realWorldApplication.testCommon.InvoiceGenerators;
import com.lsq.realWorldApplication.testCommon.InvoiceVerifiers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDigestorTests {
    InvoiceDigestor invoiceDigestor = new InvoiceDigestor();

    @Test
    void testParseLine() {
        InvoiceDto expected = new InvoiceDto(
                "supplier_1",
                "CC6F8CE3-37BA-4E9B-B264-2673A0A353BA",
                LocalDate.now(),
                new BigDecimal(1818.34),
                90,
                LocalDate.now(),
                new BigDecimal(1818.34)
            );
        String line = "supplier_1,CC6F8CE3-37BA-4E9B-B264-2673A0A353BA,2020-03-25,1818.34,90,2020-06-23,1818.34";
        InvoiceDto invoiceDto = invoiceDigestor.parseLine(line);
        InvoiceVerifiers.verify(invoiceDto, expected);
    }

    @Test
    void testValidate_happy() {
        List<InvoiceDto> invoices = new ArrayList<>();
        for(int idx = 0; idx < 10; idx++) {
            invoices.add(InvoiceGenerators.generateDto());
        }

        invoiceDigestor.validate(invoices);
    }

    @Test
    void testValidate_dups() {
        boolean exceptionThrown = false;
        List<InvoiceDto> invoices = new ArrayList<>();
        for(int idx = 0; idx < 10; idx++) {
            InvoiceDto inv = InvoiceGenerators.generateDto();
            inv.setInvoiceId("REPEATING_INVOICE_ID");
            inv.setSupplierId("REPEATING_SUPPLIER_ID");
            invoices.add(inv);
        }

        try {
            invoiceDigestor.validate(invoices);
        } catch (Exception ex) {
            assert ex.getMessage().contains("There are duplicates in batch");
            exceptionThrown = true;
        }
        assert exceptionThrown;
    }

    @Test
    void testProcess() {
        //mock fileReading
    }

    //TODO mock streams and test

}