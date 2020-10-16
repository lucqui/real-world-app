package com.lsq.realWorldApplication.controller;

import com.lsq.realWorldApplication.external.BasicResponse;
import com.lsq.realWorldApplication.service.InvoiceDigestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceDigestor invoiceDigestor;

    @PostMapping("/digest")
    public BasicResponse digestInvoiceCsv(@RequestParam("file") MultipartFile file) throws IOException {
        return invoiceDigestor.processFile(file.getInputStream());
    }

}