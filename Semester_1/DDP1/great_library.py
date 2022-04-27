'''
This program is made by:
Name   : Ignatius Henriyanto Primai Renda
No. Id : 2006525002
Class  : DDP1-D
This Program is created to fulfill Tugas Pemrograman 4
'''

from time import sleep      # To give 'delay' effect on the exit

class Book:
    '''
    Parent class of all the Book Classes
    '''
    def __init__(self, title, year, writer, publisher):
        '''
        Constructor for Book Class
        '''
        self.__title = title
        self.__year = year
        self.__writer = writer
        self.__publisher = publisher

    def setBookDesc(self):
        '''
        Getter for Book Class attributes
        '''
        return self.__title, self.__year, self.__writer, self.__publisher

    def getBookDesc(self):
        '''
        Return the book description
        '''
        return print(f"Buku {self.__title}\n"
                     f"Nama Buku : {self.__title}\n"
                     f"Tahun : {self.__year}\n"
                     f"Pengarang : {self.__writer}\n"
                     f"Penerbit : {self.__publisher}")


class FictionBook(Book):
    '''
    Child class of Book and categorize books as Fiction Book
    '''
    def __init__(self, title, year, writer, publisher, genre):
        '''
        Constructor for FictionBook Class\n
        First line is inherited from Book or Parent Class\n
        Second line is unique attribute for FictionBook Class, which is genre
        '''
        Book.__init__(self, title, year, writer, publisher)
        self.__genre = genre

    def setBookGenre(self):
        '''
        Getter for Fiction Book attribute, which is genre
        '''
        return self.__genre

    def getBookDesc(self):
        '''
        Override method from Book or Parent Class and return the book description
        '''
        bookData = self.setBookDesc()
        bookGenre = self.setBookGenre()
        return (f"Buku Fiksi\n"
                f"Nama Buku : {bookData[0]}\n"
                f"Tahun : {bookData[1]}\n"
                f"Pengarang : {bookData[2]}\n"
                f"Penerbit : {bookData[3]}\n"
                f"Genre : {bookGenre}")


class ReferenceBook(Book):
    '''
    Child class of Book and categorize books as Reference Book
    '''
    def __init__(self, title, year, writer, publisher, city):
        '''
        Constructor for ReferenceBook Class\n
        First line is inherited from Book or Parent Class\n
        Second line is unique attribute for ReferenceBook Class, which is city
        '''
        Book.__init__(self, title, year, writer, publisher)
        self.__city = city

    def setBookCity(self):
        '''
        Getter for Reference Book attribute, which is city
        '''
        return self.__city

    def getBookDesc(self):
        '''
        Override method from Book or Parent Class and return the book description
        '''
        bookData = self.setBookDesc()
        bookCity = self.setBookCity()
        return (f"Buku Referensi\n"
                f"Nama Buku : {bookData[0]}\n"
                f"Tahun : {bookData[1]}\n"
                f"Pengarang : {bookData[2]}\n"
                f"Penerbit : {bookData[3]}\n"
                f"Kota Terbit : {bookCity}")


class EncyclopediaBook(Book):
    '''
    Child class of Book and categorize books as Encyclopedia Book
    '''
    def __init__(self, title, year, writer, publisher, rev):
        '''
        Constructor for EncyclopediaBook Class\n
        First line is inherited from Book or Parent Class\n
        Second line is unique attribute for EncyclopediaBook Class, which is number of revision
        '''
        Book.__init__(self, title, year, writer, publisher)
        self.__rev = rev

    def setBookRevNum(self):
        '''
        Getter for Reference Book attribute, which is number of revision
        '''
        return self.__rev

    def getBookDesc(self):
        '''
        Override method from Book or Parent Class and return the book description
        '''
        bookData = self.setBookDesc()
        bookRevNum = self.setBookRevNum()
        return (f"Buku Ensiklopedia\n"
                f"Nama Buku : {bookData[0]}\n"
                f"Tahun : {bookData[1]}\n"
                f"Pengarang : {bookData[2]}\n"
                f"Penerbit : {bookData[3]}\n"
                f"Nomor Revisi : {bookRevNum}")

class Shelf:
    '''
    Parent class of all Shelf classes
    '''
    def __init__(self, name, shelfType):
        '''
        Constructor for Shelf Class
        '''
        self.__name = name
        self.__shelfType = shelfType
        self.__listBook = []

    def getShelfData(self):
        '''
        Setter for Shelf attributes
        '''
        return self.__name, self.__shelfType, self.__listBook

    def searchBook(self, book):
        '''
        Search a book by looping the listBook\n
        If book found, return the book\n
        If not, return False
        '''
        getBookData = self.getShelfData()[2]
        for i in getBookData:
            j = i.setBookDesc()[0]
            if j == book:
                return i
        else:
            return False

    def checkBook(self, book):
        '''
        Check if the book existed by looping the listBook and return a boolean number\n
        If book found, return True\n
        If not found or the list is empty, return False
        '''
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
    '''
    Child class of Book Class and categorize the shelf as Knowledge Shelf
    '''

    def __init__(self, name, shelfType):
        '''
        Constructor of KnowledgeShelf which is inherited from parent class
        '''
        Shelf.__init__(self, name, shelfType)

    def addBook(self, book):
        '''
        Add a book to the listBook (or namely shelf)\n
        1. Check if the book type of shelf is correct (which is in this shelf is only Reference or Encyclopedia)\n
        2. If yes, append the book\n
        3. If not, return the error message
        '''
        if isinstance(book, ReferenceBook) or isinstance(book, EncyclopediaBook):
            getShelf = self.getShelfData()[0]
            getBook = self.getShelfData()[2]
            getBook.append(book)
            return print(f"Buku baru berhasil ditambahkan pada rak {getShelf}\n\n"
                         f"{book.getBookDesc()}")
        else:
            return print("Buku gagal ditambahkan :(")


