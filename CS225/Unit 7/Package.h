//
// Created by pparker on 7/19/2016.
//

#ifndef ASSIGNMENT11_PACKAGE_H
#define ASSIGNMENT11_PACKAGE_H


#include "Address.h"

// force push

class Package {
public:
    Package(double, double, Address, Address);

    double calculateCost();

    Address getSender();

    Address getReceiver();

private:
    Address receiver;
    Address sender;
    double weight;
    double costPerOunce;
};


#endif //ASSIGNMENT11_PACKAGE_H
