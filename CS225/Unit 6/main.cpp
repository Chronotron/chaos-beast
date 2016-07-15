#include <iostream>
#include "RationalNumber.h"

using namespace std;

void expect(bool, bool);

int main() {

    cout << RationalNumber(6, 9).toString() << endl;
    cout << RationalNumber(11, 13).toString() << endl;
    cout << RationalNumber(7, 27).toString() << endl;
    cout << RationalNumber(7, 28).toString() << endl;
    cout << RationalNumber(11, 33).toString() << endl;
    cout << RationalNumber(6, 1024).toString() << endl;
    cout << RationalNumber(99, 198).toString() << endl;

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

    RationalNumber oneHalf = RationalNumber(1, 2);
    RationalNumber multi = oneHalf * oneHalf;
    cout << "1/2 * 1/2 = " << multi.toString() << endl;

    RationalNumber div = oneHalf / oneHalf;
    cout << "1/2 / 1/2 = " << div.toString() << endl;

    bool equal = oneHalf == RationalNumber(2, 4);
    expect(true, equal);
    cout << "1/2 == 2/4: " << equal << endl;

    RationalNumber threeFourths = RationalNumber(3, 4);
    bool notEqual = oneHalf == threeFourths;
    expect(false, notEqual);
    cout << "1/2 == 2/4: " << notEqual << endl;

    bool lessThan = oneHalf < threeFourths;
    expect(true, lessThan);
    cout << "1/2 < 3/4: " << lessThan << endl;

    RationalNumber twoOverTwo = RationalNumber(2, 2);
    bool notLessThan = twoOverTwo < threeFourths;
    expect(false, notLessThan);
    cout << "2/2 < 3/4: " << notLessThan << endl;

    bool lessThanOrEqual = oneHalf <= threeFourths;
    expect(true, lessThanOrEqual);
    cout << "1/2 <= 3/4: " << lessThanOrEqual << endl;

    lessThanOrEqual = oneHalf <= RationalNumber(2, 4);
    expect(true, lessThanOrEqual);
    cout << "1/2 <= 2/4: " << lessThanOrEqual << endl;

    bool notLessThanOrEqual = twoOverTwo <= threeFourths;
    expect(false, notLessThanOrEqual);
    cout << "2/2 <= 3/4: " << notLessThanOrEqual << endl;
    return 0;
}

void expect(bool expected, bool actual) {
    if(expected != actual) {
        throw 1;
    }
}