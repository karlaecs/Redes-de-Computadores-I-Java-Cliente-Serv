package lps.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private int porta;
	private ServerSocket serverSocket;

	public Servidor(int porta) {
		this.porta = porta;
	}

	public void executar() throws IOException {

		try {
			// criando o socket do servidor e executando na porta 12345
			serverSocket = new ServerSocket(this.porta);
			System.out.printf("Servidor escutando porta: %d \n", 12345);

			while (true) {
				
				// aceitando a conexão do cliente
				Socket conection = serverSocket.accept();
				// criando uma nova thread para tratar essa conexão
				new TrataCliente(conection).start();
				
			}

		} catch (IOException e) {
			System.out.println("##ERRO: Ao escutar porta 12345");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		// INICIANDO O SERVIDOR

		System.out.println("-------INICIANDO O SERVIDOR na porta: " + 12345
				+ "----------");
		new Servidor(12345).executar();
	}
}
