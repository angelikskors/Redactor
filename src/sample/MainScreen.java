package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class MainScreen extends VBox {
    public static final String NEW_WINDOW_ACTION = "New Window";
    public static final String CREATE_ACTION = "Create";
    public static final String OPEN_ACTION = "Open";
    public static final String CLOSE_ACTION = "Close";
    private int strings;
    private int words;
    private int symbols;
    public TextArea textArea;
    private HBox hBox;
    private VBox vBox;
    FunScreen newSc;

    private File currentEditFile;

    private boolean isNewFile;

    MainScreen() {
        VBox uiNewUser = buildUIForNewUser();
        getChildren().add(uiNewUser);
    }

    public MainScreen(boolean isNewFile) {
        this();
        this.isNewFile = isNewFile;
        if (isNewFile) {
            addTextField(vBox);
        }
    }

    private VBox buildUIForNewUser() {
        File f = new File("style.css");
        getStylesheets().add(f.toURI().toString());

        vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(5);
        HBox option = new HBox();
        option.setSpacing(50);
        vBox.getChildren().add(option);
        final ChoiceBox<String> choices = new ChoiceBox<String>(FXCollections.observableArrayList(NEW_WINDOW_ACTION, CREATE_ACTION, OPEN_ACTION, CLOSE_ACTION));
        choices.getStyleClass().add("my-button-choice");

        option.getChildren().add(choices);

        choices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (NEW_WINDOW_ACTION.equals(newValue)) {
                    startNewWindow();
                } else if (CREATE_ACTION.equals(newValue)) {
                    if (currentEditFile == null && !isNewFile) {
                        createFile();
                    } else {
                        startCreateNewFileWindow();
                    }
                } else if (OPEN_ACTION.equals(newValue)) {
                    if (currentEditFile == null && !isNewFile) {
                        openFile();
                    } else {
                        startOpenNewFileWindow();
                    }
                } else if (CLOSE_ACTION.equals(newValue)) {
                    closeCurrentWindow();
                }
            }
        });

        Image image1 = new Image(FileHelper.getInputStreamForFile("count.png"));
        ImageView newImageView = new ImageView();
        newImageView.setImage(image1);
        newImageView.setFitHeight(55);
        newImageView.setFitWidth(55);

        Button count = new Button();
        count.setGraphic(newImageView);
        count.setText("Count");
        count.getStyleClass().add("my-button-count");
        count.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!isNewFile) {
                    createErrorStat();
                } else {

                    createStatiscticsWindow();
                }
            }
        });

        option.getChildren().add(count);

        Image image = new Image(FileHelper.getInputStreamForFile("save-icon.png"));
        Button save = new Button();
        save.setText("Save");
        save.getStyleClass().add("my-button-save");


        save.setGraphic(new ImageView(image));
        option.getChildren().add(save);
        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!isNewFile) {
                    createErrorSave();

                } else {

                    Stage stage = new Stage();
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getInitialFileName();

                    try {
                        FileHelper.writeIntoFile(fileChooser.showSaveDialog(stage), textArea.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        Button search = new Button();
        search.setText("Search");
        search.getStyleClass().add("my-button-search");

        search.setGraphic(new ImageView(image));
        option.getChildren().add(search);
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isNewFile) {
                    createErrorSeaerch();
                } else {
                    createSearchWindow();
                }
            }
        });
        HBox boxForStuff = new HBox();

        vBox.getChildren().add(boxForStuff);
        Button eye = new Button();
        Image image2 = new Image(FileHelper.getInputStreamForFile("eye.png"));
        ImageView iname = new ImageView(image2);
        iname.setFitWidth(30);
        iname.setFitHeight(30);
        boxForStuff.setPadding(new Insets(10, 10, 10, 10));
        boxForStuff.setAlignment(Pos.BASELINE_RIGHT);
        eye.setGraphic(iname);
        boxForStuff.getChildren().add(eye);
        eye.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isNewFile) {
                    createErrorEye();
                } else
                    currentString();
            }
        });
        return vBox;
    }

    private void createErrorEye() {
        Image image = new Image(FileHelper.getInputStreamForFile("WTF.jpg"));

        ErrorScreen error = new ErrorScreen(image);
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        Scene scene = new Scene(error, 170, 170);
        stage.setScene(scene);
        stage.show();
    }

    private void createSearchWindow() {
        SearchScreen newSearch = new SearchScreen(textArea);
        Stage stage = new Stage();
        stage.setTitle("Search");
        Scene scene = new Scene(newSearch, 360, 140);
        stage.setScene(scene);
        stage.show();
    }

    private void createErrorSeaerch() {
        Image image = new Image(FileHelper.getInputStreamForFile("questions3.jpg"));

        ErrorScreen error = new ErrorScreen(" Nothing to search ", image);
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        Scene scene = new Scene(error, 360, 140);
        stage.setScene(scene);
        stage.show();
    }

    private void createErrorSave() {
        Image image = new Image(FileHelper.getInputStreamForFile("questions1.jpg"));

        ErrorScreen error = new ErrorScreen(" Nothing to save ", image);
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        Scene scene = new Scene(error, 360, 140);
        stage.setScene(scene);
        stage.show();
    }

    private void currentString() {
        int page = 1800;
        int symbols = textArea.getCaretPosition();
        double quantityPages = symbols / page;
        Stage stage = new Stage();
        long round = Math.round(quantityPages);
        String str = "The number of pages : ";


        if (quantityPages < 1) {
            Image image2 = new Image(FileHelper.getInputStreamForFile("consider.png"));
            newSc = new FunScreen(str + round, image2);


        } else if (quantityPages < 5) {
            Image image2 = new Image(FileHelper.getInputStreamForFile("challenge_accepted.png"));
            newSc = new FunScreen(str + round, image2);
        } else if (quantityPages < 10) {
            Image image2 = new Image(FileHelper.getInputStreamForFile("obama_not_bad.png"));
            newSc = new FunScreen(str + round, image2);
        } else if (quantityPages > 20) {
            Image image2 = new Image(FileHelper.getInputStreamForFile("fuckYEAH.png"));
            newSc = new FunScreen(str + round, image2);
        } else if (quantityPages < 30) {
            Image image2 = new Image(FileHelper.getInputStreamForFile("hug.jpg"));
            newSc = new FunScreen(str + round, image2);
        }
        Scene scene = new Scene(newSc, 400, 140);
        stage.setScene(scene);
        stage.show();


    }

    private void createErrorStat() {
        Image image = new Image(FileHelper.getInputStreamForFile("questions.jpg"));

        ErrorScreen error = new ErrorScreen(" Nothing to count ", image);
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        Scene scene = new Scene(error, 360, 140);
        stage.setScene(scene);
        stage.show();
    }

    private void createStatiscticsWindow() {
        FileCounter newCount = new FileCounter();

        strings = newCount.countStrings(textArea.getText());
        symbols = newCount.countSymbols(textArea.getText());
        try {
            words = newCount.countWords(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SecondScreen root = new SecondScreen(strings, words, symbols);

        Stage stage = new Stage();
        stage.setTitle("Statistics");
        Scene scene = new Scene(root, 420, 140);
        stage.setScene(scene);
        stage.show();
    }

    private void createFile() {
        addTextField(vBox);
        isNewFile = true;
    }

    private void closeCurrentWindow() {
        getScene().getWindow().hide();
    }

    private void startNewWindow() {
        Stage stage = new Stage();
        MainScreen root = new MainScreen();
        stage.setScene(new Scene(root, 800, 750));
        stage.show();
    }

    private void startCreateNewFileWindow() {
        Stage stage = new Stage();
        MainScreen root = new MainScreen(true);
        stage.setScene(new Scene(root, 800, 750));
        stage.show();
    }

    private void startOpenNewFileWindow() {
        Stage stage = new Stage();
        MainScreen root = new MainScreen();
        root.openFile();
        stage.setScene(new Scene(root, 800, 750));
        stage.show();
    }

    private VBox newWindow(VBox vebox) {

        Stage stage = new Stage();
        MainScreen root = new MainScreen();
        stage.setScene(new Scene(root, 800, 750));
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
        textArea.isResizable();
        textArea.isWrapText();
        textArea.isPickOnBounds();

        hBox.getChildren().add(textArea);
        vBox.getChildren().add(hBox);
    }

    private void addTextField(VBox vBox, StringBuilder str) {

        hBox = new HBox();
        textArea = new TextArea();
        textArea.setEditable(true);

        ScrollBar scrollBarv = (ScrollBar) textArea.lookup(".scroll-bar:horizontal");
        scrollBarv.setDisable(true);
        textArea.setText(str.toString());
        textArea.setFont(new Font("Times New Roman", 20));
        textArea.setPrefSize(800, 800);
        hBox.getChildren().add(textArea);
        vBox.getChildren().add(hBox);
    }

    private boolean openFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        currentEditFile = fileChooser.showOpenDialog(stage);
        if (currentEditFile != null) {
            addTextField(vBox, new FileHelper().readFile(currentEditFile));
        }
        return false;
    }


}


