
#include <iostream>

int main()
{
	int number1; // first number for calculations
	int number2; // second number for calculations
	int number3; // third number for calculations
	int smallest; // smallest of the numbers
	int largest; // largest of the numbers
	char end; // wait for input to exit

	// prompt for and accept input
	std::cout << "Enter 3 different integers" << std::endl;
	std::cin >> number1 >> number2 >> number3; 
	std::cout << "Sum is " << (number1 + number2 + number3) << std::endl;
	std::cout << "Average is " << (number1 + number2 + number3) / 3 << std::endl;
	std::cout << "Product is " << (number1 * number2 * number3) << std::endl;
	// determine smallest
	smallest = number1;
	smallest = smallest > number2 ? number2 : smallest;
	smallest = smallest > number3 ? number3 : smallest;
	std::cout << "Smallest is " << smallest << std::endl;
	// determine largest
	largest = number1;
	largest = largest > number2 ? largest : number2;
	largest = largest > number3 ? largest : number3;
	std::cout << "Largest is " << largest << std::endl;
	std::cin >> end;
	return 0;
}