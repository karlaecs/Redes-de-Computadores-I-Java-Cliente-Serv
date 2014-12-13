package lps.server;

public class ChassiCaminhao extends Chassi {

	/*
	 * Aqui � a classe que reune apenas as caracter�sticas presente nos
	 * caminh�es
	 */

	private double alturaCabine;
	private String adicionaisCabine;

	public ChassiCaminhao() {
		super();
	}

	public double getAlturaCabine() {
		return alturaCabine;
	}

	public void setAlturaCabine(int peso) {
		if (peso > 1 && peso < 10) {
			this.alturaCabine = 2.486;
		} else if (peso >= 10 && peso < 20) {
			this.alturaCabine = 2.986;
		} else if (peso >= 20) {
			this.alturaCabine = 3.486;
		}
	}

	public String getAdicionaisCabine() {
		return adicionaisCabine;
	}

	public void setAdicionaisCabine(String adicionaisCabine) {
		this.adicionaisCabine = adicionaisCabine;
	}

	public String textoTipoChassi(int tipoChassi) {
		String textoTipo;
		if (tipoChassi == 0)
			textoTipo = "Convencional";
		else
			textoTipo = "Cavalo";
		return textoTipo;
	}

}
