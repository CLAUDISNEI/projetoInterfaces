package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.AluguelCarro;
import model.entities.Veiculo;
import model.services.ServicoAluguel;
import model.services.TaxasBrasil;

public class ProgramaExemploSolucao1SemInterface {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		
		try {
			System.out.println("Entre com os dados do aluguel:");
			System.out.print("Modelo Carro:");
			String modelo = sc.nextLine();
			System.out.print("Retirada (dd/MM/yyyy hh:ss: ");
			Date inicio = sdf.parse(sc.nextLine());
			System.out.print("Devolução (dd/MM/yyyy hh:ss: ");
			Date fim = sdf.parse(sc.nextLine());
			
			AluguelCarro carro = new AluguelCarro(inicio, fim, new Veiculo(modelo));
			
			System.out.print("Entre o preço por hora: ");
			double precoHora = sc.nextDouble();
			System.out.print("Entre o preço por dia: ");
			double precoDia = sc.nextDouble();
			
			ServicoAluguel servicoAluguel = new ServicoAluguel(precoDia, precoHora, new TaxasBrasil());
			
			servicoAluguel.processarFatura(carro);
			
			System.out.println("Fatura: ");
			System.out.println("Pagamento básico: "+ String.format("%.2f",carro.getFatura().getPagamentoBasico()));
			System.out.println("Taxa: "+ String.format("%.2f", carro.getFatura().getTaxa()));
			System.out.println("Total pagamento: "+ String.format("%.2f", carro.getFatura().getPagamentoTotal()));
			
		} catch (ParseException e) {
			System.out.println("Erro de parse"+e.getMessage());
		}
		finally {
			if(sc!=null) {
				sc.close();
			}
			
		}

	}

}
