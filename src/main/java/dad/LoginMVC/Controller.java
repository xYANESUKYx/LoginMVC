package dad.LoginMVC;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.directory.api.ldap.model.exception.LdapAuthenticationException;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller implements Initializable {
	
	//model
	private StringProperty usuario = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private BooleanProperty checkbox = new SimpleBooleanProperty();
	
	//view
	@FXML
	private VBox view;
	@FXML
    private CheckBox ldapCheckbox;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField usuarioText;

    
    public Controller(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/view.fxml"));
			loader.setController(this);
			loader.load();
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//bindings
		usuario.bind(usuarioText.textProperty());
		password.bind(passwordText.textProperty());
		checkbox.bind(ldapCheckbox.selectedProperty());

	}
	
	public VBox getView() {
		return view;
	}

	
	//lógica de negocio
	@FXML
	private void onAcceder(ActionEvent e) {
		if (checkbox.get() != true) {
			Boolean acceso = false;
			String linea = "";
			String users = "src/test/users.csv";
			BufferedReader buffer = null;
			
			try {
		        buffer = new BufferedReader(new FileReader(users));
		        while ((linea = buffer.readLine()) != null) {
		            if (linea.equals(usuario.get() + "," + DigestUtils.md5Hex(password.get()).toUpperCase())) {
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
			}}else {
				String ORG_ID = "616d58fc59fe3e7e11ad6fda";
				String LDAP_SERVER = "ldap.jumpcloud.com";
				int LDAP_PORT = 636;
				boolean LDAP_USE_SSL = true;
				String BASE_DN = "ou=Users,o=" + ORG_ID + ",dc=jumpcloud,dc=com";
				String BIND_STRING = "uid=%s," + BASE_DN;
				
				LdapConnection connection = null;
				
				try {
					connection = new LdapNetworkConnection(LDAP_SERVER, LDAP_PORT, LDAP_USE_SSL);
					connection.setTimeOut(0); 
					connection.bind(String.format(BIND_STRING, usuario.get()), password.get()); 
					alerta();
				} catch (LdapAuthenticationException ex) {
					alertaError();
				} catch (LdapException e1) {
					alertaError();
				} finally {
					try {
						connection.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
				}
			}
		}
		
	@FXML
		private void onCancelar(ActionEvent e) {
			Stage stage = (Stage) view.getScene().getWindow();
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
