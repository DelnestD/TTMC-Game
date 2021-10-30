package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class AlertRules {
	
	public static void display(String title) {
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);;
		window.setTitle(title);
		window.setMinWidth(400);
		
		Label lblRules = new Label("Game Rules \nRead carefully before playing! \n\r"
				+ "Progression of the game :\r\n"
				+ "2 to 4 teams compete around questions \naccording to several themes.\n\r"
				+ "Each theme contains 4 questions \nof different difficulty.\n\r"
				+ "If a team answer correctly,\n they won the same number of point as the difficulty of the question.\r\n"
				+ "The winner is the team that gets 30 points.\n\r"
				+ "Specificity : \r\n"
				+ "\r\n"
				+ "Be careful not to put a space \nat the end of the answer \r\n"
				+ "Answers may contain uppercase \r\n"
				+ "Numbers must be written numerically only \r\n"
				+ "Some special characters are also available (- ') \n\n");
		lblRules.setStyle("-fx-text-fill : #F8F8F8; -fx-font-size : 15" );
		
		Button btnOK = new Button("OK");
		btnOK.setStyle("-fx-background-color : linear-gradient(#F7CA88, #DC9656);");
		btnOK.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(lblRules, btnOK);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #A1B56C;");
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

}
