import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.text.DecimalFormat;

/**
 * This class handles the bulk of GUI placement and alignment.
 *
 * Each "section" of the GUI is assigned its own method.
 * The sections are each placed in either a VBox or an HBox
 * then returned and added to the AnchorPane in Main.java.
 */
public class AddBoxes {
    private final Button multButton = new Button("Submit mult");
    private final Main main = new Main();

    private final Color[] lineColors = {Color.RED, Color.BLUE, Color.YELLOW,
            Color.GREEN, Color.TEAL, Color.ORANGE, Color.PURPLE,
            Color.BROWN, Color.MAGENTA, Color.PINK};

    /**
     * This method creates the VBox which handles the changing of colors
     * @param drawer - Instance of LineDraw.java so that the line stroke
     *               value can be changed.
     * @return - The completed VBox, with proper alignment.
     */
    public VBox addColorBox (LineDraw drawer) {
        VBox colorBox = new VBox();
        Label colorLabel = new Label("Colors");
        colorBox.getChildren().add(colorLabel);
        for(int i=0; i<lineColors.length; i++) {
            Button btn = new Button(String.valueOf(i+1));
            int finalI = i;
            btn.setOnAction(event -> drawer.setColor(lineColors[finalI]));
            colorBox.getChildren().add(btn);
        }
        colorBox.setLayoutY(40);
        colorBox.setSpacing(3);

        return colorBox;
    }

    /**
     * This method creates the HBox which is responsible for
     * changing the multiplier and the number of points.
     * This is done through the usage of text boxes, and a button
     * to submit the changes.
     * @param circles - Instance of AddCircles.java to reset the drawing
     *                of nodes around the circle.
     * @param root - AnchorPane in order to draw the new set of points
     * @return - The completed HBox with the textfields, label, and buttons.
     */
    public HBox addMathBox (AddCircles circles, AnchorPane root) {
        HBox mathBox = new HBox();
        mathBox.setSpacing(3);
        Label mathLabel = new Label("Multiplier, number of points");
        TextField multText = new TextField();
        TextField pointText = new TextField();
        Button pointButton = new Button("Submit points");
        mathBox.getChildren().addAll(mathLabel, multText, pointText,
                multButton, pointButton);
        mathBox.setLayoutX(10);
        mathBox.setLayoutY(775);
        multButton.setOnAction(event -> {
            double multiple = Double.parseDouble(multText.getText());
            main.setMultiple(multiple);
        });
        pointButton.setOnAction(event -> {
            circles.setNumberPoints(Integer.parseInt(pointText.getText()));
            circles.drawer.clearCoordinates();
            circles.drawPoints(root);
        });
        return mathBox;
    }

    /**
     * Creates the HBox which handles the changing of the framerate
     * of the animation.
     * @return - The completed HBox that has a text field and button
     */
    public HBox addFramerateBox() {
        Label frameLabel = new Label("Framerate (in nanoseconds):");
        TextField frameText = new TextField();
        frameText.setText("200000000");
        Button frameButton = new Button("Submit");
        frameButton.setOnAction(event -> {
            long frameRate = Long.parseLong(frameText.getText());
            main.setFrameRate(frameRate);
        });
        HBox frameBox = new HBox();
        frameBox.getChildren().addAll(frameLabel, frameText, frameButton);
        frameBox.setSpacing(5);
        frameBox.setLayoutX(350);

        return frameBox;
    }

    /**
     * Creates the HBox which handles the changing of the increment
     * size based on the value of the slider.
     * @return - The finished HBox with a slider and label
     */
    public HBox addSliderBox() {
        HBox sliderBox = new HBox();
        Slider slider = new Slider(2.0, 50.0, 2.0);
        Label sizeLabel = new Label("Increment size: " + slider.getValue());
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double incrementSize = (double) newValue;
            sizeLabel.setText("Increment size: " + new DecimalFormat("##.#").format(incrementSize));
            main.setIncrementSize(incrementSize);
        });
        sliderBox.getChildren().addAll(sizeLabel, slider);
        sliderBox.setLayoutX(10);
        sliderBox.setLayoutY(750);
        sliderBox.setSpacing(5);

        return sliderBox;
    }

    /**
     * Creates the box which displays the specific "favorite"
     * pictures generated by the visualization.
     * @param timer - AnimationTimer so that the visualization
     *              can be paused.
     * @param canvas - Canvas so that the previous lines can be
     *               cleared.
     * @param gc - GraphicsContext so that the new lines can be
     *           drawn.
     * @param drawer - LineDraw.java instance to access the drawing
     *               methods
     * @param circles - AddCircles.java instance to access the
     *                number of points on the circle.
     * @return - The completed VBox with 5 buttons and label.
     */
    public VBox addFavoritesBox(AnimationTimer timer, Canvas canvas, GraphicsContext gc, LineDraw drawer, AddCircles circles) {
        Double[] multiples = {2.0,34.0,86.0,76.0,99.0};

        VBox favoritesBox = new VBox();
        favoritesBox.setLayoutY(350);
        Label favoritesLabel = new Label("Favorites");
        favoritesBox.getChildren().add(favoritesLabel);

        for (int i=0;i<multiples.length;i++) {
            Button btn = new Button(String.valueOf(i+1));
            int finalI = i;
            btn.setOnAction(event -> {
                double multiple = multiples[finalI];
                main.setMultiple(multiple);
                timer.stop();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawer.drawLines(circles.getNumberPoints(), multiple, gc);
            });
            favoritesBox.getChildren().add(btn);
        }
        return favoritesBox;
    }

}
