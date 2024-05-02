import java.awt.*;
import java.util.ArrayList;

class Zmija extends Figura {
	ArrayList<Clanak> rep = new ArrayList<>();
	Clanak glava;
	Smer smerGlave = Smer.Desno;

	void postaviSmer(Smer smer) {
		smerGlave = smer;
	}

	int duzina() {
		return 1 + rep.size();
	}

	Zmija(Pozicija pozicija) {
		super(pozicija, Color.GREEN);
		glava = new Clanak(pozicija);
	}

	void dodajClanak() {
		Pozicija novi;
		if (rep.size()==0) {
			novi = glava.pozicija().susedna(smerGlave.kontra());
		} else if(rep.size()==1) {
			novi = rep.get(0).pozicija().susedna(izmedjuSuseda(glava.pozicija(), rep.get(0).pozicija()));
		} else {
			novi = rep.get(rep.size()-1).pozicija().susedna(izmedjuSuseda(rep.get(rep.size()-2).pozicija(), rep.get(rep.size()-1).pozicija()));
		}

		rep.add(new Clanak(novi));
		}

	@Override
	boolean zauzima(Pozicija p) {
		boolean zauzima = false;
		for (Clanak clanak : rep) {
			zauzima |= clanak.zauzima(p);
		}

		zauzima |= glava.zauzima(p);
		return zauzima;
	}

	@Override
	void naslikaj(Graphics g, Tabla tabla) {
		for (Clanak clanak : rep) {
			clanak.naslikaj(g, tabla);
		}

		int w = tabla.getWidth() / tabla.nw();
		int h = tabla.getHeight() / tabla.nh();

		g.setColor(boja());
		g.fillOval(pozicija().x() * w, pozicija().y() * h, w, h);
		g.setColor(Color.WHITE);
		g.fillOval(pozicija().x() * w + w/4, pozicija().y() * h + h/4, w/2, h/2);
	}

	static Smer izmedjuSuseda(Pozicija p1, Pozicija p2) {
		if (p1.y()==p2.y()) {
			if (p1.x()<p2.x()) {
				return Smer.Desno;
			} else {
				return Smer.Levo;
			}
		} else {
			if (p1.y()<p2.y()) {
				return Smer.Dole;
			} else {
				return Smer.Gore;
			}
		}
	}

	@Override
	void pomeri(Smer smer, Tabla tabla) throws Greska {
		if (rep.size()==0) {
			glava.pomeri(smer, tabla);
		} else {
			Smer smer1 = izmedjuSuseda(rep.get(0).pozicija(), glava.pozicija());
			Smer smer2;
			glava.pomeri(smer, tabla);

			for (int i=0; i<rep.size()-1; ++i) {
				smer2 = izmedjuSuseda(rep.get(i+1).pozicija(), rep.get(i).pozicija());
				rep.get(i).pomeri(smer1, tabla);
				smer1 = smer2;
			}

			rep.get(rep.size()-1).pomeri(smer1, tabla);
		}
	}
}
