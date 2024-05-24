import java.awt.*;

class Loptica extends AktivnaFigura {
	private int r;
	Loptica(Scena scena, int x, int y, int dt, int r) {
		super(scena, Color.GREEN, x, y, 'L', -1.0+2*Math.random(), -Math.random(), dt);
		this.r = r;
	}

	int r() {
		return r;
	}

	@Override
	public void run() {
		int brojIteracija = 0;
		try {
			while (!Thread.interrupted()) {
				scena().repaint();
				brojIteracija += 1;
				if (brojIteracija==100) {
					dx *= 1.1;
					dy *= 1.1;
					brojIteracija = 0;
				}

				pomeri();

				TipSudara sudar = TipSudara.NEMA_SUDARA;

				synchronized (scena()) {
					for (Figura figura : scena().figure()) {
						if (figura instanceof Cigla) {
							sudar =  ((Cigla)figura).sudar(this);
							if (sudar!=TipSudara.NEMA_SUDARA) {
								((Cigla)figura).pogodi();
								obradiSudar(sudar);
								break;
							}
						} else if (figura instanceof Igrac) {
							sudar =  ((Igrac)figura).sudar(this);
							if (sudar!=TipSudara.NEMA_SUDARA) {
								obradiSudar(sudar);
								break;
							}
						}
					}
				}

				obradiSudar(scena().sudarZid(this));

				if (scena().vanScene(this)) {
					unisti();
					zaustavi();
				}

				Thread.sleep(dt);
			}
		} catch (InterruptedException e) {}
	}

	void obradiSudar(TipSudara sudar) {
		switch (sudar) {
			case HORIZONTALNI:
				dy *= -1;
				break;
			case VERTIKALNI:
				dx *= -1;
				break;
			case NEMA_SUDARA:
				;
		}
	}

	@Override
	void iscrtaj(Graphics g) {
		g.setColor(boja());
		g.fillOval(x()-r()/2, y()-r()/2, r(), r());
	}
}
