package com.perfmath.spring.soba.testing.testng;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.perfmath.spring.soba.testing.DividentCalculator;
import com.perfmath.spring.soba.testing.SimpleDividentCalculator;

public class SimpleDividentCalculatorTestNGTest {

    private DividentCalculator dividentCalculator;

    @BeforeMethod
    public void init() {
    	dividentCalculator = new SimpleDividentCalculator();
    	dividentCalculator.setDailyRate(0.001);
    }

    @DataProvider(name = "legal")
    public Object[][] createLegalDividentParameters() {
        return new Object[][] { new Object[] { 500, 30, 15.0 } };
    }

    @DataProvider(name = "illegal")
    public Object[][] createIllegalDividentParameters() {
        return new Object[][] {new Object[] { 500, -30 }, new Object[] { -500, -30 }};
    }

    @Test(dataProvider = "legal")
    public void calculate(double balance, int numOfDays, double result) {
        double divident = dividentCalculator.calculate(balance, numOfDays);
        assertEquals(divident, result);
    }

    @Test(
        dataProvider = "illegal",
        expectedExceptions = IllegalArgumentException.class)
    public void illegalCalculate(double balance, int numOfDays) {
    	dividentCalculator.calculate(balance, numOfDays);
    }
}
