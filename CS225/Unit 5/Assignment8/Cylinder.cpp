//
// Created by pparker on 7/7/2016.
//

#include "Cylinder.h"

Cylinder::Cylinder() {
    height = 0.0;
    radius = 3.0;
}

double Cylinder::getHeight() { return height; }
double Cylinder::getRadius() { return radius; }
double Cylinder::getSurfaceArea() {
    double pi = 3.14;
    return 2.0 * pi * radius * radius + 2 * pi * radius * height;
}
void Cylinder::setHeight(double height) { this->height = height; }
void Cylinder::setRadius(double radius) { this->radius = radius; }
