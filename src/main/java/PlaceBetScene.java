import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class PlaceBetScene {
    Scene placeBetScene;
    GridPane outsideWrapper;

    BorderPane textWrapper;

    BorderPane bankWrapper;

    BorderPane textFieldBtnWrapper;

    TextField betAmountField;
    Button betButton;

    DropShadow dropShadow;

    Label totalFundsLabel;

    public PlaceBetScene() {

        dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(6.0);
        dropShadow.setColor(Color.web("#1c1c1c"));

        outsideWrapper = setOutsideWrapper();
        textWrapper = setTextWrapper();
        textFieldBtnWrapper = setTextFieldBtnWrapper();
        bankWrapper = setBankWrapper();


        outsideWrapper.add(textWrapper, 0, 0);
        outsideWrapper.add(textFieldBtnWrapper, 0, 1);
        outsideWrapper.add(bankWrapper, 0, 2);


        outsideWrapper.setAlignment(Pos.CENTER);

        alignRowInGridPane(outsideWrapper, 2, HPos.CENTER, VPos.BOTTOM);


        placeBetScene = new Scene(outsideWrapper, 800, 800);

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
        RowConstraints btnTextField = createRow(30);
        RowConstraints bottom = createRow(25);

        outsideWrapper.getRowConstraints().addAll(title, btnTextField, bottom);

        return outsideWrapper;
    }

    private BorderPane setTextWrapper() {
        textWrapper = new BorderPane();
        Label textLabel = new Label("Place Bet");

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

        betAmountField = new TextField();
        betAmountField.setEffect(dropShadow);


        betButton = new Button("Bet");
        betButton.setEffect(dropShadow);

        VBox vbox = new VBox(20, betAmountField, betButton);

        betAmountField.setPromptText("Bet Amount");
        vbox.setAlignment(Pos.CENTER);

        textFieldBtnWrapper.setCenter(vbox);

        betAmountField.setStyle(
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
        betButton.setPrefHeight(86);
        betButton.setMinHeight(86);
        betButton.setMaxHeight(86);
        betButton.setStyle(
                "-fx-padding: 0;" +
                "-fx-min-width: 340;" +
                "-fx-background-color: " + BlackJack.gold + ";" +
                "-fx-alignment: center;" +
                "-fx-background-radius: 40px; " +
                "-fx-font-family: Inter;" +
                "-fx-font-size: 61;" +
                "-fx-text-fill: " + BlackJack.black + ";" +
                "-fx-font-weight: bolder;"
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
        return placeBetScene;
    }
    public Button getBetButton(){
        return betButton;
    }
    public TextField getBetAmountField(){
        return betAmountField;
    }

    public Label getBankLabel(){
        return totalFundsLabel;
    }
}
