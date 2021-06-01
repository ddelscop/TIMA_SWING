package logicaPersistencia.valueObjects;

public class VOPermisos {
	private int perfAcceso;
	private int abmFunc;
	private int abmCli;
	private int abmServ;
	private int ingresoHoras;
	private int controlFunc;
	private int idGrupo;
	private int controlCli;
	
	private String nombre;
	
	
	//idGrupo, nombre, perfAcceso, abmFunc, abmCli, abmServ, ingresoHoras, controlFunc, controlCli 
	
	public VOPermisos(int perfAcceso, int abmFunc, int abmCli, int abmServ,
			int ingresoHoras, int controlFunc, int controlCli) {
		super();
		this.perfAcceso = perfAcceso;
		this.abmFunc = abmFunc;
		this.abmCli = abmCli;
		this.abmServ = abmServ;
		this.ingresoHoras = ingresoHoras;
		this.controlFunc = controlFunc;
		this.controlCli = controlCli;
	}
	
	
	
	

	public VOPermisos(String nombre, int perfAcceso, int abmFunc, int abmCli, int abmServ, int ingresoHoras, int controlFunc, int controlCli) {
		super();
		this.perfAcceso = perfAcceso;
		this.abmFunc = abmFunc;
		this.abmCli = abmCli;
		this.abmServ = abmServ;
		this.ingresoHoras = ingresoHoras;
		this.controlFunc = controlFunc;
		this.controlCli = controlCli;
		this.nombre = nombre;
	}






	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getPerfAcceso() {
		return perfAcceso;
	}

	public int getAbmFunc() {
		return abmFunc;
	}

	public int getAbmCli() {
		return abmCli;
	}

	public int getAbmServ() {
		return abmServ;
	}

	public int getIngresoHoras() {
		return ingresoHoras;
	}

	public int getControlFunc() {
		return controlFunc;
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public int getControlCli() {
		return controlCli;
	}

	public void setControlCli(int controlCli) {
		this.controlCli = controlCli;
	}

	
	
}
