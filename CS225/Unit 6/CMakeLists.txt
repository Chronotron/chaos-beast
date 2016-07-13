cmake_minimum_required(VERSION 3.5)
project(Assignment10)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp RationalNumber.cpp RationalNumber.h)
add_executable(Assignment10 ${SOURCE_FILES})