'''
This program is made by:
Name   : Ignatius Henriyanto Primai Renda
No. Id : 2006525002
Class  : DDP1-D
This Program is created to fulfill Tugas Pemrograman 4 Bonus GUI
'''

'''
Import module
'''
from tkinter import *
from tkinter.messagebox import showinfo
from time import sleep

'''
Main Code
Almost same as the great-library.py, the only difference is the output view
'''
class Book:
    def __init__(self, title, year, writer, publisher):
        self.__title = title
        self.__year = year
        self.__writer = writer
        self.__publisher = publisher

    def setBookDesc(self):
        return self.__title, self.__year, self.__writer, self.__publisher

    def getBookDesc(self):
        return (f"Buku {self.__title}\n"
                f"Nama Buku : {self.__title}\n"
                f"Tahun : {self.__year}\n"
                f"Pengarang : {self.__writer}\n"
                f"Penerbit : {self.__publisher}")


class FictionBook(Book):
    def __init__(self, title, year, writer, publisher, genre):
        Book.__init__(self, title, year, writer, publisher)
        self.__genre = genre

    def setBookGenre(self):
        return self.__genre

    def getBookDesc(self):
        bookData = self.setBookDesc()
        bookGenre = self.setBookGenre()
        return (f"Buku Fiksi\n"
                f"Nama Buku : {bookData[0]}\n"
                f"Tahun : {bookData[1]}\n"
                f"Pengarang : {bookData[2]}\n"
                f"Penerbit : {bookData[3]}\n"
                f"Genre : {bookGenre}")


class ReferenceBook(Book):
    def __init__(self, title, year, writer, publisher, city):
        Book.__init__(self, title, year, writer, publisher)
        self.__city = city

    def setBookCity(self):
        return self.__city

    def getBookDesc(self):
        bookData = self.setBookDesc()
        bookCity = self.setBookCity()
        return (f"Buku Referensi\n"
                f"Nama Buku : {bookData[0]}\n"
                f"Tahun : {bookData[1]}\n"
                f"Pengarang : {bookData[2]}\n"
                f"Penerbit : {bookData[3]}\n"
                f"Kota Terbit : {bookCity}")


class EncyclopediaBook(Book):
    def __init__(self, title, year, writer, publisher, rev):
        Book.__init__(self, title, year, writer, publisher)
        self.__rev = rev

    def setBookRevNum(self):
        return self.__rev

    def getBookDesc(self):
        bookData = self.setBookDesc()
        bookRevNum = self.setBookRevNum()
        return (f"Buku Ensiklopedia\n"
                f"Nama Buku : {bookData[0]}\n"
                f"Tahun : {bookData[1]}\n"
                f"Pengarang : {bookData[2]}\n"
                f"Penerbit : {bookData[3]}\n"
                f"Nomor Revisi : {bookRevNum}")


class Shelf:
    def __init__(self, name, shelfType):
        self.__name = name
        self.__shelfType = shelfType
        self.__listBook = []

    def getShelfData(self):
        return self.__name, self.__shelfType, self.__listBook

    def searchBook(self, book):
        getBookData = self.getShelfData()[2]
        for i in getBookData:
            j = i.setBookDesc()[0]
            if j == book:
                return i
        else:
            return False

    def checkBook(self, book):
        getShelf = self.getShelfData()[2]
        if len(getShelf) != 0:
            for i in getShelf:
                x = i.setBookDesc()[0]
                if x == book:
                    return True
            return False
        else:
            return False


class KnowledgeShelf(Shelf):
    def __init__(self, name, shelfType):
        Shelf.__init__(self, name, shelfType)

    def addBook(self, book):
        if isinstance(book, ReferenceBook) or isinstance(book, EncyclopediaBook):
            getShelf = self.getShelfData()[0]
            getBook = self.getShelfData()[2]
            getBook.append(book)
            return showinfo("Tambah Buku", message=f"Buku baru berhasil ditambahkan pada rak {getShelf}\n\n"
                            f"{book.getBookDesc()}")
        else:
            return showinfo(message="Buku gagal ditambahkan :(")


