cmake_minimum_required(VERSION 3.5)
project(Assignment11)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Address.cpp Address.h Package.cpp Package.h TwoDayPackage.cpp TwoDayPackage.h OvernightPackage.cpp OvernightPackage.h)
add_executable(Assignment11 ${SOURCE_FILES})