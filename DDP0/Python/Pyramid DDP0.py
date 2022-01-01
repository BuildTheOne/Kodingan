height = int(input('masukkan tinggi piramid: '))

#pucuk
print(' '*(height-1) + '*')

#bawah
for i in range(height-1):
    print(' '*int(height-i-2) + '*' + ' '*int(2*(i+1) - 1) + '*')