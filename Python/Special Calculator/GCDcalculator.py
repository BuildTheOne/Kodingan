import math

def findGCD(num1, num2):
    return (math.gcd(num1, num2))

def findLCM(num1, num2):
    return (abs(num1*num2) // math.gcd(num1, num2))

