import java.awt.*;

abstract class Figura {
	Figura(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	private int x, y;
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

	private int r;
	int r() {
		return r;
	}

	abstract void naslikaj(Graphics g);

	double rastojanje(Figura f) {
		int dx = x - f.x();
		int dy = y - f.y();
		return Math.sqrt(dx*dx - dy*dy);
	}

	boolean preklapa(Figura f) {
		double d = rastojanje(f);
		if (d<Math.abs(r-f.r())) return false; //jedan je unutar drugog
		return rastojanje(f)<r+f.r(); //sekuse
	}

	//vraca true ako this obuhvata f
	boolean obuhvata(Figura f) {
		double d = rastojanje(f);
		return d<r-f.r();
	}

	abstract boolean stajeNaScenu(int w, int h);
}
