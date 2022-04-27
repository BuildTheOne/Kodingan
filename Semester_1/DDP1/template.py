import os, random

# Fungsi di bawah dimanfaatkan untuk memulai permainan. 
# Mengembalikan tuple (string, string, int, int, list)
#     string pemain - nama pemain yang dijamin sudah valid
#     string mode   - Game Mode yang dipilih pemain
#     int score     - skor awal pemain, mulai dari 0
#     int nyawa     - kesempatan bermain pemain, tergantung Game Mode yang dipilih
#     list lagu     - list berisi semua nama file dalam folder lagu
def start_game():
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
    pemain = input("masukkan username     : ")
    mode = ''
    score = 0
    nyawa = 0 
    lagu =  os.listdir("lagu")   

    while pemain == "null" or pemain == "":
        pemain = input("harap gunakan nama yang valid.\nmasukkan username     : ")
    while mode.lower() not in ["normal", "expert"]:
        mode = input("mode (normal/expert)  : ")    

    if mode.lower() == "normal":
        nyawa = 3
    else:
        nyawa = 1

    print("~"*50)
    print("Good Luck & Have Fun :)\n")
    return pemain, mode, score, nyawa, lagu

# Fungsi di bawah membuka file dalam folder sesuai nama file lagu yang terpilih.
# Mengembalikan sebuah list isi file {nama_file}.txt 
def generate_lagu(nama_file): 
    with open(f"lagu/{nama_file}", 'r') as lagu_terpilih:
        lirik = lagu_terpilih.readlines()
    return lirik

# Fungsi di bawah memeriksa apakah file highscore.txt sudah ada di directory,
# serta mengembalikan file stream highscore.txt yang telah dibuka
# Parameter:
#     purpose 'read' / 'replace' - tujuan membuka file highscore.txt
# Mengembalikan file stream highscore.txt
def get_highscore_or_create(purpose): 
    hs = os.listdir()
    highscore = None
    # check if doesn't exist, then create default 
    if "highscore.txt" not in hs:
        highscore = open("highscore.txt", 'w')
        a = ["normal null 0\n", "expert null 0\n"]
        highscore.writelines(a)

    # opens highscore.txt in desired mode
    if purpose == 'read': 
        highscore = open("highscore.txt", 'r')
    else: 
        highscore = open("highscore.txt", 'w')
    return highscore


start_game()
generate_lagu()
# get_highscore_or_create(read)
""" 
TODO: 
- Implementasi program tebak-tebakan
- Implementasi perhitungan skor
- Implementasi game over saat kesempatan habis 
- Update highscore.txt saat diperlukan
- Manfaatkan ketiga fungsi yang sudah tersedia dengan sebaik-baiknya 
- Jangan lupa close file highscore.txt yang sudah dibuka!!
"""