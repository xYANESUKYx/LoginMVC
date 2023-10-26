package dad.LoginMVC.Acceder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class AccederControlador {
	
	private AccederView view = new AccederView();
	private AccederModelo model = new AccederModelo();
	
	public AccederControlador(){

		view.getUsuarioText().textProperty().bindBidirectional(model.usuarioProperty());
		view.getPasswordText().textProperty().bindBidirectional(model.passwordProperty());
		view.getCheckbox().selectedProperty().bindBidirectional(model.checkboxProperty());
		
		view.getAccederButton().setOnAction(this::onAccederAction);
		view.getCancelarButton().setOnAction(this::onCancelarAction);
	}
	
	public AccederView getView() {
		return view;
	}
	
	public AccederModelo getModel() {
		return model;
	}
	
	//logica de negocio
	private void onAccederAction(ActionEvent e) {
		Boolean acceso = false;
		
		String linea = "";
		String users = "src/test/users.csv";
		BufferedReader buffer = null;
		
		try {
            buffer = new BufferedReader(new FileReader(users));
            while ((linea = buffer.readLine()) != null) {
                if (linea.equals(model.getUsuario() + "," + DigestUtils.md5Hex(model.getPassword()).toUpperCase())) {
                	acceso = true;
                	break;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		
		if (acceso == true) {
			alerta();
		}else {
			alertaError();
		}
	}
	private void onCancelarAction(ActionEvent e) {
		Stage stage = (Stage) view.getCancelarButton().getScene().getWindow();
	    stage.close();
	}
	
	private void alerta() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle ( "Inisiar seción" ); 
        alert.setHeaderText( "Acceso permitido" ); 
        alert.setContentText( "Las credenciales de acceso son válidas" );  

        alert.showAndWait();
    }
	
	private void alertaError() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle ( "Inisiar seción" ); 
        alert.setHeaderText( "Acceso denegado." ); 
        alert.setContentText( "El usuario y/o la contraseña no son válidos." );  

        alert.showAndWait();
    }
}