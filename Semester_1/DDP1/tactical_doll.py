doll_name = input("### REQUEST TACTICAL DOLL ###\n"
"Masukkan nama Tactical Doll : ")
firepower = int(input("Masukkan Firepower : "))
rate_fire = int(input("Masukkan Rate of Fire : "))
accuracy = int(input("Masukkan Accuracy : "))
evasion = int(input("Masukkan Evasion : "))

dmg = (firepower * rate_fire)/60
dmg_round = round(dmg, 2)
combat = 30 * firepower + 40 * ((rate_fire**2)/120) + 15 * (accuracy + evasion)
combat_round = int(round(combat, 0))

print("\n### SUCCESS ###\n"
"Tactical Doll : " + str(doll_name) + "\n"
"Firepower : " + str(firepower) + "\n"
"Rate of Fire : " + str(rate_fire) + "\n"
"Accuracy : " + str(accuracy) + "\n"
"Evasion : " + str(evasion) + "\n"
"Damage per Second : " + str(dmg_round) + "\n"
"Combat Effectiveness : " + str(combat_round))