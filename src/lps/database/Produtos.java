package lps.database;

import java.util.ArrayList;
import java.util.List;

import lps.server.ChassiCaminhao;
import lps.server.ChassiOnibus;

/*
 * Um pequeno banco de dados simples de produtos numa ArrayList comum
 */

public class Produtos {

	public String codigo;
	public String tipo;
	public int peso;
	public int motor;
	public String eixo;
	public String descricao;
	private List<Produtos> arrayProdutos;

	public Produtos(String codigo, String tipo, int peso, int motor,
			String eixo, String descricao) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.peso = peso;
		this.motor = motor;
		this.eixo = eixo;
		this.descricao = descricao;

	}

	private void iniciarBanco() {

		List<Produtos> arrayProdutos = new ArrayList<Produtos>();

		arrayProdutos.add(new Produtos("C001", "caminhao", 3, 120, "4x2",
				"Caminhão leve para entregas urbanas"));
		arrayProdutos.add(new Produtos("C002", "caminhao", 5, 120, "4x2",
				"Caminhão leve para entregas urbanas"));
		arrayProdutos.add(new Produtos("C003", "caminhao", 5, 150, "4x2",
				"Caminhão leve para entregas urbanas"));
		arrayProdutos.add(new Produtos("C004", "caminhao", 8, 160, "4x2",
				"Caminhão leve para entregas urbanas"));
		arrayProdutos.add(new Produtos("C005", "caminhao", 9, 160, "4x2",
				"Caminhão leve para pequenas distâncias"));
		arrayProdutos.add(new Produtos("C006", "caminhao", 10, 160, "4x2",
				"Caminhão leve para pequenas e médias distâncias"));
		arrayProdutos.add(new Produtos("C007", "caminhao", 13, 190, "4x2",
				"Caminhão médio para pequenas distâncias"));
		arrayProdutos.add(new Produtos("C008", "caminhao", 15, 190, "4x2",
				"Caminhão médio para pequenas e médias distâncias"));
		arrayProdutos.add(new Produtos("C009", "caminhao", 17, 190, "4x2",
				"Caminhão semi-pesado para pequenas e médias distâncias"));
		arrayProdutos.add(new Produtos("C010", "caminhao", 17, 230, "4x2",
				"Caminhão semi-pesado para pequenas e médias distâncias"));
		arrayProdutos.add(new Produtos("C011", "caminhao", 17, 280, "6x4",
				"Caminhão semi-pesado para médias distâncias"));
		arrayProdutos.add(new Produtos("C012", "caminhao", 17, 330, "6x4",
				"Caminhão semi-pesado para médias e longas distâncias"));
		arrayProdutos.add(new Produtos("C013", "caminhao", 19, 330, "4x2",
				"Caminhão semi-pesado para longas distâncias"));
		arrayProdutos.add(new Produtos("C014", "caminhao", 19, 390, "6x4",
				"Caminhão semi-pesado para longas distâncias"));
		arrayProdutos.add(new Produtos("C015", "caminhao", 23, 230, "6x4",
				"Caminhão pesado para pequenas distâncias"));
		arrayProdutos.add(new Produtos("C016", "caminhao", 24, 280, "8x2",
				"Caminhão pesado para médias e longas distâncias"));
		arrayProdutos.add(new Produtos("C017", "caminhao", 24, 330, "8x2",
				"Caminhão pesado para aplicações na construção civil"));
		arrayProdutos.add(new Produtos("C018", "caminhao", 25, 390, "8x2",
				"Caminhão semi-pesado para pequenas e médias distâncias"));

		arrayProdutos.add(new Produtos("B001", "onibus", 5, 150, "4x2",
				"Chassi de minionibus simples"));
		arrayProdutos.add(new Produtos("B002", "onibus", 8, 160, "4x2",
				"Chassi de micro-ônibus para carrocerias de até 8m"));
		arrayProdutos.add(new Produtos("B003", "onibus", 9, 160, "4x2",
				"Chassi de micro-ônibus convencional"));
		arrayProdutos.add(new Produtos("B004", "onibus", 15, 190, "4x2",
				"Chassi de midibus"));
		arrayProdutos.add(new Produtos("B005", "onibus", 17, 230, "4x2",
				"Chassi de ônibus urbano convencional"));
		arrayProdutos.add(new Produtos("B006", "onibus", 17, 260, "4x2",
				"Chassi de ônibus de fretamento"));
		arrayProdutos.add(new Produtos("B007", "onibus", 17, 280, "4x2",
				"Chassi de ônibus padron"));
		arrayProdutos.add(new Produtos("B008", "onibus", 18, 310, "4x2",
				"Chassi de ônibus rodoviário"));
		arrayProdutos.add(new Produtos("B009", "onibus", 18, 360, "4x2",
				"Chassi de ônibus rodoviário"));
		arrayProdutos.add(new Produtos("B010", "onibus", 24, 360, "6x4",
				"Chassi de ônibus rodoviário"));
		arrayProdutos.add(new Produtos("B011", "onibus", 24, 410, "6x4",
				"Chassi de ônibus rodoviário"));
		arrayProdutos.add(new Produtos("B012", "onibus", 27, 410, "8x2",
				"Chassi de ônibus rodoviário DD"));
		arrayProdutos.add(new Produtos("B013", "onibus", 26, 360, "6x4",
				"Chassi de ônibus articulado"));
		arrayProdutos.add(new Produtos("B014", "onibus", 41, 360, "8x2",
				"Chassi de ônibus biarticulado"));

		this.arrayProdutos = arrayProdutos;

	}

	public String consultaBanco(String codigo) {

		iniciarBanco();
		int i;
		Produtos checker = null;
		boolean eValido = false;
		ChassiCaminhao caminhao = new ChassiCaminhao();
		ChassiOnibus onibus = new ChassiOnibus();
		String produto, adicional = null, descricao = null;

		for (i = 0; i < arrayProdutos.size(); i++) {
			if (codigo.equals(arrayProdutos.get(i).codigo)) {
				checker = arrayProdutos.get(i);
				eValido = true;
			}
		}

		if (eValido) {
			if (checker.tipo.equals("caminhao")) {
				caminhao.setAlturaCabine(checker.peso);
				descricao = "Altura da cabine do caminhão";
				adicional = String.valueOf(caminhao.getAlturaCabine());
			} else if (checker.tipo.equals("onibus")) {
				onibus.setPosicaoMotor(checker.motor);
				descricao = "Posição do motor do chassi de ônibus";
				adicional = onibus.getPosicaoMotor();
			}

			produto = checker.toString() + ";" + descricao + ";" + adicional;

			return produto;
		}
		
		return "ERRO";
	}

	public ArrayList<Produtos> listarProdutos(String tipo, int pesoMin,
			int motorMin, int pesoMax, int motorMax) {

		iniciarBanco();
		int i;
		
		ArrayList<Produtos> checker = new ArrayList<Produtos>();

		for (i = 0; i < arrayProdutos.size(); i++) {
			String tipoAtual = arrayProdutos.get(i).tipo;
			int pesoAtual = arrayProdutos.get(i).peso;
			int motorAtual = arrayProdutos.get(i).motor;

			if (tipo == tipoAtual) {
				if ((pesoAtual >= pesoMin && pesoAtual <= pesoMax)
						&& (motorAtual >= motorMin && motorAtual <= motorMax)) {
					checker.add(arrayProdutos.get(i));
				}
			}
		}
		return checker;
	}

	public String toString() {
		return codigo + ";" + peso + "." + motor + ";" + eixo + ";" + descricao;
	}

}
