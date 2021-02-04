import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AddCircles {
    private static int numberPoints = 200;
    LineDraw drawer = new LineDraw();

    public void outerCircle(AnchorPane root) {
        Circle c = new Circle(400, 400, 200);
        c.setStroke(Color.BLACK);
        c.setFill(null);
        root.getChildren().add(c);
    }

    public void drawPoints(AnchorPane root) {
        for (int i = 0; i < numberPoints; i++) {
            Circle pt = new Circle(400 + 200 * Math.cos(Math.PI * 2 * i / numberPoints),
                    400 + 200 * Math.sin(Math.PI * 2 * i / numberPoints), 3.00f);
            drawer.addX(pt.getCenterX());
            drawer.addY(pt.getCenterY());
            pt.setFill(Color.BLACK);
            root.getChildren().add(pt);
        }
    }

    public int getNumberPoints() {
        return numberPoints;
    }
    public void setNumberPoints(int number){ numberPoints = number;}
}
