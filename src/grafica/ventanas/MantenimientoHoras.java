package grafica.ventanas;

import grafica.controladores.ControladorMantenimientoHoras;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.toedter.calendar.JCalendar;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logicaPersistencia.excepciones.VentanaException;
import logicaPersistencia.valueObjects.VOFuncionario;
import logicaPersistencia.valueObjects.VOTuplaHorasFuncionario;

import java.awt.Font;
import java.awt.Color;
import java.awt.HeadlessException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MantenimientoHoras extends JFrame {
	private static String ciFuncionario;
	private static int idFunc;
	private JPanel contentPane;
	private JTextField textFieldCliente;
	private JTextField textFieldServicio;
	private JTextField textFieldHoras;
	private JTable tablaQuincenal;
	private JList listClientes, listServicios;
	private JTextField textFieldFuncionario;
	private DefaultTableModel modelo;
	private JTextField textFieldFecha;
	private JCalendar calendar;
	private JLabel label_horasCompensar, lblHorasDeLaQuincena, labelHOrasQuincena, labelHsCargables, labelHsNoCargables;
	private String anio, mes, dia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MantenimientoHoras frame = new MantenimientoHoras(ciFuncionario);
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
	public MantenimientoHoras(final String ciFuncionario) {
		setTitle("Ingreso de Horas");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 984, 542);
		setBounds(100, 100, 1019, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/////////////
		
		label_horasCompensar = new JLabel("");
		label_horasCompensar.setHorizontalAlignment(SwingConstants.CENTER);
		label_horasCompensar.setBounds(431, 488, 46, 14);
		contentPane.add(label_horasCompensar);
		
		lblHorasDeLaQuincena = new JLabel("Horas de la Quincena:");
		lblHorasDeLaQuincena.setBounds(309, 526, 124, 14);
		contentPane.add(lblHorasDeLaQuincena);
		
		labelHOrasQuincena = new JLabel("");
		labelHOrasQuincena.setHorizontalAlignment(SwingConstants.CENTER);
		labelHOrasQuincena.setBounds(431, 526, 46, 14);
		contentPane.add(labelHOrasQuincena);
		
		labelHsCargables = new JLabel("");
		labelHsCargables.setHorizontalAlignment(SwingConstants.CENTER);
		labelHsCargables.setBounds(770, 488, 46, 14);
		contentPane.add(labelHsCargables);
		
		labelHsNoCargables = new JLabel("");
		labelHsNoCargables.setHorizontalAlignment(SwingConstants.CENTER);
		labelHsNoCargables.setBounds(770, 526, 46, 14);
		contentPane.add(labelHsNoCargables);
		
		////////////
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Calendario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 235, 201);
		contentPane.add(panel);
		panel.setLayout(null);
		
		calendar = new JCalendar();
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				anio = Integer.toString(calendar.getCalendar().get(java.util.Calendar.YEAR));
				mes = Integer.toString(calendar.getCalendar().get(java.util.Calendar.MONTH) + 1);
				dia = Integer.toString(calendar.getCalendar().get(java.util.Calendar.DATE));			
				
				textFieldFecha.setText(dia + "/" + mes + "/" + anio);					
			}
		});
		calendar.setBounds(10, 21, 217, 172);
		panel.add(calendar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Visita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(255, 11, 743, 201);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 206, 150);
		panel_1.add(scrollPane);
		
		listClientes = new JList();
		listClientes.setBackground(new Color(255, 255, 204));
		listClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldCliente.setText((String) listClientes.getSelectedValue());
			}
		});
		scrollPane.setViewportView(listClientes);
		
		listarClientes();
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 25, 87, 14);
		panel_1.add(lblCliente);
		
		JLabel lblServicio = new JLabel("Servicio:");
		lblServicio.setBounds(245, 25, 87, 14);
		panel_1.add(lblServicio);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(245, 40, 206, 150);
		panel_1.add(scrollPane_1);
		
		listServicios = new JList();
		listServicios.setBackground(new Color(255, 255, 204));
		listServicios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldServicio.setText((String) listServicios.getSelectedValue());
			}
		});
		scrollPane_1.setViewportView(listServicios);
		
		listarServicios();
		
		JLabel lblCliente_1 = new JLabel("Cliente:");
		lblCliente_1.setBounds(483, 56, 47, 14);
		panel_1.add(lblCliente_1);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setBounds(483, 71, 250, 20);
		textFieldCliente.setBackground(new Color(204, 255, 204));
		textFieldCliente.setEditable(false);
		textFieldCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		panel_1.add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		JLabel lblServicio_1 = new JLabel("Servicio:");
		lblServicio_1.setBounds(483, 102, 56, 14);
		panel_1.add(lblServicio_1);
		
		textFieldServicio = new JTextField();
		textFieldServicio.setBounds(483, 115, 250, 20);
		textFieldServicio.setBackground(new Color(204, 255, 204));
		textFieldServicio.setEditable(false);
		textFieldServicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldServicio.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textFieldServicio);
		textFieldServicio.setColumns(10);
		
		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setBounds(614, 173, 47, 14);
		panel_1.add(lblHoras);
		
		textFieldHoras = new JTextField();
		textFieldHoras.setBounds(657, 170, 76, 20);
		textFieldHoras.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textFieldHoras);
		textFieldHoras.setColumns(10);
		
		JLabel lblFuncionario = new JLabel("Funcionario:");
		lblFuncionario.setBounds(483, 11, 98, 14);
		panel_1.add(lblFuncionario);
		
		textFieldFuncionario = new JTextField();
		textFieldFuncionario.setBounds(483, 25, 250, 20);
		textFieldFuncionario.setBackground(new Color(153, 204, 153));
		textFieldFuncionario.setForeground(Color.BLACK);
		textFieldFuncionario.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFuncionario.setEditable(false);
		textFieldFuncionario.setColumns(10);
		panel_1.add(textFieldFuncionario);
		
		ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();		
		
		try {
			idFunc = controlador.obtenerIdFuncionarioCI(ciFuncionario);
			VOFuncionario voFunc = controlador.obtenerVOFuncionario(idFunc);			
			String nombreFunc = voFunc.getNomFun() + " " + voFunc.getApeFun();			
			textFieldFuncionario.setText(nombreFunc);
			
			JLabel lblNewLabel = new JLabel("Fecha");
			lblNewLabel.setBounds(614, 149, 46, 14);
			panel_1.add(lblNewLabel);
			
			textFieldFecha = new JTextField();
			textFieldFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldFecha.setBackground(new Color(153, 255, 153));
			textFieldFecha.setEditable(false);
			textFieldFecha.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldFecha.setColumns(10);
			textFieldFecha.setBounds(657, 146, 76, 20);
			
			String anio = Integer.toString(calendar.getCalendar().get(java.util.Calendar.YEAR));
			String mes = Integer.toString(calendar.getCalendar().get(java.util.Calendar.MONTH) + 1);
			String dia = Integer.toString(calendar.getCalendar().get(java.util.Calendar.DATE));							
			textFieldFecha.setText(dia + "/" + mes + "/" + anio);			
			
			panel_1.add(textFieldFecha);
			
			JButton btnNewButton_1 = new JButton("Eliminar Visita");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();								
					try {
						
						if (textFieldCliente.getText().isEmpty() || textFieldServicio.getText().isEmpty() || textFieldFecha.getText().isEmpty()){
							JOptionPane.showMessageDialog(getContentPane(),VentanaException.CAMPOS_VACIOS, "Error en los valores", JOptionPane.ERROR_MESSAGE);
						}else{	
							String fecha = textFieldFecha.getText();
							String arrFecha[] = fecha.split("/");
							
							
							if (!controlador.estaCerradoMes(Integer.parseInt(arrFecha[1]), Integer.parseInt(arrFecha[2]))){
								String nombreCliente = textFieldCliente.getText();
								int idCli = (controlador.obtenerVOClienteNombre(nombreCliente)).getIdCli();					
								String nombreServicio = textFieldServicio.getText();
								int idServ = controlador.obtenerIdServicio(nombreServicio.trim());						
								
								if (!controlador.existeVisita(idFunc, idCli, idServ, fecha)){
									JOptionPane.showMessageDialog(getContentPane(),VentanaException.BORRAR_VISITA, "Datos incorrectos.", JOptionPane.ERROR_MESSAGE);
								}else{
									Object[] options = {"Si", "No"};
									int n = JOptionPane.showOptionDialog(getContentPane(),
										"Se eliminarán del sistema la visita, ¿desea proceder?",
										"Eliminar visita",JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										null,     //do not use a custom Icon
										options,  //the titles of buttons
										options[0]); //default button title
									if (n==0){
										controlador.eliminarVisita(idFunc, idCli, idServ, fecha);
										limpiarTablaQuincena();
										listarQuincenaTabla();
										textFieldCliente.setText("");
										textFieldServicio.setText("");
										textFieldFecha.setText("");
										textFieldHoras.setText("");
									}
								}
							}else{
								JOptionPane.showMessageDialog(getContentPane(),VentanaException.MES_CERRADO, "Mes Cerrado", JOptionPane.ERROR_MESSAGE);
							}
						}	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					
				}
			});
			btnNewButton_1.setBounds(483, 146, 121, 44);
			panel_1.add(btnNewButton_1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Resumen quincenal de tiempo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 239, 988, 238);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 27, 968, 200);
		panel_2.add(scrollPane_2);
		
		modelo = new DefaultTableModel();	
		modelo.addColumn("Cliente");
		modelo.addColumn("Servicio");
		
		String[] diaSemana = { "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" };		
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		SimpleDateFormat sdfecha = new SimpleDateFormat("d/M/yyyy");
		Calendar c1 = Calendar.getInstance();
		
		c1.add(Calendar.DATE, -15);
		
		String fechaInicial = sdfecha.format(c1.getTime());
		//System.out.println("fechaInicial: " + fechaInicial);
		
		
		
		
		for (int i=1; i<=16; i++){		    
		    String nomDia = diaSemana[c1.get(Calendar.DAY_OF_WEEK) - 1];
	    	
		    if (nomDia.equals("Do") || nomDia.equals("Sa")){
		    	//System.out.println(diaSemana[c1.get(Calendar.DAY_OF_WEEK) - 1]);
		    	modelo.addColumn(diaSemana[c1.get(Calendar.DAY_OF_WEEK) - 1]);
		    }else{
		    	//System.out.println("Date is : " + sdf.format(c1.getTime()));
		    	modelo.addColumn(sdf.format(c1.getTime()));		    		    	
		    }
		    c1.add(Calendar.DATE, 1);
	    }
	    modelo.addColumn("Horas");		
		tablaQuincenal = new JTable(modelo);
		tablaQuincenal.setEnabled(false);
		
		Font Tablefont = new Font("Dialog",Font.BOLD,12);
		tablaQuincenal.getTableHeader().setFont(Tablefont);		
		tablaQuincenal.setBackground(new Color(240, 255, 255));
		tablaQuincenal.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		// centro valores de las columnas
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        for (int k=2; k<=18;k++)
        	tablaQuincenal.getColumnModel().getColumn(k).setCellRenderer(modelocentrar);
		
		listarQuincenaTabla();
		
		scrollPane_2.setViewportView(tablaQuincenal);		
		
		JButton btnAceptar = new JButton("Registrar Horas");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();		
				int idFunc, idCli;				
				try {
					String str_horas = textFieldHoras.getText();
					if ( str_horas.isEmpty()){
						JOptionPane.showMessageDialog(getContentPane(),VentanaException.HORAS_VACIO, "Error en los valores", JOptionPane.ERROR_MESSAGE);
					}else{
						if (!controlador.estaCerradoMes(Integer.parseInt(mes), Integer.parseInt(anio))){
						idFunc = controlador.obtenerIdFuncionarioCI(ciFuncionario);
						String nombreCliente = textFieldCliente.getText();
						idCli = (controlador.obtenerVOClienteNombre(nombreCliente)).getIdCli();
						
						String nombreServicio = textFieldServicio.getText();
				
						int idServ = controlador.obtenerIdServicio(nombreServicio.trim());
					
						float horas = Float.parseFloat(textFieldHoras.getText());
						String fecha = textFieldFecha.getText();
						
						if (controlador.existeHoraDia(fecha, idFunc, idCli, idServ)){
							JOptionPane.showMessageDialog(getContentPane(),VentanaException.HORA_REPETIDA, "Error en los valores", JOptionPane.ERROR_MESSAGE);
						}else{
							if (obtenerDiaSemana(fecha).equals("Sa") || obtenerDiaSemana(fecha).equals("Do")){
								JOptionPane.showMessageDialog(getContentPane(),VentanaException.SA_DO, "Error en los valores", JOptionPane.ERROR_MESSAGE);
							}else
								controlador.registrarHoras(idFunc, idCli, idServ, horas, fecha);
						}
						
						
						limpiarTablaQuincena();
						listarQuincenaTabla();
						
						}else{
							JOptionPane.showMessageDialog(getContentPane(),VentanaException.MES_CERRADO, "Mes Cerrado", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}
		});
		btnAceptar.setBounds(10, 488, 211, 23);
		contentPane.add(btnAceptar);
		
		JButton btnModificarHoras = new JButton("Modificar Horas");
		btnModificarHoras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cliente = textFieldCliente.getText().trim();
				String servicio = textFieldServicio.getText().trim();
				String fecha = textFieldFecha.getText();
				float horas = Float.parseFloat(textFieldHoras.getText());
				
				ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();				
				try {
					String str_horas = textFieldHoras.getText();
					if ( str_horas.isEmpty()){
						JOptionPane.showMessageDialog(getContentPane(),VentanaException.HORAS_VACIO, "Error en los valores", JOptionPane.ERROR_MESSAGE);
					}else{
						if (!controlador.estaCerradoMes(Integer.parseInt(mes), Integer.parseInt(anio))){
							int idCli = controlador.obtenerVOClienteNombre(cliente).getIdCli();
							int idServ = controlador.obtenerIdServicio(servicio);
							
							if (!controlador.existeHoraDia(fecha, idFunc, idCli, idServ)){
								JOptionPane.showMessageDialog(getContentPane(),VentanaException.NO_HORA, "Error en los valores", JOptionPane.ERROR_MESSAGE);
							}else				
								if (obtenerDiaSemana(fecha).equals("Sa") || obtenerDiaSemana(fecha).equals("Do")){
									JOptionPane.showMessageDialog(getContentPane(),VentanaException.SA_DO, "Error en los valores", JOptionPane.ERROR_MESSAGE);
								}else					
									controlador.modificarHoras(horas, idFunc, fecha, idCli, idServ);
							limpiarTablaQuincena();
							listarQuincenaTabla();
						}else{
							JOptionPane.showMessageDialog(getContentPane(),VentanaException.MES_CERRADO, "Mes Cerrado", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (SQLException | HeadlessException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnModificarHoras.setBounds(10, 522, 211, 23);
		contentPane.add(btnModificarHoras);
		
		JLabel lblHorasACompensar = new JLabel("Horas a Compensar:");
		lblHorasACompensar.setBounds(309, 488, 124, 14);
		contentPane.add(lblHorasACompensar);
		/*
		lblHorasDeLaQuincena = new JLabel("Horas de la Quincena:");
		lblHorasDeLaQuincena.setBounds(309, 526, 124, 14);
		contentPane.add(lblHorasDeLaQuincena);
		*/
		JLabel lblHorasACargables = new JLabel("Horas Cargables:");
		lblHorasACargables.setBounds(642, 488, 133, 14);
		contentPane.add(lblHorasACargables);
		
		JLabel lblHorasNoCargables = new JLabel("Horas No Cargables:");
		lblHorasNoCargables.setBounds(642, 526, 133, 14);
		contentPane.add(lblHorasNoCargables);
		
		JButton btnNewButton = new JButton("Generar Reporte");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();
				VOFuncionario voFunc = controlador.obtenerVOFuncionario(idFunc);			
				String nombreFunc = voFunc.getNomFun() + " " + voFunc.getApeFun();	
					
				
				String[] diaSemana = { "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" };		
				SimpleDateFormat sdf = new SimpleDateFormat("dd");
				Calendar c1 = Calendar.getInstance();				
				c1.add(Calendar.DATE, -15);
				
				String str_quincena[] = new String[16];
				
				for (int i=0; i<=15; i++){		    
				    String nomDia = diaSemana[c1.get(Calendar.DAY_OF_WEEK) - 1];
				    if (nomDia.equals("Do")){
				    	str_quincena[i] = "Do";
				    }else
				    	if (nomDia.equals("Sa")){
					    	str_quincena[i] = "Sa";
					    }else
					    	str_quincena[i] = "" + sdf.format(c1.getTime());
				    c1.add(Calendar.DATE, 1);
				}
				
				
				String anio = Integer.toString(calendar.getCalendar().get(java.util.Calendar.YEAR));
				String mes = Integer.toString(calendar.getCalendar().get(java.util.Calendar.MONTH) + 1);
				String dia = Integer.toString(calendar.getCalendar().get(java.util.Calendar.DATE));							
				String fechaFin = dia + "/" + mes + "/" + anio;		
				
				
				
				c1.add(Calendar.DATE, -16);
				SimpleDateFormat sdfecha = new SimpleDateFormat("d/M/yyyy");
				String fechaInicio = sdfecha.format(c1.getTime());
				
				
				Map parameters = new HashMap();
				parameters.put("fechaInicio", fechaInicio);	
				parameters.put("fechaFin", fechaFin);
				parameters.put("D1", str_quincena[0]);
				parameters.put("D2", str_quincena[1]);
				parameters.put("D3", str_quincena[2]);
				parameters.put("D4", str_quincena[3]);
				parameters.put("D5", str_quincena[4]);
				parameters.put("D6", str_quincena[5]);
				parameters.put("D7", str_quincena[6]);
				parameters.put("D8", str_quincena[7]);
				parameters.put("D9", str_quincena[8]);
				parameters.put("D10", str_quincena[9]);
				parameters.put("D11", str_quincena[10]);
				parameters.put("D12", str_quincena[11]);
				parameters.put("D13", str_quincena[12]);
				parameters.put("D14", str_quincena[13]);
				parameters.put("D15", str_quincena[14]);
				parameters.put("D16", str_quincena[15]);
				parameters.put("funcionario", nombreFunc);
				
				
				// totales
				int f = tablaQuincenal.getRowCount()-1; // indice de la ultima fila		
				
				parameters.put("PD1","" + tablaQuincenal.getValueAt(f, 2));
				parameters.put("PD2","" + tablaQuincenal.getValueAt(f, 3));
				parameters.put("PD3","" + tablaQuincenal.getValueAt(f, 4));
				parameters.put("PD4","" + tablaQuincenal.getValueAt(f, 5));
				parameters.put("PD5","" + tablaQuincenal.getValueAt(f, 6));
				parameters.put("PD6","" + tablaQuincenal.getValueAt(f, 7));
				parameters.put("PD7","" + tablaQuincenal.getValueAt(f, 8));
				parameters.put("PD8","" + tablaQuincenal.getValueAt(f, 9));
				parameters.put("PD9","" + tablaQuincenal.getValueAt(f, 10));
				parameters.put("PD10","" + tablaQuincenal.getValueAt(f, 11));
				parameters.put("PD11","" + tablaQuincenal.getValueAt(f, 12));
				parameters.put("PD12","" + tablaQuincenal.getValueAt(f, 13));
				parameters.put("PD13","" + tablaQuincenal.getValueAt(f, 14));
				parameters.put("PD14","" + tablaQuincenal.getValueAt(f, 15));
				parameters.put("PD15","" + tablaQuincenal.getValueAt(f, 16));
				parameters.put("PD16","" + tablaQuincenal.getValueAt(f, 17));
				parameters.put("phoras","" + tablaQuincenal.getValueAt(f, 18));
				
				// detalle horas
				parameters.put("h_compensar","" + label_horasCompensar.getText());
				parameters.put("h_quincena","" + labelHOrasQuincena.getText());
				parameters.put("h_cargables","" + labelHsCargables.getText());
				parameters.put("h_nocargables","" + labelHsNoCargables.getText());
				
				
				try {
					JasperPrint print = JasperFillManager.fillReport("./reportes/jasper/listadoHoras.jasper", parameters, new JRBeanCollectionDataSource(getTupasHoras()) );
					//JasperPrint print = JasperFillManager.fillReport("./reportes/jasper/listadoHoras.jasper", parameters, new JREmptyDataSource());
										
					JasperExportManager.exportReportToPdfFile(print, "./reportes/pdf/listadoHoras.pdf");
					JasperViewer.viewReport(print, false);
					
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(847, 509, 151, 23);
		contentPane.add(btnNewButton);
		
		/*
		labelHsCargables = new JLabel("");
		labelHsCargables.setHorizontalAlignment(SwingConstants.CENTER);
		labelHsCargables.setBounds(770, 488, 46, 14);
		contentPane.add(labelHsCargables);
		*/
		/*
		labelHsNoCargables = new JLabel("");
		labelHsNoCargables.setHorizontalAlignment(SwingConstants.CENTER);
		labelHsNoCargables.setBounds(770, 526, 46, 14);
		contentPane.add(labelHsNoCargables);
		*/
		/*
		labelHOrasQuincena = new JLabel("");
		labelHOrasQuincena.setHorizontalAlignment(SwingConstants.CENTER);
		labelHOrasQuincena.setBounds(431, 526, 46, 14);
		contentPane.add(labelHOrasQuincena);
		*/
		
		setAnchoColumnasTablaQuincena();
		
	}
	
	private void listarClientes(){
		DefaultListModel modeloServicios = new DefaultListModel();
		ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();
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
	
	private void listarServicios(){
		DefaultListModel modeloServicios = new DefaultListModel();
		ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();
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
	
	private void setAnchoColumnasTablaQuincena() {
        int anchoTabla = 968;  //Ancho del jScrollPane1.
        int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
        //TableColumn nos provee de métodos para minimizar, maximizar,etc. columnas de tabla.
        TableColumn  columnaTabla = null;
        int ancho1=23,
        	ancho2=23,
        	ancho3=3,
        	ancho4=3,        
        	ancho5=3,
            ancho6=3,
            ancho7=3,
            ancho8=3,
        	ancho9=3,
            ancho10=3,
            ancho11=3,
            ancho12=3,        
            ancho13=3,
            ancho14=3,
            ancho15=3,
            ancho16=3,
        	ancho17=3,
            ancho18=3,
        	ancho19=6;
        
        for(int i=0; i<tablaQuincenal.getColumnCount(); i++) {
            //Obtenemos el modelo de las columnas de la tabla.
            columnaTabla = tablaQuincenal.getColumnModel().getColumn(i);

            switch(i) {
                case 0: anchoColumna = (ancho1*anchoTabla)/100;      //20 representa el porcentaje del ancho total
                        anchoColumnaMin = (ancho1*anchoTabla)/100;   //que ocupara la primera columna.
                        anchoColumnaMax = (ancho1*anchoTabla)/100;
                break;
                case 1: anchoColumna = (ancho2*anchoTabla)/100;
                        anchoColumnaMin = (ancho2*anchoTabla)/100;
                        anchoColumnaMax = (ancho2*anchoTabla)/100;
                break;        
                case 2: anchoColumna = (ancho3*anchoTabla)/100;
	                anchoColumnaMin = (ancho3*anchoTabla)/100;
	                anchoColumnaMax = (ancho3*anchoTabla)/100;
                break;
                case 3: anchoColumna = (ancho4*anchoTabla)/100;
	                anchoColumnaMin = (ancho4*anchoTabla)/100;
	                anchoColumnaMax = (ancho4*anchoTabla)/100;
                break;
                
                case 4: anchoColumna = (ancho5*anchoTabla)/100;      //20 representa el porcentaje del ancho total
	                anchoColumnaMin = (ancho5*anchoTabla)/100;   //que ocupara la primera columna.
	                anchoColumnaMax = (ancho5*anchoTabla)/100;
	             break;
		        case 5: anchoColumna = (ancho6*anchoTabla)/100;
		                anchoColumnaMin = (ancho6*anchoTabla)/100;
		                anchoColumnaMax = (ancho6*anchoTabla)/100;
		        break;        
		        case 6: anchoColumna = (ancho7*anchoTabla)/100;
		            anchoColumnaMin = (ancho7*anchoTabla)/100;
		            anchoColumnaMax = (ancho7*anchoTabla)/100;
		        break;
		        case 7: anchoColumna = (ancho8*anchoTabla)/100;
		            anchoColumnaMin = (ancho8*anchoTabla)/100;
		            anchoColumnaMax = (ancho8*anchoTabla)/100;
		        break;
		        
		        case 8: anchoColumna = (ancho9*anchoTabla)/100;      //20 representa el porcentaje del ancho total
	                anchoColumnaMin = (ancho9*anchoTabla)/100;   //que ocupara la primera columna.
	                anchoColumnaMax = (ancho9*anchoTabla)/100;
                break;
		        case 9: anchoColumna = (ancho10*anchoTabla)/100;
		                anchoColumnaMin = (ancho10*anchoTabla)/100;
		                anchoColumnaMax = (ancho10*anchoTabla)/100;
		        break;        
	        case 10: anchoColumna = (ancho11*anchoTabla)/100;
	            anchoColumnaMin = (ancho11*anchoTabla)/100;
	            anchoColumnaMax = (ancho11*anchoTabla)/100;
	        break;
	        case 11: anchoColumna = (ancho12*anchoTabla)/100;
	            anchoColumnaMin = (ancho12*anchoTabla)/100;
	            anchoColumnaMax = (ancho12*anchoTabla)/100;
	        break;        
	        case 12: anchoColumna = (ancho13*anchoTabla)/100;      //20 representa el porcentaje del ancho total
	            anchoColumnaMin = (ancho13*anchoTabla)/100;   //que ocupara la primera columna.
	            anchoColumnaMax = (ancho13*anchoTabla)/100;
	         break;
	        case 13: anchoColumna = (ancho14*anchoTabla)/100;
	                anchoColumnaMin = (ancho14*anchoTabla)/100;
	                anchoColumnaMax = (ancho14*anchoTabla)/100;
	        break;        
	        case 14: anchoColumna = (ancho15*anchoTabla)/100;
	            anchoColumnaMin = (ancho15*anchoTabla)/100;
	            anchoColumnaMax = (ancho15*anchoTabla)/100;
	        break;
	        case 15: anchoColumna = (ancho16*anchoTabla)/100;
	            anchoColumnaMin = (ancho16*anchoTabla)/100;
	            anchoColumnaMax = (ancho16*anchoTabla)/100;
	        break;
	        case 16: anchoColumna = (ancho17*anchoTabla)/100;
            anchoColumnaMin = (ancho17*anchoTabla)/100;
            anchoColumnaMax = (ancho17*anchoTabla)/100;
            break;
	        case 17: anchoColumna = (ancho18*anchoTabla)/100;
	            anchoColumnaMin = (ancho18*anchoTabla)/100;
	            anchoColumnaMax = (ancho18*anchoTabla)/100;
	        break;
	        case 18: anchoColumna = (ancho19*anchoTabla)/100;
	            anchoColumnaMin = (ancho19*anchoTabla)/100;
	            anchoColumnaMax = (ancho19*anchoTabla)/100;
	        break;
            }
            //Aplicamos el ancho para cada columna de la tabla.
            columnaTabla.setPreferredWidth(anchoColumna);
            columnaTabla.setMinWidth(anchoColumnaMin);
            columnaTabla.setMaxWidth(anchoColumnaMax);
        }
    }
	
	private void listarQuincenaTabla(){
		String anio = Integer.toString(calendar.getCalendar().get(java.util.Calendar.YEAR));
		String mes = Integer.toString(calendar.getCalendar().get(java.util.Calendar.MONTH) + 1);
		String dia = Integer.toString(calendar.getCalendar().get(java.util.Calendar.DATE));
		
		String fechaActual = dia + "/" + mes + "/" + anio;
		//System.out.println("fechaActual: " + fechaActual);
		
		Calendar c1 = Calendar.getInstance();		
		c1.add(Calendar.DATE, -15);
		
		SimpleDateFormat sdfecha = new SimpleDateFormat("d/M/yyyy");
		String fechaInicial = sdfecha.format(c1.getTime());
		//System.out.println("fechaInicial: " + fechaInicial);
		
		//limpiarTablaQuincena(); // limpio la tabla
			
		
		ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();
		
		
		Calendar cal1 = Calendar.getInstance();
		String FECHA_HOY = cal1.get(Calendar.DATE) + "/" + (cal1.get(Calendar.MONTH) + 1) +"/"+cal1.get(Calendar.YEAR);
		//System.out.println(FECHA_HOY);
		
		
		List<VOTuplaHorasFuncionario> lstTupla = controlador.listarClienteSrvicio(idFunc, fechaInicial, FECHA_HOY);		
		//System.out.println("lstTupla.size(): " + lstTupla.size());

		
		
		if (lstTupla.size() >0){
    		Iterator<VOTuplaHorasFuncionario> iterTupla = lstTupla.iterator();
    		while (iterTupla.hasNext()){
    			VOTuplaHorasFuncionario dataTupla = iterTupla.next();
    			Object[] fila = new Object[19];
    			fila[0] = " " + dataTupla.getCliente();
    			fila[1] = " " + dataTupla.getServicio();
    			
    			
    			
    			int sumHoras=0;
    			for (int i=1; i<=16; i++){
    				fechaInicial = sdfecha.format(c1.getTime());
    				try {
    					if (controlador.existeHorasFuncClienteServicio(idFunc, dataTupla.getCliente(), dataTupla.getServicio(), fechaInicial)){
	    					int horas = controlador.obtenerHorasFuncClienteServicio(idFunc, dataTupla.getCliente(), dataTupla.getServicio(), fechaInicial);    					
	    					fila[i+1] = horas;
	    					sumHoras = sumHoras + horas;
    					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				c1.add(Calendar.DATE, 1);
    			}
    			fila[18] = sumHoras;
    			c1.add(Calendar.DATE, -16);    			    			
    			
				
				modelo.addRow(fila);
    			//tablaQuincenal.setModel(modelo);
    		}
    		
    		// pie de tabla quicena
    		int horasPorDiaFuncionario = controlador.obtenerVOFuncionario(idFunc).getHorasDia(); // horas por dia que trabaja el funcionario, según fue contratado
    		int horasAcompensar=0;
    		int horasTotalQuincena = 0;
    		int horasCargablesTotalQuincena = 0;
    		Object[] filaAlPie = new Object[19];
    		filaAlPie[1] = "T o t a l   H o r a s";
    		for (int i=1; i<=16; i++){
    			fechaInicial = sdfecha.format(c1.getTime());
    			try {
    				int horasTrabajadasDia = controlador.sumaHorasClienteServicioDia(idFunc, fechaInicial);
    				int horasCargablesTrabajadasDia = controlador.sumaHorasCargablesClienteServicioDia(idFunc, fechaInicial);
					filaAlPie[i+1] = horasTrabajadasDia;
					if (horasTrabajadasDia > horasPorDiaFuncionario){
						horasAcompensar = horasAcompensar + horasTrabajadasDia - horasPorDiaFuncionario;
					}
					horasTotalQuincena = horasTotalQuincena + horasTrabajadasDia;
					horasCargablesTotalQuincena = horasCargablesTotalQuincena + horasCargablesTrabajadasDia;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			c1.add(Calendar.DATE, 1);			
    			
    		}
    		filaAlPie[18] = horasTotalQuincena;    		
    		
    		
    		modelo.addRow(filaAlPie);    	
    		tablaQuincenal.setModel(modelo);
    		
    		
			label_horasCompensar.setText("" + horasAcompensar);
			labelHOrasQuincena.setText("" + (horasTotalQuincena - horasAcompensar));
			labelHsCargables.setText("" + horasCargablesTotalQuincena);
			labelHsNoCargables.setText("" + (horasTotalQuincena - horasCargablesTotalQuincena));		
    	}
		
	}
	
	private void limpiarTablaQuincena()    {
        int filas = modelo.getRowCount();
        if (filas > 0) {
            for (int i = 0; i < filas; i++) {
            	modelo.removeRow(0);           	
            }
        }        
    }
	

	
	public List getTupasHoras(){
		List tuplas = new ArrayList();
		String FD1 = "";
		String FD2 = "";
		String FD3 = "";
		String FD4 = "";
		String FD5 = "";
		String FD6 = "";
		String FD7 = "";
		String FD8 = "";
		String FD9 = "";
		String FD10 = "";
		String FD11 = "";
		String FD12 = "";
		String FD13 = "";
		String FD14 = "";
		String FD15 = "";
		String FD16 = "";
		int horas = 0;
		
		for (int i=0; i< tablaQuincenal.getRowCount()-1; i++){			
	
			String cliente = (String) tablaQuincenal.getValueAt(i, 0);
			String servicio = (String) tablaQuincenal.getValueAt(i, 1);
			
			if (tablaQuincenal.getValueAt(i, 2) != null)
				FD1 = "" + tablaQuincenal.getValueAt(i, 2);
			else
				FD1 = "";
			if (tablaQuincenal.getValueAt(i, 3) != null)
				FD2 = "" + tablaQuincenal.getValueAt(i, 3);
			else
				FD2 = "";
			if (tablaQuincenal.getValueAt(i, 4) != null)
				FD3 = "" + tablaQuincenal.getValueAt(i, 4);
			else
				FD3 = "";
			if (tablaQuincenal.getValueAt(i, 5) != null)
				FD4 = "" + tablaQuincenal.getValueAt(i, 5);
			else
				FD4 = "";
			if (tablaQuincenal.getValueAt(i, 6) != null)
				FD5 = "" + tablaQuincenal.getValueAt(i, 6);
			else
				FD5 = "";
			if (tablaQuincenal.getValueAt(i, 7) != null)
				FD6 = "" + tablaQuincenal.getValueAt(i, 7);
			else
				FD6 = "";
			if (tablaQuincenal.getValueAt(i, 8) != null)
				FD7 = "" + tablaQuincenal.getValueAt(i, 8);
			else
				FD7 = "";
			if (tablaQuincenal.getValueAt(i, 9) != null)
				FD8 = "" + tablaQuincenal.getValueAt(i, 9);
			else
				FD8 = "";
			if (tablaQuincenal.getValueAt(i, 10) != null)
				FD9 = "" + tablaQuincenal.getValueAt(i, 10);
			else
				FD9 = "";
			if (tablaQuincenal.getValueAt(i, 11) != null)
				FD10 = "" + tablaQuincenal.getValueAt(i, 11);
			else
				FD10 = "";
			if (tablaQuincenal.getValueAt(i, 12) != null)
				FD11 = "" + tablaQuincenal.getValueAt(i, 12);
			else
				FD11 = "";
			if (tablaQuincenal.getValueAt(i, 13) != null)
				FD12 = "" + tablaQuincenal.getValueAt(i, 13);
			else
				FD12 = "";
			if (tablaQuincenal.getValueAt(i, 14) != null)
				FD13 = "" + tablaQuincenal.getValueAt(i, 14);
			else
				FD13 = "";
			if (tablaQuincenal.getValueAt(i, 15) != null)
				FD14 = "" + tablaQuincenal.getValueAt(i, 15);
			else
				FD14 = "";
			if (tablaQuincenal.getValueAt(i, 16) != null)
				FD15 = "" + tablaQuincenal.getValueAt(i, 16);
			else
				FD15 = "";
			if (tablaQuincenal.getValueAt(i, 17) != null)
				FD16 = "" + tablaQuincenal.getValueAt(i, 17);
			else
				FD16 = "";
			
			if (tablaQuincenal.getValueAt(i, 18) != null)
				horas = (int) tablaQuincenal.getValueAt(i, 18);
			
			//System.out.println("horas: " + horas);
			
			VOTuplaHorasFuncionario tupla = new VOTuplaHorasFuncionario();
			tupla.setCliente(cliente);
			tupla.setServicio(servicio);
			tupla.setFD1(FD1);
			tupla.setFD2(FD2);
			tupla.setFD3(FD3);
			tupla.setFD4(FD4);
			tupla.setFD5(FD5);
			tupla.setFD6(FD6);
			tupla.setFD7(FD7);
			tupla.setFD8(FD8);
			tupla.setFD9(FD9);
			tupla.setFD10(FD10);
			tupla.setFD11(FD11);
			tupla.setFD12(FD12);
			tupla.setFD13(FD13);
			tupla.setFD14(FD14);
			tupla.setFD15(FD15);
			tupla.setFD16(FD16);
			tupla.setHoras(horas);
			
			tuplas.add(tupla);		
		}		
		return tuplas;
	}
	
	
	public String obtenerDiaSemana(String fecha) throws ParseException{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dFecha = df.parse(fecha);
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dFecha);
		int dia = cal.get(Calendar.DAY_OF_WEEK);
		
		String strDia = "";
		switch(dia) {
		 case 1: 
		     strDia = "Do";
		     break;
		 case 2: 
			 strDia = "Lu";
		     break;
		 case 3: 
			 strDia = "Ma";
		     break;
		 case 4: 
			 strDia = "Mi";
		     break;
		 case 5: 
			 strDia = "Ju";
		     break;
		 case 6: 
			 strDia = "Vi";
		     break;
		 case 7: 
			 strDia = "Sa";
		     break;		     
		}
		return strDia;
	}
}

