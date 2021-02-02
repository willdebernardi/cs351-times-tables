import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class Main extends Application {
    private double multiple = 2;
    private double incrementSize = 0.1;
    Canvas canvas = new Canvas(300, 300);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage primaryStage) {
        AddCircles circles = new AddCircles();
        AddButtons buttons = new AddButtons();
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREY);
        circles.outerCircle(root);
        circles.drawPoints(root);
        int numberPoints = circles.getNumberPoints();

        Slider slider = new Slider(2.0, 50.0, 2.0);
        slider.setLayoutX(150);
        slider.setLayoutY(275);
        Label sizeLabel = new Label("Increment size: " + slider.getValue());
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            incrementSize = (double) newValue;
            sizeLabel.setText("Increment size: " + new DecimalFormat("##.#").format(incrementSize));
        });
        sizeLabel.setLayoutY(275);
        sizeLabel.setLayoutX(10);


        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                // Currently throttled to 100 ms for testing purposes
                if (now - lastUpdate >= 100_000_000) {
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    circles.drawer.drawLines(numberPoints, multiple, gc);
                    multiple += incrementSize;
                    lastUpdate = now;
                }

            }
        };
        timer.start();

        root.getChildren().add(canvas);
        root.getChildren().add(slider);
        root.getChildren().add(sizeLabel);
        buttons.placeButtons(root, timer);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
