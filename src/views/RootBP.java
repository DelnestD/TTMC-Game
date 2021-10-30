package views;


import javafx.scene.layout.BorderPane;
import models.Admin;
import models.Game;

public class RootBP extends BorderPane{
	
	private NodeSP spLogin;
	private Admin admin;
	public RootBP(Admin admin) {
		this.setAdmin(admin);
		this.setCenter(getSpLogin());
		//this.setBottom(getBtnNext());
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}



	public NodeSP getSpLogin() {
		if(spLogin == null) {
			spLogin = new NodeSP(admin.clone());
		}
		return spLogin;
	}

}

