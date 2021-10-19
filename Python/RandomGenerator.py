import tkinter as tk
from random import choice, randint


class App(tk.Frame):
    def __init__(self, master):
        super().__init__(master)
        self.pack()
        self.config()
        self.content()

    def config(self):
        self.master.title("Random Generator")
        self.master.configure(bg="white")
        self.master.resizable(0, 0)
        self.master.geometry("300x300")

    def content(self):
        self.label = tk.Label(self,
                              text="",
                              bg="white",
                              width=10,
                              foreground="white",)
        self.label.pack()
        self.button = tk.Button(self,
                                text="Generate",
                                command=self.listMember,
                                width=10,)
        self.button.pack()

    def listMember(self):
        bg = self.randomColor()
        listMember = open("Python\Code\List.txt", "r").read().split("\n")
        randomMember = choice(listMember)
        self.label.config(text=randomMember, bg=bg)
        print(bg)

    def randomColor(self):
        return "#"+("%06x" % randint(0, 16777215))


root = tk.Tk()
app = App(root)
app.mainloop()
