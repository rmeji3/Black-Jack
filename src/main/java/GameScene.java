import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

public class GameScene {
    private final Scene gameScene;
    private GridPane outsideWrapper;

    public String name = "game scene";

    private HBox topHbox;
    private HBox cpuCardHbox;
    private HBox playerCardHbox;

    private VBox cpuCountVbox;

    private Label dealerLabel;
    private Label dealerCount;
    private HBox bottomHbox;
    private VBox playerCountVbox;
    private Label playerLabel;
    private Label playerCount;
    private HBox middleHbox;

    private Button hitButton;
    private Button standButton;

    private Label betLabel;

    private HBox bankHbox;

    private BorderPane bankWrapper;
    private Label totalFundsLabel;
    private final DropShadow dropShadow;


    public GameScene(){
        dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(6.0);
        dropShadow.setColor(Color.web("#1c1c1c"));




        topHbox = setTopHbox();
        middleHbox = setMiddleHbox();
        bottomHbox = setBottomHbox();
        bankWrapper = setBankWrapper();
        bankHbox = new HBox(bankWrapper);
        bankHbox.setAlignment(Pos.CENTER);


        outsideWrapper = setOutsideWrapper();
        outsideWrapper.setAlignment(Pos.CENTER);
        alignRowInGridPane(outsideWrapper, 3, HPos.CENTER, VPos.BOTTOM);



        outsideWrapper.add(topHbox, 0,0);
        outsideWrapper.add(middleHbox, 0, 1);
        outsideWrapper.add(bottomHbox, 0,2);
        outsideWrapper.add(bankHbox, 0,3);


        outsideWrapper.setAlignment(Pos.CENTER);



        gameScene = new Scene(outsideWrapper, 1300, 1000);

    }

    private GridPane setOutsideWrapper(){
        outsideWrapper = new GridPane();

        outsideWrapper.setStyle("-fx-background-color:" + BlackJack.black +";");

        RowConstraints cpuRow = createRow(40);
        RowConstraints buttonRow = createRow(15);
        RowConstraints playerRow = createRow(40);
//        RowConstraints bottom = createRow(5);

        outsideWrapper.getRowConstraints().addAll(cpuRow,buttonRow,playerRow);

        return outsideWrapper;
    }
    private RowConstraints createRow(int size){
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(size);
        return row;
    }


    private HBox setTopHbox(){
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topHbox = new HBox(10);
        cpuCardHbox = new HBox(10);
        cpuCardHbox.setAlignment(Pos.CENTER);
        cpuCountVbox = new VBox(10);
        cpuCountVbox.setAlignment(Pos.CENTER);
        dealerLabel  = new Label("Dealer");
        dealerLabel.setStyle(
                "-fx-text-fill: " + BlackJack.gold + ";"+
                "-fx-font-family: Inter;"+
                "-fx-font-size: 37;" +
                "-fx-font-weight: bold;"
        );
        dealerCount = new Label("0");
        dealerCount.setPrefHeight(81);
        dealerCount.setMinHeight(81);
        dealerCount.setMaxHeight(81);
        dealerCount.setPrefWidth(81);
        dealerCount.setMinWidth(81);
        dealerCount.setMaxWidth(81);
        dealerCount.setAlignment(Pos.CENTER);
        dealerCount.setStyle(
                "-fx-text-fill: " + BlackJack.gold + ";"+
                "-fx-font-family: Inter;"+
                "-fx-font-size: 37;" +
                "-fx-font-weight: bold;"+
                "-fx-background-color: " + BlackJack.lightBlack  + ";"+
                "-fx-background-radius: 50%;"
        );
        cpuCountVbox.getChildren().addAll(dealerLabel, dealerCount);
        topHbox.getChildren().addAll(cpuCardHbox,spacer,cpuCountVbox);



        return topHbox;
    }

