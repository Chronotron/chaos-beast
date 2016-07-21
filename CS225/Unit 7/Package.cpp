//
// Created by pparker on 7/19/2016.
//

#include "Package.h"

Package::Package(double weight, double costPerOunce, Address sender, Address receiver)
        :  sender(sender), receiver(receiver) {
    this->weight = weight >= 0.0 ? weight : 0.0;
    this->costPerOunce = costPerOunce >= 0.0 ? costPerOunce : 0.0;
}

//region Public Methods

double Package::calculateCost() {
    return weight * costPerOunce;
}

Address Package::getReceiver() {
    return Address(receiver);
}

Address Package::getSender() {
    return Address(sender);
}

//endregion





