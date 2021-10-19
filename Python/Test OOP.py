class Hero:
    def __init__(self, name, health, power, defense):
        self.name = name
        self.health = health
        self.power = power
        self.defense = defense

    def attack(self, Hero):
       print(self.name + " attack " + Hero.name)
       Hero.health -= (Hero.defense/self.power)
       print(Hero.name + " health reduced to " + str(Hero.health))

    # def attacked(self):
    #     print(self.name + " diserang")

hero1 = Hero("Hero 1",100,10,5)
hero2 = Hero("Hero 2",100,5,10)

# print("Welcome to The Game.\n"
# "Your goal is to defeat all the enemies.\n\n"
# "Type Start to start the game.\n"
# "Type Exit to close the game.\n"
# "Type Hero to see the list of heroes.\n")
# input_user = input("Choose your input : ")

hero1.attack(hero2)
hero2.attack(hero1)