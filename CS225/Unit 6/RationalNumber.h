//
// Created by pparker on 7/12/2016.
//

#ifndef ASSIGNMENT10_RATIONALNUMBER_H
#define ASSIGNMENT10_RATIONALNUMBER_H


class RationalNumber {
public:
    RationalNumber(int, int);
private:
    int denominator;
    int numerator;
    void simplify();
};


#endif //ASSIGNMENT10_RATIONALNUMBER_H
