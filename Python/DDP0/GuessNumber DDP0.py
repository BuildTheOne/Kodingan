import random

key_answer = int(random.randint(1,11))
guess_count = 1

while guess_count < 4:
    answer = int(input())
    guess_count = guess_count + 1

    if key_answer == answer :
        print("Correct")
        break
    elif key_answer < answer :
        print("Too High")
    elif key_answer > answer :
        print("Too Low")
else:
    print("You Lose")
    