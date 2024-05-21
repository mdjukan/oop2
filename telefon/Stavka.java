import java.awt.*;

class Stavka extends Panel {
	String naslov;
	String tekst;

	Label naslovLabela = new Label();
	Label tekstLabela = new Label();

	Stavka(String naslov, String tekst) {
		setLayout(new GridLayout(2,1));
		add(naslovLabela);
		add(tekstLabela);

		this.naslov = naslov;
		this.tekst = tekst;

		naslovLabela.setText(naslov);
		naslovLabela.setFont(new Font("Serif", Font.BOLD, 12));

		tekstLabela.setText(tekst);
	}

	void postaviNaslov(String naslov) {
		this.naslov = naslov;
		naslovLabela.setText(naslov);
	}
}
