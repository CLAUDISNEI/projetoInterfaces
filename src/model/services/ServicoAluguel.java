package model.services;

import model.entities.AluguelCarro;
import model.entities.Fatura;

public class ServicoAluguel {
	
	private Double precoPorDia;
	private Double precoPorHora;
	
	private TaxasBrasil taxaServico;

	public ServicoAluguel(Double precoPorDia, Double precoPorHora, TaxasBrasil taxaServico) {
		this.precoPorDia = precoPorDia;
		this.precoPorHora = precoPorHora;
		this.taxaServico = taxaServico;
	}
	
	public void processarFatura(AluguelCarro carroAlugado) {
		long inicio = carroAlugado.getInicio().getTime();
		long fim = carroAlugado.getFim().getTime();
		double horas = (double)(fim - inicio)/1000/60/60;
		
		double pagamentoBasico;
		if(horas <= 12.0) {
			pagamentoBasico = Math.ceil(horas) * precoPorHora;
		}
		else {
			pagamentoBasico = Math.ceil(horas/24)* precoPorDia;
		}
		
		//retorna o valor do imposto
		double taxa = taxaServico.taxa(pagamentoBasico);
		
		carroAlugado.setFatura(new Fatura(pagamentoBasico, taxa));
		
	}
	
	
}
