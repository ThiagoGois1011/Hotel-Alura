package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DataBase.Hospede;
import DataBase.HospedeDAO;
import DataBase.Reserva;
import DataBase.ReservaController;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
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
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
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
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), tbHospedes, null);
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
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
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
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
		
		ReservaController r = new ReservaController();
		HospedeDAO h = new HospedeDAO(r.getConnection());
		JPanel btnbuscar = new JPanel();
		
		
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				tbReservas.clearSelection();
				tbHospedes.clearSelection();
				int i2 = modelo.getRowCount();
				int i3 = modeloHospedes.getRowCount();
				for(int i = 0; i < i2;i++) {
					modelo.removeRow(0);
				}
				for(int i = 0; i < i3;i++) {
					modeloHospedes.removeRow(0);
				}
				
				if(txtBuscar.getText().isEmpty()) {
					List<Hospede> hospedes = h.listar();
					hospedes.forEach(evt -> {
						modeloHospedes.addRow(new Object[]{evt.getId(),evt.getNome(), evt.getSobrenome(),
								evt.getDataNascimento(),evt.getNascionalidade(), evt.getTelefone(), evt.getIdReserva()});	
					});
					
					List<Reserva> reservas1 = r.listar();
					reservas1.forEach(t -> {
						modelo.addRow(new Object[]{t.getId(),t.getDataEntrada(), t.getDataSaida(),
								t.getValor(),t.getFormaPagamento()});
					});
					return;
				}
				try {
					
					List<Hospede> hospedes = h.BuscarHospode(Integer.valueOf(txtBuscar.getText()));
					hospedes.forEach(evt -> {
						modeloHospedes.addRow(new Object[]{evt.getId(),evt.getNome(), evt.getSobrenome(),
								evt.getDataNascimento(),evt.getNascionalidade(), evt.getTelefone(), evt.getIdReserva()});
					});
					
					List<Reserva> reservas = r.BuscarPorNReserva(Integer.valueOf(txtBuscar.getText()));
					reservas.forEach(evt -> {
						modelo.addRow(new Object[]{evt.getId(),evt.getDataEntrada(), evt.getDataSaida(),
								evt.getId(),evt.getFormaPagamento()});
					});
					
					
				}catch(NumberFormatException e1) {
					
				}
				
				List<Hospede> hospedes = h.BuscarHospode(txtBuscar.getText());
				hospedes.forEach(evt -> {
					modeloHospedes.addRow(new Object[]{evt.getId(),evt.getNome(), evt.getSobrenome(),
							evt.getDataNascimento(),evt.getNascionalidade(), evt.getTelefone(), evt.getIdReserva()});
					List<Reserva> reservas = r.BuscarPorNReserva(evt.getIdReserva());
					reservas.forEach(t -> {
						modelo.addRow(new Object[]{t.getId(),t.getDataEntrada(), t.getDataSaida(),
								t.getId(),t.getFormaPagamento()});
					});
					
				});
				

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
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mouseClicked(MouseEvent e) {
					if(tbReservas.getSelectedRow() == -1 && tbHospedes.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Você deve selecionar uma linha para poder editar.");
						return;
					}
					
					try {
						String datain =  String.valueOf(tbReservas.getValueAt(tbReservas.getSelectedRow(), 1));
						String dataout =  String.valueOf(tbReservas.getValueAt(tbReservas.getSelectedRow(), 2));

						String formaPagemento =  String.valueOf(tbReservas.getValueAt(tbReservas.getSelectedRow(), 4));
						
						r.Editar(datain,dataout,formaPagemento);
					}catch(ArrayIndexOutOfBoundsException ey) {
						
					}
					
					try {
						String nome =  String.valueOf(tbReservas.getValueAt(tbHospedes.getSelectedRow(), 1));
						String sobrenome =  String.valueOf(tbReservas.getValueAt(tbHospedes.getSelectedRow(), 2));
						String datanascimento =  String.valueOf(tbHospedes.getValueAt(tbReservas.getSelectedRow(), 3));
						String nascionalidade =  String.valueOf(tbHospedes.getValueAt(tbReservas.getSelectedRow(), 4));
						String telefone =  String.valueOf(tbHospedes.getValueAt(tbReservas.getSelectedRow(), 5));
						
						h.Editar(nome,sobrenome ,datanascimento, nascionalidade, telefone);
					}catch(ArrayIndexOutOfBoundsException ey) {
						
					}
					
					
						
						
						try {
							r.getConnection().commit();
							JOptionPane.showMessageDialog(null, "editado com sucesso.");
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					
					
					
					
	
				}
		});
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
		
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbReservas.getSelectedRow() == -1 && tbHospedes.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Você deve selecionar uma linha para poder deletar.");
					return;
				}
				String idS = "";
				String idSH = "";
				
				if(tbReservas.getSelectedRow() >= 0) {
					
					 try {
						 idS = String.valueOf(tbReservas.getValueAt(tbReservas.getSelectedRow(), 0));
					 }catch (ArrayIndexOutOfBoundsException e5) {
						
					}
				}
				if(tbHospedes.getSelectedRow() >= 0) {
					 try {
						 idSH = String.valueOf(tbHospedes.getValueAt(tbHospedes.getSelectedRow(), 0));
						 System.out.println("pegando o valor em string" + idSH);
					 }catch (ArrayIndexOutOfBoundsException e5) {
						
					}
				}
				System.out.println("entra no try");
				try {
					System.out.println(idS);
					if(idS != "") {
						System.out.println("reserva");
						int id = Integer.valueOf(idS);
						r.deletar(id);
					
					}
					System.out.println(idSH);
					System.out.println("antes");
					if(idSH != "") {
						int idH = Integer.valueOf(idSH);
						System.out.println(idH);
						h.deletar(idH);
					}
					System.out.println("depois");
													
				}catch (NumberFormatException e2) {
					e2.printStackTrace();
					return;
				}finally {
					
				}
				
				try {
					r.getConnection().commit();
					JOptionPane.showMessageDialog(null, "deletado com sucesso.");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		List<Hospede> hospedes = h.listar();
		hospedes.forEach(evt -> {
			modeloHospedes.addRow(new Object[]{evt.getId(),evt.getNome(), evt.getSobrenome(),
					evt.getDataNascimento(),evt.getNascionalidade(), evt.getTelefone(), evt.getIdReserva()});	
		});
		
		List<Reserva> reservas1 = r.listar();
		reservas1.forEach(t -> {
			modelo.addRow(new Object[]{t.getId(),t.getDataEntrada(), t.getDataSaida(),
					t.getValor(),t.getFormaPagamento()});
		});
		
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
	    
}
