//
// Created by pparker on 7/7/2016.
//

#ifndef ASSIGNMENT8_CYLINDER_H
#define ASSIGNMENT8_CYLINDER_H


class Cylinder {

public:
    Cylinder();
    double getHeight();
    double getRadius();
    double getSurfaceArea();
    void setHeight(double);
    void setRadius(double);

private:
    double height;
    double radius;
};

//    a default constructor that sets the radius to 3
//    a getter function to return the value of the height
//    a getter function to return the value of the radius
//    a setter function to set the value of the height
//    a setter function to set the value of the radius
//    a method to compute and return the cylinder’s surface area

#endif //ASSIGNMENT8_CYLINDER_H
