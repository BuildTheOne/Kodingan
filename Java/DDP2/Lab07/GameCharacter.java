package DDP2.Lab07;

// TODO: identifikasi apakah class merupakan abstract class 

// TODO: identifikasi apakah method merupakan abstract method

public abstract class GameCharacter {
    // TODO: tambahkan data field yang diperlukan
    // Data field disesuaikan dengan diagram UML
    private int lifepoint;
    private int attackPoint;
    private boolean isAlive;

    // TODO: lengkapi constructor sesuai konsep OOP
    // Constructor disesuaikan dengan docs
    public GameCharacter(int lifePoint, int attackPoint) {
        this.lifepoint = lifePoint;
        this.attackPoint = attackPoint;
        this.isAlive = true;
    }

    // TODO: tambahkan method-method lain seperti setter-getter dan Override method
    // jika diperlukan

    public abstract void attack(GameCharacter gameCharacter);

    // Setter Getter yang diperlukan agar data field dapat diakses
    public int getLifepoint() {
        return this.lifepoint;
    }

    // setter lifepoint digunakan di attack() dan jika lifepoint kurang dari 1, maka character mati atau isAlive is False
    public void setLifepoint(int lifepoint) {
        this.lifepoint = lifepoint;
        if (lifepoint < 1)
            this.isAlive = false;
    }

    public int getAttackPoint() {
        return this.attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public boolean getIsAlive() {
        return isAlive;
    }
}