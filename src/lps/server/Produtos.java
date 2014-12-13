package lps.server;

import java.util.ArrayList;
import java.util.List;

/*
 * Um pequeno banco de dados simples de produtos numa ArrayList comum
 */

public class Produtos {

	private boolean aplicacao;
	public int peso;
	public int motor;
	public String eixo;
	public String descricao;
	private List<Produtos> arrayProdutos;

	public Produtos(boolean aplicacao, int peso, int motor, String eixo,
			String descricao) {
		this.aplicacao = aplicacao; // 0 = Caminh�o // 1 = Chassi de �nibus
		this.peso = peso;
		this.motor = motor;
		this.eixo = eixo;
		this.descricao = descricao;

	}

	private void iniciarBanco() {

		List<Produtos> arrayProdutos = new ArrayList<Produtos>();

		arrayProdutos.add(new Produtos(true, 3, 120, "4x2",
				"Caminh�o leve para entregas urbanas"));
		arrayProdutos.add(new Produtos(true, 5, 120, "4x2",
				"Caminh�o leve para entregas urbanas"));
		arrayProdutos.add(new Produtos(true, 5, 150, "4x2",
				"Caminh�o leve para entregas urbanas"));
		arrayProdutos.add(new Produtos(true, 8, 160, "6x4",
				"Caminh�o leve para entregas urbanas"));
		arrayProdutos.add(new Produtos(true, 9, 160, "6x4",
				"Caminh�o leve para pequenas dist�ncias"));
		arrayProdutos.add(new Produtos(true, 10, 160, "6x4",
				"Caminh�o leve para pequenas e m�dias dist�ncias"));
		arrayProdutos.add(new Produtos(true, 13, 190, "6x4",
				"Caminh�o m�dio para pequenas dist�ncias"));
		arrayProdutos.add(new Produtos(true, 15, 190, "6x4",
				"Caminh�o m�dio para pequenas e m�dias dist�ncias"));
		arrayProdutos.add(new Produtos(true, 17, 190, "6x4",
				"Caminh�o semi-pesado para pequenas e m�dias dist�ncias"));
		arrayProdutos.add(new Produtos(true, 17, 230, "6x4",
				"Caminh�o semi-pesado para pequenas e m�dias dist�ncias"));
		arrayProdutos.add(new Produtos(true, 17, 280, "6x4",
				"Caminh�o semi-pesado para m�dias dist�ncias"));
		arrayProdutos.add(new Produtos(true, 17, 330, "6x4",
				"Caminh�o semi-pesado para m�dias e longas dist�ncias"));
		arrayProdutos.add(new Produtos(true, 19, 330, "6x4",
				"Caminh�o semi-pesado para longas dist�ncias"));
		arrayProdutos.add(new Produtos(true, 19, 390, "6x4",
				"Caminh�o semi-pesado para longas dist�ncias"));
		arrayProdutos.add(new Produtos(true, 23, 230, "6x4",
				"Caminh�o pesado para pequenas dist�ncias"));
		arrayProdutos.add(new Produtos(true, 24, 280, "8x2",
				"Caminh�o pesado para m�dias e longas dist�ncias"));
		arrayProdutos.add(new Produtos(true, 24, 330, "8x2",
				"Caminh�o pesado para aplica��es na constru��o civil"));
		arrayProdutos.add(new Produtos(true, 25, 390, "8x2",
				"Caminh�o semi-pesado para pequenas e m�dias dist�ncias"));

		// Dados para futuras implementa��es
		arrayProdutos.add(new Produtos(false, 5, 150, "4x2",
				"Chassi de minionibus simples"));
		arrayProdutos.add(new Produtos(false, 8, 160, "4x2",
				"Chassi de micro-�nibus para carrocerias de at� 8m"));
		arrayProdutos.add(new Produtos(false, 9, 160, "4x2",
				"Chassi de micro-�nibus convencional"));
		arrayProdutos.add(new Produtos(false, 15, 190, "4x2",
				"Chassi de midibus"));
		arrayProdutos.add(new Produtos(false, 17, 230, "4x2",
				"Chassi de �nibus urbano convencional"));
		arrayProdutos.add(new Produtos(false, 17, 260, "4x2",
				"Chassi de �nibus de fretamento"));
		arrayProdutos.add(new Produtos(false, 17, 280, "4x2",
				"Chassi de �nibus padron"));
		arrayProdutos.add(new Produtos(false, 18, 310, "4x2",
				"Chassi de �nibus rodovi�rio"));
		arrayProdutos.add(new Produtos(false, 18, 360, "4x2",
				"Chassi de �nibus rodovi�rio"));
		arrayProdutos.add(new Produtos(false, 24, 360, "6x4",
				"Chassi de �nibus rodovi�rio"));
		arrayProdutos.add(new Produtos(false, 24, 410, "6x4",
				"Chassi de �nibus rodovi�rio"));
		arrayProdutos.add(new Produtos(false, 27, 410, "8x2",
				"Chassi de �nibus rodovi�rio DD"));
		arrayProdutos.add(new Produtos(false, 26, 360, "6x4",
				"Chassi de �nibus articulado"));
		arrayProdutos.add(new Produtos(false, 41, 360, "8x2",
				"Chassi de �nibus biarticulado"));

		this.arrayProdutos = arrayProdutos;

	}

	public Produtos consultaBanco(boolean aplicacao, int peso, int motor,
			String eixo) {

		iniciarBanco();
		int i;
		Produtos checker = null;

		for (i = 0; i < arrayProdutos.size(); i++) {
			if (aplicacao == arrayProdutos.get(i).aplicacao
					&& peso == arrayProdutos.get(i).peso
					&& motor == arrayProdutos.get(i).motor
					&& eixo == arrayProdutos.get(i).eixo) {
				checker = arrayProdutos.get(i);
			}
		}
		return checker;
	}

	public ArrayList<Produtos> listarProdutos(boolean aplicacao, int peso, int motor) {

		iniciarBanco();
		int i;
		ArrayList<Produtos> checker = new ArrayList<Produtos>();

		for (i = 0; i < arrayProdutos.size(); i++) {
			if (aplicacao == arrayProdutos.get(i).aplicacao
					&& peso <= arrayProdutos.get(i).peso
					&& motor <= arrayProdutos.get(i).motor) {
				checker.add(arrayProdutos.get(i));
			}
		}
		return checker;
	}

	public String toString() {
		return "Modelo: " + peso + "." + motor + "\n" + "Eixo: " + eixo + "\n"
				+ "Descri��o: " + descricao;
	}

}
