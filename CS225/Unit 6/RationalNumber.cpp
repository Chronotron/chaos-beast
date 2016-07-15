//
// Created by pparker on 7/12/2016.
//

#include "RationalNumber.h"
#include <cstdlib>
#include <sstream>

int gcd(int, int);

RationalNumber::RationalNumber(int numerator, int denominator) {
    setDenominator(denominator);
    setNumerator(numerator);
    simplify();
}

void RationalNumber::simplify() {
    bool improperFraction = numerator > denominator;
    int absNumerator = abs(numerator);
    int greatestCommonDivisor = improperFraction ? gcd(absNumerator, denominator) : gcd(denominator, absNumerator);
    numerator = numerator / greatestCommonDivisor;
    denominator = denominator / greatestCommonDivisor;
    if (numerator == 0) {
        denominator = 1;
    }
}

RationalNumber &RationalNumber::operator+=(const RationalNumber &right) {
    int rightDenominator = right.denominator;
    int rightNumerator = right.numerator;
    int leftDenominator = this->denominator;
    int leftNumerator = this->numerator;

    int reconciledDenominator = rightDenominator * leftDenominator;
    int reconciledRightNumerator = rightNumerator * leftDenominator;
    int reconciledLeftNumerator = leftNumerator * rightDenominator;

    this->setNumerator(reconciledLeftNumerator + reconciledRightNumerator);
    this->setDenominator(reconciledDenominator);
    simplify();

    return *this;
}

RationalNumber RationalNumber::operator+(const RationalNumber &right) {
    RationalNumber temp = *this;
    temp += right;
    return temp;
}

void RationalNumber::setDenominator(int denominator) {
    this->denominator = denominator != 0 ? abs(denominator) : 1;
}

void RationalNumber::setNumerator(int numerator) {
    this->numerator = numerator;
}

string RationalNumber::toString() {
    std::stringstream sstm;
    sstm << numerator << "/" << denominator;
    return sstm.str();
}

RationalNumber &RationalNumber::operator-=(const RationalNumber &right) {
    int rightDenominator = right.denominator;
    int rightNumerator = right.numerator;
    int leftDenominator = this->denominator;
    int leftNumerator = this->numerator;

    int reconciledDenominator = rightDenominator * leftDenominator;
    int reconciledRightNumerator = rightNumerator * leftDenominator;
    int reconciledLeftNumerator = leftNumerator * rightDenominator;

    this->setNumerator(reconciledLeftNumerator - reconciledRightNumerator);
    this->setDenominator(reconciledDenominator);
    simplify();

    return *this;
}

RationalNumber RationalNumber::operator-(const RationalNumber &right) {
    RationalNumber temp = *this;
    temp -= right;
    return temp;
}

RationalNumber RationalNumber::operator*(const RationalNumber &right) {
    int newNumerator = this->numerator* right.numerator;
    int newDenominator = this->denominator* right.denominator;
    return RationalNumber(newNumerator, newDenominator);
}

RationalNumber RationalNumber::operator/(const RationalNumber &right) {
    int newNumerator = this->numerator * right.denominator;
    int newDenominator = this->denominator* right.numerator;
    return RationalNumber(newNumerator, newDenominator);
}


int gcd(int largerNumber, int smallerNumber) {
    if (smallerNumber == 0) {
        return 1;
    }

    if (smallerNumber == 1) {
        return smallerNumber;
    }

    int remainder = largerNumber % smallerNumber;
    if (remainder == 0 || remainder == 1) {
        return smallerNumber;
    }

    return gcd(smallerNumber, remainder);
}

