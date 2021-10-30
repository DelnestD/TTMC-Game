package views;

import java.util.ArrayList;
import java.util.Arrays;

import enums.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import models.Admin;
import models.BasicCard;
import models.Question;

public class AdminAddChangeFP extends FlowPane{
	private Label lblAuthor,lblSubject,lblTheme;
	private Label lblQuestion1,lblQuestion2,lblQuestion3,lblQuestion4;
	private Label lblAnswer1,lblAnswer2,lblAnswer3,lblAnswer4;
	private TextField txtAuthor,txtSubject;
	private TextField txtQuestion1,txtQuestion2,txtQuestion3,txtQuestion4;
	private TextField txtAnswer1,txtAnswer2,txtAnswer3,txtAnswer4;
	private ComboBox<Theme> cbxTheme;
	private Button btnValidation,btnBack,btnClear;
	private Admin admin;
	private BasicCard bc;
	
	public AdminAddChangeFP() {
		this.setOrientation(Orientation.VERTICAL);
		this.setPadding(new Insets(10));
		this.setVgap(6);
		
		HBox hbInfo=new HBox(getLblAuthor(),getTxtAuthor(),getLblSubject(),getTxtSubject(),getLblTheme(),getCbxTheme());
		hbInfo.setSpacing(5);
		HBox hbQuestion1=new HBox(getLblQuestion1(),getTxtQuestion1(),getLblAnswer1(),getTxtAnswer1());
		hbQuestion1.setSpacing(5);
		HBox hbQuestion2=new HBox(getLblQuestion2(),getTxtQuestion2(),getLblAnswer2(),getTxtAnswer2());
		hbQuestion2.setSpacing(5);
		HBox hbQuestion3=new HBox(getLblQuestion3(),getTxtQuestion3(),getLblAnswer3(),getTxtAnswer3());
		hbQuestion3.setSpacing(5);
		HBox hbQuestion4=new HBox(getLblQuestion4(),getTxtQuestion4(),getLblAnswer4(),getTxtAnswer4());
		hbQuestion4.setSpacing(5);
		HBox hbButton=new HBox(getBtnValidation(),getBtnClear(),getBtnBack());
		hbButton.setSpacing(5);
		
		this.getChildren().addAll(hbInfo,hbQuestion1,hbQuestion2,hbQuestion3,hbQuestion4,hbButton);
	}

	public void fillForm() {
		this.bc=admin.getBasicCard();
		txtAuthor.setText(bc.getAuthor().toString());
		txtSubject.setText(bc.getSubject());
		cbxTheme.getSelectionModel().select(bc.getTheme());
		txtQuestion1.setText(bc.getQuestion(0).getChallenge());
		txtAnswer1.setText(bc.getQuestion(0).getAnswer());
		txtQuestion2.setText(bc.getQuestion(1).getChallenge());
		txtAnswer2.setText(bc.getQuestion(1).getAnswer());
		txtQuestion3.setText(bc.getQuestion(2).getChallenge());
		txtAnswer3.setText(bc.getQuestion(2).getAnswer());
		txtQuestion4.setText(bc.getQuestion(3).getChallenge());
		txtAnswer4.setText(bc.getQuestion(3).getAnswer());
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
		if(!admin.isAddCard()) {
			fillForm();
		}
	}

	public Label getLblAuthor() {
		if(lblAuthor==null) {
			lblAuthor = new Label("Author : ");
		}
		return lblAuthor;
	}

	public Label getLblSubject() {
		if(lblSubject==null) {
			lblSubject = new Label("Subject : ");
		}
		return lblSubject;
	}

	public Label getLblTheme() {
		if(lblTheme==null) {
			lblTheme = new Label("Theme : ");
		}
		return lblTheme;
	}

	public Label getLblQuestion1() {
		if(lblQuestion1==null) {
			lblQuestion1=new Label("Question 1 : ");
		}
		return lblQuestion1;
	}
	public Label getLblQuestion2() {
		if(lblQuestion2==null) {
			lblQuestion2=new Label("Question 2 : ");
		}
		return lblQuestion2;
	}
	public Label getLblQuestion3() {
		if(lblQuestion3==null) {
			lblQuestion3=new Label("Question 3 : ");
		}
		return lblQuestion3;
	}
	public Label getLblQuestion4() {
		if(lblQuestion4==null) {
			lblQuestion4=new Label("Question 4 : ");
		}
		return lblQuestion4;
	}
	
	public Label getLblAnswer1() {
		if(lblAnswer1==null) {
			lblAnswer1=new Label("Answer 1 : ");
		}
		return lblAnswer1;
	}
	public Label getLblAnswer2() {
		if(lblAnswer2==null) {
			lblAnswer2=new Label("Answer 2 : ");
		}
		return lblAnswer2;
	}
	public Label getLblAnswer3() {
		if(lblAnswer3==null) {
			lblAnswer3=new Label("Answer 3 : ");
		}
		return lblAnswer3;
	}
	public Label getLblAnswer4() {
		if(lblAnswer4==null) {
			lblAnswer4=new Label("Answer 4 : ");
		}
		return lblAnswer4;
	}

