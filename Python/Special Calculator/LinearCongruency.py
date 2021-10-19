def linearCongruency(a, b, m):
    try:
        def findCongruency(a, b, m):
            if b == 0:
                return 0
            if a < 0:
                a = -a
                b = -b
            b %= m
            while a > m:
                a -= m
            return (m * findCongruency(m, -b, a) + b) // a
        return findCongruency(a, b, m)
    except(RecursionError):
        return False

print("Enter on the format : Ax ≡ B (mod M)")
while True:
    inputA = int(input("Enter the A number : "))
    inputB = int(input("Enter the B number : "))
    inputM = int(input("Enter the M number : "))
    print(linearCongruency(inputA, inputB, inputM)) if linearCongruency(inputA, inputB, inputM) else print("None")