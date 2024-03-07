import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoseScene {
    private final Scene loseScene;
    private GridPane outsideWrapper;

    private BorderPane textWrapper;

    public String name = "lose scene";


    private BorderPane textFieldBtnWrapper;

    private Button quitButton;
    private Button playAgainButton;

    private final DropShadow dropShadow;


    public LoseScene() {

        dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(6.0);
        dropShadow.setColor(Color.web("#1c1c1c"));

        outsideWrapper = setOutsideWrapper();
        textWrapper = setTextWrapper();
        textFieldBtnWrapper = setTextFieldBtnWrapper();


        outsideWrapper.add(textWrapper, 0, 0);
        outsideWrapper.add(textFieldBtnWrapper, 0, 1);


        outsideWrapper.setAlignment(Pos.CENTER);



        loseScene = new Scene(outsideWrapper, 1300, 1000);

    }



    private GridPane setOutsideWrapper() {
        outsideWrapper = new GridPane();

        outsideWrapper.setStyle("-fx-background-color:" + BlackJack.black + ";");

        RowConstraints title = createRow(45);
        RowConstraints btnTextField = createRow(30);
        RowConstraints bottom = createRow(25);

        outsideWrapper.getRowConstraints().addAll(title, btnTextField, bottom);

        return outsideWrapper;
    }

    private BorderPane setTextWrapper() {
        textWrapper = new BorderPane();
        Label textLabel = new Label("Game Over");

        textLabel.setEffect(dropShadow);


        textLabel.setAlignment(Pos.CENTER);
        textLabel.setStyle(
                "-fx-text-fill: " + BlackJack.gold + ";" +
                        "-fx-font-family: Inter;" +
                        "-fx-font-size: 100;" +
                        "-fx-font-weight: bolder;"
        );

        textWrapper.setCenter(textLabel);
        return textWrapper;
    }

    private RowConstraints createRow(int size) {
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(size);
        return row;
    }

    private BorderPane setTextFieldBtnWrapper() {
        textFieldBtnWrapper = new BorderPane();

        quitButton = new Button();
        quitButton.setEffect(dropShadow);
        quitButton.setOnMouseEntered(event -> quitButton.setCursor(Cursor.HAND));
        quitButton.setOnMouseExited(event -> quitButton.setCursor(Cursor.DEFAULT));


        playAgainButton = new Button("Play Again");
        playAgainButton.setEffect(dropShadow);
        playAgainButton.setOnMouseEntered(event -> playAgainButton.setCursor(Cursor.HAND));
        playAgainButton.setOnMouseExited(event -> playAgainButton.setCursor(Cursor.DEFAULT));

        VBox vbox = new VBox(20, playAgainButton, quitButton);

        quitButton.setText("Quit");
        vbox.setAlignment(Pos.CENTER);

        textFieldBtnWrapper.setCenter(vbox);


        playAgainButton.setPrefHeight(86);
        playAgainButton.setMinHeight(86);
        playAgainButton.setMaxHeight(86);
        playAgainButton.setStyle(
                "-fx-padding: 0;" +
                "-fx-min-width: 340;" +
                "-fx-background-color: " + BlackJack.gold + ";" +
                "-fx-alignment: center;" +
                "-fx-background-radius: 40px; " +
                "-fx-font-family: Inter;" +
                "-fx-font-size: 51;" +
                "-fx-text-fill: " + BlackJack.black + ";" +
                "-fx-font-weight: bolder;"
        );
        quitButton.setStyle(
                "-fx-min-height: 86;" +
                "-fx-max-width: 340;" +
                "-fx-prompt-text-fill:" + BlackJack.gold + ";" +
                "-fx-background-color: " + BlackJack.black + ";" +
                "-fx-alignment: center;" +
                "-fx-border-radius: 40px; " +
                "-fx-background-radius: 40px; " +
                "-fx-border-color: " + BlackJack.gold + ";" +
                "-fx-font-family: Inter;" +
                "-fx-font-size: 30;" +
                "-fx-text-fill: " + BlackJack.gold + ";"
        );

        return textFieldBtnWrapper;

    }

    public Scene getScene(){
        return loseScene;
    }
    public Button getPlayAgainButton(){
        return playAgainButton;
    }
    public Button getQuitButton(){
        return quitButton;
    }

}
