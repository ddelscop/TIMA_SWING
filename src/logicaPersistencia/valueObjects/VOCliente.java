package logicaPersistencia.valueObjects;

public class VOCliente {
	private int idCli;
	private String nombre;
	private String contacto;
	private String rut;
	private String nroCli;
	private String tel;
	private String direccion;
	private int idDepto;
	private int hsCargables;
	private int honorarios;
	private int moneda;
	private int tipoPersona;
	
	
	public VOCliente() {
		super();
		// TODO Auto-generated constructor stub
	}


	public VOCliente(int idCli, String nombre, String contacto, String rut,
			String nroCli, String tel, String direccion, int idDepto, int hsCargables, int honorarios, int moneda, int tipoPersona) {
		super();
		this.idCli = idCli;
		this.nombre = nombre;
		this.contacto = contacto;
		this.rut = rut;
		this.nroCli = nroCli;
		this.tel = tel;
		this.direccion = direccion;
		this.idDepto = idDepto;
		this.hsCargables = hsCargables;
		this.honorarios = honorarios;
		this.moneda = moneda;
		this.tipoPersona = tipoPersona;
	}


	public int getIdCli() {
		return idCli;
	}


	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getContacto() {
		return contacto;
	}


	public void setContacto(String contacto) {
		this.contacto = contacto;
	}


	public String getRut() {
		return rut;
	}


	public void setRut(String rut) {
		this.rut = rut;
	}


	public String getNroCli() {
		return nroCli;
	}


	public void setNroCli(String nroCli) {
		this.nroCli = nroCli;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public int getIdDepto() {
		return idDepto;
	}


	public void setIdDepto(int idDepto) {
		this.idDepto = idDepto;
	}


	public int getHsCargables() {
		return hsCargables;
	}


	public void setHsCargables(int hsCargables) {
		this.hsCargables = hsCargables;
	}


	public int getHonorarios() {
		return honorarios;
	}


	public void setHonorarios(int honorarios) {
		this.honorarios = honorarios;
	}


	public int getMoneda() {
		return moneda;
	}


	public void setMoneda(int moneda) {
		this.moneda = moneda;
	}


	public int getTipoPersona() {
		return tipoPersona;
	}


	public void setTipoPersona(int tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
	
	
	
}
