package com.lab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.*;
import javafx.scene.control.ListView;

interface Shape {
    void draw(GraphicsContext gc);
    Shape clone();
}

class Circle implements Shape {
    public double x, y, radius;
    public Color color;

    public Circle(double x, double y, double radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public Shape clone() {
        return new Circle(this.x, this.y, this.radius, this.color);
    }
}

class Polygon implements Shape {
    public List<Double> xPoints;
    public List<Double> yPoints;
    public Color color;

    public Polygon(List<Double> coordinates, Color color) {
        this.xPoints = new ArrayList<>();
        this.yPoints = new ArrayList<>();
        this.color = color;

        for (int i = 0; i < coordinates.size(); i += 2) {
            xPoints.add(coordinates.get(i));
            yPoints.add(coordinates.get(i + 1));
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        double[] xPointsArray = new double[xPoints.size()];
        double[] yPointsArray = new double[yPoints.size()];

        for (int i = 0; i < xPoints.size(); i++) {
            xPointsArray[i] = xPoints.get(i);
            yPointsArray[i] = yPoints.get(i);
        }

        gc.fillPolygon(xPointsArray, yPointsArray, xPoints.size());
    }

    @Override
    public Shape clone() {
        return new Polygon(new ArrayList<>(xPoints), this.color);
    }
}

class Ellipse implements Shape {
    public double x, y, radiusX, radiusY;
    public Color color;

    public Ellipse(double x, double y, double radiusX, double radiusY, Color color) {
        this.x = x;
        this.y = y;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x - radiusX, y - radiusY, radiusX * 2, radiusY * 2);
    }

    @Override
    public Shape clone() {
        return new Ellipse(this.x, this.y, this.radiusX, this.radiusY, this.color);
    }
}

class Line implements Shape {
    public double xStart, yStart, xEnd, yEnd;
    public Color color;

    public Line(double xStart, double yStart, double xEnd, double yEnd, Color color) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.strokeLine(xStart, yStart, xEnd, yEnd);
    }

    @Override
    public Shape clone() {
        return new Line(this.xStart, this.yStart, this.xEnd, this.yEnd, this.color);
    }
}

class Rectangle implements Shape {
    public double x, y, width, height;
    public Color color;

    public Rectangle(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    @Override
    public Shape clone() {
        return new Rectangle(this.x, this.y, this.width, this.height, this.color);
    }
}

class ShapeController {
    public List<Shape> shapes;
    public GraphicsContext gc;

    public ShapeController(GraphicsContext gc) {
        this.shapes = new ArrayList<>();
        this.gc = gc;
    }

    public void createCircle(double x, double y, double radius, Color color) {
        Shape circle = new Circle(x, y, radius, color);
        shapes.add(circle);
        drawShapes();
    }

    public void createRectangle(double x, double y, double width, double height, Color color) {
        Shape rectangle = new Rectangle(x, y, width, height, color);
        shapes.add(rectangle);
        drawShapes();
    }

    public void createLine(double xStart, double yStart, double xEnd, double yEnd, Color color) {
        Shape line = new Line(xStart, yStart, xEnd, yEnd, color);
        shapes.add(line);
        drawShapes();
    }

    public void createEllipse(double x, double y, double radiusX, double radiusY, Color color) {
        Shape ellipse = new Ellipse(x, y, radiusX, radiusY, color);
        shapes.add(ellipse);
        drawShapes();
    }

    public void createPolygon(List<Double> coordinates, Color color) {
        Shape polygon = new Polygon(coordinates, color);
        shapes.add(polygon);
        drawShapes();
    }

