class Kontakt extends Stavka {
	String ime;
	Broj broj;

	Kontakt(String ime, Broj broj) {
		super(ime, broj.toString());
	}
}
