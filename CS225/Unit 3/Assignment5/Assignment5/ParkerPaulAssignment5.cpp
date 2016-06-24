
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
	string exitSignal = "";
	while (exitSignal != "exit")
	{
		calcWealth();
		cout << "any key to continue or 'exit' to exit" << endl;
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

bool isRich(int age, long cashOnhand, int numberOfDependents, long amountOwed)
{
	return 0;
}

void printBanner()
{

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
