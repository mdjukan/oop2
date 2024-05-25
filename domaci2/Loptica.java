import java.awt.*;

class Loptica extends AktivnaFigura {
	private double r;
	Loptica(Scena scena, double x, double y, int dt, double r) {
		super(scena, Color.GREEN, x, y, 'L', -1.0+2*Math.random(), -Math.random(), dt);
		this.r = r;
	}

	double r() {
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

				synchronized (scena()) {
					for (Figura figura : scena().figure()) {
						if (figura instanceof Cigla) {
							TipSudara sudar =  ((Cigla)figura).sudar(this);
							obradiSudar(sudar);

							if (sudar!=TipSudara.NEMA_SUDARA) {
								((Cigla)figura).pogodi();
								break;
							}
						} else if (figura instanceof Igrac) {
							TipSudara sudar =  ((Igrac)figura).sudar(this);
							obradiSudar(sudar);

							if (sudar!=TipSudara.NEMA_SUDARA) {
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
		g.fillOval((int)(x()-r()/2), (int)(y()-r()/2), (int)r(), (int)r());
	}
}
