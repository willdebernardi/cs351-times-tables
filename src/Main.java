import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Main extends Application {
    double multiple = 2;
    Canvas canvas = new Canvas(300, 300);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LineDraw drawer = new LineDraw();
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREY);
        Circle c = new Circle(150, 150, 100);
        c.setStroke(Color.BLACK);
        c.setFill(null);

        root.getChildren().add(c);

        int numberPoints = 200;
        for (int i = 0; i < numberPoints; i++) {
            Circle pt = new Circle(150.0f + 100 * Math.cos(Math.PI * 2 * i / numberPoints),
                    150.0f + 100 * Math.sin(Math.PI * 2 * i / numberPoints), 3.00f);
            drawer.addX(pt.getCenterX());
            drawer.addY(pt.getCenterY());
            pt.setFill(Color.BLACK);
            root.getChildren().add(pt);
        }

        new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                // Currently throttled to 100 ms for testing purposes
                if (now - lastUpdate >= 100_000_000) {
                    gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
                    drawer.drawLines(numberPoints, multiple, gc);
                    multiple += 0.1;
                    lastUpdate=now;

                }
            }
        }.start();

        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
