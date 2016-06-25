
#include <iostream>
#include <string>

using namespace std;

void calcWealth();
bool isRich(int, long, int, long);
void printBanner();
int promptPleaseEnterInt(string);
long promptPleaseEnterLong(string);

int main()
{
	printBanner();
	string exitSignal = "";
	while (exitSignal != "exit")
	{
		calcWealth();
		cout << "type anything to continue or 'exit' to exit" << endl;
		cin >> exitSignal;
		cin.clear();
	}
}

void calcWealth()
{
	int age = promptPleaseEnterInt("your age");
	long cashOnHand = promptPleaseEnterLong("amount of cash on hand");
	int numberOfDependents = promptPleaseEnterInt("amount of dependents you have");
	long amountOwed = promptPleaseEnterLong("amount of money you owe");

	string message = isRich(age, cashOnHand, numberOfDependents, amountOwed) ?  "Congratulations! We consider you as being \"rich\"" : "I am sorry! You are not yet \"rich\"";
	cout << message << endl;
}

bool isRich(int age, long cashOnHand, int numberOfDependents, long amountOwed)
{
	long richThreshold = 1000000;
	long trueCashValue = cashOnHand - amountOwed;
	if(numberOfDependents <= 0 && trueCashValue >= richThreshold)
	{
		return true;
	}

	if(age < 40)
	{
		trueCashValue -= numberOfDependents * 150000;
	}
	else if (age > 40 && age <= 50)
	{
		trueCashValue -= numberOfDependents * 75000;
	}
	else
	{
		trueCashValue -= numberOfDependents * 25000;
	}

	return trueCashValue >= richThreshold;
}

void printBanner()
{
	cout << "-------***************-------" << endl;
	cout << "-------* ARE         *-------" << endl;
	cout << "-------*    YOU      *-------" << endl;
	cout << "-------*       RICH? *-------" << endl;
	cout << "-------***************-------" << endl;
	cout << endl;
}

int promptPleaseEnterInt(string prompt)
{
	int enteredValue = 0;

	cout << ("Please enter " + prompt + ": ");
	cin >> enteredValue;

	return enteredValue;
}

long promptPleaseEnterLong(string prompt)
{
	long enteredValue = 0;

	cout << ("Please enter " + prompt + ": ");
	cin >> enteredValue;

	return enteredValue;
}
