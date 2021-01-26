import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Creates a ring by subtracting smaller circle from outside circle
//        Circle outsideCircle = new Circle(100,100,100);
//        Circle insideCircle = new  Circle(100,100,95);
//        Shape finalCircle = Shape.subtract(outsideCircle, insideCircle);
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

            pt.setFill(Color.BLACK);
            root.getChildren().add(pt);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
