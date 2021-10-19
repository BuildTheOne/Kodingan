package DDP2.Lab07;

// TODO: identifikasi apakah class merupakan abstract class 
// TODO: identifikasi apakah method merupakan abstract method

public class Player extends GameCharacter {
    // TODO: tambahkan data field JIKA diperlukan
    // Penambahan data field sesuai UML
    private String name;

    // TODO: lengkapi constructor sesuai konsep OOP
    // Constructor dibuat berdasarkan docs
    public Player(String name) {
        super(100, 15);
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return this.name;
    }

    @Override
    public void attack(GameCharacter monster) {
        // TODO: lengkapi implementasi dari Override
        // Melakukan attack ke monster, dimana health dari monster dikurangi player attackPoint
        monster.setLifepoint(monster.getLifepoint() - this.getAttackPoint());
        System.out.println("Monster received " + this.getAttackPoint() + " point(s) damage from " + this.name + ".");
    }

    // TODO: tambahkan method-method lain seperti setter-getter dan Override method
    // jika diperlukan
    // Disesuaikan dengan docs
    public String toString() {
        return ("Player | name: " + name + " , lifePoint: " + this.getLifepoint() + ", attackPoint : "
                + this.getAttackPoint());
    }
}