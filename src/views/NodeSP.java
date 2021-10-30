package views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import models.Admin;
import models.Game;

public class NodeSP extends StackPane{
	
	
	private List<Node> nodes;
	private Admin admin;
	public NodeSP(Admin admin) {
		this.setAdmin(admin);
		this.getChildren().addAll(getNodes());
		this.selectVisible(0);
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int visibleNode() {//renvoie l'indice du premier noeud visible
		for(int i=0; i<nodes.size(); i++){
			if(nodes.get(i).isVisible()) {
				return i;
			}
		}
		return -1;//normalement -1 n'est pas retourné
	}
	
	public void selectVisible(int indice) {
		if(indice<0 || indice>=nodes.size()) {
			nodes.get(0).setVisible(true);
		}
		for(int i=0; i<nodes.size(); i++){
			if(i!= indice) {
				nodes.get(i).setVisible(false);
			}else {
				nodes.get(i).setVisible(true);
			}
		}
	}
	
	public List<Node> getNodes() {
		if(nodes == null) {
			nodes = new ArrayList<>();
			nodes.addAll(Arrays.asList(new MenuFP(),new EnterTeamsFP(), new GameWindowFP(), new AdminLoginFP(), new AdminSelectBP(admin.clone()),new AdminAddChangeFP()));
			
		}
		return nodes;
	}
	
}
