import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class LineDraw {
    private ArrayList<Double> xCoord = new ArrayList<>();
    private ArrayList<Double> yCoord = new ArrayList<>();
    private Group LINES;

    public void addX(double value) {
        this.xCoord.add(value);
    }
    public void addY(double value) {
        this.yCoord.add(value);
    }

    public void drawLines(int N, AnchorPane root, double multiple) {
        LINES = new Group();
        for(int i = 0;  i < N; i++) {
            Line line =  new Line(xCoord.get(i), yCoord.get(i),
                    xCoord.get((int) ((i*multiple)%N)),
                    yCoord.get((int) ((i*multiple)%N)));
            line.setStroke(Color.DARKSLATEBLUE);
            LINES.getChildren().add(line);
        }
        root.getChildren().add(LINES);

    }

    public void resetLines(AnchorPane root) {
        root.getChildren().remove(LINES);
        LINES.getChildren().clear();
    }

}
