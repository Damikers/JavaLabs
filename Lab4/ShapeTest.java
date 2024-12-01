package com.lab;

import org.junit.*;
import static org.junit.Assert.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShapeTest {
    @Test
    public void testCircleClone() {
        Circle original = new Circle(50, 200, 15, Color.RED);
        Shape clone = original.clone();
        assertNotSame(original, clone);
        assertEquals(50, (int) ((Circle) clone).x);
        assertEquals(200, (int) ((Circle) clone).y);
        assertEquals(15, (int) ((Circle) clone).radius);
        assertEquals(Color.RED, ((Circle) clone).color);

        ((Circle) clone).x = 100;
        ((Circle) clone).y = 300;
        ((Circle) clone).radius = 20;
        ((Circle) clone).color = Color.BLUE;

        assertNotEquals(100, (int) original.x);
        assertNotEquals(300, (int) original.y);
        assertNotEquals(20, (int) original.radius);
        assertNotEquals(Color.BLUE, original.color);
    }

    @Test
    public void testRectangleClone() {
        Rectangle original = new Rectangle(50, 200, 100, 50, Color.RED);
        Shape clone = original.clone();
        assertNotSame(original, clone);
        assertEquals(50, (int) ((Rectangle) clone).x);
        assertEquals(200, (int) ((Rectangle) clone).y);
        assertEquals(100, (int) ((Rectangle) clone).width);
        assertEquals(50, (int) ((Rectangle) clone).height);
        assertEquals(Color.RED, ((Rectangle) clone).color);

        ((Rectangle) clone).x = 100;
        ((Rectangle) clone).y = 300;
        ((Rectangle) clone).width = 200;
        ((Rectangle) clone).height = 100;
        ((Rectangle) clone).color = Color.BLUE;

        assertNotEquals(100, (int) original.x);
        assertNotEquals(300, (int) original.y);
        assertNotEquals(200, (int) original.width);
        assertNotEquals(100, (int) original.height);
        assertNotEquals(Color.BLUE, original.color);
    }

    @Test
    public void testEllipseClone() {
        Ellipse original = new Ellipse(50, 200, 100, 50, Color.RED);
        Shape clone = original.clone();
        assertNotSame(original, clone);
        assertEquals(50, (int) ((Ellipse) clone).x);
        assertEquals(200, (int) ((Ellipse) clone).y);
        assertEquals(100, (int) ((Ellipse) clone).radiusX);
        assertEquals(50, (int) ((Ellipse) clone).radiusY);
        assertEquals(Color.RED, ((Ellipse) clone).color);

        ((Ellipse) clone).x = 100;
        ((Ellipse) clone).y = 300;
        ((Ellipse) clone).radiusX = 200;
        ((Ellipse) clone).radiusY = 100;
        ((Ellipse) clone).color = Color.BLUE;

        assertNotEquals(100, (int) original.x);
        assertNotEquals(300, (int) original.y);
        assertNotEquals(200, (int) original.radiusX);
        assertNotEquals(100, (int) original.radiusY);
        assertNotEquals(Color.BLUE, original.color);
    }

    @Test
    public void testLineClone() {
        Line original = new Line(50, 200, 100, 300, Color.RED);
        Shape clone = original.clone();
        assertNotSame(original, clone);
        assertEquals(50, (int) ((Line) clone).xStart);
        assertEquals(200, (int) ((Line) clone).yStart);
        assertEquals(100, (int) ((Line) clone).xEnd);
        assertEquals(300, (int) ((Line) clone).yEnd);
        assertEquals(Color.RED, ((Line) clone).color);

        ((Line) clone).xStart = 100;
        ((Line) clone).yStart = 300;
        ((Line) clone).xEnd = 200;
        ((Line) clone).yEnd = 400;
        ((Line) clone).color = Color.BLUE;

        assertNotEquals(100, (int) original.xStart);
        assertNotEquals(300, (int) original.yStart);
        assertNotEquals(200, (int) original.xEnd);
        assertNotEquals(400, (int) original.yEnd);
        assertNotEquals(Color.BLUE, original.color);
    }

    @Test
    public void testShapeControllerDeleteShape() {
        Canvas canvas = new Canvas(606, 406);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ShapeController shapeController = new ShapeController(gc);
        shapeController.createCircle(50, 200, 15, Color.RED);
        shapeController.createRectangle(100, 300, 100, 50, Color.BLUE);
        assertEquals(2, shapeController.shapes.size());
        shapeController.deleteShape();
        assertEquals(1, shapeController.shapes.size());
        Shape shape = shapeController.shapes.get(0);
        assertTrue(shape instanceof Circle);
        Circle circle = (Circle) shape;
        assertEquals(50, (int) circle.x);
        assertEquals(200, (int) circle.y);
        assertEquals(15, (int) circle.radius);
        assertEquals(Color.RED, circle.color);
    }

    @Test
    public void testShapeControllerDeleteAllShapes() {
        Canvas canvas = new Canvas(606, 406);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ShapeController shapeController = new ShapeController(gc);
        shapeController.createCircle(50, 200, 15, Color.RED);
        shapeController.createRectangle(100, 300, 100, 50, Color.BLUE);
        shapeController.createEllipse(150, 400, 100, 50, Color.GREEN);
        assertEquals(3, shapeController.shapes.size());
        shapeController.deleteAllShapes();
        assertEquals(0, shapeController.shapes.size());
    }

    @Test
    public void testShapeControllerChangeShapeColor() {
        Canvas canvas = new Canvas(606, 406);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ShapeController shapeController = new ShapeController(gc);
        shapeController.createCircle(50, 200, 15, Color.RED);
        assertEquals(1, shapeController.shapes.size());
        Shape shape = shapeController.shapes.get(0);
        assertTrue(shape instanceof Circle);
        Circle circle = (Circle) shape;
        assertEquals(Color.RED, circle.color);
        shapeController.changeShapeColor(1, Color.BLUE);
        assertEquals(Color.BLUE, circle.color);
    }

    @Test
    public void testShapeControllerScaleShape() {
        Canvas canvas = new Canvas(606, 406);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ShapeController shapeController = new ShapeController(gc);
        shapeController.createCircle(50, 200, 15, Color.RED);
        assertEquals(1, shapeController.shapes.size());
        Shape shape = shapeController.shapes.get(0);
        assertTrue(shape instanceof Circle);
        Circle circle = (Circle) shape;
        assertEquals(15, (int) circle.radius);
        shapeController.scaleShape(1, 2.0);
        assertEquals(30, (int) circle.radius);
    }

    @Test
    public void testShapeControllerDeleteSpecificShape() {
        Canvas canvas = new Canvas(606, 406);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ShapeController shapeController = new ShapeController(gc);
        shapeController.createCircle(50, 200, 15, Color.RED);
        shapeController.createRectangle(100, 300, 100, 50, Color.BLUE);
        assertEquals(2, shapeController.shapes.size());
        shapeController.deleteSpecificShape(1);
        assertEquals(1, shapeController.shapes.size());
        Shape shape = shapeController.shapes.get(0);
        assertTrue(shape instanceof Rectangle);
        Rectangle rectangle = (Rectangle) shape;
        assertEquals(100, (int) rectangle.x);
        assertEquals(300, (int) rectangle.y);
        assertEquals(100, (int) rectangle.width);
        assertEquals(50, (int) rectangle.height);
        assertEquals(Color.BLUE, rectangle.color);
    }

    @Test
    public void testMain() {
        App.main(new String[0]);
    }
}
