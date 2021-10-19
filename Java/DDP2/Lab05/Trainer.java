package DDP2.Lab05;

// import java.util.*;

/*
 * TO DO: 
 * Provide Constructor
 * Complete all TO DOs
 * Write setter and getter if needed
 */

public class Trainer {
    private static Pokemon[] allPokemon = new Pokemon[30];
    private static int numOfAllPokemon = 0;
    private Pokemon[] trainersPokemon = new Pokemon[5];
    private int numOfTrainersPokemon = 0;
    private String name;

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPokemon(Pokemon pokemon) {
        if (this.numOfTrainersPokemon == 5) {
            System.out.println("Ditolak, hanya bisa ada 5 pokemon");
        } else {
            this.trainersPokemon[numOfTrainersPokemon++] = pokemon;
            Trainer.allPokemon[numOfAllPokemon++] = pokemon;
        }
    }

    public static Pokemon getSpesificPokemon(String name) {
        for (int i = 0; i < numOfAllPokemon; i++) {
            if (allPokemon[i].getName().equals(name)) {
                return allPokemon[i];
            }
        }
        return null;
    }

    public static Pokemon getStrongestPokemon() {
        Pokemon pokemon = allPokemon[0];
        for (int i = 0; i < allPokemon.length; i++) {
            if (allPokemon[i] == null)
                break;
            if (allPokemon[i].getBattlePower() > pokemon.getBattlePower()) {
                pokemon = allPokemon[i];
            }
        }
        return pokemon;
    }

    public static int getTotalBattlePower() {
        int sumPower = 0;
        for (int i = 0; i < numOfAllPokemon; i++) {
            sumPower += allPokemon[i].getBattlePower();
        }
        return sumPower;
    }

    public static double getAverageBattlePower() {
        int sumPower = 0;
        for (int i = 0; i < numOfAllPokemon; i++) {
            sumPower += allPokemon[i].getBattlePower();
        }
        return (sumPower / numOfAllPokemon);
    }

    /**
     * @return string representation of the Trainer
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Daftar pokemon milik " + this.name + ": \n");
        for (Pokemon pokemon : trainersPokemon) {
            if (pokemon == null)
                break;
            builder.append(pokemon.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}