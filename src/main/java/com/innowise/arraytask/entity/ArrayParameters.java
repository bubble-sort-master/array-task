package com.innowise.arraytask.warehouse;

public class ArrayParameters {
    private final int sum;
    private final double average;
    private final int min;
    private final int max;
    private final int count;

    public ArrayParameters(int sum, double average, int min, int max, int count) {
        this.sum = sum;
        this.average = average;
        this.min = min;
        this.max = max;
        this.count = count;
    }

    // Геттеры
    public int getSum() { return sum; }
    public double getAverage() { return average; }
    public int getMin() { return min; }
    public int getMax() { return max; }
    public int getCount() { return count; }

    @Override
    public String toString() {
        return String.format("ArrayParameters{sum=%d, average=%.2f, min=%d, max=%d, count=%d}",
                sum, average, min, max, count);
    }
}