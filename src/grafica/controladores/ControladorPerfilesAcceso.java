package grafica.controladores;

import java.sql.SQLException;

import logicaPersistencia.fachada.Fachada;
import logicaPersistencia.valueObjects.VOPermisos;

public class ControladorPerfilesAcceso {
	public void actualizarPermisos(int perfAcceso, int abmFunc, int abmCli, int abmServ, int ingresoHoras, int controlFunc, int controlCli, int idGrupo) throws SQLException {
		Fachada f = new Fachada();
		f.actualizarPermisos(perfAcceso, abmFunc, abmCli, abmServ, ingresoHoras, controlFunc, controlCli, idGrupo);
	}
	
	public VOPermisos obtenerPermisos(int idGrupo) {
		Fachada f = new Fachada();
		return f.obtenerPermisos(idGrupo);
	}
	
	
	
	
	
}
