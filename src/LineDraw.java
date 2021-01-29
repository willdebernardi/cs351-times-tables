import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class LineDraw {
    private ArrayList<Double> xCoord = new ArrayList<>();
    private ArrayList<Double> yCoord = new ArrayList<>();


    public void addX(double value) {
        this.xCoord.add(value);
    }
    public void addY(double value) {
        this.yCoord.add(value);
    }

    public void drawLines(int N, double multiple, GraphicsContext gc) {
        for(int i = 0;  i < N; i++) {
            gc.strokeLine(xCoord.get(i), yCoord.get(i),
                    xCoord.get((int) ((i*multiple)%N)),
                    yCoord.get((int) ((i*multiple)%N)));
        }
    }
}
