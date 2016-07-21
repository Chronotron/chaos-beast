//
// Created by pparker on 7/19/2016.
//

#include "Address.h"

Address::Address() {
    this->name = "";
    this->address = "";
    this->city = "";
    this->state = "";
    this->zipCode = "";
}

Address::Address(string name, string address, string city, string state, string zipCode) {
    this->name = name;
    this->address = address;
    this->city = city;
    this->state = state;
    this->zipCode = zipCode;
}

string Address::getAddress() {
    return address;
}

string Address::getCity() {
    return city;
}

string Address::getName() {
    return name;
}

string Address::getState() {
    return state;
}

string Address::getZipCode() {
    return zipCode;
}











