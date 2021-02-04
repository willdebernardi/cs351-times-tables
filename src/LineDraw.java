import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class LineDraw {
    private static ArrayList<Double> xCoord = new ArrayList<>();
    private static ArrayList<Double> yCoord = new ArrayList<>();
    private Color color = Color.BLACK;

    public void addX(double value) {
        xCoord.add(value);
    }
    public void addY(double value) {
        yCoord.add(value);
    }

    public void drawLines(int N, double multiple, GraphicsContext gc) {
        for(int i = 0;  i < N; i++) {
            gc.setStroke(color);
            gc.strokeLine(xCoord.get(i), yCoord.get(i),
                    xCoord.get((int) ((i*multiple)%N)),
                    yCoord.get((int) ((i*multiple)%N)));
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void clearCoordinates() {
        xCoord.clear();
        yCoord.clear();
    }
}
