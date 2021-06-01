package grafica.controladores;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.fachada.Fachada;
import logicaPersistencia.valueObjects.VOCliente;
import logicaPersistencia.valueObjects.VOFuncionario;
import logicaPersistencia.valueObjects.VOTuplaHorasFuncionario;

public class ControladorMantenimientoHoras {
	public List<String> listarClientes() {
		Fachada f = new Fachada();
		return f.listarClientes();
	}
	
	public List<String> listarServicios() {
		Fachada f = new Fachada();
		return f.listarServicios();
	}
	
	public int obtenerIdFuncionarioCI(String ci) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdFuncionarioCI(ci);
	}
	
	public VOFuncionario obtenerVOFuncionario(int idFun) {
		Fachada f = new Fachada();
		return f.obtenerVOFuncionario(idFun);
	}
	
	public void registrarHoras(int idFun, int idCli, int idServ, float horas, String fecha) throws SQLException {
		Fachada f = new Fachada();
		f.registrarHoras(idFun, idCli, idServ, horas, fecha);
	}
	
	public VOCliente obtenerVOClienteNombre(String nombre) {
		Fachada f = new Fachada();
		return f.obtenerVOClienteNombre(nombre);
	}
	
	public int obtenerIdServicio(String nombre) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdServicio(nombre);
	}
	
	public List<VOTuplaHorasFuncionario> listaTuplaHorasFuncionario(int idfunc, String fecha) {
		Fachada f = new Fachada();
		return f.listaTuplaHorasFuncionario(idfunc, fecha);
	}
	
	public List<VOTuplaHorasFuncionario> listarClienteSrvicio(int idfunc, String fechaInicio, String fechaFin) {
		Fachada f = new Fachada();
		return f.listarClienteSrvicio(idfunc, fechaInicio, fechaFin);
	}
	
	public int obtenerHorasFuncClienteServicio(int idFunc, String nombreCli, String nombreServ, String fecha) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerHorasFuncClienteServicio(idFunc, nombreCli, nombreServ, fecha);
	}
	
	public boolean existeHorasFuncClienteServicio(int idFunc, String nombreCli, String nombreServ, String fecha) throws SQLException {
		Fachada f = new Fachada();
		return f.existeHorasFuncClienteServicio(idFunc, nombreCli, nombreServ, fecha);
	}
	
	public int sumaHorasClienteServicioDia(int idFunc, String fecha) throws SQLException {
		Fachada f = new Fachada();
		return f.sumaHorasClienteServicioDia(idFunc, fecha);
	}
	
	public void modificarHoras(float horas, int idFun, String fecha, int idCli, int idServ) throws SQLException {
		Fachada f = new Fachada();
		f.modificarHoras(horas, idFun, fecha, idCli, idServ);
	}
	
	public int sumaHorasCargablesClienteServicioDia(int idFunc, String fecha) throws SQLException {
		Fachada f = new Fachada();
		return f.sumaHorasCargablesClienteServicioDia(idFunc, fecha);
	}
	
	public boolean existeHoraDia(String fecha, int idFun, int idCli, int idServ) throws SQLException {
		Fachada f = new Fachada();
		return f.existeHoraDia(fecha, idFun, idCli, idServ);
	}
	
	public boolean estaCerradoMes(int mes, int anio) throws SQLException {
		Fachada f = new Fachada();
		return f.estaCerradoMes(mes, anio);				
	}
	
	public void eliminarVisita(int idFun, int idCli, int idServ, String fecha) throws SQLException {
		Fachada f = new Fachada();
		f.eliminarVisita(idFun, idCli, idServ, fecha);
	}
	
	public boolean existeVisita(int idfun, int idcli, int idServ, String fecha) throws SQLException {
		Fachada f = new Fachada();
		return f.existeVisita(idfun, idcli, idServ, fecha);
	}
}