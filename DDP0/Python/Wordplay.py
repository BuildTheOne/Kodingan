sen1 = "things have it's spu and downs"
sen2 = "a row of columns is a table"
sen3 = "right, i'm out of ideas lol"
sen4 = "wha'd uoy mean?"

#never gonna give you up, never gonna let you down

never = sen2[14:27:12] + sen1[9:11] + sen2[2:3]
gonna = sen3[2:12:9] + sen1[3:23:19] + sen2[0]
give = sen3[::-1][24:26] + sen1[9:11]
you = sen4[::-1][6:10]
up = sen1[::-1][10:12]
comma = sen3[5:6]
let = sen2[25:27] + sen1[0]
down = sen3[19:27:6]
sentence1 = never + " " + gonna + " " + give + " " + you + up
sentence2 = never + " " + gonna + " " + let + " " + you +  down

print(sentence1 + comma + " " + sentence2)