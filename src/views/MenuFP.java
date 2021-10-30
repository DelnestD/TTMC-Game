package views;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import interfaces.WindowsVariables;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;


public class MenuFP extends FlowPane{
	
	private Label lbltitreJeu;
	private Button btnFermer;
	private Button btnQuestion;
	private Button btnRegle;
	private Button btnStartGame;	
	private InputStream stream;
	private Image image;
	private ImageView imageView;
	
	public MenuFP() {
		this.setOrientation(Orientation.VERTICAL);
		this.setPadding(new Insets(10));
		this.setVgap(5);
		this.setAlignment(Pos.BASELINE_CENTER);
		this.getChildren().addAll(getImageView(), getBtnStartGame(),getBtnRegle(), getBtnQuestion(), getBtnFermer());
		
	}
	
	public ImageView getImageView() {
		if(imageView == null) {
			try {
				stream = new FileInputStream("img\\Logo.png");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		image = new Image(stream);
		imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(200);
		imageView.setPreserveRatio(true);
		return imageView;
	}
	
	public Button getBtnFermer() {
		if(btnFermer == null) {
			btnFermer = new Button("Close");
			btnFermer.setPrefWidth(WindowsVariables.BUTTON_SIZE_MENU);
			btnFermer.setOnAction((ActionEvent event) -> {
	            Platform.exit();
	        });
		}
		return btnFermer;
	}
	
	public Button getBtnQuestion() {
		if(btnQuestion == null) {
			btnQuestion = new Button("Access to Questions");
			btnQuestion.setPrefWidth(WindowsVariables.BUTTON_SIZE_MENU);
			btnQuestion.setOnAction(e -> {
				NodeSP sp = (NodeSP)btnStartGame.getParent().getParent();
				sp.selectVisible(3);
				}
		);
		}
		return btnQuestion;
	}
	
	public Button getBtnRegle() {
		if(btnRegle == null) {
			btnRegle = new Button("Rules");
			btnRegle.setPrefWidth(WindowsVariables.BUTTON_SIZE_MENU);
			btnRegle.setOnAction(e -> AlertRules.display("Rules"));
		}
		return btnRegle;
	}
	
	// on commence le jeu
	public Button getBtnStartGame() {
		if(btnStartGame == null) {
			btnStartGame = new Button("Start Game");
			btnStartGame.setPrefWidth(WindowsVariables.BUTTON_SIZE_MENU);
			btnStartGame.setOnAction(e -> {
					NodeSP sp = (NodeSP)btnStartGame.getParent().getParent();
					sp.selectVisible(1);
					}
			);
		}
		return btnStartGame;
	}
	
	public Label getLblTitreJeu() {
		if(lbltitreJeu == null) {
			lbltitreJeu = new Label("Welcome to MAD - Knownedge Card Game");
		}
		return lbltitreJeu;
	}
		
	
}
