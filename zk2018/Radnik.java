class Radnik extends Thread {
	private String ime;
	private Voznja voznja;
	private Red red;
	private int zarada = 0;

	void dodajKartu(Karta karta) {
		red.dodaj(karta);
	}

	String dohvatiIme() {
		return ime;
	}

	Voznja dohvatiVoznju() {
		return voznja;
	}

	Radnik(String ime, Voznja voznja) {
		this.ime = ime;
		this.voznja = voznja;
		red = new Red();
	}

	@Override
	public void run() {
		Karta karta;

		try {
			while (!Thread.interrupted()) {
				synchronized (red) {
					while ((karta=red.uzmi())==null) {
						wait();
					}
				}

				if (voznja.mozeSeVoziti(karta.dohvatiPosetioca())) {
					synchronized (voznja) {
						while (!voznja.dodajPosetioca(karta.dohvatiPosetioca())) {
							wait();
						}
					}
					zarada += karta.cena();
				}

			}
		} catch (InterruptedException e) {}
	}

	void trajnoZaustavi() {
		this.interrupt();
	}

	int zarada() {
		return zarada;
	}
}
