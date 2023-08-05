package com.education_ministry.m3s_plus.model;

public class Values {

    private String prediction;
    private int step;
    private int value;

    public Values(String prediction, int value) {
        this.prediction = prediction;
        this.value = value;
    }

    public Values(int step,String prediction, int value) {
        this.step=step;
        this.prediction = prediction;
        this.value = value;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public int getStep() { return step; }

    public void setStep(int step) { this.step = step; }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

