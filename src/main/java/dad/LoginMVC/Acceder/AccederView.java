package dad.LoginMVC.Acceder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AccederView extends VBox {

	private Label usuarioLabel;
	private Label passwordLabel;
	private TextField usuarioText;
	private PasswordField passwordText;
	private CheckBox checkbox;
	private Button accederButton;
	private Button cancelarButton;

	public AccederView() {
		super();
        
		usuarioLabel = new Label("Usuario:");
		usuarioText = new TextField();
		usuarioText.setPromptText("Nombre de usuario");
		usuarioText.setPrefWidth(150);
		
		passwordLabel = new Label("Contraseña:");
		passwordText = new PasswordField();
		passwordText.setPromptText("Contraseña de usuario");
		passwordText.setPrefWidth(150);
		
		checkbox = new CheckBox("Usar LDAP");
		checkbox.setSelected(false);
		
		GridPane gridpane = new GridPane();
		gridpane.setVgap(5);
		gridpane.setHgap(5);
		gridpane.setPadding(new Insets(5));
		gridpane.addRow(0, usuarioLabel, usuarioText);
		gridpane.addRow(1, passwordLabel, passwordText);
		gridpane.addRow(2, new Label(""), checkbox);
		
		accederButton = new Button("Acceder");
		cancelarButton = new Button("Cancelar");
		
		HBox hBox = new HBox(5, accederButton, cancelarButton);

		getChildren().addAll(gridpane, hBox);
		setSpacing(5);
		setFillWidth(false);
		setAlignment(Pos.CENTER);
	}

	public Label getUsuarioLabel() {return usuarioLabel;}

	public Label getPasswordLabel() {return passwordLabel;}

	public TextField getUsuarioText() {return usuarioText;}

	public TextField getPasswordText() {return passwordText;}
	
	public CheckBox getCheckbox() {return checkbox;}

	public Button getAccederButton() {return accederButton;}

	public Button getCancelarButton() {return cancelarButton;}
	
}
