//
// Created by pparker on 7/22/2016.
//

#include "OvernightPackage.h"

OvernightPackage::OvernightPackage(double additionalFeePerOunce, double weight, double costPerOunce, Address sender,
                                   Address receiver)
        : Package(weight, costPerOunce, sender, receiver) {
    this->additionalFeePerOunce = additionalFeePerOunce;
}

double OvernightPackage::calculateCost() {
    return Package::calculateCost() + (additionalFeePerOunce * getWeight());
}



