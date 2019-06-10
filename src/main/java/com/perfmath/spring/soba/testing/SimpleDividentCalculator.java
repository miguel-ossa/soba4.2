package com.perfmath.spring.soba.testing;

public class SimpleDividentCalculator implements DividentCalculator {

    private double dailyRate;

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double calculate(double balance, int numOfDays) {
        if (numOfDays <0) {
            throw new IllegalArgumentException("number of days must be positive");
        } else if (balance < 0) {
        	return 0;
        } else {
            return balance * numOfDays * dailyRate;	
        }
    }
}
