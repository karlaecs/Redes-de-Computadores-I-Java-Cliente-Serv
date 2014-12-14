package lps.client;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		
		
		//String host = "localhost";
		// Servidor João desktop
		String host = "187.65.94.136";

		// Alocando clientes para nossa aplicação
		Cliente c1 = new Cliente(host, 12345);
		Menu menu1 = new Menu(c1);
		
		menu1.iniciarAplicacao();
		c1.desconectar();
		
	}

}
