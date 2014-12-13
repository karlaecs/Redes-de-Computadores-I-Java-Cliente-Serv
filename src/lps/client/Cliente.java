package lps.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Cliente {

	private String host;
	private int porta;
	private Socket clientSocket;
	private BufferedWriter output;
	private BufferedReader in;
	private String response;

	public Cliente(String host, int porta) throws UnknownHostException,
			IOException {
		this.host = host;
		this.porta = porta;
		conectar();
	}

	public void conectar() {
		try {
			System.out.printf("Conectando ao servidor %s na porta %d \n",
					this.host, this.porta);
			clientSocket = new Socket(this.host, this.porta);
		} catch (UnknownHostException e) {
			System.out.println("## ERRO: Servidor nao encontrado ##");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("## ERRO: Ao conectar no servidor ##");
			e.printStackTrace();
		}

		// Strem para Mandar mensagem ao servidor
		try {
			output = new BufferedWriter(new OutputStreamWriter(
					this.clientSocket.getOutputStream()));
		} catch (IOException e) {
			System.out
					.println("## ERRO: Ao acessar streams de entrada e saida do cliente ##");
			e.printStackTrace();
		}

		// Stream para Receber mensagem do servidor
		try {
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
		} catch (IOException e) {
			System.out
					.println("## ERRO: Ao acessar streams de entrada e saida do cliente ##");
			e.printStackTrace();
		}
	}

	public void desconectar() {
		try {
			output.close();
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("## ERRO: Ao desconectar do servidor ##");
			e.printStackTrace();
		}
	}

	public String requestListar(String mensagem) throws IOException {
		try {
			System.out.println("=> Enviando mensagem: " + mensagem);
			output.write(mensagem);
			output.flush();
		} catch (IOException e) {
			System.out.println("##ERRO: Ao enviar mensagem");
			e.printStackTrace();
		}

		// Resposta do cliente com um objeto Json
		this.response = in.readLine();
		System.out.println("===> resposta" + this.response);

		return this.response;

	}

}