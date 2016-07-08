//
// Created by pparker on 7/8/2016.
//

#ifndef ASSIGNMENT9_SAVINGSACCOUNT_H
#define ASSIGNMENT9_SAVINGSACCOUNT_H


class SavingsAccount {
    // static data member annualInterestRate
    // static member function modifyInterestRate
public:
    SavingsAccount(double);
    double calculateMonthlyInterest();
    double getSavingsBalance();
    static void modifyInterestRate(double);
private:
    double savingsBalance;
    static double annualInterestRate;
};


#endif //ASSIGNMENT9_SAVINGSACCOUNT_H
