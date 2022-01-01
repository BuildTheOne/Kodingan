# This program is made by:
# Name   : Ignatius Henriyanto Primai Renda
# No. Id : 2006525002
# Class  : DDP1-D
# This Program is created to fulfill Tugas Pemrograman 3

from time import sleep     # Used for 'delay' effect on the exit program 

# This function is used for check a rack on the rack list using recursion and the output is boolean
def checkRack(rackList, rack):                  
    rackListKey = list(rackList.keys())         # Get the value of racklist's list of rack
    def findRack(rackListKey, rack):            # Recursion starts here
        if len(rackListKey) == 0:               # Base case when the list is empty, return False
            return False
        if rackListKey[0] == rack:              # Case when the rack is found on the rack list, return True
            return True
        return findRack(rackListKey[1:], rack)  # Recursion Case, if conditions above is not fulfilled, check on the next value on the list
    return findRack(rackListKey, rack)          # Return the function above using the parameter given in the beginning

# This function is used for check the book on a rack using recursion and the output is boolean
def checkBook(book, rack):
    if len(rack) == 0 or rackList.get(rack) == None:    # Check the list, if it's empty, return False
        return False
    else:                                               # Else, get the values of a rack, and get the value of book on the rack, and return it in the form of list
        rck = list(rackList.get(rack).keys())

    def findBook(rck, book):                            # Recursion starts here
        if len(rck) == 0:                               # Base case when the list is empty, return False
            return False
        if rck[0] == book:                              # Case when the book is found on the rack, return True
            return True
        return findBook(rck[1:], book)                  # Recursion case, if conditions above is not fulfilled, check on the next value on the list
    return findBook(rck, book)                          # Return the function above using the parameter given in the beginning

# This function is used for add rack if it's not exist, give the warning if otherwise
def addRack(rack):
    check = checkRack(rackList, rack)                                       # Check if rack exist or not using function above
    if check:                                                               # If True, give the error message that rack is existed
        return print(f"Rak dengan nama {rack} sudah ada di dalam sistem")
    else:                                                                   # Else, add the rack as an empty dictionary object and message
        rackList[rack] = {}
        return print(f"Rak dengan nama {rack} berhasil ditambahkan")

# This function is used to add book on a rack if it's not exist, give the warning otherwise
def addBook(data):                              # The parameter is on the form of list
    # Function below is to add book on a rack
    def bookAdded():                            # This function is actually just to follow the rules "Don't Repeat Yourself" (DRY)
        rackList[rack][book] = {
            "judul": book,
            "penulis": writer,
            "tahun": year,
            "penerbit": publisher,
            "genre": genre,
        }
        return print(f"Buku dengan nama {book}, {year}, {publisher}, {genre} berhasil ditambahkan")

    # Codes below is to defined the the parameter
    rack = data[0]
    book = data[1]
    writer = data[2]
    year = int(data[3])
    publisher = data[4]
    genre = data[5]

    checkRak = checkRack(rackList, rack)        # Check if the rack exist or not using the function above
    checkBuku = checkBook(book, rack)           # Check if the book exist or not on the rack using the function above
    if checkRak:                                # If the rack exist,
        if checkBuku:                           # And the book exist, print the error messages
            return print(f"Buku dengan nama {book} sudah ada di dalam sistem")
        else:                                   # Else, add the book.
            bookAdded()
    else:                                       # If the rack don't exist, add the rack and book
        addRack(rack)
        bookAdded()

# This function is to check a book on the all of the rack
def recursive(rackList, rack):
    if len(rack) == 0:                              # Base case, if the rack is empty, return False
        return False
    else:                                           # Recursion case, check the book on the rack
        checkBuku = checkBook(book, rack[0])
        if checkBuku:                               # If the book is exist, return True and the position of the book
            return True, rack[0]
        else:
            return recursive(rackList, rack[1:])    # Recursion case, check the book on the next rack