    private HBox setMiddleHbox(){
        HBox middleHbox = new HBox();
        middleHbox.setAlignment(Pos.CENTER);
        middleHbox.setSpacing(20);
        middleHbox.setStyle("-fx-background-color: " + BlackJack.black + ";");
        hitButton = new Button("Hit");
        standButton = new Button("Stand");
        betLabel = new Label("$1");


        hitButton.setStyle(
            "-fx-padding: 0;" +
            "-fx-min-width: 150;" +
            "-fx-background-color: " + BlackJack.gold + ";" +
            "-fx-alignment: center;" +
            "-fx-background-radius: 40px; " +
            "-fx-font-family: Inter;" +
            "-fx-font-size: 37;" +
            "-fx-text-fill: " + BlackJack.black + ";" +
            "-fx-font-weight: bolder;"
        );
        standButton.setStyle(
            "-fx-padding: 0;" +
            "-fx-min-width: 150;" +
            "-fx-background-color: " + BlackJack.gold + ";" +
            "-fx-alignment: center;" +
            "-fx-background-radius: 40px; " +
            "-fx-font-family: Inter;" +
            "-fx-font-size: 37;" +
            "-fx-text-fill: " + BlackJack.black + ";" +
            "-fx-font-weight: bolder;"
        );
        betLabel.setStyle(
            "-fx-min-height: 54;" +
            "-fx-min-width: 172;" +
            "-fx-max-width: 172;" +
            "-fx-background-color: " + BlackJack.black + ";" +
            "-fx-alignment: center;" +
            "-fx-border-radius: 40px; " +
            "-fx-background-radius: 40px; " +
            "-fx-border-color: " + BlackJack.gold + ";" +
            "-fx-font-family: Inter;" +
            "-fx-font-size: 32;" +
            "-fx-text-fill: " + BlackJack.gold + ";" +
            "-fx-font-weight: bolder;"
        );
        middleHbox.getChildren().addAll(hitButton, betLabel, standButton);
        return middleHbox;
    }
    private HBox setBottomHbox(){
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        bottomHbox = new HBox(10);
        playerCardHbox = new HBox(10);
        playerCardHbox.setAlignment(Pos.CENTER);
        playerCountVbox = new VBox(10);
        playerCountVbox.setAlignment(Pos.CENTER);
        playerLabel  = new Label("Player");
        playerLabel.setStyle(
                "-fx-text-fill: " + BlackJack.gold + ";"+
                "-fx-font-family: Inter;"+
                "-fx-font-size: 38;" +
                "-fx-font-weight: bold;"
        );
        playerCount = new Label("0");
        playerCount.setAlignment(Pos.CENTER);
        playerCount.setPrefHeight(81);
        playerCount.setMinHeight(81);
        playerCount.setMaxHeight(81);
        playerCount.setPrefWidth(81);
        playerCount.setMinWidth(81);
        playerCount.setMaxWidth(81);
        playerCount.setStyle(
                "-fx-text-fill: " + BlackJack.gold + ";"+
                "-fx-font-family: Inter;"+
                "-fx-font-size: 38;" +
                "-fx-font-weight: bold;"+
                "-fx-background-color: " + BlackJack.lightBlack  + ";"+
                "-fx-background-radius: 50%;"
        );
        playerCountVbox.getChildren().addAll(playerLabel, playerCount);
        bottomHbox.getChildren().addAll(playerCardHbox,spacer,playerCountVbox);



        return bottomHbox;
    }
    private BorderPane setBankWrapper(){
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

    public Button getHitButton(){
        return hitButton;
    }
    public Button getStandButton(){
        return standButton;
    }

    public HBox getCpuCardHbox(){return cpuCardHbox;}
    public HBox getPlayerCardHbox(){return playerCardHbox;}

    public void setDealerCount(String amount){dealerCount.setText(amount);}

    public void setPlayerCount(String amount){playerCount.setText(amount);}

    public void setBetLabel(String bet){betLabel.setText(bet);}
    public Scene getScene(){return gameScene;}
}
