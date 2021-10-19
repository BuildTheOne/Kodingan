def translate(text):
    t = text
    if "u" in t:
        t = t.replace("u", "oe")
    if "j" in t:
        t = t.replace("j", "dj")
    if "y" in t:
        t = t.replace("y", "j")
    if "c" in t:
        t = t.replace("c", "tj")

    return t

while True:
    inputText = input("Masukkan Teks : ")
    print(f"{translate(inputText)}")