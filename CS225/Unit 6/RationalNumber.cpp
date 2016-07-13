//
// Created by pparker on 7/12/2016.
//

#include "RationalNumber.h"
#include <cstdlib>

int gcd(int, int);

RationalNumber::RationalNumber(int numerator, int denominator) {
    this->numerator = numerator;
    this->denominator = denominator != 0 ? abs(denominator) : 1;
    simplify();
}

void RationalNumber::simplify() {
    bool improperFraction = numerator > denominator;
    int greatestCommonDivisor = improperFraction ? gcd(numerator, denominator) : gcd(denominator, numerator);
    numerator = numerator / greatestCommonDivisor;
    denominator = denominator / greatestCommonDivisor;
}

int gcd(int largerNumber, int smallerNumber) {
    if (smallerNumber == 1) {
        return smallerNumber;
    }
    int remainder = largerNumber % smallerNumber;
    if (remainder == 0 || remainder == 1) {
        return smallerNumber;
    }
    return gcd(smallerNumber, remainder);
}

