	#include <iostream>
	#include <string>
	#include <cstdlib>
	#include <ctime>
	using namespace std;
	
	int main() {
		int num1, num2, sum, min, times, divide, choice;
		srand((unsigned) time(0));
		int randomNumber = rand();
		
		num1 = 1 + (rand() % 99);
		num2 = 1 + (rand() % 99);
		
		string command1 = " Choose operation.";
		string command2 = " Calculate these number!";
		string guide = " 1 for addition \n 2 for subtraction \n 3 for multiplication \n 4 for division \n Your input : ";
		string plus = " + ";
		string minus = " - ";
		string time = " * ";
		string divid = " / ";
		
		sum = num1 + num2;
		min = num1 - num2;
		times = num1 * num2;
		divide = num1 / num2;
		
		cout << command1 << '\n' << guide ;
		
		if (cin.get() == '1') {
			cout << command2 << '\n' << num1 << plus << num2 ;
		}
		if (cin.get() == '2') {
			cout << command2 << '\n' << num1 << minus << num2 ;
		}
		if (cin.get() == '3') {
			cout << command2 << '\n' << num1 << time << num2 ;
		}
		if (cin.get() == '4') {
			cout << command2 << '\n' << num1 << divid << num2 ;
		}
		
	return 0;
	}
