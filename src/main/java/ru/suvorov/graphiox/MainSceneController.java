package ru.suvorov.graphiox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MainSceneController {
    @FXML
    private Button mainButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button upButton;
    @FXML
    private Button downButton;

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Line line;

    public MainSceneController() {
    }

    @FXML
    private void buttonClicked() {
        mainButton.setText("Click me again!");
    }

    public void buttonClickedLeft() {
        circle1.setCenterX((circle1.getCenterX() - 5));
        line.setStartX(circle1.getCenterX());
    }

    public void buttonClickedRight() {
        circle1.setCenterX(circle1.getCenterX() + 5);
        line.setStartX(circle1.getCenterX());
    }

    public void buttonClickedUp() {
        circle1.setCenterY(circle1.getCenterY() - 5);
        line.setStartY(circle1.getCenterY());
    }

    public void buttonClickedDown() {
        circle1.setCenterY(circle1.getCenterY() + 5);
        line.setStartY(circle1.getCenterY());
    }
}
