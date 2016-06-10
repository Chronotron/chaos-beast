
#include <iostream>
using namespace std;

int main() 
{
	int y; // declare y
	int x = 1; // initialize x
	int total = 0; // initialize total

	while (x <= 10) // loop 10 times
	{
		y = x * x; // perform calculation
		cout << y << endl; // output result
		total += y; // add y to total
		x++; // increment counter x
	} // end while

	cout << "Total is " << total << endl; // display result
	// 1
	// 4
	// 9
	// 16
	// 25
	// 36
	// 49
	// 64
	// 81
	// 100
	// Total is 385
}