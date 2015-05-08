package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.jnlp.FileSaveService;
import java.io.File;
import java.io.IOException;


public class MainScreen extends VBox {
    private TextArea textArea;
    private HBox hBox;
    private VBox vBox;

    MainScreen() {
        VBox uiNewUser = buildUIForNewUser();
        getChildren().add(uiNewUser);
    }


    private VBox buildUIForNewUser() {
        File f = new File("style.css");
        getStylesheets().add(f.toURI().toString());

        vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);
        HBox option = new HBox();
        option.setSpacing(50);
        vBox.getChildren().add(option);
        final ChoiceBox<String> choices = new ChoiceBox<String>(FXCollections.observableArrayList("New Window", "Create", "Open", "Close"));
        choices.getStyleClass().add("my-button");

        option.getChildren().add(choices);

        choices.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {

                                                                            int count = 0;
                                                                            VBox vebox = new VBox();

                                                                            @Override
                                                                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                                                                if (vBox.getChildren().contains(textArea)) {
                                                                                    System.out.println("!!!!!!!!!!!!!!!");
                                                                                }


                                                                                if (observable.getValue().toString().equals("0")) {
                                                                                    newWindow();

                                                                                } else if (observable.getValue().toString().equals("1")) {

                                                                                    count++;
                                                                                    if (count > 1) {
                                                                                        newWindow();

                                                                                    } else {
                                                                                        addTextField(vBox);
                                                                                    }


                                                                                } else if (observable.getValue().toString().equals("2")) {


                                                                                    if (oldValue.toString().equals("1") || (oldValue.toString().equals("-1"))) {
                                                                                        openFile(newWindow(vebox));


                                                                                    } else {
                                                                                        openFile(vBox);
                                                                                    }

                                                                                } else if (observable.getValue().toString().equals(2)) {
                                                                                    getScene().getWindow().hide();


                                                                                } else
                                                                                    System.out.println("No matches found");


                                                                            }
                                                                        }

        );

        Image image1 = new Image(FileHelper.getInputStreamForFile("search.png"));
        Button search = new Button();
        search.setGraphic(new ImageView(image1));
        search.setText("Search");
        search.getStyleClass().add("my-button-search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int strings;
                int words;
                int symbols;

                FileCounter newCount = new FileCounter();
                if (textArea.getText() != null) {
                    try {
                        strings = newCount.countStrings(textArea);
                        symbols = newCount.countSymbols(textArea);
                        words = newCount.countWords(textArea);
                        SecondScreen root = new SecondScreen(strings, words, symbols);
                        Stage stage = new Stage();

                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

        option.getChildren().add(search);

        Image image = new Image(FileHelper.getInputStreamForFile("save-icon.png"));
        Button save = new Button();
        save.setText("Save");
        save.getStyleClass().add("my-button-save");


        save.setGraphic(new ImageView(image));
        option.getChildren().add(save);
        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.getInitialFileName();

                try {
                    FileHelper.writeIntoFile(fileChooser.showSaveDialog(stage), textArea.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        return vBox;
    }

    private void newWindow() {
        Stage stage = new Stage();
        MainScreen root = new MainScreen();
        stage.setScene(new Scene(root, 720, 720));
        stage.show();

    }

    private VBox newWindow(VBox vebox) {

        Stage stage = new Stage();
        MainScreen root = new MainScreen();
        stage.setScene(new Scene(root, 720, 720));
        vebox = new VBox();
        root.getChildren().add(vebox);
        stage.show();
        return vebox;
    }

    private void addTextField(VBox vBox) {

        hBox = new HBox();
        textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setFont(new Font("Times New Roman", 20));
        textArea.setPrefSize(800, 800);
        hBox.getChildren().add(textArea);
        vBox.getChildren().add(hBox);
    }

    private void addTextField(VBox vBox, StringBuilder str) {

        hBox = new HBox();
        textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setText(str.toString());
        textArea.setFont(new Font("Times New Roman", 20));
        textArea.setPrefSize(800, 800);
        hBox.getChildren().add(textArea);
        vBox.getChildren().add(hBox);
    }

    private boolean openFile(VBox vBox) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                addTextField(vBox, new FileHelper().readFile(file));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


}


