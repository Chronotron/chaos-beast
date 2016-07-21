//
// Created by pparker on 7/21/2016.
//

#include "TwoDayPackage.h"

TwoDayPackage::TwoDayPackage(double flatFee, double weight, double costPerOunce, Address sender, Address receiver)
        :  Package(weight, costPerOunce, sender, receiver) {
    this->flatFee = flatFee > 0.0 ? flatFee : 0.0;
}

double TwoDayPackage::calculateCost() {
    return Package::calculateCost() + flatFee;
}



