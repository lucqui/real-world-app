package com.lsq.realWorldApplication.service;

import com.lsq.realWorldApplication.external.BasicResponse;
import com.lsq.realWorldApplication.external.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class InvoiceDigestor {

    @Autowired
    InvoiceProcessor invoiceProcessor;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    String header = "Supplier Id,Invoice Id,Invoice Date,Invoice Amount,Terms,Payment Date,Payment Amount\n";

    public BasicResponse processFile(InputStream inputStream) {
        List<InvoiceDto> invoices = parseStream(inputStream);

        try {
            validate(invoices);
        } catch (IllegalArgumentException ex) {
            return new BasicResponse(false, ex.getMessage());
        }

        invoices.forEach( inv -> {
            invoiceProcessor.process(inv);
        });

        return new BasicResponse(true);
    }

    protected void validate(List<InvoiceDto> invoices) {
        //TODO javax.validation
        List<InvoiceDto> uniqueInvoices = invoices.stream().filter(distinctByKey(InvoiceDto::getUniqueKey)).collect(Collectors.toList());
        if(uniqueInvoices.size() < invoices.size()) {
            //TODO could return ids
            throw new IllegalArgumentException("There are duplicates in batch");
        }
    }

    protected static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    protected List<InvoiceDto> parseStream(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);
        List<InvoiceDto> invoices = new ArrayList<>();
        while (sc.hasNextLine()) {
            String lineString = sc.nextLine();
            System.out.println("nextLine is: " + lineString);

            if (lineString.contains("Supplier Id")) {
                System.out.println("We've reached the header... Will now process rows...");
            } else if(lineString.trim().length() == 0) {
                break;
            } else {
                InvoiceDto invoice = parseLine(lineString);
                invoices.add(invoice);
            }
        }
        return invoices;
    }

    protected InvoiceDto parseLine(String lineString) {
        String[] line = lineString.split(",");
        //TODO yuck! Make this easier to read
        return new InvoiceDto(
                nullSafeAccessor(line, 0),
                nullSafeAccessor(line, 1),
                nullSafeDateParser(nullSafeAccessor(line, 2)),
                safeBigDecimalParser(nullSafeAccessor(line, 3)),
                Integer.parseInt(nullSafeAccessor(line, 4)),
                nullSafeDateParser(nullSafeAccessor(line, 5)),
                safeBigDecimalParser(nullSafeAccessor(line, 6))
        );
    }

    protected String nullSafeAccessor(String[] data, int idx) {
        try {
            System.out.println(data + ", " + idx +", value: [" + data[idx] + "]");
            return data[idx].trim();
        } catch(ArrayIndexOutOfBoundsException ex) {
            return "";
        }
    }

    protected LocalDate nullSafeDateParser(String dateString) {
        try {
            return LocalDate.parse(dateString, formatter);
        } catch(DateTimeParseException ex) {
            return null;
        }
    }

    protected BigDecimal safeBigDecimalParser(String numeric) {
        try {
            return new BigDecimal(numeric);
        } catch(Exception ex) {
            return null;
        }
    }

}