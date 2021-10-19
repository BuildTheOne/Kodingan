package DDP2.Lab07;

import java.util.Random;

// TODO: identifikasi apakah class merupakan abstract class 

// TODO: identifikasi apakah method merupakan abstract method

public class Kobold extends Monster {

    // TODO: tambahkan data field JIKA diperlukan
    // Menambahkan util Random untuk method dropItem
    private Random random = new Random();

    // TODO: lengkapi constructor sesuai konsep OOP
    // Cukup meng-inherit dari parent class
    public Kobold(int lifePoint, int attackPoint) {
        super(lifePoint, attackPoint);
    }

    @Override
    public void attack(GameCharacter player) {
        // TODO: lengkapi implementasi dari Override
        // Melakukan attack ke player, dimana health dari player dikurangi monster attackPoint
        player.setLifepoint(player.getLifepoint() - this.getAttackPoint());
        System.out.println(
                ((Player) player).getName() + " received " + getAttackPoint() + " point(s) damage from Kobold.");
    }

    @Override
    public void dropItem(Player player) {
        // dropItem dijalankan ketika monster dikalahkan. Kobold akan meng-drop item lifepoint
        // Nilai dari item lifepoint ditentukan secara random dari -10 s/d 10. 
        // Teknik yang saya pakai untuk mendapatkan nilai tersebut adalah meng-random nilai 10 - 10
        int getPoint = (random.nextInt(10) - random.nextInt(10));   // dengan begini, akan didapatkan nilai -10 s/d 10
        player.setLifepoint(player.getLifepoint() + getPoint);      // Nilai dari point akan ditambahkan ke dalam player lifepoint
        System.out.println("Kobold has been defeated. It dropped a life potion.");
        System.out.println("The potion added " + getPoint + " point(s) to your lifePoint");
    }

    // TODO: tambahkan method-method lain seperti setter-getter dan Override method
    // jika diperlukan

    // toString disesuaikan dengan docs
    public String toString() {
        return ("Kobold| lifePoint: " + getLifepoint() + ", attackPoint: " + getAttackPoint());
    }
}