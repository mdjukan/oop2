import java.awt.*;

class Usisivac extends Figura {
	private int pomeraj;

	int pomeraj() {
		return pomeraj;
	}

	Usisivac(int x, int y) {
		super(x, y, 50);
		pomeraj = 15/2;

		System.out.println("x->"+ x);
		System.out.println("y->"+ y);
	}

private	int[] xs() {
		int[] theta = {-30, 90, 210};
		int[] xs = new int[3];
		for (int i=0; i<3; ++i) {
			xs[i] = (int)(x() + r() * Math.cos(theta[i]));
		}

		xs[0] = xs[1];

		return xs;
	}

private	int[] ys() {
		int[] theta = {-30, 90, 210};
		int[] ys = new int[3];
		for (int i=0; i<3; ++i) {
			ys[i] = (int)(y() - r() * Math.sin(theta[i]));
		}

		ys[0] = ys[1];

		return ys;
}
	
	@Override
	void naslikaj(Graphics g) {
		System.out.println("usisivac naslikan!");
		int[] xs = xs();
		int[] ys = ys();
		g.setColor(Color.RED);
		g.fillPolygon(xs, ys, 3);
	}

	@Override
	boolean stajeNaScenu(int w, int h) {
		int[] xs = xs();
		int[] ys = ys();

		for (int i=0; i<3; ++i) {
			if (!(xs[i]>0 && xs[i]<w && ys[i]>0 && ys[i]<h)) {
				return false;
			}
		}

		return true;
	}

	void pomeri(Smer smer) {
		switch (smer) {
			case GORE:
				postaviY(y()-pomeraj);
				break;
			case DOLE:
				postaviY(y()+pomeraj);
				break;
			case LEVO:
				postaviX(x()-pomeraj);
				break;
			case DESNO:
				postaviX(x()+pomeraj);
				break;
		}
	}
}
