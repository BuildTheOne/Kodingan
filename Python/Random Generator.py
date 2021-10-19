import tkinter as tk
import random

main = tk.Tk()
main.title("Random Generator")
main.resizable(0,0)
main.geometry("300x300")

def listMember():
    listMember = open(r"Python\Code\List.txt", "r")
    listMember = listMember.read().split("\n")
    return listMember

def randomMember():
    Member = listMember()
    randomMember = random.choice(Member)
    memberLabel.config(text=randomMember)
    return randomMember

memberLabel = tk.Label(main, text="")
memberLabel.pack()

random_btn = tk.Button(main, text="Random", command=randomMember)
random_btn.pack()

main.mainloop()