import java.awt.*;

class Letelica extends Objekat {
	int a, h;
	Letelica(int x, int y, int a, int h) {
		super(x, y, Color.CYAN);
		this.a = a;
		this.h = h;
	}

	boolean preklapa(NebeskoTelo telo) {
		return rastojanje(telo)<telo.r();
	}

	@Override
	void naslikaj(Graphics g) {
		int[] xs = {x(), x()-a/2,x()+a/2};
		int[] ys = {y()-h/2, y()+h/2, y()+h/2};
		int n = 3;
		g.setColor(boja());
		g.fillPolygon(xs, ys, n);
	}
}
