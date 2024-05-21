class Broj {
	private String kodDrzave;
 private String pozivniBroj;
	private String brojPretplatnika;

	String kodDrzave() {
		return kodDrzave;
	}

	String pozivniBroj() {
		return pozivniBroj;
	}

	String brojPretplatnika() {
		return brojPretplatnika;
	}

	boolean istaDrzava(Broj broj) {
		return kodDrzave==broj.kodDrzave();
	}

	boolean istaMreza(Broj broj) {
		return istaDrzava(broj) && pozivniBroj==broj.pozivniBroj();
	}

	boolean jednak(Broj broj) {
		return istaMreza(broj) && brojPretplatnika==broj.brojPretplatnika();
	}

	public String toString() {
		return "+" + kodDrzave + " " + pozivniBroj + " " + brojPretplatnika;
	}

	Broj(String zapis) {
		kodDrzave = zapis.substring(1,4);
		pozivniBroj = zapis.substring(5,7);
		brojPretplatnika = zapis.substring(8);
	}
}
