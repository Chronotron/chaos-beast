#include <iostream>
#include "RationalNumber.h"

using namespace std;

int main() {
    RationalNumber rationalNumber(2, 4);
    cout << "Initial: " << rationalNumber.toString() << endl;
    RationalNumber rationalOtherNumber(1, 8);
    cout << "Other: " << rationalOtherNumber.toString() << endl;

    rationalNumber += rationalOtherNumber;
    cout << "Initial += Other: " << rationalNumber.toString() << endl;

    RationalNumber thirdNumber = rationalNumber + rationalNumber;
    cout << "Initial + Initial: " << thirdNumber.toString() << endl;
    cout << "Initial: " << rationalNumber.toString() << endl;

    RationalNumber minus = rationalNumber - rationalNumber;
    cout << "Initial - Initial: " << minus.toString() << endl;

    RationalNumber number1 = RationalNumber(1, 2);
    RationalNumber multi = number1 * number1;
    cout << "1/2 * 1/2 = " << multi.toString() << endl;

    RationalNumber div = number1 / number1;
    cout << "1/2 / 1/2 = " << div.toString() << endl;

    bool equal = number1 == RationalNumber(2, 4);
    cout << "1/2 == 2/4: " << equal << endl;

    RationalNumber number = RationalNumber(3, 4);
    bool notEqual = number1 == number;
    cout << "1/2 == 2/4: " << notEqual << endl;

    bool lessThan = number1 < number;
    cout << "1/2 < 3/4: " << lessThan << endl;

    RationalNumber number2 = RationalNumber(2, 2);
    bool notLessThan = number2 < number;
    cout << "2/2 < 3/4: " << notLessThan << endl;

    bool lessThanOrEqual = number1 <= number;
    cout << "1/2 <= 3/4: " << lessThanOrEqual << endl;

    lessThanOrEqual = number1 <= RationalNumber(2, 4);
    cout << "1/2 <= 2/4: " << lessThanOrEqual << endl;

    bool notLessThanOrEqual = number2 <= number;
    cout << "2/2 <= 3/4: " << notLessThanOrEqual << endl;
    return 0;
}