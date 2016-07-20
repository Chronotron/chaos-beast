//
// Created by pparker on 7/19/2016.
//

#ifndef ASSIGNMENT11_ADDRESS_H
#define ASSIGNMENT11_ADDRESS_H

#include <string>

using namespace std;

class Address {
public:
    Address(string,string,string,string,string);
    string getAddress();
    string getCity();
    string getName();
    string getState();
    string getZipCode();
private:
    string address;
    string city;
    string name;
    string state;
    string zipCode;
};


#endif //ASSIGNMENT11_ADDRESS_H
