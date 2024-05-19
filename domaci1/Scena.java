import java.awt.*;

class Scena extends Canvas implements Runnable {
	static final int dt = 50;

	private Simulacija sim;
	private SkupFigura skupFigura = new SkupFigura();
	private Thread nit = new Thread(this);
	private Usisivac usisivac;

	Scena(Simulacija sim) {
		setBackground(Color.GRAY);
		this.sim = sim;

		nit.start();
		repaint();
	}

	void dodajUsisivac() {
		usisivac = new	Usisivac(getWidth()/2, getHeight()/2);
		dodajFiguru(usisivac);
		skupFigura.prvaTekuca();
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
			} catch (Greska g) {
				System.out.println("Nije uspelo dodavanje!");
			}
		} else {
			System.out.println("Ne staje na scenu!");
		}

		if (radi==false && skupFigura.brojFigura()==2 && preDodavanja==1) {
			radi = true;
			notify();
		}
	}

	synchronized void ukloniFiguru(Figura figura) {
		skupFigura.izbaci(figura);
		if (skupFigura.brojFigura()==1) {
			System.out.println("Scena pauzirana!");
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

				Figura najbliza = skupFigura.najbliza(usisivac);
				if (usisivac.preklapa(najbliza)) {
					ukloniFiguru(najbliza);
				} else {
					usisivac.pomeri(najbliza);
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
