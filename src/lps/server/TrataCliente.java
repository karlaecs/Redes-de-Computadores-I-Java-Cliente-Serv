package lps.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import org.xml.sax.InputSource;

public class TrataCliente extends Thread {

	private Socket clientSocket;
	private BufferedReader input;
	private BufferedWriter output; 
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
		
		//Stream para enviar mensagem ao cliente
		try {  
            output = new BufferedWriter(new OutputStreamWriter(  
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
				System.out.println("## ERRO: Ao ler mensagem do cliente ##");
				e.printStackTrace();
			}

			if (mensagem.equals("LISTA 0")) {
				System.out.println("=> Mensagem Recebida = " + mensagem);
				ChassiCaminhao chassi = new ChassiCaminhao();
				this.response = ((Chassi) chassi).listarProduto(true, 0);
				try {
					// Enviando um objeto Json
					System.out.println("=> Enviando resposta pro cliente "); 
					PrintStream sender = new PrintStream(clientSocket.getOutputStream());
					sender.println(this.response);
					
				} catch (IOException e) {
					System.out.println("##ERRO: Ao enviar mensagem ao cliente");  
		            e.printStackTrace();  
				} 
			}
			
		}

	}
	
	public void responseCliente(String mensagem) {
		if(mensagem == "Convencional")
		{
			
		}
	}

}
