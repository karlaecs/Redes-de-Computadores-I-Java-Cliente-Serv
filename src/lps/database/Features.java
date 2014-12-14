package lps.database;

import java.util.ArrayList;
import java.util.List;

/*
 * Um pequeno banco de dados simples de features numa ArrayList comum
 */

public class Features {

	public String codigo;
	public String tipo;
	public String opcao;
	private List<Features> arrayFeatures;

	public Features(String codigo, String tipo, String opcao) {
		this.codigo = codigo;
		this.tipo = tipo; 
		this.opcao = opcao;	}

	private void iniciarBanco() {

		List<Features> arrayFeatures = new ArrayList<Features>();

		arrayFeatures.add(new Features("F001","Freios","Hidráulico"));
		arrayFeatures.add(new Features("F002","Freios","a Ar"));
		arrayFeatures.add(new Features("F003","Freios","Hidrovácuo"));
		arrayFeatures.add(new Features("F004","Freios","ABS"));
		
		arrayFeatures.add(new Features("T001","Transmissão","Eaton FS 2305 C"));
		arrayFeatures.add(new Features("T002","Transmissão","Eaton FSO 4405 C"));
		arrayFeatures.add(new Features("T003","Transmissão","ZF S5420 HD"));
		arrayFeatures.add(new Features("T004","Transmissão","ZF 6S 1010 BO"));
		arrayFeatures.add(new Features("T006","Transmissão","Eaton FS 6406 B"));
		arrayFeatures.add(new Features("T007","Transmissão","Eaton FSB0 9406 AE"));
		
		arrayFeatures.add(new Features("D001","Direção","Elétrica"));
		arrayFeatures.add(new Features("D002","Direção","Hidráulica"));
		
		arrayFeatures.add(new Features("A001","Adicionais de Cabine","Ar Condicionado"));
		arrayFeatures.add(new Features("A002","Adicionais de Cabine","Vidros Elétricos"));
		arrayFeatures.add(new Features("A003","Adicionais de Cabine","Travas Elétricas"));
		arrayFeatures.add(new Features("A004","Adicionais de Cabine","Alarme de segurança"));
		arrayFeatures.add(new Features("A005","Adicionais de Cabine","Sistema de som"));
		arrayFeatures.add(new Features("A006","Adicionais de Cabine","GPS"));
		
		arrayFeatures.add(new Features("K001","Carroceria","Baú tradicional"));
		arrayFeatures.add(new Features("K002","Carroceria","Baú frigorifico"));
		arrayFeatures.add(new Features("K003","Carroceria","Caçamba comum"));
		arrayFeatures.add(new Features("K004","Carroceria","Caçamba graneleira"));
		arrayFeatures.add(new Features("K005","Carroceria","Poliguindaste"));
		arrayFeatures.add(new Features("K006","Carroceria","Basculante"));
		arrayFeatures.add(new Features("K007","Carroceria","Transporte de gado"));
		arrayFeatures.add(new Features("K008","Carroceria","Transporte de madeira"));
		arrayFeatures.add(new Features("K009","Carroceria","Transporte canavieiro"));
		arrayFeatures.add(new Features("K010","Carroceria","Tanque Pipa"));
		arrayFeatures.add(new Features("K011","Carroceria","Coletor de lixo"));
		arrayFeatures.add(new Features("K012","Carroceria","Carreta Agricola"));
		arrayFeatures.add(new Features("K013","Carroceria","Furgão Lonado"));
		arrayFeatures.add(new Features("K014","Carroceria","Cegonheira"));
		
		arrayFeatures.add(new Features("S001","Suspensão","Metálica"));
		arrayFeatures.add(new Features("S002","Suspensão","a Ar"));

		this.arrayFeatures = arrayFeatures;

	}

	public String consultaBanco(String codigo) {

		iniciarBanco();
		int i;
		boolean eValido = false;
		Features checker = null;

		for (i = 0; i < arrayFeatures.size(); i++) {
			if (codigo.equals(arrayFeatures.get(i).codigo)) {
				checker = arrayFeatures.get(i);
				eValido = true;
			}
		}		
		
		if (eValido) return checker.toString();

		return "ERRO";
	}

	public ArrayList<Features> listarFeatures(String tipo) {

		iniciarBanco();
		int i;
		ArrayList<Features> checker = new ArrayList<Features>();

		for (i = 0; i < arrayFeatures.size(); i++) {
			if (tipo.equals(arrayFeatures.get(i).tipo)) {
				checker.add(arrayFeatures.get(i));
			}
		}
		return checker;
	}

	public String toString() {
		return codigo + ";" + tipo + ";" + opcao;
	}

}
