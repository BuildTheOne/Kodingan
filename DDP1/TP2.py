# This program is made by:
# Name   : Ignatius Henriyanto Primai Renda
# No. Id : 2006525002
# Class  : DDP1-D
# This Program is created to fulfill Tugas Pemrograman 2

# OS Function is used to access an entire folder
# Random Function is used to randomize item in list
import os
import random

# Start function is used to initialize the program and
# to input player's data and difficulty
def start():
    # Print the title
    print("""
  _                                      
 | |__   ___ _ __ _ __   __ _  ___ _   _ 
 | '_ \ / _ \ '__| '_ \ / _` |/ __| | | |
 | |_) |  __/ |  | |_) | (_| | (__| |_| |
 |_.__/ \___|_|  | .__/ \__,_|\___|\__,_|
                 |_|                DALAM
  ██╗      ██╗ ██████╗  ██╗ ██╗  ██╗
  ██║      ██║ ██╔══██╗ ██║ ██║ ██╔╝
  ██║      ██║ ██████╔╝ ██║ █████╔╝ 
  ██║      ██║ ██╔══██╗ ██║ ██╔═██╗ 
  ███████╗ ██║ ██║  ██║ ██║ ██║  ██╗
  ╚══════╝ ╚═╝ ╚═╝  ╚═╝ ╚═╝ ╚═╝  ╚═╝      
    """)
    print("~"*50)
    
    # Code below is used to input the player's data and
    # initialize the starting point
    playerName = input("Masukkan nama kamu : ")
    mode = ""
    score = 0
    life = 0

    # Code below is to determine whether the player's name is valid or not
    while playerName == "null" or playerName == "":
        playerName = input("Harap gunakan nama yang valid.\n"
                           "Masukkan nama kamu : ")

    # Code below is used to input player's difficulty and its life and
    # at the same time determine whether the input is valid or not
    while mode.lower() not in ["normal", "expert"]:
        mode = input("Mode (Normal/Expert)  : ")
    if mode.lower() == "normal":
        life = 3
    elif mode.lower() == "expert":
        life = 1

    print("~"*50)
    print("Good Luck & Have Fun :)\n")

    # return the variables
    return playerName, mode, score, life

# This function is used to generate list of text file
# The writer separeted the function to generate the list of file and
# function to randomize the list so it won't be mixed the list (More explanation later)
def generateSongList():
    songList = os.listdir("lagu")
    return songList

# This function code of flow as follows :
# 1. Select a random text file (Explanation below)
# 2. Get the list of lyrics
# 3. Generate the random line number and its five lyrics line
# 4. Save the first four lyrics as question and last lyric as answer
# 5. Return the variables needed
def generateLyric(song):
    # This code is used to generate the the text file and its lyrics
    with open(f"lagu/{song}", "r") as selectedSong:
        songLyric = selectedSong.readlines()
    lyricLineLen = len(songLyric)   # This code to determine the length if list

    # This code is to generate the random number line
    # Random.randint is used so the number is randomize each time
    # (lyricLineLen - 5) is used so the random lyric line never be the last 4 lines
    selectedLyricLine = random.randint(1, (lyricLineLen - 5))

    # Initiate the variable so it wouldn't raise an error
    i = 0
    lyricList = []
    lyricString = ""

    # This while loop is used to generate the lyrics from the list based on selectedLine and
    # put it in a list
    # lyric are called that way so it would be in order
    while i < 4:
        lyric = songLyric[selectedLyricLine + i]
        lyricList.append(lyric)
        i += 1

    # First line of code below is to arrange the lyric and
    # The second one is to generate the answer
    lyricQuestion = lyricString.join(lyricList)
    lyricAnswer = songLyric[selectedLyricLine + i]
    
    # return the variable needed
    return song, lyricQuestion, lyricAnswer