	public TextField getTxtAuthor() {
		if(txtAuthor==null) {
			txtAuthor=new TextField();
		}
		
		return txtAuthor;
	}

	public TextField getTxtSubject() {
		if(txtSubject==null) {
			txtSubject = new TextField();
		}
		return txtSubject;
	}

	public ComboBox<Theme> getCbxTheme() {
		if(cbxTheme==null) {
			cbxTheme = new ComboBox<Theme>();
			for(Theme t:Theme.values()) {
				cbxTheme.getItems().add(t);
			}
			cbxTheme.getSelectionModel().selectFirst();
		}
		return cbxTheme;
	}

	public TextField getTxtQuestion1() {
		if(txtQuestion1==null) {
			txtQuestion1 = new TextField();
		}
		return txtQuestion1;
	}

	public TextField getTxtQuestion2() {
		if(txtQuestion2==null) {
			txtQuestion2 = new TextField();
		}
		return txtQuestion2;
	}

	public TextField getTxtQuestion3() {
		if(txtQuestion3==null) {
			txtQuestion3 = new TextField();
		}
		return txtQuestion3;
	}

	public TextField getTxtQuestion4() {
		if(txtQuestion4==null) {
			txtQuestion4 = new TextField();
		}
		return txtQuestion4;
	}

	public TextField getTxtAnswer1() {
		if(txtAnswer1==null) {
			txtAnswer1 = new TextField();
		}
		return txtAnswer1;
	}

	public TextField getTxtAnswer2() {
		if(txtAnswer2==null) {
			txtAnswer2 = new TextField();
		}
		return txtAnswer2;
	}

	public TextField getTxtAnswer3() {
		if(txtAnswer3==null) {
			txtAnswer3 = new TextField();
		}
		return txtAnswer3;
	}

	public TextField getTxtAnswer4() {
		if(txtAnswer4==null) {
			txtAnswer4 = new TextField();
		}
		return txtAnswer4;
	}

	//initialisation button
	public Button getBtnValidation() {
		//if not already initialised created it
		if(btnValidation==null) {
			btnValidation = new Button("Validation");
			
			btnValidation.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//create a temporary BasicCard
					Question q1=new Question(txtAuthor.getText(),cbxTheme.getValue(),txtSubject.getText(),txtQuestion1.getText(),txtAnswer1.getText());
					Question q2=new Question(txtAuthor.getText(),cbxTheme.getValue(),txtSubject.getText(),txtQuestion2.getText(),txtAnswer2.getText());
					Question q3=new Question(txtAuthor.getText(),cbxTheme.getValue(),txtSubject.getText(),txtQuestion3.getText(),txtAnswer3.getText());
					Question q4=new Question(txtAuthor.getText(),cbxTheme.getValue(),txtSubject.getText(),txtQuestion4.getText(),txtAnswer4.getText());
					bc = new BasicCard(txtAuthor.getText(),cbxTheme.getValue(),txtSubject.getText(),new ArrayList<Question>(Arrays.asList(q1,q2,q3,q4)));
					/*check adding mode
					 	-> if true add the temporary BasicCard
					 	-> if false update the select Basic card with de temporary Basic Card values*/
					if(admin.isAddCard()) {
						System.out.println(admin.addCard(bc));
					}else {
						System.out.println(admin.modifyCard(bc));
					}
					//Change of view
					NodeSP sp = (NodeSP)btnValidation.getParent().getParent().getParent();
					sp.selectVisible(4);
					//load the new data and refresh the tableView of AdminSelect
					ObservableList<BasicCard> data=FXCollections.observableArrayList(admin.getCards());
					((AdminSelectBP) sp.getChildren().get(4)).getTvCard().setItems(data);
					((AdminSelectBP) sp.getChildren().get(4)).getTvCard().refresh();
				}
				
			});
			
		}
		return btnValidation;
	}
	
	

	public Button getBtnBack() {
		if(btnBack==null) {
			btnBack = new Button("Back");
			btnBack.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					NodeSP sp = (NodeSP)btnBack.getParent().getParent().getParent();
					sp.selectVisible(4);
				}
			});
		}
		return btnBack;
	}

	public Button getBtnClear() {
		if(btnClear==null) {
			btnClear = new Button("Clear Form");
			btnClear.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					txtAuthor.clear();
					txtSubject.clear();
					cbxTheme.getSelectionModel().selectFirst();
					txtQuestion1.clear();
					txtAnswer1.clear();
					txtQuestion2.clear();
					txtAnswer2.clear();
					txtQuestion3.clear();
					txtAnswer3.clear();
					txtQuestion4.clear();
					txtAnswer4.clear();
				}
			});
		}
		return btnClear;
	}
	
}
