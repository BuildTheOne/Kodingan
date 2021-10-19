tahun = int(input("Masukkan Tahun : "))

if (tahun%4) == 0:
    if (tahun%100) == 0:
        if (tahun%400) == 0:
            print("Tahun Kabisat")
        else:
            print("Bukan tahun Kabisat")
    else:
        if (tahun%400) == 0:
            print("tahun kabisat")
        else:
            print("tahun Kabisat")
else:
    print("Bukan Tahun Kabisat")