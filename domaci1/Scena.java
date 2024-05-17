import java.awt.*;

class Scena extends Canvas implements Runnable {
	static final int dt = 50;

	private Simulacija sim;
	private SkupFigura skupFigura = new SkupFigura();
	private Thread nit = new Thread(this);

	Scena(Simulacija sim) {
		setBackground(Color.GRAY);
		this.sim = sim;
		
		try {
			skupFigura.dodajFiguru(new	Usisivac(250,250));
			skupFigura.prvaTekuca();
		} catch (Greska g) {}

		nit.start();
		repaint();
		//da li moze odmah getWidth(), getHeight() ??? --> nema dim
	}

	@Override
	public void paint(Graphics g) {
		skupFigura.naslikaj(g);
	}

	volatile private boolean radi = false;

	boolean radi() {
		return radi;
	}

	synchronized void dodajFiguru(Figura figura) {
		int preDodavanja = skupFigura.brojFigura();

		if (figura.stajeNaScenu(getWidth(), getHeight())) {
			try {
				skupFigura.dodajFiguru(figura);
			} catch (Greska g) {}
		}

		if (radi==false && skupFigura.brojFigura()==2 && preDodavanja==1) {
			radi = true;
			notify();
		}
	}

	synchronized void ukloniFiguru(Figura figura) {
		skupFigura.izbaci(figura);
		if (skupFigura.brojFigura()==1) {
			radi = false;
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					if (!radi) {
						wait();
					}
				}

				Usisivac usisivac = (Usisivac)skupFigura.tekuca();

				Figura najbliza = skupFigura.najbliza(usisivac);

				if (usisivac.preklapa(najbliza)) {
					ukloniFiguru(najbliza);
				} else {
					int dx = usisivac.x() - najbliza.x();
					int dy = usisivac.y() - najbliza.y();
					if (Math.abs(dx)>usisivac.pomeraj()) {
						if (dx < 0) {
							usisivac.pomeri(Smer.DESNO);
						} else {
							usisivac.pomeri(Smer.LEVO);
						}
					} else {
						if (dy < 0) {
							usisivac.pomeri(Smer.DOLE);
						} else {
							usisivac.pomeri(Smer.GORE);
						}
					}
				}

				repaint();
				Thread.sleep(dt);	
			}
		} catch (InterruptedException e) {}
	}

	void pauziraj() {
		radi = false;
	}

	synchronized	void pokreni() {
		if (skupFigura.brojFigura()!=1) {
			radi = true;
			notify();
		}
	}

	void zaustavi() {
		nit.interrupt();
	}
}
