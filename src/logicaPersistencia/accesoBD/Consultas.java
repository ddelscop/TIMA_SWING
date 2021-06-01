package logicaPersistencia.accesoBD;

public class Consultas {
	public String nuevoServicio(){
		/* crea el texto de la consulta que inserta un servicio */
			String consulta = "INSERT INTO servicios (nombre) VALUES (?);";
			return consulta;			
		}
	
	
	public String existeServicio(){
		/* crea el texto de la consulta que retorna true si existe el servicio en la tabla de servicios */
			String consulta = "SELECT nombre FROM servicios WHERE nombre = ?;";
			return consulta;
		}
	
	public String listarServicios(){
		String consulta="SELECT nombre FROM servicios order by nombre;";
		return consulta;
	}
	
	public String modificarServicio(){
		String consulta = "UPDATE servicios SET nombre = ? WHERE nombre = ?;";
		return consulta;
	}
	
	public String nuevoCliente(){
		/* crea el texto de la consulta que inserta un cliente */
			String consulta = "INSERT INTO clientes (contacto, rut, nroCli, tel, direccion, idDepto, nomCli, hsCargables, honorarios, moneda,tipoPersona) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
			return consulta;			
		}
	
	public String obtenerIdDepto(){
		String consulta = "select idDepto from departamentos where nombre = ?;";
		return consulta;
	}
	
	public String obtenerNombreDepto(){
		String consulta = "select nombre from departamentos where idDepto = ?;";
		return consulta;
	}
	
		
	public String existeNombreCliente(){
		String consulta = "select nomCli from clientes where nomCli = ?;";
		return consulta;
	}
	
	public String existeRUTCliente(){
		String consulta = "select rut from clientes where rut = ?;";
		return consulta;
	}
	
	public String existeNROCliente(){
		String consulta = "select nroCli from clientes where nroCli = ?;";
		return consulta;
	}
	
	public String listarClientes(){
		String consulta = "select nomCli from clientes order by nomCli;";
		return consulta;
	}
	
	public String obtenerVOCliente(){
		/* crea el texto de la consulta que retorna un VOCliente a partir de su nombre */
			String consulta = "select * from clientes where nomCli = ?;";
			return consulta;
	}
	
	public String modificarCliente(){
		String consulta = "UPDATE clientes SET contacto = ?, rut = ?, nroCli = ?, tel = ?, direccion = ?, idDepto = ?, nomCli = ?, hsCargables = ?, honorarios = ?, moneda = ?, tipoPersona = ? WHERE idCli = ?;";
		return consulta;
	}
	
	public String nuevoFuncionario(){
		/* crea el texto de la consulta que inserta un funcionario */
			String consulta = "insert into funcionarios (nomFun, apeFun, ciFun, FechNacFun, celFun, baja, idGrupo, pass, horasDia) values(?,?,?,?,?,?,?,?,?);";
			return consulta;			
	}
	
	public String listarFuncionarios(){
		String consulta="select concat(nomFun, ' ', apeFun) from funcionarios where baja =? order by nomFun, apeFun;";
		return consulta;
	}
	
	
	public String obtenerIdFunNomApe(){
		String consulta ="select idFun from funcionarios where concat(nomFun, ' ', apeFun) = ?;";
		return consulta;
	}
	
	public String obtenerVOFuncionario(){
		/* crea el texto de la consulta que retorna un VOCliente a partir de su nombre */
			String consulta = "select * from funcionarios where idFun = ?;";
			return consulta;
	}
	
	
	public String existeCedulaFuncionario(){
		String consulta = "select idFun from funcionarios where ciFun = ?;";
		return consulta;
	}
	
	public String bajaFuncionarioCI(){
		String consulta = "UPDATE funcionarios SET baja = 1 where ciFun = ?;";
		return consulta;
	}
	
	public String altaLogicaFuncionarioCI(){
		String consulta = "UPDATE funcionarios SET baja = 0 where ciFun = ?;";
		return consulta;
	}
	
