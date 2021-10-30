package views;

import enums.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Admin;
import models.BasicCard;

public class AdminSelectBP extends BorderPane{
	private TableView<BasicCard> tvCard;
	private Button btnAddChange,btnDelete,btnBack;
	private Label lblQuestion1,lblQuestion2,lblQuestion3,lblQuestion4;
	private Label lblAnswer1,lblAnswer2,lblAnswer3,lblAnswer4;
	private Admin admin;
	
	public AdminSelectBP(Admin admin) {
		this.admin = admin;
		this.setPadding(new Insets(10));
		VBox vbRight=new VBox(getLblQuestion1(),getLblAnswer1(),getLblQuestion2(),getLblAnswer2(),getLblQuestion3(),getLblAnswer3(),getLblQuestion4(),getLblAnswer4());
		vbRight.setPadding(new Insets(10));
		HBox hbCenter=new HBox(getTvCard(),vbRight);
		this.setCenter(hbCenter);
		HBox hbBottom=new HBox(getBtnAddChange(),getBtnDelete(),getBtnBack());
		hbBottom.setSpacing(10);
		hbBottom.setPadding(new Insets(10));
		this.setBottom(hbBottom);
		HideLabel();		
	}
	
	public void HideLabel() {
		lblQuestion1.setVisible(false);
		lblQuestion2.setVisible(false);
		lblQuestion3.setVisible(false);
		lblQuestion4.setVisible(false);
		lblAnswer1.setVisible(false);
		lblAnswer2.setVisible(false);
		lblAnswer3.setVisible(false);
		lblAnswer4.setVisible(false);
	}
	
	public void SeeLabel() {
		lblQuestion1.setVisible(true);
		lblQuestion2.setVisible(true);
		lblQuestion3.setVisible(true);
		lblQuestion4.setVisible(true);
		lblAnswer1.setVisible(true);
		lblAnswer2.setVisible(true);
		lblAnswer3.setVisible(true);
		lblAnswer4.setVisible(true);
	}
	
	//initialisation TableView
	public TableView<BasicCard> getTvCard(){
		//if not already created
		if(tvCard==null) {
			//set the data, create the tableView and add the data
			ObservableList<BasicCard> data=FXCollections.observableArrayList(admin.getCards());
			tvCard = new TableView<>();
			tvCard.setItems(data);
			//set the police size
			tvCard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			//set columns
			TableColumn<BasicCard,String> colAuthor = new TableColumn<BasicCard,String>("Author");
			TableColumn<BasicCard,Theme> colTheme = new TableColumn<BasicCard,Theme>("Theme");
			TableColumn<BasicCard,String> colSubject = new TableColumn<BasicCard,String>("Subject");
			tvCard.getColumns().addAll(colAuthor,colTheme,colSubject);
			colAuthor.setCellValueFactory(new PropertyValueFactory<BasicCard,String>("Author"));
			colTheme.setCellValueFactory(new PropertyValueFactory<BasicCard,Theme>("Theme"));
			colSubject.setCellValueFactory(new PropertyValueFactory<BasicCard,String>("Subject"));
			/*if item of tableView clicked get the item
			 	-> if item selected show label of questions the item contains
			 	-> if no item selected hide label of questions*/
			tvCard.setOnMouseClicked((MouseEvent event) ->{
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					BasicCard bc = tvCard.getSelectionModel().getSelectedItem();
					if(bc!=null) {
						admin.setBasicCard(bc);
						getLblQuestion1().setText("Question 1 : "+bc.getQuestion(0).getChallenge());
						getLblAnswer1().setText("Answer : "+bc.getQuestion(0).getAnswer());
						getLblQuestion2().setText("Question 2 : "+bc.getQuestion(1).getChallenge());
						getLblAnswer2().setText("Answer : "+bc.getQuestion(1).getAnswer());
						getLblQuestion3().setText("Question 3 : "+bc.getQuestion(2).getChallenge());
						getLblAnswer3().setText("Answer : "+bc.getQuestion(2).getAnswer());
						getLblQuestion4().setText("Question 4 : "+bc.getQuestion(3).getChallenge());
						getLblAnswer4().setText("Answer : "+bc.getQuestion(3).getAnswer());
						SeeLabel(); //function to show labels
						getBtnAddChange().setText("Modify"); //change name of button
						admin.setAddCard(false);
					}else{
						admin.setBasicCard(null);
						HideLabel();//function to hide labels
						getBtnAddChange().setText("Add"); //change name of button
						admin.setAddCard(true);
					}
				}
			});
			
		}
		return tvCard;
	}
	
	public Label getLblQuestion1() {
		if(lblQuestion1==null) {
			lblQuestion1=new Label("Question 1");
		}
		return lblQuestion1;
	}
	public Label getLblQuestion2() {
		if(lblQuestion2==null) {
			lblQuestion2=new Label("Question 2");
		}
		return lblQuestion2;
	}
	public Label getLblQuestion3() {
		if(lblQuestion3==null) {
			lblQuestion3=new Label("Question 3");
		}
		return lblQuestion3;
	}
	public Label getLblQuestion4() {
		if(lblQuestion4==null) {
			lblQuestion4=new Label("Question 4");
		}
		return lblQuestion4;
	}
	
	public Label getLblAnswer1() {
		if(lblAnswer1==null) {
			lblAnswer1=new Label("Answer 1");
		}
		return lblAnswer1;
	}
	public Label getLblAnswer2() {
		if(lblAnswer2==null) {
			lblAnswer2=new Label("Answer 2");
		}
		return lblAnswer2;
	}
	public Label getLblAnswer3() {
		if(lblAnswer3==null) {
			lblAnswer3=new Label("Answer 3");
		}
		return lblAnswer3;
	}
	public Label getLblAnswer4() {
		if(lblAnswer4==null) {
			lblAnswer4=new Label("Answer 4");
		}
		return lblAnswer4;
	}
	
	public Button getBtnAddChange() {
		if(btnAddChange==null) {
			btnAddChange = new Button("Add");
			btnAddChange.setPrefWidth(200);
			btnAddChange.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					NodeSP sp = (NodeSP)btnAddChange.getParent().getParent().getParent();
					((AdminAddChangeFP) sp.getChildren().get(5)).setAdmin(admin.clone());
					sp.selectVisible(5);
				}
			});
		}
		return btnAddChange;
	}
	public Button getBtnDelete() {
		if(btnDelete==null) {
			btnDelete = new Button("Delete");
			btnDelete.setPrefWidth(200);
			btnDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println(admin.removeCard());
					ObservableList<BasicCard> data=FXCollections.observableArrayList(admin.getCards());
					getTvCard().setItems(data);
					getTvCard().refresh();
					admin.setBasicCard(null);
					HideLabel();
					getBtnAddChange().setText("Add");
					admin.setAddCard(true);
				}
			});
		}
		return btnDelete;
	}
	public Button getBtnBack() {
		if(btnBack==null) {
			btnBack = new Button("Back");
			btnBack.setPrefWidth(200);
			btnBack.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					NodeSP sp = (NodeSP)btnBack.getParent().getParent().getParent();
					sp.selectVisible(0);
				}
			});
		}
		return btnBack;
	}
	
}
