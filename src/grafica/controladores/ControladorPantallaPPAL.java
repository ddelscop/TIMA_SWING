package grafica.controladores;

import java.sql.SQLException;

import logicaPersistencia.fachada.Fachada;
import logicaPersistencia.valueObjects.VOFuncionario;
import logicaPersistencia.valueObjects.VOPermisos;

public class ControladorPantallaPPAL {
	
	public int obtenerIdFuncionarioCI(String ci) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdFuncionarioCI(ci);
	}
	
	public VOFuncionario obtenerVOFuncionario(int idFun) {
		Fachada f = new Fachada();
		return f.obtenerVOFuncionario(idFun);
	}
	
	public VOPermisos obtenerPermisos(int idGrupo) {
		Fachada f = new Fachada();
		return f.obtenerPermisos(idGrupo);
	}
	
	public String obtenerNombreIdGrupo(int idGrupo) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerNombreIdGrupo(idGrupo);
	}
	
	public VOPermisos obtenerPermisosGrupo(String grupo) {
		Fachada f = new Fachada();
		return f.obtenerPermisosGrupo(grupo);
	}
}
