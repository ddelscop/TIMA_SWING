package grafica.ventanas;

import grafica.controladores.ControladorPerfilesAcceso;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import logicaPersistencia.valueObjects.VOPermisos;

public class PerfilesAcceso extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxGrupo;
	private JCheckBox chckbxPerfilesDeAccesos,chckbxABMFunc,chckbxAltaYModificacin, chckbxAltaYModificacin_1, chckbxIngresoDeHoras, chckbxControlDeFuncionarios, chckbxControlDeClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PerfilesAcceso frame = new PerfilesAcceso();
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
	public PerfilesAcceso() {
		setTitle("Perfiles de Acceso");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGrupo.setBounds(10, 17, 46, 14);
		contentPane.add(lblGrupo);
		
		comboBoxGrupo = new JComboBox();
		
		comboBoxGrupo.setBounds(55, 14, 141, 20);
		contentPane.add(comboBoxGrupo);
		
		comboBoxGrupo.addItem("Administrador");
		comboBoxGrupo.addItem("Usuario Limitado");
		
		comboBoxGrupo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idGrupo=2;
				if (comboBoxGrupo.getSelectedItem() == "Administrador")
					idGrupo = 1;
				
				ControladorPerfilesAcceso controlador = new ControladorPerfilesAcceso();
				VOPermisos voPermisos = controlador.obtenerPermisos(idGrupo);
				
				int _chckbxPerfilesDeAccesos = voPermisos.getPerfAcceso();
				int _chckbxABMFunc = voPermisos.getAbmFunc();
				int _chckbxAltaYModificacin = voPermisos.getAbmCli();
				int _chckbxAltaYModificacin_1 = voPermisos.getAbmServ();
				int _chckbxIngresoDeHoras = voPermisos.getIngresoHoras();
				int _chckbxControlDeFuncionarios = voPermisos.getControlFunc();
				
				
				if (_chckbxPerfilesDeAccesos==1)
					chckbxPerfilesDeAccesos.setSelected(true);
				else
					chckbxPerfilesDeAccesos.setSelected(false);
				
				if (_chckbxABMFunc ==1)
					chckbxABMFunc.setSelected(true);
				else
					chckbxABMFunc.setSelected(false);
				
				if (_chckbxAltaYModificacin ==1)
					chckbxAltaYModificacin.setSelected(true);
				else
					chckbxAltaYModificacin.setSelected(false);		
				
				if (_chckbxAltaYModificacin_1 ==1)
					chckbxAltaYModificacin_1.setSelected(true);
				else
					chckbxAltaYModificacin_1.setSelected(false);
				
				
				if (_chckbxIngresoDeHoras==1)
					chckbxIngresoDeHoras.setSelected(true);
				else
					chckbxIngresoDeHoras.setSelected(false);
				
				if (_chckbxControlDeFuncionarios==1)
					chckbxControlDeFuncionarios.setSelected(true);
				else
					chckbxControlDeFuncionarios.setSelected(false);
			}
		});
		/*comboBoxGrupo.setBounds(55, 14, 141, 20);
		contentPane.add(comboBoxGrupo);
		
		comboBoxGrupo.addItem("Administrador");
		comboBoxGrupo.addItem("Usuario Limitado");
		*/
		JButton btnNewButton = new JButton("Guardar cambios");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int _chckbxPerfilesDeAccesos = 0;
				int _chckbxABMFunc = 0;
				int _chckbxAltaYModificacin = 0;
				int _chckbxAltaYModificacin_1 = 0;
				int _chckbxIngresoDeHoras = 0;
				int _chckbxControlDeFuncionarios = 0;
				int _chckbxControlDeClientes = 0;
				
				if (chckbxPerfilesDeAccesos.isSelected())
					_chckbxPerfilesDeAccesos = 1;
				if (chckbxABMFunc.isSelected())
					_chckbxABMFunc = 1;
				if (chckbxAltaYModificacin.isSelected())
					_chckbxAltaYModificacin = 1;
				if (chckbxAltaYModificacin_1.isSelected())
					_chckbxAltaYModificacin_1 = 1;
				if (chckbxIngresoDeHoras.isSelected())
					_chckbxIngresoDeHoras = 1;
				if (chckbxControlDeFuncionarios.isSelected())
					_chckbxControlDeFuncionarios = 1;
				
				if (chckbxControlDeClientes.isSelected())
					_chckbxControlDeClientes = 1;
				
				int idGrupo=2;
				if (comboBoxGrupo.getSelectedItem() == "Administrador")
					idGrupo = 1;
				
				ControladorPerfilesAcceso controlador = new ControladorPerfilesAcceso();
				try {
					controlador.actualizarPermisos(_chckbxPerfilesDeAccesos, _chckbxABMFunc, _chckbxAltaYModificacin, _chckbxAltaYModificacin_1, _chckbxIngresoDeHoras, _chckbxControlDeFuncionarios, _chckbxControlDeClientes, idGrupo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(238, 13, 133, 23);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Permisos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 53, 414, 245);
		contentPane.add(panel);
		panel.setLayout(null);
		
		chckbxPerfilesDeAccesos = new JCheckBox("Perfiles de Acceso");
		chckbxPerfilesDeAccesos.setBounds(21, 25, 187, 23);
		panel.add(chckbxPerfilesDeAccesos);
		
		chckbxABMFunc = new JCheckBox("Alta y Modificaci\u00F3n de Funcionarios");
		chckbxABMFunc.setBounds(21, 61, 256, 23);
		panel.add(chckbxABMFunc);
		
		chckbxAltaYModificacin = new JCheckBox("Alta y Modificaci\u00F3n de Clientes");
		chckbxAltaYModificacin.setBounds(21, 98, 213, 23);
		panel.add(chckbxAltaYModificacin);
		
		chckbxAltaYModificacin_1 = new JCheckBox("Alta y Modificaci\u00F3n de Servicios");
		chckbxAltaYModificacin_1.setBounds(21, 137, 213, 23);
		panel.add(chckbxAltaYModificacin_1);
		
		chckbxIngresoDeHoras = new JCheckBox("Ingreso de Horas");
		chckbxIngresoDeHoras.setBounds(21, 177, 213, 23);
		panel.add(chckbxIngresoDeHoras);
		
		chckbxControlDeFuncionarios = new JCheckBox("Control de Funcionarios");
		chckbxControlDeFuncionarios.setBounds(21, 216, 213, 23);
		panel.add(chckbxControlDeFuncionarios);
		
		chckbxControlDeClientes = new JCheckBox("Control de Clientes");
		chckbxControlDeClientes.setBounds(259, 216, 132, 23);
		panel.add(chckbxControlDeClientes);
		
		int idGrupo=2;
		if (comboBoxGrupo.getSelectedItem() == "Administrador")
			idGrupo = 1;
		
		ControladorPerfilesAcceso controlador = new ControladorPerfilesAcceso();
		VOPermisos voPermisos = controlador.obtenerPermisos(idGrupo);
		
		int _chckbxPerfilesDeAccesos = voPermisos.getPerfAcceso();
		int _chckbxABMFunc = voPermisos.getAbmFunc();
		int _chckbxAltaYModificacin = voPermisos.getAbmCli();
		int _chckbxAltaYModificacin_1 = voPermisos.getAbmServ();
		int _chckbxIngresoDeHoras = voPermisos.getIngresoHoras();
		int _chckbxControlDeFuncionarios = voPermisos.getControlFunc();
		int _chckbxControlDeClientes = voPermisos.getControlCli();
		
		
		if (_chckbxPerfilesDeAccesos==1)
			chckbxPerfilesDeAccesos.setSelected(true);
		else
			chckbxPerfilesDeAccesos.setSelected(false);
		
		if (_chckbxABMFunc ==1)
			chckbxABMFunc.setSelected(true);
		else
			chckbxABMFunc.setSelected(false);
		
		if (_chckbxAltaYModificacin ==1)
			chckbxAltaYModificacin.setSelected(true);
		else
			chckbxAltaYModificacin.setSelected(false);		
		
		if (_chckbxAltaYModificacin_1 ==1)
			chckbxAltaYModificacin_1.setSelected(true);
		else
			chckbxAltaYModificacin_1.setSelected(false);
		
		
		if (_chckbxIngresoDeHoras==1)
			chckbxIngresoDeHoras.setSelected(true);
		else
			chckbxIngresoDeHoras.setSelected(false);
		
		if (_chckbxControlDeFuncionarios==1)
			chckbxControlDeFuncionarios.setSelected(true);
		else
			chckbxControlDeFuncionarios.setSelected(false);
		
		if (_chckbxControlDeClientes==1)
			chckbxControlDeClientes.setSelected(true);
		else
			chckbxControlDeClientes.setSelected(false);
		
	}
}