class WorldShelf(Shelf):
    '''
    Child class of Book Class and categorize the shelf as World Shelf
    '''

    def __init__(self, name, shelfType):
        '''
        Constructor of WorldShelf which is inherited from parent class
        '''
        Shelf.__init__(self, name, shelfType)

    def addBook(self, book):
        '''
        Add a book to the listBook (or namely shelf)\n
        1. Check if the book type of shelf is correct (which is in this shelf is only Fiction or Encyclopedia)\n
        2. If yes, append the book\n
        3. If not, return the error message
        '''
        if isinstance(book, FictionBook) or isinstance(book, EncyclopediaBook):
            getShelf = self.getShelfData()[0]
            getBook = self.getShelfData()[2]
            getBook.append(book)
            return print(f"Buku baru berhasil ditambahkan pada rak {getShelf}\n\n"
                         f"{book.getBookDesc()}")
        else:
            return print("Buku gagal ditambahkan :(")


class MysteryShelf(Shelf):
    '''
    Child class of Book Class and categorize the shelf as Mystery Shelf
    '''

    def __init__(self, name, shelfType):
        '''
        Constructor of MysteryShelf which is inherited from parent class
        '''
        Shelf.__init__(self, name, shelfType)

    def addBook(self, book):
        '''
        Add a book to the listBook (or namely shelf)\n
        1. Check if the book type of shelf is correct (which is in this shelf is only Reference or Fiction)\n
        2. If yes, append the book\n
        3. If not, return the error message
        '''
        if isinstance(book, ReferenceBook) or isinstance(book, FictionBook):
            getShelf = self.getShelfData()[0]
            getBook = self.getShelfData()[2]
            getBook.append(book)
            return print(f"Buku baru berhasil ditambahkan pada rak {getShelf}\n\n"
                         f"{book.getBookDesc()}")
        else:
            return print("Buku gagal ditambahkan :(")


class CompendiumShelf(Shelf):
    '''
    Child class of Book Class and categorize the shelf as Compendium Shelf
    '''

    def __init__(self, name, shelfType):
        '''
        Constructor of CompendiumShelf which is inherited from parent class
        '''
        Shelf.__init__(self, name, shelfType)

    def addBook(self, book):
        '''
        Add a book to the listBook (or namely shelf)\n
        Different from other shelf, this shelf could hold all of book type
        '''
        getShelf = self.getShelfData()[0]
        getBook = self.getShelfData()[2]
        getBook.append(book)
        return print(f"Buku baru berhasil ditambahkan pada rak {getShelf}\n\n"
                     f"{book.getBookDesc()}")


