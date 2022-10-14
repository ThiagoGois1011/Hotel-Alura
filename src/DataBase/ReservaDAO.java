package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection)  {
		try {
			connection.setAutoCommit(false);
			this.connection = connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void salvar(Reserva reserva) {
		try {
			String sql = "INSERT INTO Reservas (DataEntrada, DataSaida, Valor, FormaPagamento) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, reserva.getDataEntrada());
				pstm.setString(2, reserva.getDataSaida());
				pstm.setDouble(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPagamento());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
	
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
		
	
	public List<Reserva> listar() {
		List<Reserva> reserva = new ArrayList<Reserva>();
		try {
			String sql = "SELECT ID, DataEntrada, DataSaida,Valor,FormaPagamento FROM Reservas";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmReserva(reserva, pstm);
			}
			return reserva;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> BuscarNumeroReserva(int number){
		List<Reserva> reserva = new ArrayList<Reserva>();
		
		try {
			String sql = "SELECT * FROM Reservas WHERE ID = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, number);
				pstm.execute();

				trasformarResultSetEmReserva(reserva, pstm);
			}
			return reserva;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void Editar(String datain, String dataout, String formaPagamento) {
		try (PreparedStatement stm = connection.prepareStatement("UPDATE Reservas SET DataEntrada = ?,"
				+ " DataSaida = ?, FormaPagamento = ?")) {
			stm.setString(1, datain);
			stm.setString(2, dataout);
			stm.setString(3, formaPagamento);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE  FROM Reservas WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void trasformarResultSetEmReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva reserva = new Reserva(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4),
						rst.getString(5));
				

				reservas.add(reserva);
			}
		}
	}
	
	
}
