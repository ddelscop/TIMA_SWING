package grafica.ventanas;

import grafica.controladores.ControladorCambioContrasenia;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import logicaPersistencia.valueObjects.VOFuncionario;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambioContrasenia extends JFrame {

	private JPanel contentPane;
	private static String ciFuncionario;
	private JTextField textFieldFuncionario;
	private JLabel lblNewLabel;
	private JTextField textFieldActual;
	private JLabel lblContraseaNueva;
	private JTextField textFieldNueva;
	private JLabel lblReingreseContrasea;
	private JTextField textFieldReNueva;
	private JLabel labelContActual, labelReCont;
	private JButton btnNewButton_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CambioContrasenia frame = new CambioContrasenia(ciFuncionario);
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
	public CambioContrasenia(final String ciFuncionario) {
		setTitle("Cambio de contrase\u00F1a");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 386, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFuncionario = new JLabel("Funcionario");
		lblFuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFuncionario.setBounds(23, 25, 84, 14);
		contentPane.add(lblFuncionario);
		
		textFieldFuncionario = new JTextField();
		textFieldFuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldFuncionario.setBackground(new Color(204, 255, 204));
		textFieldFuncionario.setEditable(false);
		textFieldFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFuncionario.setBounds(117, 22, 240, 20);
		contentPane.add(textFieldFuncionario);
		textFieldFuncionario.setColumns(10);
		
		int idFunc;
		String nombreFuncionario = "";
		
		ControladorCambioContrasenia controlador = new ControladorCambioContrasenia();		
		try {			
			idFunc = controlador.obtenerIdFuncionarioCI(ciFuncionario);
			VOFuncionario voFunc = controlador.obtenerVOFuncionario(idFunc);
			textFieldFuncionario.setText(voFunc.getNomFun() + " " + voFunc.getApeFun());
			
			lblNewLabel = new JLabel("Contrase\u00F1a actual");
			lblNewLabel.setBounds(23, 78, 108, 14);
			contentPane.add(lblNewLabel);
			
			textFieldActual = new JTextField();
			textFieldActual.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldActual.setBackground(new Color(255, 255, 204));
			textFieldActual.setBounds(151, 75, 173, 20);
			contentPane.add(textFieldActual);
			textFieldActual.setColumns(10);
			
			lblContraseaNueva = new JLabel("Contrase\u00F1a nueva");
			lblContraseaNueva.setBounds(23, 118, 108, 14);
			contentPane.add(lblContraseaNueva);
			
			textFieldNueva = new JTextField();
			textFieldNueva.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldNueva.setBackground(new Color(255, 204, 153));
			textFieldNueva.setColumns(10);
			textFieldNueva.setBounds(151, 115, 173, 20);
			contentPane.add(textFieldNueva);
			
			lblReingreseContrasea = new JLabel("Reingrese contrase\u00F1a");
			lblReingreseContrasea.setBounds(23, 159, 130, 14);
			contentPane.add(lblReingreseContrasea);
			
			textFieldReNueva = new JTextField();
			textFieldReNueva.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldReNueva.setBackground(new Color(255, 204, 153));
			textFieldReNueva.setColumns(10);
			textFieldReNueva.setBounds(151, 156, 173, 20);
			contentPane.add(textFieldReNueva);
			
			labelContActual = new JLabel("");
			JButton btnNewButton = new JButton("Aceptar");
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ControladorCambioContrasenia controlador = new ControladorCambioContrasenia();	
					int idFunc;
					try {
						idFunc = controlador.obtenerIdFuncionarioCI(ciFuncionario);
						VOFuncionario voFunc = controlador.obtenerVOFuncionario(idFunc);
						
						if(!textFieldActual.getText().equals(voFunc.getPass())){
							labelContActual.setVisible(true);
							labelContActual.setToolTipText("Contraseña incorrecta.");
						}else{
							labelContActual.setVisible(false);
							if (!textFieldNueva.getText().equals(textFieldReNueva.getText())){
								labelReCont.setVisible(true);
								labelReCont.setToolTipText("Las contraseñas no coinciden.");
							}else{
								labelReCont.setVisible(false);
								controlador.modificarContrasenia(idFunc, textFieldReNueva.getText());
							}						
							
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
				}
			});
			btnNewButton.setBounds(23, 201, 138, 23);
			contentPane.add(btnNewButton);
			
			//JLabel labelContActual = new JLabel("");
			labelContActual.setIcon(new ImageIcon(CambioContrasenia.class.getResource("/grafica/imagenes/Error.png")));
			labelContActual.setBounds(331, 75, 20, 20);
			contentPane.add(labelContActual);
			labelContActual.setVisible(false);
			
			labelReCont = new JLabel("");
			labelReCont.setIcon(new ImageIcon(CambioContrasenia.class.getResource("/grafica/imagenes/Error.png")));
			labelReCont.setBounds(334, 156, 20, 20);
			contentPane.add(labelReCont);
			
			btnNewButton_1 = new JButton("Salir");
			btnNewButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CambioContrasenia.this.dispose();
				}
			});
			btnNewButton_1.setBounds(262, 201, 89, 23);
			contentPane.add(btnNewButton_1);
			labelReCont.setVisible(false);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
