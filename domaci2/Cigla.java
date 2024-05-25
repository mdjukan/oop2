import java.awt.*;

class Cigla extends AktivnaFigura {
	private int w, h;
	boolean pogodjena = false;
	Cigla(Scena scena, double x, double y, int dt, int w, int h) {
		super(scena, Color.RED, x, y, 'C', 0, 5, dt);
		this.w = w;
		this.h = h;
	}

	synchronized void pogodi() {
		pogodjena = true;
		postaviBoju(Color.GRAY);
		notify();
	}

	@Override
	public void run() {
		try {
			synchronized (this) {
				if (!pogodjena) {
					this.wait();
				}
			}

			while (!Thread.interrupted()) {
				scena().repaint();
				pomeri();

				if (scena().vanScene(this)) {
					unisti();
					zaustavi();
				}

				Thread.sleep(dt);
			}
		} catch (InterruptedException e) {}
	}

	@Override
	void iscrtaj(Graphics g) {
		g.setColor(boja());
		g.fillRect((int)(x()-w/2), (int)(y()-h/2), w, h);
	}

	TipSudara sudar(Loptica loptica) {
		if (pogodjena) {
			return TipSudara.NEMA_SUDARA;
		}

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
}
