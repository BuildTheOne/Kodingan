def decToBin(num):
    return (int(bin(num)[2:]))


def decToOct(num):
    return (int(oct(num)[2:]))


def decToHex(num):
    return (hex(num)[2:]).upper()


def convertToDec(num, base):
    return (int(str(num), base))


def toDefault(num, base=10):
    try:
        numDec = convertToDec(num, base)
        return (f"Decimal : {numDec}\n"
                f"Binary  : {decToBin(numDec)}\n"
                f"Octal   : {decToOct(numDec)}\n"
                f"Hex     : {decToHex(numDec)}")
    except:
        raise ValueError

def decimalToOther(num, base):
    if num == 0:
        return [0]
    digits = []
    while num:
        if (num % base > 9):
            digits.append(str(chr(int(num % base) + 55)))
        else:
            digits.append(str(int(num % base)))
        num //= base
    return "".join(digits[::-1])

def otherToOther(num, base1, base2):
    return decimalToOther(convertToDec(num, base1), base2)
