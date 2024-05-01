import java.awt.*;

abstract class Figura {
	private Scena scena;
	private int x, y;
	private Color boja;

	Color boja() {
		return boja;
	}

	Scena scena() {
		return scena;
	}

	int x() {
		return x;
	}

	int y() {
		return y;
	}

	void postaviX(int x) {
		this.x = x;
	}

	void postaviY(int y) {
		this.y = y;
	}

	Figura(int x, int y, Color boja, Scena scena) {
		this.x = x;
		this.y = y;
		this.boja = boja;
		this.scena = scena;
	}

	abstract void naslikaj(Graphics g);
}
