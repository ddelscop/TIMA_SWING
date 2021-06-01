package grafica.ventanas;

import grafica.controladores.ControladorControlDeClientes;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logicaPersistencia.excepciones.VentanaException;
import logicaPersistencia.valueObjects.VOFuncionario;
import logicaPersistencia.valueObjects.VOTuplaHorasFuncionario;

import java.awt.Font;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;
import javax.swing.ScrollPaneConstants;

public class ControlDeClientes extends JFrame {
	//private static String ciFuncionario;
	//private static int idFunc;
	private JPanel contentPane;
	private JList listClientes;
	private DefaultTableModel modelo;
	private JTable tableHorasTotales;
	private DefaultTableModel modeloHorasTotales;
	private JTextField textFieldTotalHoras;
	private JYearChooser yearChooserHasta;
	private JMonthChooser monthChooserHasta;
	private JTextField textFieldHonorarios;
	private JTextField textFieldRate;
	private JMonthChooser monthChooserDesde;
    private JYearChooser yearChooserDesde;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ControlDeClientes frame = new ControlDeClientes();
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
	public ControlDeClientes() {
		setTitle("Control de Horas Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 984, 542);
		setBounds(100, 100, 991, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Detalle de Horas por Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 965, 467);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 209, 254);
		panel_1.add(scrollPane);
		
		listClientes = new JList();
		listClientes.setBackground(new Color(255, 255, 204));
		listClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//textFieldCliente.setText((String) listClientes.getSelectedValue());
			}
		});
		scrollPane.setViewportView(listClientes);
		
		listarClientes();
		
		JLabel lblCliente = new JLabel("Clientes:");
		lblCliente.setBounds(10, 25, 87, 14);
		panel_1.add(lblCliente);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_4.setBounds(239, 24, 715, 397);
		panel_1.add(scrollPane_4);
		
		modeloHorasTotales = new DefaultTableModel();
		modeloHorasTotales.addColumn("Funcionarios");
		modeloHorasTotales.addColumn("Servicios");
		modeloHorasTotales.addColumn("Horas");
		
		tableHorasTotales = new JTable(modeloHorasTotales);
		scrollPane_4.setViewportView(tableHorasTotales);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				ControladorControlDeClientes controlador = new ControladorControlDeClientes();					
					int aaaa1= yearChooserDesde.getYear();
					int mm1 = monthChooserDesde.getMonth();
				
					int aaaa2= yearChooserHasta.getYear();
					int mm2 = monthChooserHasta.getMonth();
						
					
					if (aaaa1 > aaaa2){
						JOptionPane.showMessageDialog(getContentPane(), "El año inicial debe ser menor o igual que el final", "Años incorrectos", JOptionPane.ERROR_MESSAGE);						
					}else{
						if ((aaaa1 == aaaa2) && (mm1 > mm2)){
							JOptionPane.showMessageDialog(getContentPane(), "El mes inicial debe ser menor o igual que el final", "Meses incorrectos", JOptionPane.ERROR_MESSAGE);
						}else{
							Calendar cal = new GregorianCalendar(aaaa2, mm2, 1);
							String fechaFinMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH) + "/" + (mm2 + 1) + "/" + aaaa2;
							String fechaInicial = "1/" + (mm1 + 1) + "/" + aaaa1;
													
							String cliente = (String) listClientes.getSelectedValue();
							if (cliente !=null){
								int idCli = (controlador.obtenerVOClienteNombre(cliente)).getIdCli();
								limpiartableHorasTotales();
								listarTablaHoras(idCli, fechaInicial, fechaFinMes);
								String honorarios;
								String moneda = "$";
								try {						
									if (controlador.obtenerMonedaCliente(idCli) ==1)
										moneda = "USD";
									honorarios = moneda + " " + controlador.obtenerHonorariosCliente(idCli);
									//textFieldHonorarios.setText("");
									textFieldHonorarios.setText(honorarios);
									textFieldRate.setText("");
									if (Integer.parseInt(textFieldTotalHoras.getText()) !=0){									
										float rate = controlador.obtenerHonorariosCliente(idCli) / Integer.parseInt(textFieldTotalHoras.getText());
										textFieldRate.setText("" + rate);
									}
									
									
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							
							
							
						}						
					}
						
						
						
						
						/*
						Calendar cal = new GregorianCalendar(aaaa2, mm2, 1);
						String fechaFinMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH) + "/" + (mm2 + 1) + "/" + aaaa2;
						String fechaInicial = "1/" + (mm2 + 1) + "/" + aaaa2;
												
						String cliente = (String) listClientes.getSelectedValue();
						if (cliente !=null){
							int idCli = (controlador.obtenerVOClienteNombre(cliente)).getIdCli();
							limpiartableHorasTotales();
							listarTablaHoras(idCli, fechaInicial, fechaFinMes);
							String honorarios;
							String moneda = "$";
							try {						
								if (controlador.obtenerMonedaCliente(idCli) ==1)
									moneda = "USD";
								honorarios = moneda + " " + controlador.obtenerHonorariosCliente(idCli);
								//textFieldHonorarios.setText("");
								textFieldHonorarios.setText(honorarios);
								textFieldRate.setText("");
								if (Integer.parseInt(textFieldTotalHoras.getText()) !=0){									
									float rate = controlador.obtenerHonorariosCliente(idCli) / Integer.parseInt(textFieldTotalHoras.getText());
									textFieldRate.setText("" + rate);
								}
								
								
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						*/
			
					
					
				
				
				
			}
		});
		btnNewButton.setBounds(30, 400, 154, 23);
		panel_1.add(btnNewButton);
		
		listarServicios();
		
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
		
		Font Tablefont = new Font("Dialog",Font.BOLD,12);
		
		// centro valores de las columnas
		//DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        //modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        //for (int k=2; k<=18;k++)
       // 	tablaQuincenal.getColumnModel().getColumn(k).setCellRenderer(modelocentrar);
		
        
        
        /////////////////////////////////       
        tableHorasTotales.getTableHeader().setFont(Tablefont);		
        tableHorasTotales.setBackground(new Color(240, 255, 255));
        tableHorasTotales.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        textFieldTotalHoras = new JTextField();
        textFieldTotalHoras.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldTotalHoras.setFont(new Font("Tahoma", Font.BOLD, 12));
        textFieldTotalHoras.setEditable(false);
        textFieldTotalHoras.setColumns(10);
        textFieldTotalHoras.setBackground(new Color(204, 255, 204));
        textFieldTotalHoras.setBounds(698, 435, 62, 20);
        panel_1.add(textFieldTotalHoras);
        
        JLabel lblNewLabel = new JLabel("Total:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(642, 438, 46, 14);
        panel_1.add(lblNewLabel);
        
        yearChooserHasta = new JYearChooser();
        yearChooserHasta.setBounds(140, 368, 64, 20);
        panel_1.add(yearChooserHasta);
        
        monthChooserHasta = new JMonthChooser();
        monthChooserHasta.setBounds(20, 368, 110, 20);
        panel_1.add(monthChooserHasta);
        
        JLabel lblNewLabel_1 = new JLabel("Honorarios:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setBounds(249, 438, 76, 14);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblRate = new JLabel("Rate:");
        lblRate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblRate.setBounds(459, 438, 46, 14);
        panel_1.add(lblRate);
        
        textFieldHonorarios = new JTextField();
        textFieldHonorarios.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldHonorarios.setFont(new Font("Tahoma", Font.BOLD, 12));
        textFieldHonorarios.setBackground(new Color(204, 255, 255));
        textFieldHonorarios.setEditable(false);
        textFieldHonorarios.setBounds(331, 435, 118, 20);
        panel_1.add(textFieldHonorarios);
        textFieldHonorarios.setColumns(10);
        
        textFieldRate = new JTextField();
        textFieldRate.setBackground(new Color(255, 255, 204));
        textFieldRate.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldRate.setFont(new Font("Tahoma", Font.BOLD, 12));
        textFieldRate.setEditable(false);
        textFieldRate.setColumns(10);
        textFieldRate.setBounds(500, 435, 118, 20);
        panel_1.add(textFieldRate);
        
        JButton btnNewButton_1 = new JButton("Generar reporte");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String cliente = (String) listClientes.getSelectedValue();
        		//int aaaa= yearChooserHasta.getYear();
				//int mm = monthChooserHasta.getMonth() +1;	
        		
				
				int aaaa1= yearChooserDesde.getYear();
				int mm1 = monthChooserDesde.getMonth();
				int aaaa2= yearChooserHasta.getYear();
				int mm2 = monthChooserHasta.getMonth();

				Calendar cal = new GregorianCalendar(aaaa2, mm2, 1);
				String fechaFinMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH) + "/" + (mm2 + 1) + "/" + aaaa2;
				String fechaInicial = "1/" + (mm1 + 1) + "/" + aaaa1;
				
				
				
        		Map parameters = new HashMap();
        		parameters.put("cliente", cliente);
        		parameters.put("fechaDesde", "" + fechaInicial);
        		parameters.put("fechaHasta", "" + fechaFinMes);
        		parameters.put("honorarios", textFieldHonorarios.getText());
        		parameters.put("rate", textFieldRate.getText());
        		parameters.put("totalHoras", textFieldTotalHoras.getText());	
        		        		
        		
        		try {
					//JasperPrint print = JasperFillManager.fillReport("./reportes/jasper/listadDetalleHorasCliente.jasper", parameters, new JREmptyDataSource());
        			JasperPrint print = JasperFillManager.fillReport("./reportes/jasper/listadDetalleHorasCliente.jasper", parameters, new JRBeanCollectionDataSource(getTupasHorasDetalle()));
					JasperExportManager.exportReportToPdfFile(print, "./reportes/pdf/listadDetalleHorasCliente.pdf");
					JasperViewer.viewReport(print, false);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton_1.setBounds(30, 434, 154, 23);
        panel_1.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Salir");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ControlDeClientes.this.dispose();
        	}
        });
        btnNewButton_2.setBounds(804, 434, 89, 23);
        panel_1.add(btnNewButton_2);
        
        JLabel lblHasta = new JLabel("Hasta:");
        lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblHasta.setBounds(10, 350, 46, 14);
        panel_1.add(lblHasta);
        
        monthChooserDesde = new JMonthChooser();
        monthChooserDesde.setBounds(20, 326, 110, 20);
        panel_1.add(monthChooserDesde);        
        
        yearChooserDesde = new JYearChooser();
        yearChooserDesde.setBounds(140, 326, 64, 20);
        panel_1.add(yearChooserDesde);
        
        JLabel lblDesde = new JLabel("Desde:");
        lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDesde.setBounds(10, 305, 46, 14);
        panel_1.add(lblDesde);
        
     // centro valores de las columnas
     		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
             modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        tableHorasTotales.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
        tableHorasTotales.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
        
        
      //  tableHorasTotales.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
       // tableHorasTotales.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		
		
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
		

		setAnchoColumnaslistarTablaHoras();
		
	}
	
	private void listarClientes(){
		DefaultListModel modeloServicios = new DefaultListModel();
		ControladorControlDeClientes controlador = new ControladorControlDeClientes();
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
		ControladorControlDeClientes controlador = new ControladorControlDeClientes();
		List<String> lstServicios = controlador.listarServicios();
		if (lstServicios.size() > 0){
			Iterator<String> iterServicios = lstServicios.iterator();
			while(iterServicios.hasNext()){				
				String servicio = iterServicios.next();
				modeloServicios.addElement("  " + servicio);
			}
						
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
	
	
	
	private void listarTablaHoras(int idCli, String fechaInicio, String fechaFin){	
		ControladorControlDeClientes controlador = new ControladorControlDeClientes();
		List<VOTuplaHorasFuncionario> lstHoras= controlador.listarDetalleHoras(idCli, fechaInicio, fechaFin);
		int totalHoras =0;
		if (lstHoras.size() >0){
			Iterator<VOTuplaHorasFuncionario> iterHoras = lstHoras.iterator();
			while (iterHoras.hasNext()){
				VOTuplaHorasFuncionario dataHoras = iterHoras.next();
				Object[] fila = new Object[3];
				fila[0] = "  " + dataHoras.getFuncionario();
				fila[1] = dataHoras.getServicio();				
				fila[2] = dataHoras.getHoras();				
				totalHoras = totalHoras + Integer.parseInt(dataHoras.getHoras());
				modeloHorasTotales.addRow(fila);
			}			
		}		
		textFieldTotalHoras.setText("" + totalHoras);		
	}
	
	private void limpiartableHorasTotales()    {
        int filas = modeloHorasTotales.getRowCount();
        if (filas > 0) {
            for (int i = 0; i < filas; i++) {
            	modeloHorasTotales.removeRow(0);           	
            }
        }
    }
	
	private void setAnchoColumnaslistarTablaHoras() {
        int anchoTabla = 715;  //Ancho del jScrollPane1.
        int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
        //TableColumn nos provee de métodos para minimizar, maximizar,etc. columnas de tabla.
        TableColumn  columnaTabla = null;
        int ancho1=45,
        	ancho2=45,
        	ancho3=10;
        
        for(int i=0; i<tableHorasTotales.getColumnCount(); i++) {
            //Obtenemos el modelo de las columnas de la tabla.
            columnaTabla = tableHorasTotales.getColumnModel().getColumn(i);

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
          }
            //Aplicamos el ancho para cada columna de la tabla.
            columnaTabla.setPreferredWidth(anchoColumna);
            columnaTabla.setMinWidth(anchoColumnaMin);
            columnaTabla.setMaxWidth(anchoColumnaMax);
        }
    }
	
	public List getTupasHorasDetalle(){
		List tuplas = new ArrayList();
		
		for (int i=0; i< tableHorasTotales.getRowCount(); i++){	
			String funcionario = (String) tableHorasTotales.getValueAt(i, 0);
			String servicio = (String) tableHorasTotales.getValueAt(i, 1);
			String horas = (String) tableHorasTotales.getValueAt(i, 2);
			
			VOTuplaHorasFuncionario tupla = new VOTuplaHorasFuncionario();
			tupla.setFuncionario(funcionario);
			tupla.setServicio(servicio);
			tupla.setHoras(Integer.parseInt(horas));			
			tuplas.add(tupla);				
		}	
		return tuplas;
	}
}
