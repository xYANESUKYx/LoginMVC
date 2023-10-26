package dad.LoginMVC;

import dad.LoginMVC.Acceder.AccederControlador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{
	
	//controllers
	private AccederControlador accederControlador = new AccederControlador();
		
		
	@Override
	public void start(Stage primaryStage)throws Exception{
		
		Scene accederScene = new Scene(accederControlador.getView(), 320, 200);
		
		primaryStage.setScene(accederScene);
		primaryStage.setTitle("Login.fxml");
		primaryStage.show();
		primaryStage.getIcons().add(new Image("file:src/images/IMC.png"));
		
		
		//accederControlador.getModel().valorProperty().addListener((o, ov, nv) -> {
		//	System.out.println("Valor = "+ nv);
		//});
		
	}

}
