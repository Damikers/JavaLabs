import java.util.Scanner;

interface Shape {
    Shape clone();

    void draw();
}

class Circle implements Shape {
    private String color;
    private Integer x;
    private Integer y;
    private Integer size;

    public Circle(String color, Integer x, Integer y, Integer size) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public Shape clone() {
        return new Circle(this.color, this.x, this.y, this.size);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle at (" + x + ", " + y + ") with size " + size + ".");
    }

    public String getColor() {
        return color;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getSize() {
        return size;
    }
}

class Square implements Shape {
    private String color;
    private Integer x;
    private Integer y;
    private Integer size;

    public Square(String color, Integer x, Integer y, Integer size) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public Shape clone() {
        return new Square(this.color, this.x, this.y, this.size);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " square at (" + x + ", " + y + ") with size " + size + ".");
    }

    public String getColor() {
        return color;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getSize() {
        return size;
    }
}

class Line implements Shape {
    private String color;
    private Integer x;
    private Integer y;
    private Integer size;

    public Line(String color, Integer x, Integer y, Integer size) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public Shape clone() {
        return new Line(this.color, this.x, this.y, this.size);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " line at (" + x + ", " + y + ") with size " + size + ".");
    }

    public String getColor() {
        return color;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getSize() {
        return size;
    }
}

class ShapeClient {
    private Shape shapePrototype;

    public ShapeClient(Shape shapePrototype) {
        this.shapePrototype = shapePrototype;
    }

    public Shape createShape() {
        return shapePrototype.clone();
    }
}

class ShapeController {
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            try {
                System.out.println("Enter shape type 1 - circle, 2 - Square, 3 - Line or 0 to exit:");
                Integer shapeType = Integer.parseInt(scanner.nextLine());

                if (shapeType == 0) {
                    break;
                }

                System.out.println("Enter x position of the object:");
                Integer x = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter y position of the object:");
                Integer y = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter size of the object:");
                Integer size = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter color of the object:");
                String color = scanner.nextLine();

                Shape prototype = createPrototype(shapeType, color, size, x, y);
                if (prototype == null) {
                    System.out.println("Invalid shape type.");
                    continue;
                }

                ShapeClient client = new ShapeClient(prototype);
                Shape shape = client.createShape();
                shape.draw();
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public Shape createPrototype(Integer shapeType, String color, Integer size, Integer x, Integer y) {
        if (shapeType == 1) {
            return new Circle(color, x, y, size);
        } else if (shapeType == 2) {
            return new Square(color, x, y, size);
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ShapeController controller = new ShapeController();
        controller.run();
    }
}
