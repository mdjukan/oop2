import java.awt.*;

abstract class Objekat {
	private int x, y;
	private Color boja;

	Objekat(int x, int y, Color boja) {
		this.x = x;
		this.y = y;
		this.boja = boja;
	}

	int x() {
		return x;
	}

	int y() {
		return y;
	}

	Color boja() {
		return boja;
	}

	void pomeriX(int dx) {
		x += dx;
	}

	void pomeriY(int dy) {
		y += dy;
	}

	double rastojanje(Objekat o) {
		int dx = x - o.x();
		int dy = y - o.y();
		return Math.sqrt(dx*dx + dy*dy);
	}

	abstract void naslikaj(Graphics g);
}
