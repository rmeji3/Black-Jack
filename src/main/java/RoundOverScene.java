import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

public class RoundOverScene {
    private final Scene roundOverScene;
    private GridPane outsideWrapper;

    public String name = "round over scene";

    private BorderPane textWrapper;

    private BorderPane bankWrapper;

    private BorderPane textFieldBtnWrapper;

    private Button quitButton;
    private Button continueButton;

    private Label earningsLabel;

    private final DropShadow dropShadow;

    private Label totalFundsLabel;

    public RoundOverScene() {

        dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(6.0);
        dropShadow.setColor(Color.web("#1c1c1c"));

        outsideWrapper = setOutsideWrapper();
        textWrapper = setTextWrapper();
        textFieldBtnWrapper = setLabelBtnsWrapper();
        bankWrapper = setBankWrapper();


        outsideWrapper.add(textWrapper, 0, 0);
        outsideWrapper.add(textFieldBtnWrapper, 0, 1);
        outsideWrapper.add(bankWrapper, 0, 2);


        outsideWrapper.setAlignment(Pos.CENTER);

        alignRowInGridPane(outsideWrapper, 2, HPos.CENTER, VPos.BOTTOM);


        roundOverScene = new Scene(outsideWrapper, 1300, 1000);

    }

    private void alignRowInGridPane(GridPane gridPane, int row, HPos hAlignment, VPos vAlignment) {
        gridPane.getChildren().forEach(child -> {
            Integer rowIndex = GridPane.getRowIndex(child);
            if (rowIndex == null) rowIndex = 0; // Default row index is 0
            if (rowIndex == row) {
                GridPane.setHalignment(child, hAlignment);
                GridPane.setValignment(child, vAlignment);
            }
        });
    }


    private GridPane setOutsideWrapper() {
        outsideWrapper = new GridPane();

        outsideWrapper.setStyle("-fx-background-color:" + BlackJack.black + ";");

        RowConstraints title = createRow(45);
        RowConstraints btnTextField = createRow(40);
        RowConstraints bottom = createRow(15);

        outsideWrapper.getRowConstraints().addAll(title, btnTextField, bottom);

        return outsideWrapper;
    }

    private BorderPane setTextWrapper() {
        textWrapper = new BorderPane();
        Label textLabel = new Label("Round\n Over");

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

    private BorderPane setLabelBtnsWrapper() {
        textFieldBtnWrapper = new BorderPane();

        earningsLabel = new Label("Earnings: ");
        earningsLabel.setEffect(dropShadow);


        continueButton = new Button("Continue");
        continueButton.setEffect(dropShadow);
        continueButton.setOnMouseEntered(event -> continueButton.setCursor(Cursor.HAND));
        continueButton.setOnMouseExited(event -> continueButton.setCursor(Cursor.DEFAULT));

        quitButton = new Button();
        quitButton.setEffect(dropShadow);
        quitButton.setOnMouseEntered(event -> quitButton.setCursor(Cursor.HAND));
        quitButton.setOnMouseExited(event -> quitButton.setCursor(Cursor.DEFAULT));

        VBox vbox = new VBox(20,earningsLabel, continueButton, quitButton);

        quitButton.setText("Quit");
        vbox.setAlignment(Pos.CENTER);

        textFieldBtnWrapper.setCenter(vbox);

        earningsLabel.setStyle(
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

        continueButton.setPrefHeight(86);
        continueButton.setMinHeight(86);
        continueButton.setMaxHeight(86);
        continueButton.setStyle(
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
    public BorderPane setBankWrapper(){
        bankWrapper = new BorderPane();

        bankWrapper.setPrefHeight(40);
        bankWrapper.setMinHeight(40);
        bankWrapper.setMaxHeight(40);

        bankWrapper.setPrefWidth(180);
        bankWrapper.setMinWidth(180);
        bankWrapper.setMaxWidth(180);

        bankWrapper.setStyle(
                "-fx-border-radius: 20px 20px 0px 0px;" +
                        "-fx-background-radius: 40px;" +
                        "-fx-background-color: " + BlackJack.black + ";"+
                        "-fx-border-color: " + BlackJack.gold + ";"+
                        "-fx-border-width: 1 1 0 1;"
        );

        totalFundsLabel = new Label("Bank: ");
        totalFundsLabel.setStyle(
                "-fx-text-fill: " + BlackJack.gold + ";" +
                        "-fx-font-family: Inter;" +
                        "-fx-font-size: 18;"
        );
        bankWrapper.setCenter(totalFundsLabel);

        return bankWrapper;
    }


    public Scene getScene(){
        return roundOverScene;
    }
    public Button getContinueButton(){
        return continueButton;
    }
    public Button getQuitButton(){return quitButton;}
    public Label getEarningsLabel(){return earningsLabel;}

    public Label getBankLabel(){
        return totalFundsLabel;
    }

}
