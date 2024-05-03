enum Premaz {
	Eurokrem(100, "Eurokrem"),
	Dzem(70, "Dzem");

	private int cena;
	private String naziv;

	Premaz(int cena, String naziv) {
		this.cena = cena;
		this.naziv = naziv;
	}

	int cena() {
		return cena;
	}

	@Override
	public String toString() {
		return naziv;
	}

	static Premaz generisi() {
		if (Math.random()<0.5) {
			return Eurokrem;
		} else {
			return Dzem;
		}
	}
}
