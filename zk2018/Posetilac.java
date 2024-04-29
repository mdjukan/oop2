class Posetilac {
	static int poslednjiID = 0;
	private int id = ++poslednjiID;
	private String ime;
	private int godine;
	private int visina;

	Posetilac(String ime, int godine, int visina) {
		this.ime = ime;
		this.godine = godine;
		this.visina = visina;
	}
	
	String dohvatiId() {
		return ""+id;
	}

	String dohvatiIme() {
		return ime;
	}

	int dohvatiGodine() {
		return godine;
	}

	int dohvatiVisinu() {
		return visina;
	}
}
