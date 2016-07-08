#include <iostream>
#include <iomanip>
#include "Cylinder.h"

using namespace std;

void reportCylinder(Cylinder);

int main() {
    Cylinder customCylinder;
    Cylinder defaultCylinder;
    double radius;
    double height;

    // initialize cylinders
    customCylinder.setHeight(10.0);
    defaultCylinder.setHeight(10.0);

    // collect input for custom cylinder
    cout << "Please enter the radius: " << endl;
    cin >> radius;
    cout << "Please enter the height: " << endl;
    cin >> height;

    // update cylinder
    customCylinder.setRadius(radius);
    customCylinder.setHeight(height);


    reportCylinder(defaultCylinder);
    reportCylinder(customCylinder);

    return 0;
}

void reportCylinder(Cylinder cylinder) {
    cout << "The cylinder with a radius of " << cylinder.getRadius() << " and height of " <<
    cylinder.getHeight() << " has a surface area of:  " << fixed << setprecision(2) << cylinder.getSurfaceArea() <<
    endl;
    cout << resetiosflags(ios::fixed); // restore output stream state
}