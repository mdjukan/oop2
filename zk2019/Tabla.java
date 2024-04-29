import java.awt.*;
import java.util.ArrayList;

class Tabla extends Canvas implements Runnable {
	Thread nit = new Thread(this);
	private ArrayList<Figura> figure = new ArrayList<>();
	static final Color[] boje = {Color.GREEN, Color.RED, Color.BLUE};
	private int poeni = 0;
	private Figura pokretna;
	private Figura uPripravnosti;
	private int nw, nh;

	Tabla(int nw, int nh) {
		this.nw = nw;
		this.nh = nh;

		uPripravnosti = nasumicnaFigura();
		dodajFiguru(uPripravnosti);
		postaviPokretnu();
		nit.start();
	}

void ukloniFigure() {
	figure.clear();
	uPripravnosti = nasumicnaFigura();
	dodajFiguru(uPripravnosti);
	postaviPokretnu();
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

void resetujPoene() {
	poeni = 0;
}

	void pomeriPokretnu(Smer smer) {
		try {
			pokretna.pomeri(this, smer);
		} catch (Greska g) {}
	}

	///////////////////////LABEL UPDATE////////////////

	Label poeniLabel;
	Label figureLabel;

	void zabeleziLabele(Label x, Label y) {
		this.poeniLabel = x;
		this.figureLabel = y;
	}

	void azurirajLabele() {
		poeniLabel.setText("Poeni: "+poeni);
		figureLabel.setText("Figura: " + figure.size());
	}

	///////////////////////////////////////////////


	Figura nasumicnaFigura() {
		Pozicija p = new Pozicija(nw/2, 0);
		Color boja = boje[(int)(Math.random()*boje.length)];
		if (Math.random()<0.5) {
			return new Jednodelni(p, boja);
		} else {
			return new Dvodelni(p, boja);
		}
	}

	void postaviPokretnu() {
		pokretna = uPripravnosti;
		try {
			pokretna.pomeri(this, Smer.Dole);
		} catch (Greska g) {
			//game over
		}
		uPripravnosti = nasumicnaFigura();
		dodajFiguru(uPripravnosti);
	}


	void dodajFiguru(Figura f) {
		figure.add(f);
	}

	void ukloniPopunjeneRedove() {
		ArrayList<Integer> popunjeni = new ArrayList<>();
		for (int i=0; i<nh; ++i) {
			boolean popunjen = true;

			for (int j=0; j<nw; ++j) {
				Pozicija trenutna = new Pozicija(j, i);
				try {
					popunjen &= this.zauzeta(trenutna);
				} catch (Greska g) {}
			}

			if (popunjen) {
				popunjeni.add(i);
			}
		}

		poeni += popunjeni.size() * 100;

		ArrayList<Figura> zaBrisanje = new ArrayList<>();
		for (Figura figura : figure) {
			if (popunjeni.contains(figura.pozicija().y())) {
				zaBrisanje.add(figura);
			}
		}

		for (Figura figura : zaBrisanje) {
			figure.remove(figura);
		}

		for (Figura figura : figure) {
			if (figura!=pokretna) {
				while (true) {
					try {
						figura.pomeri(this, Smer.Dole);
					} catch (Greska g) {
						break;
					}
				}
			}
		}

		azurirajLabele();
		if (popunjeni.size()!=0) {
			ukloniPopunjeneRedove();
		}
	}

	volatile boolean radi = true;

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					if (!radi) {
						wait();
					}
				}

				try {
					pokretna.pomeri(this, Smer.Dole);
				} catch (Greska g) {
					postaviPokretnu();
					ukloniPopunjeneRedove();
				}
				repaint();

				Thread.sleep(200);
			}
		} catch (InterruptedException e ){
		}
	}

	synchronized void pokreni() {
		radi = true;
		notify();
	}

	void pauziraj() {
		radi = false;
	}

	void prekini() {
		nit.interrupt();
	}

	@Override 
	public void paint(Graphics g) {
		int W = this.getWidth();
		int H = this.getHeight();
		int w = W / nw;
		int h = H / nh;

		for (int i=0; i<=nw; ++i) {
			g.drawLine(i*w, 0, i*w, H);
		}

		for (int i=0; i<=nh; ++i) {
			g.drawLine(0, i*h, W, i*h);
		}

		for (Figura figura : figure) {
			figura.naslikaj(g, this);
		} 
	}

	boolean zauzeta(Pozicija p) throws Greska {
		if (p.x()<0 || p.x()>=nw || p.y()<0 || p.y()>=nh) {
			throw new Greska("Pozicija van table!");
		}

		boolean z = false;
		for (Figura f : figure) {
			z |= f.zauzima(p);
		}

		return z;
	}
}
