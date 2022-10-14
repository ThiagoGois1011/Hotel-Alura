package funcionalidades;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ValorReserva {
	
	List<Integer> dataEntrada;
	List<Integer> dataSaida;
	
	public ValorReserva(List<Integer> dataEntrada,List<Integer> dataSaida){
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;	
	}
	
	public int Calcula() {
		LocalDateTime saida = LocalDateTime.of(dataSaida.get(0), dataSaida.get(1), dataSaida.get(2), 0, 0, 0);
		LocalDateTime entrada = LocalDateTime.of(dataEntrada.get(0), dataEntrada.get(1), dataEntrada.get(2), 0, 0, 0);
		int dias = (int) entrada.until(saida, ChronoUnit.DAYS);
		int taxa = 0 ;
		
		if (dias < 30 ) {
			taxa = 15;
		}else {
			taxa = 10;
		}
		
		return taxa * dias;
	}
	
}
