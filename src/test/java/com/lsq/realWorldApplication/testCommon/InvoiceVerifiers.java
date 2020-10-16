package com.lsq.realWorldApplication.testCommon;

import com.lsq.realWorldApplication.external.InvoiceDto;
import static org.junit.jupiter.api.Assertions.*;


public class InvoiceVerifiers {

    public static void verify(InvoiceDto incoming, InvoiceDto outgoing) {
        assertEquals(incoming.getUniqueKey(), outgoing.getUniqueKey());
        //TODO add more fields
    }
}
