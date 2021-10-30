package views;

import java.util.ArrayList;
import java.util.List;

import enums.Difficulty;
import interfaces.GameVariables;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import models.Game;

public class GameWindowFP extends FlowPane implements GameVariables{

	private Label lblTeam;
	private Label lblTheme;
	private Label lblSubject;
	private Label lblInformation;
	private Label lblDifficulty;
	private Label lblQuestion;
	private Label lblScoreTeam1;
	private Label lblScoreTeam2;
	private Label lblScoreTeam3;
	private Label lblScoreTeam4;
	private Label lblName1;
	private Label lblName2;
	private Label lblName3;
	private Label lblName4;
	private Label lblTime;
	private Label lblOutOfTime;
	
	private Integer seconds;
	Timeline time = new Timeline();
	
	private List<RadioButton> rdDiff;
	private ToggleGroup tgrDiff;
	
	private Button btnGetQuestion;
	private Button btnValidate;
	
	private Game game;
	
	private TextField TxtReponse;

	private HBox hbdiff;
	private HBox hbScoreAndNames;
	private VBox vbName;
	private VBox vbScore;


	public GameWindowFP() {
		this.setOrientation(Orientation.VERTICAL);
		this.setPadding(new Insets(10));
		this.setVgap(6);
	
	}
		
	//Permet de selcetionner le niveau de difficulte
	private void getQuestion(List<RadioButton> rdDiff) {
		String difficulteString = ((RadioButton)tgrDiff.getSelectedToggle()).getText();
		int DifficultyLevel;
		if (difficulteString == "ONE") {
			DifficultyLevel = 0;	
		}
		else if(difficulteString == "TWO") {
			DifficultyLevel = 1;		
		}
		else if(difficulteString == "THREE") {
			DifficultyLevel = 2;
		}
		else if(difficulteString == "FOUR") {
			DifficultyLevel = 3;
		} else {
			DifficultyLevel = -1;
			System.out.println("ERROR. Il faut choisir une option.");
		}
		
		game.setLevel(DifficultyLevel);
		getLblQuestion().setText(game.getQuestion());
		System.out.println(game.getQuestion());
		System.out.println(game.getReponse()); // uniquement pour tester les reponses
		getBtnGetQuestion().setDisable(true); // pour empecher de choisir la difficulté plusieurs fois
		getBtnValidate().setDisable(false); // pour lassier utilisateur valider la reponse
		getHbdiff().setDisable(true); 
		getTxtReponse().setText("");
		getLblInformation().setText("");
			
	}
	
	private void printScores() {
		int nbrTeam = game.getNbrTeams();
		//show the name and the score of the 2 first team
		getLblName1().setText(game.getTeamName(0));
		getLblName2().setText(game.getTeamName(1));
		getLblScoreTeam1().setText(game.getScoreTeamByPos(0));
		getLblScoreTeam2().setText(game.getScoreTeamByPos(1));
		getLblName1().setVisible(true);
		getLblScoreTeam1().setVisible(true);
		getLblName2().setVisible(true);
		getLblScoreTeam2().setVisible(true);
		//only show the name and score of the 3rd team if it exists
		if (nbrTeam == 3) {
			getLblName3().setText(game.getTeamName(2));
			getLblScoreTeam3().setText(game.getScoreTeamByPos(2));
			getLblName3().setVisible(true);
			getLblScoreTeam3().setVisible(true);
		}
		//only show the name and score of the 3rd and the 4th team if it exists
		if (nbrTeam ==4) {
			getLblName3().setText(game.getTeamName(2));
			getLblScoreTeam3().setText(game.getScoreTeamByPos(2));
			getLblName4().setText(game.getTeamName(3));
			getLblScoreTeam4().setText(game.getScoreTeamByPos(3));
			getLblName3().setVisible(true);
			getLblScoreTeam3().setVisible(true);
			getLblName4().setVisible(true);
			getLblScoreTeam4().setVisible(true);
		}
		
	}
	
	private void goNext() {
		game.shuffleDeck(); 
		game.nextTour(); 
		getLblTeam().setText("Question for team: " + game.getTeamName(game.getTeamPlaying()));
		printScores();
		getLblTheme().setText("The theme is : " + game.getTheme());
		getLblSubject().setText("The subject is : " + game.getSubject());
		getLblQuestion().setText(""); //sert a nettoyer 
		getTxtReponse().setText("");  //sert a nettoyer
		getHbdiff().setDisable(false);
		getBtnGetQuestion().setDisable(false);
		getBtnValidate().setDisable(true);
	}
	
