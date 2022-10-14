package DataBase;


public class Reserva {
	
	private int id;
	private String dataEntrada;
	private String dataSaida;
	private int valor;
	private String formaPagamento;
	
	public Reserva(String dataEntrada, String dataSaida, int valor, String formaPagamento) {
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}
	
	public Reserva(int id, String dataEntrada, String dataSaida, int valor, String formaPagamento) {
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.id = id;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public int getValor() {
		return valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return String.format("a reserva:  %d, %s, %s,  %d, %s ", this.id, this.dataEntrada, this.dataSaida,this.valor, this.formaPagamento);
	}
	
}
