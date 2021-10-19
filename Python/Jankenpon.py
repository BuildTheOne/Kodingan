import random
import time

tool_list = ["Rock", "Paper", "Scissors"]

play_time = 1

while play_time == 1 :
    com_tool = random.choice(tool_list)
    tool_input = input("\n1. Rock\n2. Paper\n3. Scissors\nEnter Your Choice : ")
    tool_input = int(tool_input)
    tool_choice = str()
    
    if tool_input == 1:
        tool_choice = (tool_list[0])
    elif tool_input == 2:
        tool_choice = (tool_list[1])
    elif tool_input == 3:
        tool_choice = (tool_list[2])
    else :
        print("Wrong Input")

    print("\nYou : " + tool_choice + " vs. AI : " + com_tool)

    time.sleep(1)

    if tool_choice == com_tool :
        print("Draw")
    elif tool_choice == "Rock" :
        if com_tool == "Paper" :
            print("You Lose")
        else :
            print("You Win")
    elif tool_choice == "Paper" :
        if com_tool == "Rock" :
            print("You Win")
        else :
            print("You Lose")
    elif tool_choice == "Scissors":
        if com_tool == "Rock" :
            print("You Lose")
        else :
            print("You Win")
    
    play_time = input("\nPress 1 To Play Again\n")
    play_time = int(play_time)