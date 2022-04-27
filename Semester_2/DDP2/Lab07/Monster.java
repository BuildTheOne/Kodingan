package DDP2.Lab07;

// TODO: perbaiki class ini sesuai konsep OOP

public abstract class Monster extends GameCharacter {

    // TODO: tambahkan data field JIKA diperlukan
    // Tidak ada data field baru di abstract class ini

    // TODO: lengkapi constructor sesuai konsep OOP
    // Cukup meng-inherit dari parent class
    public Monster(int lifePoint, int attackPoint) {
        super(lifePoint, attackPoint);
    }

    // TODO: tambahkan method-method lain seperti setter-getter dan Override method
    // jika diperlukan

    public abstract void dropItem(Player player);

    // Menambahkan abstract method sesuai UML
    public abstract void attack(GameCharacter player);
}