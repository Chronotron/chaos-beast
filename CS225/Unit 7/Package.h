//
// Created by pparker on 7/19/2016.
//

#ifndef ASSIGNMENT11_PACKAGE_H
#define ASSIGNMENT11_PACKAGE_H


#include "Address.h"

class Package {
public:
    Package();

    Package(double, double, Address, Address);

    virtual double calculateCost();

    Address getSender();

    Address getReceiver();

    double getWeight();

protected:
    Address receiver;
    Address sender;

private:
    double weight;
    double costPerOunce;
};


#endif //ASSIGNMENT11_PACKAGE_H
