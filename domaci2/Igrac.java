import java.awt.*;

class Igrac extends Figura {
	private final int POMERAJ = 10;
	private int w, h;

	Igrac(Scena scena, int x, int y, int w, int h) {
		super(scena, Color.BLUE, x, y, 'I');
		this.w = w;
		this.h = h;
	}

	void ispaliLopticu() {
		Loptica loptica = new Loptica(scena(), x(), (int)(y()-3.0/2*h), 10, 2*h);
		loptica.pokreni();
	}

	@Override
	void iscrtaj(Graphics g) {
		g.setColor(boja());
		g.fillRect((int)(x()-w/2), (int)(y()-h/2), w, h);
	}

	TipSudara sudar(Loptica loptica) {
		double r = loptica.r()/2;
		double xl = loptica.x();
		double yl = loptica.y();

		if ((x()-w/2<xl && xl<x()+w/2) &&
				((y()-h/2-r<yl && yl<y()-h/2 ) || (y()+h/2<yl && yl<y()+r+h/2))) {
			return TipSudara.HORIZONTALNI;
				}


		if ((y()-h/2<yl && yl<y()+h/2)	&&
				((x()-w/2-r <xl && xl<x()-w/2) || (x()+w/2<xl && xl<x()+w/2+r))) {
			return TipSudara.VERTIKALNI;
				}

		return TipSudara.NEMA_SUDARA;
	}

	void pomeri(Smer smer) {
		switch (smer) {
			case LEVO:
				if (x()-w/2-POMERAJ>0) {
					pomeri(-POMERAJ, 0);
					scena().repaint();
				}
				break;
			case DESNO:
				if (x()+w/2+POMERAJ<scena().getWidth()) {
					pomeri(POMERAJ, 0);
					scena().repaint();
				}
				break;
		}
	}
}
