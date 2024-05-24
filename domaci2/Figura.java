import java.awt.*;

abstract class Figura {
	private Scena scena;
	private int x, y;
	private char vrsta;
	private Color boja;

	Figura(Scena scena, Color boja, int x, int y, char vrsta) {
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
		x += (int)dx;
		y += (int)dy;
	}

	char vrsta() {
		return vrsta;
	}

	Color boja() {
		return boja;
	}

	int x() {
		return x;
	}

	int y() {
		return y;
	}

	Scena scena() {
		return scena;
	}

	void postaviBoju(Color boja) {
		this.boja = boja;
	}

	void postaviX(int x) {
		this.x = x;
	}

	void postaviY(int y) {
		this.y = y;
	}
}
