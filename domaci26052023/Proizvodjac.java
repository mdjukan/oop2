import java.awt.*;

abstract class Proizvodjac extends Parcela implements Runnable {
	int dt;
	Baterija baterija;
	Thread nit = new Thread(this);

	Proizvodjac(char oznaka, Color boja, Plac plac, int dt, Baterija baterija) {
		super(oznaka, boja, plac);
		this.dt = dt + (int)(Math.random()*301);
		this.baterija = baterija;
	}

	abstract boolean uspesnoProizveo();
 abstract int proizvedenaEnergija();

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Thread.sleep(dt);
				if (uspesnoProizveo()) {
					baterija.dodajEnergiju(proizvedenaEnergija());

					synchronized (baterija) {
						baterija.notifyAll();
					}

					this.setForeground(Color.RED);
					Thread.sleep(300);
					this.setForeground(Color.BLACK);
				} else {
					Thread.sleep(300);
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