	public String modificarFuncionario(){
		String consulta = "UPDATE funcionarios SET nomFun = ?, apeFun = ?, ciFun = ?, celFun = ?, baja = ?, idGrupo = ?, pass = ?, horasDia = ? WHERE idFun = ?;";
		return consulta;
	}
	
	public String obtenerIdFuncionarioCI(){
			String consulta = "select idFun from funcionarios where ciFun = ?;";
			return consulta;
	}
	
	public String validarLogin(){
		String consulta = "select * from funcionarios where ciFun = ? and pass = ?;";
		return consulta;
	}
	
	public String registrarHoras(){
		String consulta = "INSERT INTO HorasFunc (idFun, idCli, idServ, horas, fecha) VALUES (?, ?, ?, ?, ?);";
		return consulta;
	}
	
	public String obtenerIdServicio(){
		String consulta = "select idServ from servicios where nombre = ?;";
		return consulta;
	}
	
	public String listaTuplaHorasFuncionario(){
		String consulta = "select c.nomCli, s.nombre, h.horas from horasfunc h, clientes c, servicios s where c.idCli = h.idCli and s.idServ = h.idServ and h.idFun = ? and h.fecha = ?;";
		return consulta;
	}
	
	
	public String listarClienteSrvicio(){
		String consulta = "select c.nomCli, s.nombre from horasfunc h, clientes c, servicios s where c.idCli = h.idCli and s.idServ = h.idServ and h.idFun = ? and STR_TO_DATE(h.fecha, '%e/%c/%Y') between STR_TO_DATE(?, '%e/%c/%Y') and STR_TO_DATE(?, '%e/%c/%Y') group by c.nomCli, s.nombre;";
		return consulta;
	}
	
	public String obtenerHorasFuncClienteServicio(){
		String consulta = "select h.horas from horasfunc h, clientes c, servicios s where c.idCli = h.idCli and s.idServ = h.idServ and h.idFun = ? and c.nomCli = ? and s.nombre = ? and h.fecha = ?;";
		return consulta;
	}
	
	
	
	public String sumaHorasClienteServicioDia(){
		String consulta = "select sum(horas) from horasfunc where idFun = ? and fecha = ?;";
		return consulta;
	}
	
	public String modificarHoras(){
		String consulta = "UPDATE horasfunc SET horas = ? where idFun = ? and fecha = ? and idCli = ? and idServ = ?;";
		return consulta;
	}
	
	public String sumaHorasCargablesClienteServicioDia(){
		String consulta = "select sum(h.horas) from horasfunc h, clientes c where h.idFun = ? and h.fecha = ? and h.idCli = c.idCli and c.hsCargables = 1;";
		return consulta;
	}
	
	public String existeHoraDia(){
		String consulta = "select idFun from horasfunc where fecha=? and idFun=? and idCli=? and idServ=?;";
		return consulta;
	}
	
	public String listarHorasPorClienteServicio(){
		String consulta = "select concat(f.nomFun, ' ', f.apeFun), sum(h.horas) from horasfunc h, funcionarios f where h.idFun = f.idFun and h.idcli=? and h.idServ=? and STR_TO_DATE(h.fecha, '%e/%c/%Y') between STR_TO_DATE(?, '%e/%c/%Y') and STR_TO_DATE(?, '%e/%c/%Y') group by h.idFun order by f.nomFun;";
		return consulta;
	}
	
	public String actualizarPermisos(){
		String consulta = "UPDATE permisos set perfAcceso = ?, abmFunc = ?, abmCli = ?, abmServ = ?, ingresoHoras = ?, controlFunc = ?, controlCli = ? where idGrupo = ?;";
		return consulta;
	}
	
	public String obtenerPermisos(){		
			String consulta = "select * from permisos where idGrupo = ?;";
			return consulta;
	}
	
	
	public String esAdminFuncionario(){
		String consulta = "select admin from funcionarios where idFun=?;";
		return consulta;
	}
	
	public String modificarContrasenia(){
		String consulta = "update funcionarios set pass = ? where idFun=?;";
		return consulta;
	}
	
