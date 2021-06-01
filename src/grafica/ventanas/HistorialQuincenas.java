package grafica.ventanas;

import grafica.controladores.ControladorHistorialQuincenas;
import grafica.controladores.ControladorMantenimientoHoras;

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
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logicaPersistencia.valueObjects.VOFuncionario;
import logicaPersistencia.valueObjects.VOTuplaHorasFuncionario;

import java.awt.Component;
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

public class HistorialQuincenas extends JFrame {
	//private static String ciFuncionario;
	//private static int idFunc;
	private JPanel contentPane;
	private JTable tabla1aQuincena;
	private DefaultTableModel modelo1aQuincena, modelo2aQuincena, modeloHorasMensuales;
	private JLabel label_horasCompensar, lblHorasDeLaQuincena1, labelHOrasQuincena1, labelHsCargables, labelHsNoCargables;
	private DefaultTableModel modeloHorasTotales;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	private JTable tabla2aQuincena;
	private JLabel labelHCompensar, labelHCargables, labelHOrasQuincena2, labelNoCargables;
	private static int idFuncionario;
	private JLabel labelNombreFuncionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					//MantenimientoHoras frame = new MantenimientoHoras(ciFuncionario);
					HistorialQuincenas frame = new HistorialQuincenas(idFuncionario);
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
	public HistorialQuincenas(final int idFuncionario) {
		setTitle("Historial de Quincenas");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 984, 542);
		setBounds(100, 100, 1054, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listarClientes();
		
		tabla2aQuincena = new JTable();
		
		modeloHorasTotales = new DefaultTableModel();
		modeloHorasTotales.addColumn("Funcionarios");
		modeloHorasTotales.addColumn("Servicios");
		modeloHorasTotales.addColumn("Horas");
		
		listarServicios();
		
		ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();		
		
		//try {
			//idFunc = controlador.obtenerIdFuncionarioCI(ciFuncionario);
			//VOFuncionario voFunc = controlador.obtenerVOFuncionario(idFunc);			
			//String nombreFunc = voFunc.getNomFun() + " " + voFunc.getApeFun();
			
			//String anio = Integer.toString(calendar.getCalendar().get(java.util.Calendar.YEAR));
			//String mes = Integer.toString(calendar.getCalendar().get(java.util.Calendar.MONTH) + 1);
			//String dia = Integer.toString(calendar.getCalendar().get(java.util.Calendar.DATE));
		//} catch (SQLException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
		
		monthChooser = new JMonthChooser();
		yearChooser = new JYearChooser();
		yearChooser.getSpinner().setLocation(0, 11);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Primera quincena", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 97, 1018, 226);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(10, 27, 998, 163);
		panel_2.add(scrollPane_2);
		
		modelo1aQuincena = new DefaultTableModel();	
		modelo1aQuincena.addColumn("Cliente");
		modelo1aQuincena.addColumn("Servicio");
		
		String[] diaSemana = { "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" };		
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		SimpleDateFormat sdfecha = new SimpleDateFormat("d/M/yyyy");
		Calendar c1 = Calendar.getInstance();
		
		int mes =monthChooser.getMonth();
		int anio = yearChooser.getYear();

		c1.set(anio, mes, 1);
		
		
		for (int i=1; i<=16; i++){		    
			String nomDia = diaSemana[c1.get(c1.DAY_OF_WEEK) - 1];
			
		    if (nomDia.equals("Do") || nomDia.equals("Sa")){
		    	modelo1aQuincena.addColumn(diaSemana[c1.get(c1.DAY_OF_WEEK) - 1]);
		    }else{
		    	//System.out.println("Date is : " + sdf.format(c1.getTime()));
		    	modelo1aQuincena.addColumn(sdf.format(c1.getTime()));		    		    	
		    }
		    c1.add(Calendar.DATE, 1);
	    }
		modelo1aQuincena.addColumn("Horas");		
		//tabla1aQuincena = new JTable(modelo1aQuincena);
		tabla1aQuincena = new JTable();

		

		tabla1aQuincena.setEnabled(false);
		
		Font Tablefont = new Font("Dialog",Font.BOLD,12);
		tabla1aQuincena.getTableHeader().setFont(Tablefont);		
		tabla1aQuincena.setBackground(new Color(240, 255, 255));
		tabla1aQuincena.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		tabla2aQuincena.getTableHeader().setFont(Tablefont);		
		tabla2aQuincena.setBackground(new Color(240, 255, 255));
		tabla2aQuincena.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		
		
        	
        
        
        
        
        
        
        
        
        
        
		//listarQuincenaTabla();
		
		scrollPane_2.setViewportView(tabla1aQuincena);
		
		JLabel lblHorasACompensar = new JLabel("Horas a Compensar:");
		lblHorasACompensar.setBounds(10, 201, 124, 14);
		panel_2.add(lblHorasACompensar);
		
		/////////////
		
		label_horasCompensar = new JLabel("");
		label_horasCompensar.setBounds(125, 201, 46, 14);
		panel_2.add(label_horasCompensar);
		label_horasCompensar.setHorizontalAlignment(SwingConstants.CENTER);
		/*
		lblHorasDeLaQuincena = new JLabel("Horas de la Quincena:");
		lblHorasDeLaQuincena.setBounds(309, 526, 124, 14);
		contentPane.add(lblHorasDeLaQuincena);
		*/
		JLabel lblHorasACargables = new JLabel("Horas Cargables:");
		lblHorasACargables.setBounds(224, 201, 102, 14);
		panel_2.add(lblHorasACargables);
		
		labelHsCargables = new JLabel("");
		labelHsCargables.setBounds(336, 201, 46, 14);
		panel_2.add(labelHsCargables);
		labelHsCargables.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblHorasDeLaQuincena1 = new JLabel("Quincena 1:");
		lblHorasDeLaQuincena1.setBounds(412, 201, 75, 14);
		panel_2.add(lblHorasDeLaQuincena1);
		
		labelHOrasQuincena1 = new JLabel("");
		labelHOrasQuincena1.setBounds(485, 201, 46, 14);
		panel_2.add(labelHOrasQuincena1);
		labelHOrasQuincena1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblHorasNoCargables = new JLabel("Horas No Cargables:");
		lblHorasNoCargables.setBounds(634, 201, 124, 14);
		panel_2.add(lblHorasNoCargables);
		
		labelHsNoCargables = new JLabel("");
		labelHsNoCargables.setBounds(757, 201, 46, 14);
		panel_2.add(labelHsNoCargables);
		labelHsNoCargables.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton_2 = new JButton("Generar reporte");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorHistorialQuincenas controlador = new ControladorHistorialQuincenas();				
				//String func = ((String) listFuncionarios.getSelectedValue()).trim(); 
				int idFunc;
				VOFuncionario voFunc;
				String nombreFunc;
				try {
					//idFunc = controlador.obtenerIdFunNomApe(func);
					voFunc = controlador.obtenerVOFuncionario(idFuncionario);
					nombreFunc = voFunc.getNomFun() + " " + voFunc.getApeFun();
					
					String[] diaSemana = { "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" };		
					SimpleDateFormat sdf = new SimpleDateFormat("dd");
					Calendar c1 = Calendar.getInstance();			
					
					int mes =monthChooser.getMonth();
					int anio = yearChooser.getYear();

					c1.set(anio, mes, 1);				
					
					
					//c1.add(Calendar.DATE, -15);
					
					
					
					String str_quincena[] = new String[16];
					
					for (int i=0; i<=15; i++){		    
						String nomDia = diaSemana[c1.get(c1.DAY_OF_WEEK) - 1];
					    if (nomDia.equals("Do")){
					    	str_quincena[i] = "Do";
					    }else
					    	if (nomDia.equals("Sa")){
						    	str_quincena[i] = "Sa";
						    }else
						    	str_quincena[i] = "" + sdf.format(c1.getTime());
					    c1.add(Calendar.DATE, 1);
					}
					
				
					c1.add(Calendar.DATE, -16);
					SimpleDateFormat sdfecha = new SimpleDateFormat("d/M/yyyy");
					String fechaInicio = sdfecha.format(c1.getTime());					
					String fechaFin = "16/" + (mes+1) + "/" + anio;
					
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
					int f = tabla1aQuincena.getRowCount()-1; // indice de la ultima fila		
					
					parameters.put("PD1","" + tabla1aQuincena.getValueAt(f, 2));
					parameters.put("PD2","" + tabla1aQuincena.getValueAt(f, 3));
					parameters.put("PD3","" + tabla1aQuincena.getValueAt(f, 4));
					parameters.put("PD4","" + tabla1aQuincena.getValueAt(f, 5));
					parameters.put("PD5","" + tabla1aQuincena.getValueAt(f, 6));
					parameters.put("PD6","" + tabla1aQuincena.getValueAt(f, 7));
					parameters.put("PD7","" + tabla1aQuincena.getValueAt(f, 8));
					parameters.put("PD8","" + tabla1aQuincena.getValueAt(f, 9));
					parameters.put("PD9","" + tabla1aQuincena.getValueAt(f, 10));
					parameters.put("PD10","" + tabla1aQuincena.getValueAt(f, 11));
					parameters.put("PD11","" + tabla1aQuincena.getValueAt(f, 12));
					parameters.put("PD12","" + tabla1aQuincena.getValueAt(f, 13));
					parameters.put("PD13","" + tabla1aQuincena.getValueAt(f, 14));
					parameters.put("PD14","" + tabla1aQuincena.getValueAt(f, 15));
					parameters.put("PD15","" + tabla1aQuincena.getValueAt(f, 16));
					parameters.put("PD16","" + tabla1aQuincena.getValueAt(f, 17));
					parameters.put("phoras","" + tabla1aQuincena.getValueAt(f, 18));
					
					// detalle horas
					parameters.put("h_compensar","" + label_horasCompensar.getText());
					parameters.put("h_quincena","" + labelHOrasQuincena1.getText());
					parameters.put("h_cargables","" + labelHsCargables.getText());
					parameters.put("h_nocargables","" + labelHsNoCargables.getText());
					
					JasperPrint print = JasperFillManager.fillReport("./reportes/jasper/res1aquinc.jasper", parameters, new JRBeanCollectionDataSource(getTupasHoras1aQuincena()) );
					//JasperPrint print = JasperFillManager.fillReport("./reportes/jasper/listadoHoras.jasper", parameters, new JREmptyDataSource());
										
					JasperExportManager.exportReportToPdfFile(print, "./reportes/pdf/res1aquinc_" + idFuncionario + ".pdf");
					JasperViewer.viewReport(print, false);
					
					
					
				} catch (JRException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}		
				
			
			}
		});
		btnNewButton_2.setBounds(857, 197, 151, 23);
		panel_2.add(btnNewButton_2);
		
