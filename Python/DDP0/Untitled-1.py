print("Selamat datang di Benny™ EZ Converter")
print("Format input [base] [angka]")
print("ketik \"keluar\" untuk keluar program")

while(True): # infinite loop, kecuali ada break
  masukkan = input(">>> ")
  if(masukkan == ""): # jika inputnya "keluar", maka keluar
    print("\n================\nProgram Berhenti")
    break