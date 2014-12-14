package lps.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lps.client.Email;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Menu {

	Cliente cliente;

	public Menu(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	@SuppressWarnings("rawtypes")
	public void decoding(String response, int tipo) {
		// Decodificando para String os dados recebidos
		verificarErros(response);
		JSONParser parser = new JSONParser();
		ContainerFactory containerFactory = new ContainerFactory() {
			public List<?> creatArrayContainer() {
				return new LinkedList<Object>();
			}

			public Map<?, ?> createObjectContainer() {
				return new LinkedHashMap<Object, Object>();
			}

		};
		try {
			Map<?, ?> json = (Map<?, ?>) parser.parse(response,
					containerFactory);
			Iterator<?> iter = json.entrySet().iterator();
			imprimeBarra();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if (tipo == 0)
					imprimeListaProdutos((String) entry.getValue());
				else if (tipo == 1)
					imprimeListaFeatures((String) entry.getValue());
			}

			// System.out.println("==toJSONString()==");
			// System.out.println(JSONValue.toJSONString(json));
		} catch (ParseException pe) {
			System.out.println(pe);
		}
	}

	public void imprimeBarra() {
		System.out.println("===================");
	}

	public void imprimeListaProdutos(String produto) {
		String[] listaProduto = produto.split(";");
		String produtoResultante = "Código do Produto: " + listaProduto[0]
				+ "\n" + "Modelo do produto: " + listaProduto[1] + "\n"
				+ "Eixo: " + listaProduto[2] + "\n" + "Descrição do Produto: "
				+ listaProduto[3];

		if (listaProduto.length > 4) {
			produtoResultante = produtoResultante + "\n" + listaProduto[4]
					+ ": " + listaProduto[5];
		}
		System.out.println(produtoResultante);
		imprimeBarra();

	}

	public void imprimeListaFeatures(String feature) {
		String[] listaFeature = feature.split(";");
		String featureResultante = "Código da Feature: " + listaFeature[0]
				+ "\n" + listaFeature[1] + ": " + listaFeature[2];

		System.out.println(featureResultante);
		imprimeBarra();

	}

	

	public String iniciarFeature(int codigoListar, String tipo)
			throws IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		String response;
		System.out
				.println("Agora, listaremos as(os) " + tipo + " disponíveis.");
		response = this.cliente.requestListar("LISTAR FEATURES " + codigoListar
				+ "\n");
		verificarErros(response);
		decoding(response, 1);
		System.out.println("Escolha as(os) " + tipo
				+ " digitando seu código correspondente.\n");
		String codigo = sc.next();
		codigo = codigo.toUpperCase();

		response = this.cliente.requestListar("SELECIONAR FEATURES " + codigo
				+ "\n");
		verificarErros(response);
		System.out.println("Listando a feature escolhida\n");
		imprimeBarra();
		imprimeListaFeatures(response);

		return response;
	}

	public void verificarErros(String response) {
		if (response.equals("ERRO")) {
			System.out
					.println("Ocorreu um erro e o sistema foi encerrado. Verifique a sintaxe de seus comandos ou verifique a conexão com o servidor.");
			System.exit(0);
		}
	}

	public void iniciarAplicacao() throws UnknownHostException, IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out
				.println("Bem-vindos ao sistema de geração de produtos! \n "
						+ "Digite a opção do tipo de produto que deseja, e nós listaremos todas as opções disponíveis.\n"
						+ "0 -> Caminhões Convencionais Leves\n"
						+ "1 -> Caminhões Convencionais Semi-Pesados\n"
						+ "2 -> Caminhão \"Cavalo\"\n"
						+ "3 -> Chassis de mini e micro-ônibus\n"
						+ "4 -> Chassis de midibus\n"
						+ "5 -> Chassis de ônibus de 17t\n"
						+ "6 -> Chassis de ônibus rodoviários\n"
						+ "7 -> Chassis de ônibus articulados e biarticulados");

		// Lendo a opção digitada pelo cliente
		int opcao = sc.nextInt();
		String response = this.cliente.requestListar("LISTAR PRODUTOS " + opcao
				+ "\n");
		verificarErros(response);

		System.out.println("Listando todos os produtos disponíveis.\n");
		decoding(response, 0);

		System.out
				.println("Escolha seu produto digitando seu código correspondente.\n");
		String codigo = sc.next();
		codigo = codigo.toUpperCase();

		response = this.cliente.requestListar("SELECIONAR PRODUTOS " + codigo
				+ "\n");
		String produto = response;
		verificarErros(response);
		System.out.println("Listando o produto escolhido\n");
		imprimeBarra();
		imprimeListaProdutos(response);

		produto = produto + ";";

		List<String> listaFeatures = new ArrayList<String>();
		System.out
				.println("Agora, deve-se adicionar novas features ao seu produto.\n");

		String[] splitFeature = iniciarFeature(0, "tipos de freios").split(";");
		listaFeatures.add(splitFeature[2]);
		splitFeature = iniciarFeature(1, "transmissões de caixa de marcha")
				.split(";");
		listaFeatures.add(splitFeature[2]);
		splitFeature = iniciarFeature(2, "direção").split(";");
		listaFeatures.add(splitFeature[2]);

		// Agora irá entrar nas features específicas para caminhão ou ônibus.
		// Isto é determinado pela variável opcao, que definiu se é caminhão ou
		// ônibus

		if (opcao < 4) {
			splitFeature = iniciarFeature(4, "carrocerias").split(";");
			listaFeatures.add(splitFeature[1]);
			listaFeatures.add(splitFeature[2]);

			while (true) {
				splitFeature = iniciarFeature(3, "adicionais de cabine").split(
						";");
				listaFeatures.add(splitFeature[2]);

				System.out
						.println("Deseja adicionar mais um incremento à sua cabine? Então digite \"Sim\" (sem as aspas)\n"
								+ "Caso contrário, \"Nao\" (sem as aspas) ");
				String repetir = sc.next();
				repetir = repetir.toUpperCase();

				if (!repetir.equals("SIM"))
					break;
			}

		} else {
			splitFeature = iniciarFeature(5,"suspensões").split(";");
			listaFeatures.add(splitFeature[1]);
			listaFeatures.add(splitFeature[2]);
		}
		Email email = new Email();
		
		System.out.println("Agora, digite seu email, e nós lhe enviaremos todos os detalhes sobre o seu produto");
		
		String emailUsuario = sc.next();
		
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");  
	    Matcher m = p.matcher(emailUsuario);  
	    if (!m.find()) System.err.println("Email inválido. Por favor, tente novamente. Encerrando.\n");
	    else email.enviarEmail(emailUsuario, produto, listaFeatures);		
		
		response = this.cliente.requestListar("ENCERRAR\n");
		cliente.desconectar();

	}

}
