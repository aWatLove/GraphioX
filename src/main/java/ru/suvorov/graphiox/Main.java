package ru.suvorov.graphiox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("GraphIOX");
        stage.setWidth(600);
        stage.setHeight(400);

        InputStream iconStream = getClass().getResourceAsStream("icon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainScene.fxml"));

        MainSceneController controller = loader.getController();

        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}