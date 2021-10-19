# This program is made by:
# Name   : Ignatius Henriyanto Primai Renda
# No. Id : 2006525002
# Class  : DDP1-D
# This Program is created to fulfill the Tugas Pemrograman 1

import math # Used for math function
import time # Used for time function in the exit program output

# Class is used to simplify the code so the calculation code only needs to be written once
class BaseCalc():
    # Define the Class BaseCalc arguments, consist by 2 variables: 
    # startBase stand for original numeral base and endBase stand for result numeral base
    def __init__(self, startBase, endBase):
        self.startBase = startBase
        self.endBase = endBase

    #Two Functions below are used to define the name of numeral bases
    def baseNameFirst(self):
        if self.startBase == 3:
            baseNameStart = "ternary"
        elif self.startBase == 7:
            baseNameStart = "septenary"
        elif self.startBase == 10:
            baseNameStart = "decimal"
        return baseNameStart
    
    def baseNameLast(self):
        if self.endBase == 3:
            baseNameEnd = "ternary"
        elif self.endBase == 7:
            baseNameEnd = "septenary"
        elif self.endBase == 10:
            baseNameEnd = "decimal"
        return baseNameEnd

    # This function is to calculate and convert number from decimal to another base
    def DecimalOther(self, num):
        # Calling the name of bases function
        start = str(self.baseNameFirst()) 
        end = str(self.baseNameLast())

        # This conditional is to determine whether the input is valid or not
        num = str(num)
        if (num.isdigit()) == True :   # Used as error handling
            number = int(num)
        else:
            return ("Input harus dalam bentuk " + start + "!")

        # Code below is used to convert the number
        result = "" # Making an empty string so it wouldn't raise an error
        if number == 0: # If the input 0, the output is also 0
            return 0
        else:
        # This while loop is used convert the input into another base
        # The result is added in front of the previous iteration, until the input is cannot mod anymore by endBase
        # Then, the input is divided by endBase until the number < 0 so the loop wouldn't goes forever
            while (number > 0):   
                result = str(number % self.endBase) + result   
                number //= self.endBase   
        return result   # When the iteration and condition is done, return the result
    
    # Function below is used to calculate and convert another base number to decimal
    def OtherDecimal(self, num):
        # Calling the name of bases function
        start = str(self.baseNameFirst())
        end = str(self.baseNameLast())

        # This conditional is to determine whether the input is valid or not
        num = str(num)
        if (num.isdigit()) == True:   # Used as error handling
            number = int(num)
        else:
            return ("Input harus dalam bentuk " + start + "!")

        # Code below is used to as prerequisite for the conditional error handling so the input can be calculated as boolean
        numList = [str(number)]
        numList = list(map(int, str(numList[0])))

        if (all(int(i) < self.startBase for i in numList)) == True:   # To determine whether the input is on the correct base
            num = int(num)
            
            # Code below is used to convert the input into decimal
            if (num != 0):
                # Making the empty variables so it wouldn't raise an error
                result = 0
                remainder = 0
                i = 0
                # While loop is used to iterate and sum the calculated input with the previous iteration
                # Input is divided by ten to limit how much the loop would be looped
                # Sum the iteration with the previous iteration that defined as startBase to the power of i
                while (number != 0):   
                    remainder = number % 10  
                    number = number // 10   
                    result += remainder * int(math.pow(self.startBase, i))   
                    i += 1
            else:
                # If the input is 0, the output is also 0
                return 0
        else:
            return ("Input harus dalam bentuk " + self.baseNameFirst() + "!")   # Used as error handling
        return result   # When the iteration and condition is done, return the result

    # This function is used to convert number from one base to another other than decimal
    # The basic thinking of this function is converting a number from a base to decimal, then convert it again to desired base
    def OtherOther(self, num):
        first = self.OtherDecimal(num)
        second = self.DecimalOther(first)
        return second

    # The function below is used to format the desired output
    def Result(self, num):
        # Give variables to the name of bases
        start = str(self.baseNameFirst())
        end = str(self.baseNameLast())

        # Calling the calculation functions and put it into the variables
        DecimalOther = str(self.DecimalOther(num))
        OtherDecimal = str(self.OtherDecimal(num))
        OtherOther = str(self.OtherOther(num))

        # These conditional is used to determine the result from calculation function is valid or not
        # It would check whether if the result from calculating fucntion is numeric
        # If the result is only numeral, it would produce the output as follow:
        # "Nilai [Nama basis tujuan] dari [Nama basis asal] [nilai input] adalah [nilai hasil konversi]"
        # If not, it would produce an invalid message as output
        if (DecimalOther.isnumeric() == True):    
            result = ("Nilai " + end + " dari " + start + " " + str(num) + " adalah " + DecimalOther)
        else:
            return (DecimalOther)
        if (OtherDecimal.isnumeric() == True):
            result = ("Nilai " + end + " dari " + start + " " + str(num) + " adalah " + str(OtherDecimal))
        else:
            return OtherDecimal
        if (OtherOther.isnumeric() == True):
            result = ("Nilai " + end + " dari " + start + " " + str(num) + " adalah " + str(OtherOther))
        else:
            return OtherOther
        return result   # When the conditional is done, return the final output

# Define the converter with BaseCalc class
DecimalTernary = BaseCalc(10,3)
TernaryDecimal = BaseCalc(3,10)
DecimalSeptenary = BaseCalc(10,7)
SeptenaryDecimal = BaseCalc(7,10)
TernarySeptenary = BaseCalc(3,7)
SeptenaryTernary = BaseCalc(7,3)

# Define the menu choices
menu = ("Selamat datang di Program Konverter Bilangan\n"
"1. Decimal ke Ternary\n"
"2. Ternary ke Decimal\n"
"3. Decimal ke Septenary\n"
"4. Septenary ke Decimal\n"
"5. Ternary ke Septenary\n"
"6. Septenary ke Ternary\n"
"7. Keluar")
num_input = "Masukkan input: angka: "

while True:   # While True would make the program looping forever except when it break, so the user could use the program forever
    print(menu)
    try:   # Used as error handling
        cmd = int(input("Masukkan operasi yang ingin dilakukan : "))
        # In the following conditiononal, the class function is called to calculating the input and produced the output
        if cmd == 1:
            num = input(num_input)
            result = DecimalTernary.Result(num)
            print(result)
        elif cmd == 2:
            num = input(num_input)
            result = TernaryDecimal.Result(num)
            print(result)
        elif cmd == 3:
            num = input(num_input)
            result = DecimalSeptenary.Result(num)
            print(result)
        elif cmd == 4:
            num = input(num_input)
            result = SeptenaryDecimal.Result(num)
            print(result)
        elif cmd == 5:
            num = input(num_input)
            result = TernarySeptenary.Result(num)
            print(result)
        elif cmd == 6:
            num = input(num_input)
            result = SeptenaryTernary.Result(num)
            print(result)
        elif cmd == 7:
            time.sleep(1)   # To give the 'delay' effect 
            print("Terima kasih telah menggunakan program")
            time.sleep(1)   # To give the 'delay' effect 
            break
        else:
            print("Maaf input tidak valid")
    except ValueError:
        print("Maaf input tidak valid")