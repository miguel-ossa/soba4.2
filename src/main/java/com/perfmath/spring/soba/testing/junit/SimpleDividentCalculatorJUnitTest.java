package com.perfmath.spring.soba.testing.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.perfmath.spring.soba.testing.DividentCalculator;
import com.perfmath.spring.soba.testing.SimpleDividentCalculator;

public class SimpleDividentCalculatorJUnitTest {

    private DividentCalculator dividentCalculator;

    @Before
    public void init() {
        dividentCalculator = new SimpleDividentCalculator();
        dividentCalculator.setDailyRate(0.001);
    }

    @Test
    public void calculate() {
        double divident = dividentCalculator.calculate(500, 30);
        assertEquals(divident, 15.0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalCalculate() {
        dividentCalculator.calculate(100, -10);
    }
}
