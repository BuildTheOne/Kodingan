package DDP2.Lab08;

public class Dogegochi {
  int energy;

  int activityRepeat = 0; // Diubah dengan initialisasi 0 agar lebih mudah diimplementasi
  String lastActivity;

  public Dogegochi() {
    this.energy = 50;
    lastActivity = "";
  }

  public void reduceEnergy() {
    // TO-DO: Implementasi method untuk reduce energy
    // Mengurangi energi jika bermain sebanyak 20
    // If-else digunakan untuk mengecek dan memastikan nilai tidak akan di bawah 0
    // Jika setelah dikurang energi masih diatas 0, kurangi 20
    // Else, energy = 0
    // Energy = 0 tidak perlu dihandle karena ada HungerException
    if (energy - 20 > 0) {
      energy -= 20;
    } else {
      energy = 0;
    }
  }

  public void addEnergy(int quantity) {
    // TO-DO: Implementasi method untuk add energy
    // Menambah energi jika makan sebanyak 2 kali quantity
    // If-else digunakan untuk mengecek dan memastikan nilai tidak akan di atas 100
    // Jika setelah ditambah energi masih dibawah 100, tambahkan
    // Else, energy = 100
    // Energy = 100 tidak perlu dihandle karena ada FullException
    if (energy + (quantity * 2) < 100) {
      energy += (quantity * 2);
    } else {
      energy = 100;
    }
  }

  public boolean checkActivity(String activity) {
    // TO-DO: Implementasi method untuk cek aktivitas
    // Method untuk mengecek apakah activity sudah dilakukan dua kali
    // lastActivity.equals(activity) --> Apakah ingin melakukan suatu activity
    // activityRepeat == 2 --> Apakah activity tersebut sudah dilakukan dua kali
    // Jika ya, return true, else return false
    if (lastActivity.equals(activity) && activityRepeat == 2)
      return true;
    else
      return false;
  }

  // Menambahkan throws FullException dan BoredException agar bisa handle exception
  public void eat(String food, int quantity) throws FullException, BoredException {
    // TO-DO: Implementasi method untuk eat
    // Jika energi sudah 100 / sudah penuh, throw FullException tanda sudah kenyang
    if (energy == 100) {
      throw new FullException("[FAILED: Dogenya sudah buncit tidak kuat makan lagi :(]");
    } 
    // Jika eat sudah dilakukan dua kali dan ingin dilakukan lagi, throw BoredException
    else if (checkActivity("eat")) {
      throw new BoredException("[FAILED: Dogenya bosan nih, gak mau melakukan hal lain saja?]");
    } else {
      //Jika tidak, masuk ke sini
      addEnergy(quantity);      // addEnergy sesuai quantity
      lastActivity = "eat";     // set LastActivity sebagai eat
      if (activityRepeat == 2)  // jika sudah lolos ke sini, artinya aktivitas sebelumnya akan direset
        activityRepeat = 0;
      activityRepeat++;         // Increment activityRepeat
      System.out.println("[SUCCESS : Dogenya makan " + food + " sebanyak " + quantity + " dengan gembira]");
    }
  }

  // Menambahkan throws HungerException dan BoredException agar bisa handle exception
  public void play() throws HungerException, BoredException {
    // TO-DO: Implementasi method untuk play
    // Jika energi = 0, throw HungerException tanda lapar
    if (energy == 0) {
      throw new HungerException("[FAILED: Dogenya lemes butuh makan:(]");
    } 
    // Jika play sudah dilakukan dua kali dan ingin dilakukan lagi, throw BoredException
    else if (checkActivity("play")) {
      throw new BoredException("[FAILED: Dogenya bosan nih, gak mau melakukan hal lain saja?]");
    } else {
      //Jika tidak, masuk ke sini
      reduceEnergy();           // reduceEnergy sebanyak 20
      lastActivity = "play";    // set LastActivity sebagai play
      if (activityRepeat == 2)  // jika sudah lolos ke sini, artinya aktivitas sebelumnya akan direset
        activityRepeat = 0;
      activityRepeat++;         // Increment activityRepeat
      System.out.println("[SUCCESS : Dogenya bermain dengan senang hati]");
    }
  }

  public void getStatus() {
    System.out.println("Status Doge:");
    System.out.println("Energy = " + this.energy);
  }
}
