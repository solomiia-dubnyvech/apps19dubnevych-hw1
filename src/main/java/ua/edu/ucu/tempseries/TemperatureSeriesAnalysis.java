package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    double[] temperatureSeries;
    int len;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[1];
        this.len = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < -273.0) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = temperatureSeries;
        this.len = this.temperatureSeries.length;
    }

    public double average() {
        if (this.len == 0){
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (int i = 0; i < this.len; i++){
            sum += this.temperatureSeries[i];
        }
        double average = sum / this.len;
        return average;
    }

    public double deviation() {
        if (this.len == 0){
            throw new IllegalArgumentException();
        }
        double deviation = 0;
        for (int i = 0; i < this.len; i++) {
            deviation += Math.pow(this.temperatureSeries[i] - this.average(),2);
        }
        deviation /= this.len;
        return Math.sqrt(deviation);
    }

    public double min() {
        if (this.len == 0){
            throw new IllegalArgumentException();
        }
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < this.len; i++) {
            if (this.temperatureSeries[i] < min){
                min = this.temperatureSeries[i];
            }
        }
        return min;
    }

    public double max() {
        if (this.len == 0) {
            throw new IllegalArgumentException();
        }
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < this.len; i++) {
            if (this.temperatureSeries[i] > max){
                max = this.temperatureSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (this.len == 0){
            throw new IllegalArgumentException();
        }
        double closest = Integer.MAX_VALUE;
        for (int i = 0; i < this.len; i++) {
            if (Math.abs(this.temperatureSeries[i]) < Math.abs(closest)){
                closest = this.temperatureSeries[i];
            }
            else if (Math.abs(this.temperatureSeries[i]) == Math.abs(closest)){
                if (this.temperatureSeries[i] > closest){
                    closest = this.temperatureSeries[i];
                }
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.len == 0){
            throw new IllegalArgumentException();
        }
        double closest = Integer.MAX_VALUE;
        for (int i = 0; i < this.len; i++) {
            if (Math.abs(this.temperatureSeries[i] - tempValue) < Math.abs(closest - tempValue)){
                closest = this.temperatureSeries[i];
            }
            else if (Math.abs(this.temperatureSeries[i] - tempValue) == Math.abs(closest - tempValue)){
                if (this.temperatureSeries[i] > closest){
                    closest = this.temperatureSeries[i];
                }
            }
        }
        return closest;
    }

    public double[] findTempsLessThan(double tempValue) {
        if (this.len == 0){
            throw new IllegalArgumentException();
        }
        int n = 0;
        for (int i = 0; i < this.len; i++) {
            if (this.temperatureSeries[i] < tempValue){
                n++;
            }
        }
        double[] lessValues = new double[n];
        int j = 0;
        for (int i = 0; i < this.len; i++) {
            if (this.temperatureSeries[i] < tempValue){
                lessValues[j] = this.temperatureSeries[i];
                j++;
            }
        }
        return lessValues;
    }

    public double[] findTempsGreaterThan(double tempValue) {
        if (this.len == 0){
            throw new IllegalArgumentException();
        }
        int n = 0;
        for (int i = 0; i < this.len; i++) {
            if (this.temperatureSeries[i] > tempValue){
                n++;
            }
        }
        double[] greaterValues = new double[n];
        int j = 0;
        for (int i = 0; i < this.len; i++) {
            if (this.temperatureSeries[i] > tempValue){
                greaterValues[j] = this.temperatureSeries[i];
                j++;
            }
        }
        return greaterValues;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.len == 0) {
            throw new IllegalArgumentException();
        }
        double avgTemp = this.average();
        double devTemp = this.deviation();
        double minTemp = this.min();
        double maxTemp = this.max();
        return new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);
    }

    public int addTemps(double... temps) {
        for (int i = 0; i < temps.length; i++) {
            if (this.temperatureSeries.length == this.len) {
                double[] buffer = new double[this.temperatureSeries.length * 2];
                for (int j = 0; j < this.len; j++) {
                    buffer[j] = this.temperatureSeries[j];
                    this.temperatureSeries = buffer;
                }
            }
            this.temperatureSeries[this.len] = temps[i];
            this.len++;
        }
        return this.len;
    }
}
