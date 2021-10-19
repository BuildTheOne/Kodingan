import math
import datetime

date = input("Enter the date : ")
month = input("Enter the month (number) : ")
year = input("Enter the year : ")

date = int(date)
month = int(month)
year = str(year)

if month == 1:
    if int(year) % 4 == 0:
        month = 4
    else:
        month = 3
elif month == 2:
    if int(year) % 4 == 0:
        month = 1
    else:
        month = 0
elif month == 3:
    month = 0
elif month == 4:
    month = 4
elif month == 5:
    month = 2
elif month == 6:
    month = 6
elif month == 7:
    month = 4
elif month == 8:
    month = 1
elif month == 9:
    month = 5
elif month == 10:
    month = 3
elif month == 11:
    month = 0
elif month == 12:
    month = 5

a = int(year[0:2])
if a == 15 or a == 19 or a == 23:
    a = 3
elif a == 16 or a == 20 or a == 4:
    a = 2
elif a == 17 or a == 21 or a == 25:
    a = 0
elif a == 18 or a == 22 or a == 2600:
    a = 5
else:
    print("Unknown Year")
b = math.floor(int(year[2:4])/12)
c = int(year[2:4]) % 12
d = math.floor(c/4)
e = a + b + c + d
f = e % 7
g = (((date-month) % 7) + f) % 7


def result(day):
    x = datetime.datetime(int(year), month, date)
    print(x.strftime("On %d %B %Y, the day is " + day))


if g == 0:
    result("Sunday")
if g == 1:
    result("Monday")
if g == 2:
    result("Tuesday")
if g == 3:
    result("Wednesday")
if g == 4:
    result("Thursday")
if g == 5:
    result("Friday")
if g == 6:
    result("Saturday")
