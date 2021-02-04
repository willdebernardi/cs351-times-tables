import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;

public class AddBoxes {
    Button multButton = new Button("Submit mult");
    Main main = new Main();

    Color[] lineColors = {Color.RED, Color.BLUE, Color.YELLOW,
            Color.GREEN, Color.TEAL, Color.ORANGE, Color.PURPLE,
            Color.BROWN, Color.MAGENTA, Color.PINK};

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

}
