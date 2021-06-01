package grafica.ventanas;

import grafica.controladores.ControladorAltaClientes;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import logicaPersistencia.excepciones.VentanaException;
import logicaPersistencia.valueObjects.VOCliente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AltaClientes extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldRUT;
	private JTextField textFieldContacto;
	private JTextField textFieldNroCliente;
	private JTextField textFieldTEL;
	private JTextField textFieldDireccion;
	private JComboBox comboBoxDeptos;
	private JTextField textField;
	private JLabel label_nombre, label_rut, label_tel, label_contacto, label_nroCli, label_direccion;
	private JList listClientes;
	private JRadioButton rdbtnHorasCargables;
	private JTextField textFieldHonorarios;
	private JComboBox comboBoxMoneda;
	private JTextField textFieldnombreNuevo;
	private JRadioButton rdbtnJuridica;
	private JRadioButton rdbtnNoAsignado;
	private JRadioButton rdbtnFisica; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AltaClientes frame = new AltaClientes();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AltaClientes() {
		setTitle("Clientes");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 774, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 266, 366);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 28, 61, 14);
		panel.add(lblNombre);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {			
			StringBuffer txt = new StringBuffer();
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (caracter == KeyEvent.VK_BACK_SPACE){
					e.consume();	
					if (txt.length() >=1){
						txt.setLength(txt.length()-1);
						filtrarTuplasClientes(clientesToString(),txt.toString());
					}
				}else{
					txt.append(caracter);					
					filtrarTuplasClientes(clientesToString(),txt.toString());
				}
				
				
				
				
			}
		});
		textField.setBounds(62, 25, 188, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 53, 230, 302);
		panel.add(scrollPane);
		
		listClientes = new JList(this.clientesToString());
		listClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String nomClie = (String) listClientes.getSelectedValue();
				textFieldNombre.setText(nomClie);
				ControladorAltaClientes controlador = new ControladorAltaClientes();
				VOCliente voCliente = controlador.obtenerVOClienteNombre(nomClie);
				textFieldContacto.setText(voCliente.getContacto());
				textFieldRUT.setText(voCliente.getRut());
				textFieldNroCliente.setText(voCliente.getNroCli());
				textFieldTEL.setText(voCliente.getTel());
				textFieldDireccion.setText(voCliente.getDireccion());

				rdbtnNoAsignado.setSelected(false);
				rdbtnFisica.setSelected(false);
				rdbtnJuridica.setSelected(false);
				switch (voCliente.getTipoPersona()){
					case 0:rdbtnNoAsignado.setSelected(true);
						break;
					case 1:rdbtnFisica.setSelected(true);
						break;
					case 2:rdbtnJuridica.setSelected(true);
						break;
				}
				
				comboBoxMoneda.removeAllItems();
				if (voCliente.getMoneda() == 0){
					//comboBoxMoneda.removeAll();
					comboBoxMoneda.addItem("PESOS");
					comboBoxMoneda.addItem("DÓLARES");
				}else{
					//comboBoxMoneda.removeAll();
					comboBoxMoneda.addItem("DÓLARES");
					comboBoxMoneda.addItem("PESOS");					
				}

				textFieldHonorarios.setText("" + voCliente.getHonorarios());
				
				if (voCliente.getHsCargables() == 1)
					rdbtnHorasCargables.setSelected(true);
				else
					rdbtnHorasCargables.setSelected(false);
				
				try {
					String depto = controlador.obtenerNombreDepto(voCliente.getIdDepto());
					comboBoxDeptos.removeAllItems();
					
					String [] deptos =  new String [19];
					deptos[0] = "MONTEVIDEO";
					deptos[1] = "ARTIGAS";
					deptos[2] = "SALTO";
					deptos[3] = "PAYSANDU";
					deptos[4] = "CERRO LARGO";
					deptos[5] = "DURAZNO";
					deptos[6] = "FLORIDA";
					deptos[7] = "MALDONADO";
					deptos[8] = "RIVERA";
					deptos[9] = "SORIANO";
					deptos[10] = "TREINTA Y TRES";
					deptos[11] = "CANELONES";
					deptos[12] = "COLONIA";
					deptos[13] = "FLORES";
					deptos[14] = "LAVALLEJA";
					deptos[15] = "ROCHA";
					deptos[16] = "SAN JOSE";
					deptos[17] = "TACUAREMBO";
					deptos[18] = "RIO NEGRO";
					
					comboBoxDeptos.addItem(depto);
					for (int i=0; i<=18;i++){
						if (!deptos[i].equals(depto)){
							comboBoxDeptos.addItem(deptos[i]);
						}
					}					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		scrollPane.setViewportView(listClientes);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Alta/Modificaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(284, 11, 463, 366);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 21, 46, 14);
		panel_1.add(lblNewLabel);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 36, 192, 20);
		panel_1.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldContacto = new JTextField();
		textFieldContacto.setBounds(249, 82, 180, 20);
		panel_1.add(textFieldContacto);
		textFieldContacto.setColumns(10);
		
		JLabel lblContacto = new JLabel("Contacto");
		lblContacto.setBounds(249, 67, 77, 14);
		panel_1.add(lblContacto);
		
		JLabel lblRut = new JLabel("RUT");
		lblRut.setBounds(10, 67, 46, 14);
		panel_1.add(lblRut);
		
		textFieldRUT = new JTextField();
		textFieldRUT.setBounds(10, 82, 192, 20);
		panel_1.add(textFieldRUT);
		textFieldRUT.setColumns(10);
		
		JLabel lblNroCliente = new JLabel("Nro. Cliente");
		lblNroCliente.setBounds(249, 113, 77, 14);
		panel_1.add(lblNroCliente);
		
		textFieldNroCliente = new JTextField();
		textFieldNroCliente.setBounds(249, 128, 180, 20);
		panel_1.add(textFieldNroCliente);
		textFieldNroCliente.setColumns(10);
		
		JLabel lblTelcel = new JLabel("Tel/Cel");
		lblTelcel.setBounds(10, 113, 77, 14);
		panel_1.add(lblTelcel);
		
		textFieldTEL = new JTextField();
		textFieldTEL.setBounds(10, 131, 192, 20);
		panel_1.add(textFieldTEL);
		textFieldTEL.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(249, 159, 77, 14);
		panel_1.add(lblDireccin);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(249, 177, 180, 20);
		panel_1.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(10, 271, 109, 14);
		panel_1.add(lblDepartamento);
		
		comboBoxDeptos = new JComboBox();
		comboBoxDeptos.setBounds(10, 286, 122, 20);
		panel_1.add(comboBoxDeptos);
		
		comboBoxDeptos.addItem("MONTEVIDEO");
		comboBoxDeptos.addItem("ARTIGAS");
		comboBoxDeptos.addItem("SALTO");
		comboBoxDeptos.addItem("PAYSANDU");
		comboBoxDeptos.addItem("CERRO LARGO");
		comboBoxDeptos.addItem("DURAZNO");
		comboBoxDeptos.addItem("FLORIDA");
		comboBoxDeptos.addItem("MALDONADO");
		comboBoxDeptos.addItem("RIVERA");
		comboBoxDeptos.addItem("SORIANO");
		comboBoxDeptos.addItem("TREINTA Y TRES");
		comboBoxDeptos.addItem("CANELONES");
		comboBoxDeptos.addItem("COLONIA");
		comboBoxDeptos.addItem("FLORES");
		comboBoxDeptos.addItem("LAVALLEJA");
		comboBoxDeptos.addItem("ROCHA");
		comboBoxDeptos.addItem("SAN JOSE");
		comboBoxDeptos.addItem("TACUAREMBO");
		comboBoxDeptos.addItem("RIO NEGRO");
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorAltaClientes controlador = new ControladorAltaClientes();
				String nomCli = textFieldNombre.getText();
				String contacto = textFieldContacto.getText();
				String rut = textFieldRUT.getText();
				String nroCli = textFieldNroCliente.getText();
				String tel = textFieldTEL.getText();
				String direccion = textFieldDireccion.getText();
				String depto = (String) comboBoxDeptos.getSelectedItem();
				try {
					int idDepto = controlador.obtenerIdDepto(depto);		
					boolean error = false;
					
					if (nomCli.isEmpty()){
						label_nombre.setVisible(true);
						label_nombre.setToolTipText("No ha escrito el nombre del Cliente.");
						error = true;
					}else{
						label_nombre.setVisible(false);
						if (controlador.existeNombreCliente(nomCli)){
							label_nombre.setVisible(true);
							label_nombre.setToolTipText("Ya existe un cliente con ese nombre.");
							error = true;
						}else
							label_nombre.setVisible(false);
					}
					
					if (contacto.isEmpty()){
						label_contacto.setVisible(true);
						label_contacto.setToolTipText("No ha escrito el nombre del contacto.");
						error = true;
					}
					
					if (rut.isEmpty() && rdbtnJuridica.isSelected()){
						label_rut.setVisible(true);
						label_rut.setToolTipText("No ha escrito el RUT del cliente.");
						error = true;
					}
					
					if (nroCli.isEmpty()){
						label_nroCli.setVisible(true);
						label_nroCli.setToolTipText("No ha escrito el número de cliente.");
						error = true;
					}
					
					
					if (tel.isEmpty()){
						label_tel.setVisible(true);
						label_tel.setToolTipText("No ha escrito el número de tel del cliente.");
						error = true;
					}
					
					if (direccion.isEmpty()){
						label_direccion.setVisible(true);
						label_direccion.setToolTipText("No ha escrito la direccion del cliente.");
						error = true;
					}
					
					if (!error){
						int hsCarcables = 0;
						if (rdbtnHorasCargables.isSelected())
							hsCarcables = 1;
						int honorarios = Integer.parseInt(textFieldHonorarios.getText());
						int moneda =1; // dolares
						if (comboBoxMoneda.getSelectedItem() == "PESOS")
							moneda =0; // pesos
						
						int tipoPersona=0;						
						if (rdbtnJuridica.isSelected())
							tipoPersona=1;
						if (rdbtnFisica.isSelected())
							tipoPersona=2;
						
						controlador.nuevoCliente(contacto, rut, nroCli, tel, direccion, idDepto, nomCli,hsCarcables, honorarios, moneda,tipoPersona);
						JOptionPane.showMessageDialog(getContentPane(), VentanaException.INGRESO_EXITOSO, "Ingreso exitoso", JOptionPane.PLAIN_MESSAGE);
						listarClientes();
					}
					
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnAlta.setBounds(10, 331, 89, 23);
		panel_1.add(btnAlta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomCli = textFieldNombre.getText();
				String contacto = textFieldContacto.getText();
				String rut = textFieldRUT.getText();
				String nroCli = textFieldNroCliente.getText();
				String tel = textFieldTEL.getText();
				String direccion = textFieldDireccion.getText();
				String depto = (String) comboBoxDeptos.getSelectedItem();
				ControladorAltaClientes controlador = new ControladorAltaClientes();
				int idCli = (controlador.obtenerVOClienteNombre(nomCli)).getIdCli();
				
				int hsCargables=0;
				if (rdbtnHorasCargables.isSelected())
					hsCargables=1;
				
				try {
					if (nomCli.isEmpty() || contacto.isEmpty() || (rut.isEmpty() && rdbtnJuridica.isSelected()) || nroCli.isEmpty() || tel.isEmpty() || direccion.isEmpty() ){
						JOptionPane.showMessageDialog(getContentPane(),VentanaException.CAMPOS_VACIOS, "Error en los valores", JOptionPane.ERROR_MESSAGE);						
					}else{
						int idDepto = controlador.obtenerIdDepto(depto);
						int honorarios = Integer.parseInt(textFieldHonorarios.getText());
						int moneda = 1;
						if (comboBoxMoneda.getSelectedItem() == "PESOS")	
							moneda =0;						
						if (!textFieldnombreNuevo.getText().isEmpty()){
							nomCli = textFieldnombreNuevo.getText();
							textFieldNombre.setText(textFieldnombreNuevo.getText());
						}
						
						int tipoPersona=0;
						if (rdbtnFisica.isSelected())
							tipoPersona=1;
						if (rdbtnJuridica.isSelected())
							tipoPersona=2;						
						
						controlador.modificarCliente(contacto, rut, nroCli, tel, direccion, idDepto, nomCli, idCli, hsCargables, honorarios, moneda, tipoPersona);
						textFieldnombreNuevo.setText("");
						JOptionPane.showMessageDialog(getContentPane(), VentanaException.MODIFICACION_EXITOSA, "Modificacion exitosa", JOptionPane.PLAIN_MESSAGE);
						listarClientes();
						
						switch (tipoPersona){
						case 0:rdbtnNoAsignado.setSelected(true);
							break;
						case 1:rdbtnFisica.setSelected(true);
							break;
						case 2:rdbtnJuridica.setSelected(true);
							break;
					}
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnModificar.setBounds(109, 331, 93, 23);
		panel_1.add(btnModificar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AltaClientes.this.dispose();
			}
		});
		btnSalir.setBounds(349, 331, 89, 23);
		panel_1.add(btnSalir);
		
				
		label_nombre = new JLabel("");
		label_nombre.setIcon(new ImageIcon(AltaClientes.class.getResource("/grafica/imagenes/Error.png")));
		label_nombre.setBounds(206, 36, 20, 20);
		panel_1.add(label_nombre);
		label_nombre.setVisible(false);
		
		
		label_rut = new JLabel("");
		label_rut.setIcon(new ImageIcon(AltaClientes.class.getResource("/grafica/imagenes/Error.png")));
		label_rut.setBounds(206, 82, 20, 20);
		panel_1.add(label_rut);
		label_rut.setVisible(false);
		
		label_tel = new JLabel("");
		label_tel.setIcon(new ImageIcon(AltaClientes.class.getResource("/grafica/imagenes/Error.png")));
		label_tel.setBounds(206, 131, 20, 20);
		panel_1.add(label_tel);
		label_tel.setVisible(false);
		
		label_contacto = new JLabel("");
		label_contacto.setIcon(new ImageIcon(AltaClientes.class.getResource("/grafica/imagenes/Error.png")));
		label_contacto.setBounds(433, 82, 20, 20);
		panel_1.add(label_contacto);
		label_contacto.setVisible(false);
		
		label_nroCli = new JLabel("");
		label_nroCli.setIcon(new ImageIcon(AltaClientes.class.getResource("/grafica/imagenes/Error.png")));
		label_nroCli.setBounds(433, 128, 20, 20);
		panel_1.add(label_nroCli);
		label_nroCli.setVisible(false);
		
		label_direccion = new JLabel("");
		label_direccion.setIcon(new ImageIcon(AltaClientes.class.getResource("/grafica/imagenes/Error.png")));
		label_direccion.setBounds(433, 177, 20, 20);
		panel_1.add(label_direccion);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textFieldNombre.setText("");
				textFieldContacto.setText("");
				textFieldRUT.setText("");
				textFieldNroCliente.setText("");
				textFieldTEL.setText("");
				textFieldDireccion.setText("");
				rdbtnNoAsignado.setSelected(true);
				rdbtnFisica.setSelected(false);
				rdbtnJuridica.setSelected(false);
				
				
				
				
				
			}
		});
		btnReset.setBounds(212, 331, 89, 23);
		panel_1.add(btnReset);
		
		rdbtnHorasCargables = new JRadioButton("Horas Cargables");
		rdbtnHorasCargables.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnHorasCargables.setBounds(153, 285, 179, 23);
		panel_1.add(rdbtnHorasCargables);
		
		JLabel lblHonorariosMensuales = new JLabel("Honorarios mensuales");
		lblHonorariosMensuales.setBounds(10, 163, 136, 14);
		panel_1.add(lblHonorariosMensuales);
		
		textFieldHonorarios = new JTextField();
		textFieldHonorarios.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldHonorarios.setColumns(10);
		textFieldHonorarios.setBounds(10, 181, 89, 20);
		panel_1.add(textFieldHonorarios);
		
		comboBoxMoneda = new JComboBox();
		comboBoxMoneda.setBounds(109, 181, 95, 20);
		panel_1.add(comboBoxMoneda);
		label_direccion.setVisible(false);
		
		comboBoxMoneda.addItem("   PESOS");
		comboBoxMoneda.addItem("   DÓLARES");
		
		textFieldnombreNuevo = new JTextField();
		textFieldnombreNuevo.setColumns(10);
		textFieldnombreNuevo.setBounds(249, 36, 180, 20);
		panel_1.add(textFieldnombreNuevo);
		
		JLabel lblCambiarNombrePor = new JLabel("Cambiar nombre por");
		lblCambiarNombrePor.setBounds(249, 21, 180, 14);
		panel_1.add(lblCambiarNombrePor);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Tipo de Persona", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 212, 421, 52);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		rdbtnFisica = new JRadioButton("F\u00EDsica");
		rdbtnFisica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnFisica.setSelected(true);
				rdbtnJuridica.setSelected(false);
				rdbtnNoAsignado.setSelected(false);
			}
		});
		rdbtnFisica.setBounds(6, 24, 109, 23);
		panel_2.add(rdbtnFisica);
		
		rdbtnJuridica = new JRadioButton("Jur\u00EDdica");
		rdbtnJuridica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnFisica.setSelected(false);
				rdbtnJuridica.setSelected(true);
				rdbtnNoAsignado.setSelected(false);
			}
		});
		rdbtnJuridica.setBounds(147, 24, 109, 23);
		panel_2.add(rdbtnJuridica);
		
		rdbtnNoAsignado = new JRadioButton("No asignado");
		rdbtnNoAsignado.setSelected(true);
		
		rdbtnNoAsignado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnFisica.setSelected(false);
				rdbtnJuridica.setSelected(false);
				rdbtnNoAsignado.setSelected(true);
			}
		});
		rdbtnNoAsignado.setBounds(306, 24, 109, 23);
		panel_2.add(rdbtnNoAsignado);
	}
	
	
	public String[] clientesToString() {
		// Retorna un array de clientess en el cual cada tupla esta formada por el nombre
		// Este array luego se le pasa al jlist de clientes
		ControladorAltaClientes controlador = new ControladorAltaClientes();		
		
			List<String> clientes = controlador.listarClientes();
			String[] tuplas = new String[clientes.size()];
			int i = 0;
			for (String pac : clientes){
				tuplas[i] = pac;
				i++;
			}		
			return tuplas;
		}
	
	public void filtrarTuplasClientes(String [] tuplasClientes, String subCadena){
		// Dado un array de tuplas de clientes y una subcadena, va filtrando los nombres del jList
			boolean hayQueFiltrar = false;
			DefaultListModel modeloClientes = new DefaultListModel();
			for (int i=0; i<tuplasClientes.length;i++){			
				if (tuplasClientes[i].toUpperCase().startsWith(subCadena.toUpperCase())){
					hayQueFiltrar = true;			
					modeloClientes.addElement(tuplasClientes[i]);
				}
			}
			if (hayQueFiltrar){
				listClientes.setModel(modeloClientes);			
			}		
		}
	
	private void listarClientes(){
		rdbtnJuridica.setSelected(false);;
		rdbtnNoAsignado.setSelected(false);;;
		rdbtnFisica.setSelected(false);;; 
		DefaultListModel modeloServicios = new DefaultListModel();
		ControladorAltaClientes controlador = new ControladorAltaClientes();
		List<String> lstClientes = controlador.listarClientes();
		if (lstClientes.size() > 0){
			Iterator<String> iterClientes = lstClientes.iterator();
			while(iterClientes.hasNext()){				
				String cliente = iterClientes.next();
				modeloServicios.addElement(cliente);
			}
						
		}
		listClientes.setModel(modeloServicios);
	}
}

