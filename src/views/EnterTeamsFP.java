package views;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import enums.NbrTeam;
import exceptions.DoublonException;
import exceptions.InvalidPointsToWinException;
import exceptions.InvalidTeamNameException;
import interfaces.GameVariables;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import models.Game;
import models.Team;

public class EnterTeamsFP extends FlowPane implements GameVariables{
	
	private Game game;
	
	private Label lblTeam1;
	private Label lblTeam2;
	private Label lblTeam3;
	private Label lblTeam4;
	private Label lblEnter;
	
	private TextField txtTeam1;
	private TextField txtTeam2;
	private TextField txtTeam3;
	private TextField txtTeam4;
	
	private Button btnValidate;
	private Button btnValidate2;
	private boolean error = true;
	
	private List<RadioButton> rdDiff;
	private ToggleGroup tgrDiff;
	
	private HBox hbdiff;
	
	private int nbrTeams;
	
    @SuppressWarnings("unused")
	private Scene main;
    
    //constructor
	public EnterTeamsFP() {
		this.setOrientation(Orientation.VERTICAL);
		this.setPadding(new Insets(10));
		this.setVgap(5);
		
		this.getChildren().addAll(getLblEnter(), getHbdiff(), getBtnValidate2(),getLblTeam1(),getTxtTeam1(),getLblTeam2(),getTxtTeam2(),getLblTeam3(),getTxtTeam3(),
				getLblTeam4(),getTxtTeam4(),getBtnValidate());
		
	}
	
	//getters and setters
	
	public List<RadioButton> getRdDiff() {
		if(rdDiff == null) {
			rdDiff = new ArrayList<>();
			tgrDiff = new ToggleGroup();
			for(NbrTeam ec :NbrTeam.values()) {
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
	
	public ToggleGroup getTgrDiff() {
		return tgrDiff;
	}
	
	public HBox getHbdiff() {
		if (hbdiff == null) {
			hbdiff = new HBox();
			hbdiff.setSpacing(10.);
			hbdiff.getChildren().addAll(getRdDiff());
		}
		return hbdiff;
	}
	
	public Label getLblEnter() {
		if (lblEnter == null) {
			lblEnter = new Label("Enter the number of teams that will play");
			lblEnter.setStyle("-fx-text-fill : #F8F8F8; -fx-font-size : 20" );
		}
		return lblEnter;
	}
	
	public Label getLblTeam1() {
		if (lblTeam1 == null) {
			lblTeam1 = new Label("Team 1");
			lblTeam1.setVisible(false);
			lblTeam1.setStyle("-fx-text-fill : #125C23; -fx-font-size : 12" );
		}
		return lblTeam1;
	}

	public TextField getTxtTeam1() {
		if (txtTeam1 == null) {
			txtTeam1 = new TextField("Bananas1");
			txtTeam1.setVisible(false);
		}
		return txtTeam1;
	}

	public Label getLblTeam2() {
		if (lblTeam2 == null) {
			lblTeam2 = new Label("Team 2");
			lblTeam2.setVisible(false);
			lblTeam2.setStyle("-fx-text-fill : #125C23; -fx-font-size : 12" );
		}
		return lblTeam2;
	}

	public TextField getTxtTeam2() {
		if (txtTeam2 == null) {
			txtTeam2 = new TextField("Aritists2");
			txtTeam2.setVisible(false);
		}	
		return txtTeam2;
	}

	public Label getLblTeam3() {
		if(lblTeam3 == null) {
			lblTeam3 = new Label("Team 3");
			lblTeam3.setVisible(false);
			lblTeam3.setStyle("-fx-text-fill : #125C23; -fx-font-size : 12" );
		}
			
		return lblTeam3;
	}

	public TextField getTxtTeam3() {
		if(txtTeam3 == null) {
			txtTeam3 = new TextField("Programmers3");
			txtTeam3.setVisible(false);
		}
			
		return txtTeam3;
	}

	public Label getLblTeam4() {
		if (lblTeam4 == null) {
			lblTeam4 = new Label("Team 4");
			lblTeam4.setVisible(false);
			lblTeam4.setStyle("-fx-text-fill : #125C23; -fx-font-size : 12" );
		}
			
		return lblTeam4;
	}

	public TextField getTxtTeam4() {
		if (txtTeam4 == null) {
			txtTeam4 = new TextField("SeriesFans4");
			txtTeam4.setVisible(false);
		}
				
		return txtTeam4;
	}
	
	public Button getBtnValidate() {
		//creation de bouton
		if (btnValidate == null) {
				btnValidate = new Button("Validate Teams");
				btnValidate.setVisible(false);
				btnValidate.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent arg0) {
						
						
						NodeSP sp = (NodeSP)btnValidate.getParent().getParent();
						
						setGame(new Game(nbrTeams));
						//try to add a team to the list but only works if there is no exception
						if(nbrTeams>=2) {
						try {
							game.addTeam(new Team(getTxtTeam1().getText())); 
							getTxtTeam1().setDisable(true);
							error = true;
						} catch (InvalidTeamNameException | DoublonException | InvalidPointsToWinException e) {
								errorAlert(e.getMessage());
								error = false;
						} 
						try {
								game.addTeam(new Team(getTxtTeam2().getText()));
								getTxtTeam2().setDisable(true);
								error = true;
						} catch (InvalidTeamNameException | DoublonException | InvalidPointsToWinException e) {
								errorAlert(e.getMessage());
								error = false;

						}
						if(nbrTeams==3) {
						try {
								game.addTeam(new Team(getTxtTeam3().getText()));
								getTxtTeam3().setDisable(true);
								error = true;
						} catch (InvalidTeamNameException | DoublonException | InvalidPointsToWinException e) {
								errorAlert(e.getMessage());
								error = false;
						}
						}
						if(nbrTeams==4) {
						try {
								game.addTeam(new Team(getTxtTeam3().getText()));
								getTxtTeam3().setDisable(true);
								error = true;
						} catch (InvalidTeamNameException | DoublonException | InvalidPointsToWinException e) {
								errorAlert(e.getMessage());
								error = false;
						}
						try {
								game.addTeam(new Team(getTxtTeam4().getText()));
								getTxtTeam4().setDisable(true);
								error = true;
						} catch (InvalidTeamNameException | DoublonException | InvalidPointsToWinException e) {
								errorAlert(e.getMessage());
								error = false;
						}
						}
						if(error) {
							((GameWindowFP) sp.getChildren().get(2)).setGame(game.clone());
							sp.selectVisible(2);
							
						}
					}
				}
				});
			}
				
		return btnValidate;
	
	}
	
