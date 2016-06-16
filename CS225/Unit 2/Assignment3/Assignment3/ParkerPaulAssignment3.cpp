
#include <iostream>
using namespace std;

int main()
{
	double weeklySalary = 200.00; // initialize base weekly salary
	double commissionPercent = 0.09; // initialize commission percent
	double weeklySales = 0.00; // initialize weekly sales to non -1 value

	// main input loop
	while (weeklySales != -1)
	{
		cout << "Enter sales in dollars (-1 to end): ";
		cin >> weeklySales;
		if(weeklySales != -1) {
			cout << "Salary is: $" << weeklySalary + (weeklySales * commissionPercent) << endl;
		}
	} // end while
}