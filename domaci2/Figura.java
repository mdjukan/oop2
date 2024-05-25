import java.awt.*;

abstract class Figura {
	private Scena scena;
	private double x, y;
	private char vrsta;
	private Color boja;

	Figura(Scena scena, Color boja, double x, double y, char vrsta) {
		this.scena = scena;
		this.boja = boja;
		this.x = x;
		this.y = y;
		this.vrsta = vrsta;
		scena.dodajFiguru(this);
	}

	void unisti() {
		scena.ukloniFiguru(this);
	}

	abstract void iscrtaj(Graphics g);

	void pomeri(double dx, double dy) {
		x += dx;
		y += dy;
	}

	char vrsta() {
		return vrsta;
	}

	Color boja() {
		return boja;
	}

	double x() {
		return x;
	}

	double y() {
		return y;
	}

	Scena scena() {
		return scena;
	}

	void postaviBoju(Color boja) {
		this.boja = boja;
	}

	void postaviX(double x) {
		this.x = x;
	}

	void postaviY(double y) {
		this.y = y;
	}
}
