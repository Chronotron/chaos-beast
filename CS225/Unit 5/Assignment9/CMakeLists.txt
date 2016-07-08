cmake_minimum_required(VERSION 3.5)
project(Assignment9)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES SavingsAccountTest.cpp SavingsAccount.cpp SavingsAccount.h)
add_executable(Assignment9 ${SOURCE_FILES})