class WorldShelf(Shelf):
    def __init__(self, name, shelfType):
        Shelf.__init__(self, name, shelfType)

    def addBook(self, book):
        if isinstance(book, FictionBook) or isinstance(book, EncyclopediaBook):
            getShelf = self.getShelfData()[0]
            getBook = self.getShelfData()[2]
            getBook.append(book)
            return showinfo("Tambah Buku", message=f"Buku baru berhasil ditambahkan pada rak {getShelf}\n\n"
                            f"{book.getBookDesc()}")
        else:
            return showinfo(message="Buku gagal ditambahkan :(")


class MysteryShelf(Shelf):
    def __init__(self, name, shelfType):
        Shelf.__init__(self, name, shelfType)

    def addBook(self, book):
        if isinstance(book, ReferenceBook) or isinstance(book, ReferenceBook):
            getShelf = self.getShelfData()[0]
            getBook = self.getShelfData()[2]
            getBook.append(book)
            return showinfo("Tambah Buku", message=f"Buku baru berhasil ditambahkan pada rak {getShelf}\n\n"
                            f"{book.getBookDesc()}")
        else:
            return showinfo(message="Buku gagal ditambahkan :(")


class CompendiumShelf(Shelf):
    def __init__(self, name, shelfType):
        Shelf.__init__(self, name, shelfType)

    def addBook(self, book):
        getShelf = self.getShelfData()[0]
        getBook = self.getShelfData()[2]
        getBook.append(book)
        return showinfo("Tambah Buku", message=f"Buku baru berhasil ditambahkan pada rak {getShelf}\n\n"
                        f"{book.getBookDesc()}")


class Library:
    def __init__(self):
        self.__listShelf = [
            KnowledgeShelf("Pengetahuan01", "Pengetahuan"),
            WorldShelf("Dunia01", "Dunia"),
            MysteryShelf("Misteri01", "Misteri"),
            CompendiumShelf("Compendium01", "Compendium")
        ]

    def getListShelf(self):
        return self.__listShelf

    def addShelf(self, shelfName, shelfType):
        getShelf = self.getListShelf()
        statusShelf = self.checkShelf(shelfName)
        title = "Tambah Rak"
        if statusShelf:
            return showinfo(title, message=f"Rak dengan nama {shelfName} udah ada")
        else:
            if shelfType == "Pengetahuan":
                shelf = KnowledgeShelf(shelfName, shelfType)
            elif shelfType == "Dunia":
                shelf = WorldShelf(shelfName, shelfType)
            elif shelfType == "Misteri":
                shelf = MysteryShelf(shelfName, shelfType)
            elif shelfType == "Compendium":
                shelf = CompendiumShelf(shelfName, shelfType)
            else:
                return showinfo(title, message=f"Tidak dapat menambahkan Rak dengan jenis {shelfType}")
            getShelf.append(shelf)
            return showinfo(title, message=f"Rak baru berhasil ditambahkan\n\nNama : {shelfName}\nJenis : {shelfType}")

    def addBook(self, book, shelf):
        getShelf = self.getListShelf()
        bookData = list(book.setBookDesc())
        statusShelf = self.checkShelf(shelf)
        statusBook = self.checkBook(bookData[0])
        if statusShelf:
            if statusBook:
                return showinfo(message=f"Buku dengan nama {book.setBookDesc()[0]} sudah ada di dalam sistem")
            else:
                for i in getShelf:
                    if i.getShelfData()[0] == shelf:
                        return i.addBook(book)
        else:
            return showinfo(message=f"Buku gagal ditambahkan :(")

    def searchBook(self, book):
        getShelf = self.getListShelf()
        check = self.checkBook(book)
        title = "Cari Buku"
        for i in getShelf:
            if check:
                x = i.searchBook(book)
                if x != False:
                    return showinfo(title, message=f"Buku Ditemukan\n\n{x.getBookDesc()}")
        return showinfo(title, message=f"Buku dengan nama {book} tidak ditemukan")

    def checkShelf(self, shelfName):
        getShelf = self.getListShelf()
        if len(getShelf) != 0:
            for i in getShelf:
                if i.getShelfData()[0] == shelfName:
                    return True
            return False
        else:
            return False

    def checkBook(self, book):
        getShelf = self.getListShelf()
        for i in getShelf:
            x = i.checkBook(book)
            if x:
                return True
        else:
            return False


'''
GUI Code
'''
class Page(Frame):
    def __init__(self, *args, **kwargs):
        Frame.__init__(self, *args, **kwargs)

    def show(self):
        self.lift()


