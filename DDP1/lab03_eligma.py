code = input("Masukkan string : ")

codeList = list(code) #Diubah menjadi list agar lebih mudah dipisah antara string dan integer
noIntegers = [x for x in codeList if not (x.isdigit() or x[0] == '-' and x[1:].isdigit())]
string = "".join(noIntegers) #menghasilkan string saja

onlyIntegers = [x for x in codeList if not (x.isalpha() or x[0] == '-' and x[1:].isdigit())] #menghasilkan integer saja
shift = sum([int(i) for i in onlyIntegers]) #menjumlah integer

result = ""
for i in range(len(string)):
    char = string[i] 
    result += chr((ord(char) + shift - 97) % 26 + 97)
print(result)