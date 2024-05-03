import java.util.ArrayList;

class Porudzbina {
	static int poslednjiId = 1;
	private int id = poslednjiId++;
	private ArrayList<Palacinka> palacinke = new ArrayList<>();

	void dodajPalacinku(Palacinka palacinka) {
		palacinke.add(palacinka);
	}

	int brojPalacinka() {
		return palacinke.size();
	}

 int cena() {
		int t = 0;
		for (Palacinka palacinka : palacinke) {
			t += palacinka.cena();
		}

		return t;
	}

	void isprazni() {
		palacinke.clear();
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("porudzbina_"+id+"("+brojPalacinka()+"):");
		for (Palacinka palacinka : palacinke) {
			out.append("["+palacinka+"]");
		}

		return out.toString();
	}

	static Porudzbina generisi() {
		Porudzbina porudzbina = new Porudzbina();
		porudzbina.dodajPalacinku(Palacinka.generisi());
		if (Math.random()<0.5) {
			porudzbina.dodajPalacinku(Palacinka.generisi());
		}

		return porudzbina;
	}

	int id() {
		return id;
	}
}
