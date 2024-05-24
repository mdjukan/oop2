import java.awt.*;

class Cigla extends AktivnaFigura {
	private int w, h;
	boolean pogodjena = false;
	Cigla(Scena scena, int x, int y, int dt, int w, int h) {
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
		g.fillRect(x()-w/2, y()-h/2, w, h);
	}

	TipSudara sudar(Loptica loptica) {
		if (pogodjena) {
			return TipSudara.NEMA_SUDARA;
		}

		if ((x()-w/2<loptica.x() && loptica.x()<x()+w/2) &&
				(Math.abs(y()-h/2-loptica.y())<loptica.r()/2 ||
					Math.abs(y()+h/2-loptica.y())<loptica.r()/2 )) {
			return TipSudara.HORIZONTALNI;
					}

		if ( (y()-h/2<loptica.y() && loptica.y()<y()+h/2) &&
				(Math.abs(x()-w/2-loptica.x())<loptica.r()/2 ||
					Math.abs(x()+w/2-loptica.x())<loptica.r()/2) ) {
			return TipSudara.VERTIKALNI;
					}

		return TipSudara.NEMA_SUDARA;
	}
}
