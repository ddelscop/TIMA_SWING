package grafica.ventanas;

import grafica.controladores.ControladorAltaFuncionarios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.WindowConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logicaPersistencia.excepciones.VentanaException;
import logicaPersistencia.valueObjects.VOFuncionario;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class AltaFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldCI;
	private JTextField textFieldFechNac;
	private JTextField textFieldCEL;
	private JList listActivos, listBajas;
	private JTextField textFieldPass;
	private JTextField textFieldHorasDia;
	private JComboBox comboBoxPermisos;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AltaFuncionarios frame = new AltaFuncionarios();
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
	public AltaFuncionarios() {
		setTitle("Mantenimiento de Funcionarios");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 679, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		lblNewLabel.setBounds(307, 11, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Verdana", Font.BOLD, 11));
		lblApellido.setBounds(493, 11, 76, 14);
		contentPane.add(lblApellido);
		
		JLabel lblCi = new JLabel("CI");
		lblCi.setFont(new Font("Verdana", Font.BOLD, 11));
		lblCi.setBounds(307, 64, 76, 14);
		contentPane.add(lblCi);
		
		JLabel lblFechNac = new JLabel("Fech. Nac.");
		lblFechNac.setFont(new Font("Verdana", Font.BOLD, 11));
		lblFechNac.setBounds(493, 64, 76, 14);
		contentPane.add(lblFechNac);
		
		JLabel lblTelcel = new JLabel("Tel./Cel.");
		lblTelcel.setFont(new Font("Verdana", Font.BOLD, 11));
		lblTelcel.setBounds(307, 123, 76, 14);
		contentPane.add(lblTelcel);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(307, 30, 163, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(493, 30, 163, 20);
		contentPane.add(textFieldApellido);
		
		textFieldCI = new JTextField();
		textFieldCI.setColumns(10);
		textFieldCI.setBounds(307, 82, 163, 20);
		contentPane.add(textFieldCI);
		
		textFieldFechNac = new JTextField();
		textFieldFechNac.setColumns(10);
		textFieldFechNac.setBounds(493, 82, 86, 20);
		contentPane.add(textFieldFechNac);
		
		textFieldCEL = new JTextField();
		textFieldCEL.setColumns(10);
		textFieldCEL.setBounds(369, 121, 103, 20);
		contentPane.add(textFieldCEL);
		
		JButton btnAceptar = new JButton("Alta");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomFun = textFieldNombre.getText();
				String apeFun = textFieldApellido.getText();
				String ciFun = textFieldCI.getText();
				String FechNacFun = textFieldFechNac.getText();
				String celFun = textFieldCEL.getText();
				String pass = textFieldPass.getText();
				int horasDia = Integer.parseInt(textFieldHorasDia.getText());
				
				String funcBaja = (String) listBajas.getSelectedValue();
				String grupo = (String) comboBoxPermisos.getSelectedItem();
				
				
				if(funcBaja != null){
					ControladorAltaFuncionarios controlador = new ControladorAltaFuncionarios();					
					try {
						controlador.altaLogicaFuncionarioCI(ciFun);
						listarFuncionarios(0);
						listarFuncionarios(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{					
					ControladorAltaFuncionarios controlador = new ControladorAltaFuncionarios();
					try {
						if (nomFun.isEmpty() || apeFun.isEmpty() || ciFun.isEmpty() || FechNacFun.isEmpty() || celFun.isEmpty()){
							JOptionPane.showMessageDialog(getContentPane(),VentanaException.CAMPOS_VACIOS, "Error en los valores", JOptionPane.ERROR_MESSAGE);
						}else
							if (!controlador.existeCedulaFuncionario(ciFun)){
								int idGrupo = controlador.obtenerIdGrupo(grupo);
								
								controlador.nuevoFuncionario(nomFun, apeFun, ciFun, FechNacFun, celFun, idGrupo, pass, horasDia);
								listarFuncionarios(0);
							}else
									JOptionPane.showMessageDialog(getContentPane(),VentanaException.EXISTE_CEDULA, "Error en los valores", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
				
			}
		});
		btnAceptar.setBounds(310, 277, 76, 23);
		contentPane.add(btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AltaFuncionarios.this.dispose();
			}
		});
		btnSalir.setBounds(580, 277, 76, 23);
		contentPane.add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Activos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 287, 151);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 267, 118);
		panel.add(scrollPane);
		
		listActivos = new JList();
		listActivos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ControladorAltaFuncionarios controlador = new ControladorAltaFuncionarios();
				
				String nomApe = ((String) listActivos.getSelectedValue()).trim();
				try {
					int idFun = controlador.obtenerIdFunNomApe(nomApe);
					VOFuncionario voFunc = controlador.obtenerVOFuncionario(idFun);
					
					textFieldNombre.setText(voFunc.getNomFun());
					textFieldApellido.setText(voFunc.getApeFun());
					textFieldCI.setText(voFunc.getCiFun());
					textFieldFechNac.setText(voFunc.getFechNacFun());
					textFieldCEL.setText(voFunc.getCelFun());
					textFieldHorasDia.setText(""+ voFunc.getHorasDia());
					
					int idGrupo = voFunc.getidGrupo();
					String nombreGrupo = controlador.obtenerNombreIdGrupo(idGrupo);					
					
					List <String> lstGrupos = controlador.listarGrupNom();
					if (lstGrupos.size() > 0){
						Iterator<String> iterGrupos = lstGrupos.iterator();
							comboBoxPermisos.removeAllItems();
							comboBoxPermisos.addItem(nombreGrupo);
								while(iterGrupos.hasNext()){				
									String dataGrupo = iterGrupos.next();
									if (!dataGrupo.equals(nombreGrupo))
										comboBoxPermisos.addItem(dataGrupo);
								}					
						}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(listActivos);
		
		listarFuncionarios(0);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bajas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 173, 287, 142);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 267, 109);
		panel_1.add(scrollPane_1);
		
		listBajas = new JList();
		listBajas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ControladorAltaFuncionarios controlador = new ControladorAltaFuncionarios();
				
				String nomApe = ((String) listBajas.getSelectedValue()).trim();
				try {
					int idFun = controlador.obtenerIdFunNomApe(nomApe);
					VOFuncionario voFunc = controlador.obtenerVOFuncionario(idFun);
					
					textFieldNombre.setText(voFunc.getNomFun());
					textFieldApellido.setText(voFunc.getApeFun());
					textFieldCI.setText(voFunc.getCiFun());
					textFieldFechNac.setText(voFunc.getFechNacFun());
					textFieldCEL.setText(voFunc.getCelFun());
					
					int idGrupo = voFunc.getidGrupo();
					String nombreGrupo = controlador.obtenerNombreIdGrupo(idGrupo);					
					
					List <String> lstGrupos = controlador.listarGrupNom();
					if (lstGrupos.size() > 0){
						Iterator<String> iterGrupos = lstGrupos.iterator();
							comboBoxPermisos.removeAllItems();
							comboBoxPermisos.addItem(nombreGrupo);
								while(iterGrupos.hasNext()){				
									String dataGrupo = iterGrupos.next();
									if (!dataGrupo.equals(nombreGrupo))
										comboBoxPermisos.addItem(dataGrupo);
								}					
						}
					
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		scrollPane_1.setViewportView(listBajas);
		
		listarFuncionarios(1);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ci = textFieldCI.getText();
				if (ci.isEmpty()){
					JOptionPane.showMessageDialog(getContentPane(),VentanaException.SELECCIONAR_FUNCIONARIO, "Error en los valores", JOptionPane.ERROR_MESSAGE);
				}else{
					ControladorAltaFuncionarios controlador = new ControladorAltaFuncionarios();
					try {
						controlador.bajaFuncionarioCI(ci);
						listarFuncionarios(1);
						listarFuncionarios(0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnBaja.setBounds(396, 277, 76, 23);
		contentPane.add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ci =textFieldCI.getText();
				String nomFun = textFieldNombre.getText();
				String apeFun = textFieldApellido.getText();
				String FechNacFun = textFieldFechNac.getText();
				String celFun = textFieldCEL.getText();
				String pass = textFieldPass.getText();
				int horasDia = Integer.parseInt(textFieldHorasDia.getText());
				String grupo = (String) comboBoxPermisos.getSelectedItem();
				
				ControladorAltaFuncionarios controlador = new ControladorAltaFuncionarios();
				try {
					int idFun = controlador.obtenerIdFuncionarioCI(ci);					
					int idGrupo = controlador.obtenerIdGrupo(grupo);
					controlador.modificarFuncionario(nomFun, apeFun, ci, celFun, 0, idGrupo, pass, horasDia, idFun);
					
					
					JOptionPane.showMessageDialog(getContentPane(), VentanaException.INGRESO_EXITOSO, "Datos modificados con éxito", JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnModificar.setBounds(476, 277, 93, 23);
		contentPane.add(btnModificar);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Verdana", Font.BOLD, 11));
		lblContrasea.setBounds(493, 125, 76, 14);
		contentPane.add(lblContrasea);
		
		textFieldPass = new JTextField();
		textFieldPass.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPass.setText("1234");
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(588, 123, 68, 20);
		contentPane.add(textFieldPass);
		
		JLabel lblNewLabel_1 = new JLabel("dd/mm/yyyy");
		lblNewLabel_1.setBounds(589, 85, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Borrar Datos");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldNombre.setText("");
				textFieldApellido.setText("");
				textFieldCI.setText("");
				textFieldFechNac.setText("");
				textFieldCEL.setText("");
				
				
			}
		});
		btnNewButton.setBounds(493, 221, 147, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblHorasPorDa = new JLabel("Horas por d\u00EDa");
		lblHorasPorDa.setFont(new Font("Verdana", Font.BOLD, 11));
		lblHorasPorDa.setBounds(307, 173, 103, 14);
		contentPane.add(lblHorasPorDa);
		
		textFieldHorasDia = new JTextField();
		textFieldHorasDia.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldHorasDia.setColumns(10);
		textFieldHorasDia.setBounds(406, 170, 64, 20);
		contentPane.add(textFieldHorasDia);
		
		comboBoxPermisos = new JComboBox();
		comboBoxPermisos.setBounds(483, 171, 157, 19);
		contentPane.add(comboBoxPermisos);
		
		listarGrupos();
	}
	
	private void listarFuncionarios(int i){
		DefaultListModel modeloServicios = new DefaultListModel();
		ControladorAltaFuncionarios controlador = new ControladorAltaFuncionarios();
		List<String> lstServicios = controlador.listarFuncionarios(i);
		if (lstServicios.size() > 0){
			java.util.Iterator<String> iterServicios = lstServicios.iterator();
			while(iterServicios.hasNext()){				
				String servicio = iterServicios.next();
				modeloServicios.addElement("  " + servicio);
			}						
		}
		if (i == 0){
			listActivos.setModel(modeloServicios);
		}else
			listBajas.setModel(modeloServicios);
	}
	
	public void listarGrupos(){
		//Cargo lista de actividades del socio				
				//modeloGrupos = new DefaultListModel();				
		ControladorAltaFuncionarios controlador = new ControladorAltaFuncionarios();
		List <String> lstGrupos = controlador.listarGrupNom();
		if (lstGrupos.size() > 0){
			Iterator<String> iterGrupos = lstGrupos.iterator();
				comboBoxPermisos.removeAllItems();				
					while(iterGrupos.hasNext()){				
						String dataGrupo = iterGrupos.next();
						comboBoxPermisos.addItem(dataGrupo);
					}					
			}
	}
}
