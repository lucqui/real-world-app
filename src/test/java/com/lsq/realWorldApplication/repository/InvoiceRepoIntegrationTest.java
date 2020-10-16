package com.lsq.realWorldApplication.repository;

import com.lsq.realWorldApplication.testCommon.InvoiceGenerators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = {InvoiceRepoIntegrationTestConfig.class, FlywayAutoConfiguration.FlywayConfiguration.class})
class InvoiceRepoIntegrationTest {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Test
    void saveAndRetrieve() {
        Invoice invoice = InvoiceGenerators.generateDbInvoice();

        invoice = invoiceRepo.saveAndFlush(invoice);

        assert invoiceRepo.findById(invoice.id).isPresent();
        assert invoiceRepo.findAll().size() == 1;
    }

    @Test
    void saveAndFindByInvoiceIdAndSupplierId() {
        Invoice invoice = InvoiceGenerators.generateDbInvoice();

        invoice = invoiceRepo.saveAndFlush(invoice);

        assert invoiceRepo.findOneByInvoiceIdAndSupplierId(invoice.invoiceId, invoice.supplierId) != null;
        assert invoiceRepo.findAll().size() == 1;
    }

}