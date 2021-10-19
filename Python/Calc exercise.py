import random

def start():
    print("Calculation Exercise.\n"
    "1 for addition\n"
    "2 for subtraction\n"
    "3 for multiplication\n"
    "4 for division\n")
    while True:
        try:
            choice = int(input("Enter your choice : "))
            if choice <= 4:
                return choice
            else:
                print("Your input is not valid.")
                continue
        except ValueError:
            print("Your input is not valid.")

def calc(choice):
    firstNum = random.randint(2,99)
    secondNum = random.randint(2,99)
    print("Calculate:")
    if choice == 1:
        print(f"{firstNum} + {secondNum}")
        keyAnswer = firstNum + secondNum
    if choice == 2:
        print(f"{firstNum} - {secondNum}")
        keyAnswer = firstNum - secondNum
    if choice == 3:
        print(f"{firstNum} x {secondNum}")
        keyAnswer = firstNum * secondNum
    if choice == 4:
        print(f"{firstNum} / {secondNum}")
        keyAnswer = ("{:0.2f}".format((firstNum / secondNum)))

    answer = input()
    if float(answer) == float(keyAnswer):
        print("Correct")
    else:
        print(f"False. The answer is {keyAnswer}")
    
def main():
    playInput = 1
    while int(playInput) == 1:
        play = start()
        calc(play)
        playInput = input("Press 1 to play again.\n")

main()