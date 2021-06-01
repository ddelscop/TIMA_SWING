package grafica.controladores;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.fachada.Fachada;
import logicaPersistencia.valueObjects.VOCliente;
import logicaPersistencia.valueObjects.VOFuncionario;
import logicaPersistencia.valueObjects.VOTuplaHorasFuncionario;

public class ControladorHistorialQuincenas {
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
	
	public List<String> listarFuncionarios(int i) {
		Fachada f = new Fachada();
		return f.listarFuncionarios(i);
	}
	
	public int obtenerIdFunNomApe(String nomApe) throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdFunNomApe(nomApe);
	}
	
	public List<VOTuplaHorasFuncionario> listarHorasPorClienteServicio(int idCli, int idServ, String fechaInicio, String fechaFin) {
		Fachada f = new Fachada();
		return f.listarHorasPorClienteServicio(idCli, idServ, fechaInicio, fechaFin);
	}
	
	public List<VOTuplaHorasFuncionario> listarDetalleHoras(int idCli,String fechaInicio, String fechaFin) {
		Fachada f = new Fachada();
		return f.listarDetalleHoras(idCli, fechaInicio, fechaFin);
	}
	
	public int obtenerHonorariosCliente(int idCli)	throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerHonorariosCliente(idCli);
	}
	
	public int obtenerMonedaCliente(int idCli)	throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerMonedaCliente(idCli);
	}
	
	public List<VOTuplaHorasFuncionario> listarHorasMensualesCliente(int idFun,String fechaInicio, String fechaFin) {
		Fachada f = new Fachada();
		return f.listarHorasMensualesCliente(idFun, fechaInicio, fechaFin);
	}
	
	public int obtenerIdCliente(String nombre)	throws SQLException {
		Fachada f = new Fachada();
		return f.obtenerIdCliente(nombre);
	}
	
	public void borrarSeleccionHorasMensuales(int mes, int anio, int idFun, int idCli, int idServ) throws SQLException {
		Fachada f = new Fachada();
		f.borrarSeleccionHorasMensuales(mes, anio, idFun, idCli, idServ);
	}
}
