import org.junit.*;
import java.io.*;

import static org.junit.Assert.*;

public class ShapeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCircleClone() {
        Circle original = new Circle("red", 50, 200, 15);
        Shape clone = original.clone();
        assertNotSame(original, clone);
        assertEquals("red", ((Circle) clone).getColor());
        assertEquals(50, (int) ((Circle) clone).getX());
        assertEquals(200, (int) ((Circle) clone).getY());
        assertEquals(15, (int) ((Circle) clone).getSize());
    }

    @Test
    public void testSquareClone() {
        Square original = new Square("blue", 450, 210, 70);
        Shape clone = original.clone();
        assertNotSame(original, clone);
        assertEquals("blue", ((Square) clone).getColor());
        assertEquals(450, (int) ((Square) clone).getX());
        assertEquals(210, (int) ((Square) clone).getY());
        assertEquals(70, (int) ((Square) clone).getSize());
    }

    @Test
    public void testLineClone() {
        Line original = new Line("white", 400, 300, 100);
        Shape clone = original.clone();
        assertNotSame(original, clone);
        assertEquals("white", ((Line) clone).getColor());
        assertEquals(400, (int) ((Line) clone).getX());
        assertEquals(300, (int) ((Line) clone).getY());
        assertEquals(100, (int) ((Line) clone).getSize());
    }

    @Test
    public void testCircleDraw() {
        Circle circle = new Circle("red", 50, 200, 15);
        circle.draw();
        assertEquals("Drawing a red circle at (50, 200) with size 15.\n", outContent.toString());
    }

    @Test
    public void testSquareDraw() {
        Square square = new Square("blue", 450, 210, 70);
        square.draw();
        assertEquals("Drawing a blue square at (450, 210) with size 70.\n", outContent.toString());
    }

    @Test
    public void testLineDraw() {
        Line line = new Line("white", 400, 300, 100);
        line.draw();
        assertEquals("Drawing a white line at (400, 300) with size 100.\n", outContent.toString());
    }

    @Test
    public void testShapeClient() {
        Shape circlePrototype = new Circle("red", 50, 200, 15);
        ShapeClient clientCircle = new ShapeClient(circlePrototype);
        Shape redCircle = clientCircle.createShape();
        assertNotSame(circlePrototype, redCircle);
        assertEquals("red", ((Circle) redCircle).getColor());
        assertEquals(50, (int) ((Circle) redCircle).getX());
        assertEquals(200, (int) ((Circle) redCircle).getY());
        assertEquals(15, (int) ((Circle) redCircle).getSize());

        Shape squarePrototype = new Square("blue", 450, 210, 70);
        ShapeClient clientSquare = new ShapeClient(squarePrototype);
        Shape blueSquare = clientSquare.createShape();
        assertNotSame(squarePrototype, blueSquare);
        assertEquals("blue", ((Square) blueSquare).getColor());
        assertEquals(450, (int) ((Square) blueSquare).getX());
        assertEquals(210, (int) ((Square) blueSquare).getY());
        assertEquals(70, (int) ((Square) blueSquare).getSize());

        Shape linePrototype = new Line("white", 400, 300, 100);
        ShapeClient clientLine = new ShapeClient(linePrototype);
        Shape whiteLine = clientLine.createShape();
        assertNotSame(linePrototype, whiteLine);
        assertEquals("white", ((Line) whiteLine).getColor());
        assertEquals(400, (int) ((Line) whiteLine).getX());
        assertEquals(300, (int) ((Line) whiteLine).getY());
        assertEquals(100, (int) ((Line) whiteLine).getSize());
    }

    @Test
    public void testCreatePrototype() {
        ShapeController controller = new ShapeController();
        Shape circle = controller.createPrototype(1, "red", 15, 50, 200);
        assertNotNull(circle);
        assertTrue(circle instanceof Circle);
        assertEquals("red", ((Circle) circle).getColor());

        Shape square = controller.createPrototype(2, "blue", 70, 450, 210);
        assertNotNull(square);
        assertTrue(square instanceof Square);
        assertEquals("blue", ((Square) square).getColor());

        Shape unknown = controller.createPrototype(5, "white", 100, 400, 300);
        assertNull(unknown);
    }

    @Test
    public void testRun() {
        String input = "1\n50\n200\n15\nred\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ShapeController controller = new ShapeController();
        controller.run();
        String expectedOutput = "Enter shape type 1 - circle, 2 - Square, 3 - Line or 0 to exit:\n" +
                "Enter x position of the object:\n" +
                "Enter y position of the object:\n" +
                "Enter size of the object:\n" +
                "Enter color of the object:\n" +
                "Drawing a red circle at (50, 200) with size 15.\n" +
                "Enter shape type 1 - circle, 2 - Square, 3 - Line or 0 to exit:\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
