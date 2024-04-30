class Grad {
	private int x, y;
	private String naziv;

	Grad(String naziv, int x, int y) {
		this.x = x;
		this.y = y;
		this.naziv = naziv;
	}

	int x() {
		return x;
	}

	int y() {
		return y;
	}

	String naziv() {
		return naziv;
	}

	double rastojanje(Grad grad) {
		int dx = x - grad.x();
		int dy = y - grad.y();
		return Math.sqrt(dx*dx + dy*dy);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Grad)) {
			return false;
		}

		Grad g = (Grad)o;
		return x==g.x() && y==g.y() && naziv==g.naziv();
	}
}