	public String listarDetalleHoras(){
		String consulta="select concat(f.nomFun, ' ', f.apeFun), s.nombre ,sum(h.horas) from horasfunc h, funcionarios f, servicios s where  h.idFun = f.idFun and s.idServ = h.idServ and h.idCli=? and STR_TO_DATE(h.fecha, '%e/%c/%Y') between STR_TO_DATE(?, '%e/%c/%Y') and STR_TO_DATE(?, '%e/%c/%Y') group by h.idFun, h.idServ order by f.nomFun, f.apeFun;"; 
				//"select concat(f.nomFun, ' ', f.apeFun), s.nombre, sum(h.horas) from horasfunc h, funcionarios f, servicios s where h.idCli = ? and h.idFun = f.idFun and	h.idServ = s.idServ and	STR_TO_DATE(h.fecha, '%e/%c/%Y') between STR_TO_DATE(?, '%e/%c/%Y') and STR_TO_DATE(?, '%e/%c/%Y') group by s.nombre order by f.nomFun, f.apeFun;";
		return consulta;			
	}
	
	public String obtenerHonorariosCliente(){
		String consulta = "select honorarios from clientes where idCli=?;";
		return consulta;
	}
	
	public String obtenerMonedaCliente(){
		String consulta = "select moneda from clientes where idCli=?;";
		return consulta;
	}
	
	public String listarHorasMensualesCliente(){
		String consulta = "select c.nomCli, s.nombre, sum(h.horas) from horasfunc h, clientes c, servicios s where h.idCli = c.idCli and h.idServ = s.idServ and h.idFun = ? and STR_TO_DATE(h.fecha, '%e/%c/%Y') between STR_TO_DATE(?, '%e/%c/%Y') and STR_TO_DATE(?, '%e/%c/%Y') group by h.idCli;";
		return consulta;
	}
	
	public String nuevoGrupo(){
		String consulta = "INSERT INTO grupos (nombre, perfAcceso, abmFunc, abmCli, abmServ, ingresoHoras,controlFunc,controlCLi) VALUES (?,?,?,?,?,?,?,?);";
		return consulta;
	}
	
	public String existeGrupo(){
		/* crea el texto de la consulta que retorna true si existe el grupo en la tabla gruposusr */
			String consulta = "select idGrupo from grupos where nombre = ?;";
			return consulta;
		}
	
	public String listarGrupNom(){
		/* crea el texto de la consulta que obtiene un listado de todos 
		 * los nombres de los grupos
		 */
			String consulta = "select nombre from grupos;";
			return consulta;
		}
	
	public String eliminarGrupo(){
		/* crea el texto de la consulta que elimina un grupo */
		String consulta = "delete from grupos where nombre = ?;";
		return consulta;
	}
		
	public String obtenerPermisosGrupo(){		
		String consulta = "select * from grupos where nombre = ?;";
		return consulta;
	}
	
	public String actualizarPermisosGrupo(){
		String consulta = "UPDATE grupos set perfAcceso = ?, abmFunc = ?, abmCli = ?, abmServ = ?, ingresoHoras = ?, controlFunc = ?, controlCli = ? where nombre = ?;";
		return consulta;
	}
	
	public String obtenerIdGrupo(){		
		String consulta = "select idGrupo from grupos where nombre = ?;";
		return consulta;
	}
	
	
	public String obtenerNombreIdGrupo(){
		String consulta = "select nombre from grupos where idGrupo = ?;";
		return consulta;
	}
	
	public String obtenerIdCliente(){
		String consulta = "select idCli from clientes where nomCli= ?;";
		return consulta;
	}
	
	public String borrarSeleccionHorasMensuales(int mes, int anio){
		//String consulta = "delete from horasfunc where idFun=? and idCli=? and idServ=? and fecha like '%/10/2013';";
		String consulta = "delete from horasfunc where idFun=? and idCli=? and idServ=? and fecha like '%/" +mes +"/" + anio +"'";
		return consulta;
	}
	
