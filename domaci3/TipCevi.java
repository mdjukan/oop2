enum TipCevi {
	LEVO_DESNO("Levo-Desno"), DESNO_LEVO("Desno-Levo"),
	GORE_DOLE("Gore-Dole"), DOLE_GORE("Dole-Gore"),
	LEVO_DOLE("Levo-Dole"), DOLE_LEVO("Dole-Levo"),
	DOLE_DESNO("Dole-Desno"), DESNO_DOLE("Desno-Dole"),
	GORE_DESNO("Gore-Desno"), DESNO_GORE("Desno-Gore"),
	GORE_LEVO("Gore-Levo"), LEVO_GORE("Levo-Gore");

	String naziv;
	TipCevi(String naziv) {
		this.naziv = naziv;
	}

	static TipCevi odNaziva(String naziv) {
		switch (naziv) {
			case"Levo-Desno":
				return LEVO_DESNO;
			case "Desno-Levo":
				return DESNO_LEVO;
			case "Gore-Dole":
				return GORE_DOLE;
			case "Dole-Gore":
				return DOLE_GORE;
			case "Levo-Dole":
				return LEVO_DOLE;
			case "Dole-Levo":
				return DOLE_LEVO;
			case "Dole-Desno":
				return DOLE_DESNO;
			case "Desno-Dole":
				return DESNO_DOLE;
			case "Gore-Desno":
				return GORE_DESNO;
			case "Desno-Gore":
				return DESNO_GORE;
			case "Gore-Levo":
				return GORE_LEVO;
			case "Levo-Gore":
				return LEVO_GORE;
		}
		return null;
	}
}
