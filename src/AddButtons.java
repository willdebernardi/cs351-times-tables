import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AddButtons {
    private final Button start = new Button("Start");
    private final Button pause = new Button("Stop");

    public void placeButtons(AnchorPane root, AnimationTimer timer) {
        pause.setLayoutX(50);
        start.setOnAction(event -> timer.start());
        pause.setOnAction(event -> timer.stop());

        root.getChildren().add(start);
        root.getChildren().add(pause);
    }
}