	public String cerrarMes(){
		String consulta="insert into cierre_mes (mes, anio) values(?,?);";
		return consulta;
	}
	
	public String estaCerradoMes(){
		String consulta="select * from cierre_mes where anio = ? and mes = ?;";
		return consulta;
	}
	
	public String abridMes(){
		String consulta="delete from cierre_mes where anio = ? and mes = ?;";
		return consulta;
	}
	
	public String eliminarVisita(){
		String consulta="delete from horasfunc where idfun = ? and idcli = ? and idServ = ? and fecha = ?;";
		return consulta;
	}
	
	public String existeVisita(){
		String consulta="select * from horasfunc where idfun = ? and idcli = ? and idServ = ? and fecha = ?;";
		return consulta;
	}
	
	public String listarPersonasFisicas(){
		String consulta = "select nomCli from clientes where tipoPersona = 1 order by nomCli;";
		return consulta;
	}
	
	public String listarPersonasJuridicas(){
		String consulta = "select nomCli from clientes where tipoPersona = 2 order by nomCli;";
		return consulta;
	}
	
	public String agregarPersonaFisica(){
		String consulta="insert into perfisica (idcli,rxa,eau,email,tipodoccli,numdoccli,paisemision,fechanaccli,lugarnac,nacionalidad,estcivil,profcli,paisdesact,nomcony,tipodoccoy,numdoccony,negoactlav,propinatrelcom,orijfontransac,bancos,servfirma,tardosanpep,refprofs,refnufirm) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		return consulta;	
	}
	
	public String agregarPersonaJuridica(){		
		String consulta="insert into perjuridica (idcli, rxa,eau, fecha, lugconst, domicilio, sede, tel, pagweb, activprincip, volingresos, paisdondedesact, origenfondos, paisenquedesact, accionistas, propinatrelcom, refbanc, refprof, servfirma, refpornuestrfirm) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		return consulta;	
	}
	
	public String existePersonaFisica(){
		/* crea el texto de la consulta que retorna true si existe la persona fisica */
			String consulta = "select * from perfisica where idcli=?;";
			return consulta;
		}
	
	public String existePersonaJuridica(){
		/* crea el texto de la consulta que retorna true si existe la persona juridica */
			String consulta = "select * from perjuridica where idcli=?;";
			return consulta;
		}
	
	public String obtenerPersonaFisica(){
		/* crea el texto de la consulta que retorna un VOCliente a partir de su nombre */
			String consulta = "select * from perfisica where idcli=?;";
			return consulta;
	}
	
	public String obtenerPersonaJuridica(){
		/* crea el texto de la consulta que retorna un VOCliente a partir de su nombre */
			String consulta = "select * from perjuridica where idcli=?;";
			return consulta;
	}
	
	public String modificarPersonaFisica(){
		String consulta = "UPDATE perfisica SET idcli = ?, rxa = ?, eau = ?, email = ?, tipodoccli = ?, numdoccli = ?, paisemision = ?, fechanaccli = ?, lugarnac = ?, nacionalidad = ?, estcivil = ?, profcli = ?, paisdesact = ?, nomcony = ?, tipodoccoy = ?, numdoccony = ?, negoactlav = ?, propinatrelcom = ?, orijfontransac = ?, bancos = ?, servfirma = ?, tardosanpep = ?, refprofs = ?, refnufirm = ? WHERE idCli = ?;";
		return consulta;
	}
	
	public String modificarPersonaJuridica(){
		String consulta = "UPDATE perjuridica SET idcli = ?, rxa = ?,eau = ?, fecha = ?, lugconst = ?, domicilio = ?, sede = ?, tel = ?, pagweb = ?, activprincip = ?, volingresos = ?, paisdondedesact = ?, origenfondos = ?, paisenquedesact = ?, accionistas = ?, propinatrelcom = ?, refbanc = ?, refprof = ?, servfirma = ?, refpornuestrfirm = ?  WHERE idCli = ?;";
		return consulta;
	}
	
}
