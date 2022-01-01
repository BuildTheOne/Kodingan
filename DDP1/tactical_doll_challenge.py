doll_name = input("### MY TACTICAL DOLL ###\n"
"Masukkan nama Tactical Doll : ")
firepower = int(input("Masukkan Firepower : "))
rate_fire = int(input("Masukkan Rate of Fire : "))
accuracy = int(input("Masukkan Accuracy : "))
evasion = int(input("Masukkan Evasion : "))

doll_enemy_name = input("\n### ENEMY TACTICAL DOLL ###\n"
"Masukkan nama Tactical Doll : ")
firepower_enemy = int(input("Masukkan Firepower : "))
rate_fire_enemy = int(input("Masukkan Rate of Fire : "))
accuracy_enemy = int(input("Masukkan Accuracy : "))
evasion_enemy = int(input("Masukkan Evasion : "))

dmg = (firepower * rate_fire)/60
dmg_round = str(round(dmg, 2))
combat = 30 * firepower + 40 * ((rate_fire**2)/120) + 15 * (accuracy + evasion)
combat_round = str(int(round(combat, 0)))

dmg_enemy = (firepower_enemy * rate_fire_enemy)/60
dmg_enemy_round = str(round(dmg_enemy, 2))
combat_enemy = 30 * firepower_enemy + 40 * ((rate_fire_enemy**2)/120) + 15 * (accuracy_enemy + evasion_enemy)
combat_enemy_round = str(int(round(combat_enemy, 0)))

print("\n### RESULT ###\n"
+ doll_name + "\n"
"Damage per Second : " + dmg_round + "\n"
"Combat Effectiveness : " + combat_round + "\n\n"
+ doll_enemy_name + "\n"
"Damage per Second : " + dmg_enemy_round + "\n"
"Combat Effectiveness : " + combat_enemy_round + "\n")

if dmg_round + combat_round >= dmg_enemy_round + combat_enemy_round:
    print("Keputusan : Gass lawan")
else:
    print("Keputusan : Kabuur")