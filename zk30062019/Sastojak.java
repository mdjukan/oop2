enum Sastojak {
	Plazma("Plazma"),
	Kikiriki("Kikiriki"),
	Kokos("Kokos"),
	Orasi("Orasi");

	static Sastojak[] sastojci = {Plazma, Kikiriki, Kokos, Orasi};

	private String naziv;
	Sastojak(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return naziv;
	}

	static Sastojak generisi() {
		return sastojci[(int)(Math.random()*sastojci.length)];
	}
}
