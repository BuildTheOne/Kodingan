key1 = True
key2 = False
key3 = not key1 or key2
key4 = not not key1 and not key3

if key1:
    print("Success")
    if not key4:
        print("Fail")
    else:
        if key1 == key4 and not key3:
            if key2 == True:
                print("Fail")
            else:
                if key2:
                    print("Fail")
                else:
                    print("Success")
        else:
            print("Fail")
else:
    print("Fail")