class StartPage(Page):
    def __init__(self, *args, **kwargs):
        Page.__init__(self, *args, **kwargs)

        content = Frame(self)
        title = Label(
            content, text="\n\n\nTHE GREAT LIBRARY\nFeaturing Tkinter", font="Times")
        createdBy = Label(content, text="\n\n\n\n© Ignatius Henriyanto\n2020")

        content.pack()
        title.pack()
        createdBy.pack()


class AddRakMenu(Page):
    def __init__(self, *args, **kwargs):
        Page.__init__(self, *args, **kwargs)

        content = Frame(self)

        title = Label(content, text="Form Tambah Rak")
        labelShelfName = Label(content, text="Masukkan Nama Rak Baru")
        self.nameVar = StringVar()
        inputName = Entry(content, textvariable=self.nameVar)
        labelShelfType = Label(content, text="Masukkan Jenis Rak Baru")
        self.typeVar = StringVar()
        inputType = Entry(content, textvariable=self.typeVar)
        btn = Button(content, text="Submit",  command=self.addRak)

        content.pack()
        title.grid(row="0", column="0", columnspan="2")
        labelShelfName.grid(row="1", column="0")
        inputName.grid(row="1", column="1")
        labelShelfType.grid(row="2", column="0")
        inputType.grid(row="2", column="1")
        btn.grid(row="3", columnspan="2", pady=2)

    def addRak(self):
        lib.addShelf(self.nameVar.get(), self.typeVar.get())
        self.nameVar.set("")
        self.typeVar.set("")


class AddBukuMenu(Page):
    def __init__(self, *args, **kwargs):
        Page.__init__(self, *args, **kwargs)

        content = Frame(self)

        title = Label(content, text="Form Tambah Buku")

        labelBookType = Label(content, text="Masukkan Jenis Buku")
        self.bookType = StringVar()
        inputBookType = Entry(content, textvariable=self.bookType)

        labelBookShelf = Label(content, text="Masukkan Rak")
        self.bookShelf = StringVar()
        inputBookShelf = Entry(content, textvariable=self.bookShelf)

        labelBookName = Label(content, text="Masukan Judul Buku")
        self.bookName = StringVar()
        inputBookName = Entry(content, textvariable=self.bookName)

        labelBookYear = Label(content, text="Masukkan Tahun Terbit Buku")
        self.bookYear = StringVar()
        inputBookYear = Entry(content, textvariable=self.bookYear)

        labelBookWriter = Label(content, text="Masukan Penulis Buku")
        self.bookWriter = StringVar()
        inputBookWriter = Entry(content, textvariable=self.bookWriter)

        labelBookPublisher = Label(content, text="Masukan Penerbit Buku")
        self.bookPublisher = StringVar()
        inputBookPublisher = Entry(content, textvariable=self.bookPublisher)

        labelBookExtra = Label(content, text="Masukkan Extra Attribut")
        self.bookExtra = StringVar()
        inputBookExtra = Entry(content, textvariable=self.bookExtra)

        btn = Button(content, text="Submit",  command=self.addBuku)

        content.pack()

        title.grid(row="0", column="0", columnspan="2")
        labelBookType.grid(row="1", column="0")
        inputBookType.grid(row="1", column="1")

        labelBookShelf.grid(row="2", column="0")
        inputBookShelf.grid(row="2", column="1")

        labelBookName.grid(row="3", column="0")
        inputBookName.grid(row="3", column="1")

        labelBookYear.grid(row="4", column="0")
        inputBookYear.grid(row="4", column="1")

        labelBookWriter.grid(row="5", column="0")
        inputBookWriter.grid(row="5", column="1")

        labelBookPublisher.grid(row="6", column="0")
        inputBookPublisher.grid(row="6", column="1")

        labelBookExtra.grid(row="7", column="0")
        inputBookExtra.grid(row="7", column="1")

        btn.grid(row="8", columnspan="2", pady=2)

    def addBuku(self):
        bookType = self.bookType.get()
        if bookType == "Fiksi":
            book = FictionBook(self.bookName.get(), self.bookYear.get(
            ), self.bookWriter.get(), self.bookPublisher.get(), self.bookExtra.get())
        elif bookType == "Referensi":
            book = ReferenceBook(self.bookName.get(), self.bookYear.get(
            ), self.bookWriter.get(), self.bookPublisher.get(), self.bookExtra.get())
        elif bookType == "Ensiklopedia":
            book = EncyclopediaBook(self.bookName.get(), self.bookYear.get(
            ), self.bookWriter.get(), self.bookPublisher.get(), self.bookExtra.get())
        else:
            return showinfo(message="Buku gagal ditambahkan :(")
        lib.addBook(book, self.bookShelf.get())
        self.bookType.set("")
        self.bookShelf.set("")
        self.bookName.set("")
        self.bookYear.set("")
        self.bookWriter.set("")
        self.bookPublisher.set("")
        self.bookExtra.set("")


