package lps.server;

public class ChassiOnibus extends Chassi {

	/*
	 * Aqui é a classe que reune apenas as características presente nos ônibus
	 */

	private String posicaoMotor;
	private int suspensao;

	public ChassiOnibus() {
		super();
	}

	public int getSuspensao() {
		return suspensao;
	}

	public void setSuspensao(int suspensao) {
		if (suspensao == 0) {
			// System.out.println("Suspensão a ar");
		} else {
			// System.out.println("Suspensão Comum");
		}
	}

	public void setPosicaoMotor(int motor) {
		if (motor < 280) {
			this.posicaoMotor = "Dianteiro";
		} else {
			this.posicaoMotor = "Traseiro";
		}
	}

	public String getPosicaoMotor() {
		return posicaoMotor;
	}

	public String textoTipoSuspensao(int suspensao) {
		String textoTipo;
		if (suspensao == 0)
			textoTipo = "Suspensão a Ar";
		else
			textoTipo = "Suspensão Metálica";
		return textoTipo;
	}

}
