//
// Created by pparker on 7/22/2016.
//

#ifndef ASSIGNMENT11_OVERNIGHTPACKAGE_H
#define ASSIGNMENT11_OVERNIGHTPACKAGE_H


#include "Package.h"

class OvernightPackage : public Package {
public:
    OvernightPackage(double, double, double, Address, Address);

    double additionalFeePerOunce;

    double calculateCost();
};


#endif //ASSIGNMENT11_OVERNIGHTPACKAGE_H
