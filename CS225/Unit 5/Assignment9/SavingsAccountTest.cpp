#include <iostream>
#include "SavingsAccount.h"

using namespace std;

int main() {
    // saver1: 2000.00,saver2: 3000.00
    SavingsAccount saver1(2000.00);
    SavingsAccount saver2(3000.00);

    // set annual interest rate to 3%
    SavingsAccount::modifyInterestRate(0.03);
    // calculate balances
    cout << "Current annual interest rate: 3%" << endl;
    cout << "Saver 1, Month 1 balance before interest: " << saver1.getSavingsBalance() << endl;;
    saver1.calculateMonthlyInterest();
    cout << "Saver 1, Month 1 balance after interest: " << saver1.getSavingsBalance() << endl;;

    cout << "Saver 2, Month 1 balance before interest: " << saver2.getSavingsBalance() << endl;;
    saver2.calculateMonthlyInterest();
    cout << "Saver 2, Month 1 balance after interest: " << saver2.getSavingsBalance() << endl;;

    // set annual interest rate to 4%
    SavingsAccount::modifyInterestRate(0.04);

    cout << "New annual interest rate: 4%" << endl;
    saver1.calculateMonthlyInterest();
    cout << "Saver 1, Month 2 balance after interest: " << saver1.getSavingsBalance() << endl;;

    saver2.calculateMonthlyInterest();
    cout << "Saver 2, Month 2 balance after interest: " << saver2.getSavingsBalance() << endl;;
    return 0;
}