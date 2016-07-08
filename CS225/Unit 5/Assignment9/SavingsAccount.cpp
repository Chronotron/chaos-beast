//
// Created by pparker on 7/8/2016.
//

#include "SavingsAccount.h"

double SavingsAccount::annualInterestRate = 0.0;

void SavingsAccount::modifyInterestRate(double newRate) {
    annualInterestRate = newRate >= 0.0 ? newRate : 0.0;
}

SavingsAccount::SavingsAccount(double initialBalance) {
    savingsBalance = initialBalance >= 0.0 ? initialBalance : 0.0;
}

double SavingsAccount::getSavingsBalance() {
    return savingsBalance;
}

double SavingsAccount::calculateMonthlyInterest() {
    double interest = savingsBalance * annualInterestRate / 12;
    savingsBalance += interest;
    return savingsBalance;
}