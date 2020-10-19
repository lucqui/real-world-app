package com.lsq.realWorldApplication.service.converter;

import com.lsq.realWorldApplication.external.SupplierSummaryEntry;
import com.lsq.realWorldApplication.repository.InvoiceStatus;
import com.lsq.realWorldApplication.repository.TotalsBySupplier;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SupplierSummaryEntryConverterTest {

    public static final String SUPPLIER_ID = "SUPPLIER_ID";

    @Test
    void testConvertToSupplierSummary() {
        List<TotalsBySupplier> totals = new ArrayList<>(){{
            add(new TotalsBySupplier(InvoiceStatus.CLOSED, SUPPLIER_ID, new BigInteger("15"), new BigDecimal("5.05")));
            add(new TotalsBySupplier(InvoiceStatus.OPEN, SUPPLIER_ID, new BigInteger("100"), new BigDecimal("1.01")));
            add(new TotalsBySupplier(InvoiceStatus.LATE, SUPPLIER_ID, new BigInteger("200"), new BigDecimal("2.02")));
            add(new TotalsBySupplier(InvoiceStatus.LATE, "different", new BigInteger("1"), new BigDecimal("1")));
        }};

        List<SupplierSummaryEntry> result = SupplierSummaryEntryConverter.convertToSupplierSummary(totals);
        assert result.size() == 2;
        assert result.get(0).supplierId.equals(SUPPLIER_ID);
        assert result.get(0).totalInvoices == 315;
        assert result.get(0).openInvoices == 100;
        assert result.get(0).lateInvoices == 200;
        assert result.get(0).totalOpenInvoiceAmount.doubleValue() == 1.01;
        assert result.get(0).totalLateInvoiceAmount.doubleValue() == 2.02;

        assert result.get(1).openInvoices == 0;
    }

}