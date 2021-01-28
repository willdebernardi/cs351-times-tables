import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Main extends Application {
    ArrayList<Double> xCoord = new ArrayList<>();
    ArrayList<Double> yCoord = new ArrayList<>();
    private Group LINES;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREY);

        Circle c = new Circle(150, 150, 100);
        c.setStroke(Color.BLACK);
        c.setFill(null);

        root.getChildren().add(c);

        int N = 50;
        for (int i = 0; i < N; i++) {
            Circle pt = new Circle(150.0f + 100 * Math.cos(Math.PI * 2 * i / N),
                    150.0f + 100 * Math.sin(Math.PI * 2 * i / N), 3.00f);
            xCoord.add(pt.getCenterX());
            yCoord.add(pt.getCenterY());
            pt.setFill(Color.BLACK);
            root.getChildren().add(pt);
        }
        double multiple = 2;
        drawLines(N, root, multiple);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawLines(int N, AnchorPane root, double multiple) {
        for(int i = 0;  i < N; i++) {
            LINES = new Group();
            Line line =  new Line(xCoord.get(i), yCoord.get(i),xCoord.get((int) ((i*multiple)%N)), yCoord.get((int) ((i*multiple)%N)));
            line.setStroke(Color.DARKSLATEBLUE);
            LINES.getChildren().add(line);
            root.getChildren().add(LINES);
        }
    }
}
