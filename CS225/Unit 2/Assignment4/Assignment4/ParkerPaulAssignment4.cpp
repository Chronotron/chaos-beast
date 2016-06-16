
#include <iostream>

using namespace std;

int main()
{
	int const product1 = 1; // initialize product1 key
	int const product2 = 2; // initialize product2 key
	int const product3 = 3; // initialize product3 key
	int const product4 = 4; // initialize product4 key
	int const product5 = 5; // initialize product4 key

	int productSelection = 0; // initialize product selection to non -1 value
	int productQuantity = 0; // initialize product quantity to non -1 value

	double currentPrice; // declare current price
	double totalSales = 0.0; // initialize total sales

	while (productSelection != -1 && productQuantity != -1)
	{
		cout << "Please enter product number (1,2,3,4, or 5 and -1 to cancel): ";
		cin >> productSelection;

		if(productSelection == -1)
		{
			break;
		}

		cout << "Please enter quantity (-1 to cancel): ";
		cin >> productQuantity;

		if(productQuantity == -1)
		{
			break;
		}

		switch (productSelection)
		{
			case product1:
				currentPrice = 2.98;
				break;
			case product2:
				currentPrice = 4.50;
				break;
			case product3:
				currentPrice = 9.98;
				break;
			case product4:
				currentPrice = 4.49;
				break;
			case product5:
				currentPrice = 6.87;
				break;
			default:
				currentPrice = 0.0;
				break;
		}

		totalSales += currentPrice * productQuantity;
	}

	cout << "Total Sales: $" << totalSales << endl;
}