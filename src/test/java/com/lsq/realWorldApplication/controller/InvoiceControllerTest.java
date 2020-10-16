package com.lsq.realWorldApplication.controller;

import com.lsq.realWorldApplication.external.BasicResponse;
import com.lsq.realWorldApplication.service.InvoiceDigestor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import static org.mockito.Mockito.*;

class InvoiceControllerTest {

    @Test
    public void testDigestInvoiceCsv_happy() throws IOException {
        testDigestInvoiceCsv(false);
    }

    @Test
    public void testDigestInvoiceCsv_bad() throws IOException {
        testDigestInvoiceCsv(true);
    }

    public void testDigestInvoiceCsv(boolean throwsException) throws IOException {
        InvoiceController invoiceController = new InvoiceController();
        invoiceController.invoiceDigestor = mock(InvoiceDigestor.class);

                if(throwsException) {
                    when(invoiceController.invoiceDigestor.processFile(any(InputStream.class)))
                            .thenThrow(new IllegalArgumentException("Something happened!"));
                } else {
                    when(invoiceController.invoiceDigestor.processFile(any(InputStream.class)))
                            .thenReturn(new BasicResponse(true));
                }
        MultipartFile file = new MockMultipartFile("test", new byte[]{});
        try {
            assert invoiceController.digestInvoiceCsv(file) != null;
        } catch (Exception ex) {
            assert throwsException;
        }
    }

}