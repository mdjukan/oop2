import java.awt.*;

class Kamencic extends Figura {
	Kamencic(int x, int y) {
		super(x, y, 5);
	}

	@Override
	void naslikaj(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x()-r(), y()-r(), 2*r(), 2*r());
	}

	@Override
	boolean stajeNaScenu(int w, int h) {
		return x()>r() && y()>r() && x()+r()<w && y()+r()<h;
	}
}
