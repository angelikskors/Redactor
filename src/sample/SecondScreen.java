package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SecondScreen extends VBox {

    private final TextField strings;
    private final TextField words;
    private final TextField symbols;


    public SecondScreen(int strings, int words, int symbols) {

        setPadding(new Insets(20, 50, 10, 10));
        setSpacing(10);

        HBox stringsBox = new HBox();

        stringsBox.setSpacing(5);

        Label string = new Label();
        string.setText("The number of strings : ");
        string.setFont(new Font("Times New Roman", 18));
        string.setAlignment(Pos.CENTER);


        stringsBox.getChildren().add(string);
        this.strings = new TextField("" + strings);
        this.strings.setEditable(false);
        this.strings.setAlignment(Pos.CENTER);
        stringsBox.getChildren().add(this.strings);

        getChildren().add(stringsBox);

        HBox wordsHBox = new HBox();
        wordsHBox.setSpacing(5);
        Label word = new Label();
        word.setText("The number of words :  ");
        word.setFont(new Font("Times New Roman", 18));
        word.setAlignment(Pos.CENTER);

        wordsHBox.getChildren().add(word);
        this.words = new TextField("" + words);
        this.words.setEditable(false);
        this.words.setAlignment(Pos.CENTER);

        wordsHBox.getChildren().add(this.words);

        getChildren().add(wordsHBox);
        Label symbol = new Label();
        symbol.setText("The number of symbols: ");
        symbol.setFont(new Font("Times New Roman", 18));
        symbol.setAlignment(Pos.CENTER);

        HBox symbolsHBox = new HBox();
        symbolsHBox.setSpacing(5);

        symbolsHBox.getChildren().add(symbol);
        this.symbols = new TextField("" + symbols);
        this.symbols.setEditable(false);
        this.symbols.setAlignment(Pos.CENTER);

        symbolsHBox.getChildren().add(this.symbols);

        getChildren().add(symbolsHBox);


    }


}
