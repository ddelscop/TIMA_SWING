package logicaPersistencia.excepciones;

public class VentanaException extends Exception{
	static final public String INGRESO_EXITOSO = "Datos ingresados con exito";
	static final public String MODIFICACION_EXITOSA = "Datos modificados con exito";	
	static final public String EXISTE_SERVICIO = "Ya existe un servicio con ese nombre";
	static final public String NO_EXISTE_SERVICIO = "No existe un servicio con ese nombre";
	static final public String CAMPOS_VACIOS = "Uno o más campos están en blanco.";
	static final public String EXISTE_CEDULA = "Ya existe un funcionario con esa cédula..";
	static final public String PERMISOS_USUARIO = "Seleccione permisos de usuario.";
	static final public String SELECCIONAR_FUNCIONARIO = "Seleccione un funcionario activo.";
	static final public String LOGIN_INCORRECTO = "El nombre de usuario y/o contrasenia no son validos.";
	static final public String HORA_REPETIDA = "Ya se registró una hora, para el cliente y servicio en ese día.";
	static final public String NO_HORA = "No se puede modificar una hora que no ha sido registrada.";
	static final public String SA_DO = "No se pueden registrar horas los Sábados y/o Domingos.";
	static final public String EXISTE_GRUPO = "Ya existe un grupo con ese nombre.";
	static final public String MES_CERRADO = "El mes ha sido cerrado y no se pueden ingresar horas";
	static final public String HORAS_VACIO = "El campo Horas no puede ser vacío, ingrese un valor";
	static final public String BORRAR_VISITA = "No se puede eliminar la visita, revise los datos";
	static final public String EXISTE_PERSONA_FISICA = "Ya existe una persona con estos datos ..";
	static final public String EXISTE_PERSONA_JURIDICA = "Ya existe una persona con estos datos ..";
	static final public String FECHA_OBLIGATORIA = "El campo fecha es obligatorio";
	
	
	
	
	
	public VentanaException(String _msg) {
		super(_msg);
	}
}
