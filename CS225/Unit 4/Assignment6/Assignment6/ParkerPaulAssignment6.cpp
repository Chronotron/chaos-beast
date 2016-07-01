
#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>

using namespace std;

vector<int [3]> generateSalesSlips(void);
void populateSales(int[][4], int);
void printSales(const int[][4], int);

int main()
{
	int sales [5][4];
	populateSales(sales, 5);
	printSales(sales, 5);
	cout << "done" << endl;
	string whatevs;
	cin >> whatevs;
	return 0;
}

void populateSales(int sales[][4], int rows)
{
	vector<int [3]> salesSlips = generateSalesSlips();
	for (int i = 0; i < rows; i++)
	{
		for (int j = 0; j < 4; j++) 
		{
			sales[i][j] = (i + 1) * (j + 1);
		}
	}
}

void printSales(const int sales[][4], int rows)
{
	for (int i = 0; i < rows; i++)
	{
		for (int j = 0; j < 4; j++) 
		{
			int sale = sales[i][j];
			printf("%*d ", 2, sale);
		}

		cout << endl;
	}
}
