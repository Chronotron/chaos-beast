cmake_minimum_required(VERSION 3.5)
project(Assignment8)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES CylinderTest.cpp Cylinder.cpp Cylinder.h)
add_executable(Assignment8 ${SOURCE_FILES})