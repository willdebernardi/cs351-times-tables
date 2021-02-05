import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * This class handles the drawing of the lines based on the circle.
 * This is done by keeping track of the x and y coordinates of each
 * node, and adding them to the ArrayLists, so that the startX/Y and
 * endX/Y of the lines can be created.
 */
public class LineDraw {
    private static ArrayList<Double> xCoord = new ArrayList<>();
    private static ArrayList<Double> yCoord = new ArrayList<>();
    private Color color = Color.BLACK;

    /**
     * Two methods which add values to the coordinate ArrayList.
     * @param value - The coordinate to be added to the list/
     */
    public void addX(double value) {
        xCoord.add(value);
    }
    public void addY(double value) {
        yCoord.add(value);
    }

    /**
     * This method actually draws the lines, using the relationship
     * between the start and end coordinates.
     * @param N - Number of points around the circle
     * @param multiple - What number to multiply by
     * @param gc - GraphicsContext to actually draw the lines
     */
    public void drawLines(int N, double multiple, GraphicsContext gc) {
        for(int i = 0;  i < N; i++) {
            gc.setStroke(color);
            gc.strokeLine(xCoord.get(i), yCoord.get(i),
                    xCoord.get((int) ((i*multiple)%N)),
                    yCoord.get((int) ((i*multiple)%N)));
        }
    }

    /**
     * Setter method for the color of the lines
     * @param color - Desired color of lines
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Method that clears the ArrayLists so that the number
     * of points can be changed, and the coordinates reset.
     */
    public void clearCoordinates() {
        xCoord.clear();
        yCoord.clear();
    }
}
