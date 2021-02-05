import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class handles the creation of the outer circle
 * as well as the nodes around the circle.
 */
public class AddCircles {
    private static int numberPoints = 200;
    /**
     * Has an instance of LineDraw.java so that the
     * drawer can have access to the nodes placed around the circle.
     * Also used to access the drawLines() method.
     */
    public LineDraw drawer = new LineDraw();

    /**
     * This method simply creates the outer circle
     * @param root - AnchorPane which the circle is
     *             displayed upon.
     */
    public void outerCircle(AnchorPane root) {
        Circle c = new Circle(400, 400, 200);
        c.setStroke(Color.BLACK);
        c.setFill(null);
        root.getChildren().add(c);
    }

    /**
     * This method draws the nodes around the circumference of the
     * outer circle, and sends their coordinates to the ArrayLists
     * in LineDraw.java.
     * @param root - AnchorPane which the nodes are
     *             displayed upon.
     */
    public void drawPoints(AnchorPane root) {
        for (int i = 0; i < numberPoints; i++) {
            // Formula which places nodes around the circle based on desired
            // number of points.
            Circle pt = new Circle(400 + 200 * Math.cos(Math.PI * 2 * i / numberPoints),
                    400 + 200 * Math.sin(Math.PI * 2 * i / numberPoints), 3.00f);
            // Adding of coordinates to the ArrayLists
            drawer.addX(pt.getCenterX());
            drawer.addY(pt.getCenterY());
            pt.setFill(Color.BLACK);
            root.getChildren().add(pt);
        }
    }

    /**
     * Getter method for the number of points around the circle
     * @return - int value which represents number of nodes
     */
    public int getNumberPoints() {
        return numberPoints;
    }

    /**
     * Setter method for number of points, when changing it via
     * the text box in AddBoxes.java.
     * @param number - desired number of points
     */
    public void setNumberPoints(int number){ numberPoints = number;}
}
