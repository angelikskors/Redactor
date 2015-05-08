package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SecondScreen extends VBox {

    private final TextField strings;
    private final TextField words;
    private final TextField symbols;


    public SecondScreen(int strings, int words, int symbols) {

        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(10);

        HBox stringsBox = new HBox();
        stringsBox.setSpacing(10);

        stringsBox.getChildren().add(new Label("Strings: "));
        this.strings = new TextField("" + strings);

        stringsBox.getChildren().add(this.strings);

        getChildren().add(stringsBox);

        HBox wordsHBox = new HBox();
        wordsHBox.setSpacing(10);

        wordsHBox.getChildren().add(new Label("Words: "));
        this.words = new TextField("" + words);
        wordsHBox.getChildren().add(this.words);

        getChildren().add(wordsHBox);

        HBox symbolsHBox = new HBox();
        symbolsHBox.setSpacing(10);

        symbolsHBox.getChildren().add(new Label("Words: "));
        this.symbols = new TextField("" + symbols);
        symbolsHBox.getChildren().add(this.symbols);

        getChildren().add(symbolsHBox);


    }


}
