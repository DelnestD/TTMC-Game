package views;

import interfaces.AdminVariables;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class AdminLoginFP extends FlowPane{
	private Label lblUsername,lblPassword;
	private TextField txtUsername;
	private PasswordField pwdPassword;
	private Button btnLogin,btnShowPassword;
	private String pwd,tmpError;
	private Alert error;
	public AdminLoginFP(){
		error= new Alert(AlertType.ERROR);
		error.setTitle("Login Error");
		this.setOrientation(Orientation.VERTICAL);
		this.setPadding(new Insets(10));
		this.setVgap(6);
		HBox hbUsername = new HBox(getLblUsername(),getTxtUsername());
		HBox hbPassword = new HBox(getLblPassword(),getPwdPassword());
		HBox hbButton = new HBox(getBtnLogin(),getBtnShowPassword());
		this.getChildren().addAll(hbUsername,hbPassword,hbButton);
	}
	
	public Label getLblUsername() {
		if(lblUsername==null) {
			lblUsername = new Label("Username : ");
		}
		return lblUsername;
	}
	public TextField getTxtUsername() {
		if(txtUsername==null) {
			txtUsername = new TextField();
		}
		return txtUsername;
	}
	public Label getLblPassword() {
		if(lblPassword==null) {
			lblPassword = new Label("Password : ");
		}
		return lblPassword;
	}
	public PasswordField getPwdPassword() {
		if(pwdPassword==null) {
			pwdPassword = new PasswordField();
		}
		return pwdPassword;
	}
	public Button getBtnLogin() {
		if(btnLogin==null) {
			btnLogin = new Button("Login");
			btnLogin.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(AdminVariables.USERNAME_ADMIN.equals(txtUsername.getText())&&AdminVariables.PASSWORD_ADMIN.equals(pwdPassword.getText())){
						NodeSP sp = (NodeSP) btnLogin.getParent().getParent().getParent();
						sp.selectVisible(4);
					}
					
					if(txtUsername.getText().isEmpty()&&pwdPassword.getText().isEmpty()) {
						error.setContentText("Empty Fields");
						error.showAndWait();			
						error.close();
					}else {
						if(txtUsername.getText().isEmpty()) {
							tmpError = "Username Empty and ";

						}else if(!AdminVariables.USERNAME_ADMIN.equals(txtUsername.getText())) {
							tmpError = "Wrong Username and ";
						}
						if(pwdPassword.getText().isEmpty()) {
							error.setContentText(tmpError+"Password Empty");
							error.showAndWait();			
							error.close();
						}else if(!AdminVariables.PASSWORD_ADMIN.equals(pwdPassword.getText())) {
							error.setContentText(tmpError+"Wrong Password");
							error.showAndWait();			
							error.close();
						}
					}
					txtUsername.clear();
					pwdPassword.clear();
					pwd="";
				}
			});
		}
		return btnLogin;
	}
	
	//initialisation of button for show password
	public Button getBtnShowPassword() {
		//if not already created
		if(btnShowPassword==null) {
			//cretion of button named Show Password
			btnShowPassword = new Button("Show Password");
			//if pressed show password
			btnShowPassword.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
				pwd = pwdPassword.getText();
				pwdPassword.clear();
				pwdPassword.setPromptText(pwd);
			});
			//if released hide password
			btnShowPassword.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
				pwdPassword.setText(pwd);
				pwdPassword.setPromptText("");
			});
			
		}
		return btnShowPassword;
	}
}
