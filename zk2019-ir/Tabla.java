import java.awt.*;

class Tabla extends Canvas implements Runnable {
	Thread nit = new Thread(this);
	Label duzina = new Label("Duzina:1");
	Zmija zmija;
	Muva muva;

	void reset() {
		zmija.rep.clear();
		zmija.pozicija().postaviX(nw/2);
		zmija.pozicija().postaviY(nh/2);
		muva.pozicija().postaviX((int)(Math.random()*nw));
		muva.pozicija().postaviY((int)(Math.random()*nh));
		repaint();
	}

	void azurirajLabelu() {
		duzina.setText("Duzina:"+zmija.duzina());
	}

	private int dt;
	private int nw, nh;
	Tabla(int nw, int nh, int dt) {
		this.nw = nw;
		this.nh = nh;
		this.dt = dt;
		zmija = new Zmija(new Pozicija(nw/2, nh/2));
		muva = new Muva(new Pozicija((int)(Math.random()*nw), (int)(Math.random()*nh)));
	}

	int nw() {
		return nw;
	}

	int nh() {
		return nh;
	}

	void postaviNw(int nw) {
		this.nw = nw;
	}

	void postaviNh(int nh) {
		this.nh = nh;
	}

	void postaviDt(int dt) {
		this.dt = dt;
	}

	boolean uOkviru(Pozicija p) {
		return 0<=p.x() && p.x()<nw && 0<=p.y() && p.y()<nh;
	}

	boolean zauzeta(Pozicija p) {
		return zmija.zauzima(p);
	}

	boolean pokrenuta = false;
	volatile boolean radi = true;
	volatile boolean gameover = false;

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					if (!radi) {
						wait();
					}
				}

				Thread.sleep(dt);
				try {
				zmija.pomeri(zmija.smerGlave, this);
				} catch (Greska g) {
					gameover = true;
					radi = false;
				}

				if (zmija.glava.pozicija().equals(muva.pozicija())) {
					zmija.dodajClanak();

					Pozicija novaPozicija;
					do {
						novaPozicija = new Pozicija((int)(Math.random()*nw), (int)(Math.random()*nh));
					}	while (zauzeta(novaPozicija));

					muva.pozicija().postaviX(novaPozicija.x());
					muva.pozicija().postaviY(novaPozicija.y());
				}

				azurirajLabelu();
				repaint();
			}
		} catch (InterruptedException e) {}
	}

	void pokreni() {
		nit.start();
		pokrenuta = true;
	}

	void pauziraj() {
		radi = false;
	}

 synchronized void nastavi() {
		radi = true;
		notify();
	}

	void trajnoZaustavi() {
		nit.interrupt();
	}

	@Override
	public void paint(Graphics g) {
		int w = getWidth() / nw;
		int h = getHeight() / nh;

		//[1] vertikalne linije
		for (int i=0; i<=nw; ++i) {
			g.drawLine(i*w, 0, i*w, getHeight());
		}
		//[2] horiznialne liniije
		for (int i=0; i<=nh; ++i) {
			g.drawLine(0, i*h, getWidth(), i*h);
		}

	 zmija.naslikaj(g, this);
		muva.naslikaj(g, this);

		if (gameover) {
			g.setColor(Color.BLACK);
			g.setFont(g.getFont().deriveFont(40f));
			g.drawString("Kraj igre!", getWidth()/2-20, getHeight()/2-10);
			gameover = false;
		}
	}
}