class SearchMenu(Page):
    def __init__(self, *args, **kwargs):
        Page.__init__(self, *args, **kwargs)

        content = Frame(self)

        title = Label(content, text="Form Cari Buku")

        labelSearch = Label(content, text="Cari Judul Buku")
        self.searchVar = StringVar()
        inputSearch = Entry(content, textvariable=self.searchVar)
        btn = Button(content, text="Cari",  command=self.searchBook)

        content.pack()
        title.grid(row="0", columnspan="2")
        labelSearch.grid(row="1", column="0")
        inputSearch.grid(row="1", column="1")
        btn.grid(row="2", columnspan="2", pady=2)

    def searchBook(self):
        lib.searchBook(self.searchVar.get())
        self.searchVar.set("")


class ListMenu(Page):
    def __init__(self, *args, **kwargs):
        Page.__init__(self, *args, **kwargs)

        self.content = Frame(self)

        self.btn = Button(self, text="TAMPILKAN LIST", command=self.listView)
        self.label = Label(self.content, text="Belum ada buku dalam sistem :(")
        self.text = Text(self.content, state=DISABLED)

        self.btn.pack()
        self.content.pack()
        self.label.pack()
        self.text.pack()

    def listView(self):
        self.label.config(text="")
        self.text.config(state=NORMAL)
        self.text.delete(1.0, END)
        k = []

        data = lib.getListShelf()
        m = 0
        for i in data:
            if len(i.getShelfData()[2]) != 0:
                m += 1
        self.btn.config(text="UPDATE LIST")
        if m == 0:
            return self.label.config(text="Belum ada buku dalam sistem :(")
        self.label.config(text="")

        for j in data:
            k.append(j.getShelfData()[0])
            if len(j.getShelfData()[2]) != 0:
                for l in j.getShelfData()[2]:
                    k.append(f" - {l.setBookDesc()[0]}")
        x = "\n".join(k)
        self.text.insert(INSERT, x)
        self.text.config(state=DISABLED)


class MainView(Frame):
    def __init__(self, *args, **kwargs):
        Frame.__init__(self, *args, **kwargs)

        start = StartPage(self)
        p1 = AddRakMenu(self)
        p2 = AddBukuMenu(self)
        search = SearchMenu(self)
        listMenu = ListMenu(self)

        buttonframe = Frame(self)
        container = Frame(self)
        buttonframe.pack(side="top", fill="x", expand=False)
        container.pack(side="top", fill="both", expand=True)

        start.place(in_=container, x=0, y=0, relwidth=1, relheight=1)
        p1.place(in_=container, x=0, y=0, relwidth=1, relheight=1)
        p2.place(in_=container, x=0, y=0, relwidth=1, relheight=1)
        search.place(in_=container, x=0, y=0, relwidth=1, relheight=1)
        listMenu.place(in_=container, x=0, y=0, relwidth=1, relheight=1)

        b1 = Button(buttonframe, text="Tambah Rak", width=10, bg="red",
                    command=p1.lift)
        b2 = Button(buttonframe, text="Tambah Buku", width=10, bg="aqua",
                    command=p2.lift)
        b3 = Button(buttonframe, text="Cari Buku", width=10, bg="green2",
                    command=search.lift)
        b4 = Button(buttonframe, text="List Buku", width=10, bg="yellow",
                    command=listMenu.lift)

        b1.pack(side="left")
        b2.pack(side="left")
        b3.pack(side="left")
        b4.pack(side="left")

        start.show()


if __name__ == "__main__":
    lib = Library()
    root = Tk()
    main = MainView(root)
    main.pack(side="top", fill="both", expand=True)
    root.title("The Great Library")
    root.wm_geometry("320x250")
    root.resizable(0, 0)
    root.mainloop()
