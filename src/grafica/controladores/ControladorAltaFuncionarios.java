package grafica.controladores;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.accesoBD.AccesoBD;
import logicaPersistencia.fachada.Fachada;
import logicaPersistencia.valueObjects.VOFuncionario;

public class ControladorAltaFuncionarios {
	public void nuevoFuncionario(String nomFun, String apeFun, String ciFun, String FechNacFun, String celFun, int idGrupo, String pass, int horasDia) throws SQLException {
		Fachada f = new Fachada();
		f.nuevoFuncionario(nomFun, apeFun, ciFun, FechNacFun, celFun, idGrupo, pass, horasDia);
	}


	public List<String> listarFuncionarios(int i) {
		Fachada f = new Fachada();
		return f.listarFuncionarios(i);
	}
	
	public int obtenerIdFunNomApe(String nomApe) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdFunNomApe(nomApe);
	}
	
	public VOFuncionario obtenerVOFuncionario(int idFun) {
		Fachada f = new Fachada();
		return f.obtenerVOFuncionario(idFun);
	}
	
	public boolean existeCedulaFuncionario(String ci) throws SQLException {
		Fachada f = new Fachada();
		return f.existeCedulaFuncionario(ci);
	}
	
	public void bajaFuncionarioCI(String ciFun) throws SQLException {
		Fachada f = new Fachada();
		f.bajaFuncionarioCI(ciFun);
	}
	
	public void altaLogicaFuncionarioCI(String ciFun) throws SQLException {
		Fachada f = new Fachada();
		f.altaLogicaFuncionarioCI(ciFun);
	}
	
	public void modificarFuncionario(String nomFun, String apeFun, String ciFun, String celFun, int baja, int idGrupo, String pass, int horasDia, int idFun) throws SQLException {
		Fachada f = new Fachada();
		f.modificarFuncionario(nomFun, apeFun, ciFun, celFun, baja, idGrupo, pass, horasDia, idFun);
	}
	
	public int obtenerIdFuncionarioCI(String ci) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdFuncionarioCI(ci);
	}
	
	public List<String> listarGrupNom() {
		Fachada f = new Fachada();
		return f.listarGrupNom();
	}
	
	public int obtenerIdGrupo(String nombre)	throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdGrupo(nombre);
	}
	
	public String obtenerNombreIdGrupo(int idGrupo) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerNombreIdGrupo(idGrupo);
	}
	
}
