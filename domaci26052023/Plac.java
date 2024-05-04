import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

class Plac extends Panel {
	Parcela generisiParcelu() {
		if (Math.random()<0.7) {
			return new TravnataPovrs(this);
		} else {
			return new VodenaPovrs(this);
		}
	}

	Parcela izabrana = null;
	Vetar vetar = new Vetar(this);
	ArrayList<Parcela> parcele = new ArrayList<>();

	void obavestiVetrenjace() {
		for (Parcela parcela : parcele) {
			if (parcela instanceof Vetrenjaca) {
				((Vetrenjaca)parcela).vetar();
			}
		}
	}

	void reset() {
		for (Parcela parcela : parcele) {
			if (parcela instanceof Potrosac) {
				((Potrosac)parcela).zaustavi();
			}

			if (parcela instanceof Proizvodjac) {
				((Proizvodjac)parcela).zaustavi();
			}
		}

		parcele.clear();
		izabrana = null;

		generisiParcele();
		azurirajPrikaz();
	}

	int m, n;
	Plac(int m, int n) {
		super();
		this.m = m;
		this.n = n;
		this.setLayout(new GridLayout(m, n, 5, 5));
		generisiParcele();
		for (Parcela parcela : parcele) {
			this.add(parcela);
		}
	}

	void azurirajPrikaz() {
		this.removeAll();
		for (Parcela parcela : parcele) {
			this.add(parcela);
		}
	}

	void generisiParcele() {
		parcele.clear();
		for (int i=0; i<m*n; ++i) {
			parcele.add(generisiParcelu());
		}
	}

	void postaviIzabranu(Parcela parcela) {
		if (izabrana!=null) {
			izabrana.umanjiFont();
		}

		izabrana = parcela;
		izabrana.uvecajFont();
	}

	int indeksIzabrane() {
		return parcele.indexOf(izabrana);
	}

	int susedneVodenePovrsi(Parcela parcela) {
		int t = 0;

		int idx = parcele.indexOf(parcela);
		int i = idx / n;
		int j = idx % n;

		int[] ox = {-1, 0, 1, -1, 1, -1, 0, 1};
		int[] oy = {-1, -1, -1, 0, 0, 1, 1, 1};

		for (int k=0; k<ox.length; ++k) {
			int ni = i+oy[k];
			int nj = j+ox[k];
			if (nj>=0 && ni>=0 && ni<n && nj<m) {
				if (parcele.get(ni * m + nj) instanceof VodenaPovrs) {
					t += 1;
				}
			}
		}
		return t;
	}

	void zaustaviIzabranu() {
			if (izabrana instanceof Potrosac) {
				((Potrosac)izabrana).zaustavi();
			}

			if (izabrana instanceof Proizvodjac) {
				((Proizvodjac)izabrana).zaustavi();
			}
	}
}
