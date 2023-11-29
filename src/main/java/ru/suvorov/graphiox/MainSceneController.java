package ru.suvorov.graphiox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainSceneController implements Initializable {
    @FXML
    private Button addButton;

    @FXML
    private Pane pane;

    private final int centerPane = 255;
    private int counter = 0;
    private int radius = 50;

    public MainSceneController() {
    }

    @FXML
    private void addButtonClicked() {
        counter++;

        int step = 360/counter;
        pane.getChildren().clear();

        if(counter%6 == 0 && radius < 200){
            radius+=25;
        }

        for (int i = 0; i < counter; i++) {
            double cosX = Math.cos(step * Math.PI/180 * (i));
            double sinY = Math.sin(step * Math.PI/180 * (i));

            // Добавление номера вершины
            Label label = new Label(String.format("%d",i+1));
            pane.getChildren().addAll(label);
            label.setLayoutX(centerPane - 7 + (radius + 20) *  cosX);
            label.setLayoutY(centerPane - 7 + (radius + 20) * sinY);

            // Добавление вершины
            Circle circle = new Circle(5);
            pane.getChildren().addAll(circle);
            circle.setCenterX(centerPane + radius *  cosX); //X
            circle.setCenterY(centerPane + radius *  sinY); //Y
        }

        pane.requestLayout();
    }

    // open file
    @FXML
    private void selectFile(ActionEvent event) throws Exception {
        System.out.println("You clicked me!");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузить граф");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(Main.mainStage);
        if (file != null) {
            Scanner sc = new Scanner(file);
            String fileText = "";
            while (sc.hasNext()) {
                fileText += sc.nextLine() + "\n";
            }
            System.out.println(fileText);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo
    }
}
