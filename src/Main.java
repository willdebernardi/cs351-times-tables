import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This is the primary class in which the visualization is actually created.
 * This class really only has one method - start(), which handles everything.
 * The main() method simply just launches everything.
 *
 * The class extends Application, in order to leverage the features of JavaFX.
 * Once started, the visualization window will appear with various controls
 * that are described in the README. The project is best ran from the JAR.
 */
public class Main extends Application {
    private static double multiple = 2;
    private static long frameRate = 200_000_000;
    private static double incrementSize = 0.1;
    private final Canvas canvas = new Canvas(800, 800);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    /**
     * This is the primary method where all of the logic is contained.
     * The method begins by creating instances of the other classes and
     * initializing the scene. The AddCircles instance is used to create
     * the initial circles.
     *
     * Additionally, the method contains an animation timer which handles
     * the logic of drawing the lines, and is called at a variable rate, but
     * defaults to 100 ms.
     * @param primaryStage - Stage variable to be supplied to scene.
     */
    @Override
    public void start(Stage primaryStage) {
        // Creating instances of objects
        AnchorPane root = new AnchorPane();
        LineDraw drawer = new LineDraw();
        AddButtons buttons = new AddButtons();
        AddCircles circles = new AddCircles();
        AddBoxes boxes = new AddBoxes();
        // Initializing scene
        Scene scene = new Scene(root, 800, 800, Color.LIGHTGREY);
        // Drawing initial circles
        circles.outerCircle(root);
        circles.drawPoints(root);

        // Timer which draws and resets the lines as often as frameRate dictates
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

        // Adding elements to the AnchorPane, and consequently to the scene
        root.getChildren().add(canvas);
        root.getChildren().add(boxes.addMathBox(circles, root));
        root.getChildren().add(boxes.addColorBox(drawer));
        root.getChildren().add(boxes.addFramerateBox());
        root.getChildren().add(boxes.addSliderBox());
        root.getChildren().add(boxes.addFavoritesBox(timer,
                canvas, gc, drawer, circles));
        buttons.placeButtons(root, timer);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Setter method for the multiple
     * @param multiple - Double which indicates time table number
     *                 default is 2.0.
     */
    public void setMultiple(double multiple) {
        Main.multiple = multiple;
    }

    /**
     * Setter method for the update speed
     * @param frameRate - Long which represents time in nanoseconds
     *                  default is 200,000,000 ns.
     */
    public void setFrameRate(long frameRate) {
        Main.frameRate = frameRate;
    }

    /**
     * Setter method for the increment size.
     * @param incrementSize - Double value which is added to the multiple
     *                      default is 0.1.
     */
    public void setIncrementSize(double incrementSize) {
        Main.incrementSize = incrementSize;
    }

    /**
     * Method which doesn't do much except start the visualization.
     * @param args - No parameters supplied in this case
     */
    public static void main(String[] args) {
        launch(args);
    }
}
