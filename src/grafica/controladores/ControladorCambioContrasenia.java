package grafica.controladores;

import java.sql.SQLException;

import logicaPersistencia.fachada.Fachada;
import logicaPersistencia.valueObjects.VOFuncionario;

public class ControladorCambioContrasenia {
	public int obtenerIdFuncionarioCI(String ci) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdFuncionarioCI(ci);
	}
	
	public VOFuncionario obtenerVOFuncionario(int idFun) {
		Fachada f = new Fachada();
		return f.obtenerVOFuncionario(idFun);
	}
	
	public void modificarContrasenia(int idFun, String passNuevo) throws SQLException {
		Fachada f = new Fachada();
		f.modificarContrasenia(idFun, passNuevo);
	}
}
