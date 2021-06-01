package grafica.controladores;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.fachada.Fachada;
import logicaPersistencia.valueObjects.VOCliente;
import logicaPersistencia.valueObjects.VOPerFisica;
import logicaPersistencia.valueObjects.VOPerJuridica;

public class ControladorCompliance {
	public List<String> listarPersonasFisicas() {
		Fachada f = new Fachada();
		return f.listarPersonasFisicas();
	}
	
	public List<String> listarPersonasJuridicas() {
		Fachada f = new Fachada();
		return f.listarPersonasJuridicas();
	}
	
	public VOCliente obtenerVOClienteNombre(String nombre) {
		Fachada f = new Fachada();
		return f.obtenerVOClienteNombre(nombre);
	}
	
	public void agregarPersonaFisica(VOPerFisica persona) throws SQLException {
		Fachada f = new Fachada();
		f.agregarPersonaFisica(persona);
	}
	
	public void agregarPersonaJuridica(VOPerJuridica persona) throws SQLException {
		Fachada f = new Fachada();
		f.agregarPersonaJuridica(persona);
	}
	
	public boolean existePersonaFisica(int idCli){
		Fachada f = new Fachada();
		return f.existePersonaFisica(idCli);
	}
	
	public boolean existePersonaJuridica(int idCli){
		Fachada f = new Fachada();
		return f.existePersonaJuridica(idCli);
	}
	
	public VOPerFisica obtenerPersonaFisica(int idCli) {
		Fachada f = new Fachada();
		return f.obtenerPersonaFisica(idCli);
	}
	
	public VOPerJuridica obtenerPersonaJuridica(int idCli) {
		Fachada f = new Fachada();
		return f.obtenerPersonaJuridica(idCli);
	}
	
	public boolean existeNombreCliente(String nombre) throws SQLException {
		Fachada f = new Fachada();
		return f.existeNombreCliente(nombre);
	}
	
	public void modificarPersonaFisica(VOPerFisica persona) throws SQLException {
		Fachada f = new Fachada();
		f.modificarPersonaFisica(persona);
	}
	
	public void modificarPersonaJuridica(VOPerJuridica persona) throws SQLException {
		Fachada f = new Fachada();
		f.modificarPersonaJuridica(persona);
	}
}
