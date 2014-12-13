package lps.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lps.server.Chassi;
import lps.server.ChassiCaminhao;
import lps.server.ChassiOnibus;
import lps.server.Produtos;


public class Menu {

	Cliente cliente;
	
	public Menu(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public void decoding(String response)
	{
		// Decodificando para String os dados recebidos
				JSONParser parser = new JSONParser();
				ContainerFactory containerFactory = new ContainerFactory() {
					public List creatArrayContainer() {
						return new LinkedList();
					}

					public Map createObjectContainer() {
						return new LinkedHashMap();
					}

				};
				try {
					Map json = (Map) parser.parse(response, containerFactory);
					Iterator iter = json.entrySet().iterator();
					System.out.println("==iterate result==");
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						System.out.println(entry.getKey() + "=>" + entry.getValue());
					}

					System.out.println("==toJSONString()==");
					System.out.println(JSONValue.toJSONString(json));
				} catch (ParseException pe) {
					System.out.println(pe);
				}
	}
	public void iniciarAplicacao() throws UnknownHostException, IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out
				.println("Bem-vindos ao sistema de gercaoo de produtos! \n "
						+ "Digite a opção do tipo de produto que deseja, e n�s listaremos todas as op��es dispon�veis.\n"
						+ "0 -> Caminh�o\n" + "1 -> Chassi de �nibus");
		
		// Lendo a opção digitada pelo cliente
		int opcao = sc.nextInt();

		//Escolher entre caminhão - 0 e Ônibus - 1
		if (opcao == 0) {
			
			// requisição ao servidor para mandar a lista do caminhão
			String response = this.cliente.requestListar("LISTA 0\n");

			System.out
					.println("Listando todos os produtos disponíveis.\n");
			decoding(response);
			
			ChassiCaminhao chassi = new ChassiCaminhao();
		
			Produtos produtoEscolhido = chassi.escolherProdutos(sc.nextInt());
			chassi.setAlturaCabine(produtoEscolhido.peso);

			System.out.println("Voc� escolheu o seguinte caminh�o: \n"
					+ "Tipo do Caminh�o: "
					+ chassi.textoTipoChassi(chassi.getTipoChassi()) + "\n"
					+ produtoEscolhido + "\n"
					+ "Este caminh�o possui a altura da cabine igual a "
					+ chassi.getAlturaCabine() + "m\n");

			System.out
					.println("Vamos adicionar novas caracteristicas no caminh�o. Responda as seguintes quest�es atentamente."
							+ "\n");

			System.out
					.println("Escolha um sistema de transmiss�o para seu caminh�o: \n"
							+ "0 -> ZF\n1 -> Eaton");
			chassi.setTransmissao(sc.nextInt());
			System.out
					.println("Agora escolha um sistema de dire��o para seu caminh�o: \n"
							+ "0 -> Hidr�ulica\n1 -> Comum");
			chassi.setDirecao(sc.nextInt());
			System.out
					.println("Agora escolha um sistema de freios para seu caminh�o: \n"
							+ "0 -> ABS\n1 -> Comum");
			chassi.setFreios(sc.nextInt());
			System.out
					.println("Por fim, digite o que voc� deseja adicionar na cabine do seu caminh�o \n"
							+ "Exemplo: Ar condicionado");
			chassi.setAdicionaisCabine(sc.next());

			System.out
					.println("O seu caminh�o est� pronto! Abaixo, todos os dados do produto: \n\n"
							+ "Tipo do Caminh�o: "
							+ chassi.textoTipoChassi(chassi.getTipoChassi())
							+ "\n"
							+ produtoEscolhido
							+ "\n"
							+ "Sistema de transmiss�o: "
							+ chassi.textoTipoTransmissao(chassi
									.getTransmissao())
							+ "\n"
							+ "Sistema de dire��o: "
							+ chassi.textoTipoDirecao(chassi.getDirecao())
							+ "\n"
							+ "Sistema de Freios: "
							+ chassi.textoTipoFreios(chassi.getFreios())
							+ "\n"
							+ "Este caminh�o possui a altura da cabine igual a "
							+ chassi.getAlturaCabine()
							+ "m\n"
							+ "A cabine possui como adicionais: "
							+ chassi.getAdicionaisCabine() + "\n");
		} else {
			ChassiOnibus chassi = new ChassiOnibus();

			System.out
					.println("Listando todos os produtos dispon�veis. Selecione o produto atrav�s de seu c�digo\n");

			System.out.println(((Chassi) chassi).listarProduto(false, 0));

			Produtos produtoEscolhido = chassi.escolherProdutos(sc.nextInt());
			chassi.setPosicaoMotor(produtoEscolhido.motor);

			System.out.println("Voc� escolheu o seguinte chassi de �nibus: \n"
					+ produtoEscolhido + "\n" + "Posi��o do motor: "
					+ chassi.getPosicaoMotor() + "\n");

			System.out
					.println("Vamos adicionar novas caracteristicas no chassi de �nibus. Responda as seguintes quest�es atentamente."
							+ "\n");

			System.out
					.println("Escolha um sistema de transmiss�o para seu chassi de �nibus: \n"
							+ "0 -> ZF\n1 -> Eaton");
			chassi.setTransmissao(sc.nextInt());
			System.out
					.println("Agora escolha um sistema de dire��o para seu chassi de �nibus: \n"
							+ "0 -> Hidr�ulica\n1 -> Comum");
			chassi.setDirecao(sc.nextInt());
			System.out
					.println("Agora escolha um sistema de freios para seu chassi de �nibus: \n"
							+ "0 -> ABS\n1 -> Comum");
			chassi.setFreios(sc.nextInt());
			System.out
					.println("Agora escolha um sistema de suspens�o para seu chassi de �nibus: \n"
							+ "0 -> Suspens�o a Ar\n1 -> Suspens�o Met�lica");
			chassi.setSuspensao(sc.nextInt());

			System.out
					.println("O seu chassi de �nibus est� pronto! Abaixo, todos os dados do produto: \n\n"
							+ produtoEscolhido
							+ "\n"
							+ "Sistema de transmiss�o: "
							+ chassi.textoTipoTransmissao(chassi
									.getTransmissao())
							+ "\n"
							+ "Sistema de dire��o: "
							+ chassi.textoTipoDirecao(chassi.getDirecao())
							+ "\n"
							+ "Sistema de Freios: "
							+ chassi.textoTipoFreios(chassi.getFreios())
							+ "\n"
							+ "Sistema de suspens�o: "
							+ chassi.textoTipoSuspensao(chassi.getSuspensao())
							+ "\n");

		}
		System.out.println("Pressione \"Q\" para sair\n");
		opcao = sc.nextByte();
	}
}
