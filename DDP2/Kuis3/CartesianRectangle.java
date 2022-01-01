package DDP2.Kuis3;
public class CartesianRectangle implements Comparable<CartesianRectangle> {
  private double height;
  private double width;
  private Point position;

  public CartesianRectangle(double height, double width) {
    this.height = height;
    this.width = width;
  }

  public double getHeight() {
    return height;
  }

  public double getWidth() {
    return width;
  }

  public Point getPoint() {
    return position;
  }

  @Override
  public int compareTo(CartesianRectangle c) {
    if (height == c.getHeight()) {
      if (width > c.getWidth()) {
        return 1;
      } else {
        return -1;
      }
    } else if (height > c.getHeight()) {
      return 1;
    } else {
      return -1;
    }
  }
}