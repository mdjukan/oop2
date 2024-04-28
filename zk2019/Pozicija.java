class Pozicija {
	private int x, y;

	Pozicija(int x, int y) {
		this.x = x;
		this.y = y;
	}

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

	void pomeri(Smer smer) {
		switch (smer) {
			case Levo:
				x -= 1;
				break;
			case Desno:
				x += 1;
				break;
			case Dole:
				y += 1;
				break;
		}
	}

	Pozicija pored(Smer smer) {
		switch (smer) {
			case Levo:
				return new Pozicija(x-1, y);
			case Desno:
				return new Pozicija(x+1, y);
			case Dole:
				return new Pozicija(x, y+1);
		}

		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pozicija)) {
			return false;
		}

		Pozicija p = (Pozicija)o;
		return x==p.x() && y==p.y();
	}
}