    public void drawShapes() {
        gc.setFill(Color.WHITE);
        gc.fillRect(2, 2, gc.getCanvas().getWidth() - 6, gc.getCanvas().getHeight() - 6);
        
        for (Shape shape : shapes) {
            shape.draw(gc);
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 2, gc.getCanvas().getHeight());
        gc.fillRect(602, 0, 404, gc.getCanvas().getHeight());
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), 2);
        gc.fillRect(0, 402, gc.getCanvas().getWidth(), 404);
    }

    public void deleteShape() {
        if (shapes != null) {
            shapes.removeLast();
            drawShapes();
        }
    }

    public void deleteAllShapes() {
        if (shapes != null) {
            shapes.clear();
            drawShapes();
        }
    }

    public void changeShapeColor(Integer index, Color color) {
        if (shapes.get(index - 1) instanceof Circle) {
            Circle circle = (Circle) shapes.get(index - 1);
            circle.color = color;
        } else if (shapes.get(index - 1) instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shapes.get(index - 1);
            rectangle.color = color;
        } else if (shapes.get(index - 1) instanceof Line) {
            Line line = (Line) shapes.get(index - 1);
            line.color = color;
        } else if (shapes.get(index - 1) instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) shapes.get(index - 1);
            ellipse.color = color;
        } else if (shapes.get(index - 1) instanceof Polygon) {
            Polygon polygon = (Polygon) shapes.get(index - 1);
            polygon.color = color;
        }
        drawShapes();
    }

    public void deleteSpecificShape(Integer index) {
        shapes.remove(index - 1);
        drawShapes();
    }

    public void scaleShape(Integer index, double scale) {
        if (shapes.get(index - 1) instanceof Circle) {
            Circle circle = (Circle) shapes.get(index - 1);
            circle.radius = circle.radius * scale;
        }
        if (shapes.get(index - 1) instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shapes.get(index - 1);
            rectangle.width = rectangle.width * scale;
            rectangle.height = rectangle.height * scale;
        }
        if (shapes.get(index - 1) instanceof Line) {
            Line line = (Line) shapes.get(index - 1);
            line.xEnd = line.xEnd * scale;
            line.yEnd = line.yEnd * scale;
        }
        if (shapes.get(index - 1) instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) shapes.get(index - 1);
            ellipse.radiusX = ellipse.radiusX * scale;
            ellipse.radiusY = ellipse.radiusY * scale;
        }
        if (shapes.get(index - 1) instanceof Polygon) {
            Polygon polygon = (Polygon) shapes.get(index - 1);
            List<Double> xPoints = polygon.xPoints;
            List<Double> yPoints = polygon.yPoints;
            for (int i = 0; i < xPoints.size(); i++) {
                xPoints.set(i, xPoints.get(i) * scale);
                yPoints.set(i, yPoints.get(i) * scale);
            }
        }
        drawShapes();
    }

    public void showListWindow() {
        Stage listStage = new Stage();
        listStage.setTitle("List Window");

        ObservableList<String> shapesItems = FXCollections.observableArrayList();
        int count = 1;
        for (Shape s : shapes) {
            if (s instanceof Circle) {
                Circle circle = (Circle) s;
                shapesItems.add(count + ". " + "Circle " + ": x=" + circle.x + ", y=" + circle.y + ", radius="
                        + circle.radius + ", color=" + circle.color);
            }
            if (s instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) s;
                shapesItems.add(count + ". " + "Rectangle " + ": x=" + rectangle.x + ", y=" + rectangle.y
                        + ", width=" + rectangle.width + ", height=" + rectangle.height + ", color="
                        + rectangle.color);
            }
            if (s instanceof Line) {
                Line line = (Line) s;
                shapesItems.add(count + ". " + "Line " + ": xStart=" + line.xStart + ", yStart=" + line.yStart
                        + ", xEnd=" + line.xEnd + ", yEnd=" + line.yEnd + ", color=" + line.color);
            }
            if (s instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) s;
                shapesItems.add(count + ". " + "Ellipse " + ": x=" + ellipse.x + ", y=" + ellipse.y
                        + ", radiusX=" + ellipse.radiusX + ", radiusY=" + ellipse.radiusY + ", color="
                        + ellipse.color);
            }
            if (s instanceof Polygon) {
                Polygon polygon = (Polygon) s;
                shapesItems.add(count + ". " + "Polygon " + ": color=" + polygon.color);
            }
            count++;
        }
        ListView<String> listView = new ListView<>(shapesItems);

        Scene scene = new Scene(listView, 500, 300);
        listStage.setScene(scene);
        listStage.show();
    }
}

