package grafica.controladores;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.fachada.Fachada;
import logicaPersistencia.valueObjects.VOPermisos;

public class ControladorPerfilesDeAcceso {
	public void nuevoGrupo(String nombreGrupo) throws SQLException {
		Fachada f = new Fachada();
		f.nuevoGrupo(nombreGrupo);
	}
	
	public boolean existeGrupo(String nombreGrupo) throws SQLException {
		Fachada f = new Fachada();
		return f.existeGrupo(nombreGrupo);
	}
	
	public List<String> listarGrupNom() {
		Fachada f = new Fachada();
		return f.listarGrupNom();
	}
	
	public void eliminarGrupo(String grupo) throws SQLException {
		Fachada f = new Fachada();
		f.eliminarGrupo(grupo);
	}
	
	public VOPermisos obtenerPermisosGrupo(String grupo) {
		Fachada f = new Fachada();
		return f.obtenerPermisosGrupo(grupo);
	}
	
	public void actualizarPermisosGrupo(int perfAcceso, int abmFunc, int abmCli, int abmServ, int ingresoHoras, int controlFunc, int controlCli, String nombre) throws SQLException {
		Fachada f = new Fachada();
		f.actualizarPermisosGrupo(perfAcceso, abmFunc, abmCli, abmServ, ingresoHoras, controlFunc, controlCli, nombre);
	}
}
