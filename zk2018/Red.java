class Red {
	private class Element {
		Karta karta;
		Element prethodni, sledeci;

		Element(Karta karta) {
			this.karta = karta;
			prethodni = null;
			sledeci = null;
		}
	}

	private Element glava = null;
	private int brojKarata = 0;
	private Element rep = null;

	synchronized void dodaj(Karta karta) {
		Element e = new Element(karta);
		if (glava==null) {
			glava = e;
			rep = e;
		} else {
			rep.sledeci = e;
			e.prethodni = rep;
			rep = e;
		}

		brojKarata += 1;
		notify();
	}

	Karta uzmi() {
		if (brojKarata==0) {
			return null;
		}

		Karta karta = rep.karta;
		if (glava==rep) {
			glava = null;
			rep = null;
		} else {
			rep.prethodni.sledeci = null;
		}

		brojKarata -= 1;
		return karta;
	}

	int brojKarata() {
		return brojKarata;
	}
}
