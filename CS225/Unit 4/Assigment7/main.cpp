#include <iostream>

using namespace std;

// 7.10
void zero(long []); //b
int add1AndSum(int);// d

int main() {
    long varialble1 = 200000;
    long varialble2;
    // 7.9
    long *longPrt; // a
    longPrt = &varialble1; // b
    cout << *longPrt << endl; // c
    longPrt = &varialble2; // d
    cout << varialble2 << endl;  // e
    cout << &varialble1 << endl;
    cout << longPrt << endl;

    // 7.11
    // a
//    int *number;
//    cout << number << endl; // number not initialized

    // b
//    double *realPtr;
//    long *integerPtr;
//    integerPtr = realPtr; // incompatible pointer types
    // c
//    int * x, y;
//    x = y; // taking pointer from integer without a cast. y is read as int y;

    // d
//    char s[] = "this is a character array";
//    for (; *s != '\0'; s++) // lvalue required as increment operand
//        cout << *s << ' ';

    // e
//    short *numPtr, result;
//    void *genericPtr = numPtr;
//    result = *genericPtr + 7; // dereferencing void pointer

    // f
//    double x = 19.34;
//    double xPtr = &x; // double is not double * should be double * xPtr
//    cout << xPtr << endl;

    return 0;
}

// 7.10
void zero(long bigIntegers[]) { } // a
int add1AndSum(int oneTooSmall) { return 0; } // c


