package procedural;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Scanner;

public class ProcessSale {

	static class SaleItemStruct{
		double value;
		int type; 
		String productName;
		double calculatedTax;
		int id;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<SaleItemStruct> saleList = new ArrayList<>();
		boolean running = true;
		int idSale=0;
		double salesTotal = 0;
		double salesTax = 0;

		double invokeTotal = 0;

		//1-LEITURA DE DADOS DA VENDA

		while(running) {
			int option=0;
			System.out.println("SISTEMA DE VENDAS - MODELO PROCEDURAL");
			System.out.println("1 - Adicionar um produto/servico");
			System.out.println("2 - Salvar venda no BD");
			System.out.println("3 - Sair do menu de vendas");
			System.out.println("Escolha uma das alternativas pelo número: ");
			try {
				option = Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
				System.out.println("Opção inválida, somente números de [1-3] são permitidos.");
			}

			switch (option) {
			case 1:
				SaleItemStruct itemStruct = new SaleItemStruct();
				System.out.println("Insira o nome do produto: ");

				itemStruct.productName = sc.nextLine();

				System.out.println("Insira o valor R$: ");
				itemStruct.value = Double.parseDouble(sc.nextLine());

				System.out.println("Insira o tipo da venda (1-Produto | 2-Serviço)");
				itemStruct.type = Integer.parseInt(sc.nextLine());

				idSale++;
				itemStruct.id=idSale;

				saleList.add(itemStruct);
				System.out.println("Item "+ itemStruct.productName + " adcionado à lista. A lista possui "+saleList.size()+ " produtos/serviços.");
				break;
			case 2:
				if(saleList.size()==0) {
					System.out.println("Não existem vendas registradas para processar. A lista está vazia");
				}else {
					System.out.println("Processar essa venda?");
					System.out.println(listProducts(saleList));

					System.out.println("(S) Sim?");
					String prossig = sc.nextLine();

					if(prossig.equals("S") || prossig.equals("s")) {

						for(int i=0; i<saleList.size(); i++) {
							salesTotal += saleList.get(i).value;

							//Calculate taxes
							if(saleList.get(i).type == 1) {
								salesTax += saleList.get(i).value * 0.10; //Product tax rate 10%
							}else if (saleList.get(i).type == 2) {
								salesTax += saleList.get(i).value * 0.25; //Service tax rate 25%
							}else {
								salesTax += 0; //Item tax exempt
							}
						}
						invokeTotal = salesTotal + salesTax;
						process(saleList, salesTotal, salesTax, invokeTotal);
						saleList.clear();
					}else {
						System.out.println("Processamento da venda cancelado");
					}
				}
				break;
			case 3:
				System.out.println("Saindo do sistema...");
				running=false;
				break;

			default:
				System.out.println("Opção não reconhecida. Número inválido.");
				break;
			}
		}
		sc.close();
	}
		
	public static void process(ArrayList<SaleItemStruct> list, double salesTotal, double salesTax, double invokeTotal) {
		System.out.println("Enviando NF para impressão...");
		listProducts(list);
		System.out.println("Valor dos produtos da NF R$: " + salesTotal + " + impostos R$: "+salesTax+" Total a pagar R$: "+invokeTotal);
		System.out.println("Registrando dados da venda em logfile...");
		registerLog(list, salesTotal, salesTax, invokeTotal);
		System.out.println("\nProcesso de venda finalizado.");
	}
	
	public static String listProducts(ArrayList<SaleItemStruct> list) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<list.size(); i++) {
			String line = "[ " + list.get(i).id + " ] Produto: " + list.get(i).productName + " R$: " + list.get(i).value;
			sb.append(line).append("\n");
		}
		return sb.toString();
	}
	
	public static String formatDate(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return formatter.format(dateTime);
		}
	
	public static void registerLog(ArrayList<SaleItemStruct> list, double salesTotal, double salesTax, double invokeTotal) {
		try {
			FileWriter logFile = new FileWriter("syslog.log", true);
			PrintWriter logRecord = new PrintWriter(logFile);
			logRecord.print("DADOS DA VENDA\n");
			logRecord.print(formatDate(LocalDateTime.now()) + "\n");
			logRecord.print(listProducts(list));
			logRecord.print("Valor dos produtos da NF R$: " + salesTotal + " + impostos R$: "+salesTax+" Total a pagar R$: "+invokeTotal +"\n");
			logFile.close();

		}catch (IOException e) {
			System.out.println("Erro ao tentar gravar log");

		}
	}
}
