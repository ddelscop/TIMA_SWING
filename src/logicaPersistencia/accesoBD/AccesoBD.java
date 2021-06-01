package logicaPersistencia.accesoBD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import logicaPersistencia.valueObjects.VOCliente;
import logicaPersistencia.valueObjects.VOFuncionario;
import logicaPersistencia.valueObjects.VOPerFisica;
import logicaPersistencia.valueObjects.VOPerJuridica;
import logicaPersistencia.valueObjects.VOPermisos;
import logicaPersistencia.valueObjects.VOTuplaHorasFuncionario;


public class AccesoBD {
	private String driver;
	private String username;
	private String pass;
	private String url;
	private String base;
	
	public Connection conectarBD() {
		// Cargo los datos desde el archivo de configuracion
		// Si existeBase = false, se conecta al servidor de BD nada mas, en otro
		// caso, se conecta a la base
		Connection con = null;
		try {
			Properties p = new Properties();
			try {
				p.load(new FileInputStream("config/parametros.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver = p.getProperty("driver");
			username = p.getProperty("usuario");
			pass = p.getProperty("password");
			url = p.getProperty("url");
			base = p.getProperty("bdatos");
			Class.forName(driver);
			con = DriverManager.getConnection(url + base, username, pass);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void desconectarBD(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void nuevoServicio(String nombre) throws SQLException {
		// Ingresa un nuevo servicio al sistema
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String insert = consultas.nuevoServicio();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(insert);
		pstmt.setString(1, nombre);
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public boolean existeServicio(String nombre) throws SQLException {
		// retorna true si existe el servicio
		Connection con = null;
		con = this.conectarBD();
		boolean existeActividad = false;
		try {
			Consultas consultas = new Consultas();
			String strActividades = consultas.existeServicio();
			PreparedStatement pstmt = con.prepareStatement(strActividades);
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existeActividad = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}	
		this.desconectarBD(con);
		return existeActividad;
	}
	
	public List<String> listarServicios() {
		Connection con = null;
		con = this.conectarBD();
		List<String> lstDescripciones = null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strDescripciones = consultas.listarServicios();
			PreparedStatement pstmt = con.prepareStatement(strDescripciones);

			ResultSet rs = pstmt.executeQuery();
			lstDescripciones = new ArrayList<String>();
			while (rs.next()) {				
				lstDescripciones.add(rs.getString("nombre"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstDescripciones;
	}
	
	public void modificarServicio(String nombreNEW, String nombreOLD) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.modificarServicio();		
			PreparedStatement pstmt = con.prepareStatement(update);
			pstmt.setString(1, nombreNEW);
			pstmt.setString(2, nombreOLD);
			pstmt.executeUpdate();
			pstmt.close();		
		this.desconectarBD(con);
	}
	
	public void nuevoCliente(String contacto, String rut, String nroCli, String tel, String direccion, int idDepto, String nomCli, int hsCargables, int honorarios, int moneda, int tipoPersona) throws SQLException {
		// Ingresa un nuevo servicio al sistema
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String insert = consultas.nuevoCliente();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(insert);
		pstmt.setString(1, contacto);
		pstmt.setString(2, rut);
		pstmt.setString(3, nroCli);
		pstmt.setString(4, tel);
		pstmt.setString(5, direccion);
		pstmt.setInt(6, idDepto);
		pstmt.setString(7, nomCli);
		pstmt.setInt(8, hsCargables);
		pstmt.setInt(9, honorarios);
		pstmt.setInt(10, moneda);
		pstmt.setInt(11, tipoPersona);
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public int obtenerIdDepto(String depto)	throws SQLException {
		// Dado un el nombre de la actividad retorna su id
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.obtenerIdDepto();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setString(1, depto);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int IdAct = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return IdAct;
	}
	
	public String obtenerNombreDepto(int idDepto) throws SQLException {
		// Dado un el nombre de la actividad retorna su id
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.obtenerNombreDepto();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setInt(1, idDepto);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		String depto = rs.getString(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return depto;
	}
	
	public boolean existeNombreCliente(String nombre) throws SQLException {
		// retorna true si existe el servicio
		Connection con = null;
		con = this.conectarBD();
		boolean existeActividad = false;
		try {
			Consultas consultas = new Consultas();
			String strActividades = consultas.existeNombreCliente();
			PreparedStatement pstmt = con.prepareStatement(strActividades);
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existeActividad = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}	
		this.desconectarBD(con);
		return existeActividad;
	}
	
	public boolean existeRUTCliente(String rut) throws SQLException {
		// retorna true si existe el servicio
		Connection con = null;
		con = this.conectarBD();
		boolean existeActividad = false;
		try {
			Consultas consultas = new Consultas();
			String strActividades = consultas.existeRUTCliente();
			PreparedStatement pstmt = con.prepareStatement(strActividades);
			pstmt.setString(1, rut);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existeActividad = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}	
		this.desconectarBD(con);
		return existeActividad;
	}
	
	public boolean existeNROCliente(String nroCli) throws SQLException {
		// retorna true si existe el servicio
		Connection con = null;
		con = this.conectarBD();
		boolean existeActividad = false;
		try {
			Consultas consultas = new Consultas();
			String strActividades = consultas.existeNROCliente();
			PreparedStatement pstmt = con.prepareStatement(strActividades);
			pstmt.setString(1, nroCli);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existeActividad = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}	
		this.desconectarBD(con);
		return existeActividad;
	}
	
	public List<String> listarClientes() {
		Connection con = null;
		con = this.conectarBD();
		List<String> lstClientes = null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strCli = consultas.listarClientes();
			PreparedStatement pstmt = con.prepareStatement(strCli);

			ResultSet rs = pstmt.executeQuery();
			lstClientes = new ArrayList<String>();
			while (rs.next()) {				
				lstClientes.add(rs.getString("nomCli"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstClientes;
	}
	
	public VOCliente obtenerVOClienteNombre(String nombre) {
		// Dada un nombre retorna un VOCliente
		VOCliente voCliente = null;
		Connection con = null;
		con = this.conectarBD();
		Statement stmt;

		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strClientes = consultas.obtenerVOCliente();

			PreparedStatement pstmt = con.prepareStatement(strClientes);
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			voCliente = new VOCliente(rs.getInt("idCli"), rs.getString("nomCli"), rs.getString("contacto"), rs.getString("rut"), rs.getString("nroCli"), rs.getString("tel"), rs.getString("direccion"), rs.getInt("idDepto"), rs.getInt("hsCargables"), rs.getInt("honorarios"), rs.getInt("moneda"),rs.getInt("tipoPersona"));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.desconectarBD(con);
		return voCliente;
	}
	
	public void modificarCliente(String contacto, String rut, String nroCli, String tel, String direccion, int idDepto, String nomCli, int idCli, int hsCargables, int honorarios, int moneda, int tipoPersona) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.modificarCliente();		
			PreparedStatement pstmt = con.prepareStatement(update);			
			pstmt.setString(1,contacto);
			pstmt.setString(2, rut);
			pstmt.setString(3, nroCli);
			pstmt.setString(4, tel);
			pstmt.setString(5, direccion);
			pstmt.setInt(6, idDepto);
			pstmt.setString(7, nomCli);
			pstmt.setInt(8, hsCargables);			
			pstmt.setInt(9, honorarios);
			pstmt.setInt(10, moneda);			
			pstmt.setInt(11, tipoPersona);
			pstmt.setInt(12, idCli);
			
			pstmt.executeUpdate();
			pstmt.close();		
		this.desconectarBD(con);
	}
	
	public void nuevoFuncionario(String nomFun, String apeFun, String ciFun, String FechNacFun, String celFun, int idGrupo, String pass, int horasDia) throws SQLException {
		// Ingresa un nuevo servicio al sistema
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String insert = consultas.nuevoFuncionario();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(insert);
		pstmt.setString(1, nomFun);
		pstmt.setString(2, apeFun);
		pstmt.setString(3, ciFun);
		pstmt.setString(4, FechNacFun);
		pstmt.setString(5, celFun);
		pstmt.setInt(6, 0);		
		pstmt.setInt(7, idGrupo);
		pstmt.setString(8, pass);
		pstmt.setInt(9, horasDia);		
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public List<String> listarFuncionarios(int i) {
		// funcionarios activos = 0, funcionarios dados de baja = 1
		Connection con = null;
		con = this.conectarBD();
		List<String> lstDescripciones = null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strDescripciones = consultas.listarFuncionarios();
			PreparedStatement pstmt = con.prepareStatement(strDescripciones);
			pstmt.setInt(1, i);

			ResultSet rs = pstmt.executeQuery();
			lstDescripciones = new ArrayList<String>();
			while (rs.next()) {				
				lstDescripciones.add(rs.getString("concat(nomFun, ' ', apeFun)"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstDescripciones;
	}
	
	public int obtenerIdFunNomApe(String nomApe) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.obtenerIdFunNomApe();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setString(1, nomApe);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int IdAct = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return IdAct;
	}
	
	public VOFuncionario obtenerVOFuncionario(int idFun) {
		// Dada un nombre retorna un VOCliente
		VOFuncionario voFuncionario = null;
		Connection con = null;
		con = this.conectarBD();
		Statement stmt;

		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strClientes = consultas.obtenerVOFuncionario();

			PreparedStatement pstmt = con.prepareStatement(strClientes);
			pstmt.setInt(1, idFun);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			voFuncionario = new VOFuncionario(rs.getInt("idFun"), rs.getString("nomFun"), rs.getString("apeFun"), rs.getString("ciFun"), rs.getString("fechNacFun"), rs.getString("celFun"), rs.getInt("baja"), rs.getInt("horasDia"),rs.getInt("idGrupo"),rs.getString("pass"));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.desconectarBD(con);
		return voFuncionario;
	}
	
	public boolean existeCedulaFuncionario(String ci) throws SQLException {
		Connection con = null;
		con = this.conectarBD();
		boolean existeActividad = false;
		try {
			Consultas consultas = new Consultas();
			String strActividades = consultas.existeCedulaFuncionario();
			PreparedStatement pstmt = con.prepareStatement(strActividades);
			pstmt.setString(1, ci);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existeActividad = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}	
		this.desconectarBD(con);
		return existeActividad;
	}
	
	public void bajaFuncionarioCI(String ciFun) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.bajaFuncionarioCI();		
			PreparedStatement pstmt = con.prepareStatement(update);			
			pstmt.setString(1,ciFun);
			pstmt.executeUpdate();
			pstmt.close();		
		this.desconectarBD(con);
	}
	
	public void altaLogicaFuncionarioCI(String ciFun) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.altaLogicaFuncionarioCI();		
			PreparedStatement pstmt = con.prepareStatement(update);			
			pstmt.setString(1,ciFun);
			pstmt.executeUpdate();
			pstmt.close();		
		this.desconectarBD(con);
	}
	

//UPDATE funcionarios SET nomFun = ?, apeFun = ?, ciFun = ?, celFun = ?, baja = ?, idGrupo = ?, pass = ?, horasDia = ? WHERE idFun = ?
	
	public void modificarFuncionario(String nomFun, String apeFun, String ciFun, String celFun, int baja, int idGrupo, String pass, int horasDia, int idFun) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.modificarFuncionario();		
			PreparedStatement pstmt = con.prepareStatement(update);			
			pstmt.setString(1,nomFun);
			pstmt.setString(2,apeFun);
			pstmt.setString(3, ciFun);
			pstmt.setString(4, celFun);
			pstmt.setInt(5, baja);
			pstmt.setInt(6, idGrupo);
			pstmt.setString(7, pass);
			pstmt.setInt(8, horasDia);
			pstmt.setInt(9, idFun);
			pstmt.executeUpdate();
			pstmt.close();		
		this.desconectarBD(con);
	}
	
	public int obtenerIdFuncionarioCI(String ci) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.obtenerIdFuncionarioCI();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setString(1, ci);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int IdAct = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return IdAct;
	}
	
	public boolean validarLogin(String ci, String pass) {
		Connection con = null;
		con = this.conectarBD();
		boolean esValidoUsuario = false;
		try {
			Consultas consultas = new Consultas();
			String strEsValido = consultas.validarLogin();
			PreparedStatement pstmt = con.prepareStatement(strEsValido);
			pstmt.setString(1, ci);
			pstmt.setString(2, pass);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				esValidoUsuario = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}	
		this.desconectarBD(con);
		return esValidoUsuario;
	}
	
	public void registrarHoras(int idFun, int idCli, int idServ, float horas, String fecha) throws SQLException {
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String insert = consultas.registrarHoras();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(insert);
		pstmt.setInt(1, idFun);
		pstmt.setInt(2, idCli);
		pstmt.setInt(3, idServ);
		pstmt.setFloat(4, horas);
		pstmt.setString(5, fecha);
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public int obtenerIdServicio(String nombre) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.obtenerIdServicio();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setString(1, nombre);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int IdAct = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return IdAct;
	}
	
	public List<VOTuplaHorasFuncionario> listaTuplaHorasFuncionario(int idfunc, String fecha) {
		Connection con = null;
		con = this.conectarBD();
		VOTuplaHorasFuncionario tupla = null;
		List<VOTuplaHorasFuncionario> tuplas = null;
		/* creo un Statement para listar todas las tuplas */
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strTuplas = consultas.listaTuplaHorasFuncionario();
			PreparedStatement pstmt = con.prepareStatement(strTuplas);
			pstmt.setInt(1, idfunc);
			pstmt.setString(2, fecha);

			ResultSet rs = pstmt.executeQuery();
			tuplas = new ArrayList<VOTuplaHorasFuncionario>();
			while (rs.next()) {
				String cliente = rs.getString("c.nomCli");
				String servicio = rs.getString("s.nombre");
				int horas = rs.getInt("h.horas");
				tupla = new VOTuplaHorasFuncionario(cliente, servicio, horas);				
				tuplas.add(tupla);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuplas;
	}
	
	public List<VOTuplaHorasFuncionario> listarClienteSrvicio(int idfunc, String fechaInicio, String fechaFin) {
		Connection con = null;
		con = this.conectarBD();
		VOTuplaHorasFuncionario tupla = null;
		List<VOTuplaHorasFuncionario> tuplas = null;
		/* creo un Statement para listar todas las tuplas */
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strTuplas = consultas.listarClienteSrvicio();
			PreparedStatement pstmt = con.prepareStatement(strTuplas);
			pstmt.setInt(1, idfunc);
			pstmt.setString(2, fechaInicio);
			pstmt.setString(3, fechaFin);

			ResultSet rs = pstmt.executeQuery();
			tuplas = new ArrayList<VOTuplaHorasFuncionario>();
			while (rs.next()) {
				String cliente = rs.getString("c.nomCli");
				String servicio = rs.getString("s.nombre");
				tupla = new VOTuplaHorasFuncionario(cliente, servicio);				
				tuplas.add(tupla);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuplas;
	}
	
	public int obtenerHorasFuncClienteServicio(int idFunc, String nombreCli, String nombreServ, String fecha) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.obtenerHorasFuncClienteServicio();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setInt(1, idFunc);
		pstmt.setString(2, nombreCli);
		pstmt.setString(3, nombreServ);
		pstmt.setString(4, fecha);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int IdAct = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return IdAct;
	}
	
	public boolean existeHorasFuncClienteServicio(int idFunc, String nombreCli, String nombreServ, String fecha) throws SQLException {
		Connection con = null;
		con = this.conectarBD();
		boolean existeActividadSocio = false;
		try {
			Consultas consultas = new Consultas();
			String strActividad = consultas.obtenerHorasFuncClienteServicio();
			PreparedStatement pstmt = con.prepareStatement(strActividad);
			pstmt.setInt(1, idFunc);
			pstmt.setString(2, nombreCli);
			pstmt.setString(3, nombreServ);
			pstmt.setString(4, fecha);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existeActividadSocio = true;
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			// e.printStackTrace();
		}
		// throw new PersistenciaException(e);
		this.desconectarBD(con);
		return existeActividadSocio;
	}
	
	public int sumaHorasClienteServicioDia(int idFunc, String fecha) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.sumaHorasClienteServicioDia();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setInt(1, idFunc);
		pstmt.setString(2, fecha);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int IdAct = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return IdAct;
	}
	
	public void modificarHoras(float horas, int idFun, String fecha, int idCli, int idServ) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.modificarHoras();		
			PreparedStatement pstmt = con.prepareStatement(update);			
			pstmt.setFloat(1,horas);
			pstmt.setInt(2,idFun);
			pstmt.setString(3, fecha);
			pstmt.setInt(4, idCli);
			pstmt.setInt(5, idServ);
			pstmt.executeUpdate();
			pstmt.close();		
		this.desconectarBD(con);
	}
	
	public int sumaHorasCargablesClienteServicioDia(int idFunc, String fecha) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.sumaHorasCargablesClienteServicioDia();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setInt(1, idFunc);
		pstmt.setString(2, fecha);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int IdAct = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return IdAct;
	}
	
	
	public boolean existeHoraDia(String fecha, int idFun, int idCli, int idServ) throws SQLException {
		// retorna true si existe una hora registrada en determinado dia
		Connection con = null;
		con = this.conectarBD();
		boolean existeActividad = false;
		try {
			Consultas consultas = new Consultas();
			String strActividades = consultas.existeHoraDia();
			PreparedStatement pstmt = con.prepareStatement(strActividades);
			pstmt.setString(1, fecha);
			pstmt.setInt(2, idFun);
			pstmt.setInt(3, idCli);
			pstmt.setInt(4, idServ);			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				existeActividad = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}	
		this.desconectarBD(con);
		return existeActividad;
	}
	
	public List<VOTuplaHorasFuncionario> listarHorasPorClienteServicio(int idCli, int idServ, String fechaInicio, String fechaFin) {
		Connection con = null;
		con = this.conectarBD();
		VOTuplaHorasFuncionario tupla = null;
		List<VOTuplaHorasFuncionario> tuplas = null;
		/* creo un Statement para listar todas las tuplas */
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strTuplas = consultas.listarHorasPorClienteServicio();
			PreparedStatement pstmt = con.prepareStatement(strTuplas);
			pstmt.setInt(1, idCli);
			pstmt.setInt(2, idServ);
			pstmt.setString(3, fechaInicio);
			pstmt.setString(4, fechaFin);

			ResultSet rs = pstmt.executeQuery();
			tuplas = new ArrayList<VOTuplaHorasFuncionario>();
			while (rs.next()) {
				String funcionario = rs.getString("concat(f.nomFun, ' ', f.apeFun)");
				int horas = rs.getInt("sum(h.horas)");
				tupla = new VOTuplaHorasFuncionario(horas, funcionario);				
				tuplas.add(tupla);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuplas;
	}
	
	public void actualizarPermisos(int perfAcceso, int abmFunc, int abmCli, int abmServ, int ingresoHoras, int controlFunc, int controlCli, int idGrupo) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.actualizarPermisos();		
			PreparedStatement pstmt = con.prepareStatement(update);			
			pstmt.setInt(1,perfAcceso);
			pstmt.setInt(2,abmFunc);
			pstmt.setInt(3, abmCli);
			pstmt.setInt(4, abmServ);
			pstmt.setInt(5, ingresoHoras);
			pstmt.setInt(6, controlFunc);
			pstmt.setInt(7, controlCli);			
			pstmt.setInt(8, idGrupo);
			pstmt.executeUpdate();
			pstmt.close();		
		this.desconectarBD(con);
	}
	
	public VOPermisos obtenerPermisos(int idGrupo) {
		VOPermisos voPermisos = null;
		Connection con = null;
		con = this.conectarBD();
		Statement stmt;

		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strClientes = consultas.obtenerPermisos();

			PreparedStatement pstmt = con.prepareStatement(strClientes);
			pstmt.setInt(1, idGrupo);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			voPermisos = new VOPermisos(rs.getInt("perfAcceso"),rs.getInt("abmFunc"),rs.getInt("abmCli"),rs.getInt("abmServ"),rs.getInt("ingresoHoras"),rs.getInt("controlFunc"), rs.getInt("controlCli"));			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.desconectarBD(con);
		return voPermisos;
	}
	
	public void modificarContrasenia(int idFun, String passNuevo) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.modificarContrasenia();		
			PreparedStatement pstmt = con.prepareStatement(update);			
			pstmt.setString(1,passNuevo);
			pstmt.setInt(2,idFun);
			pstmt.executeUpdate();
			pstmt.close();		
		this.desconectarBD(con);
	}
	
	public List<VOTuplaHorasFuncionario> listarDetalleHoras(int idCli,String fechaInicio, String fechaFin) {
		Connection con = null;
		con = this.conectarBD();
		VOTuplaHorasFuncionario tupla = null;
		List<VOTuplaHorasFuncionario> tuplas = null;
		/* creo un Statement para listar todas las tuplas */
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strTuplas = consultas.listarDetalleHoras();
			PreparedStatement pstmt = con.prepareStatement(strTuplas);
			pstmt.setInt(1, idCli);
			pstmt.setString(2, fechaInicio);
			pstmt.setString(3, fechaFin);
			ResultSet rs = pstmt.executeQuery();
			tuplas = new ArrayList<VOTuplaHorasFuncionario>();
			while (rs.next()) {
				String funcionario = rs.getString("concat(f.nomFun, ' ', f.apeFun)");
				String servicio = rs.getString("s.nombre");
				int horas = rs.getInt("sum(h.horas)");
				tupla = new VOTuplaHorasFuncionario(funcionario, servicio, "" + horas);				
				tuplas.add(tupla);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuplas;
	}
	
	public int obtenerHonorariosCliente(int idCli)	throws SQLException {
		// Dado un el nombre de la actividad retorna su id
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strHonorarios = consultas.obtenerHonorariosCliente();
		PreparedStatement pstmt = con.prepareStatement(strHonorarios);
		pstmt.setInt(1, idCli);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int honorarios = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return honorarios;
	}
	
	public int obtenerMonedaCliente(int idCli)	throws SQLException {
		// Dado un el nombre de la actividad retorna su id
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strHonorarios = consultas.obtenerMonedaCliente();
		PreparedStatement pstmt = con.prepareStatement(strHonorarios);
		pstmt.setInt(1, idCli);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int moneda = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return moneda;
	}
	
	public List<VOTuplaHorasFuncionario> listarHorasMensualesCliente(int idFun,String fechaInicio, String fechaFin) {
		Connection con = null;
		con = this.conectarBD();
		VOTuplaHorasFuncionario tupla = null;
		List<VOTuplaHorasFuncionario> tuplas = null;
		/* creo un Statement para listar todas las tuplas */
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strTuplas = consultas.listarHorasMensualesCliente();
			PreparedStatement pstmt = con.prepareStatement(strTuplas);
			pstmt.setInt(1, idFun);
			pstmt.setString(2, fechaInicio);
			pstmt.setString(3, fechaFin);
			ResultSet rs = pstmt.executeQuery();
			tuplas = new ArrayList<VOTuplaHorasFuncionario>();
			while (rs.next()) {
				String cliente = rs.getString("c.nomCli");
				String servicio = rs.getString("s.nombre");
				int horas = rs.getInt("sum(h.horas)");
				tupla = new VOTuplaHorasFuncionario(cliente, servicio, horas);				
				tuplas.add(tupla);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuplas;
	}
	
	public void nuevoGrupo(String nombreGrupo) throws SQLException {
		// Ingresa una nueva actividad al sistema
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String insert = consultas.nuevoGrupo();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(insert);
		pstmt.setString(1, nombreGrupo);
		pstmt.setInt(2, 0);
		pstmt.setInt(3, 0);
		pstmt.setInt(4, 0);
		pstmt.setInt(5, 0);
		pstmt.setInt(6, 0);
		pstmt.setInt(7, 0);
		pstmt.setInt(8, 0);		
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public boolean existeGrupo(String nombreGrupo) throws SQLException {
		// retorna true si existe el grupo
		Connection con = null;
		con = this.conectarBD();
		boolean existeGrupo = false;
		try {
			Consultas consultas = new Consultas();
			String strGrupos = consultas.existeGrupo();
			PreparedStatement pstmt = con.prepareStatement(strGrupos);
			pstmt.setString(1, nombreGrupo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existeGrupo = true;
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			// e.printStackTrace();
		}
		// throw new PersistenciaException(e);
		this.desconectarBD(con);
		return existeGrupo;
	}
	
	public List<String> listarGrupNom() {
		/*
		 * devuelve un listado de todos los nombres de los grupos
		 */
		Connection con = null;
		con = this.conectarBD();
		String grupo = null;
		List<String> lstGrupos = null;
		/* creo un Statement para listar todas los grupos */
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strGrupos = consultas.listarGrupNom();
			ResultSet rs = stmt.executeQuery(strGrupos);
			lstGrupos = new ArrayList<String>();
			while (rs.next()) {
				grupo = rs.getString("nombre");								
				lstGrupos.add(grupo);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.desconectarBD(con);
		return lstGrupos;
	}
	
	public void eliminarGrupo(String grupo) throws SQLException {
		// Elimina una actividad de un socio
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strGrupos = consultas.eliminarGrupo();
		PreparedStatement pstmt = con.prepareStatement(strGrupos);
		pstmt.setString(1, grupo);
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public VOPermisos obtenerPermisosGrupo(String grupo) {
		VOPermisos voPermisos = null;
		Connection con = null;
		con = this.conectarBD();
		Statement stmt;

		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strClientes = consultas.obtenerPermisosGrupo();

			PreparedStatement pstmt = con.prepareStatement(strClientes);
			pstmt.setString(1, grupo);
			ResultSet rs = pstmt.executeQuery();
			rs.next();						
			voPermisos = new VOPermisos( rs.getString("nombre"), rs.getInt("perfAcceso"), rs.getInt("abmFunc"),rs.getInt("abmCli"),rs.getInt("abmServ"),rs.getInt("ingresoHoras"),rs.getInt("controlFunc"), rs.getInt("controlCli"));			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.desconectarBD(con);
		return voPermisos;
	}
	
	public void actualizarPermisosGrupo(int perfAcceso, int abmFunc, int abmCli, int abmServ, int ingresoHoras, int controlFunc, int controlCli, String nombre) throws SQLException {		
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.actualizarPermisosGrupo();		
			PreparedStatement pstmt = con.prepareStatement(update);			
			pstmt.setInt(1,perfAcceso);
			pstmt.setInt(2,abmFunc);
			pstmt.setInt(3, abmCli);
			pstmt.setInt(4, abmServ);
			pstmt.setInt(5, ingresoHoras);
			pstmt.setInt(6, controlFunc);
			pstmt.setInt(7, controlCli);			
			pstmt.setString(8, nombre);
			pstmt.executeUpdate();
			pstmt.close();		
		this.desconectarBD(con);
	}
	
	public int obtenerIdGrupo(String nombre)	throws SQLException {
		// Dado un el nombre del g rupo retorna su id
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.obtenerIdGrupo();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setString(1, nombre);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int IdAct = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return IdAct;
	}
	
	public String obtenerNombreIdGrupo(int idGrupo) throws SQLException {
		// Dado un el nombre de la actividad retorna su id
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.obtenerNombreIdGrupo();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setInt(1, idGrupo);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		String depto = rs.getString(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return depto;
	}
	
	public int obtenerIdCliente(String nombre)	throws SQLException {
		// Dado un el nombre del g rupo retorna su id
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strIdDep = consultas.obtenerIdCliente();
		PreparedStatement pstmt = con.prepareStatement(strIdDep);
		pstmt.setString(1, nombre);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int IdAct = rs.getInt(1);
		rs.close();
		pstmt.close();
		this.desconectarBD(con);
		return IdAct;
	}
	
	public void borrarSeleccionHorasMensuales(int mes, int anio, int idFun, int idCli, int idServ) throws SQLException {
		// Elimina una selección de horas
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strGrupos = consultas.borrarSeleccionHorasMensuales(mes, anio);
		PreparedStatement pstmt = con.prepareStatement(strGrupos);
		pstmt.setInt(1, idFun);
		pstmt.setInt(2, idCli);
		pstmt.setInt(3, idServ);
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public void cerrarMes(int mes, int anio) throws SQLException {
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String insert = consultas.cerrarMes();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(insert);
		pstmt.setInt(1, mes);
		pstmt.setInt(2, anio);
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public boolean estaCerradoMes(int mes, int anio) throws SQLException {
		// retorna true si existe el servicio
		Connection con = null;
		con = this.conectarBD();
		boolean estaCerradoMes = false;
		try {
			Consultas consultas = new Consultas();
			String strActividades = consultas.estaCerradoMes();
			PreparedStatement pstmt = con.prepareStatement(strActividades);
			pstmt.setInt(1, anio);
			pstmt.setInt(2, mes);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				estaCerradoMes = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}	
		this.desconectarBD(con);
		return estaCerradoMes;
	}
	
	public void abridMes(int mes, int anio) throws SQLException {
		// Elimina una selección de horas
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strGrupos = consultas.abridMes();
		PreparedStatement pstmt = con.prepareStatement(strGrupos);
		pstmt.setInt(1, anio);
		pstmt.setInt(2, mes);		
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public void eliminarVisita(int idFun, int idCli, int idServ, String fecha) throws SQLException {
		// Elimina una vista
		Connection con = null;
		con = this.conectarBD();
		Consultas consultas = new Consultas();
		String strVisita = consultas.eliminarVisita();
		PreparedStatement pstmt = con.prepareStatement(strVisita);
		pstmt.setInt(1, idFun);
		pstmt.setInt(2, idCli);
		pstmt.setInt(3, idServ);
		pstmt.setString(4, fecha);
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	
	public boolean existeVisita(int idfun, int idcli, int idServ, String fecha) throws SQLException {
		// retorna true si existe el servicio
		Connection con = null;
		con = this.conectarBD();
		boolean existeVisita = false;
		try {
			Consultas consultas = new Consultas();
			String strVisitas = consultas.existeVisita();
			PreparedStatement pstmt = con.prepareStatement(strVisitas);
			pstmt.setInt(1, idfun);
			pstmt.setInt(2, idcli);
			pstmt.setInt(3, idServ);
			pstmt.setString(4, fecha);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existeVisita = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}	
		this.desconectarBD(con);
		return existeVisita;
	}
	
	public List<String> listarPersonasFisicas() {
		Connection con = null;
		con = this.conectarBD();
		List<String> lstClientes = null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strCli = consultas.listarPersonasFisicas();
			PreparedStatement pstmt = con.prepareStatement(strCli);

			ResultSet rs = pstmt.executeQuery();
			lstClientes = new ArrayList<String>();
			while (rs.next()) {				
				lstClientes.add(rs.getString("nomCli"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstClientes;
	}
	
	public List<String> listarPersonasJuridicas() {
		Connection con = null;
		con = this.conectarBD();
		List<String> lstClientes = null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strCli = consultas.listarPersonasJuridicas();
			PreparedStatement pstmt = con.prepareStatement(strCli);

			ResultSet rs = pstmt.executeQuery();
			lstClientes = new ArrayList<String>();
			while (rs.next()) {				
				lstClientes.add(rs.getString("nomCli"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstClientes;
	}
	
	public void agregarPersonaFisica(VOPerFisica persona) throws SQLException {
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String insert = consultas.agregarPersonaFisica();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(insert);

		pstmt.setInt(1, persona.getIdCliPerFis());
		pstmt.setInt(2, persona.getRxa());
		pstmt.setInt(3, persona.getEau());
		pstmt.setString(4, persona.getEmailPerFis());
		pstmt.setString(5, persona.getTipoDocPerFis());
		pstmt.setString(6, persona.getNumDocPerFis());
		pstmt.setString(7, persona.getPaisEmisionPerFis());
		pstmt.setString(8, persona.getFechNacPerFis());
		pstmt.setString(9, persona.getLugarNacPerFis());
		pstmt.setString(10, persona.getNacionalidadPerFis());
		pstmt.setString(11, persona.getEstCivilPerFis());
		pstmt.setString(12, persona.getProfesionPerFis());		
		pstmt.setString(13, persona.getPaiseDesPerFis());
		pstmt.setString(14, persona.getNomConyuguePerFis());
		pstmt.setString(15, persona.getTipoDocConyPerFis());
		pstmt.setString(16, persona.getNumDocConyPerFis());
		pstmt.setString(17, persona.getActividPerFis());
		pstmt.setString(18, persona.getNaturalezaTransacPerFis());
		pstmt.setString(19, persona.getOrigFondosPerFis());
		pstmt.setString(20, persona.getBancosPerFis());
		pstmt.setString(21, persona.getServicioReqFirmaPerFis());
		pstmt.setString(22, persona.getDesempTarPerFis());
		pstmt.setString(23, persona.getRefPerPerFis());
		pstmt.setString(24, persona.getRefFirmaPerFis());		
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public void agregarPersonaJuridica(VOPerJuridica persona) throws SQLException {
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String insert = consultas.agregarPersonaJuridica();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(insert);
		
		pstmt.setInt(1, persona.getIdcli());
		pstmt.setInt(2, persona.getRxa());
		pstmt.setInt(3, persona.getEau());

		pstmt.setString(4, persona.getFecha());
		pstmt.setString(5, persona.getLugconst());
		pstmt.setString(6, persona.getDomicilio());
		pstmt.setString(7, persona.getSede());
		pstmt.setString(8, persona.getTel());
		pstmt.setString(9, persona.getPagweb());
		pstmt.setString(10, persona.getActivprincip());
		pstmt.setString(11, persona.getVolingresos());		
		pstmt.setString(12, persona.getPaisdondedesact());
		pstmt.setString(13, persona.getOrigenfondos());
		pstmt.setString(14, persona.getPaisdondedesact());
		pstmt.setString(15, persona.getAccionistas());
		pstmt.setString(16, persona.getPropinatrelcom());
		pstmt.setString(17, persona.getRefbanc());
		pstmt.setString(18, persona.getRefprof());
		pstmt.setString(19, persona.getServfirma());
		pstmt.setString(20, persona.getRefpornuestrfirm());
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}

	
	public boolean existePersonaFisica(int idCli){
		Connection con = null;
		con = this.conectarBD();
		boolean existePersona = false;
		try {
			Consultas consultas = new Consultas();
			String strActividad = consultas.existePersonaFisica();
			PreparedStatement pstmt = con.prepareStatement(strActividad);
			pstmt.setInt(1, idCli);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existePersona = true;
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			 e.printStackTrace();
		}	
		this.desconectarBD(con);
		return existePersona;
	}
	
	
	public boolean existePersonaJuridica(int idCli){
		Connection con = null;
		con = this.conectarBD();
		boolean existePersona = false;
		try {
			Consultas consultas = new Consultas();
			String strActividad = consultas.existePersonaJuridica();
			PreparedStatement pstmt = con.prepareStatement(strActividad);
			pstmt.setInt(1, idCli);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				existePersona = true;
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			 e.printStackTrace();
		}	
		this.desconectarBD(con);
		return existePersona;
	}
	
	public VOPerFisica obtenerPersonaFisica(int idCli) {		
		VOPerFisica persona = null;
		Connection con = null;
		con = this.conectarBD();
		Statement stmt;

		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strClientes = consultas.obtenerPersonaFisica();

			PreparedStatement pstmt = con.prepareStatement(strClientes);
			pstmt.setInt(1, idCli);
			ResultSet rs = pstmt.executeQuery();
			rs.next();			
			persona= new VOPerFisica(
					rs.getInt("idCli"),
					rs.getInt("rxa"),
					rs.getInt("eau"),
					rs.getString("email"),
					rs.getString("tipodoccli"),
					rs.getString("numdoccli"),
					rs.getString("paisemision"),
					rs.getString("fechanaccli"),
					rs.getString("lugarnac"),
					rs.getString("nacionalidad"),
					rs.getString("estcivil"),
					rs.getString("profcli"),
					rs.getString("paisdesact"),
					rs.getString("nomcony"),
					rs.getString("tipodoccoy"),
					rs.getString("numdoccony"),
					rs.getString("negoactlav"),
					rs.getString("propinatrelcom"),
					rs.getString("orijfontransac"),
					rs.getString("bancos"),
					rs.getString("servfirma"),
					rs.getString("tardosanpep"),
					rs.getString("refprofs"),
					rs.getString("refnufirm"));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.desconectarBD(con);
		return persona;
	}
	
	public VOPerJuridica obtenerPersonaJuridica(int idCli) {		
		VOPerJuridica persona = null;
		Connection con = null;
		con = this.conectarBD();
		Statement stmt;

		try {
			stmt = con.createStatement();
			Consultas consultas = new Consultas();
			String strClientes = consultas.obtenerPersonaJuridica();

			PreparedStatement pstmt = con.prepareStatement(strClientes);
			pstmt.setInt(1, idCli);
			ResultSet rs = pstmt.executeQuery();
			rs.next();							
			
			persona= new VOPerJuridica(
					rs.getInt("idCli"),
					rs.getInt("rxa"),
					rs.getInt("eau"),
					rs.getString("fecha"),
					rs.getString("lugconst"),
					rs.getString("domicilio"),
					rs.getString("sede"),
					rs.getString("tel"),
					rs.getString("pagweb"),
					rs.getString("activprincip"),
					rs.getString("volingresos"),
					rs.getString("paisdondedesact"),
					rs.getString("origenfondos"),
					rs.getString("paisenquedesact"),
					rs.getString("accionistas"),
					rs.getString("propinatrelcom"),
					rs.getString("refbanc"),
					rs.getString("refprof"),
					rs.getString("servfirma"),
					rs.getString("refpornuestrfirm"));
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.desconectarBD(con);
		return persona;
	}
	
	
	public void modificarPersonaFisica(VOPerFisica persona) throws SQLException {
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String update = consultas.modificarPersonaFisica();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(update);

		pstmt.setInt(1, persona.getIdCliPerFis());
		pstmt.setInt(2, persona.getRxa());
		pstmt.setInt(3, persona.getEau());
		pstmt.setString(4, persona.getEmailPerFis());
		pstmt.setString(5, persona.getTipoDocPerFis());
		pstmt.setString(6, persona.getNumDocPerFis());
		pstmt.setString(7, persona.getPaisEmisionPerFis());
		pstmt.setString(8, persona.getFechNacPerFis());
		pstmt.setString(9, persona.getLugarNacPerFis());
		pstmt.setString(10, persona.getNacionalidadPerFis());
		pstmt.setString(11, persona.getEstCivilPerFis());
		pstmt.setString(12, persona.getProfesionPerFis());		
		pstmt.setString(13, persona.getPaiseDesPerFis());
		pstmt.setString(14, persona.getNomConyuguePerFis());
		pstmt.setString(15, persona.getTipoDocConyPerFis());
		pstmt.setString(16, persona.getNumDocConyPerFis());
		pstmt.setString(17, persona.getActividPerFis());
		pstmt.setString(18, persona.getNaturalezaTransacPerFis());
		pstmt.setString(19, persona.getOrigFondosPerFis());
		pstmt.setString(20, persona.getBancosPerFis());
		pstmt.setString(21, persona.getServicioReqFirmaPerFis());
		pstmt.setString(22, persona.getDesempTarPerFis());
		pstmt.setString(23, persona.getRefPerPerFis());
		pstmt.setString(24, persona.getRefFirmaPerFis());
		pstmt.setInt(25, persona.getIdCliPerFis());
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	public void modificarPersonaJuridica(VOPerJuridica persona) throws SQLException {
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String insert = consultas.modificarPersonaJuridica();
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(insert);
		
		pstmt.setInt(1, persona.getIdcli());
		pstmt.setInt(2, persona.getRxa());
		pstmt.setInt(3, persona.getEau());

		pstmt.setString(4, persona.getFecha());
		pstmt.setString(5, persona.getLugconst());
		pstmt.setString(6, persona.getDomicilio());
		pstmt.setString(7, persona.getSede());
		pstmt.setString(8, persona.getTel());
		pstmt.setString(9, persona.getPagweb());
		pstmt.setString(10, persona.getActivprincip());
		pstmt.setString(11, persona.getVolingresos());		
		pstmt.setString(12, persona.getPaisdondedesact());
		pstmt.setString(13, persona.getOrigenfondos());
		pstmt.setString(14, persona.getPaisdondedesact());
		pstmt.setString(15, persona.getAccionistas());
		pstmt.setString(16, persona.getPropinatrelcom());
		pstmt.setString(17, persona.getRefbanc());
		pstmt.setString(18, persona.getRefprof());
		pstmt.setString(19, persona.getServfirma());
		pstmt.setString(20, persona.getRefpornuestrfirm());
		pstmt.setInt(21, persona.getIdcli());
		pstmt.executeUpdate();
		pstmt.close();
		this.desconectarBD(con);
	}
	
	
	
	
}
