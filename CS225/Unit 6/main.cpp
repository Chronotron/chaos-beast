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

    RationalNumber multi = RationalNumber(1, 2) * RationalNumber(1, 2);
    cout << "1/2 * 1/2 = " << multi.toString() << endl;

    RationalNumber div = RationalNumber(1, 2) / RationalNumber(1, 2);
    cout << "1/2 / 1/2 = " << div.toString() << endl;
    return 0;
}