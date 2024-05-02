import java.awt.*;

abstract class Figura {
	private Color boja;
	private Pozicija pozicija;
	Figura(Pozicija pozicija, Color boja) {
		this.pozicija = pozicija;
		this.boja = boja;
	}

	Color boja() {
		return boja;
	}

	Pozicija pozicija() {
		return pozicija;
	}

	void postaviKoordinate(Pozicija p) {
		pozicija.postaviX(p.x());
		pozicija.postaviY(p.y());
	}

	void postaviBoju(Color boja) {
		this.boja = boja;
	}

	abstract void naslikaj(Graphics g, Tabla tabla);
	abstract boolean zauzima(Pozicija p);
	abstract void pomeri(Smer smer, Tabla tabla) throws Greska;
}
