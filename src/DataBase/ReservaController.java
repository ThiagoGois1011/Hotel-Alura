package DataBase;

import java.sql.Connection;
import java.util.List;


public class ReservaController {
	
	private ReservaDAO reservaDAO;
	private Connection connection;
	
	public ReservaController(){
		Connection c = new ConnectionFactory().RecuperaConexao();
		this.connection = c;
		this.reservaDAO = new ReservaDAO(c);
	}
	
	public void salvar(Reserva r) {
		this.reservaDAO.salvar(r);
	}

	public List<Reserva> listar() {
		return this.reservaDAO.listar();
	}
	
	public List<Reserva> BuscarPorNReserva(int numero) {
		return this.reservaDAO.BuscarNumeroReserva(numero);
	}
	
	public void Editar(String datain, String dataout, String formaPagamento) {
		this.reservaDAO.Editar(datain, dataout, formaPagamento);
	}
	
	public void deletar(Integer id) {
		this.reservaDAO.deletar(id);
	}
	
	
	public Connection getConnection() {
		return this.connection;
	}
}
