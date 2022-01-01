package DDP2.Lab07;

import java.util.Random;

// TODO: identifikasi apakah class merupakan abstract class 
// TODO: identifikasi apakah method merupakan abstract method

public class Goblin extends Monster {

    // TODO: tambahkan data field JIKA diperlukan
    // Menambahkan util Random untuk method attack dan dropItem
    private Random random = new Random();

    // TODO: lengkapi constructor sesuai konsep OOP
    // Cukup meng-inherit dari parent class
    public Goblin(int lifePoint, int attackPoint) {
        super(lifePoint, attackPoint);
    }

    @Override
    public void attack(GameCharacter player) {
        // TODO: lengkapi implementasi dari Override
        // Melakukan attack ke player, dimana health dari player dikurangi monster attackPoint dan nilai acak dari 0 s/d 4
        player.setLifepoint(player.getLifepoint() - this.getAttackPoint() - random.nextInt(5));
        System.out.println(
                ((Player) player).getName() + " received " + getAttackPoint() + " point(s) damage from Kobold.");
    }

    @Override
    public void dropItem(Player player) {
        // dropItem dijalankan ketika monster dikalahkan. Kobold akan meng-drop item attackpoint
        // Nilai dari item attackpoint ditentukan secara random dari -10 s/d 10. 
        // Teknik yang saya pakai untuk mendapatkan nilai tersebut adalah meng-random nilai 10 - 10
        int getPoint = random.nextInt(10) - random.nextInt(10);
        player.setAttackPoint(player.getAttackPoint() + getPoint);
        System.out.println("Goblin has been defeated. It dropped a attack potion.");
        System.out.println("The potion added " + getPoint + " point(s) to your attackPoint");
    }

    // TODO: tambahkan method-method lain seperti setter-getter dan Override method
    // jika diperlukan

    // toString disesuaikan dengan docs
    public String toString() {
        return ("Goblin | lifePoint: " + getLifepoint() + ", attackPoint: " + getAttackPoint());
    }
}