package grafica.ventanas;

import grafica.controladores.ControladorMantemimientoHorasNew;
import grafica.controladores.ControladorPantallaPPAL;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import logicaPersistencia.valueObjects.VOFuncionario;
import logicaPersistencia.valueObjects.VOPermisos;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PantallaPPAL extends JFrame {

	private JPanel contentPane;
	private static String ciFuncionario;
	private JMenuItem mntmNewMenuItem, mntmNewMenuItemControlFunc, mntmNewMenuItemClientes;
	private JMenu mnNewMenuFuncionarios, mnNewMenuClientes, mnServicios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PantallaPPAL frame = new PantallaPPAL(ciFuncionario);
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
	public PantallaPPAL(final String ciFuncionario) {
		ControladorPantallaPPAL controlador = new ControladorPantallaPPAL();
		int idFunc;
		String nombreFuncionario = "";
		
		mntmNewMenuItem = new JMenuItem("Perfiles de acceso");
		mnNewMenuFuncionarios = new JMenu("Funcionarios");
		mnNewMenuClientes = new JMenu("Clientes");
		mnServicios = new JMenu("Servicios");
		mntmNewMenuItemControlFunc = new JMenuItem("Control de Funcionarios");
		mntmNewMenuItemClientes = new JMenuItem("Control de Clientes");
		
		try {
			idFunc = controlador.obtenerIdFuncionarioCI(ciFuncionario);
			VOFuncionario voFunc = controlador.obtenerVOFuncionario(idFunc);
			nombreFuncionario = voFunc.getNomFun() + " " + voFunc.getApeFun();
			
			int idGrupo = voFunc.getidGrupo();
			String nombreGrupo = controlador.obtenerNombreIdGrupo(idGrupo);
			VOPermisos permisos = controlador.obtenerPermisosGrupo(nombreGrupo);
			
			
			
			int perfilesDeAcceso = permisos.getPerfAcceso();
			int abmFUncionarios = permisos.getAbmFunc();
			int abmClientes = permisos.getAbmCli();
			int abmServicios = permisos.getAbmServ();
			int ingresoHoras = permisos.getIngresoHoras();
			int controlDeFuncionarios = permisos.getControlFunc();
			int controlDeClientes = permisos.getControlCli();
			
			
			
			if (perfilesDeAcceso==0)
				mntmNewMenuItem.setVisible(false);
			
			if (abmFUncionarios ==0)
				mnNewMenuFuncionarios.setVisible(false);
			
			if (abmClientes == 0)
				mnNewMenuClientes.setVisible(false);
			
			if (abmServicios == 0)
				mnServicios.setVisible(false);
			
			if (controlDeFuncionarios == 0)
				mntmNewMenuItemControlFunc.setVisible(false);
			
			if (controlDeClientes == 0)
				mntmNewMenuItemClientes.setVisible(false);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
				
		setTitle("Usuario: " + nombreFuncionario  + "             TiMa (C) 2013");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 807, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 791, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Sistema");
		menuBar.add(mnNewMenu);
		
		JMenu mnSeguridad = new JMenu("Seguridad");
		mnNewMenu.add(mnSeguridad);
		
//		mntmNewMenuItem = new JMenuItem("Perfiles de acceso");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PerfilesDeAcceso pa = new PerfilesDeAcceso();
				pa.setVisible(true);
			}
		});
		mnSeguridad.add(mntmNewMenuItem);
		
		JMenuItem mntmCambioDeContrasea = new JMenuItem("Cambio de contrase\u00F1a");
		mntmCambioDeContrasea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CambioContrasenia ca = new CambioContrasenia(ciFuncionario);
				ca.setVisible(true);
			}
		});
		mnSeguridad.add(mntmCambioDeContrasea);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cerrar sesi\u00F3n");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaLogin plogin = new PantallaLogin();
				plogin.setVisible(true);
				PantallaPPAL.this.dispose();				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaPPAL.this.dispose();
			}
		});
		mnNewMenu.add(mntmSalir);
		
		//mnNewMenuFuncionarios = new JMenu("Funcionarios");
		menuBar.add(mnNewMenuFuncionarios);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Alta/Modificaci\u00F3n");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AltaFuncionarios af = new AltaFuncionarios();
				af.setVisible(true);
			}
		});
		mnNewMenuFuncionarios.add(mntmNewMenuItem_2);
		
		//mnNewMenuClientes = new JMenu("Clientes");
		menuBar.add(mnNewMenuClientes);
		
		JMenuItem mntmAlta = new JMenuItem("Alta/Modificaci\u00F3n");
		mntmAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AltaClientes ac = new AltaClientes();
				ac.setVisible(true);
			}
		});
		mnNewMenuClientes.add(mntmAlta);
		
		JMenuItem mntmCompliance = new JMenuItem("Compliance");
		mntmCompliance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Compliance com  = new Compliance();
				com.setVisible(true);
			}
		});
		mnNewMenuClientes.add(mntmCompliance);
		
//		mnServicios = new JMenu("Servicios");
		menuBar.add(mnServicios);
		
		JMenuItem mntmAlta_1 = new JMenuItem("Alta/Modificaci\u00F3n");
		mntmAlta_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AltaServicios as = new AltaServicios();
				as.setVisible(true);
			}
		});
		mnServicios.add(mntmAlta_1);
		
		JMenu mnHoras = new JMenu("Horas");
		menuBar.add(mnHoras);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Ingresar");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorMantemimientoHorasNew controlador = new ControladorMantemimientoHorasNew();
				MantenimientoHorasNew ventana;
				try {
					ventana = new MantenimientoHorasNew(controlador.obtenerIdFuncionarioCI(ciFuncionario));
					ventana.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnHoras.add(mntmNewMenuItem_3);
		
		//mntmNewMenuItemControlFunc = new JMenuItem("Control de Funcionarios");
		mntmNewMenuItemControlFunc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlDeFuncionarios ventana = new ControlDeFuncionarios();
				ventana.setVisible(true);
			}
		});
		
		//JMenuItem mntmNewMenuItemClientes = new JMenuItem("Control de Clientes");
		mntmNewMenuItemClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlDeClientes ventana = new ControlDeClientes();
				ventana.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Historial");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorPantallaPPAL controlador = new ControladorPantallaPPAL();
				HistorialQuincenas ventana;
				try {
					ventana = new HistorialQuincenas(controlador.obtenerIdFuncionarioCI(ciFuncionario));
					ventana.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		mnHoras.add(mntmNewMenuItem_4);
		mnHoras.add(mntmNewMenuItemClientes);
		mnHoras.add(mntmNewMenuItemControlFunc);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PantallaPPAL.class.getResource("/grafica/imagenes/logo2.2.png")));
		lblNewLabel.setBounds(10, 26, 753, 307);
		contentPane.add(lblNewLabel);
	}
}
