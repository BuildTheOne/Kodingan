package DDP2.Tugas3;

import java.util.ArrayList;
import java.util.Collections;

class Person implements Comparable<Person> {
  private String name;
  private double height;
  private double weight;

  public Person(String name, double height, double weight) {
    this.name = name;
    this.height = height;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public double getHeight() {
    return height;
  }

  public double getWeight() {
    return weight;
  }

  @Override
  public int compareTo(Person p) {
    if (height == p.getHeight())
      if (weight > p.getWeight())
        return 1;
      else if (weight < p.getWeight())
        return -1;
      else
        return 0;
    else if (height > p.getHeight())
      return 1;
    else
      return -1;
  }

  public String toString() {
    return (name + ", " + height + ", " + weight);
  }
}

public class Tugas {
  public static void main(String[] args) {
    ArrayList<Person> persons = new ArrayList<>();

    persons.add(new Person("ani", 170, 70));
    persons.add(new Person("budi", 165, 65));
    persons.add(new Person("caca", 170, 60));
    persons.add(new Person("dino", 165, 58));

    Collections.sort(persons);

    for (int i = 0; i < persons.size(); i++) {
      System.out.println(persons.get(i));
    }
  }
}