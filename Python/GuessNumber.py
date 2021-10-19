import random

play_time = 1

minimum = int(input("Enter the minimum number to guess : "))
maximum = int(input("Enter the maximum number to guess : "))
guess_chance = int(input("Enter the maximum chance to guess : "))

while play_time == 1:
    key_answer = int(random.randint(minimum, maximum))
    guess_count = 0

    print(f"I'm thinking a number between {minimum} and {maximum}.\nGuess the number : ")
    while guess_count < guess_chance:
        answer = int(input())
        guess_count += 1

        if answer == key_answer:
            print("Correct")
            break
        if answer < key_answer:
            print("Too Low, Guess Again")
        if answer > key_answer:
            print("Too High, Guess Again")
    else:
        print("Your chance is out, the answer is " + str(key_answer))

    play_time = int(input("Press 1 To Play Again\n"))