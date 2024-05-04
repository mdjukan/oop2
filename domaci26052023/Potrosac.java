import java.awt.*;

// [dt] [energija] [dodaj potrosaca]

class Potrosac extends Parcela implements Runnable {
	Baterija baterija;
	int energija;
	int dt;
	Thread nit = new Thread(this);

	Potrosac(Plac plac, Baterija baterija, int energija, int dt) {
		super('P', Color.ORANGE, plac);
		this.baterija = baterija;
		this.energija = energija;
		this.dt = dt;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (baterija) {
					while (!baterija.uzmiEnergiju(energija)) {
						this.setForeground(Color.RED);
						baterija.wait();
					}
				}
				
				System.out.println(baterija);

				this.setForeground(Color.BLUE);
				Thread.sleep(200);
				this.setForeground(Color.BLACK);
				Thread.sleep(dt);
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
