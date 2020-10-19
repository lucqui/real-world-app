package com.lsq.realWorldApplication.repository;

import com.lsq.realWorldApplication.testCommon.InvoiceGenerators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

    @Test
    public void shouldReturnCorrectSummary() {
        List<Invoice> invoices = new ArrayList<>();

        for(int idx = 0; idx < 10; idx++){
            invoices.add(InvoiceGenerators.generateDbInvoice());
        }

        invoiceRepo.saveAll(invoices);
        invoiceRepo.flush();

        List<TotalsBySupplier> summaryEntries = invoiceRepo.getTotalsBySupplierIdList();

        Set<String> supplierIds = invoices.stream().map(inv -> { return inv.getSupplierId(); }).collect(Collectors.toSet());
        Set<String> supplierIdsFromResult = summaryEntries.stream().map(summary -> { return summary.getSupplierId(); }).collect(Collectors.toSet());

        assert supplierIds.equals(supplierIdsFromResult);
    }

}