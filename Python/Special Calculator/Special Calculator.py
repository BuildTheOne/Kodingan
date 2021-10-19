from PrimeNumber import checkPrime
from GCDcalculator import findGCD, findLCM
from LinearCongruency import linearCongruency
from BaseCalculator import toDefault, otherToOther


def baseCalcCommand():
    baseMenu = ("\n----Base Calculator----\n"
                "1. Decimal to Default\n"
                "2. Binary to Default\n"
                "3. Octal to Default\n"
                "4. Hex to Default\n"
                "5. Other to Default\n"
                "6. Other to Other")
    print(baseMenu)
    inputCmd = int(input("Enter your input : "))
    if (inputCmd == 1):
        inputNum = int(input("Enter the number : "))
        print(toDefault(inputNum))
    elif (inputCmd == 2):
        inputNum = int(input("Enter the number : "))
        print(toDefault(inputNum, 2))
    elif (inputCmd == 3):
        inputNum = int(input("Enter the number : "))
        print(toDefault(inputNum, 8))
    elif (inputCmd == 4):
        inputNum = input("Enter the number : ")
        print(toDefault(inputNum, 16))
    elif (inputCmd == 5):
        inputNum = input("Enter the number : ")
        inputBase = int(input("Enter other base : "))
        print(toDefault(inputNum, inputBase))
    elif (inputCmd == 6):
        inputNum = input("Enter the number : ")
        inputBase1 = int(input("Enter first base : "))
        inputBase2 = int(input("Enter second base : "))
        print(otherToOther(inputNum, inputBase1, inputBase2))
    else:
        print("Command error")


def checkPrimeCommand():
    inputNum = int(input("\n----Check Prime Number----\nEnter the number : "))
    if (checkPrime(inputNum)):
        print(f"{inputNum} is a prime number")
    else:
        print(f"{inputNum} is not a prime number")


def findGCDLCMcommand():
    inputCmd = int(
        input("\n1. Find GCD\n2. Find LCM\nEnter your input : "))
    if (inputCmd == 1):
        inputNum1 = int(input("Enter the first number : "))
        inputNum2 = int(input("Enter the second number : "))
        gcd = findGCD(inputNum1, inputNum2)
        print(f"GCD({inputNum1}, {inputNum2}) = {gcd}")
    else:
        inputNum1 = int(input("Enter the first number : "))
        inputNum2 = int(input("Enter the second number : "))
        print(findLCM(inputNum1, inputNum2))


def linearCongruencyCommand():
    inputNumA = int(
        input("\n----Find Linear Congruence----\nEnter the A number : "))
    inputNumB = int(input("Enter the B number : "))
    inputNumM = int(input("Enter the M number : "))
    lc = linearCongruency(inputNumA, inputNumB, inputNumM)
    if (lc == False):
        print("x has no solution")
    else:
        print(f"x ≡ {lc} (mod {inputNumM})")


def main():
    mainMenu = ("-"*10 + "Special Calculator" + "-"*10
                + "\n1. Base Calculator"
                + "\n2. Check Prime Number"
                + "\n3. Find GCD or LCM"
                + "\n4. Find Linear Congruence Ax ≡ B (Mod M)")

    while True:
        try:
            print(mainMenu)
            cmdInput = input("Enter your command : ")
            if (int(cmdInput) == 1):
                baseCalcCommand()
            elif (int(cmdInput) == 2):
                checkPrimeCommand()
            elif (int(cmdInput) == 3):
                findGCDLCMcommand()
            elif (int(cmdInput) == 4):
                linearCongruencyCommand()
            else:
                print("Command error")
        except:
            print("Input error")
        print("")


if __name__ == "__main__":
    main()
