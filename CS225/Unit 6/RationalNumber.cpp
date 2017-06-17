//
// Created by pparker on 7/12/2016.
//

#include "RationalNumber.h"
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
    int newNumerator = this->numerator * right.numerator;
    int newDenominator = this->denominator * right.denominator;
    return RationalNumber(newNumerator, newDenominator);
}

RationalNumber RationalNumber::operator/(const RationalNumber &right) {
    int newNumerator = this->numerator * right.denominator;
    int newDenominator = this->denominator * right.numerator;
    return RationalNumber(newNumerator, newDenominator);
}

bool RationalNumber::operator>(const RationalNumber &right) {
    return compareTo(right) == 1;
}

bool RationalNumber::operator<(const RationalNumber &right) {
    return compareTo(right) == -1;
}

bool RationalNumber::operator==(const RationalNumber &right) {
    return compareTo(right) == 0;
}

bool RationalNumber::operator>=(const RationalNumber &right) {
    return *this == right || *this > right;
}

bool RationalNumber::operator<=(const RationalNumber &right) {
    return *this == right || *this < right;
}

int RationalNumber::compareTo(RationalNumber right) {
    int rightDenominator = right.denominator;
    int rightNumerator = right.numerator;
    int leftDenominator = this->denominator;
    int leftNumerator = this->numerator;

    int reconciledRightNumerator = rightNumerator * leftDenominator;
    int reconciledLeftNumerator = leftNumerator * rightDenominator;

    if (reconciledLeftNumerator > reconciledRightNumerator) {
        return 1;
    } else if (reconciledLeftNumerator < reconciledRightNumerator) {
        return -1;
    } else {
        return 0;
    }
}

bool RationalNumber::operator!=(const RationalNumber &right) {
    return compareTo(right) != 0;
}

int gcd(int largerNumber, int smallerNumber) {
    if (smallerNumber == 0 || (largerNumber - smallerNumber) == 1) {
        return 1;
    }

    if (smallerNumber == 1) {
        return 1;
    }

    int remainder = largerNumber % smallerNumber;
    if (remainder == 0) {
        return smallerNumber;
    }

    if (remainder == 1) {
        return 1;
    }

    return gcd(smallerNumber, remainder);
}