class Library:
    '''
    Library Class is used as representation of The Great Library itself\n
    This Class is used as main class and most of another class is called from this class
    '''

    def __init__(self):
        '''
        Constructor of Library, which is None and only have a private attribute which contain all the shelf\n
        '''
        self.__listShelf = [
            KnowledgeShelf("Pengetahuan01", "Pengetahuan"),
            WorldShelf("Dunia01", "Dunia"),
            MysteryShelf("Misteri01", "Misteri"),
            CompendiumShelf("Compendium01", "Compendium")
        ]

    def getListShelf(self):
        '''
        Getter for listShelf attribute
        '''
        return self.__listShelf

    def addShelf(self, shelfName, shelfType):
        '''
        Method for add a shelf\n
        1. Check if the shelf existed using checkShelf method below\n
        2. If yes, return the error name\n
        3. Else, check if the type of shelf is valid\n
        4. If valid, construct a class with the respective Class and append the shelf to the list\n
        5. Else, return the error message
        '''
        getShelf = self.getListShelf()
        statusShelf = self.checkShelf(shelfName)
        if statusShelf:
            return print(f"Rak dengan nama {shelfName} sudah ada di dalam sistem")
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
                return print(f"Tidak dapat menambahkan Rak dengan jenis {shelfType}")
            getShelf.append(shelf)
            return print(f"Rak baru berhasil ditambahkan\n\nNama : {shelfName}\nJenis : {shelfType}")

    def addBook(self, book, shelf):
        '''
        Method for add a book to a shelf\n
        1. Check if the shelf is existed using checkShelf method below\n
        2. If the shelf is not existed, return the error message\n
        3. If the shelf is existed, check if the book existed using checkBook method below\n
        4. If the book existed, return the error message\n
        5. If the book not existed, add the book by searching the shelf first by looping with addBook method on the Book Class 
        '''
        getShelf = self.getListShelf()
        bookData = list(book.setBookDesc())
        statusShelf = self.checkShelf(shelf)
        statusBook = self.checkBook(bookData[0])
        if statusShelf:
            if statusBook:
                return print(f"Buku dengan nama {book.setBookDesc()[0]} sudah ada di dalam sistem")
            else:
                for i in getShelf:
                    if i.getShelfData()[0] == shelf:
                        return i.addBook(book)
        else:
            return print("Buku gagal ditambahkan :(")

    def searchBook(self, book):
        '''
        Method for search a certain book\n
        1. Check if book existed using checkBook method below\n
        2. Iterate through the all of shelf to find the book and return the getBookDesc method in the Book Class\n
        3. If the book not found, return the error message
        '''
        getShelf = self.getListShelf()
        check = self.checkBook(book)
        for i in getShelf:
            if check:
                x = i.searchBook(book)
                if x != False:
                    return print(f"Buku Ditemukan\n\n{x.getBookDesc()}")
        return print(f"Buku dengan nama {book} tidak ditemukan")

    def listBook(self):
        '''
        List all the book\n
        1. Check if there are any books\n
        2. If there is no book, return the error message\n
        3. Else, return the shelf and book inside the shelf
        '''
        getShelf = self.getListShelf()
        m = 0
        for j in getShelf:
            if len(j.getShelfData()[2]) != 0:
                m += 1
        if m == 0:
            return print("Belum ada buku di dalam sistem :(")

        print("")
        for i in getShelf:
            print(i.getShelfData()[0])
            if len(i.getShelfData()[2]) != 0:
                x = i.getShelfData()[2]
                m = []
                for j in x:
                    m.append(j.setBookDesc()[0])
                x = sorted(m)
                for k in x:
                    print(f" - {k}")

    def checkShelf(self, shelfName):
        '''
        Method for check is shelf existed by looping through listShelf and return a boolean value
        '''
        getShelf = self.getListShelf()
        if len(getShelf) != 0:
            for i in getShelf:
                if i.getShelfData()[0] == shelfName:
                    return True
            return False
        else:
            return False

    def checkBook(self, book):
        '''
        Method for check if the book existed by looping through all the shelf and through all the list of Book on the shelf and return a boolean value
        '''
        getShelf = self.getListShelf()
        for i in getShelf:
            x = i.checkBook(book)
            if x:
                return True
        else:
            return False


def main():
    '''
    Main function where all programs are executed
    '''
    lib = Library()     # Initiate the Library Class
    while True:         # Looping the input so the user could use program continously
        try:            # Try-Except is used for error handling, i.e. when the input is missed some argument
            print("Selamat datang di The The Great Library\n"
                  "Silahkan masukkan perintah!")
            # Allow user to input and split the input with space
            inputUser = input("Perintah anda : ").split(" ")
            # Assign main command input to a variable
            cmd = inputUser[0]
            if cmd == "ADD":                                    # Main command "ADD"
                # Assign second command of ADD to a variable
                item = inputUser[1]
                if item == "BUKU":
                    '''
                    Code for input a book into a shelf
                    '''
                    bookShelf = inputUser[2]
                    bookData = inputUser[3:]
                    bookName = bookData[0]
                    bookYear = bookData[1]
                    bookWriter = bookData[2]
                    bookPublisher = bookData[3]
                    bookType = bookData[4]
                    bookAttr = bookData[5]

                    '''
                    Check the book type and assign it to respective Book Class
                    '''
                    if bookType == "Fiksi":
                        bookInput = FictionBook(
                            bookName, bookYear, bookWriter, bookPublisher, bookAttr)
                    elif bookType == "Referensi":
                        bookInput = ReferenceBook(
                            bookName, bookYear, bookWriter, bookPublisher, bookAttr)
                    elif bookType == "Ensiklopedia":
                        bookInput = EncyclopediaBook(
                            bookName, bookYear, bookWriter, bookPublisher, bookAttr)
                    else:
                        print("Buku gagal ditambahkan :(\n")
                        continue
                    lib.addBook(bookInput, bookShelf)
                elif item == "RAK":
                    '''
                    Code for add a shelf to the library
                    '''
                    shelfName = inputUser[2]
                    shelfType = inputUser[3]
                    lib.addShelf(shelfName, shelfType)
                else:
                    print("Perintah tidak dikenali")
            elif cmd == "SEARCH":                               # Main command "SEARCH" to search a certain book
                lib.searchBook(inputUser[1])
            elif cmd == "LIST":                                 # Main command "LIST" to print all the shelf and book inside the shelf
                lib.listBook()
            elif cmd == "EXIT":                                 # Main command "EXIT" to exit the program
                sleep(1)
                print("Selesai, Sistem dimatikan")
                sleep(1)
                break
            else:
                print("Perintah tidak dikenali")
        except IndexError:
            print("Perintah tidak valid")
        print("")


if __name__ == "__main__":
    main()