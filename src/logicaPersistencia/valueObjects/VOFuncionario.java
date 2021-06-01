package logicaPersistencia.valueObjects;

public class VOFuncionario {
	private int idFun;
	private String nomFun;
	private String apeFun;
	private String ciFun;
	private String fechNacFun;
	private String celFun;
	private int baja;
	private int horasDia;
	private int idGrupo;
	private String pass;
	
	public VOFuncionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VOFuncionario(int idFun, String nomFun, String apeFun, String ciFun,
			String fechNacFun, String celFun, int baja, int hDia, int idGrupo, String pass) {
		super();
		this.idFun = idFun;
		this.nomFun = nomFun;
		this.apeFun = apeFun;
		this.ciFun = ciFun;
		this.fechNacFun = fechNacFun;
		this.celFun = celFun;
		this.baja = baja;
		this.horasDia = hDia;
		this.idGrupo = idGrupo;
		this.pass = pass;
	}

	public int getIdFun() {
		return idFun;
	}

	public String getNomFun() {
		return nomFun;
	}

	public String getApeFun() {
		return apeFun;
	}

	public String getCiFun() {
		return ciFun;
	}

	public String getFechNacFun() {
		return fechNacFun;
	}

	public String getCelFun() {
		return celFun;
	}

	public int getBaja() {
		return baja;
	}

	public int getHorasDia() {
		return horasDia;
	}

	public void setHorasDia(int horasDia) {
		this.horasDia = horasDia;
	}

	public int getidGrupo() {
		return idGrupo;
	}

	public void setidGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
	
	
	
}
