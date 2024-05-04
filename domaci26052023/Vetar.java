class Vetar extends Thread {
	Plac plac;
	Vetar(Plac plac) {
		this.plac = plac;
		pokreni();
	}
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			if (Math.random()<0.5) {
				plac.obavestiVetrenjace();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {}
		}
	}

	void pokreni() {
		start();
	}

	void zaustavi() {
		interrupt();
	}
}
