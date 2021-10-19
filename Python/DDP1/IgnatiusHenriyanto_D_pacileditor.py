from tkinter import *
from tkinter.filedialog import asksaveasfilename, askopenfilename


class Application(Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.master = master
        self.initUI()
        self.create_buttons()
        self.create_editor()
        self.master.bind("<Control-s>", self.save_file_event)
        self.master.bind("<Control-o>", self.load_file_event)

    def initUI(self):
        # TODO: Atur judul dan ukuran dari main window,
        # lalu buat sebuah Frame sebagai anchor dari seluruh button
        self.master.title("Judul")
        self.master.geometry("500x500")
        self.frame = Frame(self.master)
        self.frame.pack(fill="x")
        self.pack()

    def create_buttons(self):
        openBtn = Button(self.frame,
                         text="Open File",
                         width=15,
                         command=self.load_file).pack(side="left")
        saveFile = Button(self.frame,
                          text="Save File",
                          width=15,
                          command=self.save_file).pack(side="left")
        exitBtn = Button(self.frame,
                         text="Quit Program",
                         width=15,
                         command=self.master.destroy).pack(side="left")
        

    def create_editor(self):
        # TODO: Implementasikan textbox
        frame = Frame(self.master)
        frame.pack()
        self.text = Text(frame, width="300", cursor="xterm")
        ybar = Scrollbar(frame)
        ybar.config(command=self.text.yview)
        ybar.pack(side="right", fill="y")
        self.text.pack()

    def load_file_event(self, event):
        x = self.load_file()

    def load_file(self):
        file_name = askopenfilename(
            filetypes=[("All files", "*")]
        )
        if not file_name:  # Jika pengguna membatalkan dialog, langsung return
            return
        text_file = open(file_name, 'r', encoding="utf-8")
        result = text_file.read()
        # TODO: tampilkan result di textbox
        self.text.insert(INSERT, result)

    def save_file_event(self, event):
        self.save_file()

    def save_file(self):
        file_name = asksaveasfilename(
            filetypes=[("All files", "*")]
        )
        if not file_name:  # Jika pengguna membatalkan dialog, langsung return
            return
        # TODO: ambil isi dari textbox lalu simpan dalam file dengan nama file_name
        x = self.text.get("1.0", END)
        text_file = open(file_name+".txt", "w")    # Otomatis menyimpan ekstensi .txt
        text_file.write(x)
        text_file.close()


if __name__ == "__main__":
    root = Tk()
    app = Application(master=root)
    app.mainloop()
