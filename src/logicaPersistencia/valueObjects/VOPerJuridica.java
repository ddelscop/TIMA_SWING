package logicaPersistencia.valueObjects;

public class VOPerJuridica {
  int idcli;
  int rxa;
  int eau;
  String dominsoc;
  String fecha;
  String lugconst;
  String domicilio;
  String sede;
  String tel;
  String pagweb;
  String activprincip;
  String volingresos;
  String paisdondedesact;
  String origenfondos;
  String paisenquedesact;
  String accionistas;
  String propinatrelcom;
  String refbanc;
  String refprof;
  String servfirma;
  String refpornuestrfirm;
  
   
	public VOPerJuridica(int idcli, int rxa, int eau, String fecha, String lugconst, String domicilio, String sede,
		String tel, String pagweb, String activprincip, String volingresos, String paisdondedesact, String origenfondos,
		String paisenquedesact, String accionistas, String propinatrelcom, String refbanc, String refprof,
		String servfirma, String refpornuestrfirm) {
	super();
	this.idcli = idcli;
	this.rxa = rxa;
	this.eau = eau;
	this.fecha = fecha;
	this.lugconst = lugconst;
	this.domicilio = domicilio;
	this.sede = sede;
	this.tel = tel;
	this.pagweb = pagweb;
	this.activprincip = activprincip;
	this.volingresos = volingresos;
	this.paisdondedesact = paisdondedesact;
	this.origenfondos = origenfondos;
	this.paisenquedesact = paisenquedesact;
	this.accionistas = accionistas;
	this.propinatrelcom = propinatrelcom;
	this.refbanc = refbanc;
	this.refprof = refprof;
	this.servfirma = servfirma;
	this.refpornuestrfirm = refpornuestrfirm;
}

	public VOPerJuridica(int idcli, int rxa, int eau, String dominsoc, String fecha, String lugconst, String domicilio,
		String sede, String tel, String pagweb, String activprincip, String volingresos, String paisdondedesact,
		String origenfondos, String paisenquedesact, String accionistas, String propinatrelcom, String refbanc,
		String refprof, String servfirma, String refpornuestrfirm) {
	super();
	this.idcli = idcli;
	this.rxa = rxa;
	this.eau = eau;
	this.dominsoc = dominsoc;
	this.fecha = fecha;
	this.lugconst = lugconst;
	this.domicilio = domicilio;
	this.sede = sede;
	this.tel = tel;
	this.pagweb = pagweb;
	this.activprincip = activprincip;
	this.volingresos = volingresos;
	this.paisdondedesact = paisdondedesact;
	this.origenfondos = origenfondos;
	this.paisenquedesact = paisenquedesact;
	this.accionistas = accionistas;
	this.propinatrelcom = propinatrelcom;
	this.refbanc = refbanc;
	this.refprof = refprof;
	this.servfirma = servfirma;
	this.refpornuestrfirm = refpornuestrfirm;
}

	public int getIdcli() {
		return idcli;
	}

	public void setIdcli(int idcli) {
		this.idcli = idcli;
	}

	public int getRxa() {
		return rxa;
	}

	public void setRxa(int rxa) {
		this.rxa = rxa;
	}

	public int getEau() {
		return eau;
	}

	public void setEau(int eau) {
		this.eau = eau;
	}

	public String getDominsoc() {
		return dominsoc;
	}

	public void setDominsoc(String dominsoc) {
		this.dominsoc = dominsoc;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLugconst() {
		return lugconst;
	}

	public void setLugconst(String lugconst) {
		this.lugconst = lugconst;
	}

	
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPagweb() {
		return pagweb;
	}

	public void setPagweb(String pagweb) {
		this.pagweb = pagweb;
	}

	public String getActivprincip() {
		return activprincip;
	}

	public void setActivprincip(String activprincip) {
		this.activprincip = activprincip;
	}

	public String getVolingresos() {
		return volingresos;
	}

	public void setVolingresos(String volingresos) {
		this.volingresos = volingresos;
	}

	public String getPaisdondedesact() {
		return paisdondedesact;
	}

	public void setPaisdondedesact(String paisdondedesact) {
		this.paisdondedesact = paisdondedesact;
	}

	public String getOrigenfondos() {
		return origenfondos;
	}

	public void setOrigenfondos(String origenfondos) {
		this.origenfondos = origenfondos;
	}

	public String getPaisenquedesact() {
		return paisenquedesact;
	}

	public void setPaisenquedesact(String paisenquedesact) {
		this.paisenquedesact = paisenquedesact;
	}

	public String getAccionistas() {
		return accionistas;
	}

	public void setAccionistas(String accionistas) {
		this.accionistas = accionistas;
	}

	public String getPropinatrelcom() {
		return propinatrelcom;
	}

	public void setPropinatrelcom(String propinatrelcom) {
		this.propinatrelcom = propinatrelcom;
	}

	public String getRefbanc() {
		return refbanc;
	}

	public void setRefbanc(String refbanc) {
		this.refbanc = refbanc;
	}

	public String getRefprof() {
		return refprof;
	}

	public void setRefprof(String refprof) {
		this.refprof = refprof;
	}

	public String getServfirma() {
		return servfirma;
	}

	public void setServfirma(String servfirma) {
		this.servfirma = servfirma;
	}

	public String getRefpornuestrfirm() {
		return refpornuestrfirm;
	}

	public void setRefpornuestrfirm(String refpornuestrfirm) {
		this.refpornuestrfirm = refpornuestrfirm;
	}
  
    
  
}
