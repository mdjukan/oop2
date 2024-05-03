import java.awt.*;

class Sto extends Canvas implements Runnable {
	Thread nit = new Thread(this);
	private Stanje stanje;

	Stanje stanje() {
		return stanje;
	}

	Palacinkarnica palacinkarnica;
	Sto(Palacinkarnica palacinkarnica) {
		this.palacinkarnica = palacinkarnica;
		stanje = Stanje.Slobodan;
	}

	int idPor;
	double theta = 0;

 @Override
	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();

		if (stanje==Stanje.Slobodan) {
			g.setColor(Color.GREEN);
			g.fillOval(0, 0, w, h);
		} else {
			g.setColor(Color.ORANGE);
			g.fillOval(0, 0, w, h);
			g.setColor(Color.RED);
			g.fillArc(0, 0, w, h, 0, (int)theta);
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				if (stanje==Stanje.Slobodan) {
					synchronized (palacinkarnica) {
						palacinkarnica.wait();
					}
					stanje = Stanje.Porucio;
					Porudzbina p = Porudzbina.generisi();
					idPor = p.id();
					palacinkarnica.dodajPorudzbinu(p);
				} else { 
					int dt = 50 + (int)(Math.random()*50);
					for (int i=0; i<100; ++i) {
						theta += 360.0 / 100;
						repaint();
						Thread.sleep(dt);
					}

					theta = 0;

					Thread.sleep(2000);
					synchronized (palacinkarnica) {
						palacinkarnica.notify();
					}

					stanje = Stanje.Slobodan;
					repaint();
					palacinkarnica.ukloniPorudzbinu(idPor);
				}
			}
		} catch (InterruptedException e) {}
	}

	void pokreni() {
		nit.start();
	}

	void zaustavi() {
		nit.interrupt();
	}
}
