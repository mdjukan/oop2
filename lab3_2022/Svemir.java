import java.awt.*;
import java.util.ArrayList;

class Svemir extends Canvas implements Runnable {
	ArrayList<NebeskoTelo> tela = new ArrayList<>();
	Letelica letelica = new Letelica(100, 200, 30, 30);
	Thread nit = new Thread(this);
	int istrazenost = 0;
	Label poeni;

	Svemir(Label poeni) {
		this.poeni = poeni;
	}


	void dodajTelo(NebeskoTelo telo) {
		tela.add(telo);
	}

	@Override
	public void paint(Graphics g) {
		setBackground(Color.BLACK);
		for (NebeskoTelo telo : tela) {
			telo.naslikaj(g);
		}

		letelica.naslikaj(g);
	}

	int preklapanjePlaneta() {
		int brojPreklapanja = 0;
		for (NebeskoTelo telo : tela) {
			if (telo instanceof Planeta && letelica.preklapa(telo)) {
				brojPreklapanja += 1;
			}
		}

		return brojPreklapanja;
	}

	boolean preklapanjeKometa() {
		for (NebeskoTelo telo : tela) {
			if (telo instanceof Kometa && letelica.preklapa(telo)) {
				return true;
			}
		}

		return false;
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

				repaint();
				for (NebeskoTelo telo : tela) {
					telo.pomeriY(5);
				}

				if (preklapanjeKometa()) {
					break; //
				}

				istrazenost += 1;
				istrazenost += 100 * preklapanjePlaneta();
				poeni.setText("Poeni: "+istrazenost);

				Thread.sleep(100);
			}
		} catch (InterruptedException e) {}
	}

	void pokreni() {
		nit.start();
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
}