	//apres avoir clicker sur valider
	private void getReponse() {
		
		String reponse = TxtReponse.getText(); // recuperatoin de reponse 

		if (game.testAnswer(reponse)) {
				game.increaseTeamScore(game.getTeamPlaying(), game.getLevel()); 
				getLblInformation().setText("Correct answer! Team " + game.getTeamName(game.getTeamPlaying()) + " gets " + 
				game.getLevel() +" points and has total of " + game.getScoreTeam(game.getTeamPlaying()));
				getLblInformation().setStyle("-fx-text-fill : #FBEB9C; -fx-font-size : 15" );
			
			if  (game.getScoreTeam(game.getTeamPlaying()) >= POINTS_TO_WIN) {
				showWinner(); 
			}
			
		} else {	
			getLblInformation().setText("Reponse est incorrecte.");
			getLblInformation().setStyle("-fx-text-fill : #CF2817; -fx-font-size : 15" );
		}
		goNext();

	}
	
	// shows the winner
	public void showWinner() {
		Alert dialog= new Alert(AlertType.INFORMATION);
		dialog.setTitle("Game Over!");
		dialog.setHeaderText(game.getTeamName(game.getTeamPlaying()) + " is winner!");
		dialog.setContentText(game.sortTeamsByScore());
		dialog.showAndWait();			
		Platform.exit(); 
	}
	
	
	//countdown
	private void doTime() {
		if (time != null ) {
			time.stop();
		}
		//reinitialize the timer to 30 seconds
		seconds = STARTTIME;
		time = new Timeline(0);
		time.setCycleCount(GameVariables.STARTTIME);
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				//print the timer
				seconds--;
				lblTime.setText(" countdow : " + seconds.toString());
				//if the timer reach 0, the next team may start playing
				if (seconds <= 0) {
					getLblOutOfTime().setVisible(true);
					goNext();
				}
			}	
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}
	
	//getters and setters 
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
		this.getChildren().addAll(getLblTeam(), getLblTheme(), getLblSubject(), getLblDifficulty(), getHbdiff(),
				getBtnGetQuestion(),getLblQuestion(),getTxtReponse(),getBtnValidate(),getLblInformation(),
				getHbScoresAndNames(), getLblTime(), getLblOutOfTime());
	}

	public void setTxtLblTeam() {
		lblTeam.setText("Question for team " + game.getTeamPlaying());
	}

	public List<RadioButton> getRdDiff() {
		if(rdDiff == null) {
			rdDiff = new ArrayList<>();
			tgrDiff = new ToggleGroup();
			for(Difficulty ec :Difficulty.values()) {
				RadioButton rd=new RadioButton(ec.toString());
				rdDiff.add(rd);
				rd.setToggleGroup(tgrDiff);
			}
			if(!rdDiff.isEmpty()) {
				rdDiff.get(0).setSelected(true);
			}
		}
		return rdDiff;
	}
	
	public HBox getHbdiff() {
		if (hbdiff == null) {
			hbdiff = new HBox();
			hbdiff.setSpacing(10.);
			hbdiff.getChildren().addAll(getRdDiff());
		}
		return hbdiff;
	}
	
	public VBox getVbName() {
		if (vbName == null) {
			vbName = new VBox();
			vbName.setSpacing(10.);
			vbName.getChildren().addAll(getLblName1(), getLblName2(), getLblName3(), getLblName4());
		}
		return vbName;
	}	
	
	public VBox getVbScore() {
		if (vbScore == null) {
			vbScore = new VBox();
			vbScore.setSpacing(10.);
			vbScore.getChildren().addAll(getLblScoreTeam1(), getLblScoreTeam2(), getLblScoreTeam3(), getLblScoreTeam4());
		}
		return vbScore;
	}
	
	public HBox getHbScoresAndNames() {
		if (hbScoreAndNames == null) {
			hbScoreAndNames = new HBox();
			hbScoreAndNames.setSpacing(10.);
			hbScoreAndNames.getChildren().addAll(getVbName(), getVbScore());
		}
		return hbScoreAndNames;
	}
		
	public ToggleGroup getTgrDiff() {
		return tgrDiff;
	}

	public TextField getTxtReponse() {
		if (TxtReponse == null) {
			TxtReponse = new TextField("Write your answer here...");
		}
			TxtReponse.setOnKeyReleased(event->{
				if(event.getCode() == KeyCode.ENTER) {
					getReponse();
					time.stop();
				}
			});
		return TxtReponse;
	}
		
	public Button getBtnGetQuestion() {
		if(btnGetQuestion == null) {
			btnGetQuestion = new Button("Get Question");
			btnGetQuestion.setOnAction(e -> {
				getQuestion(rdDiff);
				doTime();
				lblTime.setVisible(true);
				lblOutOfTime.setVisible(false);
				});
			btnGetQuestion.setPrefWidth(100);
		}
		return btnGetQuestion;
	}
	
	//cannot validate without getting a question first
	public Button getBtnValidate() {
		if(btnValidate == null) {
			btnValidate = new Button("Validate");		
			btnValidate.setDisable(true);
			btnValidate.setOnAction(e -> {
				getReponse();
				time.stop();
			});
			btnValidate.setPrefWidth(100);
		}
		return btnValidate;
	}
	
	
	public Label getLblTheme() {
		if(lblTheme == null) {
			lblTheme= new Label("The theme is : "+game.getTheme());
			lblTheme.setStyle("-fx-text-fill : #E8F3AE; -fx-font-size : 15" );
		}
		return lblTheme;
	}
	
	public Label getLblSubject() {
		if(lblSubject == null) {
			lblSubject= new Label("The subject is : "+game.getCardInUse().getSubject());
			lblSubject.setStyle("-fx-text-fill : #E8F3AE; -fx-font-size : 15" );
		}
		return lblSubject;
	}
	
	public Label getLblTeam() {
		if(lblTeam == null) {
			lblTeam= new Label("Question for team 1");
			lblTeam.setText("Question for team: " + game.getIteratorTeams().currentItem().getName());
			lblTeam.setStyle("-fx-text-fill : #F8F8F8; -fx-font-size : 15" );
		}
		return lblTeam;
	}
	
	public Label getLblDifficulty() {
		if(lblDifficulty == null) {
			lblDifficulty = new Label("\nChoose a level of difficulty : ");
			lblDifficulty.setStyle("-fx-text-fill : #EBDFFF; -fx-font-size : 15" );
		}
		return lblDifficulty;
	}
	public Label getLblInformation() {
		if(lblInformation == null) {
			lblInformation = new Label("");
			lblInformation.setStyle("-fx-text-fill : #FBEB9C; -fx-font-size : 15" );
		}
		return lblInformation;
	}
		
	public Label getLblQuestion() {
		if(lblQuestion == null) {
			lblQuestion = new Label("");
			lblQuestion.setPrefWidth(400);
			lblQuestion.setWrapText(true);
			lblQuestion.setStyle("-fx-text-fill : #073676; -fx-font-size : 15" );
		}
		return lblQuestion;
	}
	
	public Label getLblScoreTeam1() {
		if(lblScoreTeam1 == null) {
			lblScoreTeam1 = new Label("0");
			lblScoreTeam1.setVisible(false);
			lblScoreTeam1.setStyle("-fx-text-fill : #E2FBF5; -fx-font-size : 15" );
		}
		return lblScoreTeam1;
	}
	
	public Label getLblScoreTeam2() {
		if(lblScoreTeam2 == null) {
			lblScoreTeam2 = new Label("0");
			lblScoreTeam2.setVisible(false);
			lblScoreTeam2.setStyle("-fx-text-fill : #E2FBF5; -fx-font-size : 15" );
		}
		return lblScoreTeam2;
	}
	
	public Label getLblScoreTeam3() {
		if(lblScoreTeam3 == null) {
			lblScoreTeam3 = new Label("0");
			lblScoreTeam3.setVisible(false);
			lblScoreTeam3.setStyle("-fx-text-fill : #E2FBF5; -fx-font-size : 15" );
		}
		return lblScoreTeam3;
	}
	
	public Label getLblScoreTeam4() {
		if(lblScoreTeam4 == null) {
			lblScoreTeam4 = new Label("0");
			lblScoreTeam4.setVisible(false);
			lblScoreTeam4.setStyle("-fx-text-fill : #E2FBF5; -fx-font-size : 15" );
		}
		return lblScoreTeam4;
	}

	public Label getLblName1() {
		if(lblName1 == null) {
			lblName1= new Label(game.getTeamName(0));
			lblName1.setVisible(false);
			lblName1.setStyle("-fx-text-fill : #E2FBF5; -fx-font-size : 15" );
		}
		return lblName1;
	}

	public Label getLblName2() {
		if(lblName2 == null) {
			lblName2 = new Label("Team2");
			lblName2.setVisible(false);
			lblName2.setStyle("-fx-text-fill : #E2FBF5; -fx-font-size : 15" );
		}
		return lblName2;
	}

	public Label getLblName3() {
		if(lblName3 == null) {
			lblName3 = new Label("Team3");
			lblName3.setVisible(false);
			lblName3.setStyle("-fx-text-fill : #E2FBF5; -fx-font-size : 15" );
		}
		return lblName3;
	}

	public Label getLblName4() {
		if(lblName4 == null) {
			lblName4 = new Label("Team4");
			lblName4.setVisible(false);
			lblName4.setStyle("-fx-text-fill : #E2FBF5; -fx-font-size : 15" );
		}
		return lblName4;
	}
	
	public Label getLblTime() {
		if(lblTime == null) {
			lblTime = new Label();
			lblTime.setTextFill(Color.ORANGE);
			lblTime.setFont(Font.font(30));
			lblTime.setVisible(false);
		}
		return lblTime;
	}
		
	public Label getLblOutOfTime() {
		if(lblOutOfTime == null) {
			lblOutOfTime= new Label("Oups ! Looks like you didn't answer in time !");
			lblOutOfTime.setVisible(false);
		}
		return lblOutOfTime;
	}
	
}