# This function is to generate the high score
def highscore(name, mode, score):
    # Code below is to check whether the highscore.txt exists
    highscore = os.listdir()

    # This conditional is used if the file doesn't exist
    if "highscore.txt" not in highscore:
        with open("highscore.txt", "w") as highscore:
            a = ["normal null 0\n" + "expert null 0\n"]
            highscore.writelines(a)
    else:
        pass
    
    # Code below is used to read the content of file and
    # put in a list so it would easier to get
    with open("highscore.txt", "r") as highscore:
        lastHighscore = highscore.readlines()   # .readlines is used so the content are put in the list per lines

    # Code below is to get the current highscore, in two difficulty
    scoreNormal = lastHighscore[0]                              # To get the first line/first element on list above, which is the normal difficulty
    scoreNormal = scoreNormal.split(" ")                        # Split the string in the element that called above into list
    scoreNormalNum = scoreNormal[2]                             # To get the third element of list, which is the highscore number
    scoreNormalNum = str(scoreNormalNum).replace("\n", "")      # Replace the last char, which is \n, into empty string

    scoreExpert = lastHighscore[1]                              # To get the second line/first second on list above, which is the expert difficulty
    scoreExpert = scoreExpert.split(" ")                        # Split the string in the element that called above into list
    scoreExpertNum = scoreExpert[2]                             # To get the third element of list, which is the highscore number
    scoreExpertNum = str(scoreExpertNum).replace("\n", "")      # Replace the last char, which is \n, into empty string

    # These conditional are to determine whether the score is bigger than highscore
    if mode == "normal":
        if score <= int(scoreNormalNum):    # This conditional is used so if the score is equal, it would not be recorded
            pass
        else:
            # Record the player's data in the list of normal difficulty
            scoreNormal[1] = name      
            scoreNormal[2] = str(score) + "\n"

            # Join the list into one string, then put it in the list of highscore, then change it again into full string
            scoreNormal = " ".join(scoreNormal)
            lastHighscore[0] = scoreNormal
            newHighscore = ""
            newHighscore = "".join(lastHighscore)

            # Open the file highscore.txt and put the new highscore in the text file
            with open("highscore.txt", "w") as highscore:
                highscore.write(newHighscore)

            # Print the output and player's data in terminal
            print("\nNEW HIGH SCORE!!!")
            print("Nama : " + name)
            print("Score : " + str(score))
            print("Berhasil meraih highscore di mode " + str(mode).capitalize())
            print("~"*16 + " Thanks for playing " + "~"*16)

    # Well, basically the code of flow is same as above, the difference is the mode
    elif mode == "expert":
        if score <= int(scoreExpertNum):
            pass
        else:
            scoreExpert[1] = name
            scoreExpert[2] = str(score) + "\n"
            scoreExpert = " ".join(scoreExpert)
            lastHighscore[1] = scoreExpert
            newHighscore = ""
            newHighscore = "".join(lastHighscore)
            with open("highscore.txt", "w") as highscore:
                highscore.write(newHighscore)
            print("\nNEW HIGH SCORE!!!")
            print("Nama : " + name)
            print("Score : " + str(score))
            print("Berhasil meraih highscore di mode " + str(mode).capitalize())
            print("~"*16 + " Thanks for playing " + "~"*16)

# Declare the start function
start = start()

# Access the return variables from start
name = str(start[0])
mode = str(start[1])
score = start[2]
life = start[3]

# Generate the list of song from this function
songList = generateSongList()

# This while loop is used to generate playing stage in 5 rounds (If always won)
stage = 1
while stage < 6:
    song = random.choice(songList)  # Select the random song from list above
    lyrics = generateLyric(song)    # Generate the lyrics from selected song
    songList.remove(song)           # The said song is removed from the list so the song wouldn't called twice

    title = str(lyrics[0]).replace(".txt", "")      # Declare the title from lyrics function and replace the last char
    question = str(lyrics[1])                       # Declare the question
    answer = str(lyrics[2].replace("\n", ""))       # Declare the answer and replace the last char

    # Print the round and question in the desired form
    print("Round " + str(stage) + "\n"
          "Nyawa : " + str(life) + "\n"
          "Score : " + str(score))
    print("~"*50)
    print("Judul lagu : " + title)
    print(question)
    
    # Player input
    answerInput = input("Silahkan menebak : \n")

    # These conditional is used to determine whether the answer is correct or not
    if (answerInput.lower() == answer.lower()):     # Used to meet the task prerequisite
        score += len(answer)
        print("JAWABAN BENAR\n")
    else:
        life -= 1
        print("Jawaban SALAH")
        print("Jawaban : " + answer + "\n")

    # These conditional is used to determine what happens if these requirement meet
    if stage < 5:
        if life == 0:
            print("GAME OVER")
            print("Sayang sekali " + name + ", anda terhenti disini\nHasil Akhir : ")
            print("Score : " + str(score))
            break
    else:
        print("SELAMAT!")
        print("Anda berhasil menyelesaikan permainan\nHasil Akhir : ")
        print("Score : " + str(score))

    stage += 1

# Calculate and print the highscore
highscore(name, mode, score)