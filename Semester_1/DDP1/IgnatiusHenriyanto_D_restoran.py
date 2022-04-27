def beliDonat(pesanan):
    i = 0
    while i < len(dataList):
        if (pesanan in dataList[i].values()) == True:
            donatJual.add(dataList[i]["nama"])
            donatHarga.add(dataList[i]["harga"])
            print("{} terjual dengan harga {}".format(
                dataList[i]["nama"], dataList[i]["harga"]))
            break
        i += 1
    else:
        print(f"Tidak ada Donat DUAARRR!!! dengan nama {pesanan} :(")


dataDict = {
    "nama": "",
    "harga": "",
    "rasa": "",
}
dataList = []
data = []
donatJual = set()
donatHarga = set()

i = 1
beliDonat = int(input("Masukkan Jumlah Donat DUAARRR!!! : "))
while i < (beliDonat + 1):
    dataInput = input(f"Data {i} : ").split(" ")
    dataDict["nama"] = str(dataInput[0])
    dataDict["harga"] = str(dataInput[1])
    dataDict["rasa"] = str(dataInput[2])
    dataList.append(dataDict)
    i += 1

j = 1
jualDonat = int(input("Masukkan Jumlah Pembeli : "))
while j < (jualDonat + 1):
    pembeliInput = input(f"Pembeli {j} : ")
    pesanan = pembeliInput.split(" ")
    if pesanan[0] == "BELI_NAMA" or "BELI_RASA":
        # Entah kenapa ini masih error di program ini, tapi tidak error di program lain :')
        beliDonat(pesanan[1])
    else:
        print("Perintah Salah")
    j += 1

print("Donat terjual : " + donatJual)
print("Total pendapatan : " + sum(donatHarga))
