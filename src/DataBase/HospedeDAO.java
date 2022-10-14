package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HospedeDAO {
	
	private Connection connection;
	public HospedeDAO(Connection c) {
	this.connection = c;
	}
	
	public void salvar(Hospede hospede) {
		try {
			String sql = "INSERT INTO Hospedes (Nome, Sobrenome, DataNascimento, Nascionalidade, Telefone, ID_Reserva) VALUES (?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setString(3, hospede.getDataNascimento());
				pstm.setString(4, hospede.getNascionalidade());
				pstm.setString(5, hospede.getTelefone());
				pstm.setInt(6, hospede.getIdReserva());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						hospede.setId(rst.getInt(1));
					}
	
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Hospede> listar() {
		List<Hospede> hospede = new ArrayList<>();
		try {
			String sql = "SELECT ID, Nome, Sobrenome, DataNascimento, Nascionalidade, Telefone, ID_Reserva FROM Hospedes";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmHospede(hospede, pstm);
			}
			return hospede;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Editar(String nome, String sobrenome, String datanascimento, String nascionalidade , String telefone) {
		try (PreparedStatement stm = connection.prepareStatement("UPDATE Hospedes SET Nome = ?,"
				+ " Sobrenome = ?, DataNascimento = ?, Nascionalidade = ? , Telefone = ?")) {
			stm.setString(1, nome);
			stm.setString(2, sobrenome);
			stm.setString(3, datanascimento);
			stm.setString(4, nascionalidade);
			stm.setString(5, telefone);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Hospede> BuscarHospode(int number){
		List<Hospede> hospede = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM Hospedes WHERE ID_Reserva = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, number);
				pstm.execute();

				trasformarResultSetEmHospede(hospede, pstm);
			}
			return hospede;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Hospede> BuscarHospode(String sobrenome){
		List<Hospede> hospede = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM Hospedes WHERE Sobrenome = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, sobrenome);
				pstm.execute();

				trasformarResultSetEmHospede(hospede, pstm);
			}
			return hospede;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void trasformarResultSetEmHospede(List<Hospede> hospedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Hospede hospede = new Hospede(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
						rst.getString(6), rst.getInt(7));
				

				hospedes.add(hospede);
			}
		}
	}
	
	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM Hospedes WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void fechaConexao() {
		try {
			connection.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

}
