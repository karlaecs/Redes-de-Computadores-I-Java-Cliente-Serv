package lps.client;

import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void enviarEmail(String emailCliente, String produto,
			List<String> listaFeatures)  {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"emailtesteredes1@gmail.com", "a9b8c7d6e5");
					}
				});

		session.setDebug(false);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("emailtesteredes1@gmail.com")); // Remetente

			Address[] toUser = InternetAddress // Destinatário(s)
					.parse(emailCliente);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Descrição do seu produto");// Assunto
			message.setText(gerarListaProdutoFinal(produto, listaFeatures));
			Transport.send(message);

			System.out.println("Email enviado. Obrigado por utilizar nosso software.");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public String gerarListaProdutoFinal(String produto,
			List<String> listaFeatures) {
		String[] listaProduto = produto.split(";");
		String[] tipoProduto = listaProduto[3].split(" ");

		String CODIGO = listaProduto[0];
		String MODELO = listaProduto[1];
		String PESO = MODELO.substring(0, 1);
		String MOTOR = MODELO.substring(2, 5);
		String EIXO = listaProduto[2];
		String TIPO = tipoProduto[0];
		String DESCRICAO = listaProduto[3];
		String FEATUREOBRIGATORIADESCRICAO = listaProduto[4];
		String FEATUREOBRIGATORIADADOS = listaProduto[5];

		String FREIOS = listaFeatures.get(0);
		String TRANSMISSAO = listaFeatures.get(1);
		String DIRECAO = listaFeatures.get(2);
		String FEATUREESPECIFICA = listaFeatures.get(3);
		String ESPECIFICA = listaFeatures.get(4);

		String codigodoproduto = "Código do Produto: " + CODIGO + "\n";

		String introducao = "Descrição do Produto: O " + MODELO
				+ " é um " + DESCRICAO + ", cuja "
				+ FEATUREOBRIGATORIADESCRICAO + " é " + FEATUREOBRIGATORIADADOS
				+ ".";

		String descricao1 = " Com seus "
				+ MOTOR
				+ " cv de potência e com "
				+ PESO
				+ "t de peso bruto, e eixos "
				+ EIXO
				+ ", o "
				+ MODELO
				+ " é perfeito para a aplicação proposta." 
				+ " Além disso o "
				+ TIPO
				+ " possui freios do tipo "
				+ FREIOS
				+ " e a direção "
				+ DIRECAO
				+ " o que resulta em baixa manutenção e consequentemente, menor custo de operação."
				+ "\n";
		String descricao2 = "\tO "
				+ MODELO
				+ " possui alta tecnologia embarcada, já que possui uma transmissão "
				+ TRANSMISSAO
				+ ". Com toda esta tecnologia, o motorista trabalhará"
				+ " melhor, resultando em maior rentabilidade. Por fim, o "
				+ TIPO + " possui uma " + FEATUREESPECIFICA + " do tipo "
				+ ESPECIFICA + " o que garante que o " + MODELO
				+ " seja um produto completo.\n";
		String descricao3 = "";

		if (!listaFeatures.equals("") && TIPO.equals("Caminhão")) {
			descricao3 = "\tNa cabine do seu caminhão, podem ser encontrados ";
			for (int i = 5; i < listaFeatures.size(); i++) {
				if (i != (listaFeatures.size() - 1))
					descricao3 = descricao3 + " " + listaFeatures.get(i)
							+ ",";
				else
					descricao3 = descricao3 + "e " + listaFeatures.get(i)
							+ ".\n";
			}

		}

		String descricao4 = "\n\tPor isso, peça seu " + TIPO + " " + MODELO
				+ ". Rentabilidade, resistência e tecnologia definem o " + TIPO
				+ " " + MODELO + ".\n";

		return codigodoproduto + introducao + descricao1 + descricao2
				+ descricao3 + descricao4;

	}

}
