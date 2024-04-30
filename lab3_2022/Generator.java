class Generator implements Runnable {
	Thread nit = new Thread(this);
	Svemir svemir;

	Generator(Svemir svemir) {
		this.svemir = svemir;
	}

	NebeskoTelo generisiTelo() {
		if (Math.random()<0.25) {
			return new Planeta((int)(Math.random()*200), 0, 10+(int)(20*Math.random()));
		} else {
			return new Kometa((int)(Math.random()*200), 0, 10+(int)(20*Math.random()));
		}
	}

	volatile boolean radi = true;

	@Override 
	public void run() {
		try {
			while (!Thread.interrupted() && svemir.nit.isAlive()) {
				synchronized (this) {
					if (!radi) {
						wait();
					}
				}

				svemir.dodajTelo(generisiTelo());
				Thread.sleep(900);
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
