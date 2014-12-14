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
		String produtoResultante = "C�digo do Produto: " + listaProduto[0]
				+ "\n" + "Modelo do produto: " + listaProduto[1] + "\n"
				+ "Eixo: " + listaProduto[2] + "\n" + "Descri��o do Produto: "
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
		String featureResultante = "C�digo da Feature: " + listaFeature[0]
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
				.println("Agora, listaremos as(os) " + tipo + " dispon�veis.");
		response = this.cliente.requestListar("LISTAR FEATURES " + codigoListar
				+ "\n");
		verificarErros(response);
		decoding(response, 1);
		System.out.println("Escolha as(os) " + tipo
				+ " digitando seu c�digo correspondente.\n");
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
					.println("Ocorreu um erro e o sistema foi encerrado. Verifique a sintaxe de seus comandos ou verifique a conex�o com o servidor.");
			System.exit(0);
		}
	}

	public void iniciarAplicacao() throws UnknownHostException, IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out
				.println("Bem-vindos ao sistema de gera��o de produtos! \n "
						+ "Digite a op��o do tipo de produto que deseja, e n�s listaremos todas as op��es dispon�veis.\n"
						+ "0 -> Caminh�es Convencionais Leves\n"
						+ "1 -> Caminh�es Convencionais Semi-Pesados\n"
						+ "2 -> Caminh�o \"Cavalo\"\n"
						+ "3 -> Chassis de mini e micro-�nibus\n"
						+ "4 -> Chassis de midibus\n"
						+ "5 -> Chassis de �nibus de 17t\n"
						+ "6 -> Chassis de �nibus rodovi�rios\n"
						+ "7 -> Chassis de �nibus articulados e biarticulados");

		// Lendo a op��o digitada pelo cliente
		int opcao = sc.nextInt();
		String response = this.cliente.requestListar("LISTAR PRODUTOS " + opcao
				+ "\n");
		verificarErros(response);

		System.out.println("Listando todos os produtos dispon�veis.\n");
		decoding(response, 0);

		System.out
				.println("Escolha seu produto digitando seu c�digo correspondente.\n");
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
		splitFeature = iniciarFeature(1, "transmiss�es de caixa de marcha")
				.split(";");
		listaFeatures.add(splitFeature[2]);
		splitFeature = iniciarFeature(2, "dire��o").split(";");
		listaFeatures.add(splitFeature[2]);

		// Agora ir� entrar nas features espec�ficas para caminh�o ou �nibus.
		// Isto � determinado pela vari�vel opcao, que definiu se � caminh�o ou
		// �nibus

		if (opcao < 4) {
			splitFeature = iniciarFeature(4, "carrocerias").split(";");
			listaFeatures.add(splitFeature[1]);
			listaFeatures.add(splitFeature[2]);

			while (true) {
				splitFeature = iniciarFeature(3, "adicionais de cabine").split(
						";");
				listaFeatures.add(splitFeature[2]);

				System.out
						.println("Deseja adicionar mais um incremento � sua cabine? Ent�o digite \"Sim\" (sem as aspas)\n"
								+ "Caso contr�rio, \"Nao\" (sem as aspas) ");
				String repetir = sc.next();
				repetir = repetir.toUpperCase();

				if (!repetir.equals("SIM"))
					break;
			}

		} else {
			splitFeature = iniciarFeature(5,"suspens�es").split(";");
			listaFeatures.add(splitFeature[1]);
			listaFeatures.add(splitFeature[2]);
		}
		Email email = new Email();
		
		System.out.println("Agora, digite seu email, e n�s lhe enviaremos todos os detalhes sobre o seu produto");
		
		String emailUsuario = sc.next();
		
		email.enviarEmail(emailUsuario, produto, listaFeatures);

		//while (true);

	}

}
