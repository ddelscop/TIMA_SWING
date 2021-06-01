package grafica.controladores;

import logicaPersistencia.fachada.Fachada;

public class ControladorPantallaLogin {
	public boolean validarLogin(String ci, String pass) {
		Fachada f= new Fachada();
		return f.validarLogin(ci, pass);
	}
	
	
	
	
	
}