public class App extends Application {
    public ShapeController shapeController;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setTitle("Graphical Editor");
        primaryStage.setScene(scene);
        primaryStage.show();

        Canvas canvas = new Canvas(606, 406);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 2, canvas.getHeight());
        gc.fillRect(602, 0, 404, canvas.getHeight());
        gc.fillRect(0, 0, canvas.getWidth(), 2);
        gc.fillRect(0, 402, canvas.getWidth(), 404);
        shapeController = new ShapeController(gc);

        // Create text fields

        TextField xFieldCircle = new TextField();
        xFieldCircle.setPromptText("X");
        TextField yFieldCircle = new TextField();
        yFieldCircle.setPromptText("Y");
        TextField sizeFieldCircle = new TextField();
        sizeFieldCircle.setPromptText("Size");

        TextField xFieldRectangle = new TextField();
        xFieldRectangle.setPromptText("X");
        TextField yFieldRectangle = new TextField();
        yFieldRectangle.setPromptText("Y");
        TextField sizeFieldRectangleWidth = new TextField();
        sizeFieldRectangleWidth.setPromptText("Width");
        TextField sizeFieldRectangleLength = new TextField();
        sizeFieldRectangleLength.setPromptText("Height");

        TextField xFieldLineStart = new TextField();
        xFieldLineStart.setPromptText("X Start");
        TextField yFieldLineStart = new TextField();
        yFieldLineStart.setPromptText("Y Start");
        TextField xFieldLineEnd = new TextField();
        xFieldLineEnd.setPromptText("X End");
        TextField yFieldLineEnd = new TextField();
        yFieldLineEnd.setPromptText("Y End");

        TextField xFieldEllipse = new TextField();
        xFieldEllipse.setPromptText("X");
        TextField yFieldEllipse = new TextField();
        yFieldEllipse.setPromptText("Y");
        TextField sizeFieldEllipseX = new TextField();
        sizeFieldEllipseX.setPromptText("Size X");
        TextField sizeFieldEllipseY = new TextField();
        sizeFieldEllipseY.setPromptText("Size Y");

        TextField xyFieldPolygon = new TextField();
        xyFieldPolygon.setPromptText("XY");
        xyFieldPolygon.setPrefWidth(503);

        TextField objectTextField = new TextField();
        objectTextField.setPromptText("Object Index");

        TextField colorTextField = new TextField();
        colorTextField.setPromptText("Color");

        TextField scaleTextField = new TextField();
        scaleTextField.setPromptText("Scale");

        //

        ColorPicker colorPickerCircle = new ColorPicker();
        ColorPicker colorPickerRectangle = new ColorPicker();
        ColorPicker colorPickerLine = new ColorPicker();
        ColorPicker colorPickerEllipse = new ColorPicker();
        ColorPicker colorPickerPolygon = new ColorPicker();
        ColorPicker colorPickerAdjust = new ColorPicker();

        // Create buttons

        Button createCircleButton = new Button("Create Circle");
        Button createRectangleButton = new Button("Create Rectangle");
        Button createLineButton = new Button("Create Line");
        Button createEllipseButton = new Button("Create Ellipse");
        Button createPolygonButton = new Button("Create Polygon");
        Button deleteButton = new Button("Delete Last Shape");
        Button createAllButton = new Button("Create All Shapes");
        Button deleteAllButton = new Button("Delete All Shapes");
        Button showListButton = new Button("Show List");
        Button specificDeleteButton = new Button("Delete Shape");
        Button scaleButton = new Button("Scale");
        Button colorButton = new Button("Color");

        //

        createCircleButton.setOnAction(e -> {
            try {
                double x = Double.parseDouble(xFieldCircle.getText());
                double y = Double.parseDouble(yFieldCircle.getText());
                double size = Double.parseDouble(sizeFieldCircle.getText());
                Color color = colorPickerCircle.getValue();
                shapeController.createCircle(x, y, size, color);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }
        });

        createLineButton.setOnAction(e -> {
            try {
                double xStart = Double.parseDouble(xFieldLineStart.getText());
                double yStart = Double.parseDouble(yFieldLineStart.getText());
                double xEnd = Double.parseDouble(xFieldLineEnd.getText());
                double yEnd = Double.parseDouble(yFieldLineEnd.getText());
                Color color = colorPickerLine.getValue();
                shapeController.createLine(xStart, yStart, xEnd, yEnd, color);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }
        });

        createRectangleButton.setOnAction(e -> {
            try {
                double x = Double.parseDouble(xFieldRectangle.getText());
                double y = Double.parseDouble(yFieldRectangle.getText());
                double width = Double.parseDouble(sizeFieldRectangleWidth.getText());
                double height = Double.parseDouble(sizeFieldRectangleLength.getText());
                Color color = colorPickerRectangle.getValue();
                shapeController.createRectangle(x, y, width, height, color);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }
        });

        createEllipseButton.setOnAction(e -> {
            try {
                double x = Double.parseDouble(xFieldEllipse.getText());
                double y = Double.parseDouble(yFieldEllipse.getText());
                double sizeX = Double.parseDouble(sizeFieldEllipseX.getText());
                double sizeY = Double.parseDouble(sizeFieldEllipseY.getText());
                Color color = colorPickerEllipse.getValue();
                shapeController.createEllipse(x, y, sizeX, sizeY, color);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }
        });

        createPolygonButton.setOnAction(e -> {
            try {
                String[] coordinates = xyFieldPolygon.getText().split(",");
                ArrayList<Double> xy = new ArrayList<>();
                for (String coordinate : coordinates) {
                    xy.add(Double.parseDouble(coordinate));
                }
                Color color = colorPickerPolygon.getValue();
                if (xy.size() % 2 == 0) {
                    shapeController.createPolygon(xy, color);
                } else {
                    System.out.println("Invalid input");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }
        });

        deleteButton.setOnAction(e -> {
            try {
                shapeController.deleteShape();
            } catch (Exception ex) {
                System.out.println("Nothing to delete");
            }
        });
        
        colorButton.setOnAction(e -> {
            try {
                int index = Integer.parseInt(objectTextField.getText());
                Color color = colorPickerAdjust.getValue();
                shapeController.changeShapeColor(index, color);
            } catch (Exception ex) {
                System.out.println("Nothing to color");
            }
        });

        specificDeleteButton.setOnAction(e -> {
            try {
                int index = Integer.parseInt(objectTextField.getText());
                shapeController.deleteSpecificShape(index);
            } catch (Exception ex) {
                System.out.println("Nothing to delete");
            }
        });

        scaleButton.setOnAction(e -> {
            try {
                int index = Integer.parseInt(objectTextField.getText());
                double scale = Double.parseDouble(scaleTextField.getText());
                shapeController.scaleShape(index, scale);
            } catch (Exception ex) {
                System.out.println("Nothing to scale");
            }
        });

        deleteAllButton.setOnAction(e -> { 
            try {
                shapeController.deleteAllShapes();
            } catch (Exception ex) {
                System.out.println("Nothing to delete");
            }
        });

        showListButton.setOnAction(e -> shapeController.showListWindow());

        createAllButton.setOnAction(e -> {
            try {
                if (!xFieldCircle.getText().isEmpty() && !yFieldCircle.getText().isEmpty()
                        && !sizeFieldCircle.getText().isEmpty()) {
                    double x = Double.parseDouble(xFieldCircle.getText());
                    double y = Double.parseDouble(yFieldCircle.getText());
                    double size = Double.parseDouble(sizeFieldCircle.getText());
                    Color color = colorPickerCircle.getValue();
                    shapeController.createCircle(x, y, size, color);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }

            try {                
                if (!xFieldRectangle.getText().isEmpty() && !yFieldRectangle.getText().isEmpty()
                        && !sizeFieldRectangleWidth.getText().isEmpty()
                        && !sizeFieldRectangleLength.getText().isEmpty()) {
                    double x = Double.parseDouble(xFieldRectangle.getText());
                    double y = Double.parseDouble(yFieldRectangle.getText());
                    double width = Double.parseDouble(sizeFieldRectangleWidth.getText());
                    double height = Double.parseDouble(sizeFieldRectangleLength.getText());
                    Color color = colorPickerRectangle.getValue();
                    shapeController.createRectangle(x, y, width, height, color);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }

            try {
                if (!xFieldLineStart.getText().isEmpty() && !yFieldLineStart.getText().isEmpty()
                        && !xFieldLineEnd.getText().isEmpty() && !yFieldLineEnd.getText().isEmpty()) {
                    double xStart = Double.parseDouble(xFieldLineStart.getText());
                    double yStart = Double.parseDouble(yFieldLineStart.getText());
                    double xEnd = Double.parseDouble(xFieldLineEnd.getText());
                    double yEnd = Double.parseDouble(yFieldLineEnd.getText());
                    Color color = colorPickerLine.getValue();
                    shapeController.createLine(xStart, yStart, xEnd, yEnd, color);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }

            try {
                if (!xFieldEllipse.getText().isEmpty() && !yFieldEllipse.getText().isEmpty()
                        && !sizeFieldEllipseX.getText().isEmpty() && !sizeFieldEllipseY.getText().isEmpty()) {
                    double x = Double.parseDouble(xFieldEllipse.getText());
                    double y = Double.parseDouble(yFieldEllipse.getText());
                    double sizeX = Double.parseDouble(sizeFieldEllipseX.getText());
                    double sizeY = Double.parseDouble(sizeFieldEllipseY.getText());
                    Color color = colorPickerEllipse.getValue();
                    shapeController.createEllipse(x, y, sizeX, sizeY, color);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }

            try {
                if (!xyFieldPolygon.getText().isEmpty()) {
                    String[] coordinates = xyFieldPolygon.getText().split(",");
                    ArrayList<Double> xy = new ArrayList<>();
                    for (String coordinate : coordinates) {
                        xy.add(Double.parseDouble(coordinate));
                    }
                    Color color = colorPickerPolygon.getValue();
                    if (xy.size() % 2 == 0) {
                        shapeController.createPolygon(xy, color);
                    } else {
                        System.out.println("Invalid input");
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }
        });

        VBox controlPanel = new VBox(5);
        Region spacerC = new Region();
        spacerC.setMaxWidth(161);
        Region spacerP = new Region();
        spacerP.setMaxWidth(161);
        HBox.setHgrow(spacerC, Priority.ALWAYS);
        HBox.setHgrow(spacerP, Priority.ALWAYS);
        controlPanel.getChildren().addAll(
                new HBox(10, xFieldCircle, yFieldCircle, sizeFieldCircle, spacerC, colorPickerCircle,
                        createCircleButton),
                new HBox(10, xFieldRectangle, yFieldRectangle, sizeFieldRectangleWidth, sizeFieldRectangleLength,
                        colorPickerRectangle, createRectangleButton),
                new HBox(10, xFieldLineStart, yFieldLineStart, xFieldLineEnd, yFieldLineEnd, colorPickerLine,
                        createLineButton),
                new HBox(10, xFieldEllipse, yFieldEllipse, sizeFieldEllipseX, sizeFieldEllipseY, colorPickerEllipse,
                        createEllipseButton),
                new HBox(10, xyFieldPolygon, spacerP, colorPickerPolygon, createPolygonButton),
                new HBox(10, createAllButton, deleteButton, deleteAllButton, colorButton, showListButton),
                new HBox(10, objectTextField, colorPickerAdjust, colorButton, scaleTextField, scaleButton, specificDeleteButton));

        root.getChildren().add(canvas);
        root.getChildren().add(controlPanel);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
