package ru.suvorov.graphiox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MainSceneController {
    @FXML
    private Button addButton;

    @FXML
    private Pane pane;

    private int centerPane = 255;

    public MainSceneController() {}

    @FXML
    private void addButtonClicked() {
        Circle circle = new Circle(5); //todo запоминать Circle гдето храня их
                                            // todo понять как отрисовывать на окружности
                                            // todo отрисовывать заново при добавлении новой вершины


        pane.getChildren().addAll(circle);
        centerPane+=10;
        circle.setCenterX(centerPane);
        circle.setCenterY(centerPane);
        pane.requestLayout();

    }
}
