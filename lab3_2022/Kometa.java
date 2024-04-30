import java.awt.*;

class Kometa extends NebeskoTelo {
	Kometa(int x, int y, int r) {
		super(x, y, Color.GRAY, r);
	}

	@Override
	void naslikaj(Graphics g) {
		g.setColor(boja());
		double theta = 2 * Math.PI * Math.random();
		double fi = 2 * Math.PI / 5;
		int[] xs = new int[5];
		int[] ys = new int[5];
		for (int i=0; i<5; ++i) {
			xs[i] = (int)(x() + r() * Math.cos(theta + i * fi));
			ys[i] = (int)(y() + r() * Math.sin(theta + i * fi));
		}

		g.fillPolygon(xs, ys, 5);
	}
}
