import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Card {

    private BorderPane frontCardContainer;

    private BorderPane backCardContainer;

    private BorderPane backCardContainerInner;

    private Image uicLogo;

    private ImageView uicLogoImageView;

    private Image suitImage;

    private ImageView suitImageView;

    private Label cardNum;


    private final String suit;

    private int value;

    private boolean show;

    private int royalty;

    public Card(String theSuit, int theValue){
        suit = theSuit;
        value = theValue;
        show = true;
        frontCardContainer = createFrontCard();
        backCardContainer = createBackCardContainer();
        checkRoyalty();
    }
    public void setRoyalty(int royalty){
       this.royalty = royalty;
    }
    public int getRoyalty(){
        return royalty;
    }
    public void setShow(boolean bool){
        show = bool;
    }

    public void checkRoyalty(){
        if(royalty == 1){
            cardNum.setText("K");
        }
        else if (royalty == 2){
            cardNum.setText("Q");
        }
        else if (royalty == 3){
            cardNum.setText("J");
        }
        else if (value == 11 || value == 1){
            cardNum.setText("A");
        }
    }

    private BorderPane createFrontCard(){
        frontCardContainer = new BorderPane();
        suitImage = new Image("/"+suit+".png");
        suitImageView = new ImageView(suitImage);
        suitImageView.setFitWidth(70);
        suitImageView.setFitHeight(70);
        suitImageView.setPreserveRatio(true);
        cardNum = new Label(Integer.toString(value));
        HBox numBox = new HBox();
        numBox.getChildren().add(cardNum);

        HBox picBox = new HBox();
        picBox.getChildren().add(suitImageView);
        picBox.setAlignment(Pos.CENTER);

        frontCardContainer.setPrefHeight(272);
        frontCardContainer.setMinHeight(272);
        frontCardContainer.setMaxHeight(272);
        frontCardContainer.setPrefWidth(181);
        frontCardContainer.setMinWidth(181);
        frontCardContainer.setMaxWidth(181);

        frontCardContainer.setStyle(
                "-fx-border-width: 5;" +
                "-fx-border-radius: 15;" +
                "-fx-background-color: " + BlackJack.black + ";"+
                "-fx-border-color: " +  BlackJack.gold + ";"
        );
        //ADD MARGIN TO LEFT

        cardNum.setStyle(
                "-fx-text-fill: " + BlackJack.gold + ";"+
                "-fx-font-family: Inter;"+
                "-fx-font-size: 55;" +
                "-fx-font-weight: bold;"
        );
        numBox.setStyle("-fx-padding: 0 0 0 12");
        picBox.setStyle(
//                "-fx-border-color: white;"+
                "-fx-padding: 0 0 60 0;"
                );


        frontCardContainer.setCenter(picBox);
        frontCardContainer.setTop(numBox);
        return frontCardContainer;

    }
    private BorderPane createBackCardContainer(){
        backCardContainer = new BorderPane();
        backCardContainerInner = new BorderPane();
        uicLogo = new Image("/uic.png");
        uicLogoImageView = new ImageView(uicLogo);

        backCardContainer.setPrefHeight(272);
        backCardContainer.setMinHeight(272);
        backCardContainer.setMaxHeight(272);
        backCardContainer.setPrefWidth(181);
        backCardContainer.setMinWidth(181);
        backCardContainer.setMaxWidth(181);

        backCardContainer.setStyle(
                "-fx-border-width: 10;" +
                "-fx-border-radius: 15;" +
                "-fx-background-color: " + BlackJack.black + ";"+
                "-fx-border-color: " +  BlackJack.gold + ";"
        );

        backCardContainerInner.setPrefHeight(225);
        backCardContainerInner.setMinHeight(225);
        backCardContainerInner.setMaxHeight(225);
        backCardContainerInner.setPrefWidth(133);
        backCardContainerInner.setMinWidth(133);
        backCardContainerInner.setMaxWidth(133);
        backCardContainerInner.setStyle(
                "-fx-border-width: 5;" +
                "-fx-border-radius: 15;" +
                "-fx-background-color: transparent;"+
                "-fx-border-color: " +  BlackJack.gold + ";"
        );

        backCardContainer.setCenter(backCardContainerInner);
        backCardContainerInner.setCenter(uicLogoImageView);
        return backCardContainer;

    }

    public String getSuit() {
        return suit;
    }

    public boolean isShow() {
        return show;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value){ this.value = value;}

    public BorderPane getFrontCardContainer(){
        return frontCardContainer;
    }
    public BorderPane getBackCardContainer(){
        return backCardContainer;
    }
}
