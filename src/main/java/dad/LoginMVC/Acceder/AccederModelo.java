package dad.LoginMVC.Acceder;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AccederModelo {

	private StringProperty usuario = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private BooleanProperty checkbox = new SimpleBooleanProperty();
	
	public final StringProperty usuarioProperty() {
		return this.usuario;
	}
	
	public final String getUsuario() {
		return this.usuarioProperty().get();
	}
	
	public final void setUsuario(final String usuario) {
		this.usuarioProperty().set(usuario);
	}
	
	
	
	public final StringProperty passwordProperty() {
		return this.password;
	}
	
	public final String getPassword() {
		return this.passwordProperty().get();
	}
	
	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
	
	public final BooleanProperty checkboxProperty() {
		return this.checkbox;
	}
	
	public final Boolean getCheckbox() { 
		return this.checkboxProperty().get();
	}
	
	public final void setCheckbox(final Boolean checkbox) {
		this.checkboxProperty().set(checkbox);
	}
	
}
