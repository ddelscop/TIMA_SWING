package grafica.controladores;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.fachada.Fachada;
import logicaPersistencia.valueObjects.VOCliente;

public class ControladorAltaClientes {
	public void nuevoCliente(String contacto, String rut, String nroCli, String tel, String direccion, int idDepto, String nomCli, int hsCargables, int honorarios, int moneda, int tipoPersona) throws SQLException {
		Fachada f = new Fachada();
		f.nuevoCliente(contacto, rut, nroCli, tel, direccion, idDepto, nomCli, hsCargables, honorarios, moneda,tipoPersona);
	}
	
	public int obtenerIdDepto(String depto)	throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdDepto(depto);		
	}
	
	public String obtenerNombreDepto(int idDepto) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerNombreDepto(idDepto);
	}
	
	public boolean existeNombreCliente(String nombre) throws SQLException {
		Fachada f = new Fachada();
		return f.existeNombreCliente(nombre);
	}
	
	public boolean existeRUTCliente(String rut) throws SQLException {
		Fachada f = new Fachada();
		return f.existeRUTCliente(rut);
	}
	
	public boolean existeNROCliente(String nroCli) throws SQLException {
		Fachada f = new Fachada();
		return f.existeNROCliente(nroCli);
	}
	
	public List<String> listarClientes() {
		Fachada f = new Fachada();
		return f.listarClientes();
	}
	
	public VOCliente obtenerVOClienteNombre(String nombre) {
		Fachada f = new Fachada();
		return f.obtenerVOClienteNombre(nombre);
	}
	
	public void modificarCliente(String contacto, String rut, String nroCli, String tel, String direccion, int idDepto, String nomCli, int idCli, int hsCargables, int honorarios, int moneda, int tipoPersona) throws SQLException {
		Fachada f = new Fachada();
		f.modificarCliente(contacto, rut, nroCli, tel, direccion, idDepto, nomCli, idCli, hsCargables, honorarios, moneda, tipoPersona);		
	}
	
}
