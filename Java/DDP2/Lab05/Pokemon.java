package DDP2.Lab05;

// import java.util.*;

/*
 * TO DO: 
 * Provide Constructor
 * Write setter and getter if needed
 * Complete all TO DOs
 */
public class Pokemon {
    private String name;
    private Trainer trainer;
    private int level;
    private int defaultPower;


    public Pokemon(String name, Trainer trainer, int level, int defaultPower) {
        this.name = name;
        this.trainer = trainer;
        this.level = level;
        this.defaultPower = defaultPower;
    }    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBattlePower() {
        return (defaultPower * level);
    }
    
    /**
     * @return string representation of the Pokemon
     */
    public String toString() {
        //TODO : Perbaiki agar program dapat berjalan sesuai contoh
        return String.format("%s saat ini berada di level %d memiliki battle power sebanyak %d dan dimiliki oleh %s", this.name, this.level, getBattlePower(), trainer.getName());
    }

}