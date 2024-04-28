import java.awt.*;

/* Color.RED, Color.BLUE ... */

abstract class Figura {
	private Pozicija pozicija; //donji desno ugao-> abs
	private Color boja;

	Figura(Pozicija p, Color boja) {
		pozicija = p;
		this.boja = boja;
	}

	Pozicija pozicija() {
		return pozicija;
	}

	Color boja() {
		return boja;
	}

	abstract boolean zauzima(Pozicija p);
	abstract boolean mozeSePomeriti(Tabla tabla, Smer smer);
	abstract void naslikaj(Graphics g, Tabla tabla);

	void pomeri(Tabla tabla, Smer smer) throws Greska {
		if (mozeSePomeriti(tabla, smer)) {
			pozicija.pomeri(smer);
		} else {
			throw new Greska("Pomeranje nije moguce");
		}
	}
}
