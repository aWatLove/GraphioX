package ru.suvorov.graphiox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javafx.scene.paint.Color.WHITE;

public class MainSceneController implements Initializable {
    private MathGraph mathGraph;
    private ArrayList<Circle> circles;

    @FXML
    private Button addButton;

    @FXML
    private Label exceptionLabelAddEdge;

    @FXML
    private TextField vertex1;

    @FXML
    private TextField vertex2;

    @FXML
    private TextField weight;

    @FXML
    private Pane pane;

    private final int centerPane = 255;
    private int counter = 0;
    private int radius = 100;

    public MainSceneController() {
    }

    @FXML
    private void addButtonClicked() {
        if (mathGraph == null) {
            mathGraph = new MathGraph();
        }
        mathGraph.addVertex();
        circles = new ArrayList<>();
        counter++;

        int step = 360 / counter;
        pane.getChildren().clear();

        if (counter % 6 == 0 && radius < 200) {
            radius += 25;
        }

        for (int i = 0; i < counter; i++) {
            double cosX = Math.cos(step * Math.PI / 180 * (i));
            double sinY = Math.sin(step * Math.PI / 180 * (i));

            // Добавление номера вершины
            Label label = new Label(String.format("%d", i + 1));
            pane.getChildren().addAll(label);
            label.setLayoutX(centerPane - 7 + (radius + 20) * cosX);
            label.setLayoutY(centerPane - 7 + (radius + 20) * sinY);

            // Добавление вершины
            Circle circle = new Circle(5);
            pane.getChildren().addAll(circle);
            circles.add(circle);
            circle.setAccessibleText("" + i + 1);
            circle.setCenterX(centerPane + radius * cosX); //X
            circle.setCenterY(centerPane + radius * sinY); //Y
        }
        renderEdges();
    }

    private void renderEdges() {
        for (int i = 0; i < mathGraph.getMatrix().size(); i++) {
            for (int j = 0; j < mathGraph.getMatrix().size(); j++) {
                if (mathGraph.getMatrix().get(i).get(j) != 0) {
                    Line line = new Line(circles.get(i).getCenterX(), circles.get(i).getCenterY(), circles.get(j).getCenterX(), circles.get(j).getCenterY());
                    Label label = new Label(String.format("%.2f", mathGraph.getMatrix().get(i).get(j)));
                    pane.getChildren().add(line);
                    pane.getChildren().add(label);

                    double xCordLabel = Math.abs(circles.get(i).getCenterX() - circles.get(j).getCenterX()) / 2;
                    xCordLabel = Math.min(circles.get(i).getCenterX(), circles.get(j).getCenterX()) + xCordLabel;
                    double yCordLabel = Math.abs(circles.get(i).getCenterY() - circles.get(j).getCenterY()) / 2;
                    yCordLabel = Math.min(circles.get(i).getCenterY(), circles.get(j).getCenterY()) + yCordLabel;

                    label.setLayoutX(xCordLabel - 10);
                    label.setLayoutY(yCordLabel - 10);

                    label.setBackground(Background.fill(Paint.valueOf("#ffffff")));
                    pane.requestLayout();
                }
            }
        }
    }

    // open file
    @FXML
    private void selectFile(ActionEvent event) throws Exception {
//        System.out.println("You clicked me!");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузить граф");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Файлы txt (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(Main.mainStage);
        if (file != null) {
            Scanner sc = new Scanner(file);
            String fileText = "";
            while (sc.hasNext()) {
                fileText += sc.nextLine() + "\n";
            }
            mathGraph = new MathGraph(fileText);

            System.out.println(fileText);

        }
    }

    public void saveFile() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extMatrixFilter = new FileChooser.ExtensionFilter("Матрица смежности (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extMatrixFilter);
        FileChooser.ExtensionFilter extListFilter = new FileChooser.ExtensionFilter("Лист смежности (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extListFilter);


        //Show save file dialog
        File file = fileChooser.showSaveDialog(Main.mainStage);
        if (fileChooser.getSelectedExtensionFilter() == extListFilter) {


        }
        if (file != null) {
            if (mathGraph != null)
                if (fileChooser.getSelectedExtensionFilter() == extListFilter)
                    saveTextToFile(mathGraph.convertToList(), file);
                else
                    saveTextToFile(mathGraph.toString(), file);
            else
                saveTextToFile("0", file);
        }
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
//            Logger.getLogger(SaveFileWithFileChooser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File dont save");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo
    }


    public void clearGraph(ActionEvent actionEvent) {
        pane.getChildren().clear();
        counter = 0;
        radius = 50;
        circles = new ArrayList<>(); //todo
        mathGraph = null;
    }

    public void selectAddEdge() {
        if (mathGraph == null) {
            exceptionLabelAddEdge.setText("Нет вершин");
            exceptionLabelAddEdge.setVisible(true);
            return;
        } else if (mathGraph.getVertexCount() < 2) {
            exceptionLabelAddEdge.setText("Вершин меньше 2");
            exceptionLabelAddEdge.setVisible(true);
            return;
        }
        if (validateVertex(vertex1.getText()) && validateVertex(vertex2.getText())) {
            if (vertex1.getText().equals(vertex2.getText())) {
                exceptionLabelAddEdge.setText("Невозможно создать ребро");
                exceptionLabelAddEdge.setVisible(true);
                return;
            }
            if (!weight.getText().isEmpty() && validateWeight(weight.getText())) {
                mathGraph.addEdge(Integer.parseInt(vertex1.getText()), Integer.parseInt(vertex2.getText()), Double.parseDouble(weight.getText()));
            } else
                mathGraph.addEdge(Integer.parseInt(vertex1.getText()), Integer.parseInt(vertex2.getText()), 1);

            exceptionLabelAddEdge.setVisible(false);

            renderEdges();
            System.out.println(mathGraph);
        }


    }

    private boolean validateVertex(String text) {
        if (text.matches("\\d*") && !text.isEmpty()) {
            if (Integer.parseInt(text) <= mathGraph.getVertexCount() && Integer.parseInt(text) > 0) {
                return true;
            }
        }
        return false;
    }

    private boolean validateWeight(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
