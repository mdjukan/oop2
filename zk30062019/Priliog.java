class Prilog {
	private Sastojak sastojak;
	private int cena;
	Sastojak sastojak() {
		return sastojak;
	}

	int cena() {
		return cena;
	}

	Prilog(Sastojak sastojak, int cena) {
		this.sastojak = sastojak;
		this.cena = cena;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Prilog) {
			Prilog p = (Prilog)o;
			return sastojak==p.sastojak();
		}

		return false;
	}

	@Override
	public String toString() {
		return sastojak.toString();
	}

	static Prilog generisi() {
		return new Prilog(Sastojak.generisi(), (int)(5+Math.random()*16));
	}
}
