#include <iostream>
#include "Package.h"
#include "TwoDayPackage.h"
#include "OvernightPackage.h"

using namespace std;

int main() {
    // common data
    Address sender("Leonardo J. Pottsi", "1900 Main St.", "Onett", "EL", "70909");
    Address receiver("Carol M. Pottsi", "100 Abe St.", "Twoson", "EL", "70910");
    double weight = 5.0;
    double costPerOunce = 1.5;
    double flatFee = 15.00;
    double additionalCostPerOunce = 2.50;

    // package family
    Package package(weight, costPerOunce, sender, receiver);
    TwoDayPackage twoDayPackage(flatFee, weight, costPerOunce, sender, receiver);
    OvernightPackage overnightPackage(additionalCostPerOunce, weight, costPerOunce, sender, receiver);

    cout << "Package cost: $" << package.calculateCost() << endl;
    cout << "TwoDayPackage cost: $" << twoDayPackage.calculateCost() << endl;
    cout << "OvernightPackage cost: $" << overnightPackage.calculateCost() << endl;
    return 0;
}