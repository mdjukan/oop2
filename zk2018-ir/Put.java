class Put {
	private double brzina;
	private double cena;
	Grad pocetni, krajnji;

	Put(double brzina, double cena, Grad pocetni, Grad krajnji) {
		this.brzina = brzina;
		this.cena = cena;
		this.pocetni = pocetni;
		this.krajnji = krajnji;
	}

	double brzina() {
		return brzina;
	}

	double cena() {
		return cena;
	}

	double duzina() {
		return pocetni.rastojanje(krajnji);
	}

	double trajanje() {
		return duzina()/brzina;
	}

	@Override
	public String toString() {
		return pocetni.naziv() + "-" + krajnji.naziv();
	}
}
