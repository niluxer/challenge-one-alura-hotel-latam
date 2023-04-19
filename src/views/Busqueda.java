package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import controller.HuespedController;
import controller.ReservaController;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	JTabbedPane panel;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	HuespedController huespedController = new HuespedController();
	ReservaController reservaController = new ReservaController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 300, 42);
		contentPane.add(lblNewLabel_4);
		
		//JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		cargarTablaReservas();
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		
		cargarTablaHuespedes();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarActionPerformed();
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		FontIcon iconSearch = FontIcon.of(FontAwesomeSolid.SEARCH);
		iconSearch.setIconSize(20);
		iconSearch.setIconColor(Color.WHITE);
		lblBuscar.setIcon(iconSearch);
		
		//JPanel btnEditar = new JPanel();
		JButton btnEditar = new JButton();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificar();
                //limpiarTabla();
                //cargarTabla();
            }
        });
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		FontIcon iconEdit = FontIcon.of(FontAwesomeSolid.EDIT);
		iconEdit.setIconSize(20);
		iconEdit.setIconColor(Color.WHITE);
		lblEditar.setIcon(iconEdit);

		btnEditar.add(lblEditar);
		
		//JPanel btnEliminar = new JPanel();
		JButton btnEliminar = new JButton();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminar();
                //limpiarTabla();
                //cargarTabla();
            }
        });
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		FontIcon iconTrash = FontIcon.of(FontAwesomeSolid.TRASH);
		iconTrash.setIconSize(20);
		iconTrash.setIconColor(Color.WHITE);
		lblEliminar.setIcon(iconTrash);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
	}

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void buscarActionPerformed() 
    {                 
    	int selectedIndex = panel.getSelectedIndex();
    	if (selectedIndex == 0)
    	{
    		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tbReservas.getModel())); 
    		sorter.setRowFilter(RowFilter.regexFilter(txtBuscar.getText()));

    		tbReservas.setRowSorter(sorter);
    	} else if (selectedIndex == 1)
    	{
    		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tbHuespedes.getModel())); 
    		sorter.setRowFilter(RowFilter.regexFilter(txtBuscar.getText()));

    		tbHuespedes.setRowSorter(sorter);
    	}
        
    }
    
    private void cargarTablaHuespedes(){
    	var huespedes = this.huespedController.listar();
    	huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFecha_nacimiento().toString(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getReserva_id() }));
    }

    private void cargarTablaReservas(){
    	var reservas = this.reservaController.listar();
    	reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getFecha_entrada().toString(), reserva.getFecha_salida().toString(), reserva.getValor(), reserva.getForma_pago() }));
    }
    
    private boolean tieneFilaElegida(int tabElegido) {
        if (tabElegido == 0)
        	return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
        else if(tabElegido == 1)
        	return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
        return false;
    }
    
    private void modificar() {
    	int selectedIndex = panel.getSelectedIndex();
        if (tieneFilaElegida(selectedIndex)) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        if (selectedIndex == 0)
	        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    //Integer id = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);
	                	Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	                    String fecha_entrada = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
	                    String fecha_salida = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
	                    double valor = Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
	                    String forma_pago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
	
	                    int filasModificadas = this.reservaController.modificar(fecha_entrada, fecha_salida, valor, forma_pago, id);
	                    
	                    JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        else if (selectedIndex == 1)
            Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
            .ifPresentOrElse(fila -> {
            	Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                String nombres = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
                String apellidos = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
                String fecha_nacimiento = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3);
                String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
                String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
                int reserva_id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

                int filasModificadas = this.huespedController.modificar(nombres, apellidos, fecha_nacimiento, nacionalidad, telefono, reserva_id, id);
                
                JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
            }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));

    }    

    private void eliminar() {
    	int selectedIndex = panel.getSelectedIndex();
        if (tieneFilaElegida(selectedIndex)) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }
        
        if (selectedIndex == 0)
	        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf( modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	
	                    int cantidadEliminada = this.reservaController.eliminar(id);
			
	                    modelo.removeRow(tbReservas.getSelectedRow());
	
	                    JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        else if (selectedIndex == 1)
            Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
            .ifPresentOrElse(fila -> {
                Integer id = Integer.valueOf( modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

                int cantidadEliminada = this.huespedController.eliminar(id);
	
                modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

                JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");
            }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));

    }
    
}
