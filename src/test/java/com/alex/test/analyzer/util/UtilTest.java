package com.alex.test.analyzer.util;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UtilTest {

    @Test
    public void getExcludeListTest() {
        assertNotNull(Util.getExcludeList());
    }

    @Test
    public void readFileTest() {

    }

    @Test
    public void getInverseSignTest() {
        assertEquals("[",Util.getInverseSign("]"));
        assertEquals("{",Util.getInverseSign("}"));
        assertEquals("(",Util.getInverseSign(")"));
        assertEquals("",Util.getInverseSign(">"));
    }
}