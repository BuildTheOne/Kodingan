package DDP2.Tugas2;

public class Tugas2 {
  public static void main(String[] args) {

    // image: hanya terdiri dari piksel '■' dan ' '
    char[][] image = { { '■', '■', '■', '■', ' ', ' ', '■', '■', '■', '■' },
        { '■', ' ', ' ', ' ', ' ', ' ', '■', ' ', ' ', ' ' }, { '■', ' ', ' ', ' ', ' ', ' ', '■', '■', '■', '■' },
        { '■', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '■' }, { '■', '■', '■', '■', ' ', ' ', '■', '■', '■', '■' } };

    System.out.println("Gambar asli: ");
    cetak(image);

    mirror(image);
    mirrorVertical(image);

    System.out.println("\nSetelah dicerminkan: ");
    cetak(image);
  }

  // mencetak gambar ke layar
  public static void cetak(char[][] image) {
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[i].length; j++) {
        System.out.print(image[i][j] + " ");
      }
      System.out.println();
    }
  }

  // lakukan pencerminan gambar secara in-place
  public static void mirror(char[][] image) {
    for (int i = 0; i < image.length; i++) {
      // Initialize a new array for each row as temp variable
      // This is done to avoid copying problem on Java
      char[] temp = new char[image[i].length];
      for (int t = 0; t < temp.length; t++) {
        temp[t] = image[i][t];
      }
      // Then, put the original array value from temp in reverse
      for (int j = 0; j < image[i].length; j++) {
        image[i][j] = temp[image[i].length - 1 - j]; // This code is to take the element array temp in reverse
      }
    }
  }

  /*
   * BONUS (20 poin): Implementasi satu method pengolahan gambar yang lain.
   * Terserah Anda: flip gambar atas-bawah, rotasi, dan sebagainya.
   */
  // I choose to make a method to mirror the array vertically
  public static void mirrorVertical(char[][] image) {
    // Similar the mirror method, but it's not need to nested loop
    // Instead, just use one loop to reverse the whole image array
    char[][] temp = new char[image.length][image.length];
    for (int t = 0; t < temp.length; t++) {
      temp[t] = image[t];
    }
    for (int i = 0; i < image.length; i++) {
      image[i] = temp[image.length - i - 1];
    }
  }
}
