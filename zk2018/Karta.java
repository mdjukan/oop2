class Karta {
	private Posetilac posetilac;
	private Voznja voznja;
	private boolean posebna;

	Karta(Posetilac posetilac, Voznja voznja, boolean posebna) {
		this.posetilac = posetilac;
		this.voznja = voznja;
		this.posebna = posebna;
	}

	Posetilac dohvatiPosetioca() {
		return posetilac;
	}

	Voznja dohvatiVoznju() {
		return voznja;
	}

	boolean dohvatiPosebna() {
		return posebna;
	}

	int cena() {
		if (posebna) {
			return (int)(1.5*(double)voznja.dohvatiCenu());
		}

		return voznja.dohvatiCenu();
	}

	@Override
	public String toString() {
		return "Karta["+posetilac.dohvatiId()+"]";
	}
}
