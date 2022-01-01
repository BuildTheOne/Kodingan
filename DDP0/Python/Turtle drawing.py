import turtle

jumlah_sisi = int(input("Masukkan jumlah sisi : "))

for i in range(jumlah_sisi):
    turtle.forward(100)
    turtle.left(360/jumlah_sisi)

turtle.exitonclick()
turtle.mainloop