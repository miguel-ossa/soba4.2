package com.perfmath.spring.soba.testing;

public interface DividentCalculator {
    public void setDailyRate(double dailyRate);
    public double calculate(double balance, int numOfDays);
}
