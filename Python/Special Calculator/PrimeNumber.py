def checkPrime(num):
    if num < 2:
        return False
    else:
        for i in range(2, num):
            if num % i == 0:
                return False
        else:
            return True

while True:
    num = int(input("Input the number : "))
    print("Prime") if checkPrime(num) else print("Not Prime")