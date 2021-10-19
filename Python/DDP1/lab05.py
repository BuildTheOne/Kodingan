# List daftar_keranjang untuk menyimpan semua keranjang
daftar_keranjang = []

def beli_keranjang(nama_keranjang, kapasitas_keranjang):
    if nama_keranjang in daftar_keranjang:
        return ("Nama keranjang sudah ada!")
    else:
        newItem = [nama_keranjang, kapasitas_keranjang]
        daftar_keranjang.append(newItem)
        return ("Berhasil menambahkan " + nama_keranjang + " dengan kapasitas " + str(kapasitas_keranjang))

def jual_keranjang(indeks):
    index = int(indeks) - 1
    if index > len(daftar_keranjang):
        return ("Nama keranjang tidak ada!")
    else:
        item = daftar_keranjang[index]
        msg = "Berhasil menjual " + item[0] + " dengan kapasitas sebesar " + item[1]
        daftar_keranjang.pop(index)
        return msg

def ubah_kapasitas(indeks, kapasitas_baru):
    index = int(indeks) - 1
    if index > len(daftar_keranjang):
        return ("Nama keranjang tidak ada!")
    else:
        item = daftar_keranjang[index]
        item[1] = kapasitas_baru
        return "Berhasil mengubah kapasitas " + item[0] + " menjadi " + item[1]

def ubah_nama(indeks, nama_baru):
    index = int(indeks) - 1
    if index > len(daftar_keranjang):
        return ("Nama keranjang tidak ada!")
    else:
        item = daftar_keranjang[index]
        oldName = item[0]
        item[0] = nama_baru
        return "Berhasil mengubah nama " + oldName + " menjadi " + item[0]

def lihat(indeks):
    index = int(indeks) - 1
    if index > len(daftar_keranjang):
        return ("Nama keranjang tidak ada!")
    else:
        item = daftar_keranjang[index]
        name = item[0]
        num = item[1]
        return "Keranjang " + name +" memiliki kapasitas sebesar " + num

def lihat_semua():
    print("Keranjang Dek Depe")
    print("-"*30)
    i = 0
    for item in daftar_keranjang:
        totalItem = ("{:<20}|  {:<10}".format(item[0], item[1]))
        print(totalItem)
        
def total_kapasitas():
    i = 0
    flatList = []
    for i in daftar_keranjang:
        item = i[1]
        flatList.append(int(item))
    return "Total kapasitas keranjang Dek Depe adalah " + str(sum(flatList))

jumlah_operasi = int(input("Masukkan banyak operasi: "))
for i in range(jumlah_operasi):
    operasi = input("Masukkan operasi: ")	# Input query sebagai 1 string
	
    # TO DO: Pecah operasi menggunakan method .split() dan tampung ke sebuah variable
    listCommand = operasi.split(" ")

    if (1 < len(listCommand)) == True:
        name = listCommand[1]
    else:
        pass
    if (2 < len(listCommand)) == True:
        quantity = listCommand[2]
    else:
        pass

    cmd = listCommand[0]
	
    if (cmd.lower() == "beli"):
        print(beli_keranjang(name, quantity))
    elif (cmd.lower() == "jual"):
        print(jual_keranjang(name))
    elif (cmd.lower() == "ubah_kapasitas"):
        print(ubah_kapasitas(name, quantity))
    elif (cmd.lower() == "ubah_nama"):
        print(ubah_nama(name, quantity))
    elif (cmd.lower() == "lihat"):
        print(lihat(name))
    elif (cmd.lower() == "lihat_semua"):
        lihat_semua()
    else:
        print(total_kapasitas())