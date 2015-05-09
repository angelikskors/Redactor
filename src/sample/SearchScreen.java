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
import java.util.ArrayList;
import java.util.List;


public class SearchScreen extends VBox {
    TextField textField;
    TextArea textArea;

    private final List<FoundWord> foundWords = new ArrayList<>();
    private int currentWord = 0;

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
        Button nextWord = new Button();
        nextWord.setText("Next");
        nextWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentWord < foundWords.size()) {
                    selectWord(textArea);
                } else if (!foundWords.isEmpty() && currentWord == foundWords.size()) {
                    currentWord = 0;
                    selectWord(textArea);
                }
            }
        });
        forButton.getChildren().add(nextWord);
    }

    private void selectWord(TextArea textArea) {
        FoundWord foundWord = foundWords.get(currentWord++);
        textArea.selectRange(foundWord.startPosition, foundWord.endPosition);
    }

    public int searchWords(TextArea textarea, String search) {
        foundWords.clear();
        String s = textarea.getText();
        int fromIndex = 0;
        while ((fromIndex = s.indexOf(search, fromIndex)) != -1) {
            foundWords.add(new FoundWord(search, fromIndex));
            fromIndex += search.length();
        }
        if(!foundWords.isEmpty()){
            selectWord(textarea);
        }
        return foundWords.size();
    }

    private static class FoundWord {
        private final String word;
        private final int startPosition;
        private final int endPosition;

        public FoundWord(String word, int startPosition) {
            this.word = word;
            this.startPosition = startPosition;
            this.endPosition = startPosition + word.length();
        }
    }
}



