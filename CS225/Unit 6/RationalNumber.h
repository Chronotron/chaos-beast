//
// Created by pparker on 7/12/2016.
//

#include <iostream>
using namespace std;

#ifndef ASSIGNMENT10_RATIONALNUMBER_H
#define ASSIGNMENT10_RATIONALNUMBER_H


class RationalNumber {
public:
    RationalNumber(int, int);
    string toString();
    RationalNumber &operator+=(const RationalNumber &);
    RationalNumber operator+(const RationalNumber &);
private:
    int denominator;
    int numerator;
    void setDenominator(int);
    void setNumerator(int);
    void simplify();
};


#endif //ASSIGNMENT10_RATIONALNUMBER_H
