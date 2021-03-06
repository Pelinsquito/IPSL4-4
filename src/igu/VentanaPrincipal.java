package igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.Atleta;
import src.DataBaseManager;
import src.GestorComprobaciones;
import src.MyTableModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class VentanaPrincipal {
	
	private final String SQLError = "Ha ocurrido un error ejecutando una consulta a la base de datos! REF:";

	private JFrame frame;
	private MyTableModel modelEquipo;
	private JPanel pnControles;
	private JButton btnPagos;
	private JPanel pnTittle;
	private JPanel pnControlPagos;
	private JButton btnActualizar;
	private JTable tablePagos;
	private MyTableModel modelPagos;
	private JButton btnPagar;
	
	
	
	private JPanel pnClasificacion;
	private JPanel panelDatos;
	private JPanel panelContenido;
	private JTable tablaResultados;
	private JPanel panelBoton;
	private JButton btnMostrarResultados;
	private JPanel panelTabla;
	private JLabel lblTitulo;
	private JLabel lblNombreDeCarrera;
	private JPanel panelNombreCarrera;
	private JTextField textoNombreCarrera;
	
	//Otros datos
		private String m = "masculino";
		private String f = "femenino";
		private JPanel panelFiltroCarrera;
		private JPanel panelTituloYColumnas;
		private JPanel panelColumnas;
		private JLabel lblCDNI;
		private JLabel lblCPosicion;
		private JLabel lblCSexo;
		private JLabel lblCDorsal;
		private JLabel lblCNombre;
		private JLabel lblCApellidos;
		private JLabel lblCFNacimiento;
		private JLabel lblCFInscripcion;
		private JLabel lblCTiempo;
		private JButton btnClasificacion;
		private JButton btnVolverAlMenu;
		private JButton btMenu;
		private JButton btnRegistrarCorredor;
		
		private JPanel panelFicheros;
		private JPanel panelSeleccion;
		private JButton btnSeleccion;
		private JPanel panelInformacion;
		private JLabel lblCarreraElegida;
		private JPanel panelBotones;
		private JButton btnCargar;
		private JPanel panelVolver;
		private JButton btnVolverAlMen;
		private File archivo;
		private boolean sinFallosFormato = true;
		private boolean sinFallosDni = true;
		private boolean sinFallosNombreCarrera = true;
		private GestorComprobaciones gc = new GestorComprobaciones();	
		private JButton btnRegistrarTiempos;
		
		private JPanel pnlAtletasSegunCarrera;
		private JTable tableAtletas;
		private JButton btnMostrar;
		private JButton btnMenu;
		private JLabel lblListarAtletasSegn;
		private JTextField txtfldCarrera1;
		private JButton btnAtletas;
		private MyTableModel modelAtletas;
		
		private JButton btnAsignarDorsales;
		private JButton btnAsignarDorsal;
		private String carreraSeleccionada;
		private JLabel lblCarreraSeleccionada;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(getPnTittle(), "panelTitulo");
		frame.getContentPane().add(getPanel_4(), "panelPagos");
		frame.getContentPane().add(getPnClasificacion(), "panelClasificacion");
		frame.getContentPane().add(getPanelFicheros(), "panelFicheros");
		frame.getContentPane().add(getPnlAtletasSegunCarrera(), "pnlAtletasSegunCarrera");
		
	}
	private JPanel getPnControles() {
		if (pnControles == null) {
			pnControles = new JPanel();
			pnControles.setBounds(357, 11, 292, 469);
			pnControles.setLayout(new GridLayout(0, 1, 0, 0));
			pnControles.add(getBtnRegistrarCorredor());
			pnControles.add(getBtnPagos());
			pnControles.add(getBtnClasificacion());
			pnControles.add(getBtnRegistrarTiempos());
			pnControles.add(getBtnAtletas());
			pnControles.add(getBtnAsignarDorsales());
		}
		return pnControles;
	}
	
	private JPanel getPnClasificacion()
	{
		if(pnClasificacion == null)
		{
			pnClasificacion = new JPanel();
			pnClasificacion.setLayout(new BorderLayout(0, 0));
			pnClasificacion.add(getPanelDatos(), BorderLayout.CENTER);
			pnClasificacion.add(getPanelFiltroCarrera(), BorderLayout.WEST);
		}
		return pnClasificacion;
	}
	private JButton getBtnPagos() {
		if (btnPagos == null) {
			btnPagos = new JButton("Control de pagos");
			btnPagos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					actualizarTablaPagos();
					card.show(frame.getContentPane(), "panelPagos");
				}
			});
		}
		return btnPagos;
	}
	private JPanel getPnTittle() {
		if (pnTittle == null) {
			pnTittle = new JPanel();
			pnTittle.setLayout(null);
			pnTittle.add(getPnControles());
		}
		return pnTittle;
	}
	private JPanel getPanel_4() {
		if (pnControlPagos == null) {
			pnControlPagos = new JPanel();
			pnControlPagos.setLayout(null);
			pnControlPagos.add(getBtnActualizar());
			pnControlPagos.add(getTable_1());
			pnControlPagos.add(getBtnPagar());
			pnControlPagos.add(getBtMenu());
		}
		return pnControlPagos;
	}
	private JButton getBtnActualizar() {
		if (btnActualizar == null) {
			btnActualizar = new JButton("Actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					actualizarTablaPagos();
				}
			});
			btnActualizar.setBounds(483, 134, 156, 23);
		}
		return btnActualizar;
	}
	
	private void addToModel(MyTableModel model, ArrayList<String[]> datos)
	{
		for(String[] dato : datos)
		{
			model.addRow(dato);
		}
	}
	
	private void removeModelContent(MyTableModel model)
	{
	    if(model.getRowCount() > 0)
	    	for (int i = model.getRowCount()-1; i>=0; i--)
	    		model.removeRow(i);
	}
	private JTable getTable_1() {
		if (tablePagos == null) {
			modelPagos = new MyTableModel();
			modelPagos.addColumn("Nombre Y Apellidos");
			modelPagos.addColumn("UO");
			modelPagos.addColumn("DNI");
			modelPagos.addColumn("Plazo");
			tablePagos = new JTable(modelPagos);
			actualizarTablaPagos();
			tablePagos.setBounds(10, 11, 463, 432);
		}
		return tablePagos;
	}
	
	public void actualizarTablaPagos()
	{
		ArrayList<String[]> atletasSinPagar;
		ArrayList<String[]> atletasFueraDePlazo;
		try {
			//Consulta para obtener los datos
			atletasSinPagar = DataBaseManager.getAtletasSinPagar();
			atletasFueraDePlazo = DataBaseManager.getAtletasFueraPlazoPago();
		    
		    
		    //Si ya hay datos en la tabla, los elimina
		    removeModelContent(modelPagos);
		    
		    String[] cabeceras = {"Nombre y Apellidos", "DNI", "Carrera", "Plazo"};
		    modelPagos.addRow(cabeceras);
		    int rowCount = 0;
		    
		    for(String[] atleta : atletasSinPagar)
			{
		    	boolean coincide = false;
		    	for(String[] fueraPlazo: atletasFueraDePlazo)
		    	{
		    		if(atleta[1].equals(fueraPlazo[0]) && atleta[2].equals(fueraPlazo[1]))
		    		{
		    				coincide = true;
		    				break;
		    		}
		    	}
		    	if(coincide)
		    		atleta[3] = "Fuera de plazo";
		    	
			    modelPagos.addRow(atleta);
		    	rowCount ++;
		    	
			}
		    
		} catch (SQLException e1) 
		{
			JOptionPane.showMessageDialog(null, SQLError + "control_pagos");
			System.err.println( SQLError + "control_pagos");
			e1.printStackTrace();
		}
	}
	
	public void miembrosEquipo()
	{
		ArrayList<String[]> miembrosEquipo;
		try {
			//Consulta para obtener los datos
			miembrosEquipo = DataBaseManager.getNombresEquipo();
		    
		    //Si ya hay datos en la tabla, los elimina
		    removeModelContent(modelEquipo);
		    
		    String[] cabeceras = {"Nombre y Apellidos", "DNI", "UO"};
		    modelEquipo.addRow(cabeceras);
		    
		    addToModel(modelEquipo, miembrosEquipo);
		    
		} catch (SQLException e1) 
		{
			JOptionPane.showMessageDialog(null, SQLError + "miembros_equipo");
			System.err.println( SQLError + "miembros_equipo");
			e1.printStackTrace();
		}
	}
	private JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton("Pagar");
			btnPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					if(tablePagos.getSelectedRow()>= 0)
					{
						int row = tablePagos.getSelectedRow();
						String DNI = (String)tablePagos.getModel().getValueAt(row, 1);
						String carrera = (String)tablePagos.getModel().getValueAt(row, 2);
						
						try 
						{
							DataBaseManager.actualizarPagado(DNI, carrera);
							JOptionPane.showMessageDialog(null, "Se ha realizado el pago de "+tablePagos.getModel().getValueAt(row, 1) +" para la carrera "
									+tablePagos.getModel().getValueAt(row, 2)+".");
						}
						catch (SQLException e) 
						{
							JOptionPane.showMessageDialog(null, "Error actualizando datos en la base de datos! Puede que sus cambios no hayan sido modificados!");
							e.printStackTrace();
						}
						actualizarTablaPagos();
					}
					else
						JOptionPane.showMessageDialog(null, "Para realizar el pago debe primero seleccionar un atleta que no haya pagado.");
				}
			});
			btnPagar.setBounds(483, 178, 156, 23);
		}
		return btnPagar;
	}
	
	//ESTE PANEL CONTIENE: LA TABLA, EL TITULO DE ESTA, LAS LABELS DE LAS COLUMNAS DE LA TABLA Y EL BTN DE MOSTRAR RESULTADOS
		private JPanel getPanelDatos() {
			if (panelDatos == null) {
				panelDatos = new JPanel();
				panelDatos.setLayout(new BorderLayout(0, 0));
				panelDatos.add(getPanelContenido());
				panelDatos.add(getPanelBoton(), BorderLayout.SOUTH);
			}
			return panelDatos;
		}
		
		//PANEL CONTENIDO EN PANELDATOS, CONTIENE LA TABLA, SU TITULO Y LAS LABELS DE LAS COLUMNAS DE LA TABLA
		private JPanel getPanelContenido() {
			if (panelContenido == null) {
				panelContenido = new JPanel();
				panelContenido.setLayout(new BorderLayout(0, 0));
				panelContenido.add(getPanelTabla(), BorderLayout.CENTER);
				panelContenido.add(getPanelTituloYColumnas(), BorderLayout.NORTH);
			}
			return panelContenido;
		}
		
		
		
		
		
		/*
		 * A PARTIR DE AQUI TODO LO RELACIONADO SOLO CON LA TABLA
		 */
		
		//PANEL QUE CONTIENE LA TABLA
		private JPanel getPanelTabla() {
			if (panelTabla == null) {
				panelTabla = new JPanel();
				panelTabla.setLayout(new BorderLayout(0, 0));
				panelTabla.add(getTablaResultados());
			}
			return panelTabla;
		}
		
		//LA TABLA EN SI CON SUS COLUMNAS
		private JTable getTablaResultados() {
			if (tablaResultados == null) {
				MyTableModel model = new MyTableModel();
				model.addColumn("DNI");
				model.addColumn("Posici�n");
				model.addColumn("Sexo");	
				model.addColumn("Dorsal");
				model.addColumn("Nombre");
				model.addColumn("Apellidos");
				model.addColumn("Fecha nacimiento");
				model.addColumn("Fecha Inscripcion");
				model.addColumn("Tiempo");
				tablaResultados = new JTable(model);
			}
			return tablaResultados;
		}
		
		
		
		
		
		/*
		 * A PARTIR DE AQUI TODO LO RELACIONADO CON EL BOTON DE MUESTRA DE RESULTADOS EN LA TABLA
		 */
		
		//PANEL CONTENIDO EN PANELDATOS, CONTIENE EL BOTON MOSTRAR RESULTADOS
		private JPanel getPanelBoton() {
			if (panelBoton == null) {
				panelBoton = new JPanel();
				panelBoton.setLayout(new BorderLayout(0, 0));
				panelBoton.add(getBtnMostrarResultados(), BorderLayout.EAST);
				panelBoton.add(getBtnVolverAlMenu(), BorderLayout.WEST);
			}
			return panelBoton;
		}
		
		//BOTON ENCARGADO DE MOSTRAR LOS RESULTADOS DE CIERTA CARRERA ESCRITA EN EL JTEXTFIELD EN LA JTABLE
		private JButton getBtnMostrarResultados() {
			if (btnMostrarResultados == null) {
				btnMostrarResultados = new JButton("Mostrar resultados");
				btnMostrarResultados.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String carrera = textoNombreCarrera.getText();
						List<String[]> participantes;
						List<Atleta> atletasConTiempo = new ArrayList<Atleta>();
						List<Atleta> atletasSinTiempo = new ArrayList<Atleta>();	//ya que el order by coloca primero a los sin tiempo
						
						
						try{
							MyTableModel model = (MyTableModel) tablaResultados.getModel();
							participantes = DataBaseManager.getCorredores();
							
							for(int i = 0; i<participantes.size();i++){
								if(participantes.get(i)[5].equals(carrera)) {
									if(participantes.get(i)[8] != null){
										atletasConTiempo.add(new Atleta(participantes.get(i)[0],participantes.get(i)[1],participantes.get(i)[2],
										participantes.get(i)[3],participantes.get(i)[4],participantes.get(i)[5],participantes.get(i)[6],
										participantes.get(i)[7],participantes.get(i)[8],participantes.get(i)[9]));
									}
									else{
										atletasSinTiempo.add(new Atleta(participantes.get(i)[0],participantes.get(i)[1],participantes.get(i)[2],
										participantes.get(i)[3],participantes.get(i)[4],participantes.get(i)[5],participantes.get(i)[6],
										participantes.get(i)[7],participantes.get(i)[8],participantes.get(i)[9]));
									}
								}
							}						
							
							//Si hay datos en la tabla, los borra
							removeModelContent((MyTableModel)tablaResultados.getModel());
							
							
							
							int contadorPosM = 1;	//contador para las posiciones masculinas
							int contadorPosF = 1;	//contador para las posiciones femeninas
							for(int i = 0; i<atletasConTiempo.size(); i++){	//A�ADIMOS PRIMERO LOS QUE TIENEN TIEMPO
								
								if(atletasConTiempo.get(i).getSexo().equals(m)) {
									Object[] temp = {atletasConTiempo.get(i).getDni() ,contadorPosM, atletasConTiempo.get(i).getSexo(), 
											atletasConTiempo.get(i).getDorsal(), atletasConTiempo.get(i).getNombre(), atletasConTiempo.get(i).getApellidos(),
											atletasConTiempo.get(i).getFechaDeNacimiento(), atletasConTiempo.get(i).getFecha_inscripcion(), 
											atletasConTiempo.get(i).getTiempo()};
									
									atletasConTiempo.get(i).setPosicion(String.valueOf(contadorPosM));
									model.addRow(temp);
									contadorPosM++;		
								}
								else{
									Object[] temp = {atletasConTiempo.get(i).getDni() ,contadorPosF, atletasConTiempo.get(i).getSexo(), 
											atletasConTiempo.get(i).getDorsal(), atletasConTiempo.get(i).getNombre(), atletasConTiempo.get(i).getApellidos(),
											atletasConTiempo.get(i).getFechaDeNacimiento(), atletasConTiempo.get(i).getFecha_inscripcion(), 
											atletasConTiempo.get(i).getTiempo()};
									
									atletasConTiempo.get(i).setPosicion(String.valueOf(contadorPosF));
									model.addRow(temp);
									contadorPosF++;	
								}
							}

							for(int i = 0; i<atletasSinTiempo.size(); i++){	//Y LUEGO LOS QUE NO
								if(atletasSinTiempo.get(i).getSexo().equals(m)) {
									Object[] temp = {atletasSinTiempo.get(i).getDni() ,contadorPosM, atletasSinTiempo.get(i).getSexo(), 
											atletasSinTiempo.get(i).getDorsal(), atletasSinTiempo.get(i).getNombre(), atletasSinTiempo.get(i).getApellidos(),
											atletasSinTiempo.get(i).getFechaDeNacimiento(), atletasSinTiempo.get(i).getFecha_inscripcion(), 
											"---"};
									
									atletasSinTiempo.get(i).setPosicion(String.valueOf(contadorPosF));
									model.addRow(temp);
									contadorPosM++;
								}
								else{
									Object[] temp = {atletasSinTiempo.get(i).getDni() ,contadorPosF, atletasSinTiempo.get(i).getSexo(), 
											atletasSinTiempo.get(i).getDorsal(), atletasSinTiempo.get(i).getNombre(), atletasSinTiempo.get(i).getApellidos(),
											atletasSinTiempo.get(i).getFechaDeNacimiento(), atletasSinTiempo.get(i).getFecha_inscripcion(), 
											atletasSinTiempo.get(i).getTiempo()};
									
									atletasSinTiempo.get(i).setPosicion(String.valueOf(contadorPosF));
									model.addRow(temp);
									contadorPosF++;
								}
							}
							
							tablaResultados.setModel(model);
						}
						
						catch (SQLException ex){
							JOptionPane.showMessageDialog(null, "Error en atleta");
							System.err.println("Error en atleta");
							ex.printStackTrace();
						}
						
						
					}
				});
			}
			return btnMostrarResultados;
		}
		
		
		
		
		
		/*
		 * A PARTIR DE AQUI TODO LO RELACIONADO CON EL FILTRO DEL NOMBRE DE LA CARRERA (PARTE IZQ DE LA IGU)
		 */
		
		//PANEL QUE CONTIENE TANTO LA LABEL QUE INDICA LA ESCRITURA COMO EL JTEXTFIELD
		private JPanel getPanelFiltroCarrera() {
			if (panelFiltroCarrera == null) {
				panelFiltroCarrera = new JPanel();
				panelFiltroCarrera.setLayout(new GridLayout(2, 1, 0, 0));
				panelFiltroCarrera.add(getLblNombreDeCarrera());
				panelFiltroCarrera.add(getPanelNombreCarrera());
			}
			return panelFiltroCarrera;
		}
			
		//LABEL QUE INDICA QUE SE DEBE FILTRAR
		private JLabel getLblNombreDeCarrera() {
			if (lblNombreDeCarrera == null) {
				lblNombreDeCarrera = new JLabel("Nombre de la carrera a filtrar:");
				lblNombreDeCarrera.setHorizontalAlignment(SwingConstants.CENTER);
				lblNombreDeCarrera.setVerticalAlignment(SwingConstants.BOTTOM);
				lblNombreDeCarrera.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
			}
			return lblNombreDeCarrera;
		}
		
		//PANEL QUE CONTIENE EL JTEXTFIELD
		private JPanel getPanelNombreCarrera() {
			if (panelNombreCarrera == null) {
				panelNombreCarrera = new JPanel();
				panelNombreCarrera.setLayout(new BorderLayout(0, 0));
				panelNombreCarrera.add(getTextoNombreCarrera(), BorderLayout.NORTH);
			}
			return panelNombreCarrera;
		}
		
		//JTEXTFIELD PARA INDICAR EL NOMBRE DE LA CARRERA
		private JTextField getTextoNombreCarrera() {
			if (textoNombreCarrera == null) {
				textoNombreCarrera = new JTextField();
				textoNombreCarrera.setColumns(35);
			}
			return textoNombreCarrera;
		}
		
		
		
		
		
		
		
		/*
		 * A PARTIR DE AQUI TODO LO RELACIONADO CON EL T�TULO: "RESULTADOS" Y LAS COLUMNAS DE LA JTABLE 
		 */
			
		
		//PANEL QUE CONTIENE LA LABEL DEL TITULO Y DE LAS CATEGORIAS DE LA TABLA
		private JPanel getPanelTituloYColumnas() {
			if (panelTituloYColumnas == null) {
				panelTituloYColumnas = new JPanel();
				panelTituloYColumnas.setLayout(new BorderLayout(0, 0));
				panelTituloYColumnas.add(getLblTitulo());
				panelTituloYColumnas.add(getPanelColumnas(), BorderLayout.SOUTH);
			}
			return panelTituloYColumnas;
		}	
		
		//LABEL DEL TITULO DE LA TABLA
		private JLabel getLblTitulo() {
			if (lblTitulo == null) {
				lblTitulo = new JLabel("Resultados");
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			}
			return lblTitulo;
		}
		
		//PANEL QUE SOLO CONTIENE LAS LABELS DE LAS CATEGORIAS DE LA TABLA
		private JPanel getPanelColumnas() {
			if (panelColumnas == null) {
				panelColumnas = new JPanel();
				panelColumnas.setLayout(new GridLayout(0, 9, 0, 0));
				panelColumnas.add(getLblCDNI());
				panelColumnas.add(getLblCPosicion());
				panelColumnas.add(getLblCSexo());
				panelColumnas.add(getLblCDorsal());
				panelColumnas.add(getLblCNombre());
				panelColumnas.add(getLblCApellidos());
				panelColumnas.add(getLblCFNacimiento());
				panelColumnas.add(getLblCFInscripcion());
				panelColumnas.add(getLblCTiempo());
			}
			return panelColumnas;
		}
		
		
		//LABELS QUE INDICAN LAS COLUMNAS DE LA JTABLE
		private JLabel getLblCDNI() {
			if (lblCDNI == null) {
				lblCDNI = new JLabel("DNI");
				lblCDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCDNI.setHorizontalAlignment(SwingConstants.CENTER);
			}
			return lblCDNI;
		}
		private JLabel getLblCPosicion() {
			if (lblCPosicion == null) {
				lblCPosicion = new JLabel("Posici\u00F3n");
				lblCPosicion.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCPosicion.setHorizontalAlignment(SwingConstants.CENTER);
			}
			return lblCPosicion;
		}
		private JLabel getLblCSexo() {
			if (lblCSexo == null) {
				lblCSexo = new JLabel("Sexo");
				lblCSexo.setHorizontalAlignment(SwingConstants.CENTER);
				lblCSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCSexo;
		}
		private JLabel getLblCDorsal() {
			if (lblCDorsal == null) {
				lblCDorsal = new JLabel("Dorsal");
				lblCDorsal.setHorizontalAlignment(SwingConstants.CENTER);
				lblCDorsal.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCDorsal;
		}
		private JLabel getLblCNombre() {
			if (lblCNombre == null) {
				lblCNombre = new JLabel("Nombre");
				lblCNombre.setHorizontalAlignment(SwingConstants.CENTER);
				lblCNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCNombre;
		}
		private JLabel getLblCApellidos() {
			if (lblCApellidos == null) {
				lblCApellidos = new JLabel("Apellidos");
				lblCApellidos.setHorizontalAlignment(SwingConstants.CENTER);
				lblCApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCApellidos;
		}
		private JLabel getLblCFNacimiento() {
			if (lblCFNacimiento == null) {
				lblCFNacimiento = new JLabel("F. Nacimiento");
				lblCFNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
				lblCFNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCFNacimiento;
		}
		private JLabel getLblCFInscripcion() {
			if (lblCFInscripcion == null) {
				lblCFInscripcion = new JLabel("F. Inscripci\u00F3n");
				lblCFInscripcion.setHorizontalAlignment(SwingConstants.CENTER);
				lblCFInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCFInscripcion;
		}
		private JLabel getLblCTiempo() {
			if (lblCTiempo == null) {
				lblCTiempo = new JLabel("Tiempo");
				lblCTiempo.setHorizontalAlignment(SwingConstants.CENTER);
				lblCTiempo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCTiempo;
		}
	private JButton getBtnClasificacion() {
		if (btnClasificacion == null) {
			btnClasificacion = new JButton("Clasificacion");
			btnClasificacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelClasificacion");
				}
			});
		}
		return btnClasificacion;
	}
	private JButton getBtnVolverAlMenu() {
		if (btnVolverAlMenu == null) {
			btnVolverAlMenu = new JButton("Volver al Menu");
			btnVolverAlMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelTitulo");
				}
			});
		}
		return btnVolverAlMenu;
	}
	private JButton getBtMenu() {
		if (btMenu == null) {
			btMenu = new JButton("Volver al menu");
			btMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelTitulo");
				}
			});
			btMenu.setBounds(483, 212, 156, 23);
		}
		return btMenu;
	}
	private JButton getBtnRegistrarCorredor() {
		if (btnRegistrarCorredor == null) {
			btnRegistrarCorredor = new JButton("Registrar Corredor");
			btnRegistrarCorredor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					VentanaDatosAtleta ventanaRegistro = new VentanaDatosAtleta();
					ventanaRegistro.setAlwaysOnTop(true);
					ventanaRegistro.setVisible(true);
				}
			});
		}
		return btnRegistrarCorredor;
	}
	
	private JButton getBtnRegistrarTiempos() {
		if (btnRegistrarTiempos == null) {
			btnRegistrarTiempos = new JButton("Registrar Tiempos");
			btnRegistrarTiempos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelFicheros");
				}
			});
		}
		return btnRegistrarTiempos;
	}
	
	private JPanel getPanelFicheros() {
		if(panelFicheros==null) {
			panelFicheros = new JPanel();
			panelFicheros.setLayout(new BorderLayout(0, 0));
			panelFicheros.add(getPanelSeleccion(), BorderLayout.SOUTH);
			panelFicheros.add(getPanelInformacion(), BorderLayout.CENTER);
		}
		return panelFicheros;
	}
	
	private JPanel getPanelSeleccion() {
		if (panelSeleccion == null) {
			panelSeleccion = new JPanel();
			panelSeleccion.setLayout(new BorderLayout(0, 0));
			panelSeleccion.add(getPanelBotones(), BorderLayout.EAST);
			panelSeleccion.add(getPanelVolver(), BorderLayout.WEST);
		}
		return panelSeleccion;
	}
	
	//BOTON QUE ABRE UN JFILECHOOSER PARA SELECCIONAR EL FICHERO A CARGAR
	private JButton getBtnSeleccion(){
		if (btnSeleccion == null) {
			btnSeleccion = new JButton("Seleccionar archivo");
			btnSeleccion.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	JFileChooser fc = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
						    "TXT files", "txt");
					fc.setFileFilter(filter);
	                fc.setCurrentDirectory(new java.io.File("user.home"));
	                fc.setDialogTitle("Selector de archivos");
	                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	                fc.setMultiSelectionEnabled(false);
	                if (fc.showOpenDialog(btnSeleccion) == JFileChooser.APPROVE_OPTION) {
	                	if(!fc.getSelectedFile().getName().endsWith(".txt"))
	                		JOptionPane.showMessageDialog(null, "Solo se admiten archivos de texto (.txt)");
	                	else
	                	{
	                		btnCargar.setEnabled(true);
		                	archivo = fc.getSelectedFile();   
		                	lblCarreraElegida.setText("Carrera elegida: " + obtenNombreCarrera(archivo.getName()));
	                	}
	                }
	            }
	        });
		}	
		return btnSeleccion;
	}
	private JPanel getPanelInformacion() {
		if (panelInformacion == null) {
			panelInformacion = new JPanel();
			panelInformacion.setLayout(new BorderLayout(0, 0));
			panelInformacion.add(getLblCarreraElegida(), BorderLayout.CENTER);
		}
		return panelInformacion;
	}
	private JLabel getLblCarreraElegida() {
		if (lblCarreraElegida == null) {
			lblCarreraElegida = new JLabel("Carrera elegida:");
			lblCarreraElegida.setHorizontalAlignment(SwingConstants.CENTER);
			lblCarreraElegida.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
			
		}
		return lblCarreraElegida;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.add(getBtnSeleccion());
			panelBotones.add(getBtnCargar());
		}
		return panelBotones;
	}
	
	//BOTON QUE LLAMA AL METODO QUE CARGA EL FICHERO SELECCIONADO EN LA BASE DE DATOS
	private JButton getBtnCargar() {
		if (btnCargar == null) {
			btnCargar = new JButton("Cargar");
			btnCargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						cargaContenido(archivo);
						if(sinFallosFormato && sinFallosDni && sinFallosNombreCarrera) {
							JOptionPane.showMessageDialog(null, "Operaci�n realizada con �xito.");
							btnCargar.setEnabled(false);
							archivo = null;
							lblCarreraElegida.setText("Carrera elegida:");
						}
						sinFallosFormato = true;
						sinFallosDni = true;
						sinFallosNombreCarrera = true;
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					btnCargar.setEnabled(false);
				}
			});
			btnCargar.setEnabled(false);
		}
		return btnCargar;
	}
	
	private JPanel getPanelVolver() {
		if (panelVolver == null) {
			panelVolver = new JPanel();
			panelVolver.add(getBtnVolverAlMen());
		}
		return panelVolver;
	}
	private JButton getBtnVolverAlMen() {
		if (btnVolverAlMen == null) {
			btnVolverAlMen = new JButton("Volver al menu");
			btnVolverAlMen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelTitulo");
				}
			});
		}
		return btnVolverAlMen;
	}
	
	//METODO QUE CARGA EL CONTENIDO EN LA BBDD, SI ALGUN DATO ESTA MAL NO LO CARGA, NO IMPLICA QUE NO CARGUE LA TOTALIDAD DEL FICHERO, SOLO AQUELLOS DATOS QUE TENGAN EL FORMATO CORRECTO
	void cargaContenido(File archivo) throws FileNotFoundException, IOException, SQLException {
	      boolean errorFormato = false;
	      boolean errorPresencia = false;
	      boolean errorNombreCarrera = false;
	      
	      String carrera = archivo.getName();
	      String nombreCarrera = obtenNombreCarrera(carrera);
	      
	      if(!gc.comprobadorCarrera(nombreCarrera)) {
	    	  errorNombreCarrera = true;
	      }
	      
	      
		  String cadena;							//ESTRUCTURA DEL FICHERO: TIEMPO DNI
	      FileReader f = new FileReader(archivo);
	      BufferedReader b = new BufferedReader(f);
	      
	      while((cadena = b.readLine())!=null) {
	          String[] partes = cadena.split(" ");	//dividimos las partes
	          if(partes.length==2) {
	        	  if(partes[0].equals("---")) {     	//sin tiempo?
	        		  DataBaseManager.a�adirTiempoAtleta(nombreCarrera, partes[0], partes[1]);						//buscamos su dni en la bbdd y le asignamos su tiempo null
	        	  }
	        	  else {								//con tiempo?
	        		  if(gc.comprobadorTiempos(partes[0])) { //si el tiempo es valido buscamos su dni en la bbdd y le asignamos su tiempo
	        			  DataBaseManager.a�adirTiempoAtleta(nombreCarrera, partes[0], partes[1]);
	        		  }
	        		  else {
	        			  errorFormato = true;
	        		  }
	        	  }
	          	//Lo que conseguimos as� es que a�ada los corredores cuyo formato es correcto, los que tengan un formato incorrecto han de ser revisados por el cliente
	          
	          	//Vamos a comprobar tambien que el corredor est� en la carrera, si no est� lo daremos a conocer:
	          	if(!gc.comprobadorPresencia(partes[1],nombreCarrera)) {
	          		errorPresencia = true;
	          	}
	          }
	          else {
	        	 errorFormato = true; 
	          }
	      }
	      if(errorNombreCarrera) {
	    	  JOptionPane.showMessageDialog(null, "La carrera referente al nombre del fichero no existe en la base de datos.");
        	  sinFallosNombreCarrera = false;
	      }
	      else {
	    	  if(errorFormato) {
	    		  JOptionPane.showMessageDialog(null, "Algunos tiempos no han sido a�adidos a la base de datos. Por favor, compruebe el fichero de tiempos.");
	    		  sinFallosFormato=false;
	    	  }
	    	  if(errorPresencia) {
	    		  JOptionPane.showMessageDialog(null, "Alguno de los corredores del fichero no se encuentra en �sta carrera, por tanto no ha sido a�adido.");
	    		  sinFallosDni = false;
	    	  }
	      }
	      
	      b.close();
	}

	private String obtenNombreCarrera(String carrera) {
		int caracteresBorrar = 4; //la extension .txt
		return carrera.substring(0, carrera.length()-caracteresBorrar);
	}
	private JButton getBtnAsignarDorsales() {
		if (btnAsignarDorsales == null) {
			btnAsignarDorsales = new JButton("Asignar dorsales");
			btnAsignarDorsales.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					DialogDorsales dialogDorsales = new DialogDorsales();
					dialogDorsales.setAlwaysOnTop(true);
					dialogDorsales.setVisible(true);
				}
			});
		}
		return btnAsignarDorsales;
	}
	
	private JPanel getPnlAtletasSegunCarrera() {
		if (pnlAtletasSegunCarrera == null) {
			pnlAtletasSegunCarrera = new JPanel();
			pnlAtletasSegunCarrera.setBackground(Color.LIGHT_GRAY);
			pnlAtletasSegunCarrera.setLayout(null);
			pnlAtletasSegunCarrera.add(getTableAtletas());
			pnlAtletasSegunCarrera.add(getBtnMostrar());
			pnlAtletasSegunCarrera.add(getBtnMenu());
			pnlAtletasSegunCarrera.add(getLblListarAtletasSegn());
			pnlAtletasSegunCarrera.add(getTxtfldCarrera1());
			pnlAtletasSegunCarrera.add(getBtnAsignarDorsal());
			pnlAtletasSegunCarrera.add(getLblCarreraSeleccionada());
		}
		return pnlAtletasSegunCarrera;
	}

	private JTable getTableAtletas() {
		if (tableAtletas == null) {
			modelAtletas = new MyTableModel();
			modelAtletas.addColumn("DNI");
			modelAtletas.addColumn("Nombre");
			modelAtletas.addColumn("Sexo");
			modelAtletas.addColumn("Fecha de inscripci�n");
			modelAtletas.addColumn("Estado");
			modelAtletas.addColumn("Dorsal");
			tableAtletas = new JTable(modelAtletas);
			tableAtletas.setBounds(10, 11, 461, 328);
		}
		return tableAtletas;
	}

	private void actualizarTablaAtletas() {
		ArrayList<String[]> atletas;
		try {
				if(DataBaseManager.existeCarrera(txtfldCarrera1.getText()))
				{
					atletas = DataBaseManager.listarAtletas(txtfldCarrera1.getText());
					carreraSeleccionada = txtfldCarrera1.getText();
					lblCarreraSeleccionada.setText(carreraSeleccionada + " seleccionada");
					removeModelContent(modelAtletas);
					String[] cabeceras = { "DNI", "Nombre", "Sexo", "Fecha de Inscripci�n", "Estado", "Dorsal" };
					modelAtletas.addRow(cabeceras);
					for (String[] a : atletas) {
						if(a[5] == null)
							a[5] = "No asignado";
						modelAtletas.addRow(a);
					}
					tableAtletas.setModel(modelAtletas);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "La carrera especificada no se encuentra en la base de datos.");
				}
				

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, SQLError + "lista de atletas");
			e.printStackTrace();
		}

	}


	private JButton getBtnMostrar() {
		if (btnMostrar == null) {
			btnMostrar = new JButton("Mostrar");
			btnMostrar.setMnemonic('M');
			btnMostrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarTablaAtletas();
				}
			});
			btnMostrar.setBounds(686, 60, 89, 23);
		}
		return btnMostrar;
	}

	private JButton getBtnMenu() {
		if (btnMenu == null) {
			btnMenu = new JButton("Men\u00FA");
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelTitulo");
				}
			});
			btnMenu.setMnemonic('U');
			btnMenu.setBounds(481, 316, 145, 23);
		}
		return btnMenu;
	}

	private JLabel getLblListarAtletasSegn() {
		if (lblListarAtletasSegn == null) {
			lblListarAtletasSegn = new JLabel("Listar atletas seg\u00FAn la siguiente carrera: ");
			lblListarAtletasSegn.setDisplayedMnemonic('L');
			lblListarAtletasSegn.setLabelFor(getTxtfldCarrera1());
			lblListarAtletasSegn.setBounds(481, 35, 205, 14);
		}
		return lblListarAtletasSegn;
	}

	private JTextField getTxtfldCarrera1() {
		if (txtfldCarrera1 == null) {
			txtfldCarrera1 = new JTextField();
			txtfldCarrera1.setBounds(481, 61, 195, 20);
			txtfldCarrera1.setColumns(10);
		}
		return txtfldCarrera1;
	}

	private JButton getBtnAtletas() {
		if (btnAtletas == null) {
			btnAtletas = new JButton("Atletas");
			btnAtletas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CardLayout card = (CardLayout) frame.getContentPane().getLayout();
					//actualizarTablaAtletas();
					card.show(frame.getContentPane(), "pnlAtletasSegunCarrera");
				}
			});
		}
		return btnAtletas;
	}
	private JButton getBtnAsignarDorsal() {
		if (btnAsignarDorsal == null) {
			btnAsignarDorsal = new JButton("Asignar Dorsal");
			btnAsignarDorsal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(tableAtletas.getSelectedRow()>= 0)
					{
						int row = tableAtletas.getSelectedRow();
						String dni = (String)tableAtletas.getValueAt(row, 0);
						String dorsal = (String)tableAtletas.getValueAt(row, 5);
						String carrera = carreraSeleccionada;
						String estado = (String)tableAtletas.getValueAt(row, 4);
						if(dorsal.equals("No asignado"))
						{
							if(estado.equals("pagado"))
							{
								try 
								{
									int siguienteDorsal = DataBaseManager.getSiguienteDorsalDisponible(carrera);
									dorsal  = ""+siguienteDorsal;
									DataBaseManager.a�adirDorsalCorredor(dni, carrera, dorsal);
									JOptionPane.showMessageDialog(null, "Dorsal "+dorsal+" a�adido al corredor "+dni+" para la carrera "+carrera);
								} 
								catch (SQLException e1) {
									JOptionPane.showMessageDialog(null, "No se han podido realizar los cambios!");
									e1.printStackTrace();
								}
								
							}
							else
								JOptionPane.showMessageDialog(null, "No puedes asignar dorsal a un corredor que a�n no ha pagado.");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "No puedes asignar dorsal a un corredor que ya tiene un dorsal asignado");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Para ejecutar esta opci�n debe seleccionar el atleta al que quiere asignar un dorsal.");
					}
				}
			});
			btnAsignarDorsal.setBounds(479, 117, 147, 23);
		}
		return btnAsignarDorsal;
	}
	private JLabel getLblCarreraSeleccionada() {
		if (lblCarreraSeleccionada == null) {
			lblCarreraSeleccionada = new JLabel("Ninguna Carrera Seleccionada");
			lblCarreraSeleccionada.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCarreraSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
			lblCarreraSeleccionada.setBounds(10, 350, 461, 23);
		}
		return lblCarreraSeleccionada;
	}
}
