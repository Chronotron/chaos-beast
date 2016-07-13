#include <iostream>
#include "RationalNumber.h"

using namespace std;

int main() {
    RationalNumber rationalNumber(2, 4);
    RationalNumber rationalOtherNumber(1, 8);
    rationalNumber += rationalOtherNumber;
    const string &basic_string = rationalNumber.toString();
    cout << basic_string << endl;
    return 0;
}