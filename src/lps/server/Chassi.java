package lps.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.javafx.scene.paint.GradientUtils.Parser;
/*
 Aqui � a classe que define um chassi (um produto). Leia mais sobre o projeto de LPS aqui: 
 https://drive.google.com/file/d/0B1dyo0OuV6fEQ1BuaEhQNGJMLWI5Vzl5NmM3V2p5UlRTLVZz/view?usp=sharing
 */

public abstract class Chassi {

	protected int motor;
	private int transmissao;
	private int freios;
	private int direcao;
	private int peso;
	private String eixo;
	private List<Produtos> arrayProdutos;
	private int tipoChassi;

	public Chassi() {

	}

	public int getMotor() {
		return motor;
	}

	public void setMotor(int motor) {
		this.motor = motor;
	}

	public int getPeso() {
		return peso;
	}

	public int getTransmissao() {
		return transmissao;
	}

	public void setTransmissao(int transmissao) {
		this.transmissao = transmissao;
	}

	public String getEixo() {
		return eixo;
	}

	public void setEixo(int rodas) {

		switch (rodas) {
		case 4:
			eixo = "4x2";
			break;
		case 6:
			eixo = "6x4";
			break;
		case 8:
			eixo = "8x2";
			break;
		}
	}

	public int getFreios() {
		return freios;
	}

	public void setFreios(int freios) {
		if (freios == 0) {
			// System.out.println("Freios ABS");
		} else {
			// System.out.println("Freios Comuns");
		}
	}

	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		if (direcao == 0) {
			// System.out.println("Dire��o Hidr�ulica");
		} else {
			// System.out.println("Dire��o Comum");
		}
	}

	public Produtos definirProduto() {
		int peso = getPeso();
		int motor = getMotor();
		String eixo = getEixo();
		Produtos produto = new Produtos(true, 0, 0, null, null);

		return produto.consultaBanco(true, peso, motor, eixo);
	}

	public String listarProduto(boolean aplicacao, int tipoChassi) {
		
		ArrayList<Produtos> produto = new ArrayList<Produtos>();
		Produtos produtos = new Produtos(true, 0, 0, null, null);
	
		if (aplicacao == true) {
			if (tipoChassi == 0) {
				produto = produtos.listarProdutos(true, 3, 120);
			} else if (tipoChassi == 1) {
				produto = produtos.listarProdutos(true, 17, 280);
			}
		} else {
			produto = produtos.listarProdutos(false, 5, 150);
		}

		this.arrayProdutos = produto;
		
		// Convertendo para um objeto Json
		Map data = new LinkedHashMap();
		LinkedList list = new LinkedList();
		String jsonText= "";
		// Defina essa resposta no nosso documento que o servidor sempre vai responder uma listagem de caminha nessa ordem ai
		// codigo: [ motor, eixo, descriçao] 
		for (int i = 0; i < produto.size(); i++) {
			//list.add(i);
			list.add(produto.get(i).peso + "." + produto.get(i).motor);
			list.add(produto.get(i).eixo);
			list.add(produto.get(i).descricao);
			data.put(i, list);
		}
		jsonText = JSONValue.toJSONString(data);
		//System.out.print(jsonText);
		//return produto;	
		/*String lista = "";
		for (int i = 0; i < produto.size(); i++) {
			lista = lista
					+ "Codigo: " + i + "\n"
					+ "Modelo: " + produto.get(i).peso + "." + produto.get(i).motor + "\n" 
					+ "Eixo: " + produto.get(i).eixo + "\n" 
					+"Descriçao: " + produto.get(i).descricao + "\n";

		}*/
		
		return jsonText;
	}

	public Produtos escolherProdutos(int opcao) {
		return arrayProdutos.get(opcao);
	}

	public int getTipoChassi() {
		return tipoChassi;
	}

	public void setTipoChassi(int tipoChassi) {
		this.tipoChassi = tipoChassi;
	}

	public String textoTipoTransmissao(int transmissao) {
		String textoTipo;
		if (transmissao == 0)
			textoTipo = "ZF";
		else
			textoTipo = "Eaton";
		return textoTipo;
	}

	public String textoTipoDirecao(int direcao) {
		String textoTipo;
		if (direcao == 0)
			textoTipo = "Hidr�ulica";
		else
			textoTipo = "Comum";
		return textoTipo;
	}

	public String textoTipoFreios(int freios) {
		String textoTipo;
		if (freios == 0)
			textoTipo = "ABS";
		else
			textoTipo = "Comum";
		return textoTipo;
	}

}