//		monthChooser = new JMonthChooser();
		monthChooser.setBounds(10, 58, 109, 20);
		contentPane.add(monthChooser);
		
//		yearChooser = new JYearChooser();
		yearChooser.setBounds(129, 58, 68, 20);
		contentPane.add(yearChooser);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Segunda quincena", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 345, 1018, 226);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 33, 998, 163);
		panel.add(scrollPane);
		
//		tabla2aQuincena = new JTable();
		scrollPane.setViewportView(tabla2aQuincena);
		
		JLabel label = new JLabel("Horas a Compensar:");
		label.setBounds(10, 201, 124, 14);
		panel.add(label);
		
		labelHCompensar = new JLabel("");
		labelHCompensar.setHorizontalAlignment(SwingConstants.CENTER);
		labelHCompensar.setBounds(125, 201, 46, 14);
		panel.add(labelHCompensar);
		
		JLabel label_2 = new JLabel("Horas Cargables:");
		label_2.setBounds(224, 201, 102, 14);
		panel.add(label_2);
		
		labelHCargables = new JLabel("");
		labelHCargables.setHorizontalAlignment(SwingConstants.CENTER);
		labelHCargables.setBounds(336, 201, 46, 14);
		panel.add(labelHCargables);
		
		JLabel lblHorasDelMes_1 = new JLabel("Quincena 2:");
		lblHorasDelMes_1.setBounds(412, 201, 71, 14);
		panel.add(lblHorasDelMes_1);
		
		labelHOrasQuincena2 = new JLabel("");
		labelHOrasQuincena2.setHorizontalAlignment(SwingConstants.CENTER);
		labelHOrasQuincena2.setBounds(476, 201, 46, 14);
		panel.add(labelHOrasQuincena2);
		
		JLabel label_6 = new JLabel("Horas No Cargables:");
		label_6.setBounds(634, 201, 124, 14);
		panel.add(label_6);
		
		labelNoCargables = new JLabel("");
		labelNoCargables.setHorizontalAlignment(SwingConstants.CENTER);
		labelNoCargables.setBounds(757, 201, 46, 14);
		panel.add(labelNoCargables);
		
		JButton button = new JButton("Generar reporte");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorHistorialQuincenas controlador = new ControladorHistorialQuincenas();				
				//String func = ((String) listFuncionarios.getSelectedValue()).trim(); 
				int idFunc;
				VOFuncionario voFunc;
				String nombreFunc;
				try {
					//idFunc = controlador.obtenerIdFunNomApe(func);
					voFunc = controlador.obtenerVOFuncionario(idFuncionario);
					nombreFunc = voFunc.getNomFun() + " " + voFunc.getApeFun();
					
					String[] diaSemana = { "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" };		
					SimpleDateFormat sdf = new SimpleDateFormat("dd");
					Calendar c1 = Calendar.getInstance();			
					
					int mes =monthChooser.getMonth();
					int anio = yearChooser.getYear();

					c1.set(anio, mes, 17);				
					
					String str_quincena[] = new String[16];
					
					for (int i=0; i<=15; i++){		    
						String nomDia = diaSemana[c1.get(c1.DAY_OF_WEEK) - 1];
					    if (nomDia.equals("Do")){
					    	str_quincena[i] = "Do";
					    }else
					    	if (nomDia.equals("Sa")){
						    	str_quincena[i] = "Sa";
						    }else
						    	str_quincena[i] = "" + sdf.format(c1.getTime());
					    c1.add(Calendar.DATE, 1);
					}
					
				
					c1.add(Calendar.DATE, -16);
					SimpleDateFormat sdfecha = new SimpleDateFormat("d/M/yyyy");
					String fechaInicio = sdfecha.format(c1.getTime());
					int ultDiaMes = c1.getActualMaximum(c1.DAY_OF_MONTH);
					
					String fechaFin = ultDiaMes +"/" + (mes+1) + "/" + anio;
					
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
					int f = tabla2aQuincena.getRowCount()-1; // indice de la ultima fila		
					
					parameters.put("PD1","" + tabla2aQuincena.getValueAt(f, 2));
					parameters.put("PD2","" + tabla2aQuincena.getValueAt(f, 3));
					parameters.put("PD3","" + tabla2aQuincena.getValueAt(f, 4));
					parameters.put("PD4","" + tabla2aQuincena.getValueAt(f, 5));
					parameters.put("PD5","" + tabla2aQuincena.getValueAt(f, 6));
					parameters.put("PD6","" + tabla2aQuincena.getValueAt(f, 7));
					parameters.put("PD7","" + tabla2aQuincena.getValueAt(f, 8));
					parameters.put("PD8","" + tabla2aQuincena.getValueAt(f, 9));
					parameters.put("PD9","" + tabla2aQuincena.getValueAt(f, 10));
					parameters.put("PD10","" + tabla2aQuincena.getValueAt(f, 11));
					parameters.put("PD11","" + tabla2aQuincena.getValueAt(f, 12));
					
					if (tabla2aQuincena.getValueAt(f, 13) == null)
						parameters.put("PD12","" + "");
					else
						parameters.put("PD12","" + tabla2aQuincena.getValueAt(f, 13));
					
					if (tabla2aQuincena.getValueAt(f, 14) == null)
						parameters.put("PD13","" + "");
					else
						parameters.put("PD13","" + tabla2aQuincena.getValueAt(f, 14));
					
					if (tabla2aQuincena.getValueAt(f, 15) == null)
						parameters.put("PD14","" + "");
					else
						parameters.put("PD14","" + tabla2aQuincena.getValueAt(f, 15));
					
					if (tabla2aQuincena.getValueAt(f, 16) == null)
						parameters.put("PD15","" + "");
					else
						parameters.put("PD15","" + tabla2aQuincena.getValueAt(f, 16));
					
					if (tabla2aQuincena.getValueAt(f, 17) == null)
						parameters.put("PD16","" + "");
					else
						parameters.put("PD16","" + tabla2aQuincena.getValueAt(f, 17));
					parameters.put("phoras","" + tabla2aQuincena.getValueAt(f, 18));
					
					// detalle horas
					parameters.put("h_compensar","" + labelHCompensar.getText());
					parameters.put("h_quincena","" + labelHOrasQuincena2.getText());
					parameters.put("h_cargables","" + labelHCargables.getText());
					parameters.put("h_nocargables","" + labelNoCargables.getText());
					
					JasperPrint print = JasperFillManager.fillReport("./reportes/jasper/res2aquinc.jasper", parameters, new JRBeanCollectionDataSource(getTupasHoras2aQuincena()) );
					//JasperPrint print = JasperFillManager.fillReport("./reportes/jasper/listadoHoras.jasper", parameters, new JREmptyDataSource());
										
					JasperExportManager.exportReportToPdfFile(print, "./reportes/pdf/res2aquinc_" + idFuncionario + ".pdf");
					JasperViewer.viewReport(print, false);
					
					
					
				} catch (JRException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}		
			}
		});
		button.setBounds(857, 197, 151, 23);
		panel.add(button);
		
		JButton btnNewButton = new JButton("Cargar quincenas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo1aQuincena = new DefaultTableModel();	
				modelo1aQuincena.addColumn("Cliente");
				modelo1aQuincena.addColumn("Servicio");
				
				modelo2aQuincena = new DefaultTableModel();	
				modelo2aQuincena.addColumn("Cliente");
				modelo2aQuincena.addColumn("Servicio");
				
				
				
				
				String[] diaSemana = { "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" };		
				SimpleDateFormat sdf = new SimpleDateFormat("dd");
				Calendar c1 = Calendar.getInstance();				
				Calendar c2 = Calendar.getInstance();
				
				int mes =monthChooser.getMonth();
				int anio = yearChooser.getYear();
					
				c1.set(anio, mes, 1);				
				c2.set(anio, mes, 17);
				
				
				for (int i=1; i<=16; i++){		    
					String nomDia1aQuincena = diaSemana[c1.get(c1.DAY_OF_WEEK) - 1];
					String nomDia2aQuincena = diaSemana[c2.get(c2.DAY_OF_WEEK) - 1];
					
				    if (nomDia1aQuincena.equals("Do") || nomDia1aQuincena.equals("Sa")){
				    	modelo1aQuincena.addColumn(diaSemana[c1.get(c1.DAY_OF_WEEK) - 1]);
				    }else{
				       	modelo1aQuincena.addColumn(sdf.format(c1.getTime()));		    		    	
				    }
				    c1.add(Calendar.DATE, 1);
				    
				    
				    if (nomDia2aQuincena.equals("Do") || nomDia2aQuincena.equals("Sa")){
				    	modelo2aQuincena.addColumn(diaSemana[c2.get(c2.DAY_OF_WEEK) - 1]);
				    }else{
				       	modelo2aQuincena.addColumn(sdf.format(c2.getTime()));		    		    	
				    }
				    c2.add(Calendar.DATE, 1);
				    
				    
			    }
				modelo1aQuincena.addColumn("Horas");	
				modelo2aQuincena.addColumn("Horas");

				tabla1aQuincena.setModel(modelo1aQuincena);
				tabla2aQuincena.setModel(modelo2aQuincena);
				setAnchoColumnasTabla1aQuincena();
				setAnchoColumnasTabla2aQuincena();
				
				
				// centro valores de las columnas
				DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		        for (int k=2; k<=18;k++){
		        	tabla1aQuincena.getColumnModel().getColumn(k).setCellRenderer(modelocentrar);
		        	tabla2aQuincena.getColumnModel().getColumn(k).setCellRenderer(modelocentrar);
		        }
				
				
				ControladorHistorialQuincenas controlador = new ControladorHistorialQuincenas();
				//int idFunc = controlador.obtenerIdFunNomApe(strnomFunc);
				listar1aQuincenaTabla(idFuncionario);
				listar2aQuincenaTabla(idFuncionario);
				
				// tabla horasMensuales
				
				limpiartablaHorasMensuales();
				//idFunc = controlador.obtenerIdFunNomApe(strnomFunc);
				String fechaInicio = "1" +"/" + (mes+1) + "/" + anio; 
				int ultDiaMes = c1.getActualMaximum(c1.DAY_OF_MONTH);				
				String fechaFin = ultDiaMes +"/" + (mes+1) + "/" + anio;					
				listarTablaHorasMensuales(idFuncionario, fechaInicio, fechaFin);
				
				int horasMensualesACompensar = Integer.parseInt(label_horasCompensar.getText()) + Integer.parseInt(labelHCompensar.getText());
				int horasMensualesCargables = Integer.parseInt(labelHsCargables.getText()) + Integer.parseInt(labelHCargables.getText());
				
				VOFuncionario voFuncionario = controlador.obtenerVOFuncionario(idFuncionario);
				
				//int horasMensualesQuincena= Integer.parseInt(labelHOrasQuincena1.getText()) + Integer.parseInt(labelHOrasQuincena2.getText());
				int horasMensualesQuincena= Integer.parseInt(labelHOrasQuincena1.getText()) + voFuncionario.getHorasDia();
				
					
				
				int hteoricasMes= getDiasHabiles(mes+1, anio);
				//int hcontratadas = Integer.parseInt(textFieldHORAS.getText());


				
				
			}
		});
		btnNewButton.setBounds(207, 55, 135, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Funcionario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 27, 89, 20);
		contentPane.add(lblNewLabel);
		
		labelNombreFuncionario = new JLabel("");
		labelNombreFuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelNombreFuncionario.setBounds(97, 27, 245, 20);
		contentPane.add(labelNombreFuncionario);
		
		VOFuncionario voFuncionario = controlador.obtenerVOFuncionario(idFuncionario);
		labelNombreFuncionario.setText(voFuncionario.getNomFun() + " " + voFuncionario.getApeFun());
		
		modeloHorasMensuales = new DefaultTableModel();
		modeloHorasMensuales.addColumn("Clientes");
		modeloHorasMensuales.addColumn("Servicios");
		modeloHorasMensuales.addColumn("Horas");
		modeloHorasMensuales.addColumn("Selec.");
		
		
		// centro valores de las columnas
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        setAnchoColumnastablaHorasMensuales();
		
		listarFuncionarios(0);
		
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
		
		setAnchoColumnasTabla1aQuincena();
		
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
	}
	
	private void setAnchoColumnasTabla1aQuincena() {
        int anchoTabla = 998;  //Ancho del jScrollPane1.
        int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
        //TableColumn nos provee de m�todos para minimizar, maximizar,etc. columnas de tabla.
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
        
        for(int i=0; i<tabla1aQuincena.getColumnCount(); i++) {
            //Obtenemos el modelo de las columnas de la tabla.
            columnaTabla = tabla1aQuincena.getColumnModel().getColumn(i);

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
	
	
	
	private void setAnchoColumnasTabla2aQuincena() {
        int anchoTabla = 998;  //Ancho del jScrollPane1.
        int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
        //TableColumn nos provee de m�todos para minimizar, maximizar,etc. columnas de tabla.
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
        
        for(int i=0; i<tabla2aQuincena.getColumnCount(); i++) {
            //Obtenemos el modelo de las columnas de la tabla.
            columnaTabla = tabla2aQuincena.getColumnModel().getColumn(i);

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
	
	private void listar1aQuincenaTabla(int idFuncionario){		
		int mes =monthChooser.getMonth() +1;
		int anio = yearChooser.getYear();

		Calendar c1 = Calendar.getInstance();		

		c1.set(anio, mes-1, 1);		
		
		SimpleDateFormat sdfecha = new SimpleDateFormat("d/M/yyyy");		
		String fechaInicial = "1/" + mes + "/" + anio;
		
		ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();		
		String FECHA_HOY = "16/" + mes + "/" + anio;
		
		List<VOTuplaHorasFuncionario> lstTupla = controlador.listarClienteSrvicio(idFuncionario, fechaInicial, FECHA_HOY);		

		
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
    					if (controlador.existeHorasFuncClienteServicio(idFuncionario, dataTupla.getCliente(), dataTupla.getServicio(), fechaInicial)){
	    					int horas = controlador.obtenerHorasFuncClienteServicio(idFuncionario, dataTupla.getCliente(), dataTupla.getServicio(), fechaInicial);    					
	    					fila[i+1] = horas;
	    					sumHoras = sumHoras + horas;
    					}
					} catch (SQLException e) {
						e.printStackTrace();
					}
    				c1.add(Calendar.DATE, 1);
    			}
    			fila[18] = sumHoras;
    			c1.add(Calendar.DATE, -16);    			    		    			
				
				modelo1aQuincena.addRow(fila);
    		}
    		
    		// pie de tabla quicena
    		int horasPorDiaFuncionario = controlador.obtenerVOFuncionario(idFuncionario).getHorasDia(); // horas por dia que trabaja el funcionario, seg�n fue contratado
    		int horasAcompensar=0;
    		int horasTotalQuincena = 0;
    		int horasCargablesTotalQuincena = 0;
    		Object[] filaAlPie = new Object[19];
    		filaAlPie[1] = "T o t a l   H o r a s";
    		for (int i=1; i<=16; i++){
    			fechaInicial = sdfecha.format(c1.getTime());
    			try {
    				int horasTrabajadasDia = controlador.sumaHorasClienteServicioDia(idFuncionario, fechaInicial);
    				int horasCargablesTrabajadasDia = controlador.sumaHorasCargablesClienteServicioDia(idFuncionario, fechaInicial);
					filaAlPie[i+1] = horasTrabajadasDia;
					if (horasTrabajadasDia > horasPorDiaFuncionario){
						horasAcompensar = horasAcompensar + horasTrabajadasDia - horasPorDiaFuncionario;
					}
					horasTotalQuincena = horasTotalQuincena + horasTrabajadasDia;
					horasCargablesTotalQuincena = horasCargablesTotalQuincena + horasCargablesTrabajadasDia;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
    			
    			c1.add(Calendar.DATE, 1);			
    			
    		}
    		filaAlPie[18] = horasTotalQuincena;    		
    		
    		
    		modelo1aQuincena.addRow(filaAlPie);    	
    		tabla1aQuincena.setModel(modelo1aQuincena);    		
    		
			label_horasCompensar.setText("" + horasAcompensar);
			//labelHOrasQuincena1.setText("" + (horasTotalQuincena - horasAcompensar));
			
			//int horasTeoricasQuincena1 = getDiasHabiles1aQuincena(mes,anio) * Integer.parseInt(textFieldHORAS.getText());
			
			
			VOFuncionario voFuncionario = controlador.obtenerVOFuncionario(idFuncionario);
    		int horasFunc = voFuncionario.getHorasDia();
			
			
			
			int horasTeoricasQuincena1 = getDiasHabiles1aQuincena(mes,anio) * horasFunc;
			
			//System.out.println(getDiasHabiles1aQuincena(9,anio));
			
			labelHOrasQuincena1.setText("" + horasTeoricasQuincena1); 
			
			labelHsCargables.setText("" + horasCargablesTotalQuincena);
			labelHsNoCargables.setText("" + (horasTotalQuincena - horasCargablesTotalQuincena));		
    	}else{
    		label_horasCompensar.setText("0");
			labelHOrasQuincena1.setText("0");
			labelHsCargables.setText("0");
			labelHsNoCargables.setText("0");
    	}
		
	}
	
	
	private void listar2aQuincenaTabla(int idFuncionario){		
		int mes =monthChooser.getMonth() +1;
		int anio = yearChooser.getYear();

		Calendar c1 = Calendar.getInstance();		

		c1.set(anio, mes-1, 17);
		int ultDiaMes = c1.getActualMaximum(c1.DAY_OF_MONTH);
		
		SimpleDateFormat sdfecha = new SimpleDateFormat("d/M/yyyy");		
		String fechaInicial = "17/" + mes + "/" + anio;
		
		ControladorMantenimientoHoras controlador = new ControladorMantenimientoHoras();		
		String FECHA_HOY = ultDiaMes +"/" + mes + "/" + anio;
		
		List<VOTuplaHorasFuncionario> lstTupla = controlador.listarClienteSrvicio(idFuncionario, fechaInicial, FECHA_HOY);		

		int largo2aQuinc = ultDiaMes - 17 +1;
		if (lstTupla.size() >0){
    		Iterator<VOTuplaHorasFuncionario> iterTupla = lstTupla.iterator();
    		while (iterTupla.hasNext()){
    			VOTuplaHorasFuncionario dataTupla = iterTupla.next();
    			Object[] fila = new Object[19];
    			fila[0] = " " + dataTupla.getCliente();
    			fila[1] = " " + dataTupla.getServicio();    			
    			
    			int sumHoras=0;
    			for (int i=1; i<=largo2aQuinc; i++){
    				fechaInicial = sdfecha.format(c1.getTime());
    				try {
    					if (controlador.existeHorasFuncClienteServicio(idFuncionario, dataTupla.getCliente(), dataTupla.getServicio(), fechaInicial)){
	    					int horas = controlador.obtenerHorasFuncClienteServicio(idFuncionario, dataTupla.getCliente(), dataTupla.getServicio(), fechaInicial);    					
	    					fila[i+1] = horas;
	    					sumHoras = sumHoras + horas;
    					}
					} catch (SQLException e) {
						e.printStackTrace();
					}
    				c1.add(Calendar.DATE, 1);
    			}
    			fila[18] = sumHoras;
    			c1.add(Calendar.DATE, -(largo2aQuinc));    			    		    			
				
				modelo2aQuincena.addRow(fila);
    		}
    		
    		// pie de tabla quicena
    		int horasPorDiaFuncionario = controlador.obtenerVOFuncionario(idFuncionario).getHorasDia(); // horas por dia que trabaja el funcionario, seg�n fue contratado
    		int horasAcompensar=0;
    		int horasTotalQuincena = 0;
    		int horasCargablesTotalQuincena = 0;
    		Object[] filaAlPie = new Object[19];
    		filaAlPie[1] = "T o t a l   H o r a s";
    		for (int i=1; i<=largo2aQuinc; i++){
    			fechaInicial = sdfecha.format(c1.getTime());
    			try {
    				int horasTrabajadasDia = controlador.sumaHorasClienteServicioDia(idFuncionario, fechaInicial);
    				int horasCargablesTrabajadasDia = controlador.sumaHorasCargablesClienteServicioDia(idFuncionario, fechaInicial);
					filaAlPie[i+1] = horasTrabajadasDia;
					if (horasTrabajadasDia > horasPorDiaFuncionario){
						horasAcompensar = horasAcompensar + horasTrabajadasDia - horasPorDiaFuncionario;
					}
					horasTotalQuincena = horasTotalQuincena + horasTrabajadasDia;
					horasCargablesTotalQuincena = horasCargablesTotalQuincena + horasCargablesTrabajadasDia;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
    			
    			c1.add(Calendar.DATE, 1);			
    			
    		}
    		filaAlPie[18] = horasTotalQuincena;    		
    		
    		
    		modelo2aQuincena.addRow(filaAlPie);    	
    		tabla2aQuincena.setModel(modelo2aQuincena);    		
    		
    		labelHCompensar.setText("" + horasAcompensar);
    		labelHOrasQuincena2.setText("" + (horasTotalQuincena - horasAcompensar));
    		VOFuncionario voFuncionario = controlador.obtenerVOFuncionario(idFuncionario);
    		int horasFunc = voFuncionario.getHorasDia();
    		//int horasTeoricas2aQuicena = getDiasHabiles2aQuicena(mes,anio) * Integer.parseInt(textFieldHORAS.getText());
    		int horasTeoricas2aQuicena = getDiasHabiles2aQuicena(mes,anio) * horasFunc;
    		labelHOrasQuincena2.setText("" + horasTeoricas2aQuicena);
    		
    		
    		
			labelHCargables.setText("" + horasCargablesTotalQuincena);
			labelNoCargables.setText("" + (horasTotalQuincena - horasCargablesTotalQuincena));		
    	}else{
    		labelHCompensar.setText("0");
    		labelHOrasQuincena2.setText("0");
			labelHCargables.setText("0");
			labelNoCargables.setText("0");
    	}
		
	}
	
	
	private void limpiarTabla1aQuincena()    {
        int filas = modelo1aQuincena.getRowCount();
        if (filas > 0) {
            for (int i = 0; i < filas; i++) {
            	modelo1aQuincena.removeRow(0);           	
            }
        }
    }
	
	private void listarFuncionarios(int i){
		DefaultListModel modeloServicios = new DefaultListModel();
		ControladorHistorialQuincenas controlador = new ControladorHistorialQuincenas();
		List<String> lstFuncionarios = controlador.listarFuncionarios(i);
		if (lstFuncionarios.size() > 0){
			java.util.Iterator<String> iterServicios = lstFuncionarios.iterator();
			while(iterServicios.hasNext()){				
				String servicio = iterServicios.next();
				modeloServicios.addElement("  " + servicio);
			}						
		}
		/*if (i == 0){
			listFuncionarios.setModel(modeloServicios);
		}*/
	}
	
	private void listarTablaHoras(int idCli, String fechaInicio, String fechaFin){
		ControladorHistorialQuincenas controlador = new ControladorHistorialQuincenas();
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
		//textFieldTotalHoras.setText("" + totalHoras);		
	}
	
	private void limpiartableHorasTotales()    {
        int filas = modeloHorasTotales.getRowCount();
        if (filas > 0) {
            for (int i = 0; i < filas; i++) {
            	modeloHorasTotales.removeRow(0);           	
            }
        }
    }
	
	
	private void limpiartablaHorasMensuales()    {		
        int filas = modeloHorasMensuales.getRowCount();
        if (filas > 0) {
            for (int i = 0; i < filas; i++) {
            	modeloHorasMensuales.removeRow(0);           	
            }
        }
    }
	
	
	private void limpiarTablasQuincenas(){
		int filas = modelo1aQuincena.getRowCount();
		if (filas > 0) {
            for (int i = 0; i < filas; i++) {
            	modelo1aQuincena.removeRow(0);           	
            }
        }
		
		filas = modelo2aQuincena.getRowCount();
		if (filas > 0) {
            for (int i = 0; i < filas; i++) {
            	modelo2aQuincena.removeRow(0);           	
            }
        }
		
		
	}
		
	
	
	public List getTupasHoras1aQuincena(){
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
		
		for (int i=0; i< tabla1aQuincena.getRowCount()-1; i++){			
	
			String cliente = (String) tabla1aQuincena.getValueAt(i, 0);
			String servicio = (String) tabla1aQuincena.getValueAt(i, 1);
			
			if (tabla1aQuincena.getValueAt(i, 2) != null)
				FD1 = "" + tabla1aQuincena.getValueAt(i, 2);
			else
				FD1 = "";
			if (tabla1aQuincena.getValueAt(i, 3) != null)
				FD2 = "" + tabla1aQuincena.getValueAt(i, 3);
			else
				FD2 = "";
			if (tabla1aQuincena.getValueAt(i, 4) != null)
				FD3 = "" + tabla1aQuincena.getValueAt(i, 4);
			else
				FD3 = "";
			if (tabla1aQuincena.getValueAt(i, 5) != null)
				FD4 = "" + tabla1aQuincena.getValueAt(i, 5);
			else
				FD4 = "";
			if (tabla1aQuincena.getValueAt(i, 6) != null)
				FD5 = "" + tabla1aQuincena.getValueAt(i, 6);
			else
				FD5 = "";
			if (tabla1aQuincena.getValueAt(i, 7) != null)
				FD6 = "" + tabla1aQuincena.getValueAt(i, 7);
			else
				FD6 = "";
			if (tabla1aQuincena.getValueAt(i, 8) != null)
				FD7 = "" + tabla1aQuincena.getValueAt(i, 8);
			else
				FD7 = "";
			if (tabla1aQuincena.getValueAt(i, 9) != null)
				FD8 = "" + tabla1aQuincena.getValueAt(i, 9);
			else
				FD8 = "";
			if (tabla1aQuincena.getValueAt(i, 10) != null)
				FD9 = "" + tabla1aQuincena.getValueAt(i, 10);
			else
				FD9 = "";
			if (tabla1aQuincena.getValueAt(i, 11) != null)
				FD10 = "" + tabla1aQuincena.getValueAt(i, 11);
			else
				FD10 = "";
			if (tabla1aQuincena.getValueAt(i, 12) != null)
				FD11 = "" + tabla1aQuincena.getValueAt(i, 12);
			else
				FD11 = "";
			if (tabla1aQuincena.getValueAt(i, 13) != null)
				FD12 = "" + tabla1aQuincena.getValueAt(i, 13);
			else
				FD12 = "";
			if (tabla1aQuincena.getValueAt(i, 14) != null)
				FD13 = "" + tabla1aQuincena.getValueAt(i, 14);
			else
				FD13 = "";
			if (tabla1aQuincena.getValueAt(i, 15) != null)
				FD14 = "" + tabla1aQuincena.getValueAt(i, 15);
			else
				FD14 = "";
			if (tabla1aQuincena.getValueAt(i, 16) != null)
				FD15 = "" + tabla1aQuincena.getValueAt(i, 16);
			else
				FD15 = "";
			if (tabla1aQuincena.getValueAt(i, 17) != null)
				FD16 = "" + tabla1aQuincena.getValueAt(i, 17);
			else
				FD16 = "";
			
			if (tabla1aQuincena.getValueAt(i, 18) != null)
				horas = (int) tabla1aQuincena.getValueAt(i, 18);
			
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
	
	public List getTupasHoras2aQuincena(){
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
		
		for (int i=0; i< tabla2aQuincena.getRowCount()-1; i++){			
	
			String cliente = (String) tabla2aQuincena.getValueAt(i, 0);
			String servicio = (String) tabla2aQuincena.getValueAt(i, 1);
			
			if (tabla2aQuincena.getValueAt(i, 2) != null)
				FD1 = "" + tabla2aQuincena.getValueAt(i, 2);
			else
				FD1 = "";
			if (tabla2aQuincena.getValueAt(i, 3) != null)
				FD2 = "" + tabla2aQuincena.getValueAt(i, 3);
			else
				FD2 = "";
			if (tabla2aQuincena.getValueAt(i, 4) != null)
				FD3 = "" + tabla2aQuincena.getValueAt(i, 4);
			else
				FD3 = "";
			if (tabla2aQuincena.getValueAt(i, 5) != null)
				FD4 = "" + tabla2aQuincena.getValueAt(i, 5);
			else
				FD4 = "";
			if (tabla2aQuincena.getValueAt(i, 6) != null)
				FD5 = "" + tabla2aQuincena.getValueAt(i, 6);
			else
				FD5 = "";
			if (tabla2aQuincena.getValueAt(i, 7) != null)
				FD6 = "" + tabla2aQuincena.getValueAt(i, 7);
			else
				FD6 = "";
			if (tabla2aQuincena.getValueAt(i, 8) != null)
				FD7 = "" + tabla2aQuincena.getValueAt(i, 8);
			else
				FD7 = "";
			if (tabla2aQuincena.getValueAt(i, 9) != null)
				FD8 = "" + tabla2aQuincena.getValueAt(i, 9);
			else
				FD8 = "";
			if (tabla2aQuincena.getValueAt(i, 10) != null)
				FD9 = "" + tabla2aQuincena.getValueAt(i, 10);
			else
				FD9 = "";
			if (tabla2aQuincena.getValueAt(i, 11) != null)
				FD10 = "" + tabla2aQuincena.getValueAt(i, 11);
			else
				FD10 = "";
			if (tabla2aQuincena.getValueAt(i, 12) != null)
				FD11 = "" + tabla2aQuincena.getValueAt(i, 12);
			else
				FD11 = "";
			if (tabla2aQuincena.getValueAt(i, 13) != null)
				FD12 = "" + tabla2aQuincena.getValueAt(i, 13);
			else
				FD12 = "";
			if (tabla2aQuincena.getValueAt(i, 14) != null)
				FD13 = "" + tabla2aQuincena.getValueAt(i, 14);
			else
				FD13 = "";
			if (tabla2aQuincena.getValueAt(i, 15) != null)
				FD14 = "" + tabla2aQuincena.getValueAt(i, 15);
			else
				FD14 = "";
			if (tabla2aQuincena.getValueAt(i, 16) != null)
				FD15 = "" + tabla2aQuincena.getValueAt(i, 16);
			else
				FD15 = "";
			if (tabla2aQuincena.getValueAt(i, 17) != null)
				FD16 = "" + tabla2aQuincena.getValueAt(i, 17);
			else
				FD16 = "";
			
			if (tabla2aQuincena.getValueAt(i, 18) != null)
				horas = (int) tabla2aQuincena.getValueAt(i, 18);
			
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
	
	private void setAnchoColumnastablaHorasMensuales() {
        int anchoTabla = 480;  //Ancho del jScrollPane1.
        int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
        //TableColumn nos provee de m�todos para minimizar, maximizar,etc. columnas de tabla.
        TableColumn  columnaTabla = null;
        int ancho1=40,
        	ancho2=35,
        	ancho3=10,
        	ancho4=15;
        
       // for(int i=0; i<tablaHorasMensuales.getColumnCount(); i++) {
            //Obtenemos el modelo de las columnas de la tabla.
         //   columnaTabla = tablaHorasMensuales.getColumnModel().getColumn(i);
/*
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
            }
            //Aplicamos el ancho para cada columna de la tabla.
            columnaTabla.setPreferredWidth(anchoColumna);
            columnaTabla.setMinWidth(anchoColumnaMin);
            columnaTabla.setMaxWidth(anchoColumnaMax);
        }*/
    }
	
	private void listarTablaHorasMensuales(int idFun, String fechaInicio, String fechaFin){
		ControladorHistorialQuincenas controlador = new ControladorHistorialQuincenas();
		List<VOTuplaHorasFuncionario> lstHoras= controlador.listarHorasMensualesCliente(idFun, fechaInicio, fechaFin);
		int totalHoras =0;
		if (lstHoras.size() >0){
			Iterator<VOTuplaHorasFuncionario> iterHoras = lstHoras.iterator();
			while (iterHoras.hasNext()){
				VOTuplaHorasFuncionario dataHoras = iterHoras.next();
				Object[] fila = new Object[4];
				fila[0] = "  " + dataHoras.getCliente();
				fila[1] = "  " + dataHoras.getServicio();				
				fila[2] = dataHoras.getHoras();				
				fila[3] = new Boolean(false);
				totalHoras = totalHoras + Integer.parseInt(dataHoras.getHoras());
				modeloHorasMensuales.addRow(fila);
			}			
		}		
		//textFieldTotalHoras.setText("" + totalHoras);		
	}
	
	
	
	public int getDiasHabiles(int mes, int anio) {	
		int diffDays=0;		
		Calendar fechaInicial = new GregorianCalendar(anio,mes-1,01);		
		Calendar c1 = Calendar.getInstance();	
		c1.set(anio, mes-1, 1);
		int ultDiaMes = c1.getActualMaximum(c1.DAY_OF_MONTH);		
		Calendar fechaFinal= new GregorianCalendar(anio,mes-1,ultDiaMes);		
		//mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
		 while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
			 //si el dia de la semana de la fecha minima es diferente de sabado o domingo
			 if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				//se aumentan los dias de diferencia entre min y max
                 diffDays++;				 
			 }
			//se suma 1 dia para hacer la validacion del siguiente dia.
			 fechaInicial.add(Calendar.DATE, 1);				 
		 }
		return diffDays;
	}
	
	public int getDiasHabiles1aQuincena(int mes, int anio) {	
		int diffDays=0;		
		Calendar fechaInicial = new GregorianCalendar(anio,mes-1,01);		
		//Calendar c1 = Calendar.getInstance();	
		//c1.set(anio, mes-1, 1);
		//int ultDiaMes = c1.getActualMaximum(c1.DAY_OF_MONTH);		
		Calendar fechaFinal= new GregorianCalendar(anio,mes-1,16);		
		//mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
		 while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
			 //si el dia de la semana de la fecha minima es diferente de sabado o domingo
			 if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				//se aumentan los dias de diferencia entre min y max
                 diffDays++;				 
			 }
			//se suma 1 dia para hacer la validacion del siguiente dia.
			 fechaInicial.add(Calendar.DATE, 1);				 
		 }
		return diffDays;
	}
	
	public int getDiasHabiles2aQuicena(int mes, int anio) {	
		int diffDays=0;		
		Calendar fechaInicial = new GregorianCalendar(anio,mes-1,17);		
		Calendar c1 = Calendar.getInstance();	
		c1.set(anio, mes-1, 1);
		int ultDiaMes = c1.getActualMaximum(c1.DAY_OF_MONTH);		
		Calendar fechaFinal= new GregorianCalendar(anio,mes-1,ultDiaMes);		
		//mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
		 while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
			 //si el dia de la semana de la fecha minima es diferente de sabado o domingo
			 if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				//se aumentan los dias de diferencia entre min y max
                 diffDays++;				 
			 }
			//se suma 1 dia para hacer la validacion del siguiente dia.
			 fechaInicial.add(Calendar.DATE, 1);				 
		 }
		return diffDays;
	}
	
	class CELL_RENDERER extends JCheckBox implements TableCellRenderer{			
		public Component getTableCellRendererComponent(JTable arg0,	Object value, boolean arg2, boolean arg3, int arg4, int arg5) {
			setSelected((value != null && ((Boolean) value).booleanValue()));
			setBackground(arg0.getBackground());
			setHorizontalAlignment(JLabel.CENTER);
			return this;
		}		
	}
	
	class CELL_EDITOR extends DefaultCellEditor{
		public CELL_EDITOR(JCheckBox checkBox) {
			super(checkBox);
			checkBox.setHorizontalAlignment(JLabel.CENTER);	
		}
	}
}
