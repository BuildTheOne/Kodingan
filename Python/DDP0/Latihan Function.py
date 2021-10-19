def fun(*words):
    for word in words:
        length = len(word.split(" "))
        if length == 1:
            print("'" + word + "'" + " adalah kata")
        else:
            print("'" + word + "'" + " memiliki " + str(length) + " kata")

fun("Hello", "Hello World", "Saya ikut DDP0")