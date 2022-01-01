import itertools

input_count = int(input("Banyak perintah : "))
meong_pos_x = 0
meong_pos_y = 0
pos_list_x = [0]
pos_list_y = [0]

i = 0
while i < input_count:
    cmd = str(input("Masukkan perintah : "))
    if cmd == "U":
        meong_pos_y += 1
    elif cmd == "S":
        meong_pos_y -= 1
    elif cmd == "T":
        meong_pos_x += 1
    elif cmd == "B":
        meong_pos_x -= 1
    elif cmd == "HOME":
        break
    else:
        meong_pos_x = meong_pos_x
        meong_pos_y = meong_pos_y
        
    pos_list_x.append(meong_pos_x)
    pos_list_y.append(meong_pos_y)
    i += 1

# meong_pos_x = str(meong_pos_x)
# meong_pos_y = str(meong_pos_y)

# print("Karakter Meong Brosss berada di koordinat (" + meong_pos_x + "," + meong_pos_y + ")")

for (a, b) in zip(pos_list_x, pos_list_y):
    pos = "(" + str(a) + "," + str(b) + ")"
    print(pos)