package com.fintech.model;

public class User {
    private String name;
    private String cpf;
    private Integer age;
    private double monthlyIncome;
    private double requestedAmount;
    private Integer installments;
    private boolean debts;
    private Integer monthsEmployed;

    public User(String name, String cpf, Integer age, double monthlyIncome, double requestedAmount,
            Integer installments, boolean debts,
            Integer monthsEmployed) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.monthlyIncome = monthlyIncome;
        this.requestedAmount = requestedAmount;
        this.installments = installments;
        this.debts = debts;
        this.monthsEmployed = monthsEmployed;

    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getAge() {
        return age;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public Integer getinstallments() {
        return installments;
    }

    public boolean getDebts() {
        return debts;
    }

    public Integer getMonthsEmployed() {
        return monthsEmployed;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public void setDebts(boolean debts) {
        this.debts = debts;
    }

    public void setMonthsEmployed(Integer monthsEmployed) {
        this.monthsEmployed = monthsEmployed;
    }
}
