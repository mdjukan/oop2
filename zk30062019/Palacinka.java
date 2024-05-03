import java.util.ArrayList;
class Palacinka {
	Premaz premaz;
	ArrayList<Prilog> prilozi = new ArrayList<>();

	Palacinka(Premaz premaz) {
		this.premaz = premaz;
	}

	void dodajPrilog(Prilog prilog) throws GSastojak {
		for (Prilog p : prilozi) {
			if (p.equals(prilog)) {
				throw new GSastojak();
			}
		}

		prilozi.add(prilog);
	}

	int cena() {
		int t = premaz.cena();
		for (Prilog prilog : prilozi) {
			t += prilog.cena();
		}

		return t;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(premaz.toString());
		for (Prilog prilog : prilozi) {
			out.append(", " + prilog);
		}

		return out.toString();
	}

	static Palacinka generisi() {
		Palacinka palacinka = new Palacinka(Premaz.generisi());
		int brojPriloga = (int)Math.random()*3;
		while (palacinka.prilozi.size()!=brojPriloga) {
			try {
				palacinka.dodajPrilog(Prilog.generisi());
			} catch (GSastojak g) {}
		}

		return palacinka;
	}
}
