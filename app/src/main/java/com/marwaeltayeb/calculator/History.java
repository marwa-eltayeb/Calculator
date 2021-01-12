package com.marwaeltayeb.calculator;

public class History {

    private String firstNumber;
    private String operator;
    private String secondNumber;
    private String result;

    public History(String firstNumber, String operator,String secondNumber, String result) {
        this.firstNumber = firstNumber;
        this.operator = operator;
        this.secondNumber = secondNumber;
        this.result = result;
    }

    public String getFirstNumber() {
        return firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public String getResult() {
        return result;
    }
}
