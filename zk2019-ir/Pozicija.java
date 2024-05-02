class Pozicija {
	private int x, y;
	int x() {
		return x;
	}

	int y() {
		return y;
	}

	void postaviX(int x) {
		this.x = x;
	}

	void postaviY(int y) {
		this.y = y;
	}

	Pozicija(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void pomeri(Smer smer) {
		switch (smer) {
			case Gore:
				y -= 1;
				break;
			case Dole:
				y += 1;
				break;
			case Levo:
				x -= 1;
				break;
			case Desno:
				x += 1;
				break;
		}
	}

	Pozicija susedna(Smer smer) {
		Pozicija p = new Pozicija(x, y);
		p.pomeri(smer);
		return p;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Pozicija) {
			Pozicija p = (Pozicija)o;
			return p.x()==x && p.y()==y;
		}

		return false;
	}
}
