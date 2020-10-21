package com.lsq.realWorldApplication.controller;

import com.lsq.realWorldApplication.external.BasicResponse;
import com.lsq.realWorldApplication.external.InvoiceSummaryEntry;
import com.lsq.realWorldApplication.external.PaymentSummaryEntry;
import com.lsq.realWorldApplication.external.SupplierSummaryEntry;
import com.lsq.realWorldApplication.service.InvoiceDigestor;
import com.lsq.realWorldApplication.service.InvoiceInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceDigestor invoiceDigestor;

    @Autowired
    InvoiceInformationService informationService;

    @PostMapping("/digest")
    public BasicResponse digestInvoiceCsv(@RequestParam("file") MultipartFile file) throws IOException {
        return invoiceDigestor.processFile(file.getInputStream());
    }

    @GetMapping("/supplier/summary")
    public BasicResponse<SupplierSummaryEntry> getSupplierSummaries() {
        return new BasicResponse<SupplierSummaryEntry>(informationService.getSupplierSummary());
    }

    @GetMapping("/summary")
    public BasicResponse<InvoiceSummaryEntry> getInvoiceSummaries(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "100", name="pageSize") Integer pageSize) {
        return new BasicResponse<InvoiceSummaryEntry>(informationService.getInvoiceSummary(pageSize, page));
    }

    @GetMapping("/payment/summary")
    public BasicResponse<PaymentSummaryEntry> getPaymentSummaries() {
        return new BasicResponse(informationService.getPayments());
    }

}