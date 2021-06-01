package grafica.ventanas;

import grafica.controladores.ControladorAltaServicios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import logicaPersistencia.excepciones.VentanaException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AltaServicios extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldNombreActual;
	private JTextField textFieldNombreNuevo;
	private JList listServicios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AltaServicios frame = new AltaServicios();
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
	public AltaServicios() {
		setTitle("Alta de Servicio");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 652, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AltaServicios.this.dispose();
			}
		});
		btnSalir.setBounds(403, 228, 89, 23);
		contentPane.add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Lista de Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 268, 240);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 248, 208);
		panel.add(scrollPane);
		
		listServicios = new JList();
		listServicios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textFieldNombreActual.setText((String) listServicios.getSelectedValue());
			}
		});
		scrollPane.setViewportView(listServicios);
		
		listarServicios();
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Nuevo Servicio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(288, 11, 343, 82);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 23, 59, 14);
		panel_1.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(91, 20, 242, 20);
		panel_1.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControladorAltaServicios controlador = new ControladorAltaServicios();
				String nombreServicio = textFieldNombre.getText();
				if (!nombreServicio.isEmpty()){					
					try {
						if (controlador.existeServicio(nombreServicio)){
							JOptionPane.showMessageDialog(getContentPane(),VentanaException.EXISTE_SERVICIO, "Error en los valores", JOptionPane.ERROR_MESSAGE);
						}else{
							controlador.nuevoServicio(nombreServicio.toUpperCase());
							listarServicios();
							textFieldNombre.setText("");
							JOptionPane.showMessageDialog(getContentPane(), VentanaException.INGRESO_EXITOSO, "Ingreso exitoso", JOptionPane.PLAIN_MESSAGE);
						}
					} catch (SQLException e) {						
						e.printStackTrace();
					}	
				}else
					JOptionPane.showMessageDialog(getContentPane(),VentanaException.CAMPOS_VACIOS, "Error en los valores", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnNewButton.setBounds(91, 51, 242, 23);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Modificar Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(288, 106, 343, 111);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNombreActual = new JLabel("Nombre actual");
		lblNombreActual.setBounds(13, 24, 96, 14);
		panel_2.add(lblNombreActual);
		
		textFieldNombreActual = new JTextField();
		textFieldNombreActual.setBounds(108, 21, 225, 20);
		panel_2.add(textFieldNombreActual);
		textFieldNombreActual.setColumns(10);
		
		JLabel lblCambiarPor = new JLabel("Cambiar por");
		lblCambiarPor.setBounds(13, 52, 96, 14);
		panel_2.add(lblCambiarPor);
		
		textFieldNombreNuevo = new JTextField();
		textFieldNombreNuevo.setColumns(10);
		textFieldNombreNuevo.setBounds(108, 49, 225, 20);
		panel_2.add(textFieldNombreNuevo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nombre_actual = textFieldNombreActual.getText().trim(); 
				String nombre_nuevo = textFieldNombreNuevo.getText().trim();
				ControladorAltaServicios controlador = new ControladorAltaServicios();
				
				if (!nombre_actual.isEmpty() && !nombre_nuevo.isEmpty()){
					try {
						if (controlador.existeServicio(nombre_actual)){
							if (!controlador.existeServicio(nombre_nuevo)){
								controlador.modificarServicio(nombre_nuevo, nombre_actual);
								listarServicios();
							}else{
								JOptionPane.showMessageDialog(getContentPane(),VentanaException.EXISTE_SERVICIO, "Error en los valores", JOptionPane.ERROR_MESSAGE);
							}
							
						}else{
							JOptionPane.showMessageDialog(getContentPane(),VentanaException.NO_EXISTE_SERVICIO, "Error en los valores", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else
					JOptionPane.showMessageDialog(getContentPane(),VentanaException.CAMPOS_VACIOS, "Error en los valores", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnModificar.setBounds(108, 77, 225, 23);
		panel_2.add(btnModificar);
	}
	
	
	private void listarServicios(){
		DefaultListModel modeloServicios = new DefaultListModel();
		ControladorAltaServicios controlador = new ControladorAltaServicios();
		List<String> lstServicios = controlador.listarServicios();
		if (lstServicios.size() > 0){
			Iterator<String> iterServicios = lstServicios.iterator();
			while(iterServicios.hasNext()){				
				String servicio = iterServicios.next();
				modeloServicios.addElement("  " + servicio);
			}
						
		}
		listServicios.setModel(modeloServicios);
	}
	
	
}