	public Button getBtnValidate2() {
		if(btnValidate2 == null) {
			btnValidate2 = new Button("Validate");		
			
			btnValidate2.setOnAction(e -> {
				nbrTeams = getNbrTeams(rdDiff);
				System.out.println(nbrTeams);
				btnValidate2.setDisable(true);
				setVi(nbrTeams);
			});
			btnValidate2.setPrefWidth(100);
		}
		return btnValidate2;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	//methods
		
		public void errorAlert(String am) {
			Alert dialog= new Alert(AlertType.ERROR);
			dialog.setTitle("ERROR");
			dialog.setHeaderText("Error in team Name");
			dialog.setContentText(am);
			dialog.showAndWait();
		}
		
		
		private int getNbrTeams(List<RadioButton> rdDiff) {
			String nbrString = ((RadioButton)tgrDiff.getSelectedToggle()).getText();
			if(nbrString == "TWO") {
				return 2;		
			}
			else if(nbrString == "THREE") {
				return 3;
			}
			else if(nbrString == "FOUR") {
				return 4;
			} else {
				return -1;
				
			}
			
		}
		
		private void setVi(int nb) {
			btnValidate.setVisible(true);
			getLblTeam1().setVisible(true);
			getLblTeam2().setVisible(true);
			getTxtTeam1().setVisible(true);
			getTxtTeam2().setVisible(true);
			if (nb == 3) {
				getLblTeam3().setVisible(true);
				getTxtTeam3().setVisible(true);
			}
			
		else if (nb == 4) {
				getLblTeam3().setVisible(true);
				getLblTeam4().setVisible(true);
				getTxtTeam3().setVisible(true);
				getTxtTeam4().setVisible(true);
			}
		}
	
}
