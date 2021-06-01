package logicaPersistencia.fachada;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.accesoBD.AccesoBD;
import logicaPersistencia.valueObjects.VOCliente;
import logicaPersistencia.valueObjects.VOFuncionario;
import logicaPersistencia.valueObjects.VOPerFisica;
import logicaPersistencia.valueObjects.VOPerJuridica;
import logicaPersistencia.valueObjects.VOPermisos;
import logicaPersistencia.valueObjects.VOTuplaHorasFuncionario;

public class Fachada {
	
	public void nuevoServicio(String nombre) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.nuevoServicio(nombre);
	}
	
	public boolean existeServicio(String nombre) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.existeServicio(nombre);
	}
	
	public List<String> listarServicios() {
		AccesoBD a = new AccesoBD();
		return a.listarServicios();
	}
	
	public void modificarServicio(String nombreNEW, String nombreOLD) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.modificarServicio(nombreNEW, nombreOLD);
	}
	
	public void nuevoCliente(String contacto, String rut, String nroCli, String tel, String direccion, int idDepto, String nomCli, int hsCargables, int honorarios, int moneda, int tipoPersona) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.nuevoCliente(contacto, rut, nroCli, tel, direccion, idDepto, nomCli, hsCargables, honorarios, moneda,tipoPersona);
	}
	
	public int obtenerIdDepto(String depto)	throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerIdDepto(depto);
	}
	
	public String obtenerNombreDepto(int idDepto) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerNombreDepto(idDepto);
	}
	
	public boolean existeNombreCliente(String nombre) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.existeNombreCliente(nombre);
	}
	
	public boolean existeRUTCliente(String rut) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.existeRUTCliente(rut);
	}
	
	public boolean existeNROCliente(String nroCli) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.existeNROCliente(nroCli);
	}
	
	public List<String> listarClientes() {
		AccesoBD a = new AccesoBD();
		return a.listarClientes();
	}
	
	public VOCliente obtenerVOClienteNombre(String nombre) {
		AccesoBD a = new AccesoBD();
		return a.obtenerVOClienteNombre(nombre);
	}
	
	public void modificarCliente(String contacto, String rut, String nroCli, String tel, String direccion, int idDepto, String nomCli, int idCli, int hsCargables, int honorarios, int moneda, int tipoPersona) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.modificarCliente(contacto, rut, nroCli, tel, direccion, idDepto, nomCli, idCli, hsCargables, honorarios, moneda, tipoPersona);
	}
	
	public void nuevoFuncionario(String nomFun, String apeFun, String ciFun, String FechNacFun, String celFun, int idGrupo, String pass, int horasDia) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.nuevoFuncionario(nomFun, apeFun, ciFun, FechNacFun, celFun,idGrupo, pass, horasDia);
	}
	
	public List<String> listarFuncionarios(int i) {
		AccesoBD a = new AccesoBD();
		return a.listarFuncionarios(i);
	}
	
	public int obtenerIdFunNomApe(String nomApe) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerIdFunNomApe(nomApe);
	}
	
	public VOFuncionario obtenerVOFuncionario(int idFun) {
		AccesoBD a = new AccesoBD();
		return a.obtenerVOFuncionario(idFun);
	}
	
	public boolean existeCedulaFuncionario(String ci) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.existeCedulaFuncionario(ci);
	}
	
	public void bajaFuncionarioCI(String ciFun) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.bajaFuncionarioCI(ciFun);
	}
	
	public void altaLogicaFuncionarioCI(String ciFun) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.altaLogicaFuncionarioCI(ciFun);
	}
	
	public void modificarFuncionario(String nomFun, String apeFun, String ciFun, String celFun, int baja, int idGrupo, String pass, int horasDia, int idFun) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.modificarFuncionario(nomFun, apeFun, ciFun, celFun, baja, idGrupo, pass, horasDia, idFun);
	}
	
	public int obtenerIdFuncionarioCI(String ci) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerIdFuncionarioCI(ci);
	}
	
	public boolean validarLogin(String ci, String pass) {
		AccesoBD a = new AccesoBD();
		return a.validarLogin(ci, pass);
	}
	
	public void registrarHoras(int idFun, int idCli, int idServ, float horas, String fecha) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.registrarHoras(idFun, idCli, idServ, horas, fecha);
	}
	
	public int obtenerIdServicio(String nombre) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerIdServicio(nombre);
	}
	
	public List<VOTuplaHorasFuncionario> listaTuplaHorasFuncionario(int idfunc, String fecha) {
		AccesoBD a = new AccesoBD();
		return a.listaTuplaHorasFuncionario(idfunc, fecha);
	}
	
	public List<VOTuplaHorasFuncionario> listarClienteSrvicio(int idfunc, String fechaInicio, String fechaFin) {
		AccesoBD a = new AccesoBD();
		return a.listarClienteSrvicio(idfunc, fechaInicio, fechaFin);
	}
	
	public int obtenerHorasFuncClienteServicio(int idFunc, String nombreCli, String nombreServ, String fecha) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerHorasFuncClienteServicio(idFunc, nombreCli, nombreServ, fecha);
	}
	
	public boolean existeHorasFuncClienteServicio(int idFunc, String nombreCli, String nombreServ, String fecha) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.existeHorasFuncClienteServicio(idFunc, nombreCli, nombreServ, fecha);
	}
	
	public int sumaHorasClienteServicioDia(int idFunc, String fecha) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.sumaHorasClienteServicioDia(idFunc, fecha);
	}
	
	public void modificarHoras(float horas, int idFun, String fecha, int idCli, int idServ) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.modificarHoras(horas, idFun, fecha, idCli, idServ);
	}
	
	public int sumaHorasCargablesClienteServicioDia(int idFunc, String fecha) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.sumaHorasCargablesClienteServicioDia(idFunc, fecha);
	}
	
	public boolean existeHoraDia(String fecha, int idFun, int idCli, int idServ) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.existeHoraDia(fecha, idFun, idCli, idServ);
	}
	
	public List<VOTuplaHorasFuncionario> listarHorasPorClienteServicio(int idCli, int idServ, String fechaInicio, String fechaFin) {
		AccesoBD a = new AccesoBD();
		return a.listarHorasPorClienteServicio(idCli, idServ, fechaInicio, fechaFin);
	}
	
	public void actualizarPermisos(int perfAcceso, int abmFunc, int abmCli, int abmServ, int ingresoHoras, int controlFunc, int controlCli, int idGrupo) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.actualizarPermisos(perfAcceso, abmFunc, abmCli, abmServ, ingresoHoras, controlFunc, controlCli, idGrupo);
	}
	
	public VOPermisos obtenerPermisos(int idGrupo) {
		AccesoBD a = new AccesoBD();
		return a.obtenerPermisos(idGrupo);
	}
	
	public void modificarContrasenia(int idFun, String passNuevo) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.modificarContrasenia(idFun, passNuevo);
	}
	
	public List<VOTuplaHorasFuncionario> listarDetalleHoras(int idCli,String fechaInicio, String fechaFin) {
		AccesoBD a = new AccesoBD();
		return a.listarDetalleHoras(idCli, fechaInicio, fechaFin);
	}
	
	public int obtenerHonorariosCliente(int idCli)	throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerHonorariosCliente(idCli);
	}
	
	public int obtenerMonedaCliente(int idCli)	throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerMonedaCliente(idCli);
	}
	
	public List<VOTuplaHorasFuncionario> listarHorasMensualesCliente(int idFun,String fechaInicio, String fechaFin) {
		AccesoBD a = new AccesoBD();
		return a.listarHorasMensualesCliente(idFun, fechaInicio, fechaFin);
	}
	
	public void nuevoGrupo(String nombreGrupo) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.nuevoGrupo(nombreGrupo);
	}
	
	public boolean existeGrupo(String nombreGrupo) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.existeGrupo(nombreGrupo);
	}
	
	public List<String> listarGrupNom() {
		AccesoBD a = new AccesoBD();
		return a.listarGrupNom();
	}
	
	public void eliminarGrupo(String grupo) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.eliminarGrupo(grupo);
	}
	
	public VOPermisos obtenerPermisosGrupo(String grupo) {
		AccesoBD a = new AccesoBD();
		return a.obtenerPermisosGrupo(grupo);
	}
	
	public void actualizarPermisosGrupo(int perfAcceso, int abmFunc, int abmCli, int abmServ, int ingresoHoras, int controlFunc, int controlCli, String nombre) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.actualizarPermisosGrupo(perfAcceso, abmFunc, abmCli, abmServ, ingresoHoras, controlFunc, controlCli, nombre);
	}
	
	public int obtenerIdGrupo(String nombre)	throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerIdGrupo(nombre);
	}
	
	public String obtenerNombreIdGrupo(int idGrupo) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerNombreIdGrupo(idGrupo);
	}
	
	public int obtenerIdCliente(String nombre)	throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.obtenerIdCliente(nombre);
	}
	
	public void borrarSeleccionHorasMensuales(int mes, int anio, int idFun, int idCli, int idServ) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.borrarSeleccionHorasMensuales(mes, anio, idFun, idCli, idServ);
	}
	
	public void cerrarMes(int mes, int anio) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.cerrarMes(mes, anio);
	}
	
	public boolean estaCerradoMes(int mes, int anio) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.estaCerradoMes(mes, anio);
	}
	
	public void abridMes(int mes, int anio) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.abridMes(mes, anio);
	}
	
	public void eliminarVisita(int idFun, int idCli, int idServ, String fecha) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.eliminarVisita(idFun, idCli, idServ, fecha);
	}
	
	public boolean existeVisita(int idfun, int idcli, int idServ, String fecha) throws SQLException {
		AccesoBD a = new AccesoBD();
		return a.existeVisita(idfun, idcli, idServ, fecha);
	}
	
	public List<String> listarPersonasFisicas() {
		AccesoBD a = new AccesoBD();
		return a.listarPersonasFisicas();
	}
	
	public List<String> listarPersonasJuridicas() {
		AccesoBD a = new AccesoBD();
		return a.listarPersonasJuridicas();
	}
	
	public void agregarPersonaFisica(VOPerFisica persona) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.agregarPersonaFisica(persona);
	}
	
	public void agregarPersonaJuridica(VOPerJuridica persona) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.agregarPersonaJuridica(persona);
	}
	
	public boolean existePersonaFisica(int idCli){
		AccesoBD a = new AccesoBD();
		return a.existePersonaFisica(idCli);
	}
	
	public boolean existePersonaJuridica(int idCli){
		AccesoBD a = new AccesoBD();
		return a.existePersonaJuridica(idCli);
	}
	
	public VOPerFisica obtenerPersonaFisica(int idCli) {
		AccesoBD a = new AccesoBD();
		return a.obtenerPersonaFisica(idCli);
	}
	
	public VOPerJuridica obtenerPersonaJuridica(int idCli) {
		AccesoBD a = new AccesoBD();
		return a.obtenerPersonaJuridica(idCli);
	}
	public void modificarPersonaFisica(VOPerFisica persona) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.modificarPersonaFisica(persona);
	}
	
	public void modificarPersonaJuridica(VOPerJuridica persona) throws SQLException {
		AccesoBD a = new AccesoBD();
		a.modificarPersonaJuridica(persona);
	}
	
}
