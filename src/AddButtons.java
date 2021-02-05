import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * This class places the start and stop buttons for the visualization.
 * An instance of this class is created in Main.java.
 */
public class AddButtons {
    private final Button start = new Button("Start");
    private final Button pause = new Button("Stop");

    /**
     * This method places the buttons and adds event listeners
     * so that the timers can be controlled.
     * @param root - AnchorPane to display the buttons on
     * @param timer - Timer so that it can be paused
     */
    public void placeButtons(AnchorPane root, AnimationTimer timer) {
        pause.setLayoutX(50);
        start.setOnAction(event -> timer.start());
        pause.setOnAction(event -> timer.stop());

        root.getChildren().add(start);
        root.getChildren().add(pause);
    }
}
