import java.awt.*;

class Usisivac extends Figura {
	private int pomeraj;

	int pomeraj() {
		return pomeraj;
	}

	Usisivac(int x, int y) {
		super(x, y, 15);
		pomeraj = 15/2;
	}

private	int[] xs() {
		int[] theta = {330, 90, 210};
		int[] xs = new int[3];
		for (int i=0; i<3; ++i) {
			xs[i] = (int)(x() + r() * Math.cos(theta[i] * Math.PI/180));
		}

		return xs;
	}

private	int[] ys() {
		int[] theta = {330, 90, 210};
		int[] ys = new int[3];
		for (int i=0; i<3; ++i) {
			ys[i] = (int)(y() - r() * Math.sin(theta[i] * Math.PI/180));
		}

		return ys;
}
	
	@Override
	void naslikaj(Graphics g) {
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

	void pomeriUSmeru(Smer smer) {
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

void pomeri(Figura najbliza) {
	int dx = x() - najbliza.x();
	int dy = y() - najbliza.y();
	if (Math.abs(dx)>pomeraj) {
		if (dx < 0) {
			pomeriUSmeru(Smer.DESNO);
		} else {
			pomeriUSmeru(Smer.LEVO);
		}
	} else {
		if (dy < 0) {
			pomeriUSmeru(Smer.DOLE);
		} else {
			pomeriUSmeru(Smer.GORE);
		}
	}
}

//pomeri
}
