package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import models.Admin;
import models.Game;
import views.RootBP;
import javafx.scene.Scene;
//--module-path ${FX} --add-modules=ALL-MODULE-PATH

public class MainProjet extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Admin admin = new Admin();
			RootBP root = new RootBP(admin.clone());
			//Scene scene = new Scene(root,425,325);
			Scene scene = new Scene(root,800,350);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("./MAD - Card Game");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//test push
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}
