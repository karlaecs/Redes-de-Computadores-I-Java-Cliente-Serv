package lps.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

public class TrataCliente extends Thread {

	private Socket clientSocket;
	private BufferedReader input;
	String response;

	OutputStreamWriter osw;

	public TrataCliente(Socket clientSocket) {
		this.clientSocket = clientSocket;
		iniciarStreams();

	}

	private void iniciarStreams() {

		// Stream para receber a mensagem ao cliente
		try {

			input = new BufferedReader(new InputStreamReader(
					this.clientSocket.getInputStream()));

		} catch (IOException e) {
			System.out
					.println("##ERRO: Ao acessar streams de entrada e saida do cliente ##");
			e.printStackTrace();
		}

		// Stream para enviar mensagem ao cliente
		try {
			new BufferedWriter(new OutputStreamWriter(
					this.clientSocket.getOutputStream()));
		} catch (IOException e) {
			System.out
					.println("## ERRO: Ao acessar streams de entrada e saida do cliente ##");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String mensagem = "Esperando...";
		while (true && !clientSocket.isClosed()) {
			try {
				mensagem = input.readLine();
			} catch (IOException e) {
				System.out.println("## ERRO: O cliente "+ clientSocket.getLocalSocketAddress() + " se desconectou de uma forma inesperada.");
				break;
			}

			// Aqui pega a mensagem e divide em dois, sendo o primeiro campo o
			// pedido e o segundo o parâmetro

			if (mensagem.equals("ENCERRAR")) {
				try {
					System.out.println("Encerrando a conexão.\n");
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}				

			} else {
				String[] parametrosMensagem = (mensagem.split(" "));
				ChassiCaminhao chassi = new ChassiCaminhao();

				if (parametrosMensagem[0].equals("LISTAR")) {
					System.out.println("=> Mensagem Recebida = " + mensagem);
					if (parametrosMensagem[1].equals("PRODUTOS")) {
						this.response = (chassi.listarProduto(Integer
								.parseInt(parametrosMensagem[2])));
					} else if (parametrosMensagem[1].equals("FEATURES")) {
						this.response = (chassi.listarFeature(Integer
								.parseInt(parametrosMensagem[2])));
					}

					try {
						// Enviando um objeto Json
						System.out.println("=> Enviando resposta pro cliente ");
						PrintStream sender = new PrintStream(
								clientSocket.getOutputStream());
						sender.println(this.response);

					} catch (IOException e) {
						System.out
								.println("##ERRO: Ao enviar mensagem ao cliente");
						e.printStackTrace();
					}
				} else if (parametrosMensagem[0].equals("SELECIONAR")) {
					System.out.println("=> Mensagem Recebida = " + mensagem);
					if (parametrosMensagem[1].equals("PRODUTOS")) {
						this.response = (chassi
								.definirProduto(parametrosMensagem[2]));
					} else if (parametrosMensagem[1].equals("FEATURES")) {
						this.response = (chassi
								.definirFeature(parametrosMensagem[2]));
					}
					try {
						// Enviando a mensagem com o produto selecionado
						System.out.println("=> Enviando resposta pro cliente ");
						PrintStream sender = new PrintStream(
								clientSocket.getOutputStream());
						sender.println(this.response);

					} catch (IOException e) {
						System.out
								.println("##ERRO: Ao enviar mensagem ao cliente");
						e.printStackTrace();
					}
				}
			}
		}
	}

}
