
#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>

using namespace std;

#pragma region Prototypes

vector<vector<int>> generateSalesSlips();
int getSalesFor(int, int, const vector<vector<int>>);
void insertSales(vector<vector<int>> &, int, size_t, size_t);
void populateSales(int[][4], int);
void printSales(const int[][4], int);

#pragma endregion

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

#pragma region Helper Functions

vector<vector<int>> generateSalesSlips()
{
	int count1 = rand() % 6;
	int count2 = rand() % 6;
	int count3 = rand() % 6;
	int count4 = rand() % 6;
	vector<vector<int>> salesSlips(count1 + count2 + count3 + count4);
	insertSales(salesSlips, 1, count1, 0);
	insertSales(salesSlips, 2, count2, count1);
	insertSales(salesSlips, 3, count3, count1 + count2);
	insertSales(salesSlips, 4, count4, count1 + count2 + count3);
	return salesSlips;
}

int getSalesFor(int productId, int salesId, const vector<vector<int>> salesSlips)
{
	int totalSales = 0;

	for (size_t i = 0; i < salesSlips.size(); i++)
	{
		vector<int> slip = salesSlips[i];
		if(slip[0] == salesId && slip[1] == productId)
		{
			totalSales += slip[2];
		}
	}

	return totalSales;
}

void insertSales(vector<vector<int>> &salesSlips, int id, size_t count, size_t offset)
{
	for (size_t i = offset; i < count + offset; i++)
	{
		int product = rand() % 6 + 1;
		int sales = rand() % 100 + 1;
		vector<int> slip(3);
		slip[0] = id;
		slip[1] = product;
		slip[2] = sales;
		salesSlips[i] = slip;
	}
}

void populateSales(int sales[][4], int rows)
{
	vector<vector<int>> salesSlips = generateSalesSlips();
	for (int i = 0; i < rows; i++)
	{
		int productId = i + 1;
		for (int j = 0; j < 4; j++) 
		{
			int salesId = j + 1;
			sales[i][j] = getSalesFor(productId, salesId, salesSlips);
		}
	}
}

void printSales(const int sales[][4], int rows)
{
	int columnTotals [4] = {0, 0, 0, 0};
	for (int i = 0; i < rows; i++)
	{
		int rowTotal = 0;
		for (int j = 0; j < 4; j++) 
		{
			int sale = sales[i][j];
			rowTotal += sale;
			columnTotals[j] += sale;
			printf("%*d ", 5, sale);
		}

		cout << " " << rowTotal << endl;
	}
	for (int k = 0; k < 4; k++)
	{
		printf("%*d ", 5, columnTotals[k]);
	}
	cout << endl;
}

#pragma endregion
