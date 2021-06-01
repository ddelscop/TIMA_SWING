package grafica.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import grafica.controladores.ControladorAltaClientes;
import grafica.controladores.ControladorCompliance;
import logicaPersistencia.excepciones.VentanaException;
import logicaPersistencia.valueObjects.VOCliente;
import logicaPersistencia.valueObjects.VOPerFisica;
import logicaPersistencia.valueObjects.VOPerJuridica;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Compliance extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFNombre;
	private JTextField textFieldFDireccion;
	private JTextField textFieldFTel;
	private JTextField textFieldFEmail;
	private JTextField textFieldFCI;
	private JTextField textFieldFPais;
	private JTextField textFieldFNombCony;
	private JTextField textFieldFLugNac;
	private JTextField textFieldFNacionalidad;
	private JTextField textFieldFEstCivil;
	private JTextField textFieldFNUmDocCony;
	private JTextField textFieldFProf;
	private JTextField textFieldFDesAct;
	private JTextField text;
	private JTextField textField_15;
	private JTextField textFieldDenomSocial;
	private JTextField textFieldLugarConst;
	private JTextField textFieldNumRut;
	private JTextField textFieldDomic;
	private JTextField textFieldSede;
	private JTextField textFieldTel;
	private JTextField textFieldPagWeb;
	private JTextField textFieldActivPrincip;
	private JTextField textFieldVolIngresos;
	private JTextField textFieldPaisActivid;
	private JTextField textFieldOrigenFondos;
	private JTextField textFieldPaisEnQueDesActividad;
	private JTextField textFieldServRequFirma;
	private JTextField textFieldRefNuestraFirma;
	private JList listPerFisicas;
	private JList listPerJuridicas;
	private JCheckBox chckbxFRXA;
	private JCheckBox chckbxFEAU;
	private JTextArea textAreaFNegocioAct;
	private JTextArea textAreaFRelCom;
	private JTextArea textAreaFOrigFondos;
	private JTextArea textAreaFServReq;
	private JTextArea textAreaFBancos;
	private JTextArea textAreaFTareas;
	private JTextArea textAreaFRefProf;
	private JTextArea textAreaFRefFirma;
	private JDateChooser dateChooserF;
	private JComboBox comboBoxFCI;
	private JComboBox comboBoxFTipDocCony;
	private JDateChooser dateChooserF1;
	private JTextArea textAreaAccionistas;
	private JTextArea textAreaPropoRelCom;
	private JTextArea textAreaBancos;
	private JTextArea textAreaRefProf;
	private JCheckBox checkBoxRXA;
	private JCheckBox checkBoxEAU;
	private JButton btnNewButton, btnModificarCliente, btnNewButton_1, btnSalvarCliJuridico,btnModificarCliente_1, button_1;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compliance frame = new Compliance();
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
	public Compliance() {
		setTitle("Formularios de Compliance");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1170, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1134, 577);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Persona Física", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Datos Generales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(230, 11, 889, 260);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNombreYApellidos = new JLabel("Nombre y apellidos completos:");
		lblNombreYApellidos.setBounds(10, 23, 185, 14);
		panel_2.add(lblNombreYApellidos);
		
		textFieldFNombre = new JTextField();
		textFieldFNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFNombre.setForeground(new Color(0, 0, 128));
		textFieldFNombre.setBackground(new Color(153, 255, 153));
		textFieldFNombre.setEditable(false);
		textFieldFNombre.setBounds(10, 39, 320, 20);
		panel_2.add(textFieldFNombre);
		textFieldFNombre.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(342, 23, 65, 14);
		panel_2.add(lblDireccin);
		
		textFieldFDireccion = new JTextField();
		textFieldFDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFDireccion.setForeground(new Color(0, 0, 128));
		textFieldFDireccion.setBackground(new Color(153, 255, 153));
		textFieldFDireccion.setEditable(false);
		textFieldFDireccion.setBounds(340, 39, 225, 20);
		panel_2.add(textFieldFDireccion);
		textFieldFDireccion.setColumns(10);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(575, 23, 65, 14);
		panel_2.add(lblTelfono);
		
		textFieldFTel = new JTextField();
		textFieldFTel.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFTel.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFTel.setForeground(new Color(0, 0, 128));
		textFieldFTel.setBackground(new Color(153, 255, 153));
		textFieldFTel.setEditable(false);
		textFieldFTel.setBounds(575, 39, 76, 20);
		panel_2.add(textFieldFTel);
		textFieldFTel.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(675, 23, 46, 14);
		panel_2.add(lblEmail);
		
		textFieldFEmail = new JTextField();
		textFieldFEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFEmail.setForeground(new Color(0, 0, 128));
		textFieldFEmail.setBackground(new Color(255, 255, 204));
		textFieldFEmail.setBounds(675, 39, 205, 20);
		panel_2.add(textFieldFEmail);
		textFieldFEmail.setColumns(10);
		
		comboBoxFCI = new JComboBox();
		comboBoxFCI.setModel(new DefaultComboBoxModel(new String[] {"CI", "Pasaporte", "DNI"}));
		comboBoxFCI.setBounds(10, 84, 117, 20);
		panel_2.add(comboBoxFCI);
		
		textFieldFCI = new JTextField();
		textFieldFCI.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFCI.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFCI.setForeground(new Color(0, 0, 128));
		textFieldFCI.setBackground(new Color(255, 255, 204));
		textFieldFCI.setBounds(137, 84, 86, 20);
		panel_2.add(textFieldFCI);
		textFieldFCI.setColumns(10);
		
		JLabel lblPas = new JLabel("Pa\u00EDs de emisi\u00F3n:");
		lblPas.setBounds(233, 70, 97, 14);
		panel_2.add(lblPas);
		
		textFieldFPais = new JTextField();
		textFieldFPais.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFPais.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFPais.setForeground(new Color(0, 0, 128));
		textFieldFPais.setBackground(new Color(255, 255, 204));
		textFieldFPais.setBounds(233, 84, 97, 20);
		panel_2.add(textFieldFPais);
		textFieldFPais.setColumns(10);
		
		JLabel lblNombreCompletoDel = new JLabel("Nombre completo del c\u00F3nyugue:");
		lblNombreCompletoDel.setBounds(438, 123, 213, 14);
		panel_2.add(lblNombreCompletoDel);
		
		textFieldFNombCony = new JTextField();
		textFieldFNombCony.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFNombCony.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFNombCony.setForeground(new Color(0, 0, 128));
		textFieldFNombCony.setBackground(new Color(255, 255, 204));
		textFieldFNombCony.setBounds(438, 138, 213, 20);
		panel_2.add(textFieldFNombCony);
		textFieldFNombCony.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(137, 70, 89, 14);
		panel_2.add(lblNmero);
		
		JLabel lblTipoDocumento = new JLabel("Tipo documento:");
		lblTipoDocumento.setBounds(10, 70, 97, 14);
		panel_2.add(lblTipoDocumento);
		
		JLabel lblFechaNac = new JLabel("Fecha Nac.");
		lblFechaNac.setBounds(342, 70, 86, 14);
		panel_2.add(lblFechaNac);
		
		textFieldFLugNac = new JTextField();
		textFieldFLugNac.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFLugNac.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFLugNac.setForeground(new Color(0, 0, 128));
		textFieldFLugNac.setBackground(new Color(255, 255, 204));
		textFieldFLugNac.setBounds(461, 84, 104, 20);
		panel_2.add(textFieldFLugNac);
		textFieldFLugNac.setColumns(10);
		
		JLabel lblLugarNac = new JLabel("Lugar Nac.");
		lblLugarNac.setBounds(461, 70, 104, 14);
		panel_2.add(lblLugarNac);
		
		textFieldFNacionalidad = new JTextField();
		textFieldFNacionalidad.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFNacionalidad.setForeground(new Color(0, 0, 128));
		textFieldFNacionalidad.setBackground(new Color(255, 255, 204));
		textFieldFNacionalidad.setBounds(575, 84, 76, 20);
		panel_2.add(textFieldFNacionalidad);
		textFieldFNacionalidad.setColumns(10);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setBounds(575, 70, 76, 14);
		panel_2.add(lblNacionalidad);
		
		textFieldFEstCivil = new JTextField();
		textFieldFEstCivil.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFEstCivil.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFEstCivil.setForeground(new Color(0, 0, 128));
		textFieldFEstCivil.setBackground(new Color(255, 255, 204));
		textFieldFEstCivil.setBounds(675, 84, 205, 20);
		panel_2.add(textFieldFEstCivil);
		textFieldFEstCivil.setColumns(10);
		
		JLabel lblEstadoCivil_1 = new JLabel("Estado Civil:");
		lblEstadoCivil_1.setBounds(675, 70, 205, 14);
		panel_2.add(lblEstadoCivil_1);
		
		comboBoxFTipDocCony = new JComboBox();
		comboBoxFTipDocCony.setModel(new DefaultComboBoxModel(new String[] {"CI", "Pasaporte", "DNI"}));
		comboBoxFTipDocCony.setBounds(675, 138, 104, 20);
		panel_2.add(comboBoxFTipDocCony);
		
		textFieldFNUmDocCony = new JTextField();
		textFieldFNUmDocCony.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFNUmDocCony.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFNUmDocCony.setForeground(new Color(0, 0, 128));
		textFieldFNUmDocCony.setBackground(new Color(255, 255, 204));
		textFieldFNUmDocCony.setColumns(10);
		textFieldFNUmDocCony.setBounds(791, 138, 86, 20);
		panel_2.add(textFieldFNUmDocCony);
		
		JLabel label = new JLabel("Tipo documento:");
		label.setBounds(682, 123, 97, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("N\u00FAmero:");
		label_1.setBounds(791, 123, 89, 14);
		panel_2.add(label_1);
		
		textFieldFProf = new JTextField();
		textFieldFProf.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFProf.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFProf.setForeground(new Color(0, 0, 128));
		textFieldFProf.setBackground(new Color(255, 255, 204));
		textFieldFProf.setBounds(10, 138, 228, 20);
		panel_2.add(textFieldFProf);
		textFieldFProf.setColumns(10);
		
		JLabel lblProfesinOficioO = new JLabel("Profesi\u00F3n, oficio o actividad principal:");
		lblProfesinOficioO.setBounds(10, 123, 320, 14);
		panel_2.add(lblProfesinOficioO);
		
		JLabel lblNegocioOActividad = new JLabel("Negocio o actividad Laboral:");
		lblNegocioOActividad.setBounds(10, 169, 365, 14);
		panel_2.add(lblNegocioOActividad);
		
		textFieldFDesAct = new JTextField();
		textFieldFDesAct.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFDesAct.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFDesAct.setForeground(new Color(0, 0, 128));
		textFieldFDesAct.setBackground(new Color(255, 255, 204));
		textFieldFDesAct.setBounds(248, 138, 180, 20);
		panel_2.add(textFieldFDesAct);
		textFieldFDesAct.setColumns(10);
		
		JLabel lblPasEnQue = new JLabel("Pa\u00EDs desarrollo actividad:");
		lblPasEnQue.setBounds(251, 123, 169, 14);
		panel_2.add(lblPasEnQue);
		
		JLabel lblNewLabel = new JLabel("Prop\u00F3sito y naturaleza de la relaci\u00F3n comercial o de la transacci\u00F3n a realizar:");
		lblNewLabel.setBounds(403, 169, 474, 14);
		panel_2.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 194, 380, 53);
		panel_2.add(scrollPane);
		
		textAreaFNegocioAct = new JTextArea();
		textAreaFNegocioAct.setFont(new Font("Tahoma", Font.BOLD, 11));
		textAreaFNegocioAct.setWrapStyleWord(true);
		textAreaFNegocioAct.setLineWrap(true);
		textAreaFNegocioAct.setForeground(new Color(0, 0, 128));
		textAreaFNegocioAct.setBackground(new Color(255, 255, 204));
		textAreaFNegocioAct.setSize(416, 14);
		textAreaFNegocioAct.setLocation(0, 169);
		scrollPane.setViewportView(textAreaFNegocioAct);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(400, 194, 480, 53);
		panel_2.add(scrollPane_1);
		
		textAreaFRelCom = new JTextArea();
		textAreaFRelCom.setFont(new Font("Tahoma", Font.BOLD, 11));
		textAreaFRelCom.setWrapStyleWord(true);
		textAreaFRelCom.setLineWrap(true);
		textAreaFRelCom.setForeground(new Color(0, 0, 128));
		textAreaFRelCom.setBackground(new Color(255, 255, 204));
		textAreaFRelCom.setSize(440, 14);
		textAreaFRelCom.setLocation(0, 168);
		scrollPane_1.setViewportView(textAreaFRelCom);
		
		dateChooserF = new JDateChooser();
		dateChooserF.setBackground(new Color(255, 255, 204));
		dateChooserF.setBounds(342, 84, 109, 20);
		panel_2.add(dateChooserF);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Buscador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 11, 210, 260);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		text = new JTextField();
		text.setForeground(new Color(0, 0, 128));
		
		text.addKeyListener(new KeyAdapter() {	
			StringBuffer txt = new StringBuffer();
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();	
				if (caracter == KeyEvent.VK_BACK_SPACE){
					e.consume();	
					if (txt.length() >=1){
						txt.setLength(txt.length()-1);
						filtrarTuplasPersonasFisicas(perFisicasToString(),txt.toString());
					}
				}else{
					txt.append(caracter);					
					filtrarTuplasPersonasFisicas(perFisicasToString(),txt.toString());
				}
			}
		});
		text.setBackground(new Color(255, 255, 204));
		text.setBounds(10, 22, 190, 20);
		panel_3.add(text);
		text.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 56, 190, 193);
		panel_3.add(scrollPane_2);
		
		listPerFisicas = new JList(this.perFisicasToString());
		listPerFisicas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ControladorCompliance controlador = new ControladorCompliance();	
				String nombrePersona = (String) listPerFisicas.getSelectedValue();
				VOCliente persona = controlador.obtenerVOClienteNombre(nombrePersona);
				textFieldFNombre.setText(persona.getNombre());
				textFieldFDireccion.setText(persona.getDireccion());
				textFieldFTel.setText(persona.getTel());
				
				int idPersona=persona.getIdCli();
				limpiarPantallaPersonaFisica();
				if (controlador.existePersonaFisica(idPersona)){
					
					btnNewButton.setEnabled(false);
					btnModificarCliente.setEnabled(true);
					btnNewButton_1.setEnabled(true);
					VOPerFisica personaFisica = controlador.obtenerPersonaFisica(idPersona);					
					desplegarPersonaFisica(personaFisica);
				}else{
					btnNewButton.setEnabled(true);
					btnModificarCliente.setEnabled(false);
					btnNewButton_1.setEnabled(false);
				}
				
				
				
				
			}
		});
		scrollPane_2.setViewportView(listPerFisicas);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Origen de los fondos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(20, 282, 396, 186);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblOrigenDeLos = new JLabel("Origen de los fondos manejados en la transacci\u00F3n:");
		lblOrigenDeLos.setBounds(10, 24, 376, 14);
		panel_4.add(lblOrigenDeLos);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 41, 376, 54);
		panel_4.add(scrollPane_3);
		
		textAreaFOrigFondos = new JTextArea();
		textAreaFOrigFondos.setFont(new Font("Tahoma", Font.BOLD, 11));
		textAreaFOrigFondos.setLineWrap(true);
		textAreaFOrigFondos.setForeground(new Color(0, 0, 128));
		textAreaFOrigFondos.setBackground(new Color(255, 255, 204));
		scrollPane_3.setViewportView(textAreaFOrigFondos);
		
		JLabel lblDesempeoDeTareas = new JLabel("Desempe\u00F1o de tareas en los \u00FAltimos 2 a\u00F1os inherentes a una PEP:");
		lblDesempeoDeTareas.setBounds(10, 106, 376, 14);
		panel_4.add(lblDesempeoDeTareas);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(10, 124, 376, 54);
		panel_4.add(scrollPane_8);
		
		textAreaFTareas = new JTextArea();
		textAreaFTareas.setFont(new Font("Tahoma", Font.BOLD, 11));
		textAreaFTareas.setLineWrap(true);
		textAreaFTareas.setForeground(new Color(0, 0, 128));
		textAreaFTareas.setBackground(new Color(255, 255, 204));
		scrollPane_8.setViewportView(textAreaFTareas);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Referencias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(426, 288, 693, 180);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblBancosConLos = new JLabel("Bancos con los que opera:");
		lblBancosConLos.setBounds(10, 22, 309, 14);
		panel_5.add(lblBancosConLos);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 47, 309, 42);
		panel_5.add(scrollPane_4);
		
		textAreaFBancos = new JTextArea();
		textAreaFBancos.setFont(new Font("Tahoma", Font.BOLD, 11));
		textAreaFBancos.setLineWrap(true);
		textAreaFBancos.setForeground(new Color(0, 0, 128));
		textAreaFBancos.setBackground(new Color(255, 255, 204));
		scrollPane_4.setViewportView(textAreaFBancos);
		
		JLabel lblReferenciasProfesionales = new JLabel("Referencias Profesionales:");
		lblReferenciasProfesionales.setBounds(10, 98, 309, 14);
		panel_5.add(lblReferenciasProfesionales);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 120, 309, 52);
		panel_5.add(scrollPane_5);
		
		textAreaFRefProf = new JTextArea();
		textAreaFRefProf.setFont(new Font("Tahoma", Font.BOLD, 11));
		textAreaFRefProf.setLineWrap(true);
		textAreaFRefProf.setForeground(new Color(0, 0, 128));
		textAreaFRefProf.setBackground(new Color(255, 255, 204));
		scrollPane_5.setViewportView(textAreaFRefProf);
		
		JLabel lblServicioRequeridoA = new JLabel("Servicio requerido a nuestra firma:");
		lblServicioRequeridoA.setBounds(329, 22, 354, 14);
		panel_5.add(lblServicioRequeridoA);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(329, 47, 354, 42);
		panel_5.add(scrollPane_6);
		
		textAreaFServReq = new JTextArea();
		textAreaFServReq.setFont(new Font("Tahoma", Font.BOLD, 11));
		textAreaFServReq.setLineWrap(true);
		textAreaFServReq.setForeground(new Color(0, 0, 128));
		textAreaFServReq.setBackground(new Color(255, 255, 204));
		scrollPane_6.setViewportView(textAreaFServReq);
		
		JLabel lblReferidoANuestra = new JLabel("Referido a nuestra firma:");
		lblReferidoANuestra.setBounds(329, 98, 354, 14);
		panel_5.add(lblReferidoANuestra);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(329, 120, 354, 52);
		panel_5.add(scrollPane_7);
		
		textAreaFRefFirma = new JTextArea();
		textAreaFRefFirma.setFont(new Font("Tahoma", Font.BOLD, 11));
		textAreaFRefFirma.setLineWrap(true);
		textAreaFRefFirma.setForeground(new Color(0, 0, 128));
		textAreaFRefFirma.setBackground(new Color(255, 255, 204));
		scrollPane_7.setViewportView(textAreaFRefFirma);
		
		btnNewButton = new JButton("Salvar Cliente");	
		
		btnNewButton.setEnabled(false);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = textFieldFEmail.getText();
				String tipoDoc = (String) comboBoxFCI.getSelectedItem();
				String numDoc = textFieldFCI.getText();
				String paisEmision = textFieldFPais.getText();
				
				if (dateChooserF.getCalendar() == null){					
					JOptionPane.showMessageDialog(getContentPane(),VentanaException.FECHA_OBLIGATORIA, "Error en los valores", JOptionPane.ERROR_MESSAGE);
				}else{
					
					///
					btnNewButton.setEnabled(false);
					btnModificarCliente.setEnabled(true);		
					btnNewButton_1.setEnabled(true);
					///
					
					int anio = dateChooserF.getCalendar().get(Calendar.YEAR);
					int mes = dateChooserF.getCalendar().get(Calendar.MONTH) + 1;
					int dia = dateChooserF.getCalendar().get(Calendar.DAY_OF_MONTH);
					String fechNac = "" + dia +"/"+mes+"/"+anio;
				
				String lugarNac = textFieldFLugNac.getText();
				String nacionalidad = textFieldFNacionalidad.getText();
				String estadoCivil = textFieldFEstCivil.getText();
				String profesion = textFieldFProf.getText();
				String paisdesarrolloActividad = textFieldFDesAct.getText();
				String nombreConyugue = textFieldFNombCony.getText();
				String tipoDocConyugue = (String) comboBoxFTipDocCony.getSelectedItem();
				String numDocConyugue = textFieldFNUmDocCony.getText();
				String actividadLaboral = textAreaFNegocioAct.getText();
				String propositoTransaccion = textAreaFRelCom.getText();
				String origenFondos = textAreaFOrigFondos.getText();
				String bancos = textAreaFBancos.getText();
				String servicioFirma = textAreaFServReq.getText();
				String ultimasTareas = textAreaFTareas.getText();
				String refPersonales = textAreaFRefProf.getText();
				String referidoFirma = textAreaFRefFirma.getText();
				
				String nombreCliente = (String) listPerFisicas.getSelectedValue();				
				ControladorCompliance controladorCliente = new ControladorCompliance();
				
				if (controladorCliente.existePersonaFisica(controladorCliente.obtenerVOClienteNombre(nombreCliente).getIdCli())){
					JOptionPane.showMessageDialog(getContentPane(),VentanaException.EXISTE_PERSONA_FISICA, "Error en los valores", JOptionPane.ERROR_MESSAGE);
				}else{
					VOCliente cliente = controladorCliente.obtenerVOClienteNombre(nombreCliente);							
					int idPersonaFisica = cliente.getIdCli();
					
					int rxa=0; int eau=0;
					if (chckbxFRXA.isSelected())
						rxa=1;
					if (chckbxFEAU.isSelected())
						eau=1;			
					
					VOPerFisica personaFisica = new VOPerFisica(
							idPersonaFisica, 
							rxa, 
							eau, 
							email, 
							tipoDoc, 
							numDoc, 
							paisEmision, 
							fechNac, 
							lugarNac, 
							nacionalidad, 
							estadoCivil, 
							profesion, 
							paisdesarrolloActividad, 
							nombreConyugue, 
							tipoDocConyugue, 
							numDocConyugue, 
							actividadLaboral, 
							propositoTransaccion, 
							origenFondos, 
							bancos, 
							servicioFirma, 
							ultimasTareas, 
							refPersonales, 
							referidoFirma);				
					try {
						controladorCliente.agregarPersonaFisica(personaFisica);
						JOptionPane.showMessageDialog(getContentPane(), VentanaException.INGRESO_EXITOSO, "Ingreso exitoso", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				}
				
			
				
			
				
			}
		});
		btnNewButton.setBounds(30, 479, 158, 59);
		panel.add(btnNewButton);
		
		btnModificarCliente = new JButton("Modificar Cliente");
		
		btnModificarCliente.setEnabled(false);
		
		btnModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = textFieldFEmail.getText();
				String tipoDoc = (String) comboBoxFCI.getSelectedItem();
				String numDoc = textFieldFCI.getText();
				String paisEmision = textFieldFPais.getText();
				
				if (dateChooserF.getCalendar() != null){				
					int anio = dateChooserF.getCalendar().get(Calendar.YEAR);
					int mes = dateChooserF.getCalendar().get(Calendar.MONTH) + 1;
					int dia = dateChooserF.getCalendar().get(Calendar.DAY_OF_MONTH);
					String fechNac = "" + dia +"/"+mes+"/"+anio;
													
					String lugarNac = textFieldFLugNac.getText();
					String nacionalidad = textFieldFNacionalidad.getText();
					String estadoCivil = textFieldFEstCivil.getText();
					String profesion = textFieldFProf.getText();
					String paisdesarrolloActividad = textFieldFDesAct.getText();
					String nombreConyugue = textFieldFNombCony.getText();
					String tipoDocConyugue = (String) comboBoxFTipDocCony.getSelectedItem();
					String numDocConyugue = textFieldFNUmDocCony.getText();
					String actividadLaboral = textAreaFNegocioAct.getText();
					String propositoTransaccion = textAreaFRelCom.getText();
					String origenFondos = textAreaFOrigFondos.getText();
					String bancos = textAreaFBancos.getText();
					String servicioFirma = textAreaFServReq.getText();
					String ultimasTareas = textAreaFTareas.getText();
					String refPersonales = textAreaFRefProf.getText();
					String referidoFirma = textAreaFRefFirma.getText();
					
					String nombreCliente = (String) listPerFisicas.getSelectedValue();
					ControladorCompliance controladorCliente = new ControladorCompliance();
					VOCliente cliente = controladorCliente.obtenerVOClienteNombre(nombreCliente);							
					int idPersonaFisica = cliente.getIdCli();
					
					int rxa=0; int eau=0;
					if (chckbxFRXA.isSelected())
						rxa=1;
					if (chckbxFEAU.isSelected())
						eau=1;			
					VOPerFisica personaFisica = new VOPerFisica(
							idPersonaFisica, 
							rxa, 
							eau, 
							email, 
							tipoDoc, 
							numDoc, 
							paisEmision, 
							fechNac, 
							lugarNac, 
							nacionalidad, 
							estadoCivil, 
							profesion, 
							paisdesarrolloActividad, 
							nombreConyugue, 
							tipoDocConyugue, 
							numDocConyugue, 
							actividadLaboral, 
							propositoTransaccion, 
							origenFondos, 
							bancos, 
							servicioFirma, 
							ultimasTareas, 
							refPersonales, 
							referidoFirma);				
					try {
						controladorCliente.modificarPersonaFisica(personaFisica);
						JOptionPane.showMessageDialog(getContentPane(), VentanaException.MODIFICACION_EXITOSA, "Modificacion exitosa", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				}
				 
						 
			}
		});
		btnModificarCliente.setBounds(205, 479, 158, 59);
		panel.add(btnModificarCliente);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Generar Formulario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(426, 477, 325, 61);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		chckbxFRXA = new JCheckBox("RXA");
		chckbxFRXA.setBounds(6, 27, 57, 23);
		panel_6.add(chckbxFRXA);
		
		chckbxFEAU = new JCheckBox("EAU");
		chckbxFEAU.setBounds(64, 27, 66, 23);
		panel_6.add(chckbxFEAU);
		
		btnNewButton_1 = new JButton("Generar PDF");		
		btnNewButton_1.setEnabled(false);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Map parametros1 = new HashMap();				
				String logoRXA = "rxa.png";
				String logoEAU = "eau.png";				
				
				if (chckbxFRXA.isSelected())				
					parametros1.put("logo_rxa",this.getClass().getResourceAsStream(logoRXA));
				if (chckbxFEAU.isSelected())
					parametros1.put("logo_eau", this.getClass().getResourceAsStream(logoEAU));
				
				parametros1.put("nombre", textFieldFNombre.getText());
				parametros1.put("direccion", textFieldFDireccion.getText());
				parametros1.put("telefono", textFieldFTel.getText());
				parametros1.put("email", textFieldFEmail.getText());				
				parametros1.put("tipo_numced", comboBoxFCI.getSelectedItem() + ",  " + textFieldFCI.getText());
				parametros1.put("pais", textFieldFPais.getText());
				
				int anio = dateChooserF.getCalendar().get(Calendar.YEAR);
				int mes = dateChooserF.getCalendar().get(Calendar.MONTH) + 1;
				int dia = dateChooserF.getCalendar().get(Calendar.DAY_OF_MONTH);
				String fechNac = "" + dia +"/"+mes+"/"+anio;				
				parametros1.put("fechynum", fechNac + ", " + textFieldFLugNac.getText());
				parametros1.put("nacionalidad", textFieldFNacionalidad.getText());
				parametros1.put("estadocivil", textFieldFEstCivil.getText());
				parametros1.put("nombre_cony", textFieldFNombCony.getText());
				parametros1.put("tipo_numced_cony", comboBoxFTipDocCony.getSelectedItem() + ", " +textFieldFNUmDocCony.getText());				
				parametros1.put("profesion", textFieldFProf.getText());
				parametros1.put("negocio", textAreaFNegocioAct.getText());
				parametros1.put("pais_actividad", textFieldFDesAct.getText());
				parametros1.put("proposito_transact", textAreaFRelCom.getText());
				
				
				Map parametros2 = new HashMap();
				parametros2.put("origen_fondos", textAreaFOrigFondos.getText());
				parametros2.put("bancos_opera", textAreaFBancos.getText());
				parametros2.put("referencias_pers", textAreaFRefProf.getText());
				parametros2.put("servicio_brindado", textAreaFServReq.getText());
				parametros2.put("referido_por", textAreaFRefFirma.getText());
				
				Map parametros3 = new HashMap();
				parametros3.put("tareas", textAreaFTareas.getText());
				
				try {
					JasperPrint jp1 = JasperFillManager.fillReport("./reportes/jasper/comperfis1.jasper", parametros1, new JREmptyDataSource());
					JasperPrint jp2 = JasperFillManager.fillReport("./reportes/jasper/comperfis2.jasper", parametros2, new JREmptyDataSource());
					JasperPrint jp3 = JasperFillManager.fillReport("./reportes/jasper/comperfis3.jasper", parametros3, new JREmptyDataSource());
					
					List pages = jp2 .getPages();
					for (int j = 0; j < pages.size(); j++) {
					    JRPrintPage object = (JRPrintPage)pages.get(j);
					    jp1.addPage(object);
					}
					
					pages = jp3 .getPages();
					for (int j = 0; j < pages.size(); j++) {
					    JRPrintPage object1 = (JRPrintPage)pages.get(j);
					    jp1.addPage(object1);
					}
					
					//JasperExportManager.exportReportToPdfFile(jp1, "./reportes/pdf/comperfis1.pdf");
					JasperViewer.viewReport(jp1, false);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(132, 27, 183, 23);
		panel_6.add(btnNewButton_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Formulario en blanco", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(761, 479, 358, 59);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		final JCheckBox checkBoxfisrxa = new JCheckBox("RXA");
		checkBoxfisrxa.setBounds(6, 25, 57, 23);
		panel_7.add(checkBoxfisrxa);
		
		final JCheckBox checkBoxfiseau = new JCheckBox("EAU");
		checkBoxfiseau.setBounds(64, 25, 66, 23);
		panel_7.add(checkBoxfiseau);
		
		JButton button = new JButton("Generar PDF");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
				    try {
				    	File myFile;
				    	if (checkBoxfisrxa.isSelected() && checkBoxfiseau.isSelected()){
				    		myFile = new File("./formularios/pfrxaeau.pdf");
				    		 Desktop.getDesktop().open(myFile);
				    	}else
					    	if (checkBoxfisrxa.isSelected()){
					    		myFile = new File("./formularios/pfrxa.pdf");
					    		 Desktop.getDesktop().open(myFile);
					    	}else
					    		if(checkBoxfiseau.isSelected()){
					    			myFile = new File("./formularios/pfeau.pdf");
						    		 Desktop.getDesktop().open(myFile);
					    		}else{
					    			myFile = new File("./formularios/pf.pdf");
						    		 Desktop.getDesktop().open(myFile);
					    		}
				    	
				    	
				        //File myFile = new File("./formularios/perfisica.pdf");
				       
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				}
				
			}
		});
		button.setBounds(165, 25, 183, 23);
		panel_7.add(button);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Persona Jurídica", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(null, "Buscador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(10, 11, 210, 260);
		panel_1.add(panel_8);
		
		textField_15 = new JTextField();
		textField_15.addKeyListener(new KeyAdapter() {
			StringBuffer txt = new StringBuffer();
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();	
				if (caracter == KeyEvent.VK_BACK_SPACE){
					e.consume();	
					if (txt.length() >=1){
						txt.setLength(txt.length()-1);
						filtrarTuplasPersonasJuridicas(perJuridicasToString(),txt.toString());
					}
				}else{
					txt.append(caracter);					
					filtrarTuplasPersonasJuridicas(perJuridicasToString(),txt.toString());
				}
			}
		});
		textField_15.setBackground(new Color(204, 255, 255));
		textField_15.setColumns(10);
		textField_15.setBounds(10, 22, 190, 20);
		panel_8.add(textField_15);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(10, 56, 190, 193);
		panel_8.add(scrollPane_9);
		
		listPerJuridicas = new JList(this.perJuridicasToString());
		listPerJuridicas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ControladorCompliance controlador = new ControladorCompliance();
				String nombrePersona = (String) listPerJuridicas.getSelectedValue();
				VOCliente persona = controlador.obtenerVOClienteNombre(nombrePersona);
				textFieldDenomSocial.setText(persona.getNombre());
				textFieldNumRut.setText(persona.getRut());
				textFieldDomic.setText(persona.getDireccion());
				textFieldTel.setText(persona.getTel());
				
				limpiarPantallaPersonaJuridica();
				int idPersona=persona.getIdCli();
				
				if (controlador.existePersonaJuridica(idPersona)){
					VOPerJuridica personaJuridica = controlador.obtenerPersonaJuridica(idPersona);	
					desplegarPersonaJuridica(personaJuridica);
					
					btnModificarCliente_1.setEnabled(true);
					btnSalvarCliJuridico.setEnabled(false);
					button_1.setEnabled(true);
				}else{
					btnSalvarCliJuridico.setEnabled(true);
					btnModificarCliente_1.setEnabled(false);
					button_1.setEnabled(false);
				}
				
				
				
			}
		});
		scrollPane_9.setViewportView(listPerJuridicas);
		
		JLabel lblDenominacinSocial = new JLabel("Denominaci\u00F3n Social:");
		lblDenominacinSocial.setBounds(230, 45, 195, 14);
		panel_1.add(lblDenominacinSocial);
		
		textFieldDenomSocial = new JTextField();
		textFieldDenomSocial.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDenomSocial.setEditable(false);
		textFieldDenomSocial.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldDenomSocial.setBackground(new Color(102, 255, 153));
		textFieldDenomSocial.setBounds(230, 62, 195, 20);
		panel_1.add(textFieldDenomSocial);
		textFieldDenomSocial.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(458, 45, 60, 14);
		panel_1.add(lblFecha);
		
		textFieldLugarConst = new JTextField();
		textFieldLugarConst.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLugarConst.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldLugarConst.setBackground(new Color(204, 255, 255));
		textFieldLugarConst.setBounds(601, 62, 293, 20);
		panel_1.add(textFieldLugarConst);
		textFieldLugarConst.setColumns(10);
		
		JLabel lblLugarDeConstitucin = new JLabel("Lugar de constituci\u00F3n:");
		lblLugarDeConstitucin.setBounds(601, 45, 293, 14);
		panel_1.add(lblLugarDeConstitucin);
		
		textFieldNumRut = new JTextField();
		textFieldNumRut.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumRut.setEditable(false);
		textFieldNumRut.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldNumRut.setBackground(new Color(102, 255, 153));
		textFieldNumRut.setBounds(931, 62, 188, 20);
		panel_1.add(textFieldNumRut);
		textFieldNumRut.setColumns(10);
		
		JLabel lblNDeRut = new JLabel("N\u00BA de RUT:");
		lblNDeRut.setBounds(931, 45, 188, 14);
		panel_1.add(lblNDeRut);
		
		textFieldDomic = new JTextField();
		textFieldDomic.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDomic.setEditable(false);
		textFieldDomic.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldDomic.setBackground(new Color(102, 255, 153));
		textFieldDomic.setBounds(230, 125, 195, 20);
		panel_1.add(textFieldDomic);
		textFieldDomic.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setBounds(230, 107, 195, 14);
		panel_1.add(lblDomicilio);
		
		textFieldSede = new JTextField();
		textFieldSede.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSede.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldSede.setBackground(new Color(204, 255, 255));
		textFieldSede.setBounds(458, 125, 100, 20);
		panel_1.add(textFieldSede);
		textFieldSede.setColumns(10);
		
		JLabel lblSede = new JLabel("Sede:");
		lblSede.setBounds(458, 107, 100, 14);
		panel_1.add(lblSede);
		
		textFieldTel = new JTextField();
		textFieldTel.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTel.setEditable(false);
		textFieldTel.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldTel.setBackground(new Color(102, 255, 153));
		textFieldTel.setBounds(601, 125, 157, 20);
		panel_1.add(textFieldTel);
		textFieldTel.setColumns(10);
		
		JLabel lblTelfono_1 = new JLabel("Tel\u00E9fono:");
		lblTelfono_1.setBounds(601, 107, 157, 14);
		panel_1.add(lblTelfono_1);
		
		textFieldPagWeb = new JTextField();
		textFieldPagWeb.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPagWeb.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldPagWeb.setBackground(new Color(204, 255, 255));
		textFieldPagWeb.setBounds(800, 125, 319, 20);
		panel_1.add(textFieldPagWeb);
		textFieldPagWeb.setColumns(10);
		
		JLabel lblPginaWeb = new JLabel("P\u00E1gina Web");
		lblPginaWeb.setBounds(800, 107, 319, 14);
		panel_1.add(lblPginaWeb);
		
		textFieldActivPrincip = new JTextField();
		textFieldActivPrincip.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldActivPrincip.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldActivPrincip.setBackground(new Color(204, 255, 255));
		textFieldActivPrincip.setBounds(230, 193, 328, 20);
		panel_1.add(textFieldActivPrincip);
		textFieldActivPrincip.setColumns(10);
		
		JLabel lblActividadPrincipal = new JLabel("Actividad principal:");
		lblActividadPrincipal.setBounds(230, 175, 195, 14);
		panel_1.add(lblActividadPrincipal);
		
		textFieldVolIngresos = new JTextField();
		textFieldVolIngresos.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldVolIngresos.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldVolIngresos.setBackground(new Color(204, 255, 255));
		textFieldVolIngresos.setBounds(601, 193, 157, 20);
		panel_1.add(textFieldVolIngresos);
		textFieldVolIngresos.setColumns(10);
		
		JLabel lblVolDeIngresos = new JLabel("Vol. de Ingresos:");
		lblVolDeIngresos.setBounds(601, 175, 110, 14);
		panel_1.add(lblVolDeIngresos);
		
		textFieldPaisActivid = new JTextField();
		textFieldPaisActivid.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPaisActivid.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldPaisActivid.setBackground(new Color(204, 255, 255));
		textFieldPaisActivid.setBounds(800, 193, 319, 20);
		panel_1.add(textFieldPaisActivid);
		textFieldPaisActivid.setColumns(10);
		
		JLabel lblPasDondeDesarrolla = new JLabel("Pa\u00EDs donde desarrolla la actividad:");
		lblPasDondeDesarrolla.setBounds(800, 175, 319, 14);
		panel_1.add(lblPasDondeDesarrolla);
		
		textFieldOrigenFondos = new JTextField();
		textFieldOrigenFondos.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOrigenFondos.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldOrigenFondos.setBackground(new Color(204, 255, 255));
		textFieldOrigenFondos.setBounds(230, 261, 528, 20);
		panel_1.add(textFieldOrigenFondos);
		textFieldOrigenFondos.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Origen de los fondos manejados en la transacci\u00F3n:");
		lblNewLabel_1.setBounds(230, 244, 528, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblPasEnQue_1 = new JLabel("Pa\u00EDs en que se desarrolla la actividad:");
		lblPasEnQue_1.setBounds(800, 244, 319, 14);
		panel_1.add(lblPasEnQue_1);
		
		textFieldPaisEnQueDesActividad = new JTextField();
		textFieldPaisEnQueDesActividad.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPaisEnQueDesActividad.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldPaisEnQueDesActividad.setBackground(new Color(204, 255, 255));
		textFieldPaisEnQueDesActividad.setColumns(10);
		textFieldPaisEnQueDesActividad.setBounds(800, 262, 319, 20);
		panel_1.add(textFieldPaisEnQueDesActividad);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(10, 314, 511, 47);
		panel_1.add(scrollPane_10);
		
		textAreaAccionistas = new JTextArea();
		textAreaAccionistas.setFont(new Font("Monospaced", Font.BOLD, 13));
		textAreaAccionistas.setBackground(new Color(204, 255, 255));
		textAreaAccionistas.setLineWrap(true);
		scrollPane_10.setViewportView(textAreaAccionistas);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(567, 314, 552, 47);
		panel_1.add(scrollPane_11);
		
		textAreaPropoRelCom = new JTextArea();
		textAreaPropoRelCom.setFont(new Font("Monospaced", Font.BOLD, 13));
		textAreaPropoRelCom.setBackground(new Color(204, 255, 255));
		textAreaPropoRelCom.setLineWrap(true);
		scrollPane_11.setViewportView(textAreaPropoRelCom);
		
		JLabel lblAccionistasYParticipacin = new JLabel("Accionistas y participaci\u00F3n accionaria:");
		lblAccionistasYParticipacin.setBounds(10, 296, 508, 14);
		panel_1.add(lblAccionistasYParticipacin);
		
		JLabel lblPropsitoYNaturaleza = new JLabel("Prop\u00F3sito y naturaleza de la relaci\u00F3n comercial o de la transacci\u00F3n a realizar:");
		lblPropsitoYNaturaleza.setBounds(567, 296, 552, 14);
		panel_1.add(lblPropsitoYNaturaleza);
		
		JLabel lblReferenciasBancariasbancos = new JLabel("Referencias Bancarias (Bancos con los que trabaja):");
		lblReferenciasBancariasbancos.setBounds(10, 372, 508, 14);
		panel_1.add(lblReferenciasBancariasbancos);
		
		JScrollPane scrollPane_12 = new JScrollPane();
		scrollPane_12.setBounds(10, 390, 511, 47);
		panel_1.add(scrollPane_12);
		
		textAreaBancos = new JTextArea();
		textAreaBancos.setFont(new Font("Monospaced", Font.BOLD, 13));
		textAreaBancos.setBackground(new Color(204, 255, 255));
		textAreaBancos.setLineWrap(true);
		scrollPane_12.setViewportView(textAreaBancos);
		
		JLabel lblReferenciasProfesionales_1 = new JLabel("Referencias Profesionales:");
		lblReferenciasProfesionales_1.setBounds(567, 372, 552, 14);
		panel_1.add(lblReferenciasProfesionales_1);
		
		JScrollPane scrollPane_13 = new JScrollPane();
		scrollPane_13.setBounds(567, 390, 552, 47);
		panel_1.add(scrollPane_13);
		
		textAreaRefProf = new JTextArea();
		textAreaRefProf.setFont(new Font("Monospaced", Font.BOLD, 13));
		textAreaRefProf.setBackground(new Color(204, 255, 255));
		textAreaRefProf.setLineWrap(true);
		scrollPane_13.setViewportView(textAreaRefProf);
		
		textFieldServRequFirma = new JTextField();
		textFieldServRequFirma.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldServRequFirma.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldServRequFirma.setBackground(new Color(204, 255, 255));
		textFieldServRequFirma.setBounds(10, 465, 319, 20);
		panel_1.add(textFieldServRequFirma);
		textFieldServRequFirma.setColumns(10);
		
		JLabel lblServicioRequeridoPor = new JLabel("Servicio requerido por nuestra firma:");
		lblServicioRequeridoPor.setBounds(10, 448, 508, 14);
		panel_1.add(lblServicioRequeridoPor);
		
		JLabel lblReferidoANuestra_1 = new JLabel("Referido a nuestra firma por:");
		lblReferidoANuestra_1.setBounds(10, 501, 319, 14);
		panel_1.add(lblReferidoANuestra_1);
		
		textFieldRefNuestraFirma = new JTextField();
		textFieldRefNuestraFirma.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRefNuestraFirma.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldRefNuestraFirma.setBackground(new Color(204, 255, 255));
		textFieldRefNuestraFirma.setColumns(10);
		textFieldRefNuestraFirma.setBounds(10, 518, 319, 20);
		panel_1.add(textFieldRefNuestraFirma);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new TitledBorder(null, "Formulario en blanco", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(843, 465, 276, 71);
		panel_1.add(panel_10);
		
		final JCheckBox checkBoxpjrxa = new JCheckBox("RXA");
		checkBoxpjrxa.setBounds(6, 25, 57, 23);
		panel_10.add(checkBoxpjrxa);
		
		final JCheckBox checkBoxpjeau = new JCheckBox("EAU");
		checkBoxpjeau.setBounds(64, 25, 66, 23);
		panel_10.add(checkBoxpjeau);
		
		JButton button_2 = new JButton("Generar PDF");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
				    try {
				    	File myFile;
				    	if (checkBoxpjrxa.isSelected() && checkBoxpjeau.isSelected()){
				    		myFile = new File("./formularios/pjrxaeau.pdf");
					        Desktop.getDesktop().open(myFile);
				    	}else
				    		if (checkBoxpjrxa.isSelected()){
				    			myFile = new File("./formularios/pjrxa.pdf");
						        Desktop.getDesktop().open(myFile);
				    		}else
				    			if (checkBoxpjeau.isSelected()){
				    				myFile = new File("./formularios/pjeau.pdf");
							        Desktop.getDesktop().open(myFile);
				    			}else{
				    				myFile = new File("./formularios/pj.pdf");
							        Desktop.getDesktop().open(myFile);
				    			}				        
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				}
			}
		});
		button_2.setBounds(138, 25, 128, 23);
		panel_10.add(button_2);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Generar formulario:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.setBounds(551, 465, 276, 73);
		panel_1.add(panel_9);
		
		checkBoxRXA = new JCheckBox("RXA");
		checkBoxRXA.setBounds(6, 25, 57, 23);
		panel_9.add(checkBoxRXA);
		
		checkBoxEAU = new JCheckBox("EAU");
		checkBoxEAU.setBounds(64, 25, 66, 23);
		panel_9.add(checkBoxEAU);
		
		button_1 = new JButton("Generar PDF");
		button_1.setEnabled(false);
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String logoRXA = "rxa.png";
				String logoEAU = "eau.png";				
				Map parametros1 = new HashMap();
				
							
				if (checkBoxRXA.isSelected())				
					parametros1.put("logo_rxa",this.getClass().getResourceAsStream(logoRXA));
				if (checkBoxEAU.isSelected())
					parametros1.put("logo_eau", this.getClass().getResourceAsStream(logoEAU));				
				
				parametros1.put("denom_social", textFieldDenomSocial.getText());
				
				int anio = dateChooserF1.getCalendar().get(Calendar.YEAR);
				int mes = dateChooserF1.getCalendar().get(Calendar.MONTH) + 1;
				int dia = dateChooserF1.getCalendar().get(Calendar.DAY_OF_MONTH);
				String fechaYlugar = "" + dia +"/"+mes+"/"+anio + ", " + textFieldLugarConst.getText();
				
				parametros1.put("fecha_lugar", fechaYlugar);
				parametros1.put("rut", textFieldNumRut.getText());
				
				String domYSede = textFieldDomic.getText() + ", " + textFieldSede.getText();
				parametros1.put("dom_sede", domYSede);
				parametros1.put("telefono", textFieldTel.getText());
				parametros1.put("web", textFieldPagWeb.getText());
				parametros1.put("actividad", textFieldActivPrincip.getText());
				parametros1.put("ingresos", textFieldVolIngresos.getText());
				parametros1.put("pais_actividad", textFieldPaisActivid.getText());
				parametros1.put("accionistas", textAreaAccionistas.getText());
				parametros1.put("prop_transact", textAreaPropoRelCom.getText());
				parametros1.put("origen_fondos", textFieldOrigenFondos.getText());
				parametros1.put("paisenque", textFieldPaisEnQueDesActividad.getText());
				parametros1.put("bancos", textAreaBancos.getText());
				
				Map parametros2 = new HashMap();
				parametros2.put("refprof", textAreaRefProf.getText());
				parametros2.put("servicio_req", textFieldServRequFirma.getText());
				parametros2.put("referido", textFieldRefNuestraFirma.getText());
				
				try {
					JasperPrint jp1 = JasperFillManager.fillReport("./reportes/jasper/comperjur1.jasper", parametros1, new JREmptyDataSource());
					JasperPrint jp2 = JasperFillManager.fillReport("./reportes/jasper/comperjur2.jasper", parametros2, new JREmptyDataSource());
										
					List pages = jp2 .getPages();
					for (int j = 0; j < pages.size(); j++) {
					    JRPrintPage object = (JRPrintPage)pages.get(j);
					    jp1.addPage(object);
					}
										
					//JasperExportManager.exportReportToPdfFile(jp1, "./reportes/pdf/comperfis1.pdf");
					JasperViewer.viewReport(jp1, false);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		button_1.setBounds(138, 25, 128, 23);
		panel_9.add(button_1);
		
		btnSalvarCliJuridico = new JButton("Salvar Cliente");
		btnSalvarCliJuridico.setEnabled(false);
		btnSalvarCliJuridico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String denomSocial = textFieldDenomSocial.getText();
				
				if (dateChooserF1.getCalendar() == null){
					JOptionPane.showMessageDialog(getContentPane(),VentanaException.FECHA_OBLIGATORIA, "Error en los valores", JOptionPane.ERROR_MESSAGE);
				}else{
					btnSalvarCliJuridico.setEnabled(false);
					btnModificarCliente_1.setEnabled(true);
					button_1.setEnabled(true);
					int anio = dateChooserF1.getCalendar().get(Calendar.YEAR);
					int mes = dateChooserF1.getCalendar().get(Calendar.MONTH) + 1;
					int dia = dateChooserF1.getCalendar().get(Calendar.DAY_OF_MONTH);
					String fecha = "" + dia +"/"+mes+"/"+anio;
				
					String lugarConst = textFieldLugarConst.getText();
					String rut = textFieldNumRut.getText();
					String domicilio = textFieldDomic.getText();
					String sede = textFieldSede.getText();
					String tel = textFieldTel.getText();
					String web = textFieldPagWeb.getText();
					String actividad = textFieldActivPrincip.getText();
					String volIngresos = textFieldVolIngresos.getText();
					String paisDondeDesarrollaActiv = textFieldPaisActivid.getText();
					String origenFondos = textFieldOrigenFondos.getText();
					String paisEnQueDesarrolaActiv = textFieldPaisEnQueDesActividad.getText();
					String accionistas = textAreaAccionistas.getText();
					String propNaturaleza = textAreaPropoRelCom.getText();
					String bancos = textAreaBancos.getText();
					String refProf = textAreaRefProf.getText();
					String servicioReq = textFieldServRequFirma.getText();
					String refANuestraFirma = textFieldRefNuestraFirma.getText();
					
					String nombreCliente = (String) listPerJuridicas.getSelectedValue();				
					ControladorCompliance controladorCliente = new ControladorCompliance();
					
					if (controladorCliente.existePersonaJuridica(controladorCliente.obtenerVOClienteNombre(nombreCliente).getIdCli())){
						JOptionPane.showMessageDialog(getContentPane(),VentanaException.EXISTE_PERSONA_JURIDICA, "Error en los valores", JOptionPane.ERROR_MESSAGE);
					}else{
						VOCliente cliente = controladorCliente.obtenerVOClienteNombre(nombreCliente);							
						int idPersonaFisica = cliente.getIdCli();
						
						int rxa=0; int eau=0;
						if (checkBoxRXA.isSelected())
							rxa=1;
						if (checkBoxEAU.isSelected())
							eau=1;			
						
						
					VOPerJuridica  personaJuridica = new VOPerJuridica(
								idPersonaFisica, 
								rxa, 
								eau, 
								nombreCliente, 
								fecha, 
								lugarConst, 
								domicilio,
								sede, 
								tel, 
								web, 
								actividad, 
								volIngresos, 
								paisDondeDesarrollaActiv,
								origenFondos, 
								paisEnQueDesarrolaActiv, 
								accionistas, 
								propNaturaleza, 
								bancos,
								refProf, 
								servicioReq, 
								refANuestraFirma);
										
						try {
							controladorCliente.agregarPersonaJuridica(personaJuridica);
							JOptionPane.showMessageDialog(getContentPane(), VentanaException.INGRESO_EXITOSO, "Ingreso exitoso", JOptionPane.PLAIN_MESSAGE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
				

					
						
						
					}
					
				}
				
				
				
			}
		});
		btnSalvarCliJuridico.setBounds(389, 464, 129, 23);
		panel_1.add(btnSalvarCliJuridico);
		
		btnModificarCliente_1 = new JButton("Modificar Cliente");
		btnModificarCliente_1.setEnabled(false);
		
		btnModificarCliente_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String denomSocial = textFieldDenomSocial.getText();
				
				if (dateChooserF1.getCalendar() != null){				
					int anio = dateChooserF1.getCalendar().get(Calendar.YEAR);
					int mes = dateChooserF1.getCalendar().get(Calendar.MONTH) + 1;
					int dia = dateChooserF1.getCalendar().get(Calendar.DAY_OF_MONTH);
					String fecha = "" + dia +"/"+mes+"/"+anio;
				
					String lugarConst = textFieldLugarConst.getText();
					String rut = textFieldNumRut.getText();
					String domicilio = textFieldDomic.getText();
					String sede = textFieldSede.getText();
					String tel = textFieldTel.getText();
					String web = textFieldPagWeb.getText();
					String actividad = textFieldActivPrincip.getText();
					String volIngresos = textFieldVolIngresos.getText();
					String paisDondeDesarrollaActiv = textFieldPaisActivid.getText();
					String origenFondos = textFieldOrigenFondos.getText();
					String paisEnQueDesarrolaActiv = textFieldPaisEnQueDesActividad.getText();
					String accionistas = textAreaAccionistas.getText();
					String propNaturaleza = textAreaPropoRelCom.getText();
					String bancos = textAreaBancos.getText();
					String refProf = textAreaRefProf.getText();
					String servicioReq = textFieldServRequFirma.getText();
					String refANuestraFirma = textFieldRefNuestraFirma.getText();
					
					String nombreCliente = (String) listPerJuridicas.getSelectedValue();				
					ControladorCompliance controladorCliente = new ControladorCompliance();
					
					if (!controladorCliente.existePersonaJuridica(controladorCliente.obtenerVOClienteNombre(nombreCliente).getIdCli())){
						JOptionPane.showMessageDialog(getContentPane(),VentanaException.EXISTE_PERSONA_JURIDICA, "Error en los valores", JOptionPane.ERROR_MESSAGE);
					}else{
						VOCliente cliente = controladorCliente.obtenerVOClienteNombre(nombreCliente);							
						int idPersonaFisica = cliente.getIdCli();
						
						int rxa=0; int eau=0;
						if (checkBoxRXA.isSelected())
							rxa=1;
						if (checkBoxEAU.isSelected())
							eau=1;			
						
						
					VOPerJuridica  personaJuridica = new VOPerJuridica(
								idPersonaFisica, 
								rxa, 
								eau, 
								nombreCliente, 
								fecha, 
								lugarConst, 
								domicilio,
								sede, 
								tel, 
								web, 
								actividad, 
								volIngresos, 
								paisDondeDesarrollaActiv,
								origenFondos, 
								paisEnQueDesarrolaActiv, 
								accionistas, 
								propNaturaleza, 
								bancos,
								refProf, 
								servicioReq, 
								refANuestraFirma);
										
						try {
							controladorCliente.modificarPersonaJuridica(personaJuridica);
							JOptionPane.showMessageDialog(getContentPane(), VentanaException.MODIFICACION_EXITOSA, "Modificacion exitosa", JOptionPane.PLAIN_MESSAGE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
				

					
						
						
					}
					
				}
				
				
				
			}
		});
		btnModificarCliente_1.setBounds(389, 517, 129, 23);
		panel_1.add(btnModificarCliente_1);
		
		dateChooserF1 = new JDateChooser();
		dateChooserF1.setBounds(458, 62, 101, 20);
		panel_1.add(dateChooserF1);
	}
	
	public String[] perFisicasToString() {
		// Retorna un array de clientess en el cual cada tupla esta formada por el nombre
		// Este array luego se le pasa al jlist de clientes
		ControladorCompliance controlador = new ControladorCompliance();		
		
			List<String> clientes = controlador.listarPersonasFisicas();
			String[] tuplas = new String[clientes.size()];
			int i = 0;
			for (String pac : clientes){
				tuplas[i] = pac;
				i++;
			}		
			return tuplas;
		}
	
	public String[] perJuridicasToString() {
		// Retorna un array de clientess en el cual cada tupla esta formada por el nombre
		// Este array luego se le pasa al jlist de clientes
		ControladorCompliance controlador = new ControladorCompliance();		
		
			List<String> clientes = controlador.listarPersonasJuridicas();
			String[] tuplas = new String[clientes.size()];
			int i = 0;
			for (String pac : clientes){
				tuplas[i] = pac;
				i++;
			}		
			return tuplas;
		}
	
	public void filtrarTuplasPersonasFisicas(String [] tuplasPersonasFisicas, String subCadena){
		// Dado un array de tuplas de pacientes y una subcadena, va filtrando los nombres del jList
			boolean hayQueFiltrar = false;
			DefaultListModel modeloSocios = new DefaultListModel();
			for (int i=0; i<tuplasPersonasFisicas.length;i++){			
				if (tuplasPersonasFisicas[i].toUpperCase().startsWith(subCadena.toUpperCase())){
					hayQueFiltrar = true;			
					modeloSocios.addElement(tuplasPersonasFisicas[i]);
				}
			}
			if (hayQueFiltrar){
				listPerFisicas.setModel(modeloSocios);			
			}		
		}
	
	public void filtrarTuplasPersonasJuridicas(String [] tuplasPersonasJuridicas, String subCadena){
		// Dado un array de tuplas de pacientes y una subcadena, va filtrando los nombres del jList
			boolean hayQueFiltrar = false;
			DefaultListModel modeloSocios = new DefaultListModel();
			for (int i=0; i<tuplasPersonasJuridicas.length;i++){			
				if (tuplasPersonasJuridicas[i].toUpperCase().startsWith(subCadena.toUpperCase())){
					hayQueFiltrar = true;			
					modeloSocios.addElement(tuplasPersonasJuridicas[i]);
				}
			}
			if (hayQueFiltrar){
				listPerJuridicas.setModel(modeloSocios);			
			}		
		}
	
	
	public void limpiarPantallaPersonaFisica(){
		textFieldFEmail.setText("");
		textFieldFPais.setText("");
		textFieldFCI.setText("");
		textFieldFLugNac.setText("");
		textFieldFNacionalidad.setText("");
		textFieldFEstCivil.setText("");
		textFieldFProf.setText("");
		textFieldFDesAct.setText("");
		textFieldFNombCony.setText("");
		textFieldFNUmDocCony.setText("");
		textAreaFNegocioAct.setText("");
		textAreaFRelCom.setText("");
		textAreaFOrigFondos.setText("");
		textAreaFBancos.setText("");
		textAreaFServReq.setText("");
		textAreaFTareas.setText("");
		textAreaFRefProf.setText("");
		textAreaFRefFirma.setText("");		
		dateChooserF.setCalendar(null);		
	}
	
	public void limpiarPantallaPersonaJuridica(){
		dateChooserF1.setCalendar(null);
		textFieldLugarConst.setText("");
		textFieldSede.setText("");
		textFieldPagWeb.setText("");
		textFieldActivPrincip.setText("");
		textFieldVolIngresos.setText("");
		textFieldPaisActivid.setText("");
		textFieldOrigenFondos.setText("");
		textFieldPaisEnQueDesActividad.setText("");
		textAreaAccionistas.setText("");
		textAreaPropoRelCom.setText("");
		textAreaBancos.setText("");
		textAreaRefProf.setText("");
		textFieldServRequFirma.setText("");
		textFieldRefNuestraFirma.setText("");		
	}
	
	
	
	
	
	
	
	
	
	public void desplegarPersonaFisica(VOPerFisica persona){
		textFieldFEmail.setText(persona.getEmailPerFis());
				
		comboBoxFCI.removeAllItems();
		if (persona.getTipoDocPerFis().equals("CI")){
			comboBoxFCI.addItem("CI");
			comboBoxFCI.addItem("Pasaporte");
			comboBoxFCI.addItem("DNI");
		}
		
		if (persona.getTipoDocPerFis().equals("Pasaporte")){
			comboBoxFCI.addItem("Pasaporte");
			comboBoxFCI.addItem("DNI");
			comboBoxFCI.addItem("CI");
		}
		
		if (persona.getTipoDocPerFis().equals("DNI")){
			comboBoxFCI.addItem("DNI");
			comboBoxFCI.addItem("CI");
			comboBoxFCI.addItem("Pasaporte");
		}
		
		
		//comboBoxFTipDocCony
		comboBoxFTipDocCony.removeAllItems();
		if (persona.getTipoDocConyPerFis().equals("CI")){
			comboBoxFTipDocCony.addItem("CI");
			comboBoxFTipDocCony.addItem("Pasaporte");
			comboBoxFTipDocCony.addItem("DNI");
		}
		
		if (persona.getTipoDocConyPerFis().equals("Pasaporte")){
			comboBoxFTipDocCony.addItem("Pasaporte");
			comboBoxFTipDocCony.addItem("DNI");
			comboBoxFTipDocCony.addItem("CI");
		}
		
		if (persona.getTipoDocConyPerFis().equals("DNI")){
			comboBoxFTipDocCony.addItem("DNI");
			comboBoxFTipDocCony.addItem("CI");
			comboBoxFTipDocCony.addItem("Pasaporte");
		}		
		
		textFieldFCI.setText(persona.getNumDocPerFis());
		textFieldFPais.setText(persona.getPaisEmisionPerFis());
		textFieldFLugNac.setText(persona.getLugarNacPerFis());
		textFieldFNacionalidad.setText(persona.getNacionalidadPerFis());
		textFieldFEstCivil.setText(persona.getEstCivilPerFis());
		textFieldFProf.setText(persona.getProfesionPerFis());
		textFieldFDesAct.setText(persona.getPaiseDesPerFis());
		textFieldFNombCony.setText(persona.getNomConyuguePerFis());
		// String tipoDocConyugue = (String) comboBoxFTipDocCony.getSelectedItem(); ver como hacer
		textFieldFNUmDocCony.setText(persona.getNumDocConyPerFis());
		textAreaFNegocioAct.setText(persona.getActividPerFis());
		textAreaFRelCom.setText(persona.getNaturalezaTransacPerFis());
		textAreaFOrigFondos.setText(persona.getOrigFondosPerFis());
		textAreaFBancos.setText(persona.getBancosPerFis());
		textAreaFServReq.setText(persona.getServicioReqFirmaPerFis());
		textAreaFTareas.setText(persona.getDesempTarPerFis());
		textAreaFRefProf.setText(persona.getRefPerPerFis());
		textAreaFRefFirma.setText(persona.getRefFirmaPerFis());
		
		String fecha =persona.getFechNacPerFis();
	      
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		      Date fechaDate;
			fechaDate = formato.parse(fecha);
			dateChooserF.setDate(fechaDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if (persona.getRxa() == 1)
			chckbxFRXA.setSelected(true);
		else
			chckbxFRXA.setSelected(false);
		
		if (persona.getEau()==1)
			chckbxFEAU.setSelected(true);
		else
			chckbxFEAU.setSelected(false);
			
	}
	
	public void desplegarPersonaJuridica(VOPerJuridica persona){
		String fecha =persona.getFecha();
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		      Date fechaDate;
			fechaDate = formato.parse(fecha);
			dateChooserF1.setDate(fechaDate);
			//
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		if (persona.getRxa() == 1)
			checkBoxRXA.setSelected(true);
		else
			checkBoxRXA.setSelected(false);
		
		if (persona.getEau()==1)
			checkBoxEAU.setSelected(true);
		else
			checkBoxEAU.setSelected(false);
		
		
		textFieldLugarConst.setText(persona.getLugconst());
		textFieldSede.setText(persona.getSede());
		textFieldPagWeb.setText(persona.getPagweb());
		textFieldActivPrincip.setText(persona.getActivprincip());
		textFieldVolIngresos.setText(persona.getVolingresos());
		textFieldPaisActivid.setText(persona.getActivprincip());
		textFieldOrigenFondos.setText(persona.getOrigenfondos());
		textFieldPaisEnQueDesActividad.setText(persona.getPaisenquedesact());
		textAreaAccionistas.setText(persona.getAccionistas());
		textAreaPropoRelCom.setText(persona.getPropinatrelcom());
		textAreaBancos.setText(persona.getRefbanc());
		textAreaRefProf.setText(persona.getRefprof());
		textFieldServRequFirma.setText(persona.getServfirma());
		textFieldRefNuestraFirma.setText(persona.getRefpornuestrfirm());	
	}
	
	
}
