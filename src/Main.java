import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    private static double multiple = 2;
    private static long frameRate = 200_000_000;
    private static double incrementSize = 0.1;
    Canvas canvas = new Canvas(800, 800);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        LineDraw drawer = new LineDraw();
        AddButtons buttons = new AddButtons();
        Scene scene = new Scene(root, 800, 800, Color.LIGHTGREY);
        AddCircles circles = new AddCircles();
        AddBoxes boxes = new AddBoxes();
        circles.outerCircle(root);
        circles.drawPoints(root);




        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= frameRate) {
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    drawer.drawLines(circles.getNumberPoints(), multiple, gc);
                    multiple += incrementSize;
                    lastUpdate = now;
                }
            }
        };



        root.getChildren().add(canvas);
        root.getChildren().add(boxes.addMathBox(circles, root));
        root.getChildren().add(boxes.addColorBox(drawer));
        root.getChildren().add(boxes.addFramerateBox());
        root.getChildren().add(boxes.addSliderBox());
        root.getChildren().add(boxes.addFavoritesBox(timer, canvas, gc, drawer, circles));
        buttons.placeButtons(root, timer);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setMultiple(double multiple) {
        Main.multiple = multiple;
    }

    public void setFrameRate(long frameRate) {
        Main.frameRate = frameRate;
    }

    public void setIncrementSize(double incrementSize) {
        Main.incrementSize = incrementSize;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
