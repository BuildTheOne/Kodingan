#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>
using namespace std;

int main() {
	int sum;
	int number1, number2;
	srand((unsigned) time(0));
	int randomNumber = rand();
	
	string command1 = "Choose operation.";
	string command2 = "Calculate these number!";
	string plus = " + ";
	string minus = " - ";
	string times = " * ";
	string divide = " / ";

	number1 = 1 + (rand() % 99);
	number2 = 1 + (rand() % 99);
	
	cout << command2 << "\n" << number1 << times << number2;
	
	sum = number1 * number2;
	
	if (cin.get() == '\n') {
		cout << "The answer is : " << sum;
	}
		
	return 0;
}
	

