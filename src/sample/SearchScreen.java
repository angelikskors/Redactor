package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;


public class SearchScreen extends VBox {
    TextField textField;
    TextArea textArea;

    SearchScreen(TextArea textArea) {
        this.textArea = textArea;

        File f = new File("style.css");
        getStylesheets().add(f.toURI().toString());
        setPadding(new Insets(10, 10, 10, 10));

        HBox textSearch = new HBox();
        textSearch.setAlignment(Pos.CENTER);
        setSpacing(10);
        textField = new TextField();
        textField.setMinWidth(300);
        textField.setEditable(true);
        textSearch.getChildren().add(textField);
        getChildren().add(textSearch);
        HBox forButton = new HBox();
        Button search = new Button();
        search.getStyleClass().add("my-button-little-search");
        search.setText("search");
        forButton.setAlignment(Pos.CENTER);
        forButton.getChildren().add(search);
        getChildren().add(forButton);
        HBox findLabel = new HBox();
        findLabel.setAlignment(Pos.CENTER);
        Label newLabel = new Label();
        newLabel.setText("Fond matches:");
        getChildren().add(findLabel);
        findLabel.getChildren().add(newLabel);
        findLabel.getStyleClass().add("my-error-label");
        Label number1 = new Label();
        findLabel.getChildren().add(number1);
        number1.getStyleClass().add("my-error-label");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (textArea.getText().length() == 0 || textField.getText().length() == 0) {
                    number1.setText("0");
                } else {
                     number1.setText(String.valueOf(searchWords(textArea, textField.getText())));

                }

            }
        });
    }

    public static int searchWords(TextArea textarea, String search) {
        String s = textarea.getText();
        String[] word = s.split(" ");
        int[] num = new int[word.length];
        for (int i = 0; i < num.length; i++) {
            num[i] = 0;
        }
        for (int i = 0; i < word.length; i++) {
            if (word[i] != "") {
                for (int j = 0; j < word.length; j++) {
                    if ((word[i].equals(search))) {

                        num[i]++;textarea.selectRange(textarea.getText().indexOf(word[i]), search.length());
return num[i];

                    }
                }
            }
        }

        return 0;
    }
}