# This function is used to move a book from a rack to another rack
def move(book, rack):
    # This function is used to move the book
    def bookMoved():                                    # This function is actually just to follow the rules "Don't Repeat Yourself" (DRY)
        x = rackList.get(bookPosition)                  # First, get the rack where the book is located
        y = x.pop(book)                                 # Then, pop the book from the rack and stored it to a variable
        rackList[rack][book] = y                        # Then, put the book on the desired rack
        return print(f"Buku dengan nama {book} dipindahkan dari rak dengan nama {bookPosition} ke rak dengan nama {rack}")      # Print the message

    checkRak = checkRack(rackList, rack)                # Check if rack is existed
    rackLstValue = list(rackList.keys())                # Get the all of rack on the list
    findBook = recursive(rackList, rackLstValue)        # Find the book using recursive function above
    try:                                                # Try-Except is used to avoid error on function recursion
        bookDataStatus = findBook[0]                    # Get the value of findBook, it's exist or not and the position if the book exist
        bookPosition = findBook[1]
        if checkRak:                                    # If the book exist, move the book using the method above
            bookMoved()
        else:                                           # Else, add the rack and move the book
            addRack(rack)
            bookMoved()
    except TypeError:                                   # Except is used if the variable bookPosition is error
        return print(f"Buku dengan nama {book} tidak ditemukan")

# This function is used to search if the book exist or not
def search(book):
    rackLst = rackList
    rackLstValue = list(rackList.keys())                        # Get the all of rack on the list
    def recursive(rackList, rack):
        if len(rack) == 0:
            return False
        else:
            checkBuku = checkBook(book, rack[0])
            if checkBuku:
                return True, rack[0]
            else:
                return recursive(rackList, rack[1:])
    try:                                                        # Try-Except is used to avoid error on function recursion
        findBook = recursive(rackLst, rackLstValue)
        bookDataStatus = findBook[0]                            # Get the value of findBook, it's exist or not and the position if the book exist
        bookPosition = findBook[1]
        if bookDataStatus:                                      # If the book exist, print the code below
            bookData = rackList[bookPosition].get(book)
            title = bookData["judul"]
            writer = bookData["penulis"]
            year = bookData["tahun"]
            publisher = bookData["penerbit"]
            genre = bookData["genre"]
            return print("Buku ditemukan\n"
                        f"Nama Buku : {title}\n"
                        f"Posisi : {bookPosition}\n"
                        f"Pengarang : {writer}\n"
                        f"Tahun terbit : {year}\n"
                        f"Penerbit : {publisher}\n"
                        f"Genre : {genre}")
        else:                                                   # Else, print the error messages
            return print(f"Buku dengan nama {book} tidak ditemukan")
    except TypeError:                                           # Except is used if the variable bookPosition is error
        return print(f"Buku dengan nama {book} tidak ditemukan")



rackList = {}                                           # Define the rack list as an empty dictionary
cmdMsg = ("Selamat datang di The The Great Library\n"   # Define the start message
          "Silahkan masukkan perintah!")

while True:                                         # While True is used so the program would loop forever until Exit
    try:                                            # Try-Except is used to avoid error on error input, e.g. the command is missing some arguments
        print(cmdMsg)
        inputCmd = input("Perintah Anda : ")        # Take the input from user
        inputMsg = inputCmd.split(" ")              # Split the input
        cmd = inputMsg[0]                           # Take the first word as main command
        if cmd == "ADD":                            # If the command "ADD",
            item = inputMsg[1]                      # Define the second command
            if item == "RAK":                       # If the second command "RAK",
                rack = inputMsg[2]                  # take the rack variable
                addRack(rack)                       # Execute the function addRack
            elif item == "BUKU":                    # If the second command "BUKU",
                book = inputMsg[2:]                 # take the rack variable
                addBook(book)                       # Execute the function addBook
            else:                                   # Else print the error message
                print("Perintah tidak dikenali")
        elif cmd == "MOVE":                         # If the command "MOVE",
            rack = inputMsg[3]                      # Define the variable rack and book
            book = inputMsg[2]
            move(book, rack)                        # Execute the function Move Book
        elif cmd == "SEARCH":                       # If the command "SEARCH",
            listRack = rackList.keys()              # Get the rack values
            book = [listRack, inputMsg[1]]          
            search(inputMsg[1])                     # Execute the function Search Book
        elif cmd == "EXIT":                         # If the command "EXIT"
            sleep(1)                                # To give the 'delay' effect
            print("Selesai, Sistem dimatikan")      # Print the message
            sleep(1)                                # To give the 'delay' effect
            break                                   # Exit the loop
        else:                                       # If the command is not identified, print the error message
            print("Perintah tidak dikenali")
        print("")                                   # Give the enter like in the example

    except IndexError:                              # Except is used if the command is missing some arguments
        print("Perintah invalid\n")
    except ValueError:
        print("Perintah invalid\n")
