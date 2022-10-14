package DataBase;

public class Hospede {
	
	private int Id;
	private String Nome;
	private String Sobrenome;
	private String DataNascimento;
	private String Nascionalidade;
	private String Telefone;
	private int IdReserva;
	
	public Hospede(String nome, String sobrenome, String dataNascimento, String nascionalidade, String telefone,
			int idReserva) {
		Nome = nome;
		Sobrenome = sobrenome;
		DataNascimento = dataNascimento;
		Nascionalidade = nascionalidade;
		Telefone = telefone;
		IdReserva = idReserva;
	}
	
	public Hospede(int ID, String nome, String sobrenome, String dataNascimento, String nascionalidade, String telefone,
			int idReserva) {
		Id = ID;
		Nome = nome;
		Sobrenome = sobrenome;
		DataNascimento = dataNascimento;
		Nascionalidade = nascionalidade;
		Telefone = telefone;
		IdReserva = idReserva;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public String getSobrenome() {
		return Sobrenome;
	}

	public String getDataNascimento() {
		return DataNascimento;
	}

	public String getNascionalidade() {
		return Nascionalidade;
	}

	public String getTelefone() {
		return Telefone;
	}

	public int getIdReserva() {
		return IdReserva;
	}
	
	@Override
	public String toString() {
		
		return String.format("Hospede:  %d, %s, %s, %s, %s,%s, %d ", this.Id, this.Nome, this.Sobrenome,this.DataNascimento, this.Nascionalidade, this.Telefone, this.IdReserva);
	}
	

}
