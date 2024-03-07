import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class StartScene {
    private final Scene startScene;
    private GridPane outsideWrapper;

    public String name = "start scene";

    private BorderPane textWrapper;

    private BorderPane iconWrapper;

    private BorderPane textFieldBtnWrapper;

    private TextField startingAmount;
    private Button startButton;

    private final DropShadow dropShadow;

    public StartScene(){

        dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(6.0);
        dropShadow.setColor(Color.web("#1c1c1c"));

        outsideWrapper = setOutsideWrapper();
        textWrapper = setTextWrapper();
        iconWrapper = setIconWrapper();
        textFieldBtnWrapper = setTextFieldBtnWrapper();


        outsideWrapper.add(textWrapper, 0,0);
        outsideWrapper.add(iconWrapper, 0, 1);
        outsideWrapper.add(textFieldBtnWrapper, 0,2);


        outsideWrapper.setAlignment(Pos.CENTER);


        startScene = new Scene(outsideWrapper, 800, 800);

    }

    private GridPane setOutsideWrapper(){
        outsideWrapper = new GridPane();

        outsideWrapper.setStyle("-fx-background-color:" + BlackJack.black +";");

        RowConstraints title = createRow(25);
        RowConstraints icons = createRow(20);
        RowConstraints btnTextField = createRow(30);
        RowConstraints bottom = createRow(25);

        outsideWrapper.getRowConstraints().addAll(title,icons,btnTextField,bottom);

        return outsideWrapper;
    }
    private RowConstraints createRow(int size){
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(size);
        return row;
    }





    private BorderPane setTextWrapper(){
        textWrapper = new BorderPane();
        Label textLabel = new Label("Black Jack");
        textLabel.setEffect(dropShadow);

        textLabel.setAlignment(Pos.CENTER);
        textLabel.setStyle(
                "-fx-text-fill: " + BlackJack.gold + ";"+
                "-fx-font-family: Inter;"+
                "-fx-font-size: 100;" +
                "-fx-font-weight: bolder;"
        );
        textWrapper.setCenter(textLabel);
        return textWrapper;
    }



    private BorderPane setIconWrapper(){

        iconWrapper = new BorderPane();
        Image spade = new Image("/spade.png");
        ImageView imageViewSpade = new ImageView(spade);
        imageViewSpade.setEffect(dropShadow);
        imageViewSpade.setFitWidth(100);
        imageViewSpade.setFitHeight(100);
        imageViewSpade.setPreserveRatio(true);

        Image diamond = new Image("/diamond.png");
        ImageView imageViewDiamond = new ImageView(diamond);
        imageViewDiamond.setEffect(dropShadow);
        imageViewDiamond.setFitWidth(100);
        imageViewDiamond.setFitHeight(100);
        imageViewDiamond.setPreserveRatio(true);

        Image heart = new Image("/hearts.png");
        ImageView imageViewHeart = new ImageView(heart);
        imageViewHeart.setEffect(dropShadow);
        imageViewHeart.setFitWidth(100);
        imageViewHeart.setFitHeight(100);
        imageViewHeart.setPreserveRatio(true);

        Image club = new Image("/clubs.png");
        ImageView imageViewClub = new ImageView(club);
        imageViewClub.setEffect(dropShadow);
        imageViewClub.setFitWidth(100);
        imageViewClub.setFitHeight(100);
        imageViewClub.setPreserveRatio(true);

        HBox iconBox = new HBox(71, imageViewSpade, imageViewClub, imageViewHeart, imageViewDiamond);
        iconWrapper.setCenter(iconBox);


        return iconWrapper;
    }




    public Button getStartButton(){
        return startButton;
    }
    public TextField getStartingAmount(){
        return startingAmount;
    }
    private BorderPane setTextFieldBtnWrapper(){
        textFieldBtnWrapper = new BorderPane();

        startingAmount = new TextField();
        startingAmount.setEffect(dropShadow);

        startButton = new Button("Start");
        startButton.setEffect(dropShadow);
        startButton.setOnMouseEntered(event -> startButton.setCursor(Cursor.HAND));
        startButton.setOnMouseExited(event -> startButton.setCursor(Cursor.DEFAULT));

        VBox vbox = new VBox(20, startingAmount, startButton);

        startingAmount.setPromptText("Starting Amount");
        vbox.setAlignment(Pos.CENTER);

        textFieldBtnWrapper.setCenter(vbox);

        startingAmount.setStyle(
            "-fx-min-height: 86;"+
            "-fx-max-width: 340;"+
            "-fx-prompt-text-fill:" + BlackJack.gold + ";" +
            "-fx-background-color: " + BlackJack.black + ";"+
            "-fx-alignment: center;" +
            "-fx-border-radius: 40px; " +
            "-fx-background-radius: 40px; " +
            "-fx-border-color: " + BlackJack.gold + ";"+
            "-fx-font-family: Inter;"+
            "-fx-font-size: 30;"+
            "-fx-text-fill: " + BlackJack.gold + ";"
        );
        startButton.setPrefHeight(86);
        startButton.setMinHeight(86);
        startButton.setMaxHeight(86);
        startButton.setStyle(
                "-fx-padding: 0;"+
                "-fx-min-width: 340;"+
                "-fx-background-color: " + BlackJack.gold + ";"+
                "-fx-alignment: center;" +
                "-fx-background-radius: 40px; " +
                "-fx-font-family: Inter;"+
                "-fx-font-size: 61;"+
                "-fx-text-fill: " + BlackJack.black + ";"+
                "-fx-font-weight: bolder;"
        );

        return textFieldBtnWrapper;
    }








    public Scene getScene(){
        return startScene;
    }

}
