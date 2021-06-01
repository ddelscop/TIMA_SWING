package grafica.ventanas;

import grafica.controladores.ControladorAltaFuncionarios;
import grafica.controladores.ControladorPerfilesDeAcceso;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import logicaPersistencia.excepciones.VentanaException;
import logicaPersistencia.valueObjects.VOPermisos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;

public class PerfilesDeAcceso extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldGrupo;
	private JTextField textFieldGrupoBorrar;
	private JList listGrupos;
	private DefaultListModel modeloGrupos;
	private JComboBox comboBoxGrupo;
	private JCheckBox checkBox_perfAcceso, checkBox_AbmFunc, checkBox_AbmCli, checkBox_AbmServ, checkBox_IngresoHoras, checkBox_controlFunc, checkBox_controlCli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilesDeAcceso frame = new PerfilesDeAcceso();
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
	public PerfilesDeAcceso() {
		setTitle("Perfiles de acceso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 578, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		
		tabbedPane.setBounds(10, 11, 542, 338);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Grupo", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Lista de grupos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 11, 199, 289);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 179, 252);
		panel_2.add(scrollPane);
		
		listGrupos = new JList();
		listGrupos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String grupo = (String) listGrupos.getSelectedValue();
				textFieldGrupoBorrar.setText(grupo);
			}
		});
		scrollPane.setViewportView(listGrupos);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Crear grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(229, 11, 275, 99);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 27, 72, 14);
		panel_3.add(lblNombre);
		
		textFieldGrupo = new JTextField();
		textFieldGrupo.setBounds(92, 24, 173, 20);
		panel_3.add(textFieldGrupo);
		textFieldGrupo.setColumns(10);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorPerfilesDeAcceso controlador = new ControladorPerfilesDeAcceso();
				String nombreGrupo = textFieldGrupo.getText();
				try {
					if (!controlador.existeGrupo(nombreGrupo)){						
						controlador.nuevoGrupo(nombreGrupo);						
						listarGrupos();					
						
					}else
						JOptionPane.showMessageDialog(getContentPane(),VentanaException.EXISTE_GRUPO, "Error en los valores", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(92, 66, 89, 23);
		panel_3.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Eliminar grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(235, 132, 269, 110);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel label = new JLabel("Nombre");
		label.setBounds(10, 37, 72, 14);
		panel_4.add(label);
		
		textFieldGrupoBorrar = new JTextField();
		textFieldGrupoBorrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldGrupoBorrar.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldGrupoBorrar.setEditable(false);
		textFieldGrupoBorrar.setColumns(10);
		textFieldGrupoBorrar.setBounds(92, 34, 167, 20);
		panel_4.add(textFieldGrupoBorrar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (!textFieldGrupoBorrar.getText().isEmpty()){
					String grupo = textFieldGrupoBorrar.getText();
					ControladorPerfilesDeAcceso controlador = new ControladorPerfilesDeAcceso();
					try {
						controlador.eliminarGrupo(grupo);
						listarGrupos();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnEliminar.setBounds(92, 76, 89, 23);
		panel_4.add(btnEliminar);
		
		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerfilesDeAcceso.this.dispose();
			}
		});
		btnNewButton_1.setBounds(415, 265, 89, 23);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		
		tabbedPane.addTab("Permisos", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setBounds(21, 11, 46, 14);
		panel_1.add(lblGrupo);
		
		comboBoxGrupo = new JComboBox();
		listarComboGrupos();
		comboBoxGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String grupo = (String) comboBoxGrupo.getSelectedItem();
				ControladorPerfilesDeAcceso controlador = new ControladorPerfilesDeAcceso();
				VOPermisos permisos = controlador.obtenerPermisosGrupo(grupo);				
				  checkBox_perfAcceso.setSelected(false);
				  checkBox_AbmFunc.setSelected(false);				  
				  checkBox_AbmCli.setSelected(false);				  
				  checkBox_AbmServ.setSelected(false);				  
				  checkBox_IngresoHoras.setSelected(false);				  
				  checkBox_controlFunc.setSelected(false);				  
				  checkBox_controlCli.setSelected(false);
				  
				  if (permisos.getPerfAcceso() == 1)
					  checkBox_perfAcceso.setSelected(true);
				  if (permisos.getAbmFunc() == 1)
					  checkBox_AbmFunc.setSelected(true);
				  if (permisos.getAbmCli() == 1)
					  checkBox_AbmCli.setSelected(true);
				  if (permisos.getAbmServ() == 1)
					  checkBox_AbmServ.setSelected(true);
				  if (permisos.getIngresoHoras() == 1)
					  checkBox_IngresoHoras.setSelected(true);
				  if (permisos.getControlFunc() == 1)
					  checkBox_controlFunc.setSelected(true);
				  if (permisos.getControlCli() == 1)
					  checkBox_controlCli.setSelected(true);
				
			}
		});
		comboBoxGrupo.setBounds(77, 8, 168, 20);
		panel_1.add(comboBoxGrupo);
		
		
		
		JButton btnNewButton_2 = new JButton("Guardar cambios");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorPerfilesDeAcceso controlador = new ControladorPerfilesDeAcceso();
				String grupo = (String) comboBoxGrupo.getSelectedItem();
				int perfAcceso=0;
				int abmFunc=0;
				int abmCli=0;
				int abmServ=0;
				int ingresoHoras=0;
				int controlFunc=0;
				int controlCli=0;
				
				if (checkBox_perfAcceso.isSelected())
					perfAcceso=1;
				if (checkBox_AbmFunc.isSelected())
					abmFunc=1;
				if (checkBox_AbmCli.isSelected())
					abmCli=1;			  
				if (checkBox_AbmServ.isSelected())
					abmServ=1;
				if (checkBox_IngresoHoras.isSelected())
					ingresoHoras=1;
				if (checkBox_controlFunc.isSelected())
					controlFunc=1;
				if (checkBox_controlCli.isSelected())
					controlCli=1;		
				
				
				try {
					
					controlador.actualizarPermisosGrupo(perfAcceso, abmFunc, abmCli, abmServ, ingresoHoras, controlFunc, controlCli, grupo);
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnNewButton_2.setBounds(347, 7, 180, 23);
		panel_1.add(btnNewButton_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Opciones de acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(21, 47, 506, 252);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		checkBox_perfAcceso = new JCheckBox("Perfiles de Acceso");
		checkBox_perfAcceso.setBounds(19, 19, 187, 23);
		panel_5.add(checkBox_perfAcceso);
		
		checkBox_AbmFunc = new JCheckBox("Alta y Modificaci\u00F3n de Funcionarios");
		checkBox_AbmFunc.setBounds(19, 53, 256, 23);
		panel_5.add(checkBox_AbmFunc);
		
		checkBox_AbmCli = new JCheckBox("Alta y Modificaci\u00F3n de Clientes");
		checkBox_AbmCli.setBounds(19, 87, 213, 23);
		panel_5.add(checkBox_AbmCli);
		
		checkBox_AbmServ = new JCheckBox("Alta y Modificaci\u00F3n de Servicios");
		checkBox_AbmServ.setBounds(19, 121, 213, 23);
		panel_5.add(checkBox_AbmServ);
		
		checkBox_IngresoHoras = new JCheckBox("Ingreso de Horas");
		checkBox_IngresoHoras.setBounds(19, 155, 213, 23);
		panel_5.add(checkBox_IngresoHoras);
		
		checkBox_controlFunc = new JCheckBox("Control de Funcionarios");
		checkBox_controlFunc.setBounds(19, 189, 213, 23);
		panel_5.add(checkBox_controlFunc);
		
		checkBox_controlCli = new JCheckBox("Control de Clientes");
		checkBox_controlCli.setBounds(19, 223, 132, 23);
		panel_5.add(checkBox_controlCli);
		
		modeloGrupos = new DefaultListModel();
		listarGrupos();
	}
	
	
	public void listarGrupos(){
		//Cargo lista de actividades del socio				
				//modeloGrupos = new DefaultListModel();				
				this.modeloGrupos.clear();
				ControladorPerfilesDeAcceso controlador = new ControladorPerfilesDeAcceso();				
				List <String> lstGrupos = controlador.listarGrupNom();				
				
				if (lstGrupos.size() > 0){
					Iterator<String> iterGrupos = lstGrupos.iterator();
					//comboBoxGrupo.removeAllItems();
					//comboBoxGrupo.removeAll();
					while(iterGrupos.hasNext()){				
						String dataGrupo = iterGrupos.next();
						//comboBoxGrupo.addItem(dataGrupo);
						modeloGrupos.addElement(dataGrupo);
					}					
					listGrupos.setModel(modeloGrupos);
				}
	}
	
	public void listarComboGrupos(){
		//Cargo lista de actividades del socio				
				//modeloGrupos = new DefaultListModel();				
		ControladorAltaFuncionarios controlador = new ControladorAltaFuncionarios();
		List <String> lstGrupos = controlador.listarGrupNom();
		if (lstGrupos.size() > 0){
			Iterator<String> iterGrupos = lstGrupos.iterator();
			comboBoxGrupo.removeAllItems();				
					while(iterGrupos.hasNext()){				
						String dataGrupo = iterGrupos.next();
						comboBoxGrupo.addItem(dataGrupo);
					}					
			}
	}
	
	
	
}
