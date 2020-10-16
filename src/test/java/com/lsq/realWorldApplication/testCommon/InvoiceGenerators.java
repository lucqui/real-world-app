package com.lsq.realWorldApplication.testCommon;

import com.lsq.realWorldApplication.external.InvoiceDto;
import com.lsq.realWorldApplication.repository.Invoice;
import com.lsq.realWorldApplication.repository.InvoiceStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceGenerators {

    public static InvoiceDto generateDto() {
        return new InvoiceDto(
                RandomStringUtils.random(20),
                RandomStringUtils.random(10),
                LocalDate.now(),
                getRandomCurrencyValue(),
                getRandomInteger(),
                LocalDate.now(),
                getRandomCurrencyValue()
        );
    }

    public static Invoice generateDbInvoice() {
        return new Invoice(
                RandomStringUtils.random(20),
                RandomStringUtils.random(10),
                LocalDate.now(),
                getRandomCurrencyValue(),
                getRandomInteger(),
                LocalDate.now(),
                getRandomCurrencyValue(),
                InvoiceStatus.CLOSED
        );
    }

    public static BigDecimal getRandomCurrencyValue() {
        return new BigDecimal(RandomStringUtils.randomNumeric(6)).divide(new BigDecimal(100));
    }

    public static Integer getRandomInteger() {
        return Integer.valueOf(RandomStringUtils.randomNumeric(5));
    }

}