package grafica.controladores;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.fachada.Fachada;

public class ControladorAltaServicios {
	public void nuevoServicio(String nombre) throws SQLException {
		Fachada f = new Fachada();
		f.nuevoServicio(nombre);
	}
	
	public boolean existeServicio(String nombre) throws SQLException {
		Fachada f = new Fachada();
		return f.existeServicio(nombre);
	}
	
	public List<String> listarServicios() {
		Fachada f = new Fachada();
		return f.listarServicios();
	}
	
	public void modificarServicio(String nombreNEW, String nombreOLD) throws SQLException {
		Fachada f = new Fachada();
		f.modificarServicio(nombreNEW, nombreOLD);
	}
}
