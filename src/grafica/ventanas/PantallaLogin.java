package grafica.ventanas;

import grafica.controladores.ControladorPantallaLogin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logicaPersistencia.excepciones.VentanaException;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class PantallaLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField textFieldPass;
	private JTextField textFieldUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PantallaLogin frame = new PantallaLogin();
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
	public PantallaLogin() {
		setTitle("Ingreso al Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PantallaLogin.class.getResource("/grafica/imagenes/logo2.1_mini.png")));
		label.setBounds(10, 11, 200, 132);
		contentPane.add(label);
		
		JButton button = new JButton("Aceptar");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ci = textFieldUsuario.getText();
				String pass = textFieldPass.getText();
				
				ControladorPantallaLogin controlador = new ControladorPantallaLogin();
				if (controlador.validarLogin(ci, pass)){
					//System.out.println("login correcto");
					setVisible(false);
					PantallaPPAL ventanaPPAL = new PantallaPPAL(ci);
					ventanaPPAL.setVisible(true);
				}else
					JOptionPane.showMessageDialog(getContentPane(), VentanaException.LOGIN_INCORRECTO, "Login incorrecto", JOptionPane.ERROR_MESSAGE);				
			}
		});
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setBounds(217, 104, 92, 23);
		contentPane.add(button);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
		btnSalir.setBounds(327, 104, 94, 23);
		contentPane.add(btnSalir);
		
		textFieldPass = new JPasswordField();
		textFieldPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ci = textFieldUsuario.getText();
				String pass = textFieldPass.getText();
				
				ControladorPantallaLogin controlador = new ControladorPantallaLogin();
				if (controlador.validarLogin(ci, pass)){
					setVisible(false);
					PantallaPPAL ventanaPPAL = new PantallaPPAL(ci);
					ventanaPPAL.setVisible(true);
				}else
					JOptionPane.showMessageDialog(getContentPane(), VentanaException.LOGIN_INCORRECTO, "Login incorrecto", JOptionPane.ERROR_MESSAGE);				
			}			
		});
		
		
		textFieldPass.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPass.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldPass.setBackground(new Color(240, 255, 240));
		textFieldPass.setBounds(313, 66, 115, 20);
		contentPane.add(textFieldPass);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setBackground(new Color(240, 255, 240));
		textFieldUsuario.setBounds(313, 35, 115, 20);
		contentPane.add(textFieldUsuario);
		
		JLabel lblUsuarioci = new JLabel("C\u00E9dula");
		lblUsuarioci.setFont(new Font("Arial", Font.BOLD, 14));
		lblUsuarioci.setBounds(219, 38, 84, 14);
		contentPane.add(lblUsuarioci);
		
		JLabel label_2 = new JLabel("Contrase\u00F1a");
		label_2.setFont(new Font("Arial", Font.BOLD, 14));
		label_2.setBounds(217, 66, 92, 14);
		contentPane.add(label_2);
	}
}
