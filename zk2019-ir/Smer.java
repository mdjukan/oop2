enum Smer {
	Gore, Dole, Levo, Desno;

	Smer kontra() {
		switch (this) {
			case Gore:
				return Dole;
			case Dole:
				return Gore;
			case Levo:
				return Desno;
			case Desno:
				return Levo;
		}

		return null;
	}
}
