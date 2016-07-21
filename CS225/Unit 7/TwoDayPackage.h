//
// Created by pparker on 7/21/2016.
//

#ifndef ASSIGNMENT11_TWODAYPACKAGE_H
#define ASSIGNMENT11_TWODAYPACKAGE_H


#include "Package.h"

class TwoDayPackage : public Package {

public:
    TwoDayPackage(double, double, double, Address, Address);

    double calculateCost();

private:
    double flatFee;
};


#endif //ASSIGNMENT11_TWODAYPACKAGE_H
