import javafx.application.Application;


import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.util.HashMap;

public class BlackJack extends Application {

	static final String black = "#2D2D2D";
	static final String lightBlack = "#424242";
	static final String gold = "#82600B";

	static BigInteger totalFunds = BigInteger.valueOf(0L);

	static BigInteger betAmount;
	static String startingAmountString;

	static String betAmountString;
	private HashMap<String,Scene> sceneMap;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	private static boolean startButtonListener(String startingAmountString, StartScene startScene){
		if (startingAmountString.isEmpty()){
			startScene.getStartingAmount().setPromptText("Enter a valid number");
			return false;
		}
		for(int i = 0; i < startingAmountString.length(); i++) {
			if(!Character.isDigit(startingAmountString.charAt(i))){
				startScene.getStartingAmount().clear();
				startScene.getStartingAmount().setPromptText("Enter a valid number");
				return false;
			}
		}
		totalFunds =  new BigInteger(startingAmountString);
		if (totalFunds.compareTo(BigInteger.ZERO) <= 0) {
			startScene.getStartingAmount().clear();
			startScene.getStartingAmount().setPromptText("Enter a valid number");
			return false;
		}

		return true;


	}

	private static boolean startBet(String betAmountString, PlaceBetScene placeBetScene){
		if (betAmountString.isEmpty()){
			placeBetScene.getBetAmountField().setPromptText("Enter a valid number");
			return false;
		}
		for(int i = 0; i < betAmountString.length(); i++) {
			if(!Character.isDigit(betAmountString.charAt(i))){
				placeBetScene.getBetAmountField().clear();
				placeBetScene.getBetAmountField().setPromptText("Enter a valid number");
				return false;
			}
		}
		betAmount =  new BigInteger(betAmountString);
		if (betAmount.compareTo(BigInteger.ZERO) <= 0){
			placeBetScene.getBetAmountField().clear();
			placeBetScene.getBetAmountField().setPromptText("Enter a valid number");
			return false;
		}
		else if(totalFunds.compareTo(betAmount) < 0)
		{
			placeBetScene.getBetAmountField().clear();
			placeBetScene.getBetAmountField().setPromptText("Not enough funds");
			return false;
		}

		return true;


	}
	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Black Jack");
		sceneMap = new HashMap<>();

		StartScene startScene = new StartScene();
		sceneMap.put("start scene", startScene.getScene());

		PlaceBetScene placeBetScene = new PlaceBetScene();
		sceneMap.put("place bet scene", placeBetScene.getScene());



		startingAmountString = "";
		startScene.getStartButton().setOnAction(e->{
			startingAmountString = startScene.getStartingAmount().getText();
			if(startButtonListener(startingAmountString, startScene))
			{
				placeBetScene.getBankLabel().setText("Bank: " + startingAmountString);
				primaryStage.setScene(sceneMap.get("place bet scene"));
			}
		});
		startScene.getStartingAmount().setOnKeyPressed(e->{
			if (e.getCode() == KeyCode.ENTER) {
				String startingAmountString = startScene.getStartingAmount().getText();
				if(startButtonListener(startingAmountString, startScene))
				{
					placeBetScene.getBankLabel().setText("Bank: " + startingAmountString);
					primaryStage.setScene(sceneMap.get("place bet scene"));
				}

			}
		});

		placeBetScene.getBetButton().setOnAction(e->{
			betAmountString = placeBetScene.getBetAmountField().getText();
			if(startBet(betAmountString, placeBetScene))
				primaryStage.setScene(sceneMap.get("start scene"));
		});
		placeBetScene.getBetAmountField().setOnKeyPressed(e->{
			if (e.getCode() == KeyCode.ENTER) {
				betAmountString = placeBetScene.getBetAmountField().getText();
				if(startBet(betAmountString, placeBetScene))
					primaryStage.setScene(sceneMap.get("start scene"));
			}
		});
		primaryStage.setScene(sceneMap.get("start scene"));
		primaryStage.show();
	}
}