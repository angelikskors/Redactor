package sample;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainScreen screen = new MainScreen();

        Scene scene = new Scene(screen, 800, 700);


        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
