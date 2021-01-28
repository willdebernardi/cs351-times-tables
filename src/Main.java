import javafx.application.Application;
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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREY);

        Circle c = new Circle();
        c.setCenterX(150.0f);
        c.setCenterY(150.0f);
        c.setRadius(100.0f);
        c.setStroke(Color.BLACK);
        c.setFill(null);

        root.getChildren().add(c);

        int N = 20;
        for (int i = 0; i < N; i++) {
            Circle pt = new Circle(150.0f + 100 * Math.cos(Math.PI * 2 * i / N),
                    150.0f + 100 * Math.sin(Math.PI * 2 * i / N), 3.00f);
            xCoord.add(pt.getCenterX());
            yCoord.add(pt.getCenterY());
            pt.setFill(Color.BLACK);
            root.getChildren().add(pt);
        }
//        String xCoordinates = xCoord.toString();
//        String yCoordinates = yCoord.toString();
//        System.out.println("x: " + xCoordinates);
//        System.out.println("y: " + yCoordinates);

        int multiple = 2;
        for(int i = 0;  i < 10; i++) {
            Line line =  new Line(xCoord.get(i), yCoord.get(i),xCoord.get(i*multiple), yCoord.get(i*multiple));
            root.getChildren().add(line);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
