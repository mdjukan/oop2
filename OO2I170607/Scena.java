import java.awt.*;
import java.util.ArrayList;

class Scena extends Canvas {
	static final int w = 500, h = 500;
	ArrayList<Figura> figure = new ArrayList<>();
	Pak pak = new Pak(this);
	{
		dodajFiguru(pak);
	}

	void resetPolozaj() {
		pak.postaviX(w/2);
		pak.postaviY(h/2);
		pak.postaviDx(1);
		pak.postaviDy(1);

		levi.postaviX(30);
		levi.postaviY(h/2);
		levi.postaviDy(1);

		desni.postaviX(w-30);
		desni.postaviY(h/2);
		desni.postaviDy(-1);
	}

	void reset() {
		resetPolozaj();
		poeniLevi = 0;
		poeniDesni = 0;
		azurirajLabelu();
	}

	private int poeniLevi = 0;
	private int poeniDesni = 0;
	Igrac levi = new Igrac(20, this);
	Igrac desni  = new Igrac(Scena.w-30, this);
	{
		dodajFiguru(levi);
		dodajFiguru(desni);
	}

	Label poeni = new Label("0:0", Label.CENTER);
	{
		poeni.setFont(new Font("Arial", Font.BOLD, 25));
	}

	void azurirajLabelu() {
		poeni.setText(poeniLevi+":"+poeniDesni);
	}

	void dodajPoenLevom() {
		poeniLevi += 1;
		azurirajLabelu();
	}

	void dodajPoenDesnom() {
		poeniDesni += 1;
		azurirajLabelu();
	}

	void dodajFiguru(Figura figura) {
		figure.add(figura);
	}

	@Override
	public void paint(Graphics g) {
		for (Figura figura : figure) {
			figura.naslikaj(g);
		}
	}

	boolean pokrenuta = false;
	void pokreni() {
		pokrenuta = true;
		for (Figura figura : figure) {
			if (figura instanceof AktivnaFigura) {
				((AktivnaFigura)figura).pokreni();
			}
		}
	}

	void nastavi() {
		for (Figura figura : figure) {
			if (figura instanceof AktivnaFigura) {
				((AktivnaFigura)figura).nastavi();
			}
		}
	}

	void pauziraj() {
		for (Figura figura : figure) {
			if (figura instanceof AktivnaFigura) {
				((AktivnaFigura)figura).pauziraj();
			}
		}
	}

	void zaustavi() {
		for (Figura figura : figure) {
			if (figura instanceof AktivnaFigura) {
				((AktivnaFigura)figura).zaustavi();
			}
		}
	}
}
