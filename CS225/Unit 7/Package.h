//
// Created by pparker on 7/19/2016.
//

#ifndef ASSIGNMENT11_PACKAGE_H
#define ASSIGNMENT11_PACKAGE_H


#include "Address.h"

class Package {
public:
    Package(double, Address, Address);

    double calculateCost();
    Address getSender();

    Address getReceiver();

private:
    Address receiver;
    Address sender;
    double weight;
};


#endif //ASSIGNMENT11_PACKAGE_H
