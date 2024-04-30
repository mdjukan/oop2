import java.awt.*;

class Planeta extends NebeskoTelo {
	static final Color[] boje = {Color.GREEN, Color.BLUE, Color.YELLOW, Color.RED};

	private boolean imaPrsten;
	Planeta(int x, int y, int r) {
		super(x, y, boje[(int)(Math.random() * boje.length)], r);
		imaPrsten = (Math.random()<0.25);
	}

	@Override
	void naslikaj(Graphics g) {
		g.setColor(boja());
		g.fillOval(x()-r(), y()-r(), 2*r(), 2*r());
		if (imaPrsten) {
			g.drawOval(x()-2*r(), y()-2*r(), 4*r(), 4*r());
		}
	}
}
